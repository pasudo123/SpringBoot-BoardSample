package edu.pasudo123.board.core.comment.service;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface CommentUpdateService {

    CommentOneResponseDto updateOnById(final long commentId, final CommentOneRequestDto dto);

}
