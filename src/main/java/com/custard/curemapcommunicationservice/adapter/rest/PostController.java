package com.custard.curemapcommunicationservice.adapter.rest;

import com.custard.curemapcommunicationservice.adapter.dto.PostDto;
import com.custard.curemapcommunicationservice.adapter.dto.SuccessApiResponse;
import com.custard.curemapcommunicationservice.adapter.mapper.AdapterPostMapper;
import com.custard.curemapcommunicationservice.application.commands.CreatePostCommand;
import com.custard.curemapcommunicationservice.application.usecases.posts.CreatePostUseCase;
import com.custard.curemapcommunicationservice.application.usecases.posts.FindPostUseCase;
import com.custard.curemapcommunicationservice.domain.model.Post;
import com.custard.curemapcommunicationservice.infrastructure.clients.AccountsFeignClient;
import com.custard.curemapcommunicationservice.infrastructure.clients.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "POST Controller", description = "CRUD RESTAPI for POSTS_&_COMMENTS in CureMap Communication Service")
@RequiredArgsConstructor
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final AdapterPostMapper adapterPostMapper;
    private final CreatePostUseCase createPostUseCase;
    private final FindPostUseCase findPostUseCase;

    //this feign client will be moved to a usecase
    private final AccountsFeignClient accountsFeignClient;

    @PostMapping
    @Operation(
            summary = "Creates a new POST",
            description = "API to create a new POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Post Created"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public ResponseEntity<SuccessApiResponse<Post>> createPost(@RequestBody CreatePostCommand command) {
        Post createdPost = createPostUseCase.execute(command);

        SuccessApiResponse<Post> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData(createdPost);
        commentSuccessApiResponse.setMessage("Post created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentSuccessApiResponse);
    }

    @GetMapping("/posterData/{posterId}")
    public ResponseEntity<SuccessApiResponse<UserDto>> getPosterDetails(@PathVariable("posterId") String posterId) {
        ResponseEntity<SuccessApiResponse<UserDto>> accountDetails = accountsFeignClient.getAccountDetails(posterId);

        logger.info("received accounts detail with variable -> {} ", accountDetails.getStatusCode());

        return ResponseEntity.status(200).body(accountDetails.getBody());
    }

    @GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get POST by Id",
            description = "Retrieve a post by postId. Returns HTTP 404 if post not found",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Post not found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public ResponseEntity<SuccessApiResponse<PostDto>> findPostById(
            @PathVariable("postId") String postId
    ) {
        logger.info("Getting post by ID {} ", postId);
        // find by PostID
        Post post = findPostUseCase.execute(postId);
        PostDto postDto = adapterPostMapper.toPostDto(post);
        SuccessApiResponse<PostDto> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData(postDto);
        commentSuccessApiResponse.setMessage("Post retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }

    @PostMapping(value = "/like/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Like / Unlike post",
            description = "API to like or unlike a post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful"),
                    @ApiResponse(responseCode = "404", description = "Post not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    public ResponseEntity<SuccessApiResponse<String>> likePost(@PathVariable("postId") String postId) {
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("");
        commentSuccessApiResponse.setMessage("Post retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }

    @PostMapping(value = "/dislike/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Like / Unlike post",
            description = "API to dislike a post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful"),
                    @ApiResponse(responseCode = "404", description = "Post not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    public ResponseEntity<SuccessApiResponse<String>> dislikePost(@PathVariable("postId") String postId) {
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("");
        commentSuccessApiResponse.setMessage("Post retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }

    @Operation(
            summary = "Flag a post",
            description = "API to flag a post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful"),
                    @ApiResponse(responseCode = "404", description = "Post not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    @PostMapping(value = "/flag/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessApiResponse<String>> flagPost(@PathVariable("postId") String postId) {
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("");
        commentSuccessApiResponse.setMessage("Post retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }

    @Operation(
            summary = "View a post",
            description = "API to add to views on post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful"),
                    @ApiResponse(responseCode = "404", description = "Post not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    @PostMapping(value = "/view/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessApiResponse<String>> view(@PathVariable("postId") String postId) {
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("");
        commentSuccessApiResponse.setMessage("Post retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }


    @Operation(
            summary = "Delete a post",
            description = "API to delete a post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful"),
                    @ApiResponse(responseCode = "404", description = "Post not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error"),
            }
    )
    @PostMapping(value = "/delete/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessApiResponse<String>> deletePost(@PathVariable("postId") String postId) {
        SuccessApiResponse<String> commentSuccessApiResponse = new SuccessApiResponse<>();
        commentSuccessApiResponse.setData("");
        commentSuccessApiResponse.setMessage("Post marked as deleted");
        return ResponseEntity.status(HttpStatus.OK).body(commentSuccessApiResponse);
    }

}
