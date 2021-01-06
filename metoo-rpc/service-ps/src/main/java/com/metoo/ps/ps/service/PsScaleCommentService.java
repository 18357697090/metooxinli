package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsScaleComment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 心理测量量表用户评论表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsScaleCommentService extends IService<PsScaleComment> {

    List<PsScaleComment> findByScaleId(Integer scaleId, Pageable pageable);
}
