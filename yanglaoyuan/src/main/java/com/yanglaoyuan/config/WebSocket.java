package com.yanglaoyuan.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.yanglaoyuan.utils.Token;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket/{tokenvalue}")
@Component
public class WebSocket {
    private static Map<String, WebSocket> webSocketSet = new ConcurrentHashMap<String, WebSocket>();
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("tokenvalue") String tokenvalue, Session session) {
        System.out.println(tokenvalue);
        String username = null;
        try {
            username = Token.getUsername(tokenvalue);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return;
        }
        this.session = session;
//        onlineCount.incrementAndGet(); //在线数加1
        webSocketSet.put(username, this);     //加入set中
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Collection<WebSocket> col = webSocketSet.values();
        while (true == col.contains(this)) {
            col.remove(this);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if ("heart".equals(message)) {
            try {
                sendMessage("heartOk");
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        Collection<WebSocket> col = webSocketSet.values();
        while (true == col.contains(this)) {
            col.remove(this);
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        webSocketSet.forEach((k, v) -> {
            try {
                v.sendMessage(message);
            } catch (IOException e) {
            }
        });
    }

    /**
     * 指定某个用户发送信息
     *
     * @param message
     */
    public static void setsendInfo(String name, String message) {
        webSocketSet.forEach((k, v) -> {
            if (name.equals(k)) {
                try {
                    v.sendMessage(message);
                } catch (IOException e) {
                }
            }
        });
    }

}
