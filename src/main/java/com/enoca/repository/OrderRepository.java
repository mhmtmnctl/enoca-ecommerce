package com.enoca.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enoca.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
	@Query("SELECT o FROM Order o WHERE o.createDate >= :date")
	List<Order> getOrdersWithDate(@Param("date") LocalDate date);

}
