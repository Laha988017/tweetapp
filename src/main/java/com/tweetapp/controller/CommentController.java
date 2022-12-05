package com.tweetapp.controller;

import com.tweetapp.exception.TweetAppException;
import com.tweetapp.model.Comments;
import com.tweetapp.model.utilityModel.ApiResponse;
import com.tweetapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(("/{username}/reply/{tweetId}"))
    public ResponseEntity<ApiResponse> commentATweet(@RequestBody Comments comments, @PathVariable String username, @PathVariable Long tweetId) throws TweetAppException {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200).message("Liked the tweet")
                .data(commentService.commentATweet(comments,username,tweetId))
                .build());
    }
}
