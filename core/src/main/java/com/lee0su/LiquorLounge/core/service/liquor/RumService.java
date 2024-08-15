package com.lee0su.LiquorLounge.core.service.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.RumEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.RumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RumService {

    private final RumRepository rumRepository;

    @Autowired
    public RumService(RumRepository rumRepository) {
        this.rumRepository = rumRepository;
    }

    public List<RumEntity> getAllRums() {
        return rumRepository.findAll();
    }

    public RumEntity createRum(RumEntity rum) {
        return rumRepository.save(rum);
    }

}
