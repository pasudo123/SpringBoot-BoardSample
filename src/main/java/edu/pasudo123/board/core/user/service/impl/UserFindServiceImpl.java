package edu.pasudo123.board.core.user.service.impl;

import edu.pasudo123.board.core.user.exception.UserNotFoundException;
import edu.pasudo123.board.core.user.model.User;
import edu.pasudo123.board.core.user.repository.UserRepository;
import edu.pasudo123.board.core.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-09
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class UserFindServiceImpl implements UserFindService {

    private final UserRepository userRepository;

    @Override
    public User currentUser() {

        return null;
    }

    @Override
    public User findByUserRegistrationId(String userRegistrationId) {
        return userRepository.findByUserRegistrationId(userRegistrationId)
                .orElseThrow(() -> new UserNotFoundException("현재 접속 유저는 존재하지 않습니다."));
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("현재 접속 유저는 존재하지 않습니다."));
    }
}
