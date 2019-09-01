package edu.pasudo123.board.core.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pasudo123 on 2019-09-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "server")
@Getter
@Setter
@ToString
public class ProfileConfiguration {

    private String email;

}
