package xyz.imaf6971.volgaitfinal.service.impl;

import org.springframework.stereotype.Service;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementDto;
import xyz.imaf6971.volgaitfinal.exception.AdvertisementNotFoundException;
import xyz.imaf6971.volgaitfinal.exception.PermissionDeniedException;
import xyz.imaf6971.volgaitfinal.model.Advertisement;
import xyz.imaf6971.volgaitfinal.model.User;
import xyz.imaf6971.volgaitfinal.repository.AdvertisementRepository;
import xyz.imaf6971.volgaitfinal.service.AdvertisementService;
import xyz.imaf6971.volgaitfinal.service.AdvertisementTypeService;
import xyz.imaf6971.volgaitfinal.service.UserService;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;
    private final AdvertisementTypeService typeService;

    public AdvertisementServiceImpl(
            AdvertisementRepository advertisementRepository,
            UserService userService,
            AdvertisementTypeService typeService) {
        this.advertisementRepository = advertisementRepository;
        this.userService = userService;
        this.typeService = typeService;
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private AdvertisementDto toDto(Advertisement advertisement) {
        return new AdvertisementDto(
                advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getText(),
                advertisement.getType().getId()
        );
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
        var currentUser = userService.getCurrentUser();
        if (!currentUser.isCanPublish()) {
            throw new PermissionDeniedException("You cant publish");
        }
        var advertisement = new Advertisement();
        advertisement.setText(advertisementDto.text());
        advertisement.setTitle(advertisementDto.title());
        advertisement.setAuthor(currentUser);
        advertisement.setType(typeService.getTypeById(advertisementDto.typeId()));
        advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisementById(Long advertisementId) {
        var currentUser = userService.getCurrentUser();
        var advertisement = getById(advertisementId);
        if (!canDeleteAdvertisement(currentUser, advertisement)) {
            throw new PermissionDeniedException("Cannot delete advertisement with id " + advertisementId);
        }
        advertisementRepository.deleteById(advertisementId);
    }

    private boolean canDeleteAdvertisement(User user, Advertisement advertisement) {
        return user.isAdmin() || user.isAuthorOf(advertisement);
    }

    @Override
    public void changeAdvertisement(Long advertisementId, AdvertisementDto advertisementDto) {
        // TODO
    }

}
