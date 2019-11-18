package com.sda.auction.service;

import com.sda.auction.dto.ItemDto;
import java.text.ParseException;

public interface ItemService {

    ItemDto addItem(ItemDto itemDto, String ownerEmail) throws ParseException;

}