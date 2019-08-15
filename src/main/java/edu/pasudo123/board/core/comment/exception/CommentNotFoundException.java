package edu.pasudo123.board.core.comment.exception;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }

}
