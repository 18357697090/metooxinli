package com.metoo.business.bu.dao.repository;

import com.metoo.business.bu.dao.entity.BuUserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuUserListRepository extends JpaRepository<BuUserList,Integer> {
    long count();

    @Query(value = "select * from bu_user_list limit ?1,10 ",nativeQuery = true)
    List<BuUserList> findUserList(Integer page);
}
