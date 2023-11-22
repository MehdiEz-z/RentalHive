package com.youcode.rentalhive.service;

import com.youcode.rentalhive.entity.Equipement;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface EquipementService {
    Equipement saveEquipement(Equipement equipment);

    Optional<Equipement> getEquipementById(Long equipmentId);

    List<Equipement> getEquipementAvailable();

    List<Equipement> getEquipementByName(String equipmentName);

    List<Equipement> fetchEquipementList();

    Equipement updateEquipement(Equipement equipment, Long equipmentId);

    void deleteEquipementById(Long equipmentId);

    boolean reserveEquipement(Long equipmentId,Date startDate, Date endDate);

    void reserveAndSave(Equipement equipment, Date startDate, Date endDate);
}
