package com.lee0su.LiquorLounge.core.entity.liquor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "rum")
public class RumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
