package com.sda.auction.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@EqualsAndHashCode
@Data
public class BidDto {

    private Integer id;

    @NotNull // nu poate fi NotEmpty pe un integer
    @Positive
    private Integer price;

    @NotNull
    private Integer itemId;

}
