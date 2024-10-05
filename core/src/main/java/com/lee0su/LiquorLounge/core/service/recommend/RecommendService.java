package com.lee0su.LiquorLounge.core.service.recommend;

import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.repository.liquor.WhiskeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    private final WhiskeyRepository whiskeyRepository;

    @Autowired
    public RecommendService(WhiskeyRepository whiskeyRepository) {
        this.whiskeyRepository = whiskeyRepository;
    }

    public WhiskeyEntity getRecommendedWhiskey(List<Integer> numbers) {

        int countOfOnes = 0;

        for (int number : numbers) {
            if (number == 1) {
                countOfOnes++;
            }
        }

        WhiskeyEntity whiskey;

        if (countOfOnes > 4) {
            whiskey = whiskeyRepository.findByName("Johnnie Walker Black Label 12 Year Old");
        } else {
            whiskey = whiskeyRepository.findByName("Balvenie 12 Year Old DoubleWood");
        }

        return  whiskey;

    }

}
