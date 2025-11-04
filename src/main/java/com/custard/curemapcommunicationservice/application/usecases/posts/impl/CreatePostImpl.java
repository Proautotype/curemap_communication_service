package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.application.mapper.PostMapper;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreatePostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostImpl implements CreatePostUseCase {

    private final Logger logger = LoggerFactory.getLogger(CreatePostImpl.class.getName());
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public Post execute(CreatePostCommand postCommand) {
        Post post = postMapper.toModel(postCommand);
        logger.info("Creating post \n{} ", post);
        postRepository.create(post);
        return post;
    }
}
