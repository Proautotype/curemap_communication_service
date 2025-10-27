package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.application.mapper.PostMapper;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreatePostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostImpl implements CreatePostUseCase {

    private final PostCommentRepository postCommentRepository;
    private final PostMapper postMapper;

    private final Logger logger = LoggerFactory.getLogger(CreatePostImpl.class.getName());

    @Override
    public Post execute(CreatePostCommand postCommand) {
        logger.info("Creating post");
        Post post = postMapper.toModel(postCommand);
        postCommentRepository.create(post);
        return post;
    }
}
