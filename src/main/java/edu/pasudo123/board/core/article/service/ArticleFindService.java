package edu.pasudo123.board.core.article.service;

import edu.pasudo123.board.core.article.dto.ArticleListResponseDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.common.PageRequest;
import org.springframework.data.domain.Page;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleFindService {

    /** 아티클 서비스 레이어 **/
    Page<ArticleListResponseDto> findAll(final PageRequest pageable);

    ArticleOneResponseDto findOneById(final long articleId);

}
