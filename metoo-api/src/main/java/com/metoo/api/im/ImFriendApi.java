package com.metoo.api.im;


import com.loongya.core.util.RE;
import com.metoo.pojo.im.model.ImFriendModel;

/**
 * <p>
 * 好友列表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImFriendApi {

    RE friendList(Integer uid);

    RE deleteFriend(Integer uid, Integer friendId);

    RE blackFriends(Integer uid, Integer friendId);

    RE findBlackFriends(Integer uid, Integer friendId);
    ImFriendModel findByUidAndFriendId(Integer uid, Integer firendId);


}
