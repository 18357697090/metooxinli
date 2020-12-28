package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftDao extends JpaRepository<Gift,Integer> {
    Gift findByGiftId(Integer giftId);
}
