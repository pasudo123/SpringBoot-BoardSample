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
    private String userRegistrationId;
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public OAuthAttributesDto(Map<String, Object> attributes, String userRegistrationId, String name, String email, String profileImage){
        this.attributes = attributes;
        this.userRegistrationId = userRegistrationId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }


    public static OAuthAttributesDto of(String registrationId, Map<String, Object> attributes){

        String userRegistrationId = "";

        for(AuthType type : AuthType.values()){
            if(!type.name().equalsIgnoreCase(registrationId)){
                continue;
            }

            userRegistrationId = type.name().toUpperCase() + "_" + attributes.get("sub");
        }

        return OAuthAttributesDto.builder()
                .attributes(attributes)
                .userRegistrationId(userRegistrationId)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .build();
    }

    public User toEntity(){
        return User.builder()
                .userRegistrationId(userRegistrationId)
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .role(Role.USER)
                .build();
    }
}
