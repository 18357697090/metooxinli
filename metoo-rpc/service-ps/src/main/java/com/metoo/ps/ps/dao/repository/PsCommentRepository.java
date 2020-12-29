package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsCommentRepository extends JpaRepository<PsComment,Integer> {
    List<PsComment> findByScaleId(int scaleId, Pageable pageable);
}
