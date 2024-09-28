package com.internal_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internal_service.model.Product;
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

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;

    @Test
    void test_get_products() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/product?shopperId=S-1000&brand=Girlds&category=Babies&limit=99")
                .accept(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test_add_product() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(getProduct())));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void test_validateLimit() {
        int limit;

        // test case default limit value
        limit = productService.validateLimit(null);
        Assertions.assertEquals(10, limit);

        // test case invalid limit
        Exception e = null;
        try {
            limit = productService.validateLimit(101);
        } catch (Exception ex) {
            e = ex;
        }
        Assertions.assertNotNull(e);
        Assertions.assertTrue(e instanceof IllegalArgumentException);
        Assertions.assertEquals("max allowed limit is 100", e.getMessage());
        e = null;

        // test case valid limit
        try {
            limit = productService.validateLimit(99);
        } catch (Exception ex) {
            e = ex;
        }
        Assertions.assertNull(e);
    }

    private Product getProduct() {
        return new Product().builder()
                .productId("testId123")
                .category("Pokemon-Cards")
                .brand("Nintendo").build();
    }
}