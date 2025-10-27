package com.custard.curemapcommunicationservice.adapter.rest;

import com.custard.curemapcommunicationservice.adapter.dto.SuccessApiResponse;
import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.application.commands.ReplyPostCommand;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreatePostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Comment;
import com.custard.curemapcommunicationservice.domain.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "POST & COMMENTS Controller",
        description = "CRUD RESTAPI for POSTS_&_COMMENTS in CureMap Communication Service"
)
@RequiredArgsConstructor
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final CreatePostUseCase createPostUseCase;

    @PostMapping
    @Operation(
            summary = "Creates a new POST",
            description = "API to create a new POST"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Post Created"
            )
    )
    public ResponseEntity<SuccessApiResponse<Post>> createPost(CreatePostCommand command) {
        Post createdPost = createPostUseCase.execute(command);

        SuccessApiResponse<Post> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData(createdPost);
        commentSuccessApiResponse.setMessage("Post created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentSuccessApiResponse);
    }

    public ResponseEntity<SuccessApiResponse<Comment>> replyPost(ReplyPostCommand command){
        SuccessApiResponse<Comment> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setMessage("Comment sent");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentSuccessApiResponse);
    }


}
