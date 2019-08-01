package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import lombok.Getter;

/**
 * Created by pasudo123 on 2019-08-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class ArticleOneDto {

    private String title;
    private ArticleType articleType;
    private String content;

}
