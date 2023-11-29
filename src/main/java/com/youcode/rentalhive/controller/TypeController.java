/* package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.controller.vm.marque.MarqueVM;
import com.youcode.rentalhive.controller.vm.type.TypeVM;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import com.youcode.rentalhive.service.type.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/type")
public class TypeController {
    TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("{id}")
    public TypeVM getTypeDetail(@PathVariable Long id){
        return typeService.getTypeById(id)
                .map(TypeVM::toVM)
                .orElse(null);
    }

    @GetMapping("/")
    public List<TypeVM> getAllTypes() {
        List<Type> types = typeService.getAllType();
        return types.stream()
                .map(TypeVM::toVM)
                .collect(Collectors.toList());
    }

    @GetMapping("/search/{marqueName}")
    public List<TypeVM> searchTypeByMarque(@PathVariable String marqueName) {
        List<Type> types = typeService.searchType(marqueName);
        return types.stream()
                .map(TypeVM::toVM)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public TypeVM createType(@RequestBody TypeVM typeVM){
        Type type = typeVM.toEntite();
        Type createdType = typeService.createType(type);
        return TypeVM.toVM(createdType);
    }

    @PutMapping("{id}")
    public TypeVM updateType(@PathVariable Long id, @RequestBody TypeVM typeVM) {
        Type type = typeVM.toEntite();
        Type updatedType = typeService.updateType(type, id);
        return TypeVM.toVM(updatedType);
    }

    @DeleteMapping("{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }
}*/
