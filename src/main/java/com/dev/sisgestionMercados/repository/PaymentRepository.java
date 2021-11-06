package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
