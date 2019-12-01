package com.sda.auction.controller;

import com.sda.auction.dto.ItemDto;
import com.sda.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/item")
public class AdminItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<ItemDto>> get() {
        List<ItemDto> result = itemService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ItemDto> post(@Valid @RequestBody ItemDto itemDto,
                                        HttpServletRequest request) throws ParseException {

        String userEmail = (String) request.getAttribute("userEmail");
        ItemDto itemDtoResult = itemService.addItem(itemDto, userEmail);

        return new ResponseEntity<>(itemDtoResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable Integer id) {
        System.out.println("Item id  = " + id);

        ItemDto itemDto = itemService.findById(id);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }


}