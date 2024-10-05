package com.lee0su.LiquorLounge.core.repository.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhiskeyRepository extends JpaRepository<WhiskeyEntity, Long> {
    WhiskeyEntity findByName(String name);
}
