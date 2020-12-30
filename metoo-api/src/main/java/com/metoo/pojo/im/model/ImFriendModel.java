package com.metoo.pojo.im.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 好友列表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ImFriendModel implements Serializable {
    private Integer uid;
    private Integer friendId;
    //是否删除,1表示正常 2表示删除,3表示拉黑。
    private Integer state=1;
    private String spare;
}
