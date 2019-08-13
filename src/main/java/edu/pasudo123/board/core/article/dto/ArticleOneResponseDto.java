package edu.pasudo123.board.core.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.common.WriterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pasudo123 on 2019-07-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
@NoArgsConstructor
public class ArticleOneResponseDto {

    private Long id;
    private String title;
    private ArticleType articleType;
    private String content;
    private LocalDate registrationDate;

    private List<CommentOneResponseDto> commentList = new ArrayList<>();

    @JsonProperty("writer")
    private WriterDto writerDto;

    public ArticleOneResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleType = article.getArticleType();
        this.content = article.getContent();
        this.registrationDate = article.getRegistrationDate();
        this.writerDto = new WriterDto(article.getWriterUser());

        if(article.getCommentList().size() == 0){
            return;
        }

        if(commentList == null){
            this.commentList = new ArrayList<>();
        }

        for(Comment comment : article.getCommentList()){
            commentList.add(new CommentOneResponseDto(comment));
        }
    }
}
