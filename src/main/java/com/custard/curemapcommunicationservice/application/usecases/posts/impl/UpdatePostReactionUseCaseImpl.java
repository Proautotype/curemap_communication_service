package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.UpdatePostReactionUseCase;
import com.custard.curemapcommunicationservice.application.commands.UpdatePostReactionCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePostReactionUseCaseImpl implements UpdatePostReactionUseCase {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post execute(UpdatePostReactionCommand command) {
        Post post = postRepository.findPostById(command.getPostId());

        if (command.isAdding()) {
            if (command.getReactionType() == UpdatePostReactionCommand.ReactionType.LIKE) {
                post.setLikes(post.getLikes() + 1);
            } else {
                post.setDislikes(post.getDislikes() + 1);
            }
        } else {
            if (command.getReactionType() == UpdatePostReactionCommand.ReactionType.LIKE) {
                post.setLikes(Math.max(0, post.getLikes() - 1));
            } else {
                post.setDislikes(Math.max(0, post.getDislikes() - 1));
            }
        }

        return postRepository.create(post);
    }
}
