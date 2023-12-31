package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.materiel.request.MaterielRequestVM;
import com.youcode.rentalhive.controller.vm.materiel.response.MaterielResponseVM;
import com.youcode.rentalhive.handler.response.ResponseMessage;
import com.youcode.rentalhive.model.entity.Materiel;
import com.youcode.rentalhive.service.materiel.MaterielService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materiel")
public class MaterielController {
    private final MaterielService materielService;

    public MaterielController(MaterielService materielService) {
        this.materielService = materielService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMaterielDetail(@PathVariable Long id){
        MaterielResponseVM materielResponseVM = MaterielResponseVM.toVM(materielService.getMaterielById(id));
        return ResponseMessage.ok(materielResponseVM, "Materiel Récuperé avec Succée");
    }

    @GetMapping("/byTypeAndMark")
    public ResponseEntity<?> getMaterielByTypeAndMark(
            @RequestParam String type,
            @RequestParam String mark) {
            MaterielResponseVM materielResponseVM = MaterielResponseVM.toVM(materielService.getMaterielByDisponibilite(type,mark));
        return ResponseMessage.ok(materielResponseVM,"Materiel Récuperé avec Succée");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMateriel() {
        List<Materiel> materiels = materielService.getAllMateriel();
        if(materiels.isEmpty()){
            return ResponseMessage.notFound("Aucun materiel disponible actuellement");
        }else{
            return ResponseMessage.ok(materiels.stream()
                    .map(MaterielResponseVM::toVM)
                    .collect(Collectors.toList()),"Materiels Récuperés avec Succée");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMateriel(@RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return ResponseMessage.badRequest("Le terme de recherche ne peut pas être vide.");
        }
        List<Materiel> materiels = materielService.searchMateriel(searchTerm);
        if(materiels.isEmpty()){
            return ResponseMessage.notFound("Aucun materiel trouvé pour le terme de recherche : " + searchTerm);
        }else{
            return ResponseMessage.ok(materiels.stream()
                    .map(MaterielResponseVM::toVM)
                    .collect(Collectors.toList()),"Materiels Récuperés avec Succée");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createMateriel(@Valid @RequestBody MaterielRequestVM materielRequestVM){
        Materiel materiel = materielRequestVM.toEntite();
        Materiel createdMateriel = materielService.createMateriel(materiel);
        return ResponseMessage.created(
                MaterielResponseVM.toVM(createdMateriel),
                "Materiel Crée Avec Succée");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMateriel(@PathVariable Long id,@Valid @RequestBody MaterielRequestVM materielRequestVM) {
        Materiel materiel = materielRequestVM.toUpdateEntite();
        Materiel updateMateriel = materielService.updateMateriel(materiel, id);
        return ResponseMessage.ok(
                MaterielResponseVM.toVM(updateMateriel),
                "Materiel Modifié Avec Succée");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMateriel(@PathVariable Long id) {
        materielService.deleteMateriel(id);
        return ResponseMessage.ok(null,"Materiel Supprimé avec Succé");
    }
}
