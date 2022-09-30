package xyz.imaf6971.volgaitfinal.controllers;

import org.springframework.web.bind.annotation.*;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementDto;
import xyz.imaf6971.volgaitfinal.service.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public void createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        advertisementService.createAdvertisement(advertisementDto);
    }

    @GetMapping
    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    @GetMapping("/{advertisementId}")
    public AdvertisementDto getAdvertisementById(@PathVariable Long advertisementId) {
        return advertisementService.getAdvertisementById(advertisementId);
    }

    @PutMapping("/{advertisementId}")
    public void changeAdvertisementById(@PathVariable Long advertisementId, @RequestBody AdvertisementDto advertisementDto) {
        advertisementService.changeAdvertisement(advertisementId, advertisementDto);
    }

    @DeleteMapping("/{advertisementId}")
    public void deleteAdvertisementById(@PathVariable Long advertisementId) {
        advertisementService.deleteAdvertisementById(advertisementId);
    }

}
