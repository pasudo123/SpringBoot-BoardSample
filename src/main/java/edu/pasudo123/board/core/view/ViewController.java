package edu.pasudo123.board.core.view;

import edu.pasudo123.board.core.config.auth.CurrentUser;
import edu.pasudo123.board.core.config.auth.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
        return "login";
    }

    @GetMapping("/success")
    public void success(HttpServletResponse response) throws Exception{
        response.sendRedirect("/home");
    }

    @GetMapping("/home")
    public String home(@CurrentUser CustomOAuth2User currentUser){

        log.debug(currentUser.toString());

        return "home";
    }

    @GetMapping("/arti**")
    public String article(){
        return "home";
    }
}
