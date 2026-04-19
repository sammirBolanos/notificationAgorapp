package com.agorapp.notificationagorapp.qpr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<QPR> getQprById(@PathVariable Long id) {
        return qprRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
