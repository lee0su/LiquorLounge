package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.GinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GinService {

    private final GinRepository ginRepository;

    @Autowired
    public GinService(GinRepository ginRepository) {
        this.ginRepository = ginRepository;
    }

    public List<GinEntity> getAllGins() {
        return ginRepository.findAll();
    }

    public GinEntity createGin(GinEntity gin) {
        return ginRepository.save(gin);
    }

}
