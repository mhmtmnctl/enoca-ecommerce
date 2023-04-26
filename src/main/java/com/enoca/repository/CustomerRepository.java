package com.enoca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enoca.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	  @Query("SELECT c FROM Customer c WHERE :q IS NULL OR c.name LIKE %:q%" )
	 Page<Customer> searchAllProducts(@Param("q") String query, Pageable pageable);

}
