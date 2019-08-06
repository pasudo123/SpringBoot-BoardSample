package edu.pasudo123.board.core.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-08-03
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Slf4j
@Controller
public class ViewController {

    @GetMapping("/login")
    public String login(){

        log.debug("로그인 화면 진입.");

        return "login";
    }

    @GetMapping("/home")
    public String home(){

        log.debug("홈 화면 진입");

        return "index";
    }

    @GetMapping("/arti**")
    public String article(){

        log.debug("홈 화면 진입");

        return "index";
    }
}
