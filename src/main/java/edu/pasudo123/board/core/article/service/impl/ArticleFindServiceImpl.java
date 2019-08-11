package edu.pasudo123.board.core.article.service.impl;

import edu.pasudo123.board.core.article.dto.ArticleListResponseDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.article.service.ArticleFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import edu.pasudo123.board.core.common.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class ArticleFindServiceImpl implements ArticleFindService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<ArticleListResponseDto> findAll(PageRequest pageable) {

        Page<Article> articlePage = articleRepository.findAll(pageable.of());

        return articlePage.map(ArticleListResponseDto::new);
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleOneResponseDto findOneById(long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));

        return new ArticleOneResponseDto(article);
    }
}
