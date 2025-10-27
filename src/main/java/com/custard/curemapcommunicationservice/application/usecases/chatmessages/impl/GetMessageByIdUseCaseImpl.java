package com.custard.curemapcommunicationservice.application.usecases.chatmessages.impl;

import com.custard.curemapcommunicationservice.application.usecases.chatmessages.GetMessageByIdUseCase;
import com.custard.curemapcommunicationservice.domain.model.ChatMessage;
import com.custard.curemapcommunicationservice.domain.ports.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMessageByIdUseCaseImpl implements GetMessageByIdUseCase {

    private final ChatMessageRepository repository;

    @Override
    public ChatMessage execute(String messageId) {

        return null;
    }
}
