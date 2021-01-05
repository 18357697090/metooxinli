package com.metoo.ps.ps.dao.repository;

import com.metoo.ps.ps.dao.entity.PsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface PsAnswerRepository extends JpaRepository<PsAnswer,Integer> {


}
