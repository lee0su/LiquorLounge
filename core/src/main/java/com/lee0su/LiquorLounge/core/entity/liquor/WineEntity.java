package com.lee0su.LiquorLounge.core.entity.liquor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "wine")
public class WineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color_wine;
    private double Price;
    private int ML;
    private double Rating;
    private int RatingsNum;
    private String Country;
    private double ABV;

}
