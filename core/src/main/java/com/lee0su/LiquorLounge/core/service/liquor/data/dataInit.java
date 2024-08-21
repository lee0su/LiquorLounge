package com.lee0su.LiquorLounge.core.service.liquor.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lee0su.LiquorLounge.core.dto.liquor.GinDTO;
import com.lee0su.LiquorLounge.core.dto.liquor.RumDTO;
import com.lee0su.LiquorLounge.core.dto.liquor.WhiskeyDTO;
import com.lee0su.LiquorLounge.core.dto.liquor.WineDTO;
import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.RumEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WineEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.GinRepository;
import com.lee0su.LiquorLounge.core.repository.liquor.RumRepository;
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
    private final RumRepository rumRepository;
    private final WineRepository wineRepository;

    @Autowired
    public dataInit(WhiskeyRepository whiskeyRepository, GinRepository ginRepository, RumRepository rumRepository, WineRepository wineRepository) {
        this.whiskeyRepository = whiskeyRepository;
        this.ginRepository = ginRepository;
        this.rumRepository = rumRepository;
        this.wineRepository = wineRepository;
    }

    // 주석처리 X -> 서버 구동 시 데이터 입력 O
    // 주석처리 O -> 서버 구동 시 데이터 입력 X
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
                gin.setImg(data[3]);
                gin.setBottleSize(data[4]);
                gin.setAlcohol(Double.parseDouble(data[5].replace("%", "")));
                gin.setCountry(data[6]);

                if (data.length > 7) {
                    gin.setCity(data[7] != null && !data[7].isEmpty() ? data[7] : "X");

                    if (data.length > 8) {
                        gin.setWebsite(data[8] != null && !data[8].isEmpty() ? data[8] : "X");
                    } else {
                        gin.setWebsite("X");
                    }

                } else {
                    gin.setCity("X");
                }

                gins.add(gin);
            }

            List<GinEntity> ginEntities = convertToGinEntity(gins);
            ginRepository.saveAll(ginEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Rum
        try (InputStream inputStream = getClass().getResourceAsStream("/static/data/rum-database.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<RumDTO> rums = new ArrayList<>();
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                RumDTO rum = new RumDTO();
                rum.setName(data[0]);
                rum.setCompany(data[1]);
                rum.setCountry(data[2]);
                rum.setPrice(Double.parseDouble(data[3]));
                rum.setRating(Double.parseDouble(data[4]));
                rum.setScore(Double.parseDouble(data[5]));
                rum.setType(data[6]);
                rum.setRum_url(data[7]);
                rum.setImg_url(data[8]);
                rum.setBr_score(Double.parseDouble(data[9]));

                rums.add(rum);
            }

            List<RumEntity> rumEntities = convertToRumEntity(rums);
            rumRepository.saveAll(rumEntities);

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
                wine.setImg(data[8]);

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
            entity.setType(dto.getType());
            entity.setPricePer70cl(dto.getPricePer70cl());
            entity.setAgeStatement(dto.getAgeStatement() != null);
            entity.setProof(dto.getProof());
            entity.setCharacteristics(List.of(dto.getCharacteristics()));
            entity.setBody(dto.getStyle().getBody());
            entity.setRichness(dto.getStyle().getRichness());
            entity.setSmoke(dto.getStyle().getSmoke());
            entity.setSweetness(dto.getStyle().getSweetness());

            if (dto.getImg() != null && dto.getImg().length > 0) {
                String imgString = String.join(",", dto.getImg());
                entity.setImg(imgString);
            } else {
                entity.setImg("temp_img.png");
            }

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
            gin.setImg(dto.getImg());
            gin.setBottleSize(dto.getBottleSize());
            gin.setAlcohol(dto.getAlcohol());
            gin.setCountry(dto.getCountry());
            gin.setCity(dto.getCity());
            gin.setWebsite(dto.getWebsite());
            return gin;
        }).toList();
    }

    // Rum
    private List<RumEntity> convertToRumEntity(List<RumDTO> rumDTOs) {
        return rumDTOs.stream().map(dto -> {
            RumEntity rum = new RumEntity();
            rum.setName(dto.getName());
            rum.setCompany(dto.getCompany());
            rum.setCountry(dto.getCountry());
            rum.setPrice(dto.getPrice());
            rum.setRating(dto.getRating());
            rum.setScore(dto.getScore());
            rum.setType(dto.getType());
            rum.setRum_url(dto.getRum_url());
            rum.setImg_url(dto.getImg_url());
            rum.setBr_score(dto.getBr_score());

            return rum;
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
            wine.setImg(dto.getImg());
            return wine;
        }).toList();
    }

}
