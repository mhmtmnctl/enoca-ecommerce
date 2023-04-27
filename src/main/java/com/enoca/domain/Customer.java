package com.enoca.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter//lomboktan kullandım. clean code için.
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name") // column koymasak da olur. kendim spesifik isim vermek için koydum
	private String name;

	@Column(name = "age")
	private Integer age;

	@OneToMany(mappedBy = "customer")
	private List<Order> orders;// orders tarafı many old. için list türünde yaptım.
}
