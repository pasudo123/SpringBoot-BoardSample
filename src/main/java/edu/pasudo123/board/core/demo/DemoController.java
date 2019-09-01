package edu.pasudo123.board.core.demo;

import edu.pasudo123.board.core.config.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-09-01
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

    private final ProfileService profileService;

    @GetMapping("profile")
    public String profile(){
        return profileService.getProfile();
    }

}
