package com.enoca.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomerResponseDTO {

	private Long id;
	
	private String name;

	private List<Long>  orderId;
}
