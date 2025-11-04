package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.DeletePostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface DeletePostUseCase {
    Post execute(DeletePostCommand command);
}
