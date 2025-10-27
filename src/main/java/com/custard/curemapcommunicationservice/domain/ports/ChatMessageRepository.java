package com.custard.curemapcommunicationservice.domain.ports;

import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {
    void saveMessage(ChatMessage message);
    void updateDeliveryStatus(String messageId, MessageDeliveryStatus deliveryStatus);
    List<ChatMessage> findByUserIdAndStatus(String userId, MessageDeliveryStatus status);
    ChatMessage getByMessageId(String messageId);
}
