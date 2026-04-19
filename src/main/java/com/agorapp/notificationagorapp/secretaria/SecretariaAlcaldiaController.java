package com.agorapp.notificationagorapp.secretaria;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secretarias")
public class SecretariaAlcaldiaController {

    private final SecretariaAlcaldiaRepository secretariaAlcaldiaRepository;

    public SecretariaAlcaldiaController(SecretariaAlcaldiaRepository secretariaAlcaldiaRepository) {
        this.secretariaAlcaldiaRepository = secretariaAlcaldiaRepository;
    }

    @GetMapping
    public List<SecretariaAlcaldia> getAllSecretarias() {
        return secretariaAlcaldiaRepository.findAll();
    }
}
