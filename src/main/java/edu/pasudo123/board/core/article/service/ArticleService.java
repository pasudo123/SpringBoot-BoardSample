package edu.pasudo123.board.core.article.service;

import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.dto.ArticleListResponseDto;
import edu.pasudo123.board.core.article.exception.ArticleNotFoundException;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.common.PageRequest;
import edu.pasudo123.board.core.config.auth.dto.SessionUserDto;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Session;
import java.util.List;

/**
 * Created by pasudo123 on 2019-07-28
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    @Transactional
    public ArticleOneResponseDto addNewArticle(ArticleOneRequestDto requestDto) {

        Article savedArticle = articleRepository.save(requestDto.toEntity());

        return new ArticleOneResponseDto(savedArticle);
    }

    @Transactional(readOnly = true)
    public Page<ArticleListResponseDto> findAll(final PageRequest pageable) {

        Page<Article> articlePage = articleRepository.findAll(pageable.of());

        return articlePage.map(ArticleListResponseDto::new);
    }

    @Transactional(readOnly = true)
    public ArticleOneResponseDto findOneById(final long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));

        return new ArticleOneResponseDto(article);
    }

    @Transactional
    public ArticleOneResponseDto updateOneById(final long articleId, ArticleOneRequestDto dto) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));
        article.updateArticle(dto);
        articleRepository.save(article);

        return new ArticleOneResponseDto(article);
    }

    @Transactional
    public void deleteOneById(final long articleId) {

        articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException("해당 게시글은 존재하지 않습니다."));
        articleRepository.deleteById(articleId);

    }
}
