package edu.pasudo123.board.core.article.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class ArticleValidationException extends RuntimeException{

    List<FieldError> fieldErrors;
    String message;

    public ArticleValidationException(String message, List<FieldError> fieldErrors){
        super(message);
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
