package edu.pasudo123.board.core.comment.service.impl;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.service.ArticleExternalFindService;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.comment.repository.CommentRepository;
import edu.pasudo123.board.core.comment.service.CommentCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class CommentCreateServiceImpl implements CommentCreateService {

    private final ArticleExternalFindService articleExternalService;
    private final CommentRepository commentRepository;

    @Override
    public CommentOneResponseDto addNewComment(CommentOneRequestDto dto) {
        Article article = articleExternalService.findOneById(dto.getArticleId());

        Comment comment = dto.toEntity();

        article.addComment(comment);

        commentRepository.save(comment);

        return new CommentOneResponseDto(comment);
    }
}
