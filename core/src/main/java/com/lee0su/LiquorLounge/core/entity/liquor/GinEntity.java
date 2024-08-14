package com.lee0su.LiquorLounge.core.entity.liquor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gin")
public class GinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String maker;
    private String type;
    private String bottleSize;
    private Double alcohol;
    private String country;
    private String city;
    private String website;
}
