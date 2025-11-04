package com.custard.curemapcommunicationservice.application.commands;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessageCommand implements Serializable {
    private String clientMessageId;
    @NotNull(message = "sender id missing")
    private String senderId;
    @NotNull(message = "recipient id missing")
    private String recipientId;
    private String content;
    private String mediaUrl;
    private String replyTo;
    @JsonSerialize
    private Instant timestamp;
}
