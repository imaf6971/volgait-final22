package xyz.imaf6971.volgaitfinal.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import xyz.imaf6971.volgaitfinal.service.AdvertisementService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdvertisementController.class)
public class AdvertisementControllerRestTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AdvertisementService serviceMock;

    @Test
    @WithMockUser
    void getAdvertisements() throws Exception {
        mvc.perform(get("/api/v1/advertisements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
