package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImSaveUserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImSaveUserMessageRepository extends JpaRepository<ImSaveUserMessage,Integer> {

    List<ImSaveUserMessage> findByUidAndSendId(Integer uid,Integer sendId);
}
