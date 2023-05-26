package com.epam.wallpaper.controller;

import com.epam.wallpaper.model.WallpaperResponse;
import com.epam.wallpaper.service.WallpaperOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WallpaperOrderingController.class)
public class WallpaperOrderingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    public WallpaperOrderService wallpaperOrderService;

    @Test
    public void testGetOrders() throws Exception {
        Mockito.when(wallpaperOrderService.getAllAreas())
                .thenReturn(getTestData());

        MvcResult mvcResult = mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andReturn();

        List<WallpaperResponse> cryptoStats = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertNotNull(cryptoStats);
        assertFalse(cryptoStats.isEmpty());
    }

    @Test
    public void testGetOrdersDuplicates() throws Exception {
        Mockito.when(this.wallpaperOrderService.getDuplicates())
                .thenReturn(getTestData());

        MvcResult mvcResult = mockMvc.perform(get("/api/orders/duplicates"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<WallpaperResponse> cryptoStats = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertNotNull(cryptoStats);
        assertFalse(cryptoStats.isEmpty());
    }

    @Test
    public void testGetOrderCubes() throws Exception {
        Mockito.when(this.wallpaperOrderService.getCubicShapes())
                .thenReturn(getTestData());

        MvcResult mvcResult = mockMvc.perform(get("/api/orders/cubes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<WallpaperResponse> cryptoStats = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        assertNotNull(cryptoStats);
        assertFalse(cryptoStats.isEmpty());
    }

    private static List<WallpaperResponse> getTestData() {
        return List.of(
                new WallpaperResponse("1x1x1", 5, 0));
    }
}
