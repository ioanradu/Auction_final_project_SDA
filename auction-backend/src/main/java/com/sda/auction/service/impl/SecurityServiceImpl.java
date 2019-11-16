package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.model.User;
import com.sda.auction.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean passwordMatch(LoginDto userDto, User user) {
        return false;
    }

    @Override
    public LoginDto createDtoWithJwt(LoginDto loginDto) {
        return null;
    }
}
