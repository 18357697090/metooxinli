package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.vo.PsCapsuleVo;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsCapsuleService extends IService<PsCapsule> {

    PsCapsule findByCapsuleId(Integer capsuleId);

    List<PsCapsule> findCapsule();

    List<PsCapsule> findCapsuleRand(Integer limit);

    void updateReadNum(Integer capsuleId);
}
