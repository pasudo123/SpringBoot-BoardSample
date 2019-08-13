package edu.pasudo123.board.core.user.model;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.config.auth.dto.OAuthAttributesDto;
import edu.pasudo123.board.core.user.dto.UserOneRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_registration_id", columnNames = {"userRegistrationId"}),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userRegistrationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @OneToMany(mappedBy = "writerUser", fetch = FetchType.LAZY)
    List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "writerUser", fetch = FetchType.LAZY)
    List<Comment> commentList = new ArrayList<>();

    @Builder
    public User(String userRegistrationId, String name, String email, String profileImage, Role role) {
        this.userRegistrationId = userRegistrationId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
    }

    public void addNewArticle(Article article) {
        getArticleList().add(article);
    }

    public void addNewComment(Comment comment){
        getCommentList().add(comment);
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User updateUser(UserOneRequestDto dto){
        this.name = dto.getName();
        this.profileImage = dto.getProfileImage();

        return this;
    }
}
