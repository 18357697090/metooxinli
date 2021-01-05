package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;


public interface TjUserAccountRepository extends JpaRepository<TjUserAccount,Integer> {

    TjUserAccount findByUid(Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set balance=? where uid=?",nativeQuery = true)
    int updateBalance(BigDecimal balance, Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set activeIntegral=? where uid=?",nativeQuery = true)
    int updateActiveIntegral(Integer ActiveIntegral,Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set psychologyIntegral=? where uid=?",nativeQuery = true)
    int updatePsychologyIntegral(Integer PsychologyIntegral,Integer uid);

}
