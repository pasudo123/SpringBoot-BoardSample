package edu.pasudo123.board.core.user.service.impl;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.user.exception.UserNotFoundException;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.repository.UserRepository;
import edu.pasudo123.board.core.user.service.UserCreateService;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserFindService userFindService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public void addNewMyArticle(User user, Article article) {

        User foundUser = userFindService.findByUserRegistrationId(user.getUserRegistrationId());

        if(foundUser == null){
            throw new UserNotFoundException("현재 접속 유저는 존재하지 않습니다.");
        }

        foundUser.addNewArticle(article);

    }

    @Override
    public void addNewMyComment(User user, Comment comment) {

    }
}
