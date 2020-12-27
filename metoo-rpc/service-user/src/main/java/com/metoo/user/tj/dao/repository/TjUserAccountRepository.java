package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TjUserAccountRepository extends JpaRepository<TjUserAccount,Integer> {
    TjUserAccount findByUserId(Integer userId);

    @Query(nativeQuery = true,value = "select * from tj_user_account where user_id=?1")
    List<TjUserAccount> findByUserId2(Integer userId);
}
