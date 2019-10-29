package com.staxter.registration.user;

import com.staxter.registration.exception.UserAlreadyExistsException;
import com.staxter.registration.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User createUser(User user) throws UserAlreadyExistsException;


}
