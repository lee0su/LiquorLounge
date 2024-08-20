package com.lee0su.LiquorLounge.core.entity.liquor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "whiskey")
public class WhiskeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    private String bottler;
    private String country;
    private String region;
    private String caskType;
    private String colouring;
    private String type;

    @Column(nullable = false)
    private double pricePer70cl; // 가격

    @Column(nullable = false)
    private boolean ageStatement;

    @Column(nullable = false)
    private double proof; // 미국식 도수 ( 우리나라 도수 = proof / 2 )

    @ElementCollection
    @CollectionTable(name = "whiskey_taste")
    private List<String> characteristics;

    // Style 필드
    @Column(nullable = false)
    private int body;

    @Column(nullable = false)
    private int richness;

    @Column(nullable = false)
    private int smoke;

    @Column(nullable = false)
    private int sweetness;

    private String img;

}

