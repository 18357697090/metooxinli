package com.metoo.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoo.pojo.im.model.ImFriendModel;
import com.metoo.pojo.im.model.ImUserMessageModel;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.AudioRoomGiftMessageDTO;
import com.metoo.pojo.old.vo.ObjectTool;
import com.metoo.pojo.old.vo.ReturnGivingGiftDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.tools.AppMessage;
import com.metoo.tools.AppMessageObject;
import com.metoo.tools.AudioRoomMessage;
import com.metoo.tools.SeatInfo;
import com.metoo.web.config.tools.Repository;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/app/{uid}")
public class    WebSocketController extends TextWebSocketHandler {
    //<用户uid，用户的session>
    private static final Map<Integer,Session> userSession = new ConcurrentHashMap<>();

    //<AudioRoomId,Set<uid>>
    private static final Map<Integer,Set<Integer>> rooms = new ConcurrentHashMap<>();

    //<uid,AudioRoomId>
    private static final Map<Integer,Integer> userAudioRoomId = new ConcurrentHashMap<>();

    //<uid,userInfo>
    private static final Map<Integer, TjUserInfoModel> userInfo = new ConcurrentHashMap<>();

    //<AudioRoomId,map<麦序，uid>>
    private static final Map<Integer,Map<Integer,Integer>> microphone = new ConcurrentHashMap<>();

    //处理离线消息<每条消息的id，AppMessage>
    private static final Map<String, AppMessage> deliveryMessage = new ConcurrentHashMap<>();

    Repository repository=new Repository();



    @OnOpen
    public void connect(@PathParam("uid") Integer uid, Session session) throws Exception{
        System.out.println("open: "+uid+"  --  -- --  session："+session);
        //tomcat发送数据默认8192b，现在设置成1MB
        AppMessage appMessage = new AppMessage();
        session.setMaxTextMessageBufferSize(5*1024*1024);
        session.setMaxBinaryMessageBufferSize(5*1024*1024);
        //获取加入房间的人的个人信息
        //存入用户的uid以及session
        userSession.put(uid,session);
        appMessage.setType("system");
        appMessage.setMessage("success");
        session.getBasicRemote().sendText(HandleAppMessage(appMessage));
        System.out.println("11111");
        List<ImUserMessageModel> userMessages = repository.takeOffLineMessage(uid);
        System.out.println("22222");
        if (!userMessages.isEmpty()){
            System.out.println("---------发送用户"+uid+"离线消息-----------");
            for (ImUserMessageModel userMessage : userMessages){
                session.getBasicRemote().sendText(userMessage.getMessage());
                repository.deleteMessage(uid);
            }

        }
    }

    @OnMessage
    public void receiveMsg(@PathParam("uid") Integer uid,String message, Session session) {
        try {
            AppMessage appMessage = new AppMessage();
            ObjectMapper mapper = new ObjectMapper();
            appMessage = mapper.readValue(message,AppMessage.class);
            System.out.println("------收到"+appMessage.getType()+"消息----"+appMessage);
            if(appMessage.getType().equals("System")){
                System.out.println("-------收到系统消息----"+appMessage);
                return;
            }
            String type =appMessage.getType();
            switch (type){
                case "AudioRoom":
                    TjUserInfoModel userInfo1 =repository.findUserInfo(uid);
                    if(appMessage.getTo()==null||appMessage.getType1()==null||userInfo1 ==null){
                        System.out.println("------发送消息不完全-----");
                        appMessage.setSpare("error");
                        session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                        return;
                    }
                    userInfo.put(uid,userInfo1);
                    Integer AudioRoomId = appMessage.getTo();
                    switch (appMessage.getType1()) {
                        case "join":  //进入聊天室
                            //房间内用户的uid
                            System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"加入房间"+AudioRoomId+"--房间内信息"+rooms+"-----");
                            if (!rooms.containsKey(AudioRoomId)) {
                                System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"----创建房间");
                                Set<Integer> room = new HashSet<>();
                                // 添加用户
                                room.add(uid);
                                rooms.put(AudioRoomId, room);
                            } else {
                                // 房间已存在，直接添加用户到相应的房间
                                System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"----进入"+AudioRoomId+"房间，房间信息"+rooms+"--------");
                                rooms.get(AudioRoomId).add(uid);
                            }
                            //判断是否在其他房间进入，移除该房间的信息
                            Integer AudioRoomId1 =  userAudioRoomId.get(uid);
                            if(AudioRoomId1!=null){
                                System.out.println("--------用户"+uid+"在房间"+AudioRoomId+"内--------");
                                if(!AudioRoomId.equals(AudioRoomId1)){
                                    rooms.get(AudioRoomId1).remove(uid);
                                    System.out.println("--------用户"+uid+"在房间"+AudioRoomId1+"内，从之前的房间中退出来--------");
                                    Map<Integer,Integer> seatExit = microphone.get(AudioRoomId1);
                                    if(seatExit.containsValue(uid)){
                                        System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"在房间"+AudioRoomId+"内，并且在麦上"+seatExit+"--------");
                                        for (int i=1;i<9;i++){
                                            if(seatExit.containsKey(i)){
                                                if (seatExit.get(i).equals(uid)) {
                                                    appMessage.setType("AudioRoom");
                                                    appMessage.setType1("unMicrophone");
                                                    appMessage.setMessage("" + (i - 1));
                                                    microphone.get(AudioRoomId1).remove(i);
                                                    System.out.println("--------用户" + uid + userInfo.get(uid).getNickName() + "在房间" + AudioRoomId + "内，并且在" + i + "麦上--------");
                                                    broadcastAll(AudioRoomId1, HandleAppMessage(appMessage));
                                                }
                                            }
                                        }
                                    }
                                }else {
                                    System.out.println("------用户"+uid+"进入到同一个房间");
                                }
                            }
                            System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"没有在房间内，为用户添加房间号"+AudioRoomId+"--------");
                            userAudioRoomId.put(uid,AudioRoomId);

                            //发送麦上信息
                            if(microphone.containsKey(AudioRoomId)){
                                //map<麦序，uid>
                                System.out.println("--------用户"+uid+userInfo.get(uid).getNickName()+"在房间"+AudioRoomId+"内，有麦上信息，为用户推送麦上人的信息--------");
                                Map<Integer,Integer> seat = microphone.get(AudioRoomId);
                                AudioRoomMessage audioRoomMessage = repository.handleAppMessage(appMessage);
                                List<SeatInfo> seatInfos = new ArrayList<>();
                                for (int i =1;i<9;i++){
                                    Integer uid1 = seat.get(i);
                                    if(uid1!=null){
                                        System.out.println("--------用户"+uid1+userInfo.get(uid1).getNickName()+"在房间"+AudioRoomId+"内的"+i+"麦上，添加入seatInfos信息--------");
                                        SeatInfo seatInfo = new SeatInfo();
                                        seatInfo.setSeat(i);
                                        seatInfo.setUserInfo(repository.userInfoModel(userInfo.get(uid1)));
                                        seatInfos.add(seatInfo);
                                    }
                                }
                                JSONObject json = JSONObject.fromObject(appMessage.getObject());
                                ObjectMapper mapper1 = new ObjectMapper();
                                ObjectTool objectTool = mapper1.convertValue(json,ObjectTool.class);
                                audioRoomMessage.setObject(objectTool);
                                audioRoomMessage.setSeatInfos(seatInfos);
                                audioRoomMessage.setType1("microphoneInfo");
                                System.out.println("------"+uid+userInfo.get(uid).getNickName()+"进入房间"+AudioRoomId+"为他推送seatInfo------"+HandleAudioRoomMessage(audioRoomMessage));
                                session.getBasicRemote().sendText(HandleAudioRoomMessage(audioRoomMessage));
                            }else {
                                System.out.println("--------用户"+uid+"在房间"+AudioRoomId+"内，没有麦上信息--------");
                                System.out.println("创建microphone");
                                Map<Integer,Integer> seat = new ConcurrentHashMap<>();
                                microphone.put(AudioRoomId,seat);
                                AudioRoomMessage audioRoomMessage = repository.handleAppMessage(appMessage);
                                List<SeatInfo> seatInfos = new ArrayList<>();
                                JSONObject json = JSONObject.fromObject(appMessage.getObject());
                                ObjectMapper mapper1 = new ObjectMapper();
                                ObjectTool objectTool = mapper1.convertValue(json,ObjectTool.class);
                                audioRoomMessage.setObject(objectTool);
                                audioRoomMessage.setSeatInfos(seatInfos);
                                audioRoomMessage.setType1("microphoneInfo");
                                session.getBasicRemote().sendText(HandleAudioRoomMessage(audioRoomMessage));
                                System.out.println("------"+uid+userInfo.get(uid).getNickName()+"进入房间"+AudioRoomId+"为他推送seatInfo------"+HandleAudioRoomMessage(audioRoomMessage));
                            }
                            break;
                        case "chat":
                        case "picture":
                            System.out.println("------收到用户"+uid+userInfo.get(uid).getNickName()+"的群发信息，内容为"+appMessage.getMessage());
                            JSONObject json = JSONObject.fromObject(appMessage.getObject());
                            ObjectMapper mapper1 = new ObjectMapper();
                            ObjectTool objectTool = mapper1.convertValue(json,ObjectTool.class);
                            AudioRoomMessage audioRoomMessage = repository.handleAppMessage(appMessage);
                            audioRoomMessage.setObject(objectTool);
                            List<SeatInfo> chatSeatInfos = new ArrayList<>();
                            SeatInfo chatSeatInfo = new SeatInfo();
                            chatSeatInfo.setUserInfo(repository.userInfoModel(userInfo.get(uid)));
                            chatSeatInfos.add(chatSeatInfo);
                            audioRoomMessage.setSeatInfos(chatSeatInfos);
                            JSONObject json1 = JSONObject.fromObject(audioRoomMessage);
                            repository.saveAudioRoomChatRecord(AudioRoomId,json1.toString());
                            broadcastAll(AudioRoomId, HandleAudioRoomMessage(audioRoomMessage));
                            break;
//                        case "joinPourOut":
//                            if (!rooms.containsKey(AudioRoomId)) {
//                                Set<Integer> room = new HashSet<>();
//                                // 添加用户
//                                room.add(uid);
//                                rooms.put(AudioRoomId, room);
//                            } else {
//                                // 房间已存在，直接添加用户到相应的房间
//                                rooms.get(AudioRoomId).add(uid);
//                            }
//
//                            break;
                        case "microphone":
                            System.out.println("-------用户"+uid+"在房间"+AudioRoomId+"上麦------microphone---"+microphone+"---------");
                            Integer seat = Integer.parseInt(appMessage.getMessage())+1;
                            if (!microphone.containsKey(AudioRoomId)){
                                System.out.println("------没有microphone房间-------");
                                return;
                            }
                            if (microphone.get(AudioRoomId).containsValue(uid)){
                                System.out.println("-----------用户"+uid+"已经在麦上-----------");
                                return;
                            }
                            if(!microphone.get(AudioRoomId).containsKey(seat)){
                                microphone.get(AudioRoomId).put(seat,uid);
                                JSONObject json11 = JSONObject.fromObject(appMessage.getObject());
                                ObjectMapper mapper11 = new ObjectMapper();
                                ObjectTool objectTool11 = mapper11.convertValue(json11,ObjectTool.class);
                                AudioRoomMessage audioRoomMessage11 = repository.handleAppMessage(appMessage);
                                audioRoomMessage11.setObject(objectTool11);
                                SeatInfo seatInfo = new SeatInfo();
                                seatInfo.setSeat(seat);
                                seatInfo.setUserInfo(repository.userInfoModel(userInfo.get(uid)));
                                List<SeatInfo> seatInfos = new ArrayList<>();
                                seatInfos.add(seatInfo);
                                audioRoomMessage11.setSeatInfos(seatInfos);
                                audioRoomMessage11.setType1("microphone");
                                System.out.println("---------用户"+uid+"在"+seat+"麦，上麦成功---"+microphone+"-----------");
                                broadcastAll(AudioRoomId, HandleAudioRoomMessage(audioRoomMessage11));
                            }
                            break;
                        case "unMicrophone":
                            Integer seat1 = Integer.parseInt(appMessage.getMessage())+1;
                            microphone.get(AudioRoomId).remove(seat1);
                            broadcastAll(AudioRoomId,HandleAppMessage(appMessage));
                            break;
                        case "exit":
                            System.out.println("--------用户"+uid+"退出房间，房间内移除用户----------------");
                            rooms.get(AudioRoomId).remove(uid);
                            Map<Integer,Integer> seatExit = microphone.get(AudioRoomId);
                            System.out.println("------查询用户"+uid+"是否在麦上"+seatExit+"----------");
                            if(seatExit.containsValue(uid)){
                                System.out.println("------用户"+uid+"在麦上"+seatExit+"----------");
                                for (int i=1;i<9;i++){
                                    if(seatExit.containsKey(i)){
                                        if(seatExit.get(i).equals(uid)) {
                                            System.out.println("-------"+uid+"移除麦克风---------");
                                            appMessage.setType1("unMicrophone");
                                            appMessage.setMessage("" + (i - 1));
                                            microphone.get(AudioRoomId).remove(i);
                                            broadcastAll(AudioRoomId, HandleAppMessage(appMessage));
                                        }
                                    }
                                }
                            }
                            userAudioRoomId.remove(uid);
                            break;
                        case "gift":
                            String[] strArr= appMessage.getMessage().split(",");
                            Integer acceptedId = Integer.parseInt(strArr[0]);
                            Integer giftId = Integer.parseInt(strArr[1]);
                            String number = strArr[2];
                            ReturnGivingGiftDTO returnGivingGiftDTO = repository.givingGift(uid,acceptedId,giftId,number);
                            if(returnGivingGiftDTO.getState().equals("success")){
                                List<TjUserInfoPojoModel> userInfos = new ArrayList<>();
                                userInfos.add(repository.userInfoModel(userInfo.get(uid)));
                                userInfos.add(repository.userInfoModel(userInfo.get(acceptedId)));
                                AudioRoomGiftMessageDTO audioRoomGiftMessageDTO = new AudioRoomGiftMessageDTO();
                                audioRoomGiftMessageDTO.setReturnGivingGiftDTO(returnGivingGiftDTO);
                                audioRoomGiftMessageDTO.setUserInfos(userInfos);
                                audioRoomGiftMessageDTO.setNumber(number);
                                appMessage.setObject(audioRoomGiftMessageDTO);
                                broadcastAll(AudioRoomId, HandleAppMessage(appMessage));
                                appMessage.setMessage(null);
                                appMessage.setObject(null);
                                appMessage.setType1("balance");
                                appMessage.setMessage(returnGivingGiftDTO.getBalance().toPlainString());
                                session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                            }else {
                                appMessage.setType1("notEnoughMoney");
                                session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                            }

                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + appMessage.getType1());
                    }
                    break;
                case "Friend":
                    // TODO 好友申请推送，离线推送
//                    if(appMessage.getType1().equals("addFriend")){
//
//                    }
                    Integer id=appMessage.getTo();
                    System.out.println("--------收到"+uid+userInfo.get(uid)+"发送给好友"+id+"的信息------------");
                    Session session1 = userSession.get(id);
                    repository.saveUserMessage(uid,id,appMessage.getMessage());
                    System.out.println("--------保存消息------");
                    ImFriendModel friend = repository.checkFriendShip(id,uid);
                    if(friend!=null){
                        if (friend.getState()==2){
                            JSONObject json = JSONObject.fromObject(appMessage.getObject());
                            AppMessageObject appMessageObject = mapper.convertValue(json,AppMessageObject.class);
                            appMessageObject.setUid(id);
                            appMessage.setTo(uid);
                            appMessage.setObject(appMessageObject);
                            appMessage.setMessage("【对方与你解除了好友关系！】");
                            session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                            System.out.println("++++++++++++++++++++++++不是好友关系");
                            return;
                        }else if (friend.getState()==3){
                            JSONObject json = JSONObject.fromObject(appMessage.getObject());
                            AppMessageObject appMessageObject = mapper.convertValue(json,AppMessageObject.class);
                            appMessageObject.setUid(id);
                            appMessage.setTo(uid);
                            appMessage.setObject(appMessageObject);
                            appMessage.setMessage("【对方拒收消息！】");
                            session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                            System.out.println("++++++++++++++++++++++++拉黑");
                            return;
                        }

                    }
                    if (session1==null){
                        System.out.println("------用户"+id+"不在线------");
                        JSONObject json = JSONObject.fromObject(appMessage);
                        repository.saveUserMessageOffOline(id,uid,json.toString());
                        System.out.println("---------保存离线消息---------");
                    }else {
                        userSession.get(id).getBasicRemote().sendText(HandleAppMessage(appMessage));
                        System.out.println("--------用户"+"在线，发送消息"+appMessage+"----------");
                        deliveryMessage.put(appMessage.getSpare(),appMessage);
                        System.out.println("--------5秒后准备验证消息发是否发送成功-----");
                        Thread.sleep(5000);
                        AppMessage appMessage2 = deliveryMessage.get(appMessage.getSpare());
                        System.out.println("--------验证消息---------");
                        if(appMessage2!=null){
                            System.out.println("----------用户"+id+"没有收到消息--------");
                            JSONObject json = JSONObject.fromObject(appMessage);
                            repository.saveUserMessageOffOline(id,uid,json.toString());
                            System.out.println("----------存入离线消息"+appMessage+"----------");
                        }else {
                            System.out.println("-------用户"+id+"收到消息------");
                        }
                    }
                    break;
                case "System":
                    appMessage.setType1("success");
                    session.getBasicRemote().sendText(HandleAppMessage(appMessage));
                    break;


                case "deliveryMessage":
                    String spare = appMessage.getSpare();
                    deliveryMessage.remove(spare);
                    break;

            }



        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @OnClose
    public void disConnect(@PathParam("uid") Integer uid,Session session) throws Exception {
        AppMessage appMessage = new AppMessage();
        System.out.println("disConnect : "+uid);
        boolean x = userSession.remove(uid,session);
        if(x){
            userInfo.remove(uid);
            System.out.println("------成功删除"+uid+"的session------移除"+uid+"的userInfo--------");
        }
        Thread.sleep(2000);
        if(!userSession.containsKey(uid)){

            Integer audioRoomId = userAudioRoomId.get(uid);
            System.out.println("----房间号"+userAudioRoomId);
            System.out.println("---------用户"+uid+"没有重连，准备删除用户房间号"+audioRoomId+"以及麦克风，让其下麦---------");
            if(audioRoomId!=null){
                rooms.get(audioRoomId).remove(uid);
                System.out.println("---------删除用户"+uid+"的房间"+audioRoomId+"----------");
                Map<Integer,Integer> seatExit = microphone.get(audioRoomId);
                if(seatExit.containsValue(uid)){
                    System.out.println("-------用户有在房间的麦上"+seatExit+"--------");
                    for (int i=1;i<9;i++){
                        if(seatExit.containsKey(i)){
                            if (seatExit.get(i).equals(uid)) {
                                System.out.println("---------让用户"+uid+"下-"+i+"号麦---------");
                                appMessage.setType("AudioRoom");
                                appMessage.setType1("unMicrophone");
                                appMessage.setMessage("" + (i - 1));
                                microphone.get(audioRoomId).remove(i);
                                broadcastAll(audioRoomId, HandleAppMessage(appMessage));
                            }
                        }
                    }
                }
                userAudioRoomId.remove(uid);
            }
        }

    }

    @OnError
    public void onError(@PathParam("uid") Integer uid,Throwable throwable) {
        System.out.println("[WebSocketServer] Connection Exception : userId = "+ uid + " , throwable = " + throwable);
    }



    // 按照房间名进行广播，除了自己
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

    //发送所有人
    public static void broadcastAll(Integer AudioRoomId, String msg) {
        try {
            for (Integer uid1 : rooms.get(AudioRoomId)) {
                if (userSession.containsKey(uid1)){
                    System.out.println("--发送给"+uid1+"的消息----"+msg);
                    userSession.get(uid1).getBasicRemote().sendText(msg);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //appMessage转String
    private String HandleAppMessage(AppMessage appMessage){
        JSONObject json = JSONObject.fromObject(appMessage);
        return json.toString();
    }

    private String HandleAudioRoomMessage(AudioRoomMessage audioRoomMessage){
        JSONObject json = JSONObject.fromObject(audioRoomMessage);
        return json.toString();
    }

    public static void exitRoom(Integer uid){
        if(!userSession.containsKey(uid)){
            AppMessage  appMessage = new AppMessage();
            Integer audioRoomId = userAudioRoomId.get(uid);
            System.out.println("----房间号"+userAudioRoomId);
            System.out.println("---------用户"+uid+"没有重连，准备删除用户房间号"+audioRoomId+"以及麦克风，让其下麦---------");
            if(audioRoomId!=null){
                rooms.get(audioRoomId).remove(uid);
                System.out.println("---------删除用户"+uid+"的房间"+audioRoomId+"----------");
                Map<Integer,Integer> seatExit = microphone.get(audioRoomId);
                if(seatExit.containsValue(uid)){
                    System.out.println("-------用户有在房间的麦上"+seatExit+"--------");
                    for (int i=1;i<9;i++){
                        if(seatExit.containsKey(i)){
                            if (seatExit.get(i).equals(uid)) {
                                System.out.println("---------让用户"+uid+"下-"+i+"号麦---------");
                                appMessage.setType("AudioRoom");
                                appMessage.setType1("unMicrophone");
                                appMessage.setMessage("" + (i - 1));
                                microphone.get(audioRoomId).remove(i);
                                JSONObject json = JSONObject.fromObject(appMessage);
                                broadcastAll(audioRoomId, json.toString());
                            }
                        }
                    }
                }
                userAudioRoomId.remove(uid);
            }
        }
    }

    public static Set<Integer> OnlineUser(Integer audioRoomId){
        return rooms.get(audioRoomId);
    }

}
