package edu.pasudo123.board.core.comment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.common.BaseTimeEntity;
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

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Builder
    public Comment(String comment){
        this.comment = comment;
    }

    public void setArticle(Article article){
        if(this.article != null){
            this.article.removeComment(this);
        }

        this.article = article;
        article.addComment(this);
    }
}
