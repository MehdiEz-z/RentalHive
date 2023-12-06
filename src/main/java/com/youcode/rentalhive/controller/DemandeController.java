package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.demande.request.DemandeRequestVM;
import com.youcode.rentalhive.controller.vm.demande.response.DemandeResponseVM;
import com.youcode.rentalhive.handler.response.ResponseMessage;
import com.youcode.rentalhive.model.entity.Demande;
import com.youcode.rentalhive.service.demande.DemandeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demande")
public class DemandeController {
    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createDemande(@Valid @RequestBody DemandeRequestVM demandeRequestVM){
        Demande demande  = demandeRequestVM.toEntite();
        Demande createDemande = demandeService.createDemande(demande);
        return ResponseMessage.created(
                DemandeResponseVM.toVM(createDemande),
                "Demande Crée Avec Succée");
    }
}
