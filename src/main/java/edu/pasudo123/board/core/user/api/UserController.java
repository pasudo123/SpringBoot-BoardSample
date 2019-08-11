package edu.pasudo123.board.core.user.api;

import edu.pasudo123.board.core.config.auth.CurrentUser;
import edu.pasudo123.board.core.config.auth.CustomOAuth2User;
import edu.pasudo123.board.core.user.dto.UserDto;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserFindService userFindService;

    @GetMapping("current-user")
    public ResponseEntity<UserDto> currentUser(@CurrentUser CustomOAuth2User currentUser){

        User user = currentUser.getUser();

        return ResponseEntity.ok().body(new UserDto(user));
    }
}
