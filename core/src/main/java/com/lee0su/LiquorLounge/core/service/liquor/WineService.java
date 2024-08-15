package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.WineEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    private final WineRepository wineRepository;

    @Autowired
    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public List<WineEntity> getAllWines() {
        return wineRepository.findAll();
    }

    public WineEntity createWine(WineEntity wine) {
        return wineRepository.save(wine);
    }

}
