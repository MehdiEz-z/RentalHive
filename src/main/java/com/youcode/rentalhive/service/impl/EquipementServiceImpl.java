package com.youcode.rentalhive.service.impl;

import com.youcode.rentalhive.entity.Demande;
import com.youcode.rentalhive.entity.Equipement;
import com.youcode.rentalhive.repository.DemandeRepository;
import com.youcode.rentalhive.repository.EquipementRepository;
import com.youcode.rentalhive.service.EquipementService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class EquipementServiceImpl implements EquipementService {
    private final EquipementRepository equipementRepository;
    private final DemandeRepository demandeRepository;

    public EquipementServiceImpl(EquipementRepository equipementRepository, DemandeRepository demandeRepository) {
        this.equipementRepository = equipementRepository;
        this.demandeRepository = demandeRepository;
    }

    @Override
    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public Optional<Equipement> getEquipementById(Long equipementId) {
        return equipementRepository.findById(equipementId);
    }

    @Override
    public List<Equipement> getEquipementAvailable() {
        return equipementRepository.findByAvailableIsTrue();
    }

    @Override
    public List<Equipement> getEquipementByName(String equipementName) {
        return equipementRepository.findByNameContains(equipementName);
    }

    @Override
    public List<Equipement> fetchEquipementList() {
        return (List<Equipement>) equipementRepository.findAll();
    }

    @Override
    public Equipement updateEquipement(Equipement equipement, Long equipementId) {
        Equipement existingEquipment = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        // Update the existing equipment with the new details
        //quantity of new equipment sock
        int newQuantityStock = equipement.getInitQuantity()-existingEquipment.getInitQuantity();
        existingEquipment.setName(equipement.getName());
        existingEquipment.setInitQuantity(equipement.getInitQuantity());
        existingEquipment.setAvailableQuantity(existingEquipment.getAvailableQuantity()+newQuantityStock);
        existingEquipment.setDailyRentalCost(equipement.getDailyRentalCost());

        return equipementRepository.save(existingEquipment);
    }

    @Override
    public void deleteEquipementById(Long equipementId) {
        equipementRepository.deleteById(equipementId);
    }

    @Override
    public boolean reserveEquipement(Long equipementId, @Valid Date startDate, @Valid Date endDate) {
        Equipement equipment = equipementRepository.findById(equipementId).orElse(null);

        if (equipment != null) {
            if(equipment.getAvailableQuantity() > 0){
                reserveAndSave(equipment,startDate,endDate);
                return true;
            }else{
                List<Demande> conflictingReservations = demandeRepository
                        .findConflictingReservations(equipementId, startDate, endDate);

                if (conflictingReservations.isEmpty()) {

                    reserveAndSave(equipment,startDate,endDate);

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void reserveAndSave(Equipement equipment, Date startDate, Date endDate) {
        Demande newRental = Demande.builder()
                .equipement(equipment)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        equipment.setAvailableQuantity(equipment.getAvailableQuantity() - 1);
        equipementRepository.save(equipment);

        demandeRepository.save(newRental);
    }
}
