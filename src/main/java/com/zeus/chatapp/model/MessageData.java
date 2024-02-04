package com.zeus.chatapp.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zeus.chatapp.dto.MessageDTO;

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
    private String content;
    private Date timestamp;
}
