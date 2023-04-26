package com.enoca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.requestDTo.OrderRequestDTO;
import com.enoca.responseDTO.OrderResponseDTO;
import com.enoca.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	//create order
	@PostMapping("/create")
	public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO){
		
		OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
		
		return ResponseEntity.ok(orderResponseDTO);
	}
	
	//get order with id
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderResponseDTO> getOrderWithId(@PathVariable("id") Long orderId){
		
		OrderResponseDTO orderResponseDTO = orderService.getOrder(orderId);
		
		return ResponseEntity.ok(orderResponseDTO);
	}
	
	//update order
	@PutMapping("/update/{id}")
	public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable("id") Long orderId, @RequestBody OrderRequestDTO orderRequestDTO){
		
		OrderResponseDTO orderResponseDTO = orderService.updateOrder(orderId,orderRequestDTO);
		
		return ResponseEntity.ok(orderResponseDTO);
	}
	
	//delete order
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OrderResponseDTO> deleteOrder(@PathVariable("id") Long orderId){
		
		OrderResponseDTO orderResponseDTO = orderService.deleteOrder(orderId);
		
		return ResponseEntity.ok(orderResponseDTO);
	}
	
	//getAll orders
	@GetMapping("/getAll")
	public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
		
		List<OrderResponseDTO> dtoList = orderService.getAllOrders();
		
		return ResponseEntity.ok(dtoList);
	}
	
	
}
