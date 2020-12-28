package com.metoo.im.im.dao.repository;

import com.metoo.metoo.entity.AudioRoomChatRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AudioRoomChatRecordDao extends JpaRepository<AudioRoomChatRecord,Integer> {
    @Query(value = "select * from audio_room_chat_record where audio_room_id =?1",nativeQuery = true)
    List<AudioRoomChatRecord> findByAudioRoomId(Integer audioRoomId,Pageable pageable);
}
