package com.lee0su.LiquorLounge.core.service.liquor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lee0su.LiquorLounge.core.dto.liquor.WhiskeyDTO;
import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.WhiskeyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class WhiskeyService {

    private final WhiskeyRepository whiskeyRepository;

    @Autowired
    public WhiskeyService(WhiskeyRepository whiskeyRepository) {
        this.whiskeyRepository = whiskeyRepository;
    }

    @PostConstruct
    public void init() {
        try (InputStream inputStream = getClass().getResourceAsStream("/static/data/whisky-database.json");
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            List<WhiskeyDTO> whiskeys = gson.fromJson(reader, new TypeToken<List<WhiskeyDTO>>(){}.getType());
            List<WhiskeyEntity> whiskeyEntities = convertToEntoty(whiskeys);
            whiskeyRepository.saveAll(whiskeyEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<WhiskeyEntity> convertToEntoty(List<WhiskeyDTO> whiskeyDTOs) {
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

}

