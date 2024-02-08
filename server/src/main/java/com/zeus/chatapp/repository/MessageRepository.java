package com.zeus.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zeus.chatapp.model.MessagePayload;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessagePayload, Long> {

    // @Query(value="SELECT msg FROM MessagePayload msg WHERE msg.receiver.userId = :receiverId ORDER BY msg.timestamp DESC")
    @Query(
        value = "SELECT * FROM tbl_message WHERE receiver_id = ?1 ORDER BY tbl_message.timestamp ASC",
        nativeQuery = true
        )
    Optional<List<MessagePayload>> findByReceiverId(Long receiverId);
        
    // @Query(value="SELECT msg FROM MessagePayload msg WHERE msg.receiver.userId = :receiverId ORDER BY msg.timestamp DESC")
    // Optional<List<MessagePayload>> findByReceiverIdOrderByTimestamp(Long receiverId);

    @Query(
        value = "SELECT * FROM tbl_message WHERE sender_id = ?1 ORDER BY tbl_message.timestamp ASC",
        nativeQuery = true
    )
    Optional<List<MessagePayload>> findBySenderId(Long senderId);

    @Query("SELECT msg FROM MessagePayload msg WHERE msg.sender.userId = :userId OR msg.receiver.userId = :userId ORDER BY msg.timestamp ASC")
    Optional<List<MessagePayload>> findByUserId(Long userId);
}
