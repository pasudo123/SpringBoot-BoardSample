package edu.pasudo123.board.core.comment.service;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.dto.CommentXXOneRequestDto;
import edu.pasudo123.board.core.user.model.User;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentCreateService {

    CommentOneResponseDto addNewComment(CommentOneRequestDto dto, User currentUser);

    CommentOneResponseDto addNewCommentXX(CommentXXOneRequestDto dto, User currentUser);
}
