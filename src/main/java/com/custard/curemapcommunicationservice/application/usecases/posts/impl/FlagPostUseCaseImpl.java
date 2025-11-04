package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.FlagPostUseCase;
import com.custard.curemapcommunicationservice.application.commands.FlagPostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FlagPostUseCaseImpl implements FlagPostUseCase {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post execute(FlagPostCommand command) {
        Post post = postRepository.findPostById(command.getPostId());

        // Increment the flag count
        post.setFlaggedCount(post.getFlaggedCount() + 1);

        // Here you might want to add additional logic like:
        // - Check if the post should be hidden after reaching a certain number of flags
        // - Log the flag reason
        // - Notify moderators

        return postRepository.create(post);
    }
}
