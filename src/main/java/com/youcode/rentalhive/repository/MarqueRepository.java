package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
}
