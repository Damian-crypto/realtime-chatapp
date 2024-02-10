package com.zeus.chatapp.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageData {
    private Long id;
    private Long sender;
    private Long receiver;
    private String content;
    private Date timestamp;
}
