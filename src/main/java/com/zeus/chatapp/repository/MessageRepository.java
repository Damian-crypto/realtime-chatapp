package com.zeus.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zeus.chatapp.model.MessagePayload;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessagePayload, Long> {

    List<MessagePayload> findByReceiverId(Long receiverId);

    @Query(
            value = "SELECT * FROM tbl_message WHERE sender_id = ?1",
            nativeQuery = true
    )
    List<MessagePayload> findBySenderId(Long senderId);
}
