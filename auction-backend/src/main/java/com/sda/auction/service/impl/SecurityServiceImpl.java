package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.jwt.TokenProvider;
import com.sda.auction.model.User;
import com.sda.auction.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean passwordMatch(LoginDto userDto, User user) {
        String plainTextPassword = userDto.getPassword(); //parola ne-hasuita
        String hashedPassword = user.getPassword(); //parola hash-uita
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }

    @Override
    public LoginDto createDtoWithJwt(User user) {
        LoginDto result = new LoginDto();
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        String jwd = tokenProvider.createJwt(user);
        result.setJwd(jwd);
        return result;
    }
}
