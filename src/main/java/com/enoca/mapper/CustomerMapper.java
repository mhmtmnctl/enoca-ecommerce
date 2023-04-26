package com.enoca.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.enoca.domain.Customer;
import com.enoca.requestDTo.CustomerRequestDTO;
import com.enoca.responseDTO.CustomerResponseDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="orders", ignore=true)
	Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO );
	
	@Mapping(target="orderList", ignore=true)
	CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);

}
