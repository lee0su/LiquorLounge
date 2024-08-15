package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.WhiskeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhiskeyService {

    private final WhiskeyRepository whiskeyRepository;

    @Autowired
    public WhiskeyService(WhiskeyRepository whiskeyRepository) {
        this.whiskeyRepository = whiskeyRepository;
    }

    public List<WhiskeyEntity> getAllWhiskeys() {
        return whiskeyRepository.findAll();
    }

    public WhiskeyEntity createWhiskey(WhiskeyEntity whiskey) {
        return whiskeyRepository.save(whiskey);
    }

}

