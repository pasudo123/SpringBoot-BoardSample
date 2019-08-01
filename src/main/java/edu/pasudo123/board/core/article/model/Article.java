package edu.pasudo123.board.core.article.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.pasudo123.board.core.article.dto.ArticleOneDto;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.comment.Comment;
import edu.pasudo123.board.core.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-07-27
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "ARTICLE", indexes = {
        @Index(name = "idx_article_1", columnList = "descIndex"),
        @Index(name = "idx_article_2", columnList = "registrationDate")
})
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ArticleType articleType;

    @Column(nullable = false, length = 1000)
    private String content;

    @JsonBackReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    private LocalDateTime registrationDateTime;
    private LocalDate registrationDate;
    private long descIndex;

    @Builder
    public Article(String title, ArticleType articleType, String content){
        this.title = title;
        this.articleType = articleType;
        this.content = content;
        this.registrationDateTime = LocalDateTime.now();
        this.registrationDate = registrationDateTime.toLocalDate();
        this.descIndex = toMillisecond(registrationDateTime);
    }

    private long toMillisecond(LocalDateTime registrationDateTime){
        return ZonedDateTime.of(registrationDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public void updateArticle(ArticleOneDto dto){
        this.title = dto.getTitle();
        this.articleType = dto.getArticleType();
        this.content = dto.getContent();
    }

    public void addComment(Comment comment){
        if(commentList == null){
            commentList = new ArrayList<>();
        }

        commentList.add(comment);
    }

    public void removeComment(Comment comment){
        if(commentList == null || commentList.size() == 0){
            return;
        }

        commentList.remove(comment);
    }
}
