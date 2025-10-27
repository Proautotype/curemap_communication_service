package com.custard.curemapcommunicationservice.application.usecases.chatmessages;

import com.custard.curemapcommunicationservice.domain.model.ChatMessage;

public interface GetMessageByIdUseCase {
    ChatMessage execute(String messageId);
}
