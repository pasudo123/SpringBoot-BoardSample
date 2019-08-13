package edu.pasudo123.board.core.article.service;

import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleUpdateService {

    ArticleOneResponseDto updateOneById(final long articleId, final ArticleOneRequestDto dto);

}
