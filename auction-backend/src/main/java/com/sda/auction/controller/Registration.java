package com.sda.auction.controller;

import com.sda.auction.dto.UserDto;
import com.sda.auction.model.User;
import com.sda.auction.service.UserService;
import com.sda.auction.validator.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register") // acesta este path-ul
public class Registration {
    @Autowired
    UserService userService;

    @Autowired
    private UserDtoValidator userDtoValidator;

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    // serveste un requset de tip post, consuma un json si produce un json
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto userDto) {
        userDtoValidator.validate(userDto);
        UserDto userDtoResult = userService.addUser(userDto);
        return new ResponseEntity<>(userDtoResult, HttpStatus.OK);
    }


}
