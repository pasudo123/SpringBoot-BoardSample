package edu.pasudo123.board.core.comment.dto;

import edu.pasudo123.board.core.comment.model.Comment;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CommentOneRequestDto {

    @NotNull(message = "articleId is required")
    private Long articleId;

    @NotNull(message = "comment is required")
    private String comment;

    public Comment toEntity(){
        return Comment.builder()
                .comment(this.comment)
                .build();
    }
}