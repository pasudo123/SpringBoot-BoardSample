package edu.pasudo123.board.core.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@JsonTest
@Slf4j
public class JsonPojoTest {

    @Autowired
    private JacksonTester<WriterDto> articleWriterDtoJacksonTester;

    @Test
    public void articleWriter_pojoJsonPropertyTest() throws Exception{

        final WriterDto dto = WriterDto.builder()
                .userRegistrationId("google_1234")
                .name("홍길동")
                .profileImage("프로필 이미지")
                .build();

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .hasJsonPathStringValue("@.registrationId");

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .hasJsonPathStringValue("@.name");

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .hasJsonPathStringValue("@.image");

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .extractingJsonPathStringValue("@.registrationId").isEqualTo("google_1234");

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .extractingJsonPathStringValue("@.name").isEqualTo("홍길동");

        assertThat(this.articleWriterDtoJacksonTester.write(dto))
                .extractingJsonPathStringValue("@.image").isEqualTo("프로필 이미지");
    }
}
