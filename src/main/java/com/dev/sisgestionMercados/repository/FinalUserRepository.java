package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.UserS;
@Repository
public interface FinalUserRepository extends JpaRepository<FinalUser,Long> {
	FinalUser findByUserName(String userName);
	FinalUser findByEmail(String email);
}
