package edu.pasudo123.board.core.comment.service.impl;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.service.ArticleExternalFindService;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.dto.CommentOneResponseDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.comment.repository.CommentRepository;
import edu.pasudo123.board.core.comment.service.CommentCreateService;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.service.UserExternalFindService;
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
public class CommentCreateServiceImpl implements CommentCreateService {

    private final UserExternalFindService userExternalFindService;
    private final ArticleExternalFindService articleExternalService;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public CommentOneResponseDto addNewComment(CommentOneRequestDto dto, User currentUser) {

        Comment comment = dto.toEntity();

        /** 아티클 X 댓글 :: Select Query **/
        Article article = articleExternalService.findOneById(dto.getArticleId());
        article.addNewComment(comment);

        /** 댓글 X 유저 :: Select Query **/
        User foundUser = userExternalFindService.findByUserRegistrationId(currentUser.getUserRegistrationId());
        comment.setWriterUser(foundUser);

        commentRepository.save(comment);

        return new CommentOneResponseDto(comment);
    }
}