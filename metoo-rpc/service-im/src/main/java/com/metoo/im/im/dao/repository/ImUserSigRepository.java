package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImUserSig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface ImUserSigRepository extends JpaRepository<ImUserSig,Integer> {
    ImUserSig findByUid(Integer uid);

    @Modifying
    @Query(value = "update im_user_sig set usersig=?,updatetime=? where uid=?",nativeQuery = true)
    int updatausersig(String usersig, Date date, Integer uid);
}
