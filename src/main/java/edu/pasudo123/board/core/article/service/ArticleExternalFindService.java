package edu.pasudo123.board.core.article.service;

import edu.pasudo123.board.core.article.model.Article;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 *
 * 다른 서비스 레이어에서 이용하는 서비스 레이어
 *
 **/
public interface ArticleExternalFindService {

    Article findOneById(final Long articleId);

}
