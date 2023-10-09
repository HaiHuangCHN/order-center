package com.nice.order.center.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 参考：https://blog.csdn.net/qq_48721706/article/details/124995148?ops_request_misc=%257B%2522request%255Fid%2522%253A
 * %2522169663955916800192290113%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&amp;
 * request_id=169663955916800192290113&amp;biz_id=0&amp;utm_medium=distribute.pc_search_result
 * .none-task-blog-2~all~top_positive~default-1-124995148-null-null.142
 */
@Component
@Slf4j
// 接口路径类似：ws://localhost:8087/webSocket/userId
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {


    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 用户ID
    private String userId;

    // concurrent包的线程安全Set，用来存放每个客户端对应的 MyWebSocket 对象。
    // 虽然 @Component 默认是单例模式的，但 Spring Boot 还是会为每个 websocket 连接初始化一个bean，所以可以用一个静态 set 保存起来。
    // 注：底下 Web Socket 是当前类名
    private static final CopyOnWriteArraySet<WebSocket> WEB_SOCKETS = new CopyOnWriteArraySet<>();

    // 用来存在线连接用户信息
    private static final ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {
            this.session = session;
            this.userId = userId;
            WEB_SOCKETS.add(this);
            SESSION_POOL.put(userId, session);
            log.info("【websocket消息】有新的连接，总数为：" + WEB_SOCKETS.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        try {
            WEB_SOCKETS.remove(this);
            SESSION_POOL.remove(this.userId);
            log.info("【websocket消息】连接断开，总数为：" + WEB_SOCKETS.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端消息：" + message);
    }

    /**
     * 发送错误时的处理
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生异常" + error.getMessage(), error);
    }

    // 此为广播消息1
    public void sendAllMessage1(String message) {
        log.info("【websocket消息】广播消息：" + message);
        SESSION_POOL.forEach((sessionId, session) -> this.doSendMessage(message, sessionId));
    }

    // 此为广播消息2
    private static void sendAllMessage2(String message) {
        log.info("【websocket消息】广播消息：" + message);
        for (WebSocket webSocket : WEB_SOCKETS) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String message) {
        doSendMessage(message, userId);
    }

    // 此为单点消息(多人)
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            doSendMessage(message, userId);
        }
    }

    private void doSendMessage(String message, String userId) {
        Session session = SESSION_POOL.get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】单点消息：" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }


}
