package com.custard.curemapcommunicationservice.application.usecases.posts.impl;

import com.custard.curemapcommunicationservice.application.usecases.posts.FindPostByTitleUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.domain.ports.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPostByTitleUseCaseImpl implements FindPostByTitleUseCase {

    private final PostRepository postRepository;

    @Override
    public List<Post> execute(String title) {
        return postRepository.searchPostByTitle(title);
    }
}
