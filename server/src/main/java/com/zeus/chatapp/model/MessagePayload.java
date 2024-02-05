package com.zeus.chatapp.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_message")
public class MessagePayload {
    @Id
    @Column(name="msg_id")
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long messageId;
    
    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private Date timestamp;

    @ManyToOne
    @JoinColumn( // this will add a user_id column to the message table as a foreign key
        name = "sender_id", // specifies the name of the foreign key column in the database
        referencedColumnName = "user_id" // primary key of the user who owns this MESSAGE
    )
    private User sender;

    @OneToOne
    @JoinColumn(
        name = "receiver_id",
        referencedColumnName = "user_id",
        nullable = true
    )
    private User receiver;

    @OneToOne(
        cascade = CascadeType.ALL,
        optional = true
    )
    @JoinColumn(
        name = "attachment_id",
        referencedColumnName = "attachment_id"
    )
    private Attachment attachment;

    private MessageType messageType;
}
