package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
    @RestResource(path = "/equipmentsAvailable")
    public List<Equipement> findByAvailableIsTrue();

    @RestResource(path = "/equipmentsByName")
    public List<Equipement> findByNameContains(@Param("name") String name);


}
