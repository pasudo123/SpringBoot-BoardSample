package edu.pasudo123.board.core.config;

import edu.pasudo123.board.core.config.auth.CustomOAuth2UserService;
import jdk.nashorn.internal.runtime.ECMAErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/**
 * Created by pasudo123 on 2019-08-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/login/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.oauth2Login()
                .loginPage("/login")
                .redirectionEndpoint()
                    .baseUri("/login/oauth2/callback/**")
                    .and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                    .and()
                .defaultSuccessUrl("/success");

        http.csrf()
                .disable()
            .headers()
                .frameOptions().disable();

    }
}
