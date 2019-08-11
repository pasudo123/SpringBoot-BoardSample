package edu.pasudo123.board.core.comment.api;

import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.service.CommentCreateService;
import edu.pasudo123.board.core.global.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/comment")
    public ResponseEntity<CommentOneResponseDto> saveComment(@Valid @RequestBody CommentOneRequestDto dto,
                                                             BindingResult bindingResult) throws ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(commentCreateService.addNewComment(dto));
    }
}
