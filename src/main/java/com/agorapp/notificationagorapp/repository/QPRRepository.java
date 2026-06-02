package com.agorapp.notificationagorapp.repository;

import com.agorapp.notificationagorapp.model.QPR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QPRRepository extends JpaRepository<QPR, String> {
    List<QPR> findBySecretariaAndResueltaFalse(String secretaria);
    List<QPR> findByResueltaTrue();
}
