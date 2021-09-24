package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

}
