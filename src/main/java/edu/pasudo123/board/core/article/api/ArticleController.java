package edu.pasudo123.board.core.article.api;

import edu.pasudo123.board.core.article.dto.ArticleListResponseDto;
import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.service.*;
import edu.pasudo123.board.core.common.PageRequest;
import edu.pasudo123.board.core.config.auth.CurrentUser;
import edu.pasudo123.board.core.config.auth.CustomOAuth2User;
import edu.pasudo123.board.core.global.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pasudo123 on 2019-07-28
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;
    private final ArticleUpdateService articleUpdateService;
    private final ArticleDeleteService articleDeleteService;

    @PostMapping("article")
    public ResponseEntity<ArticleOneResponseDto> saveArticle(@CurrentUser CustomOAuth2User customOAuth2User,
                                                             @Valid @RequestBody ArticleOneRequestDto dto,
                                                             BindingResult bindingResult) throws ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(articleCreateService.addNewArticle(dto, customOAuth2User.getUser()));
    }

    @GetMapping("article")
    public ResponseEntity<Page<ArticleListResponseDto>> findAll(final PageRequest pageable) {

        pageable.setStandard("descIndex");

        return ResponseEntity.ok().body(articleFindService.findAll(pageable));
    }

    @GetMapping("article/{articleId}")
    public ResponseEntity<ArticleOneResponseDto> findOneById(@PathVariable Long articleId) {

        return ResponseEntity.ok().body(articleFindService.findOneById(articleId));
    }

    @PatchMapping("article/{articleId}")
    public ResponseEntity<ArticleOneResponseDto> updateOneById(@PathVariable Long articleId,
                                           @Valid @RequestBody ArticleOneRequestDto dto,
                                           BindingResult bindingResult) throws ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation Result Failed.", bindingResult);
        }

        return ResponseEntity.ok().body(articleUpdateService.updateOneById(articleId, dto));
    }

    @DeleteMapping("article/{articleId}")
    public ResponseEntity<String> deleteOneById(@PathVariable Long articleId) {

        articleDeleteService.deleteOneById(articleId);

        return ResponseEntity.ok().body("Success");
    }
}
