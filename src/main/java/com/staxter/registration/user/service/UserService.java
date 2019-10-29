package com.staxter.registration.user.service;

import com.staxter.registration.exception.UserAlreadyExistsException;
import com.staxter.registration.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(User user) throws UserAlreadyExistsException;

}
