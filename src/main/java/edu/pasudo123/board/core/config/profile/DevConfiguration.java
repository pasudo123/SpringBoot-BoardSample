package edu.pasudo123.board.core.config.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by pasudo123 on 2019-09-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Configuration
@Profile("dev")
public class DevConfiguration {

    private ProfileService profileService;

    @Bean
    public ProfileService profileService() {
        this.profileService = new ProfileService("dev 환경");

        System.out.println("1 ==========================");
        System.out.println(profileService.getProfile());
        System.out.println("2 ==========================");

        return profileService;
    }
}
