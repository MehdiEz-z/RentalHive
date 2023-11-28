package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Materielrepository extends JpaRepository<Materiel, Long> {

}
