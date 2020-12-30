package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.SaveCapsulePojo;
import com.metoo.pojo.ps.model.PsCapsuleModel;

import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsCapsuleApi {

    PsCapsuleModel findByCapsuleId(Integer capsuleId);

    RE myCapsule(Integer uid, Integer page);

    RE modifyCapsule(Integer state, Integer capsuleId);

    RE capsuleDetail(Integer page);

    RE saveCapsule(SaveCapsulePojo saveCapsulePojo, Integer uid);

    RE capsule(Integer capsuleId, Integer uid);

}
