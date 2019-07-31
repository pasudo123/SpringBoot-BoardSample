package edu.pasudo123.board.core.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-07-31
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CustomErrorResponse <T> {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String details;
    private T data;

    @Builder
    public CustomErrorResponse(HttpStatus status, String message, String details, T data){
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
        this.data = data;
    }
}
