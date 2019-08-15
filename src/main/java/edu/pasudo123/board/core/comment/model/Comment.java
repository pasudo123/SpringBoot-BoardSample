package edu.pasudo123.board.core.comment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.common.BaseTimeEntity;
import edu.pasudo123.board.core.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private User writerUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Comment(String comment){
        this.comment = comment;
    }

    public void updateComment(CommentOneRequestDto dto){
        this.comment = dto.getComment();
    }

    public void setArticle(Article article){
        this.article = article;
    }

    public void setWriterUser(User user){
        this.writerUser = user;
        getWriterUser().addNewComment(this);
    }

    // 부모 댓글에서 자식 댓글 추가 ( 단방향 )
    public void addNewChildComment(Comment comment) {
        getCommentList().add(comment);
    }

    // 부모 댓글에서 자식 댓글 삭제 ( 단방향 )
    public void removeChildComment(Comment comment) {
        if(commentList == null || commentList.size() == 0){
            return;
        }

        getCommentList().remove(comment);
    }
}
