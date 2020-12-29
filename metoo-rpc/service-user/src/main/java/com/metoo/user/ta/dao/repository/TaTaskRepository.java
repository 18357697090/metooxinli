package com.metoo.user.ta.dao.repository;

import com.metoo.user.ta.dao.entity.TaTask;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaTaskRepository extends JpaRepository<TaTask,Integer> {

    @Query(value = "FROM TaTask WHERE state=1 and type=1")
    List<TaTask> findOrdinaryTask(Pageable pageable);

    @Query(value = "FROM TaTask WHERE state=1 and type=2")
    List<TaTask> findTutorialTask(Pageable pageable);

    @Modifying
    @Query(value = "update ta_task set state=2 where task_id=?1",nativeQuery = true)
    int updateTaskState(Integer taskId);

    TaTask findByTaskId(Integer taskId);
}
