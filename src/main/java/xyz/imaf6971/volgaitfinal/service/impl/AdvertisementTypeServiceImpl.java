package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.dto.AdvertDto;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementTypeDto;
import xyz.imaf6971.volgaitfinal.exception.AdvertisementTypeNotFoundException;
import xyz.imaf6971.volgaitfinal.model.Advertisement;
import xyz.imaf6971.volgaitfinal.model.AdvertisementType;
import xyz.imaf6971.volgaitfinal.repository.AdvertisementTypeRepository;
import xyz.imaf6971.volgaitfinal.service.AdvertisementTypeService;

import java.util.List;

@Service
public class AdvertisementTypeServiceImpl implements AdvertisementTypeService {

    private final AdvertisementTypeRepository typeRepository;

    public AdvertisementTypeServiceImpl(AdvertisementTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public AdvertisementTypeDto getTypeDtoById(Long typeId) {
        return toDto(getTypeById(typeId));
    }

    @Override
    public AdvertisementType getTypeById(Long typeId) {
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new AdvertisementTypeNotFoundException("Adv type with id " + typeId + "don't exists"));
    }

    @Override
    public List<AdvertisementTypeDto> getTypes() {
        return typeRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<AdvertDto> getTypeAdvertsById(Long typeId) {
        return getTypeById(typeId)
                .getAdvertisements().stream()
                .map(this::toAdvertDto)
                .toList();
    }

    @Override
    public void addType(AdvertisementTypeDto advertisementTypeDto) {
        var type = new AdvertisementType();
        type.setTitle(advertisementTypeDto.title());
        typeRepository.save(type);
    }

    private AdvertDto toAdvertDto(Advertisement advertisement) {
        return new AdvertDto(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getText()
        );
    }

    private AdvertisementTypeDto toDto(AdvertisementType advertisementType) {
        return new AdvertisementTypeDto(
                advertisementType.getId(),
                advertisementType.getTitle()
        );
    }

}
