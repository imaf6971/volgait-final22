package xyz.imaf6971.volgaitfinal.service;

import xyz.imaf6971.volgaitfinal.dto.AdvertDto;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementTypeDto;
import xyz.imaf6971.volgaitfinal.model.AdvertisementType;

import java.util.List;

public interface AdvertisementTypeService {

    AdvertisementTypeDto getTypeDtoById(Long typeId);

    AdvertisementType getTypeById(Long typeId);

    List<AdvertisementTypeDto> getTypes();

    List<AdvertDto> getTypeAdvertsById(Long typeId);

    void addType(AdvertisementTypeDto advertisementTypeDto);

}
