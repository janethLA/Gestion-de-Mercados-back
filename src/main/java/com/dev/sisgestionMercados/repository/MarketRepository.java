package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Warehouse;

public interface MarketRepository  extends JpaRepository<Warehouse, Integer> {

}
