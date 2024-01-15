package com.zeus.chatapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "tbl_attachment")
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @Column(name = "attachment_id")
    @SequenceGenerator(
            name = "attachment_sequence",
            sequenceName = "attachment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attachment_sequence"
    )
    private Long attachmentId;
    @Column(name="URI")
    private String URI;
}
