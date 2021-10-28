package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.OrderAssigned;

@Repository
public interface OrderAssignedRepository extends JpaRepository<OrderAssigned,Integer> {

}
