package xyz.imaf6971.volgaitfinal.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.imaf6971.volgaitfinal.model.Advertisement;
import xyz.imaf6971.volgaitfinal.model.Role;
import xyz.imaf6971.volgaitfinal.model.User;
import xyz.imaf6971.volgaitfinal.repository.AdvertisementRepository;
import xyz.imaf6971.volgaitfinal.service.AdvertisementTypeService;
import xyz.imaf6971.volgaitfinal.service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static xyz.imaf6971.volgaitfinal.model.Roles.ADMIN;

@ExtendWith(MockitoExtension.class)
class AdvertisementServiceImplTest {

    AdvertisementServiceImpl underTest;

    @Mock
    AdvertisementRepository advertisementRepository;

    @Mock
    UserService userService;

    @Mock
    AdvertisementTypeService advertisementTypeService;

    @BeforeEach
    void setup() {
        underTest = new AdvertisementServiceImpl(advertisementRepository, userService, advertisementTypeService);
    }

    @Test
    void adminCanDeleteAdvertisement() {
        // given
        var admin = new User();
        var adminRole = new Role();
        adminRole.setTitle(ADMIN.value);
        admin.setRole(adminRole);
        when(userService.getCurrentUser())
                .thenReturn(admin);

        // when
        underTest.deleteAdvertisementById(1L);

        // then
        verify(advertisementRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void creatorCanDeleteAdvertisement() {
        // given
        var creator = Mockito.mock(User.class);
        when(creator.isAuthorOf(any()))
                .thenReturn(true);

        when(userService.getCurrentUser())
                .thenReturn(creator);
        when(advertisementRepository.findById(1L))
                .thenReturn(Optional.of(new Advertisement()));

        // when
        underTest.deleteAdvertisementById(1L);

        // then
        verify(advertisementRepository, times(1))
                .deleteById(1L);
    }

}