package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.FlagPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface FlagPostUseCase {
    Post execute(FlagPostCommand command);
}
