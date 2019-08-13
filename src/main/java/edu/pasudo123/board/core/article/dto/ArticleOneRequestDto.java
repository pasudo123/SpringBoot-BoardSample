package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by pasudo123 on 2019-08-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleOneRequestDto {

    @NotBlank(message = "Title is Not Empty.")
    @NotNull(message = "Title is required.")
    private String title;

    private ArticleType articleType;

    @NotBlank(message = "Content is Not Empty.")
    @NotNull(message = "Content is required.")
    private String content;

    @Builder
    public ArticleOneRequestDto(String title, ArticleType articleType, String content){
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
