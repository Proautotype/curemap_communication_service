package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.domain.model.Post;

public interface FindPostByPosterIdUseCase {
    Post execute(String posterId);
}
