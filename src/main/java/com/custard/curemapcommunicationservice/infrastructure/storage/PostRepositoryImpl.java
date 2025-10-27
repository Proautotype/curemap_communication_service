package com.custard.curemapcommunicationservice.infrastructure.storage;

import com.custard.curemapcommunicationservice.application.exceptions.EntityAlreadyExistException;
import com.custard.curemapcommunicationservice.application.exceptions.EntityNotFoundException;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostCommentRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.data.JpaPostRepository;
import com.custard.curemapcommunicationservice.infrastructure.storage.mappers.InfraPostModelMapper;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.PostEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostCommentRepository {

    private final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class.getName());
    private final InfraPostModelMapper postModelMapper;

    private final JpaPostRepository postRepository;


    @Override
    public void create(Post post) {
        try {
            PostEntity postEntity = postModelMapper.toEntity(post);
            postRepository.save(postEntity);
            logger.info("Post created by {} ", post.getPosterId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Post> findByPosterId(String posterId) {
        try {
            return postRepository
                    .findByPosterId(posterId)
                    .stream()
                    .map(postModelMapper::toModel)
                    .toList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateContent(String postId, String content) {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deletePost(String postId) {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Post commentPost(Comment comment) {
        try {
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteComment(String postId, String commentId) {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updatePostsComment(String posterId, String commentId, String content) {

    }

    @Override
    public List<Comment> findCommentsOnPost(String postId) {
        return List.of();
    }

    private PostEntity findPostByIdOrThrow(String postId){
        return postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException(String.format("Post with id %s not found ", postId)));
    }

    private void findPostByIdAndThrow(String postId){
        if(this.findPostByIdOrThrow(postId) != null){
            throw new EntityAlreadyExistException("post already exist");
        }
    }
}
