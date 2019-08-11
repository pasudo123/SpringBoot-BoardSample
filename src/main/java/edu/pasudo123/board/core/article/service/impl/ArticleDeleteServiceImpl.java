package edu.pasudo123.board.core.article.service.impl;

import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.article.service.ArticleDeleteService;
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

    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public void deleteOneById(long articleId) {
        articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));
        articleRepository.deleteById(articleId);
    }
}
