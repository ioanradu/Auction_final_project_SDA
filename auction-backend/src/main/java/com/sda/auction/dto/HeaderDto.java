package com.sda.auction.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class HeaderDto {

    private String firstName;
    private boolean isAdmin;


}