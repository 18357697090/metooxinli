package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjLevelPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TjLevelPictureRepository extends JpaRepository<TjLevelPicture,Integer> {
    TjLevelPicture findByLevel(Integer level);
}
