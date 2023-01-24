package com.informationengineering.internetapplicationproject.Services;

import com.informationengineering.internetapplicationproject.Models.User;
import com.informationengineering.internetapplicationproject.Repositories.UserRepository;
import com.informationengineering.internetapplicationproject.Utils.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenHandler tokenHandler;


    public User getUser(String username) {
        return userRepository.findByUsername(username);

    }

    public String login(User user) throws BadCredentialsException, DisabledException {
        String token = tokenHandler.generateToken(user.getUsername());
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

}
