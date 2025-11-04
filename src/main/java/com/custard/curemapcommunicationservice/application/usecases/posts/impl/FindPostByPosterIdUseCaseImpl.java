package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.FindPostByPosterIdUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPostByPosterIdUseCaseImpl implements FindPostByPosterIdUseCase {

    private final Logger logger = LoggerFactory.getLogger(FindPostByPosterIdUseCaseImpl.class.getName());
    private final PostRepository postRepository;

    @Override
    public Post execute(String posterId) {
        return postRepository.findByPosterId(posterId);
    }
}
