package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface TjUserRepository extends JpaRepository<TjUser,Integer>{

        TjUser findByUsername(String username);

}
