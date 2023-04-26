package com.enoca.responseDTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

	private Long id;

	private LocalDate createDate;

	private Double totalPrice;

	private String customerName;// ben isim dönmeyi tercih ettim. id de dönüleebilir.

}
