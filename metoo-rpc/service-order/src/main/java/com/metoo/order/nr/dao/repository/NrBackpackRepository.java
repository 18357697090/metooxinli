package com.metoo.order.nr.dao.repository;

import com.metoo.order.nr.dao.entity.NrBackpack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface NrBackpackRepository extends JpaRepository<NrBackpack,Integer> {
    List<NrBackpack> findByUid(Integer uid);

    @Modifying
    @Query(value = "update nr_backpack set number = ? where uid=? and type=?",nativeQuery = true)
    int updateGoodsNumber(int number, int uid,int type);

}
