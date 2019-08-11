package edu.pasudo123.board.core.article.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleWriterDto {

    String userRegistrationId;
    String name;
    String profileImage;

    @Builder
    public ArticleWriterDto(String userRegistrationId, String name, String profileImage){
        this.userRegistrationId = userRegistrationId;
        this.name = name;
        this.profileImage = profileImage;
    }
}
