package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.dto.liquor.WineDTO;
import com.lee0su.LiquorLounge.core.entity.liquor.WineEntity;
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
public class WineService {

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    @PostConstruct
    public void init() {
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

            List<WineEntity> wineEntities = convertToEntity(wines);
            wineRepository.saveAll(wineEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<WineEntity> convertToEntity(List<WineDTO> wineDTOs) {
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

    public List<WineEntity> getAllWines() {
        return wineRepository.findAll();
    }

    public WineEntity createWine(WineEntity wine) {
        return wineRepository.save(wine);
    }

}
