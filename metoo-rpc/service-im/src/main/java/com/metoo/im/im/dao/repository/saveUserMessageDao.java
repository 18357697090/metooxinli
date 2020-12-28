package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity1.saveUserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface saveUserMessageDao extends JpaRepository<saveUserMessage,Integer> {

    List<saveUserMessage> findByUidAndSendId(Integer uid,Integer sendId);
}
