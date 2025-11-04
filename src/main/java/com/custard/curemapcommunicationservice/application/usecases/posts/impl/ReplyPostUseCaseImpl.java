package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.commands.CreateCommentCommand;
import com.custard.curemapcommunicationservice.application.mapper.PostMapper;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreateCommentUseCase;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyPostUseCaseImpl implements CreateCommentUseCase {

    private  final Logger logger = LoggerFactory.getLogger(ReplyPostUseCaseImpl.class);


    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public Post execute(CreateCommentCommand command) {
        Comment comment = postMapper.toCommentModel(command);
        return postRepository.commentPost(comment);
    }
}
