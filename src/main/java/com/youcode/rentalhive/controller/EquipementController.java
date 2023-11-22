package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.entity.Equipement;
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
    public ResponseEntity<Object> createEquipement(@Valid @RequestBody Equipement equipement, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        equipement.setAvailableQuantity(equipement.getInitQuantity());
        Equipement savedEquipement =  equipementService.saveEquipement(equipement);
        return ResponseEntity.ok(savedEquipement);
    }

    @GetMapping("/list")
    public List<Equipement> getAllEquipment() {
        return equipementService.fetchEquipementList();
    }

    @GetMapping("/{equipementId}")
    public Optional<Equipement> getEquipmentById(@PathVariable Long equipementId) {
        return equipementService.getEquipementById(equipementId);
    }

    @GetMapping("/search/equipementAvailable")
    public List<Equipement> getEquipementAvailable() {
        return equipementService.getEquipementAvailable();
    }

    @GetMapping("/search/equipementByName")
    public List<Equipement> getEquipementByName(@RequestBody String equipementName) {
        return equipementService.getEquipementByName(equipementName);
    }

    @PutMapping("/{equipementId}")
    public Equipement updateEquipement(@RequestBody Equipement equipement, @PathVariable Long equipementId) {
        return equipementService.updateEquipement(equipement, equipementId);
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
