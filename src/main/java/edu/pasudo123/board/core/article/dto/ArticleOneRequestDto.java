package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.common.Writer;
import edu.pasudo123.board.core.config.auth.dto.SessionUserDto;
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

    private Writer writer;

    @Builder
    public ArticleOneRequestDto(String title, ArticleType articleType, String content){
        this.title = title;
        this.articleType = articleType;
        this.content = content;
    }

    public void setWriter(SessionUserDto dto){
        this.writer = Writer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .userProfile(dto.getProfile())
                .build();
    }

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .articleType(articleType)
                .content(content)
                .writer(writer)
                .build();
    }
}
