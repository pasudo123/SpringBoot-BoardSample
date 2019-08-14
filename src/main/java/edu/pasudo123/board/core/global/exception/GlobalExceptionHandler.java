package edu.pasudo123.board.core.global.exception;

import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.comment.exception.CommentNotFoundException;
import edu.pasudo123.board.core.user.exception.UserNotFoundException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;

/**
 * Created by pasudo123 on 2019-07-31
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleBindException(ValidationException ex, Locale locale, WebRequest request){

        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        StringBuffer stringBuffer = new StringBuffer();
        errorList.forEach(error -> {
            stringBuffer.append(error.getDefaultMessage() + "\n");
        });

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(stringBuffer.toString())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> articleNotFoundException(ArticleNotFoundException ex, WebRequest request){

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> userNotFoundException(UserNotFoundException ex, WebRequest request){

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> CommentNotFoundException(CommentNotFoundException ex, WebRequest request){

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
