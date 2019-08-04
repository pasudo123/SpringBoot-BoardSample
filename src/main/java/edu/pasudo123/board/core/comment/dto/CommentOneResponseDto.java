package edu.pasudo123.board.core.comment.dto;

import edu.pasudo123.board.core.article.dto.ArticleResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CommentOneResponseDto {

    private String comment;
    private LocalDateTime createDate;

    public CommentOneResponseDto(Comment comment){
        this.comment = comment.getComment();
        this.createDate = comment.getCreatedDate();
    }
}
