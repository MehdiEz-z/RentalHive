package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.type.TypeVM;
import com.youcode.rentalhive.handler.response.ResponseMessage;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.service.type.TypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/type")
public class TypeController {
    private final TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTypeDetail(@PathVariable Long id){
        TypeVM typeVM = TypeVM.toVM(typeService.getTypeById(id));
        return ResponseMessage.ok(typeVM, "Type Récuperé avec Succée");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTypes() {
        List<Type> types = typeService.getAllType();
        if(types.isEmpty()){
            return ResponseMessage.notFound("Aucun type trouvé");
        }else{
            return ResponseMessage.ok(types.stream()
                    .map(TypeVM::toVM)
                    .collect(Collectors.toList()),"Types Récuperés avec Succée");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchTypeByMarque(@RequestParam String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return ResponseMessage.badRequest("Le terme de recherche ne peut pas être vide.");
        }
        List<Type> types = typeService.searchType(searchTerm);
        if(types.isEmpty()){
            return ResponseMessage.notFound("Aucun Type trouvé pour le terme de recherche : " + searchTerm);
        }else{
            return ResponseMessage.ok(types.stream()
                    .map(TypeVM::toVM)
                    .collect(Collectors.toList()),"Types Récuperés avec Succée");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createType(@Valid @RequestBody TypeVM typeVM){
        Type type = typeVM.toEntite();
        Type createdType = typeService.createType(type);
        return ResponseMessage.created(
                TypeVM.toVM(createdType),
                "Type Crée Avec Succée");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateType(@PathVariable Long id,@Valid @RequestBody TypeVM typeVM) {
        Type type = typeVM.toEntite();
        Type updatedType = typeService.updateType(type, id);
        return ResponseMessage.ok(
                TypeVM.toVM(updatedType),
                "Type Modifié Avec Succée");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseMessage.ok(null,"Type Supprimé avec Succé");
    }
}
