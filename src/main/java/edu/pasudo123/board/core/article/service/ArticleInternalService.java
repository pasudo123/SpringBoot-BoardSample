package edu.pasudo123.board.core.article.service;

import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/

/**
 * 타 서비스 레이어에서 이용하는 서비스 레이어
 */
@Service
public class ArticleInternalService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article findById(final Long articleId) throws ArticleNotFoundException{
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));
    }
}
