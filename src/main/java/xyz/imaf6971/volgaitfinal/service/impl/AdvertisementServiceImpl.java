package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementDto;
import xyz.imaf6971.volgaitfinal.exception.AdvertisementNotFoundException;
import xyz.imaf6971.volgaitfinal.model.Advertisement;
import xyz.imaf6971.volgaitfinal.repository.AdvertisementRepository;
import xyz.imaf6971.volgaitfinal.service.AdvertisementService;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private AdvertisementDto toDto(Advertisement advertisement) {
        return new AdvertisementDto(advertisement.getId(), advertisement.getTitle(), advertisement.getText());
    }

    @Override
    public AdvertisementDto getAdvertisementById(Long advertisementId) {
        return toDto(getById(advertisementId));
    }

    private Advertisement getById(Long id) {
        return advertisementRepository.findById(id)
                .orElseThrow(() -> new AdvertisementNotFoundException("Advertisement with id " + id + " not found"));
    }

    @Override
    public void createAdvertisement(AdvertisementDto advertisementDto) {
        var advertisement = new Advertisement();
        advertisement.setText(advertisementDto.text());
        advertisement.setTitle(advertisementDto.title());
        advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisementById(Long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    public void changeAdvertisement(Long advertisementId, AdvertisementDto advertisementDto) {

    }
}
