package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsScaleComment;
import com.metoo.ps.ps.dao.mapper.PsScaleCommentMapper;
import com.metoo.ps.ps.dao.mapper.PsScaleCommentMapper;
import com.metoo.ps.ps.dao.repository.PsScaleCommentRepository;
import com.metoo.ps.ps.service.PsScaleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 心理测量量表用户评论表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class PsScaleCommentServiceImpl extends ServiceImpl<PsScaleCommentMapper, PsScaleComment> implements PsScaleCommentService {

    @Autowired
    private PsScaleCommentRepository psScaleCommentRepository;

    @Override
    public List<PsScaleComment> findByScaleId(Integer scaleId, Pageable pageable) {
        return psScaleCommentRepository.findByScaleId(scaleId, pageable);
    }
}
