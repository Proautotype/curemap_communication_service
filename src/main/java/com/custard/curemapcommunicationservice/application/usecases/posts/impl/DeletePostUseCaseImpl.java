package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.DeletePostUseCase;
import com.custard.curemapcommunicationservice.application.commands.DeletePostCommand;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostUseCaseImpl implements DeletePostUseCase {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post execute(DeletePostCommand command) {
        Post post = postRepository.findPostById(command.getPostId());

        // Check if the user has permission to delete the post
        // In a real application, you might want to implement proper authorization
        if (!post.getPosterId().equals(command.getUserId())) {
            throw new SecurityException("Unauthorized to delete this post");
        }

        // Soft delete the post
        post.setDeleted(true);

        // You might want to add additional logic here like:
        // - Log the deletion reason
        // - Notify relevant users
        // - Schedule permanent deletion after a certain period

        postRepository.deletePost(command);

        return postRepository.create(post);
    }
}
