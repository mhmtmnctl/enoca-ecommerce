package com.enoca.requestDTo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

	@NotNull//boş geçilmesin
	private String name;
	
	@NotNull
	@Max(100) //belirli bir yaş aralığını almak için
	@Min(18)
	private Integer age;

	
}
