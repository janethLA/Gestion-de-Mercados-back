package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.FinalUser;
@Repository
public interface FinalUserRepository extends JpaRepository<FinalUser,Long> {
 
	
}
