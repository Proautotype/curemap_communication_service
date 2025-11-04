package com.custard.curemapcommunicationservice.infrastructure.storage;

import com.custard.curemapcommunicationservice.application.commands.DeletePostCommand;
import com.custard.curemapcommunicationservice.application.commands.UpdatePostCommand;
import com.custard.curemapcommunicationservice.application.exceptions.EntityAlreadyExistException;
import com.custard.curemapcommunicationservice.application.exceptions.EntityNotFoundException;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.data.JpaCommentRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.data.JpaPostRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.mappers.InfraCommentModelMapper;
import com.custard.curemapcommunicationservice.infrastructure.storage.mappers.InfraPostModelMapper;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.CommentEntity;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.PostEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class.getName());
    private final InfraPostModelMapper postModelMapper;
    private final InfraCommentModelMapper commentModelMapper;

    private final JpaPostRepository postRepository;
    private final JpaCommentRepository commentRepository;


    @Override
    @Transactional
    public Post create(Post post) {
        PostEntity postEntity = postModelMapper.toEntity(post);
        postRepository.save(postEntity);
        logger.info("Post created by {} ", postEntity);
        post.setId(postEntity.getId());
        return post;
    }

    @Override
    public Post findPostById(String postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found by id"));
        return postModelMapper.toModel(postEntity);
    }

    @Override
    @Transactional
    public Post findByPosterId(String posterId) {
        try {
            PostEntity postEntity = postRepository.findById(posterId)
                    .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            return postModelMapper.toModel(postEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updateContent(UpdatePostCommand command) {
        PostEntity postEntity = findByPostIdAndUserIdOrThrow(command.getPostId(), command.getUserId());
        postEntity.setContent(command.getContent());
        postRepository.save(postEntity);
    }

    @Override
    @Transactional
    public void deletePost(DeletePostCommand command) {
        try {
            PostEntity postEntity = findPostByIdOrThrow(command.getPostId());

            if (!postEntity.getPosterId().equals(command.getUserId())) {
                throw new SecurityException("Unauthorized to delete this post");
            }

            postEntity.setDeleted(true);
            postRepository.save(postEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Post commentPost(Comment comment) {
        try {
            PostEntity postEntity = findPostByIdOrThrow(comment.getPostId());
            CommentEntity commentEntity = commentModelMapper.toEntity(comment);
            postEntity.getComments().add(commentEntity);
            PostEntity updatedPost = postRepository.save(postEntity);
            logger.info("Comment added to post with id: {}", comment.getPostId());
            return postModelMapper.toModel(updatedPost);
        } catch (Exception e) {
            logger.error("Error adding comment to post: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteComment(String postId, String commentId) {
        try {
            commentRepository.deleteComment(postId, commentId);
        } catch (Exception e) {
            logger.error("Error deleting comment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void updatePostsComment(String posterId, String commentId, String content) {
        try {
            commentRepository.updateCommentContent(posterId, commentId, content);
        } catch (Exception e) {
            logger.error("Error deleting comment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Comment> findCommentsOnPost(String postId) {
        return commentRepository.findByPostEntityId(postId)
                .stream()
                .map(commentModelMapper::toModel)
                .toList();
    }

    @Override
    public List<Post> searchPostByTitle(String title) {
        return List.of();
    }

    private PostEntity findPostByIdOrThrow(String postId) {
        return postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException(String.format("Post with id %s not found ", postId)));
    }

    private PostEntity findByPostIdAndUserIdOrThrow(String postId, String userId){
        return postRepository.findByIdAndPosterId(postId, userId).orElseThrow(()-> new EntityNotFoundException("Post not found"));

    }

    private void findPostByIdAndThrow(String postId) {
        if (this.findPostByIdOrThrow(postId) != null) {
            throw new EntityAlreadyExistException("post already exist");
        }
    }
}
