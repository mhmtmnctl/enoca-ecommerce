package com.enoca.requestDTo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

	@NotNull
	private Double totalPrice;

	@NotNull
	private Long customerId;
}

//createAt servis katmanında now() ile setleyeceğim