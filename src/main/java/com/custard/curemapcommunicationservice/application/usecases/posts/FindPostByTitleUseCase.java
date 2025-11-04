package com.custard.curemapcommunicationservice.application.usecases.posts;

import com.custard.curemapcommunicationservice.domain.model.Post;

import java.util.List;

public interface FindPostByTitleUseCase {
    List<Post> execute(String title);
}
