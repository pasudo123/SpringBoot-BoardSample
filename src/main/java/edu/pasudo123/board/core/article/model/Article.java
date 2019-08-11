package edu.pasudo123.board.core.article.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.common.BaseAuditingEntity;
import edu.pasudo123.board.core.common.BaseTimeEntity;
import edu.pasudo123.board.core.common.Writer;
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

    @JsonBackReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "name", column = @Column(name = "article_user_name")),
//            @AttributeOverride(name = "email", column = @Column(name = "article_user_email")),
//            @AttributeOverride(name = "userProfile", column = @Column(name = "article_user_profile"))
//    })
//    private Writer writer;

    @ManyToOne(fetch = FetchType.LAZY)
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
        this.writerUser.getArticleList().add(this);
    }

    public void addComment(Comment comment){
        comment.setArticle(this);
        getCommentList().add(comment);
    }

    public void removeComment(Comment comment){
        if(commentList == null || commentList.size() == 0){
            return;
        }

        commentList.remove(comment);
    }
}
