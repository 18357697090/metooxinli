package com.metoo.api.ps;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.model.PourOutCapsulePojo;
import com.metoo.pojo.old.model.SaveCapsulePojo;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.pojo.ps.vo.PsCapsuleVo;

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

    RE myCapsule(PsCapsuleVo vo);

    RE modifyCapsule(Integer state, Integer capsuleId);

    RE saveCapsule(PsCapsuleVo vo);

    RE findCapsuleDetailById(Integer capsuleId, Integer uid);

    RE psCapsuleIndexList(PsCapsuleVo vo);

    RE psCapsuleHostListMore(PsCapsuleVo vo);

    RE psCapsuleIndexBannerList(PsCapsuleVo vo);
}
