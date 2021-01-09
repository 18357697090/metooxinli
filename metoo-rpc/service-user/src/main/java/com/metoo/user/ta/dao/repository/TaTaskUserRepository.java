package com.metoo.user.ta.dao.repository;

import com.metoo.user.ta.dao.entity.TaTaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface TaTaskUserRepository extends JpaRepository<TaTaskUser,Integer> {

    @Modifying
    @Query(value = "update ta_user_task set accept_id=?1 where task_id=?2",nativeQuery = true)
    int updateAcceptId(Integer acceptId,Integer taskId);

    @Modifying
    @Query(value = "update ta_user_task set publish_state=1 where task_id=?1 and publish_id =?2",nativeQuery = true)
    int updatePublishState(Integer taskId,Integer uid);

    @Modifying
    @Query(value = "update ta_user_task set accept_state=?1 where task_id=?1 and accept_id =?2",nativeQuery = true)
    int updateAcceptState(Integer taskId,Integer uid);

    @Query(value = "select * from  ta_user_task where publish_id=?1 or accept_id=?1",nativeQuery = true)
    List<TaTaskUser> findByPublishIdOrAcceptId(Integer uid);

    TaTaskUser findByTaskId(Integer taskId);

    Long countAllByUidAndTaskId(Integer uid, Integer taskId);

    TaTaskUser findFirstByUidAndTaskId(Integer uid, Integer id);

    List<TaTaskUser> findAllByTaskId(Integer taskId);
}
