package com.metoo.user.tj.dao.repository;

import com.metoo.metoo.entity.Zh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Transactional
public interface ZhDao extends JpaRepository<Zh,Integer> {

    Zh findByUid(Integer uid);

    @Modifying
    @Query(value = "update zh set balance=? where uid=?",nativeQuery = true)
    int updateBalance(BigDecimal balance, Integer uid);

    @Modifying
    @Query(value = "update zh set activeIntegral=? where uid=?",nativeQuery = true)
    int updateActiveIntegral(Integer ActiveIntegral,Integer uid);

    @Modifying
    @Query(value = "update zh set psychologyIntegral=? where uid=?",nativeQuery = true)
    int updatePsychologyIntegral(Integer PsychologyIntegral,Integer uid);

}
