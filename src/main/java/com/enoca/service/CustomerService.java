package com.enoca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enoca.domain.Customer;
import com.enoca.domain.Order;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.exception.message.ErrorMessage;
import com.enoca.mapper.CustomerMapper;
import com.enoca.repository.CustomerRepository;
import com.enoca.requestDTo.CustomerRequestDTO;
import com.enoca.responseDTO.CustomerResponseDTO;
import com.enoca.responseDTO.SearchCustomerResponseDTO;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper customerMapper;

	//REGISTER CUSTOMER
	public CustomerResponseDTO registerCustomer(CustomerRequestDTO customerRequestDTO) {

		Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);//mapper kullandım

		customerRepository.save(customer);// db'ye kayıt ettik

		// şimdi ön tarafa response dönelim

		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);

		return customerResponseDTO;
	}
	
	//GET CUSTOMER WITH ID
	public CustomerResponseDTO getCustomer(Long customerId) {
		
		Customer customer =  findCustomer(customerId);
	
		
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);
		
		return customerResponseDTO;
	}
	
	//UPDATE CUSTOMER
	public CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO customerRequestDTO) {
		
		Customer customer =  findCustomer(customerId);//böyle bir customer var mı yok mu? varsa getir, yoksa hata fırlat
		
		customer.setAge(customerRequestDTO.getAge());
		customer.setName(customerRequestDTO.getName());
		
		customerRepository.save(customer);
		
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(customer);
		
		return customerResponseDTO;
	}

	//FIND CUSTOMER WITH SEARCH
	public Page<SearchCustomerResponseDTO> searchCustomerWithPage(Pageable pageable, String q) {
		
		Page<Customer> customerPage = customerRepository.searchAllProducts(q, pageable);
		
		List<Long> orderIds = new ArrayList<>();//customere ait order id'leri tutmak için bir list oluşturdum
		
		Page<SearchCustomerResponseDTO> customerResponseDTO = customerPage.map(customer -> {
			
			List<Order> cOrders= customer.getOrders();
			
			for (Order w : cOrders) {
				
				orderIds.add(w.getId());//burada customer'ın order id'lerini listeme ekledim. (lambda ile de yapılabilirdi)
				
			}
			
			SearchCustomerResponseDTO searchCustomerResponseDTO = new SearchCustomerResponseDTO();
			
			searchCustomerResponseDTO.setId(customer.getId());
			searchCustomerResponseDTO.setName(customer.getName());
			searchCustomerResponseDTO.setOrderId(orderIds);
			
			return searchCustomerResponseDTO;
		});
		
		
		return customerResponseDTO;
	}
	
	
	private Customer findCustomer(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(//optional döndüğü için hata mekanizması kullanmam gerekiyor
																		//kendim bir hata döndürdüm kontrollü şekilde
() -> new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_MESSAGE));
		
		return customer;
		
	}

	public CustomerResponseDTO deleteCustomer(Long customerId) {
		
		Customer customer =  findCustomer(customerId);
		
		Customer c = customer;//silindikten sonra customer kaybolacağı için buraya geçici olarak koydum
		
		customerRepository.delete(customer);
		
		CustomerResponseDTO customerResponseDTO = customerMapper.customerToCustomerResponseDTO(c);
		
		return customerResponseDTO;
	}





}
