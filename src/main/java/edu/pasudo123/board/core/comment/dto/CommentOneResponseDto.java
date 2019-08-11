package edu.pasudo123.board.core.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.common.WriterDto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-08-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime createDateTime;
    private LocalDate createDate;

    @JsonProperty("writer")
    private WriterDto writerDto;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createDateTime = comment.getCreatedDate();
        this.createDate = this.createDateTime.toLocalDate();

        this.writerDto = WriterDto.builder()
                .userRegistrationId(comment.getWriterUser().getUserRegistrationId())
                .name(comment.getWriterUser().getName())
                .profileImage(comment.getWriterUser().getProfileImage())
                .build();
    }
}
