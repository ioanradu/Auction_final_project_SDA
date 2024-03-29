package com.sda.auction.controller;

import com.sda.auction.dto.ItemDto;
import com.sda.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/item")
public class UserItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ItemDto>> get() {
        List<ItemDto> result = itemService.findAllForBidding();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        System.out.println("Item id  = " + id);
        String userEmail = (String) request.getAttribute("userEmail");
        ItemDto itemDto = itemService.findByIdForUser(id, userEmail);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }


}