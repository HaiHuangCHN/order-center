package com.nice.order.center.web.temp;

import com.nice.order.center.common.util.JacksonUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Fill in desc
 *
 * @author hai.huang.a@outlook.com
 * @version 0.1.0
 * @date 2025/3/11 14:41
 * @since 0.1.0
 */
public class Temp {

    /**
     * Http Client
     */
    private CloseableHttpClient closeableHttpClient;

    /**
     * Request Config
     */
    private RequestConfig requestConfig;

    public void initHttpClient() {
        this.closeableHttpClient = HttpClients.custom().build();
        this.requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(10_000)
                .setConnectTimeout(10_000)
                .setConnectionRequestTimeout(10_000)
                .build();
    }

    @Test
    public void test() throws InterruptedException {

        this.initHttpClient();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/test/resources/req.json"))) {
            String readLine;
            int i = 0;
            boolean printByBatch = false;
            Set<String> set = new HashSet<>();

            while ((readLine = br.readLine()) != null) {
                // 过滤空行或无效行
                if (readLine.trim().isEmpty()) {
                    continue;
                }

                System.out.println("读取到的数据=" + readLine);

                // 转换模型，如：JSON 转 Object
                Payload payload = JacksonUtils.jsonToObject(readLine, Payload.class);

                boolean executeCondition = this.executeCondition(payload);

                if (executeCondition) {

                    if (!set.add(payload.consignmentNo + payload.warehouseCode + payload.topologyName)) {
                        System.out.println("skip duplicate");
                        continue;
                    }

                    HttpPost httpPost = new HttpPost("https://giws-gateway.eu.i4px.com/inbound/giws/operation/"
                            + payload.getTopologyName()
                            + "/putaway/sendES");
                    httpPost.setConfig(this.requestConfig);
                    System.out.println("请求地址=" + httpPost.getURI());

                    httpPost.setHeader("Content-type", "application/json");
                    httpPost.setHeader("Session", "e8fb1934-a666-402a-ad4a-d2c6da0eaa7d");
                    httpPost.setHeader("Warehouse", payload.warehouseCode);

                    String body = "{\"consignmentNos\":[\""+ payload.consignmentNo + "\"]}";
                    httpPost.setEntity(new StringEntity(body, CharEncoding.UTF_8));
                    System.out.println("请求体=" + body);

                    CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
                    String resStr = EntityUtils.toString(response.getEntity(), CharEncoding.UTF_8);
                    System.out.println("响应=" + resStr);

                    Thread.sleep(1000L);

                    i++;

                    if (printByBatch) {
                        if (i % 300 == 0) {
                            System.out.println("##########分隔符##########");
                        }
                    } else {
                        System.out.println("##########分隔符##########");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean executeCondition(Payload payload) {
        return payload != null
                && StringUtils.isNotBlank(payload.consignmentNo)
                && StringUtils.isNotBlank(payload.warehouseCode)
                && StringUtils.isNotBlank(payload.topologyName);
    }

    private String getContentType() {
        return MessageFormat.format("{0};charset={1}", "application/x-www-form-urlencoded", CharEncoding.UTF_8);
    }

    @Getter
    @Setter
    private static final class Model {
        private Payload payload;
    }

    @Getter
    @Setter
    private static final class Payload {
        private String warehouseCode;
        private String topologyName;
        private String consignmentNo;
    }
}
