package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.dto.liquor.GinDTO;
import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.GinRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GinService {

    @Autowired
    private GinRepository ginRepository;

    @Autowired
    public GinService(GinRepository ginRepository) {
        this.ginRepository = ginRepository;
    }

    @PostConstruct
    public void init() {
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

            List<GinEntity> ginEntities = convertToEntity(gins);
            ginRepository.saveAll(ginEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<GinEntity> convertToEntity(List<GinDTO> ginDTOs) {
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

    public List<GinEntity> getAllGins() {
        return ginRepository.findAll();
    }

    public GinEntity createGin(GinEntity gin) {
        return ginRepository.save(gin);
    }

}
