package com.metoo.user.tj.dao.repository;

import com.metoo.metoo.entity.LevelPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelPictureDao extends JpaRepository<LevelPicture,Integer> {
    LevelPicture findByLevel(Integer level);
}
