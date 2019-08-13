package edu.pasudo123.board.core.user.service.impl;

import edu.pasudo123.board.core.user.repository.UserRepository;
import edu.pasudo123.board.core.user.service.UserDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
public class UserDeleteServiceImpl implements UserDeleteService {

    private final UserRepository userRepository;

    @Override
    public void deleteOneByRegistrationId(String registrationId) {
        userRepository.deleteByUserRegistrationId(registrationId);
    }
}
