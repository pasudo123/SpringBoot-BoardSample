package edu.pasudo123.board.core.comment.dto;

import edu.pasudo123.board.core.comment.model.Comment;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CommentOneRequestDto {

    private Long articleId;

    @NotBlank(message = "comment is not empty.")
    private String comment;

    public Comment toEntity() {
        return Comment.builder()
                .comment(this.comment)
                .build();
    }
}