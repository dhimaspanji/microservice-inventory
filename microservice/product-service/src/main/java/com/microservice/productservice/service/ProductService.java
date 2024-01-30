package com.microservice.productservice.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.productservice.dto.ProductInStockResponse;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.entity.ProductEntity;
import com.microservice.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public void addProduct(ProductRequest productRequest) {
		ProductEntity product = new ProductEntity();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setQty(productRequest.getQty());
		product.setCreatedAt(new Date());
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		
		return (List<ProductResponse>) products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ProductInStockResponse> isInStock(List<String> id) throws InterruptedException {
		log.info("Wait Started");
		
		Thread.sleep(10000);
		
		log.info("Wait Ended");
		return productRepository.findByIdIn(id).stream()
				.map(product -> ProductInStockResponse.builder()
						.id(product.getId())
						.isInStock(product.getQty() > 0)
						.build()
				).collect(Collectors.toList());
	}
	
	private ProductResponse mapToProductResponse(ProductEntity product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setName(product.getName());
		productResponse.setPrice(product.getPrice());
		productResponse.setQty(product.getQty());
		
		return productResponse;
	}
}
