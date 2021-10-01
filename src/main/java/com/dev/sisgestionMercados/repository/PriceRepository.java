package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer>{

}
