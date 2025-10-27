package com.custard.curemapcommunicationservice.application.usecases.chatmessages.impl;

import com.custard.curemapcommunicationservice.application.usecases.chatmessages.UpdateMessageStatusUseCase;
import com.custard.curemapcommunicationservice.domain.enums.MessageDeliveryStatus;
import com.custard.curemapcommunicationservice.domain.ports.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMessageStatusUseCaseImpl implements UpdateMessageStatusUseCase {

    private final ChatMessageRepository repository;

    @Override
    public boolean execute(String messageId, MessageDeliveryStatus status) {
        repository.updateDeliveryStatus(messageId, status);
        return false;
    }
}
