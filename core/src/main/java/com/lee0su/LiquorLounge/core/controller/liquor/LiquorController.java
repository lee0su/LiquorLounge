package com.lee0su.LiquorLounge.core.controller.liquor;

import com.lee0su.LiquorLounge.core.entity.liquor.GinEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.RumEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WhiskeyEntity;
import com.lee0su.LiquorLounge.core.entity.liquor.WineEntity;
import com.lee0su.LiquorLounge.core.service.liquor.GinService;
import com.lee0su.LiquorLounge.core.service.liquor.RumService;
import com.lee0su.LiquorLounge.core.service.liquor.WhiskeyService;
import com.lee0su.LiquorLounge.core.service.liquor.WineService;
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

    @Autowired
    private RumService rumService;

    @Autowired
    private WineService wineService;

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

    @GetMapping("/rum")
    public ResponseEntity<List<RumEntity>> getAllRums() {
        List<RumEntity> rums = rumService.getAllRums();
        return ResponseEntity.ok(rums);
    }

    @PostMapping("/rum")
    public ResponseEntity<RumEntity> createRums(@RequestBody RumEntity rum) {
        RumEntity createdRum = rumService.createRum(rum);
        return ResponseEntity.status(201).body(createdRum);
    }

    @GetMapping("/wine")
    public ResponseEntity<List<WineEntity>> getAllWines() {
        List<WineEntity> wines = wineService.getAllWines();
        return ResponseEntity.ok(wines);
    }

    @PostMapping("/wine")
    public ResponseEntity<WineEntity> createWines(@RequestBody WineEntity wine) {
        WineEntity createdWine = wineService.createWine(wine);
        return ResponseEntity.status(201).body(createdWine);
    }

}
