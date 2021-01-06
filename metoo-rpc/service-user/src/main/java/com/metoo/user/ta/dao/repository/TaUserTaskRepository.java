package com.metoo.user.ta.dao.repository;

import com.metoo.user.ta.dao.entity.TaUserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface TaUserTaskRepository extends JpaRepository<TaUserTask,Integer> {

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
    List<TaUserTask> findByPublishIdOrAcceptId(Integer uid);

    TaUserTask findByTaskId(Integer taskId);
}
