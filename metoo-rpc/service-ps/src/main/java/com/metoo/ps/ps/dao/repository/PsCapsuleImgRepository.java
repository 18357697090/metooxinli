package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsCapsuleImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PsCapsuleImgRepository extends JpaRepository<PsCapsuleImg,Integer> {

    List<PsCapsuleImg> getAllByCapId(Integer capId);

}
