package edu.pasudo123.board.core.comment.service.impl;

import edu.pasudo123.board.core.comment.repository.CommentRepository;
import edu.pasudo123.board.core.comment.service.CommentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class CommentDeleteServiceImpl implements CommentDeleteService {

    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void deleteOneById(final long id) {
        commentRepository.deleteById(id);
    }
}
