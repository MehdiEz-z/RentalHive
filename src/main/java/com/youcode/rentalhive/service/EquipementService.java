package com.youcode.rentalhive.service;

import com.youcode.rentalhive.model.entity.Materiel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface EquipementService {
    Materiel saveEquipement(Materiel equipment);

    Optional<Materiel> getEquipementById(Long equipmentId);

    List<Materiel> getEquipementAvailable();

    List<Materiel> getEquipementByName(String equipmentName);

    List<Materiel> fetchEquipementList();

    Materiel updateEquipement(Materiel equipment, Long equipmentId);

    void deleteEquipementById(Long equipmentId);

    boolean reserveEquipement(Long equipmentId,Date startDate, Date endDate);

    void reserveAndSave(Materiel equipment, Date startDate, Date endDate);
}
