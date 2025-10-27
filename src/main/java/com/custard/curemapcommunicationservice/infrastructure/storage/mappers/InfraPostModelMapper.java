package com.custard.curemapcommunicationservice.infrastructure.storage.mappers;

import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.infrastructure.storage.model.PostEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfraPostModelMapper {

    private final InfraCommentModelMapper commentModelMapper;


    public PostEntity toEntity(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(post.getContent());
        postEntity.setMediaUrls(post.getMediaUrls());
        postEntity.setPosterId(post.getPosterId());
        return postEntity;
    }

    @Transactional
    public Post toModel(PostEntity postEntity){
        return Post.builder()
                .posterId(postEntity.getPosterId())
                .content(postEntity.getContent())
                .mediaUrls(postEntity.getMediaUrls())
                .likes(postEntity.getLikes())
                .dislikes(postEntity.getDislikes())
                .views(postEntity.getViews())
                .timestamp(postEntity.getCreatedAt())
                .comments(
                        postEntity
                                .getComments()
                                .stream()
                                .map(commentModelMapper::toModel)
                                .toList()
                )
                .build();
    }

}
