package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.marque.MarqueVM;
import com.youcode.rentalhive.handler.response.ResponseMessage;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.service.marque.MarqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/marque")
public class MarqueController {
    private final MarqueService marqueService;
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMarqueDetail(@PathVariable Long id){
        MarqueVM marqueVM = MarqueVM.toVM(marqueService.getMarqueById(id));
        return ResponseMessage.ok(marqueVM, "Marque Récuperée avec Succée");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMarques() {
        List<Marque> marques = marqueService.getAllMarque();
        if(marques.isEmpty()){
            return ResponseMessage.notFound("Aucune marque trouvée");
        }else{
            return ResponseMessage.ok(marques.stream()
                    .map(MarqueVM::toVM)
                    .collect(Collectors.toList()),"Marques Récuperées avec Succée");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMarque(@RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return ResponseMessage.badRequest("Le terme de recherche ne peut pas être vide.");
        }
        List<Marque> marques = marqueService.searchMarque(searchTerm);
        if(marques.isEmpty()){
            return ResponseMessage.notFound("Aucune Marque trouvée pour le terme de recherche : " + searchTerm);
        }else{
            return ResponseMessage.ok(marques.stream()
                    .map(MarqueVM::toVM)
                    .collect(Collectors.toList()),"Marques Récuperées avec Succée");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createMarque(@Valid @RequestBody MarqueVM marqueVM){
        Marque marque = marqueVM.toEntite();
        Marque createdMarque = marqueService.createMarque(marque);
        return ResponseMessage.created(
                MarqueVM.toVM(createdMarque),
                "Marque Crée Avec Succée");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMarque(@PathVariable Long id, @Valid @RequestBody MarqueVM marqueVM) {
        Marque marque = marqueVM.toEntite();
        Marque updatedMarque = marqueService.updateMarque(marque, id);
        return ResponseMessage.ok(
                MarqueVM.toVM(updatedMarque),
                "Marque Modifée Avec Succée");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMarque(@PathVariable Long id) {
        marqueService.deleteMarque(id);
        return ResponseMessage.ok(null,"Marque Supprimée avec Succé");
    }
}
