package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TjUserInfoRepository extends JpaRepository<TjUserInfo,Integer> {
    TjUserInfo findByUid(Integer uid);

    List<TjUserInfo> findByNameLike(String name);

    @Modifying
    @Query(value = "update tj_user set name=?1,picture=?2,city=?3,motto=?4 where uid=?5",nativeQuery = true)
    int updateUserInfo(String name,String picture,String city,String motto,Integer uid);
}
