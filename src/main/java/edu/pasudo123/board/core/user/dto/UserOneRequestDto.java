package edu.pasudo123.board.core.user.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class UserOneRequestDto {

    private String name;
    private String profileImage;

    @Builder
    public UserOneRequestDto(String name, String profileImage){
        this.name = name;
        this.profileImage = profileImage;
    }

}
