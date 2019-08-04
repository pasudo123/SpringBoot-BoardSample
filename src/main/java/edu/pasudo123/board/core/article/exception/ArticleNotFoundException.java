package edu.pasudo123.board.core.article.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by pasudo123 on 2019-07-31
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/

public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(String message){
        super(message);
    }

}
