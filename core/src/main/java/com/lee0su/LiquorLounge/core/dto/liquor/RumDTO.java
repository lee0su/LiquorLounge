package com.lee0su.LiquorLounge.core.dto.liquor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RumDTO {

    private String name;
    private String company;
    private String country;
    private double price;
    private double rating;
    private double score;
    private String type;
    private String rum_url;
    private String img_url;
    private double br_score;

}
