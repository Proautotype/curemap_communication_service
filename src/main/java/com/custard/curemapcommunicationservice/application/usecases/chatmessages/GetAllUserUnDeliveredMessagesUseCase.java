package com.custard.curemapcommunicationservice.application.usecases.chatmessages;

import com.custard.curemapcommunicationservice.domain.model.ChatMessage;

import java.util.List;

public interface GetAllUserUnDeliveredMessagesUseCase {
    List<ChatMessage> execute(String userId);
}
