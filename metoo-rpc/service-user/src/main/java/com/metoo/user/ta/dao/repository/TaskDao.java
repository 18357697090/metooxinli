package com.metoo.user.ta.dao.repository;

import com.metoo.metoo.entity1.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaskDao extends JpaRepository<Task,Integer> {

    @Query(value = "FROM Task WHERE state=1 and type=1")
    List<Task> findOrdinaryTask(Pageable pageable);

    @Query(value = "FROM Task WHERE state=1 and type=2")
    List<Task> findTutorialTask(Pageable pageable);

    @Modifying
    @Query(value = "update task set state=2 where task_id=?1",nativeQuery = true)
    int updateTaskState(Integer taskId);

    Task findByTaskId(Integer taskId);
}
