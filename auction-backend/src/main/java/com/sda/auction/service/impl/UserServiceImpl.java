package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.mapper.UserMapper;
import com.sda.auction.model.User;
import com.sda.auction.repository.UserRepository;
import com.sda.auction.service.SecurityService;
import com.sda.auction.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private SecurityService securityService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, SecurityService securityService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        //convertim dto in entity
        User user = userMapper.convert(userDto);
        // hash-uim parola
        encodePassword(user);
        //persistam in baza de date
        User savedUser = userRepository.save(user);
        // convertim entitatea persistata din baza de date in dto pentru a o intoarce
        return userMapper.convert(savedUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public LoginDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()); // returneaza tot userul cu parola hash-uita din baza de date
        if(user == null){ // mai intai verificam daca exista userul
            throw new RuntimeException("Email address not exstent!");
        }
        if(securityService.passwordMatch(loginDto, user)){
            return securityService.createDtoWithJwt(loginDto); // returneaza un Dto cu jwt
        }
        throw new RuntimeException("Passwords don't match");
    }

    private void encodePassword(User user) {
        //encode user's password and put it in passwordEncoded variable
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        //set the encoded password to user entity
        user.setPassword(passwordEncoded);
    }


}
