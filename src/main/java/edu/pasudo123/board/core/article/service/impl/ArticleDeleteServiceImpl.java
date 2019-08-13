package edu.pasudo123.board.core.article.service.impl;

import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.article.service.ArticleDeleteService;
import edu.pasudo123.board.core.article.service.ArticleExternalFindService;
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
public class ArticleDeleteServiceImpl implements ArticleDeleteService {

    private final ArticleExternalFindService articleExternalFindService;
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public void deleteOneById(final long articleId) {

        /**
         * - 아티클 삭제 시, 아티클에 속한 코멘드를 어떻게 할 것인가?
         *
         */

        Article foundArticle = articleExternalFindService.findOneById(articleId);


        articleRepository.deleteById(articleId);
    }
}
