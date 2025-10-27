package com.custard.curemapcommunicationservice.infrastructure.storage.mappers;

import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.ChatMessageEntity;
import org.springframework.stereotype.Component;

@Component
public class InfraChatMessageModelMapper {

    public ChatMessageEntity toEntity(ChatMessage chatMessage){
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setClientsMessageId(chatMessage.getId());
        entity.setSenderId(chatMessage.getSenderId());
        entity.setRecipientId(chatMessage.getRecipientId());
        entity.setContent(chatMessage.getContent());
        entity.setMediaUrl(chatMessage.getMediaUrl());
        entity.setReplyTo(chatMessage.getReplyTo());
        entity.setTimestamp(chatMessage.getTimestamp());
        return entity;
    }

    public ChatMessage toModel(ChatMessageEntity chatMessageEntity){
        return ChatMessage.builder()
                .id(chatMessageEntity.getClientsMessageId())
                .senderId(chatMessageEntity.getSenderId())
                .recipientId(chatMessageEntity.getRecipientId())
                .content(chatMessageEntity.getContent())
                .mediaUrl(chatMessageEntity.getMediaUrl())
                .replyTo(chatMessageEntity.getReplyTo())
                .timestamp(chatMessageEntity.getTimestamp())
                .status(chatMessageEntity.getStatus())
                .build();
    }

}
