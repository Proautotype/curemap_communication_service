package com.custard.curemapcommunicationservice.application.usecases.chatmessages.impl;

import com.custard.curemapcommunicationservice.application.usecases.chatmessages.DeleteMessageUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteMessageUseCaseImpl implements DeleteMessageUseCase {
    @Override
    public boolean execute(String messageId) {
        return false;
    }
}
