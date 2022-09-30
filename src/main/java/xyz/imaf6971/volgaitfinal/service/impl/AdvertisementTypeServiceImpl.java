package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.exception.AdvertisementTypeNotFoundException;
import xyz.imaf6971.volgaitfinal.model.AdvertisementType;
import xyz.imaf6971.volgaitfinal.repository.AdvertisementTypeRepository;
import xyz.imaf6971.volgaitfinal.service.AdvertisementTypeService;

@Service
public class AdvertisementTypeServiceImpl implements AdvertisementTypeService {

    private final AdvertisementTypeRepository typeRepository;

    public AdvertisementTypeServiceImpl(AdvertisementTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public AdvertisementType getTypeById(Long typeId) {
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new AdvertisementTypeNotFoundException("Adv type with id " + typeId + "don't exists"));
    }

}
