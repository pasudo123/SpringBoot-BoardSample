package edu.pasudo123.board.core.user;

import edu.pasudo123.board.core.article.model.Article;
import edu.pasudo123.board.core.article.model.ArticleType;
import edu.pasudo123.board.core.article.repository.ArticleRepository;
import edu.pasudo123.board.core.config.auth.dto.OAuthAttributesDto;
import edu.pasudo123.board.core.user.dto.UserOneRequestDto;
import edu.pasudo123.board.core.user.model.Role;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pasudo123 on 2019-08-10
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
public class UserRepositoryOnArticleTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private UserRepository userRepository;
    @Autowired private ArticleRepository articleRepository;

    private Article article;

    @Before
    public void 아티클_초기화(){
        this.article = Article.builder()
                .title("아티클 제목 테스트")
                .articleType(ArticleType.LIFE)
                .content("아티클 내용 테스트")
                .build();

        testEntityManager.persist(article);
    }

    @Test
    public void 작성유저_추가_및_아티클_삽입_테스트(){

        User user = User.builder()
                .userRegistrationId("123")
                .email("123@naver.com")
                .name("홍길동")
                .role(Role.USER)
                .build();

        article.setWriterUser(user);
        testEntityManager.persist(user);

        User foundUser = userRepository.findByUserRegistrationId(user.getUserRegistrationId()).get();

        assertThat(user.getArticleList().size(), is(1));
        assertThat(foundUser.getArticleList().size(), is(1));
        assertThat(user.getEmail(), is(foundUser.getEmail()));

        /** DB 동기화 및 영속성 초기화, 아티클 기준 테스트 **/
        testEntityManager.flush();
        testEntityManager.clear();
        Article foundArticle = articleRepository.findById(article.getId()).get();

        /** Hamcrest check (null or notNull) **/
        /** https://www.mkyong.com/unittest/hamcrest-how-to-assertthat-check-null-value/ **/
        assertThat(foundArticle.getWriterUser(), is(notNullValue()));
        assertThat(foundArticle.getWriterUser().getName(), is(user.getName()));
    }

    @Test
    public void 작성유저_수정_및_아티클_삽입_테스트(){

        User user = User.builder()
                .userRegistrationId("google_123")
                .email("oraeadoa@gamil.com")
                .name("오래도아")
                .role(Role.USER)
                .build();

        article.setWriterUser(user);
        testEntityManager.persist(user);
        testEntityManager.flush();

        Article foundArticle = articleRepository.findById(article.getId()).get();

        assertThat(foundArticle.getWriterUser(), is(article.getWriterUser()));

        /** 작성유저 수정 **/
        UserOneRequestDto userOneRequestDto = UserOneRequestDto.builder()
                .name("오래도아-수정")
                .profileImage("프로필이미지 수정")
                .build();

        User updatedUser = user.updateUser(userOneRequestDto);
        testEntityManager.persist(updatedUser);
        testEntityManager.flush();

        Article updateArticle = articleRepository.findById(article.getId()).get();

        assertThat(updatedUser.getName(), is(updateArticle.getWriterUser().getName()));
    }
}
