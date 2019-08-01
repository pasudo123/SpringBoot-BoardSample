package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-07-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor
public class ArticleOneResponseDto {

    private Long id;
    private String title;
    private ArticleType articleType;
    private String content;
    private LocalDate registrationDate;

    // TODO - Article 에 대한 CommentDto 추가하기

    public ArticleOneResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleType = article.getArticleType();
        this.content = article.getContent();
        this.registrationDate = article.getRegistrationDate();
    }
}
