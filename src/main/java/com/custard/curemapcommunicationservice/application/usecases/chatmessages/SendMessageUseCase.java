package com.custard.curemapcommunicationservice.application.usecases.chatmessages;

import com.custard.curemapcommunicationservice.application.commands.ChatMessageCommand;

public interface SendMessageUseCase {
    void execute(ChatMessageCommand command);
}
