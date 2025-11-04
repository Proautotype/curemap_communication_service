package com.custard.curemapcommunicationservice.adapter.rest;

import com.custard.curemapcommunicationservice.adapter.dto.SuccessApiResponse;
import com.custard.curemapcommunicationservice.application.commands.CreateCommentCommand;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreateCommentUseCase;
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
@RequestMapping(path = "/api/v1/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "COMMENT Controller", description = "CRUD RESTAPI for managing comments")
@RequiredArgsConstructor
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Contains the logic to create a comment on a POST",
            summary = "Create a new comment on a post"
    )

    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Comment created successfully"
            )
    )

    public ResponseEntity<SuccessApiResponse<String>>
    createComment(CreateCommentCommand command) {
        logger.info("reply received {} ", command);
        createCommentUseCase.execute(command);
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("commented");
        commentSuccessApiResponse.setMessage("Comment created");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentSuccessApiResponse);
    }

}
