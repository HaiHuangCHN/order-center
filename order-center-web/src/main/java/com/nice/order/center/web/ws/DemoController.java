package com.nice.order.center.web.ws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 跳转到 websocketDemo.html 页面，携带自定义的 cid信息。
     * http://localhost:8081/demo/toWebSocketDemo/user-1
     *
     * @param cid
     * @param model
     * @return
     */
    @GetMapping("/toWebSocketDemo/{cid}")
    public String toWebSocketDemo(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "websocketDemo";
    }

}
