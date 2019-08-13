package edu.pasudo123.board.core.comment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.common.BaseTimeEntity;
import edu.pasudo123.board.core.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pasudo123 on 2019-07-27
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String comment;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private User writerUser;

    @Builder
    public Comment(String comment){
        this.comment = comment;
    }

    public void setArticle(Article article){
        this.article = article;
    }

    public void setWriterUser(User user){
        this.writerUser = user;
        getWriterUser().addNewComment(this);
    }
}
