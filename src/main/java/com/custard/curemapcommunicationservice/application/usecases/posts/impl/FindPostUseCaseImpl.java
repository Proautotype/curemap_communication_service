package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.FindPostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPostUseCaseImpl implements FindPostUseCase {

    private final PostRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(FindPostUseCaseImpl.class.getName());

    @Override
    public Post execute(String postId) {
        logger.info("Finding posting by postId");
        return commentRepository.findByPosterId(postId);
    }
}
