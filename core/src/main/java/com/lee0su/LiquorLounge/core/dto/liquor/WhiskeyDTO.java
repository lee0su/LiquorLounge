package com.lee0su.LiquorLounge.core.dto.liquor;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiskeyDTO {
    private String Url;
    private Style Style;
    private String[] Characteristics;
    private String Bottler;
    private String Country;
    private String Region;

    @SerializedName("Cask Type")
    private String CaskType;
    private String Colouring;

    @SerializedName("Type")
    private String type;

    @SerializedName("Price per 70cl, USD")
    private double PricePer70cl;

    @SerializedName("Age Statement")
    private String AgeStatement;
    private String Name;
    private double Proof;

    @Getter
    @Setter
    public static class Style {
        private int Body;
        private int Richness;
        private int Smoke;
        private int Sweetness;
    }

}
