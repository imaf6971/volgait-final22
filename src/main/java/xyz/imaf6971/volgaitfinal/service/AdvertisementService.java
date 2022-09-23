package xyz.imaf6971.volgaitfinal.service;

import xyz.imaf6971.volgaitfinal.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {

    List<AdvertisementDto> getAllAdvertisements();

    AdvertisementDto getAdvertisementById(Long advertisementId);

    void createAdvertisement(AdvertisementDto advertisementDto);

    void deleteAdvertisementById(Long advertisementId);

    void changeAdvertisement(Long advertisementId, AdvertisementDto advertisementDto);

}
