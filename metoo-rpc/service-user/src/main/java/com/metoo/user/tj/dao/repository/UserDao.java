package com.metoo.user.tj.dao.repository;

import com.metoo.metoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends JpaRepository<User,Integer>{

        User findByUsername(String username);
        User findByUid(int uid);
        @Modifying
        @Query(value = "update user set password=? where username=?",nativeQuery = true)
        int updateUserPassword(String password,String username);

}
