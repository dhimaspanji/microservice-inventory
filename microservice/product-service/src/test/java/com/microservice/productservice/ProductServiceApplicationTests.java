package com.microservice.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.dto.ProductRequest;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = createProductReq();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/add-product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	private ProductRequest createProductReq() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("Kopi Hitam");
		productRequest.setPrice(4000);
		productRequest.setQty(5);
		
		return productRequest;
	}

}
