package com.custard.curemapcommunicationservice.application.usecases.chatmessages.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.custard.curemapcommunicationservice.application.usecases.chatmessages.GetAllUserUnDeliveredMessagesUseCase;
import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import com.custard.curemapcommunicationservice.domain.ports.ChatMessageRepository;
import com.custard.curemapcommunicationservice.domain.ports.XRedisCacheService;
import com.custard.curemapcommunicationservice.shared.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllUserUnDeliveredMessagesUseCaseImpl implements GetAllUserUnDeliveredMessagesUseCase {

    private final SocketIOServer server;
    private final ChatMessageRepository repository;
    private final XRedisCacheService redisCacheService;
    private final ObjectMapper mapper;

    @Override
    public List<ChatMessage> execute(String userId) {
        try {
            String cachedKey = AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(userId);
            String userSocketId = redisCacheService.getCache(cachedKey);
            SocketIOClient userSocket = server.getClient(UUID.fromString(userSocketId));
            var undeliveredMessages = repository.findByUserIdAndStatus(userId, MessageDeliveryStatus.SENT);
            String undeliveredMessagesAsString = mapper.writeValueAsString(undeliveredMessages);
            //write message to the wire
            userSocket.sendEvent("undelivered_messages", undeliveredMessagesAsString);
            return undeliveredMessages;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
