package com.internal_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internal_service.model.Shopper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
class ShopperServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShopperService shopperService;

    @Test
    void test_get_shopper_id() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/shopper?productId=VV-1280808856")
                .accept(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test_add_shopper() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/shopper")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(getShopper())));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private Shopper getShopper() {
        return new Shopper().builder()
                .shopperId("test-shopper-id")
                .shelf(new ArrayList<>())
                .build();
    }

    @Test
    void test_validateLimit() {
        int limit;

        // test case default limit value
        limit = shopperService.validateLimit(null);
        Assertions.assertEquals(10, limit);

        // test case invalid limit
        Exception e = null;
        try {
            limit = shopperService.validateLimit(1001);
        } catch (Exception ex) {
            e = ex;
        }
        Assertions.assertNotNull(e);
        Assertions.assertTrue(e instanceof IllegalArgumentException);
        Assertions.assertEquals("max limit allowed is 1000", e.getMessage());
        e = null;

        // test case valid limit
        try {
            limit = shopperService.validateLimit(99);
        } catch (Exception ex) {
            e = ex;
        }
        Assertions.assertNull(e);
    }

}