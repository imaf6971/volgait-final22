package xyz.imaf6971.volgaitfinal.controllers;

import org.springframework.web.bind.annotation.*;
import xyz.imaf6971.volgaitfinal.dto.AdvertDto;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementTypeDto;
import xyz.imaf6971.volgaitfinal.service.AdvertisementTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advertTypes")
public class AdvertisementTypeController {

    private final AdvertisementTypeService typeService;

    public AdvertisementTypeController(AdvertisementTypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public void createType(@RequestBody AdvertisementTypeDto advertisementTypeDto) {
        typeService.addType(advertisementTypeDto);
    }

    @GetMapping
    public List<AdvertisementTypeDto> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping("/{typeId}")
    public AdvertisementTypeDto getTypeById(@PathVariable Long typeId) {
        return typeService.getTypeDtoById(typeId);
    }

    @GetMapping("/{typeId}/adverts")
    public List<AdvertDto> getTypeAdverts(@PathVariable Long typeId) {
        return typeService.getTypeAdvertsById(typeId);
    }

}
