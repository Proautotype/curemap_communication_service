package com.custard.curemapcommunicationservice.application.usecases.chatmessages;

import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;

public interface UpdateMessageStatusUseCase {
    boolean execute(String messageId, MessageDeliveryStatus content);
}
