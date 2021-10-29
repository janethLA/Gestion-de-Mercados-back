package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.UserS;

@Repository
public interface UserRepository extends JpaRepository<UserS, Integer> {
	UserS findByUserName(String userName);
	UserS findByEmail(String email);
}
 