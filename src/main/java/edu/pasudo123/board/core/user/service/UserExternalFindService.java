package edu.pasudo123.board.core.user.service;

import edu.pasudo123.board.core.user.model.User;

/**
 * Created by pasudo123 on 2019-08-11
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface UserExternalFindService {

    User findByUserRegistrationId(String userRegistrationId);
}
