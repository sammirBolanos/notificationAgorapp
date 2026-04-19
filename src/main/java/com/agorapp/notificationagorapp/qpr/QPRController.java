package com.agorapp.notificationagorapp.qpr;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private String normalizarTexto(String texto) {
        String textoSinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return textoSinTildes
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }
}
