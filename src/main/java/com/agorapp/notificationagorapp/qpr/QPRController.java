package com.agorapp.notificationagorapp.qpr;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qprs")
public class QPRController {

    private final QPRRepository qprRepository;

    public QPRController(QPRRepository qprRepository) {
        this.qprRepository = qprRepository;
    }

    @GetMapping
    public List<QPR> getAllQprs() {
        return qprRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QPR> getQprById(@PathVariable String id) {
        return qprRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-secretaria")
    public ResponseEntity<List<QPR>> getQprsBySecretaria(@RequestParam("secretaria") String secretaria) {
        String secretariaNormalizada = normalizarTexto(secretaria);
        if (secretariaNormalizada.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(qprRepository.findBySecretariaNormalizada(secretariaNormalizada));
    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<QPR> resolverQpr(@PathVariable String id) {
        return qprRepository.findById(id)
                .map(qpr -> {
                    qpr.setResuelta(true);
                    return ResponseEntity.ok(qprRepository.save(qpr));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/secretaria")
    @Transactional
    public ResponseEntity<QPR> actualizarSecretaria(
            @PathVariable("id") String radicado,
            @RequestBody ActualizarSoloSecretariaRequest request) {

        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        String secretariaLimpia = request.secretaria() == null ? "" : request.secretaria().trim().replaceAll("\\s+", " ");
        if (secretariaLimpia.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        int filasActualizadas = qprRepository.actualizarSecretariaPorRadicado(radicado, secretariaLimpia);
        if (filasActualizadas == 0) {
            return ResponseEntity.notFound().build();
        }

        return qprRepository.findById(radicado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/secretaria", consumes = "application/json")
    @Transactional
    public ResponseEntity<QPR> actualizarSecretariaConJson(@RequestBody ActualizarSecretariaRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        String radicado = request.radicado() == null ? "" : request.radicado().trim();
        String secretariaLimpia = request.secretaria() == null ? "" : request.secretaria().trim().replaceAll("\\s+", " ");
        if (radicado.isBlank() || secretariaLimpia.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        int filasActualizadas = qprRepository.actualizarSecretariaPorRadicado(radicado, secretariaLimpia);
        if (filasActualizadas == 0) {
            return ResponseEntity.notFound().build();
        }

        return qprRepository.findById(radicado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/actualizar-secretaria", consumes = "application/json")
    @Transactional
    public ResponseEntity<QPR> actualizarSecretariaConJsonSimple(@RequestBody ActualizarSecretariaRequest request) {
        return actualizarSecretariaConJson(request);
    }

    public record ActualizarSecretariaRequest(String radicado, String secretaria) {
    }

    public record ActualizarSoloSecretariaRequest(String secretaria) {
    }

    private String normalizarTexto(String texto) {
        String textoSinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return textoSinTildes
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }
}
