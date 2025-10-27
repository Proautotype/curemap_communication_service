package com.custard.curemapcommunicationservice.application.mapper;

import com.custard.curemapcommunicationservice.application.commands.ChatMessageCommand;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {
    public ChatMessage toModel(ChatMessageCommand command){
        return ChatMessage.builder()
                .id(command.getClientMessageId())
                .senderId(command.getSenderId())
                .recipientId(command.getRecipientId())
                .content(command.getContent())
                .mediaUrl(command.getMediaUrl())
                .timestamp(command.getTimestamp())
                .replyTo(command.getReplyTo())
                .build();
    }
}
