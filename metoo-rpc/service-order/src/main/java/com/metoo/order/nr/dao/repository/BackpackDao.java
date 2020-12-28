package com.metoo.order.nr.dao.repository;

import com.metoo.metoo.entity.Backpack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BackpackDao extends JpaRepository<Backpack,Integer> {
    List<Backpack> findByUid(Integer uid);

    @Modifying
    @Query(value = "update Backpack set number = ? where uid=? and type=?",nativeQuery = true)
    int updateGoodsNumber(int number, int uid,int type);

}
