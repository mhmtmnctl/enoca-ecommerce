package com.enoca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.requestDTo.CustomerRequestDTO;
import com.enoca.responseDTO.CustomerResponseDTO;
import com.enoca.responseDTO.SearchCustomerResponseDTO;
import com.enoca.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	//Register Customer
	@PostMapping("/register")
	public ResponseEntity<CustomerResponseDTO> registerCustomer(
			@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO customerResponseDTO = customerService.registerCustomer(customerRequestDTO);
		return ResponseEntity.ok(customerResponseDTO);
	}
	
	//get Customer
	@GetMapping("/get/{id}")
	public ResponseEntity<CustomerResponseDTO> getCustomerWithId(@PathVariable("id") Long customerId){
		
		CustomerResponseDTO customerResponseDTO = customerService.getCustomer(customerId);
		
		return ResponseEntity.ok(customerResponseDTO);
	}
	
	//update customer
	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerRequestDTO customerRequestDTO){
		
		CustomerResponseDTO customerResponseDTO = customerService.updateCustomer(customerId,customerRequestDTO);
		
		return ResponseEntity.ok(customerResponseDTO);
	}
	
	//delete customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable("id") Long customerId){
		
		CustomerResponseDTO customerResponseDTO = customerService.deleteCustomer(customerId);
		
		return ResponseEntity.ok(customerResponseDTO);
			
	}

	// Get Customer With Search
	@GetMapping("/find")
	public ResponseEntity<Page<SearchCustomerResponseDTO>> searchCustomer(
			@RequestParam(required = false, defaultValue = "",name = "q") String q,//parametreyi q değişkenine atadım.
			@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestParam("sort") String prop, // neye göre sıralanacağı...
		    @RequestParam(required = false, defaultValue = "DESC",name = "type") String type)

	 {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(type), prop));
		Page<SearchCustomerResponseDTO> customerPageDTO = customerService.searchCustomerWithPage(pageable,q);
		return ResponseEntity.ok(customerPageDTO);
	}
	
	@GetMapping("/unordered")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomersUnordered(){
		List<CustomerResponseDTO> customerResponseDTOList = customerService.getUnorderedCustomers();
		return ResponseEntity.ok(customerResponseDTOList);
	}

}
