package com.dev.sisgestionMercados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.sisgestionMercados.entity.Category;
import com.dev.sisgestionMercados.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting, Integer> {

}
