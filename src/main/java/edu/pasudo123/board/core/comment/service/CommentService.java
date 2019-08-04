package edu.pasudo123.board.core.comment.service;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.service.ArticleInternalService;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
public class CommentService {

    @Autowired
    private ArticleInternalService articleInternalService;

    @Autowired
    private CommentRepository commentRepository;

    public CommentOneResponseDto addNewComment(CommentOneRequestDto dto){

        Article article = articleInternalService.findById(dto.getArticleId());

        /**
         * 여기서 디버깅하면, 동일한 값이 두 번 삽입되는 것을 확인할 수 있다.
         * 하지만 실질적으로 데이터베이스에 값은 한번만 삽입되어 있는 형태로 되어있다.
         */
        Comment comment = dto.toEntity();
        comment.setArticle(article);

        article.addComment(comment);

        commentRepository.save(comment);

        return new CommentOneResponseDto(comment);
    }
}
