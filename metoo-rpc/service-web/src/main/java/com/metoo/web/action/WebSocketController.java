package com.metoo.web.action;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/app/{uid}")
public class WebSocketController extends TextWebSocketHandler {

    @OnOpen
    public void connect(@PathParam("uid") Integer uid, Session session) throws Exception{
        System.out.println("open: "+uid+"  --  -- --  session："+session);

    }

    @OnMessage
    public void receiveMsg(@PathParam("uid") Integer uid,String message, Session session) {


    }


    @OnClose
    public void disConnect(@PathParam("uid") Integer uid,Session session) throws Exception {
        System.out.println("disConnect : "+uid);
      

    }

    @OnError
    public void onError(@PathParam("uid") Integer uid,Throwable throwable) {
        System.out.println("[WebSocketServer] Connection Exception : userId = "+ uid + " , throwable = " + throwable);
    }


}
