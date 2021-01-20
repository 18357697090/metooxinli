package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsPourOutOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PsPourOutOrderRepository extends JpaRepository<PsPourOutOrder,Integer> {

    @Query(value = "select * from ps_pour_out_order where user_id=?1 and pour_id=?2 order by id desc LIMIT 1",nativeQuery = true)
    PsPourOutOrder UnfinishedConsult(Integer userId,Integer conId);
}
