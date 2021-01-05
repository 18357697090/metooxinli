package com.metoo.user.tj.dao.repository;

import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface TjUserAccountDetailRepository extends JpaRepository<TjUserAccountDetail,Integer> {




}
