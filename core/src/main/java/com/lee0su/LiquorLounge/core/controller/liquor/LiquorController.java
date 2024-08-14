package com.lee0su.LiquorLounge.core.controller.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.service.liquor.GinService;
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

    @Autowired
    private GinService ginService;

    @GetMapping("/whiskey")
    public ResponseEntity<List<WhiskeyEntity>> getAllWhiskeys() {
        List<WhiskeyEntity> whiskeys = whiskeyService.getAllWhiskeys();
        return ResponseEntity.ok(whiskeys);
    }

    @PostMapping("/whiskey")
    public ResponseEntity<WhiskeyEntity> createWhiskey(@RequestBody WhiskeyEntity whiskey) {
        WhiskeyEntity createdWhiskey = whiskeyService.createWhiskey(whiskey);
        return ResponseEntity.status(201).body(createdWhiskey);
    }

    @GetMapping("/gin")
    public ResponseEntity<List<GinEntity>> getAllGins() {
        List<GinEntity> gins = ginService.getAllGins();
        return ResponseEntity.ok(gins);
    }

    @PostMapping("/gin")
    public ResponseEntity<GinEntity> createGins(@RequestBody GinEntity gin) {
        GinEntity createdGin = ginService.createGin(gin);
        return ResponseEntity.status(201).body(createdGin);
    }

}
