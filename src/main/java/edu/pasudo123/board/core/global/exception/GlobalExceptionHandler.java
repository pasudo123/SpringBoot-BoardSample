package edu.pasudo123.board.core.global.exception;

import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.exception.ArticleValidationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

/**
 * Created by pasudo123 on 2019-07-31
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleBindException(ArticleValidationException ex, Locale locale, WebRequest request){

        CustomErrorResponse errorResponse =
                CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getFieldErrors().stream().toString())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> articleNotFoundException(ArticleNotFoundException ex, WebRequest request){

        CustomErrorResponse errorResponse =
                CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
