package com.youcode.rentalhive.controller.vm.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.entity.Marque;
import com.youcode.rentalhive.model.entity.Type;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TypeVM(
    @NotBlank(message = "Le Nom est Obligatoire")
    String type,
    @NotBlank(message = "La Marque est Obligatoire")
    String marque
) {
    public static TypeVM toVM(Type type ){
        return new TypeVM(
                type.getNomType(),
                type.getMarque().getNomMarque());
    }

    public Type toEntite(){
        return Type.builder()
                .nomType(this.type)
                .marque(Marque.builder()
                        .nomMarque(marque)
                        .build()
                )
                .build();
    }
}
