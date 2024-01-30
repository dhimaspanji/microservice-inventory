package com.microservice.orderservice.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.orderservice.dto.OrderDetailDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.ProductInStockResponse;
import com.microservice.orderservice.entity.OrderDetailEntity;
import com.microservice.orderservice.entity.OrderEntity;
import com.microservice.orderservice.repository.OrderDetailRepository;
import com.microservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;	
	private final OrderDetailRepository orderDetailRepository;
	private final WebClient.Builder webClientBuilder;

	public String placeOrder(OrderRequest orderRequest) {
		OrderEntity order = new OrderEntity();
		order.setStatus("IN PROGRESS");
		order.setOrderDate(new Date());
		order.setCreatedAt(new Date());
		
		List<OrderDetailEntity> orderDetail = orderRequest.getOrderDetailDtoList()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
		
		int totalPrice = 0;
		
		for (OrderDetailEntity orderDetailEntity : orderDetail) {
			totalPrice += orderDetailEntity.getPrice();
		}
		
		order.setTotalPrice(totalPrice);
		
		List<String> idProduct = orderRequest.getOrderDetailDtoList().stream().map(this::mapToString).collect(Collectors.toList());
		ProductInStockResponse[] productResponseArray = webClientBuilder.build().get()
								.uri("http://product-service/api/product/check-stock",
										uriBuilder -> uriBuilder.queryParam("id", idProduct).build())
								.retrieve()
								.bodyToMono(ProductInStockResponse[].class)
								.block();
		
		boolean allProductInStock = Arrays.stream(productResponseArray).allMatch(ProductInStockResponse::isInStock);
		
		if (allProductInStock) {			
			orderRepository.save(order);
			
			for (OrderDetailEntity orderDetailEntity : orderDetail) {
				OrderDetailEntity newOrderDetail = new OrderDetailEntity();
				newOrderDetail.setIdOrder(order.getId());
				newOrderDetail.setIdProduct(orderDetailEntity.getIdProduct());
				newOrderDetail.setQty(orderDetailEntity.getQty());
				newOrderDetail.setPrice(orderDetailEntity.getPrice());
				newOrderDetail.setCreatedAt(new Date());
				
				orderDetailRepository.save(newOrderDetail);
			}
			
			return "Order Placed Successfully";
		} else {
			throw new IllegalArgumentException("Product out of stock, please try again later");
		}
	}
	
	private OrderDetailEntity mapToDto(OrderDetailDto orderDetailDto) {
		OrderDetailEntity orderDetail = new OrderDetailEntity();
		orderDetail.setIdProduct(orderDetailDto.getIdProduct());
		orderDetail.setQty(orderDetailDto.getQty());
		orderDetail.setPrice(orderDetailDto.getPrice());
		
		return orderDetail;
	}
	
	private String mapToString(OrderDetailDto orderDetailDto) {
		return orderDetailDto.getIdProduct();
	}
}
