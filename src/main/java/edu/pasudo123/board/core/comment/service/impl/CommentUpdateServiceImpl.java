package edu.pasudo123.board.core.comment.service.impl;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.exception.CommentNotFoundException;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.comment.repository.CommentRepository;
import edu.pasudo123.board.core.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class CommentUpdateServiceImpl implements CommentUpdateService {

    private final CommentRepository commentRepository;

    @Override
    public CommentOneResponseDto updateOnById(final long commentId, CommentOneRequestDto dto) {

            Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("해당 코멘트는 존재하지 않습니다."));

        comment.updateComment(dto);
        commentRepository.save(comment);

        return new CommentOneResponseDto(comment);
    }
}
