package xyz.imaf6971.volgaitfinal.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.imaf6971.volgaitfinal.dto.AdvertisementDto;
import xyz.imaf6971.volgaitfinal.service.AdvertisementService;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class AdvertisementControllerTest {

    @Mock
    AdvertisementService advertisementService;

    @Test
    void createAdvertisement() {
        var underTest = new AdvertisementController(advertisementService);

        var advertisement = new AdvertisementDto(null,"title", "text", null);
        underTest.createAdvertisement(advertisement);

        Mockito.verify(advertisementService, Mockito.times(1))
                .createAdvertisement(eq(advertisement));
    }

}