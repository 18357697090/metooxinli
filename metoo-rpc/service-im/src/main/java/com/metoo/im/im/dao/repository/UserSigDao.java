package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.UserSig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface UserSigDao extends JpaRepository<UserSig,Integer> {
    UserSig findByUid(Integer uid);

    @Modifying
    @Query(value = "update user_sig set usersig=?,updatetime=? where uid=?",nativeQuery = true)
    int updatausersig(String usersig, Date date, Integer uid);
}
