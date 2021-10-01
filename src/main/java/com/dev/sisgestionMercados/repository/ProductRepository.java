package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
