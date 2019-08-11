package edu.pasudo123.board.core.article.service.impl;

import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.dto.ArticleOneResponseDto;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.article.service.ArticleCreateService;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class ArticleCreateServiceImpl implements ArticleCreateService {

    private final UserFindService userFindService;
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public ArticleOneResponseDto addNewArticle(ArticleOneRequestDto dto, User currentUser) {

        Article savedArticle = articleRepository.save(dto.toEntity());
        User user = userFindService.findByUserRegistrationId(currentUser.getUserRegistrationId());
        savedArticle.setWriterUser(user);

        return new ArticleOneResponseDto(savedArticle);
    }
}
