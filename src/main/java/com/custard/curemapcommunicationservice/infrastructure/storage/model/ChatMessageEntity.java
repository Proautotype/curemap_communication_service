package com.custard.curemapcommunicationservice.infrastructure.storage.model;

import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class ChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(updatable = false)
    private String senderId;
    @Column(updatable = false)
    private String recipientId;
    @Column(columnDefinition = "text")
    private String content;
    @Column(updatable = false)
    private String mediaUrl;
    @Column(updatable = false)
    private String replyTo;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private MessageDeliveryStatus status = MessageDeliveryStatus.SENT;
    private Boolean deleted = false;
    // allow client application to track a chat message
    private String clientsMessageId;
}
