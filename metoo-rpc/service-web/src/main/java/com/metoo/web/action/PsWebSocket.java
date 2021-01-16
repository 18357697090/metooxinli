package com.metoo.web.action;

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
    private static final Map<Integer,Set<Integer>> room = new ConcurrentHashMap<>();

    //<用户uid，用户的session>
    private static final Map<Integer,Session> userSession = new ConcurrentHashMap<>();

    //<AudioRoomId,Set<uid>>
    private static final Map<Integer, Set<Integer>> rooms = new ConcurrentHashMap<>();

    @OnOpen
    public void connect(@PathParam("uid") Integer uid,@PathParam("roomId") Integer roomId, Session session) throws Exception{
        userSession.put(uid,session);
        if (!rooms.containsKey(roomId)) {
            Set<Integer> room = new HashSet<>();
            // 添加用户
            room.add(uid);
            rooms.put(roomId, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomId).add(uid);
        }
    }

    @OnMessage
    public void receiveMsg(@PathParam("uid") Integer uid,@PathParam("roomId") Integer roomId,String message, Session session) {

    }

    @OnClose
    public void disConnect(@PathParam("uid") Integer uid,@PathParam("roomId") Integer roomId,Session session) throws Exception {
        room.get(roomId).remove(uid);
    }

    public static void broadcast(Integer AudioRoomId, String msg,Integer uid) {
        System.out.println("发送数据11--"+msg);
        try {
            for (Integer uid1 : rooms.get(AudioRoomId)) {
                if (uid1.equals(uid)){continue;}
                if (userSession.containsKey(uid1)){
                    userSession.get(uid1).getBasicRemote().sendText(msg);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
