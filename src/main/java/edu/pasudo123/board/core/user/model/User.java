package edu.pasudo123.board.core.user.model;

import edu.pasudo123.board.core.config.auth.dto.OAuthAttributesDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String userId, String name, String email, String profileImage, Role role){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public User updateUser(OAuthAttributesDto oAuthAttributes){
        this.name = oAuthAttributes.getName();
        this.profileImage = oAuthAttributes.getProfileImage();

        return this;
    }
}
