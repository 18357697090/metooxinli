package com.metoo.order.im.dao.repository;

import com.metoo.metoo.entity.GiftRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRecordDao extends JpaRepository<GiftRecord,Integer> {

    List<GiftRecord> findByUid(Integer uid);

}


