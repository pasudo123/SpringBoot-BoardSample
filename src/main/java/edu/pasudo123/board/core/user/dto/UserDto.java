package edu.pasudo123.board.core.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pasudo123.board.core.user.model.Role;
import edu.pasudo123.board.core.user.model.User;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class UserDto {

    @JsonProperty("registrationId") private String userRegistrationId;
    @JsonProperty("name") private String name;
    @JsonProperty("email") private String email;
    @JsonProperty("image") private String profileImage;
    @JsonProperty("role") private String role;

    public UserDto(User user){
        this.userRegistrationId = user.getUserRegistrationId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.role = user.getRoleKey();
    }
}
