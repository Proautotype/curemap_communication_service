package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.ViewPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface ViewPostUseCase {
    Post execute(ViewPostCommand command);
}
