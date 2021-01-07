package com.metoo.user.in.dao.repository;

import com.metoo.user.in.dao.entity.InBanner;
import com.metoo.user.in.dao.entity.InSwiper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InBannerRepository extends JpaRepository<InBanner,Integer> {

    List<InBanner> findAllByType(Integer type);
}
