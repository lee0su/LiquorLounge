package com.lee0su.LiquorLounge.core.dto.liquor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WineDTO {

    private String name;
    private String color_wine;
    private double Price;
    private int ML;
    private double Rating;
    private int RatingsNum;
    private String Country;
    private double ABV;
    private String img;

}
