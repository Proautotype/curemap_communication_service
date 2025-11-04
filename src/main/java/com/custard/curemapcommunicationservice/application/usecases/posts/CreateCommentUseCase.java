package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.CreateCommentCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface CreateCommentUseCase {
   Post execute(CreateCommentCommand command);
}
