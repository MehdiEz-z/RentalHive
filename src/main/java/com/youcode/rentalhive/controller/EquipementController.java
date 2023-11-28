package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.request.ReservationRequest;
import com.youcode.rentalhive.service.EquipementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipement")
public class EquipementController {
    private final EquipementService equipementService;

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createEquipement(@Valid @RequestBody Materiel materiel, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        materiel.setAvailableQuantity(materiel.getInitQuantity());
        Materiel savedMateriel =  equipementService.saveEquipement(materiel);
        return ResponseEntity.ok(savedMateriel);
    }

    @GetMapping("/list")
    public List<Materiel> getAllEquipment() {
        return equipementService.fetchEquipementList();
    }

    @GetMapping("/{equipementId}")
    public Optional<Materiel> getEquipmentById(@PathVariable Long equipementId) {
        return equipementService.getEquipementById(equipementId);
    }

    @GetMapping("/search/equipementAvailable")
    public List<Materiel> getEquipementAvailable() {
        return equipementService.getEquipementAvailable();
    }

    @GetMapping("/search/equipementByName")
    public List<Materiel> getEquipementByName(@RequestBody String equipementName) {
        return equipementService.getEquipementByName(equipementName);
    }

    @PutMapping("/{equipementId}")
    public Materiel updateEquipement(@RequestBody Materiel materiel, @PathVariable Long equipementId) {
        return equipementService.updateEquipement(materiel, equipementId);
    }

    @DeleteMapping("/{equipementId}")
    public void deleteEquipement(@PathVariable Long equipementId) {
        equipementService.deleteEquipementById(equipementId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveEquipement(@Valid @RequestBody ReservationRequest reservationRequest, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        boolean isReserved = equipementService.reserveEquipement(
                reservationRequest.getEquipementId(),
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate());
        if (isReserved) {
            return ResponseEntity.ok("Equipement reserved successfully");
        } else {
            return ResponseEntity.badRequest().body("Reservation failed. Equipement not available or invalid IDs provided.");
        }
    }
}
