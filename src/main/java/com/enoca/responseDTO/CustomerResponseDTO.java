package com.enoca.responseDTO;

import java.util.List;
import com.enoca.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {


	private Long id;

	private String name;

	private Integer age;

	private List<Order> orderList;

}
