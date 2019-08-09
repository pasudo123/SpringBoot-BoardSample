package edu.pasudo123.board.core.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by pasudo123 on 2019-08-09
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Writer implements Serializable {

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_profile")
    private String userProfile;

    @Builder
    public Writer(String name, String email, String userProfile){
        this.name = name;
        this.email = email;
        this.userProfile = userProfile;
    }
}
