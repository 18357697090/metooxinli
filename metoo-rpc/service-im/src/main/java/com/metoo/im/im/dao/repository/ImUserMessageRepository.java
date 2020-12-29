package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImUserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ImUserMessageRepository extends JpaRepository<ImUserMessage,Integer> {
//    @Modifying
//    @Query(value = "",nativeQuery = true)
//    int updateState(int uid, int sendId);

    @Modifying
    @Query(value = "update im_user_message set state = 0 where uid=?",nativeQuery = true)
    int updateState(int uid);

    int deleteByUid(int uid);

    @Modifying
    @Query(value = "create table a:name (tutorial_id INT NOT NULL AUTO_INCREMENT,tutorial_title VARCHAR(100) NOT NULL,tutorial_author VARCHAR(40) NOT NULL, submission_date DATE,PRIMARY KEY ( tutorial_id ));",nativeQuery = true)
    int aaa(@Param("name") Integer name);

    @Modifying
    @Query(value = "select * from a:name",nativeQuery = true)
    List<Object> bbb(@Param("name") Integer name);


    @Query(value = "SELECT * FROM im_user_message WHERE uid=?",nativeQuery = true)
    List<ImUserMessage> uid(Integer uid);

}
