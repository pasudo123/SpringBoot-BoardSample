package edu.pasudo123.board.core.user.service;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.user.model.User;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface UserAddService {

    void addNewMyArticle(User user, Article article);

    void addNewMyComment(User user, Comment comment);
}
