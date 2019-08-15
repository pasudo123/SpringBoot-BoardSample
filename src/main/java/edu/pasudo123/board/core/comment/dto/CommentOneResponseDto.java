package edu.pasudo123.board.core.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.common.WriterDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-08-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentOneResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime createDateTime;
    private LocalDate createDate;

    @JsonProperty("writer")
    private WriterDto writerDto;

    @JsonProperty("childComment")
    private List<CommentOneResponseDto> commentList = new ArrayList<>();

    public CommentOneResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createDateTime = comment.getCreatedDate();
        this.createDate = this.createDateTime.toLocalDate();
        this.writerDto = new WriterDto(comment.getWriterUser());

        if(comment.getCommentList().size() == 0){
            return;
        }

        for(Comment element : comment.getCommentList()){
            commentList.add(new CommentOneResponseDto(element));
        }
    }
}
