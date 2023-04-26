package com.enoca.requestDTo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

	
	
	private Double totalPrice;

	
	private Long customerId;
}

//createAt servis katmanında now() ile setleyeceğim