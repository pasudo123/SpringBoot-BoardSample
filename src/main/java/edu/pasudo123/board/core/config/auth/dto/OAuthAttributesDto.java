package edu.pasudo123.board.core.config.auth.dto;

import edu.pasudo123.board.core.config.auth.AuthType;
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
    private String userId;
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public OAuthAttributesDto(Map<String, Object> attributes, String userId, String name, String email, String profileImage){
        this.attributes = attributes;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }


    public static OAuthAttributesDto of(String registrationId, Map<String, Object> attributes){

        String userId = "";

        for(AuthType type : AuthType.values()){
            if(!type.name().equalsIgnoreCase(registrationId)){
                continue;
            }

            userId = type.name().toUpperCase() + "_" + attributes.get("sub");
        }

        return OAuthAttributesDto.builder()
                .attributes(attributes)
                .userId(userId)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .build();
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .role(Role.USER)
                .build();
    }
}
