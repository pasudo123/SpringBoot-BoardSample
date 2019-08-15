package edu.pasudo123.board.core.comment.service;

import edu.pasudo123.board.core.comment.model.Comment;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentExternalFindService {

    Comment findOneById(final Long commentId);

}
