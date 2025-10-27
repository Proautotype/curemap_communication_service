package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;

public interface CreatePostUseCase {
    Post execute(CreatePostCommand post);
}
