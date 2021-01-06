package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsScaleComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsScaleCommentRepository extends JpaRepository<PsScaleComment,Integer> {
    List<PsScaleComment> findByScaleId(int scaleId, Pageable pageable);
}
