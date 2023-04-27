package com.enoca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enoca.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c WHERE :q IS NULL OR c.name LIKE %:q%")
	Page<Customer> searchAllProducts(@Param("q") String query, Pageable pageable);

	
	/*
	  "LEFT JOIN" kullanmamızın sebebi, müşterilerin herhangi bir siparişi olup
	  olmadığını kontrol etmek istediğimiz için siparişi olmayan müşterileri de
	  dahil etmek istiyoruz. "LEFT JOIN", sol tablodaki tüm kayıtları ve sağ
	  tablodaki eşleşen kayıtları getirir ve sağ tablodaki eşleşmeyen kayıtlar için
	  NULL değerleri döndürür. Bu nedenle, müşterilerin herhangi bir siparişi
	  olmayanları da dahil ederek bir listeleme yapmak için "LEFT JOIN" kullanmamız
	  gerekiyor. From Customer dediğim için customer sol tarafta kalmış oluyor
	 */
	@Query("SELECT c FROM Customer c LEFT JOIN Order o ON o.customer = c WHERE o.id IS NULL")
	List<Customer> getCustomerNoOrder();

}
