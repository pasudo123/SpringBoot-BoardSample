package edu.pasudo123.board.core.comment;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.comment.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by pasudo123 on 2019-08-04
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Article article;

    @Before
    public void init(){

        // given
        article = Article.builder()
                .articleType(ArticleType.LIFE)
                .title("홍길동의 게시글")
                .content("홍길동의 게시글 내용")
                .build();

        testEntityManager.persist(article);
        testEntityManager.flush();
    }

    @Test
    public void addComment_Test() {

        Comment comment = Comment.builder()
                .comment("첫번째 댓글을 달았습니다.")
                .build();

        Article foundArticle = articleRepository.findById(1L).get();

        testEntityManager.persist(comment);

        comment.setArticle(foundArticle);

        testEntityManager.flush();

        assertThat(foundArticle.getContent(), is(this.article.getContent()));
    }
}
