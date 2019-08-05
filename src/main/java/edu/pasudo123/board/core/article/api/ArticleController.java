package edu.pasudo123.board.core.article.api;

import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.dto.ArticleResponseDto;
import edu.pasudo123.board.core.global.exception.ValidationException;
import edu.pasudo123.board.core.article.service.ArticleService;
import edu.pasudo123.board.core.common.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pasudo123 on 2019-07-28
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @PostMapping("article")
    public ResponseEntity<ArticleOneResponseDto> saveArticle(@Valid @RequestBody ArticleOneRequestDto dto,
                                                             BindingResult bindingResult) throws ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(articleService.addNewArticle(dto));
    }

    @GetMapping("article")
    public ResponseEntity<Page<ArticleResponseDto>> findAll(final PageRequest pageable){

        pageable.setStandard("descIndex");

        return ResponseEntity.ok().body(articleService.findAll(pageable));
    }

    @GetMapping("article/{articleId}")
    public ResponseEntity<ArticleOneResponseDto> findOneById(@PathVariable Long articleId){

        return ResponseEntity.ok().body(articleService.findOneById(articleId));
    }

    @PatchMapping("article/{articleId}")
    public ResponseEntity<?> updateOneById(@PathVariable Long articleId,
                                           @Valid @RequestBody ArticleOneRequestDto dto,
                                           BindingResult bindingResult) throws ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(articleService.updateOneById(articleId, dto));
    }

    @DeleteMapping("article/{articleId}")
    public ResponseEntity<?> deleteOneById(@PathVariable Long articleId){

        articleService.deleteOneById(articleId);

        return ResponseEntity.ok().body("Success");
    }
}
