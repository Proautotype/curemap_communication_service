package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.ViewPostUseCase;
import com.custard.curemapcommunicationservice.application.commands.ViewPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewPostUseCaseImpl implements ViewPostUseCase {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post execute(ViewPostCommand command) {
        Post post = postRepository.findPostById(command.getPostId());

        // Increment view count if this is a unique view
        // You might want to implement a more sophisticated view counting mechanism
        // that prevents counting multiple views from the same user in a short time
        post.setViews(post.getViews() + 1);

        return postRepository.create(post);
    }
}
