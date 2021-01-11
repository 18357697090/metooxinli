package com.metoo.user.ta.dao.repository;

import com.metoo.user.ta.dao.entity.TaTaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface TaTaskUserRepository extends JpaRepository<TaTaskUser,Integer> {

    Long countAllByUidAndTaskId(Integer uid, Integer taskId);

    TaTaskUser findFirstByUidAndTaskId(Integer uid, Integer id);

    List<TaTaskUser> findAllByTaskId(Integer taskId);

    Integer countByTaskId(Integer taskId);
}
