package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.ReplyPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface ReplyPostUseCase {
   Post execute(ReplyPostCommand command);
}
