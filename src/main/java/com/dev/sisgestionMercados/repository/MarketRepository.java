package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Market;

public interface MarketRepository  extends JpaRepository<Market, Integer> {

}
