package edu.pasudo123.board.core.article;

import edu.pasudo123.board.core.article.dto.ArticleOneRequestDto;
import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.article.service.ArticleFindService;
import edu.pasudo123.board.core.comment.dto.CommentOneRequestDto;
import edu.pasudo123.board.core.comment.model.Comment;
import edu.pasudo123.board.core.user.model.Role;
import edu.pasudo123.board.core.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryOnUserTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ArticleRepository articleRepository;

    private User user;

    @Before
    public void 작성유저_초기화(){
        this.user = User.builder()
                .userRegistrationId("123")
                .email("123@naver.com")
                .name("홍길동")
                .role(Role.USER)
                .build();

        testEntityManager.persist(user);
    }

    @Test
    public void 새_아티클_추가_및_작성유저_삽입_테스트(){

        Article article = Article.builder()
                .title("아티클 제목")
                .articleType(ArticleType.LIFE)
                .content("아티클 내용")
                .build();

        article.setWriterUser(user);
        testEntityManager.persist(article);

        Article foundArticle = articleRepository.findById(article.getId()).get();

        assertThat(article.getWriterUser().getArticleList().size(), is(1));
        assertThat(article.getWriterUser().getName(), is(foundArticle.getWriterUser().getName()));
    }

    @Test
    public void 아티클_수정_및_작성유저_변경확인_테스트(){

        Article article = Article.builder()
                .title("아티클 제목")
                .articleType(ArticleType.LIFE)
                .content("아티클 내용")
                .build();

        article.setWriterUser(user);
        testEntityManager.persist(article);
        testEntityManager.flush();

        ArticleOneRequestDto dto = ArticleOneRequestDto.builder()
                .title("아티클의 새로운 제목")
                .articleType(ArticleType.MOVIE)
                .content("아티클의 새로운 내용")
                .build();

        Article foundArticle = articleRepository.findById(article.getId()).get();
        foundArticle.updateArticle(dto);
        testEntityManager.persist(foundArticle);

        /** 여기서 업데이트 쿼리가 날라감 :: 영속영역이랑 DB 랑 동기화가 되지 않았기 때문에 **/
        testEntityManager.flush();

        assertThat(user.getArticleList().size(), is(1));
        assertThat(user.getArticleList().get(0).getTitle(), is(foundArticle.getTitle()));
    }

    @Test
    public void 아티클_삭제_테스트(){

        Article article = Article.builder()
                .title("아티클 제목")
                .articleType(ArticleType.LIFE)
                .content("아티클 내용")
                .build();

        article.setWriterUser(user);
        testEntityManager.persist(article);
        testEntityManager.flush();

        articleRepository.deleteById(article.getId());
        testEntityManager.flush();

        Optional<Article> optionalArticle = articleRepository.findById(article.getId());

        assertThat(optionalArticle, is(Optional.empty()));
    }

    @Test
    public void 댓글이_있는_아티클_삭제_테스트() {

        Article article = Article.builder()
                .title("아티클 제목")
                .articleType(ArticleType.LIFE)
                .content("아티클 내용")
                .build();
        article.setWriterUser(user);
        testEntityManager.persist(article);
        testEntityManager.flush();

        Comment comment = Comment.builder()
                .comment("첫번째 댓글을 달았습니다.")
                .build();
        comment.setWriterUser(user);
        comment.setArticle(article);
        testEntityManager.persist(comment);
        testEntityManager.flush();

        /**
         * Article 엔티티는 Comment 를 @OneToMany 로 들고있다.
         * 여기서 CascadeType.REMOVE 와 orphanRemoval = true 로 삭제 가능
         */
        articleRepository.deleteById(article.getId());
        testEntityManager.flush();
    }
}
