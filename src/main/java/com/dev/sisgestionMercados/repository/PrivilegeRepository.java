package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.UserS;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

	Privilege findByPrivilege(String privilege);
}
