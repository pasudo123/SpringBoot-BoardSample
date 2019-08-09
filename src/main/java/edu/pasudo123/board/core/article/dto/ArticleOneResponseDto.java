package edu.pasudo123.board.core.article.dto;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.comment.dto.CommentResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.config.auth.dto.SessionUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public ArticleOneResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleType = article.getArticleType();
        this.content = article.getContent();
        this.registrationDate = article.getRegistrationDate();

        if(article.getCommentList().size() == 0){
            return;
        }

        if(commentList == null){
            this.commentList = new ArrayList<>();
        }

        for(Comment comment : article.getCommentList()){
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
