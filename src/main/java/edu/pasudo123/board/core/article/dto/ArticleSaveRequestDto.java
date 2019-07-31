package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by pasudo123 on 2019-07-28
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor
public class ArticleSaveRequestDto {

    private String title;
    private ArticleType articleType;
    private String content;

    @Builder
    public ArticleSaveRequestDto(String title, ArticleType articleType, String content){
        this.title = title;
        this.articleType = articleType;
        this.content = content;
    }

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .articleType(articleType)
                .content(content)
                .build();
    }
}