package com.staxter.registration.user.service;

import com.staxter.registration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository
                .findByUserName(s)
                .map(curr -> {
                    Set<GrantedAuthority> authorities = new HashSet<>();


                        authorities.add(new SimpleGrantedAuthority("USER"));


                    return new org.springframework
                            .security
                            .core
                            .userdetails
                            .User(curr.getUserName(), curr.getHashedPassword(), authorities);
                })
                .orElseThrow(()-> new UsernameNotFoundException(s));
    }
}
