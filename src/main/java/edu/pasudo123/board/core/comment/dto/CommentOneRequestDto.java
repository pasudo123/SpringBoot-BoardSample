package edu.pasudo123.board.core.comment.dto;

import edu.pasudo123.board.core.comment.model.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentOneRequestDto {

    private Long articleId;

    @NotBlank(message = "comment is not empty.")
    private String comment;

    @Builder
    public CommentOneRequestDto(String comment){
        this.comment = comment;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(this.comment)
                .build();
    }
}