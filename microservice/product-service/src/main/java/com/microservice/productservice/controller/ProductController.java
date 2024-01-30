package com.microservice.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.productservice.dto.ProductInStockResponse;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;

	@PostMapping("/add-product")
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(@RequestBody ProductRequest productRequest) {
		productService.addProduct(productRequest);
	}
	
	@GetMapping("/get-all-products")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/check-stock")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductInStockResponse> isInStock(@RequestParam List<String> id) throws InterruptedException {
		return productService.isInStock(id);
	}
}
