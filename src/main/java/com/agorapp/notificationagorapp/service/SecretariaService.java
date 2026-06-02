package com.agorapp.notificationagorapp.service;

import com.agorapp.notificationagorapp.model.Secretaria;
import com.agorapp.notificationagorapp.repository.SecretariaRepository;
import com.agorapp.notificationagorapp.dto.SecretariaResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretariaService {

    private final SecretariaRepository secretariaRepository;

    public SecretariaService(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    public List<SecretariaResponse> getAllSecretarias() {
        return secretariaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private SecretariaResponse convertToResponse(Secretaria secretaria) {
        return new SecretariaResponse(
            secretaria.getId(),
            secretaria.getNombreDependencia(),
            secretaria.getSectorAdministrativo()
        );
    }
}
