package edu.pasudo123.board.core.user.exception;

/**
 * Created by pasudo123 on 2019-08-09
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
