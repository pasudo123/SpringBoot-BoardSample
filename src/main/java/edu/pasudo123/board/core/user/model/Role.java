package edu.pasudo123.board.core.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
