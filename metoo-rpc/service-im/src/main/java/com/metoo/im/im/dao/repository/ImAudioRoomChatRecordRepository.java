package com.metoo.im.im.dao.repository;

import com.metoo.im.im.dao.entity.ImAudioRoomChatRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImAudioRoomChatRecordRepository extends JpaRepository<ImAudioRoomChatRecord,Integer> {
    @Query(value = "select * from im_audio_room_chat_record where audio_room_id =?1",nativeQuery = true)
    List<ImAudioRoomChatRecord> findByAudioRoomId(Integer audioRoomId,Pageable pageable);
}
