package com.agorapp.notificationagorapp.service;

import com.agorapp.notificationagorapp.model.QPR;
import com.agorapp.notificationagorapp.repository.QPRRepository;
import com.agorapp.notificationagorapp.dto.QPRResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QPRService {

    private final QPRRepository qprRepository;

    public QPRService(QPRRepository qprRepository) {
        this.qprRepository = qprRepository;
    }

    public List<QPRResponse> getAllQprs() {
        return qprRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<QPRResponse> getQprByRadicado(String radicado) {
        return qprRepository.findById(radicado)
                .map(this::convertToResponse);
    }

    public List<QPRResponse> getQprsBySecretaria(String secretaria) {
        return qprRepository.findBySecretariaAndResueltaFalse(secretaria).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<QPRResponse> getQprsResueltas() {
        return qprRepository.findByResueltaTrue().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public boolean updateSecretaria(String radicado, String secretaria) {
        Optional<QPR> qpr = qprRepository.findById(radicado);
        if (qpr.isPresent()) {
            qpr.get().setSecretaria(secretaria);
            qprRepository.save(qpr.get());
            return true;
        }
        return false;
    }

    public boolean resolverQpr(String radicado) {
        Optional<QPR> qpr = qprRepository.findById(radicado);
        if (qpr.isPresent()) {
            qpr.get().setResuelta(true);
            qprRepository.save(qpr.get());
            return true;
        }
        return false;
    }

    private QPRResponse convertToResponse(QPR qpr) {
        return new QPRResponse(
            qpr.getRadicado(),
            qpr.getPqrs(),
            qpr.getCanal(),
            qpr.getFechaUtc(),
            qpr.getUsername(),
            qpr.getNombre(),
            qpr.getSecretaria(),
            qpr.getTituloIa(),
            qpr.getResumenIa(),
            qpr.getClasificacion(),
            qpr.getFechaLimite(),
            qpr.getIrresponsable(),
            qpr.getResultado(),
            qpr.getResuelta(),
            qpr.getRespuestaSugerida()
        );
    }
}
