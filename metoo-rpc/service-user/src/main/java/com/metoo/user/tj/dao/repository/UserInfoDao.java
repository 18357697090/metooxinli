package com.metoo.user.tj.dao.repository;

import com.metoo.metoo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUid(Integer uid);

    List<UserInfo> findByNameLike(String name);

    @Modifying
    @Query(value = "update user set name=?1,picture=?2,city=?3,motto=?4 where uid=?5",nativeQuery = true)
    int updateUserInfo(String name,String picture,String city,String motto,Integer uid);
}
