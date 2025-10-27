package com.custard.curemapcommunicationservice.application.usecases.chatmessages;

public interface DeleteMessageUseCase {
    boolean execute(String messageId);
}
