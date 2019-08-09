package edu.pasudo123.board.core.article.repository;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by pasudo123 on 2019-07-27
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
