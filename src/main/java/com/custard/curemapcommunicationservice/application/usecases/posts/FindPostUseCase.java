package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.domain.model.Post;

public interface FindPostUseCase {
    Post execute(String postId);
}
