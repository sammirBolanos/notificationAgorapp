package com.agorapp.notificationagorapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agorapp.notificationagorapp.dto.QPRResponse;
import com.agorapp.notificationagorapp.dto.SecretariaResponse;
import com.agorapp.notificationagorapp.dto.UpdateSecretariaRequest;
import com.agorapp.notificationagorapp.service.QPRService;
import com.agorapp.notificationagorapp.service.SecretariaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@RestController
@RequestMapping("/qprs")
@Tag(name = "PQRS", description = "Endpoints para consultar PQRS procesadas")
public class QPRController {

    private final QPRService qprService;
    private final SecretariaService secretariaService;

    public QPRController(QPRService qprService, SecretariaService secretariaService) {
        this.qprService = qprService;
        this.secretariaService = secretariaService;
    }

    @GetMapping("/por-secretaria")
    @Operation(
        summary = "Obtener PQRS pendientes por secretaría",
        description = "Obtiene todas las PQRS pendientes (no resueltas) asociadas a una secretaría específica."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Listado de PQRS pendientes de la secretaría",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class)
            )
        )
    })
    public List<QPRResponse> getQprsBySecretaria(
        @Parameter(
            name = "secretaria",
            description = "Nombre de la secretaría",
            required = true,
            example = "Educacion"
        )
        @RequestParam String secretaria
    ) {
        return qprService.getQprsBySecretaria(secretaria);
    }

    @GetMapping("/secretarias")
    @Operation(
        summary = "Obtener lista de secretarías",
        description = "Retorna la lista completa de secretarías disponibles."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Listado de secretarías obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class)
            )
        )
    })
    public List<SecretariaResponse> getSecretarias() {
        return secretariaService.getAllSecretarias();
    }

    @GetMapping("/resueltas")
    @Operation(
        summary = "Obtener PQRS resueltas",
        description = "Obtiene todas las PQRS que han sido resueltas (resuelta = true)."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Listado de PQRS resueltas obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class)
            )
        )
    })
    public List<QPRResponse> getQprsResueltas() {
        return qprService.getQprsResueltas();
    }

    @GetMapping
    @Operation(
        summary = "Listar todas las PQRS procesadas",
        description = "Obtiene todas las PQRS procesadas desde la base de datos sin paginación."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Listado de PQRS obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = List.class)
            )
        )
    })
    public List<QPRResponse> getAllQprs() {
        return qprService.getAllQprs();
    }

    @GetMapping("/{radicado}")
    @Operation(
        summary = "Obtener una PQRS por su radicado",
        description = "Recupera los detalles completos de una PQRS específica usando su radicado único. " +
                     "Devuelve 404 si el radicado no existe en la base de datos."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "PQRS encontrada - Contiene todos los detalles de la PQRS",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = QPRResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "PQRS no encontrada - El radicado especificado no existe en la base de datos"
        )
    })
    public ResponseEntity<QPRResponse> getQprByRadicado(
        @Parameter(
            name = "radicado",
            description = "Identificador único de la PQRS (código de 28 caracteres)",
            required = true,
            example = "20240601001"
        )
        @PathVariable String radicado
    ) {
        return qprService.getQprByRadicado(radicado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{radicado}/actualizar-secretaria")
    @Operation(
        summary = "Actualizar secretaría de una PQRS",
        description = "Reasigna una PQRS a una secretaría diferente."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Secretaría actualizada exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "PQRS no encontrada"
        )
    })
    public ResponseEntity<Void> actualizarSecretaria(
        @Parameter(
            name = "radicado",
            description = "Identificador único de la PQRS",
            required = true
        )
        @PathVariable String radicado,
        @RequestBody UpdateSecretariaRequest request
    ) {
        if (qprService.updateSecretaria(radicado, request.secretaria())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{radicado}/resolver")
    @Operation(
        summary = "Marcar PQRS como resuelta",
        description = "Marca una PQRS como resuelta (resuelta = true)."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "PQRS marcada como resuelta"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "PQRS no encontrada"
        )
    })
    public ResponseEntity<Void> resolverQpr(
        @Parameter(
            name = "radicado",
            description = "Identificador único de la PQRS",
            required = true
        )
        @PathVariable String radicado
    ) {
        if (qprService.resolverQpr(radicado)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
