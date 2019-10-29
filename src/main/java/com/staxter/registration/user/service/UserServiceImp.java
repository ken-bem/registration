package com.staxter.registration.user.service;

import com.staxter.registration.exception.UserAlreadyExistsException;
import com.staxter.registration.models.User;
import com.staxter.registration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {


    public final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(User user) throws UserAlreadyExistsException {

        if (userRepository.findByUserName(user.getUserName()).isPresent()){
            //Todo: error code
            throw new UserAlreadyExistsException();
        }

        user.setHashedPassword(passwordEncoder.encode(user.getPlainTextPassword()));

        //Todo: set user roles;


        return userRepository.save(user);

    }
}
