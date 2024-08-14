package com.lee0su.LiquorLounge.core.controller.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.service.liquor.WhiskeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liquors")
public class LiquorController {

    @Autowired
    private WhiskeyService whiskeyService;

    @GetMapping("/whiskey")
    public ResponseEntity<List<WhiskeyEntity>> getAllWhiskeys() {
        System.out.println("OK1");
        List<WhiskeyEntity> whiskeys = whiskeyService.getAllWhiskeys();
        return ResponseEntity.ok(whiskeys);
    }

    @PostMapping("/whiskey")
    public ResponseEntity<WhiskeyEntity> createWhiskey(@RequestBody WhiskeyEntity whiskey) {
        System.out.println("OK2");
        WhiskeyEntity createdWhiskey = whiskeyService.createWhiskey(whiskey);
        return ResponseEntity.status(201).body(createdWhiskey);
    }

}
