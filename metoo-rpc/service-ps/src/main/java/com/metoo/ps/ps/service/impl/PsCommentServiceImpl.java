package com.metoo.ps.ps.service.impl;

import com.metoo.ps.ps.dao.entity.PsComment;
import com.metoo.ps.ps.dao.mapper.PsCommentMapper;
import com.metoo.ps.ps.dao.repository.PsCommentRepository;
import com.metoo.ps.ps.service.PsCommentService;
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
public class PsCommentServiceImpl extends ServiceImpl<PsCommentMapper, PsComment> implements PsCommentService {

    @Autowired
    private PsCommentRepository psCommentRepository;

    @Override
    public List<PsComment> findByScaleId(Integer scaleId, Pageable pageable) {
        return psCommentRepository.findByScaleId(scaleId, pageable);
    }
}
