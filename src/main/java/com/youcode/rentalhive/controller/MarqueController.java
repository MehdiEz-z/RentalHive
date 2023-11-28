package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.marque.MarqueVM;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.service.marque.MarqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/marque")
public class MarqueController {
    MarqueService marqueService;
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping("{id}")
    public MarqueVM getMarqueDetail(@PathVariable Long id){
        return marqueService.getMarqueById(id)
                .map(MarqueVM::toVM)
                .orElse(null);
    }

    @GetMapping("/")
    public List<MarqueVM> getAllMarques() {
        List<Marque> marques = marqueService.getAllMarque();
        return marques.stream()
                .map(MarqueVM::toVM)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<MarqueVM> searchMarque(@RequestParam String searchTerm) {
        List<Marque> marques = marqueService.searchMarque(searchTerm);
        return marques.stream()
                .map(MarqueVM::toVM)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public MarqueVM createMarque(@RequestBody MarqueVM marqueVM){
        Marque marque = marqueVM.toEntite();
        Marque createdMarque = marqueService.createMarque(marque);
        return MarqueVM.toVM(createdMarque);
    }

    @PutMapping("{id}")
    public MarqueVM updateMarque(@PathVariable Long id, @RequestBody MarqueVM marqueVM) {
        Marque marque = marqueVM.toEntite();
        Marque updatedMarque = marqueService.updateMarque(marque, id);
        return MarqueVM.toVM(updatedMarque);
    }

    @DeleteMapping("{id}")
    public void deleteMarque(@PathVariable Long id) {
        marqueService.deleteMarque(id);
    }
}
