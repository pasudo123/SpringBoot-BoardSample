package edu.pasudo123.board.core.comment.service;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentCreateService {

    CommentOneResponseDto addNewComment(CommentOneRequestDto dto);

}
