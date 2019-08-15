package edu.pasudo123.board.core.comment.api;

import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.dto.CommentXXOneRequestDto;
import edu.pasudo123.board.core.comment.service.CommentCreateService;
import edu.pasudo123.board.core.comment.service.CommentDeleteService;
import edu.pasudo123.board.core.comment.service.CommentUpdateService;
import edu.pasudo123.board.core.config.auth.CurrentUser;
import edu.pasudo123.board.core.config.auth.CustomOAuth2User;
import edu.pasudo123.board.core.global.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by pasudo123 on 2019-08-15
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 *
 * 코멘트에 대한 코멘트 api (CUD)
 *
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentXXController {

    private final CommentCreateService commentCreateService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;

    @PostMapping("comment-xx")
    public ResponseEntity<CommentOneResponseDto> saveCommentXX(@CurrentUser CustomOAuth2User customOAuth2User,
                                                               @Valid @RequestBody CommentXXOneRequestDto dto,
                                                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(commentCreateService.addNewCommentXX(dto, customOAuth2User.getUser()));
    }
}
