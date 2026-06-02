package com.agorapp.notificationagorapp.dto;

import java.time.OffsetDateTime;
import java.time.LocalDate;

public record QPRResponse(
    String radicado,
    String pqrs,
    String canal,
    OffsetDateTime fechaUtc,
    String username,
    String nombre,
    String secretaria,
    String tituloIa,
    String resumenIa,
    String clasificacion,
    LocalDate fechaLimite,
    Boolean irresponsable,
    Boolean resultado,
    Boolean resuelta,
    String respuestaSugerida
) {}
