package com.bozzco.microserviceproduct;

import com.bozzco.microserviceproduct.dto.ProductDTO;
import com.bozzco.microserviceproduct.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MicroserviceProductApplicationTests {
    @Container // To make junit5 understand this is a mongodb container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;
    @DynamicPropertySource // adds this property to spring context dynamically at Run Time
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }

    private ProductDTO getProductRequest(){
        ProductDTO product = new ProductDTO();
        product.setProductName("Moflix Diapers");
        product.setDescription("For Babies");
        product.setPrice(BigDecimal.valueOf(4000));
        return product;
    }


    @Test
    void createProduct() throws Exception {
        ProductDTO productDTO = getProductRequest();
        String productDTOString = objectMapper.writeValueAsString(productDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productDTOString))
                .andExpect(status().isOk());
        Assertions.assertTrue(productRepository.findAll().size()==1);
    }

}
