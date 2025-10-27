package com.custard.curemapcommunicationservice.adapter.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.custard.curemapcommunicationservice.application.commands.ChatMessageCommand;
import com.custard.curemapcommunicationservice.application.usecases.chatmessages.SendMessageUseCase;
import com.custard.curemapcommunicationservice.application.usecases.chatmessages.UpdateMessageStatusUseCase;
import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.domain.ports.XRedisCacheService;
import com.custard.curemapcommunicationservice.shared.AppUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEvents {

    private SocketIOServer socketIOServer;
    private final XRedisCacheService service;
    private final ObjectMapper objectMapper;
    private final SendMessageUseCase sendMessageUseCase;
    private final UpdateMessageStatusUseCase updateMessageStatusUseCase;
    private final Logger logger = LoggerFactory.getLogger(UserEvents.class.getName());

    @OnEvent("send_message")
    public void sendMessage(SocketIOClient client, String dataString, AckRequest ackRequest) throws JsonProcessingException {
        try {
            logger.info("send_message event received : {} ", dataString);
            String username = client.getHandshakeData().getSingleUrlParam("username");
            String usernameCacheKey = AppUtil.ON_CONNECT_REDIS_CACHE_KEY.concat(username);
            String retrievedCacheRecipientSocketId = service.getCache(usernameCacheKey);

            if (retrievedCacheRecipientSocketId == null) {
                return;
            }
            // send message
            var data = objectMapper.readValue(dataString, ChatMessageCommand.class);
            sendMessageUseCase.execute(data);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing message: {}", e.getMessage(), e);
            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData("Error: Invalid message format");
            }
        }
    }

    @OnEvent("ack_message_delivered")
    public void onMessageDelivered(SocketIOClient client, String messageId)  {
        // make message as delivered
        updateMessageStatusUseCase.execute(messageId, MessageDeliveryStatus.DELIVERED);
    }

}
