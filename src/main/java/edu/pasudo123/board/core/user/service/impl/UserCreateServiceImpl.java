package edu.pasudo123.board.core.user.service.impl;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.user.exception.UserNotFoundException;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.repository.UserRepository;
import edu.pasudo123.board.core.user.service.UserAddService;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class UserAddServiceImpl implements UserAddService {

    private final UserFindService userFindService;
    private final UserRepository userRepository;

    @Override
    public void addNewMyArticle(User user, Article article) {

        User foundUser = userFindService.findByUserRegistrationId(user.getUserRegistrationId());
        foundUser.addNewArticle(article);

    }

    @Override
    public void addNewMyComment(User user, Comment comment) {

    }
}
