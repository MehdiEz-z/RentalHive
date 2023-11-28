package com.youcode.rentalhive.repository;

import com.youcode.rentalhive.model.entity.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface Materielrepository extends JpaRepository<Materiel, Long> {

}
