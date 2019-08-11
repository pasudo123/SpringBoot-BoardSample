package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.common.Writer;
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
    private ArticleWriterDto writerDto;

    public ArticleListResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleType = article.getArticleType();
        this.registrationDateTime = article.getRegistrationDateTime();
        this.registrationDate = article.getRegistrationDate();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();

        this.writerDto = ArticleWriterDto.builder()
                .userRegistrationId(article.getWriterUser().getUserRegistrationId())
                .name(article.getWriterUser().getName())
                .profileImage(article.getWriterUser().getProfileImage())
                .build();
    }
}
