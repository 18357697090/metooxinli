package com.metoo.ps.ps.dao.repository;

import com.metoo.metoo.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Integer> {
    List<Comment> findByScaleId(int scaleId, Pageable pageable);
}
