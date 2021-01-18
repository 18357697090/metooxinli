package com.metoo.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoo.web.action.util.Message;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ps/{roomId}/{uid}")
public class PsWebSocket {

    //<AudioRoomId,Set<uid>>
    private static final Map<String,Set<String>> room = new ConcurrentHashMap<>();

    //<用户uid，用户的session>
    private static final Map<String,Session> userSession = new ConcurrentHashMap<>();


    @OnOpen
    public void connect(@PathParam("roomId") String roomId,@PathParam("uid") String uid, Session session) throws Exception{
        userSession.put(uid,session);
        System.out.println(uid+"-------"+roomId);
        if (!room.containsKey(roomId)) {
            Set<String> user = new HashSet<>();
            // 添加用户
            user.add(uid);
            room.put(roomId, user);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            room.get(roomId).add(uid);
        }
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomId") String roomId,@PathParam("uid") String uid,String message, Session session) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Message message1 = new Message();
            message1 = mapper.readValue(message,Message.class);
            System.out.println(room);
            if(!room.get(roomId).contains(uid)){
                message1.setType("system");
            }
            JSONObject json = JSONObject.fromObject(message1);
            this.broadcast(roomId,json.toString(),uid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void disConnect(@PathParam("roomId") String roomId,@PathParam("uid") String uid,Session session) throws Exception {
        System.out.println(uid+"推出房间");
        if(room.containsKey(roomId)) {
            if (room.get(roomId).contains(uid)) {
                room.get(roomId).remove(uid);
            }
        }

    }

    public static void broadcast(String roomId, String msg,String uid) {
        try {
            for (String uid1 : room.get(roomId)) {
                if (userSession.containsKey(uid1)){
                    userSession.get(uid1).getBasicRemote().sendText(msg);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
