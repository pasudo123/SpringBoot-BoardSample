package edu.pasudo123.board.core.article.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.common.BaseTimeEntity;
import edu.pasudo123.board.core.user.model.User;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @JsonManagedReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private User writerUser;

    private LocalDateTime registrationDateTime;
    private LocalDate registrationDate;
    private long descIndex;

    @Builder
    public Article(String title, ArticleType articleType, String content, User writerUser){
        this.title = title;
        this.articleType = articleType;
        this.content = content;
        this.writerUser = writerUser;
        this.registrationDateTime = LocalDateTime.now();
        this.registrationDate = registrationDateTime.toLocalDate();
        this.descIndex = toMillisecond(registrationDateTime);
    }

    private long toMillisecond(LocalDateTime registrationDateTime){
        return ZonedDateTime.of(registrationDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public void updateArticle(ArticleOneRequestDto dto){
        this.title = dto.getTitle();
        this.articleType = dto.getArticleType();
        this.content = dto.getContent();
    }

    public void setWriterUser(User user){
        this.writerUser = user;
        getWriterUser().addNewArticle(this);
    }

    public void addNewComment(Comment comment){
        getCommentList().add(comment);
        comment.setArticle(this);
    }

    public void removeComment(Comment comment){
        if(commentList == null || commentList.size() == 0){
            return;
        }

        getCommentList().remove(comment);
    }
}