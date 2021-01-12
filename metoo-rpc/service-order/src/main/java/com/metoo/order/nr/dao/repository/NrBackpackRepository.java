package com.metoo.order.nr.dao.repository;

import com.metoo.order.nr.dao.entity.NrBackpack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface NrBackpackRepository extends JpaRepository<NrBackpack,Integer> {
    List<NrBackpack> findByUid(Integer uid);

    @Modifying
    @Query(value = "update nr_backpack set num = num + 1 where uid= :userId and goods_id= :goodsId",nativeQuery = true)
    int updateGoodsNumber(@Param("userId") Integer userId,@Param("goodsId")  Integer goodsId);

    NrBackpack findFirstByUidAndGoodsIdOrderByCreateTimeDesc(Integer uid, Integer goodsId);

    @Modifying
    @Query(value = "update nr_backpack set num = num - 1 where id = :id",nativeQuery = true)
    void updateGoodsNumDownById(@Param("id") Integer id);
}
