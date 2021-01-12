package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;


public interface TjUserAccountRepository extends JpaRepository<TjUserAccount,Integer> {

    TjUserAccount findByUid(Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set balance=balance-?1 where uid=?2",nativeQuery = true)
    int updateBalance(BigDecimal balance, Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set activeIntegral=?1 where uid=?2",nativeQuery = true)
    int updateActiveIntegral(Integer ActiveIntegral,Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set psychologyIntegral=?1 where uid=?2",nativeQuery = true)
    int updatePsychologyIntegral(Integer PsychologyIntegral,Integer uid);

    @Modifying
    @Query(value = "update tj_user_account set ps_coin=ps_coin- :price where uid= :uid",nativeQuery = true)
    void updatePsCoin(@Param("price") BigDecimal price,@Param("uid") Integer uid);


    @Modifying
    @Query(value = "update tj_user_account set balance_frozen=balance_frozen + :price where uid= :uid",nativeQuery = true)
    void frozeenBalance(@Param("uid") Integer uid, @Param("price") BigDecimal price);

    @Modifying
    @Query(value = "update tj_user_account set balance_frozen=balance_frozen - :price where uid= :uid",nativeQuery = true)
    void unFrozeenBalance(@Param("uid") Integer uid, @Param("price") BigDecimal price);

    @Modifying
    @Query(value = "update tj_user_account set balance=balance + :price where uid= :uid",nativeQuery = true)
    void updateBalanceUp(@Param("price") BigDecimal price, @Param("uid") Integer uid);
}
