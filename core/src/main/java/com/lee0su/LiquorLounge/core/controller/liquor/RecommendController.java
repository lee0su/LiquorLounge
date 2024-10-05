package com.lee0su.LiquorLounge.core.controller.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.service.recommend.RecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @PostMapping("/whiskey")
    public ResponseEntity<WhiskeyEntity> recommendWhiskey(@RequestBody List<Integer> numbers) {

        WhiskeyEntity whiskey = recommendService.getRecommendedWhiskey(numbers);
        return ResponseEntity.ok(whiskey);

    }

}
