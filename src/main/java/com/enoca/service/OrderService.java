package com.enoca.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enoca.domain.Customer;
import com.enoca.domain.Order;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.exception.message.ErrorMessage;
import com.enoca.repository.OrderRepository;
import com.enoca.requestDTo.OrderRequestDTO;
import com.enoca.responseDTO.OrderResponseDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;

	// CREATE ORDER
	public OrderResponseDTO createOrder(@Valid OrderRequestDTO orderRequestDTO) {

		// öncelikle ön taraftan gelen customer id var mı yok mu onu kontrol edelim
		Customer customer = customerService.findCustomer(orderRequestDTO.getCustomerId());

		Order order = new Order();

		order.setCreateDate(LocalDate.now());
		order.setCustomer(customer);
		order.setTotalPrice(orderRequestDTO.getTotalPrice());

		orderRepository.save(order);

		// ön tarafa responseDto dönelim

		OrderResponseDTO orderResponseDTO = getOrderResponse(order);

		return orderResponseDTO;
	}

	// GET ORDER BY ID
	public OrderResponseDTO getOrder(Long orderId) {

		Order order = findOrder(orderId);// aşağıda bir metod yazdım. başka yerlerde kullanırım diye

		OrderResponseDTO orderResponseDTO = getOrderResponse(order);

		return orderResponseDTO;
	}

	// UPDATE ORDER
	public OrderResponseDTO updateOrder(Long orderId, OrderRequestDTO orderRequestDTO) {

		Order order = findOrder(orderId);

		Customer customer = customerService.findCustomer(orderRequestDTO.getCustomerId());

		order.setTotalPrice(orderRequestDTO.getTotalPrice());
		order.setCustomer(customer);

		orderRepository.save(order);

		OrderResponseDTO orderResponseDTO = getOrder(order.getId());
		return orderResponseDTO;
	}

	// DELETE ORDER
	public OrderResponseDTO deleteOrder(Long orderId) {

		Order order = findOrder(orderId);

		orderRepository.delete(order);

		OrderResponseDTO orderResponseDTO = getOrderResponse(order);

		return orderResponseDTO;
	}

	// GET ALL ORDERS

	// burada pageable kullanmadım. direkt list olarak döndüm
	public List<OrderResponseDTO> getAllOrders() {

		List<Order> orders = orderRepository.findAll();

		List<OrderResponseDTO> dtoList = new ArrayList<>();

		for (Order w : orders) {

			OrderResponseDTO orderResponseDTO = getOrderResponse(w);

			dtoList.add(orderResponseDTO);

		}

		return dtoList;
	}

	// orderResponse dönen metod
	public OrderResponseDTO getOrderResponse(Order order) {

		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

		orderResponseDTO.setCreateDate(order.getCreateDate());
		orderResponseDTO.setCustomerName(order.getCustomer().getName());
		orderResponseDTO.setId(order.getId());
		orderResponseDTO.setTotalPrice(order.getTotalPrice());

		return orderResponseDTO;

	}

	// order dönen metod
	public Order findOrder(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND_MESSAGE));

		return order;

	}

	// get orders with date parameter
	public List<OrderResponseDTO> getOrdersWithDate(LocalDate date) {

		List<Order> orderList = orderRepository.getOrdersWithDate(date);

		List<OrderResponseDTO> dtoList = new ArrayList<>();

		for (Order each : orderList) {

			OrderResponseDTO orderResponseDTO = getOrderResponse(each);

			dtoList.add(orderResponseDTO);

		}

		return dtoList;

	}

}
