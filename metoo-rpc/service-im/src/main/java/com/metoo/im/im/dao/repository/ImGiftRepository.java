package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImGift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImGiftRepository extends JpaRepository<ImGift,Integer> {
    ImGift findByGiftId(Integer giftId);
}
