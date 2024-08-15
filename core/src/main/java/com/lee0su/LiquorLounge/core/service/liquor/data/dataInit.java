package com.lee0su.LiquorLounge.core.service.liquor.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lee0su.LiquorLounge.core.dto.liquor.GinDTO;
import com.lee0su.LiquorLounge.core.dto.liquor.WhiskeyDTO;
import com.lee0su.LiquorLounge.core.dto.liquor.WineDTO;
import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WineEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.GinRepository;
import com.lee0su.LiquorLounge.core.repository.liquor.WhiskeyRepository;
import com.lee0su.LiquorLounge.core.repository.liquor.WineRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class dataInit {

    private final GinRepository ginRepository;
    private final WhiskeyRepository whiskeyRepository;
    private final WineRepository wineRepository;

    @Autowired
    public dataInit(WhiskeyRepository whiskeyRepository, GinRepository ginRepository, WineRepository wineRepository) {
        this.whiskeyRepository = whiskeyRepository;
        this.ginRepository = ginRepository;
        this.wineRepository = wineRepository;
    }

    @PostConstruct
    public void init() {

        // Whiskey
        try (InputStream inputStream = getClass().getResourceAsStream("/static/data/whisky-database.json");
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            List<WhiskeyDTO> whiskeys = gson.fromJson(reader, new TypeToken<List<WhiskeyDTO>>(){}.getType());
            List<WhiskeyEntity> whiskeyEntities = convertToWhiskeyEntity(whiskeys);
            whiskeyRepository.saveAll(whiskeyEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gin
        try (InputStream inputStream = getClass().getResourceAsStream("/static/data/gin-database.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<GinDTO> gins = new ArrayList<>();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                GinDTO gin = new GinDTO();
                gin.setName(data[0]);
                gin.setMaker(data[1] != null && !data[1].isEmpty() ? data[1] : "X");
                gin.setType(data[2]);
                gin.setBottleSize(data[3]);
                gin.setAlcohol(Double.parseDouble(data[4].replace("%", "")));
                gin.setCountry(data[5]);

                if (data.length > 6) {
                    gin.setCity(data[6] != null && !data[6].isEmpty() ? data[6] : "X");

                    if (data.length > 7) {
                        gin.setWebsite(data[7] != null && !data[7].isEmpty() ? data[7] : "X");
                    }

                }

                gins.add(gin);
            }

            List<GinEntity> ginEntities = convertToGinEntity(gins);
            ginRepository.saveAll(ginEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Wine
        try (InputStream inputStream = getClass().getResourceAsStream("/static/data/wine-database.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<WineDTO> wines = new ArrayList<>();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                WineDTO wine = new WineDTO();
                wine.setName(data[0]);
                wine.setColor_wine(data[1]);
                wine.setPrice(Double.parseDouble(data[2]));
                wine.setML(Integer.parseInt(data[3]));
                wine.setRating(Double.parseDouble(data[4]));
                wine.setRatingsNum(Integer.parseInt(data[5]));
                wine.setCountry(data[6]);
                wine.setABV(Double.parseDouble(data[7]));

                wines.add(wine);
            }

            List<WineEntity> wineEntities = convertToWineEntity(wines);
            wineRepository.saveAll(wineEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Whiskey
    private List<WhiskeyEntity> convertToWhiskeyEntity(List<WhiskeyDTO> whiskeyDTOs) {
        return whiskeyDTOs.stream().map(dto -> {
            WhiskeyEntity entity = new WhiskeyEntity();
            entity.setName(dto.getName());
            entity.setUrl(dto.getUrl());
            entity.setBottler(dto.getBottler() != null && !dto.getBottler().isEmpty() ? dto.getBottler() : "X");
            entity.setCountry(dto.getCountry() != null && !dto.getCountry().isEmpty() ? dto.getCountry() : "X");
            entity.setRegion(dto.getRegion() != null && !dto.getRegion().isEmpty() ? dto.getRegion() : "X");
            entity.setCaskType(dto.getCaskType() != null && !dto.getCaskType().isEmpty() ? dto.getCaskType() : "X");
            entity.setColouring(dto.getColouring() != null && !dto.getColouring().isEmpty() ? dto.getColouring() : "No");
            entity.setPricePer70cl(dto.getPricePer70cl());
            entity.setAgeStatement(dto.getAgeStatement() != null);
            entity.setProof(dto.getProof());
            entity.setCharacteristics(List.of(dto.getCharacteristics()));
            entity.setBody(dto.getStyle().getBody());
            entity.setRichness(dto.getStyle().getRichness());
            entity.setSmoke(dto.getStyle().getSmoke());
            entity.setSweetness(dto.getStyle().getSweetness());
            return entity;
        }).toList();
    }

    // Gin
    private List<GinEntity> convertToGinEntity(List<GinDTO> ginDTOs) {
        return ginDTOs.stream().map(dto -> {
            GinEntity gin = new GinEntity();
            gin.setName(dto.getName());
            gin.setMaker(dto.getMaker());
            gin.setType(dto.getType());
            gin.setBottleSize(dto.getBottleSize());
            gin.setAlcohol(dto.getAlcohol());
            gin.setCountry(dto.getCountry());
            gin.setCity(dto.getCity());
            gin.setWebsite(dto.getWebsite());
            return gin;
        }).toList();
    }

    // Wine
    private List<WineEntity> convertToWineEntity(List<WineDTO> wineDTOs) {
        return wineDTOs.stream().map(dto -> {
            WineEntity wine = new WineEntity();
            wine.setName(dto.getName());
            wine.setColor_wine(dto.getColor_wine());
            wine.setPrice(dto.getPrice());
            wine.setML(dto.getML());
            wine.setRating(dto.getRating());
            wine.setRatingsNum(dto.getRatingsNum());
            wine.setCountry(dto.getCountry());
            wine.setABV(dto.getABV());
            return wine;
        }).toList();
    }

}
