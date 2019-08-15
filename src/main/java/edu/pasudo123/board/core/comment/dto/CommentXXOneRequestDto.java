package edu.pasudo123.board.core.comment.dto;

import edu.pasudo123.board.core.comment.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor
public class CommentXXOneRequestDto {

    private Long commentId;

    @NotBlank(message = "comment is not Empty.")
    private String comment;

    @Builder
    public CommentXXOneRequestDto(String comment) {
        this.comment = comment;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(this.comment)
                .build();
    }
}