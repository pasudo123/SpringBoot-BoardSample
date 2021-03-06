package edu.pasudo123.board.core.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.common.WriterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-07-28
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor
public class ArticleListResponseDto {

    private Long id;
    private String title;
    private ArticleType articleType;
    private LocalDateTime registrationDateTime;
    private LocalDate registrationDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @JsonProperty("writer")
    private WriterDto writerDto;

    public ArticleListResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleType = article.getArticleType();
        this.registrationDateTime = article.getRegistrationDateTime();
        this.registrationDate = article.getRegistrationDate();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
        this.writerDto = new WriterDto(article.getWriterUser());
    }
}
