package edu.pasudo123.board.core.article.model;

import lombok.Getter;

/**
 * Created by pasudo123 on 2019-07-27
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public enum ArticleType {

    LIFE("일상"),
    MOVIE("영화"),
    MUSIC("음악");

    private final String title;

    ArticleType(String title){
        this.title = title;
    }

}
