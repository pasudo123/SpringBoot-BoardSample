package edu.pasudo123.board.core.config.auth.dto;

import edu.pasudo123.board.core.user.model.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class SessionUserDto implements Serializable {

    private String name;
    private String email;
    private String profileImage;

    public SessionUserDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
    }
}
