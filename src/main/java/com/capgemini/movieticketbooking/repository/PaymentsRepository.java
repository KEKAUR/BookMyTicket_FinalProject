package com.capgemini.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.movieticketbooking.model.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer>{
	
	@Query("SELECT p FROM Payments p JOIN p.user u WHERE u.userId = :userId")
	List<Payments> findByUserId(int userId);

	List<Payments> findByPaymentStatus(String paymentStatus);

	//List<Payments> findByPaymentDate(int year, int month, int date);
	
	@Query(value = "SELECT * FROM payments WHERE DATE(payment_date) = DATE(:paymentDate)", nativeQuery = true)
	List<Payments> findByPaymentDate(String paymentDate);

}
