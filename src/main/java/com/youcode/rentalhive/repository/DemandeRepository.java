package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    @Query("SELECT r FROM Demande r " +
            "WHERE r.equipement.id = :equipementId " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate))")
    List<Demande> findConflictingReservations(
            @Param("equipementId") Long equipementId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}
