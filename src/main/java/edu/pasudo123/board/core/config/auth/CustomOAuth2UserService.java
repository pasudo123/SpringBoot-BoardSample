package edu.pasudo123.board.core.config.auth;

import edu.pasudo123.board.core.config.auth.dto.OAuthAttributesDto;
import edu.pasudo123.board.core.config.auth.dto.SessionUserDto;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * Created by pasudo123 on 2019-08-05
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String nameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuthAttributesDto oAuthAttributes = OAuthAttributesDto.of(registrationId, oAuth2User.getAttributes());

        User user = saveOrUpdate(oAuthAttributes);
        httpSession.setAttribute("user", new SessionUserDto(user));

        return CustomOAuth2User.builder()
                .user(user)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())))
                .attributes(oAuthAttributes.getAttributes())
                .nameAttributeKey(nameAttributeKey)
                .build();
    }

    private User saveOrUpdate(OAuthAttributesDto oAuthAttributesDto) {

        User user = userRepository.findByUserRegistrationId(oAuthAttributesDto.getUserRegistrationId())
                .orElseGet(() -> oAuthAttributesDto.toEntity());

        return userRepository.save(user);
    }
}
