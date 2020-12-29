package com.metoo.order.im.dao.repository;

import com.metoo.order.im.dao.entity.ImGiftRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImGiftRecordRepository extends JpaRepository<ImGiftRecord,Integer> {

    List<ImGiftRecord> findByUid(Integer uid);

}


