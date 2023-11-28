package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

}
