package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.service.materiel.MaterielService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materiel")
public class MaterielController {
    private final MaterielService materielService;

    public MaterielController(MaterielService materielService) {
        this.materielService = materielService;
    }
}
