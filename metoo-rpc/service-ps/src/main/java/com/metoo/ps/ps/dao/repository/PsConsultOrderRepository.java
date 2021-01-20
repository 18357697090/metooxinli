package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PsConsultOrderRepository extends JpaRepository<PsConsultOrder,Integer> {

    @Query(value = "select * from ps_consult_order where user_id=?1 and con_id=?2 order by id desc LIMIT 1",nativeQuery = true)
    PsConsultOrder UnfinishedConsult(Integer userId,Integer conId);
}
