package edu.pasudo123.board.core.config.auth.dto;

import edu.pasudo123.board.core.user.model.Role;
import edu.pasudo123.board.core.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class OAuthAttributesDto {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String login;
    private String email;
    private String profileImage;

    @Builder
    public OAuthAttributesDto(Map<String, Object> attributes, String nameAttributeKey, String name, String login, String email, String profileImage){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.login = login;
        this.email = email;
        this.profileImage = profileImage;
    }


    public static OAuthAttributesDto of(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributesDto.builder()
                .name((String) attributes.get("name"))
                .login((String) attributes.get("login"))
                .email((String) attributes.get("email"))
                .profileImage("https://avatars1.githubusercontent.com/u/17797352?s=400&u=d8373790d3ea5b4ac35323b0effd1171c2a14a3d&v=4")
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .username(login)
                .profileImage(profileImage)
                .role(Role.USER)
                .build();
    }
}
