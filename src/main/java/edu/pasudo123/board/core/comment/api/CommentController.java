package edu.pasudo123.board.core.comment.api;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.service.CommentCreateService;
import edu.pasudo123.board.core.comment.service.CommentDeleteService;
import edu.pasudo123.board.core.comment.service.CommentUpdateService;
import edu.pasudo123.board.core.config.auth.CurrentUser;
import edu.pasudo123.board.core.config.auth.CustomOAuth2User;
import edu.pasudo123.board.core.global.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.OutputKeys;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentCreateService commentCreateService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;

    @PostMapping("/comment")
    public ResponseEntity<CommentOneResponseDto> saveComment(@CurrentUser CustomOAuth2User customOAuth2User,
                                                             @Valid @RequestBody CommentOneRequestDto dto,
                                                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(commentCreateService.addNewComment(dto,customOAuth2User.getUser()));
    }

    @PatchMapping("/comment/{commentId}")
    public ResponseEntity<CommentOneResponseDto> updateOnById(@PathVariable long commentId,
                             @Valid @RequestBody CommentOneRequestDto dto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(commentUpdateService.updateOnById(commentId, dto));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteOneById(@PathVariable long commentId){

        commentDeleteService.deleteOneById(commentId);

        return ResponseEntity.ok().body("Delete Success");
    }
}
