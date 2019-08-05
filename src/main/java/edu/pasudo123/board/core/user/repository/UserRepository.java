package edu.pasudo123.board.core.user.repository;

import edu.pasudo123.board.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by pasudo123 on 2019-08-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
