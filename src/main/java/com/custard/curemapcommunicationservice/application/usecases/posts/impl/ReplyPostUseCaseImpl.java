package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.commands.ReplyPostCommand;
import com.custard.curemapcommunicationservice.application.mapper.PostMapper;
import com.custard.curemapcommunicationservice.application.usecases.posts.ReplyPostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyPostUseCaseImpl implements ReplyPostUseCase {

    private  final Logger logger = LoggerFactory.getLogger(ReplyPostUseCaseImpl.class);


    private final PostCommentRepository postCommentRepository;
    private final PostMapper postMapper;

    @Override
    public Post execute(ReplyPostCommand command) {
        Comment comment = postMapper.toCommentModel(command);
        return postCommentRepository.commentPost(comment);
    }
}
