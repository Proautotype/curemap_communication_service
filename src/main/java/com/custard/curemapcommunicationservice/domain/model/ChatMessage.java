package com.custard.curemapcommunicationservice.domain.model;

import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
    private String mediaUrl;
    private String replyTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;
    private MessageDeliveryStatus status = MessageDeliveryStatus.SENT;
    private Boolean deleted = false;
}
