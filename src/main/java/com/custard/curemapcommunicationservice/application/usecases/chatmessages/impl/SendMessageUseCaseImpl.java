package com.custard.curemapcommunicationservice.application.usecases.chatmessages.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.custard.curemapcommunicationservice.application.commands.ChatMessageCommand;
import com.custard.curemapcommunicationservice.application.mapper.ChatMessageMapper;
import com.custard.curemapcommunicationservice.application.usecases.chatmessages.SendMessageUseCase;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import com.custard.curemapcommunicationservice.domain.ports.ChatMessageRepository;
import com.custard.curemapcommunicationservice.domain.ports.XRedisCacheService;
import com.custard.curemapcommunicationservice.infrastructure.Util;
import com.custard.curemapcommunicationservice.shared.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendMessageUseCaseImpl implements SendMessageUseCase {
    private final SocketIOServer socketIOServer;
    private final ChatMessageMapper chatMessageModelMapper;
    private final XRedisCacheService redisCacheService;
    private final ChatMessageRepository chatMessageRepository;
    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void execute(ChatMessageCommand command) {
        try {
            ChatMessage chatMessage = chatMessageModelMapper.toModel(command);

            String senderCacheRecipientSocketId =
                    redisCacheService.getCache(AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(command.getSenderId()));
            if (senderCacheRecipientSocketId.isBlank()) {
                // unlikely
                // send bad request notification to user
                return;
            }
            SocketIOClient senderSocketClient = socketIOServer.getClient(UUID.fromString(senderCacheRecipientSocketId));

            // retrieve socket ids
            String retrievedCacheRecipientSocketId =
                    redisCacheService.getCache(AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(command.getRecipientId()));
            if (retrievedCacheRecipientSocketId.isBlank()) {
                // return
                chatMessageRepository.saveMessage(chatMessage);
                // send notification to recipient offline
                return;
            }
            // request recipient socketID
            SocketIOClient recipientSocketClient = socketIOServer.getClient(UUID.fromString(retrievedCacheRecipientSocketId));

            // apply main business logic
            chatMessageRepository.saveMessage(chatMessage);
            // write to wire
            String message = objectMapper.writeValueAsString(chatMessage);
            // forward message to recipient
            recipientSocketClient.sendEvent(Util.TEXTS.RECEIVE_MESSAGE_EVENT, message);
            // send message_sent_acknowledgement to the sender
            senderSocketClient.sendEvent(Util.TEXTS.MESSAGE_SENT_ACK, command.getClientMessageId());
        } catch (RuntimeException | JsonProcessingException e) {
            logger.error("Error sending message: {}", e.getMessage(), e);
        }
    }

}
