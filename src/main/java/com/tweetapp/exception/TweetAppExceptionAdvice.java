package com.tweetapp.exception;

import com.tweetapp.model.utilityModel.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TweetAppExceptionAdvice {
    @ExceptionHandler(value = {TweetAppException.class,LoginException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiResponse> exception(TweetAppException exception) {
        return ResponseEntity.internalServerError().body(ApiResponse.builder()
                        .status(500).message(exception.getLocalizedMessage())
                .build());
    }
}
