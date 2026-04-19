package com.agorapp.notificationagorapp.qpr;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pqrs_procesada")
public class QPR {

    @Id
    @Column(name = "radicado")
    private String id;

    @Column(name = "pqrs", nullable = false)
    private String pqrs;

    @Column(name = "canal")
    private String canal;

    @Column(name = "fecha_utc")
    private OffsetDateTime fechaUtc;

    @Column(name = "username")
    private String username;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "secretaria")
    private String secretaria;

    @Column(name = "titulo_ia")
    private String tituloIa;

    @Column(name = "resumen_ia")
    private String resumenIa;

    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "irrespetuosa")
    private Boolean irrespetuosa;

    @Column(name = "resuelta")
    private Boolean resuelta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPqrs() {
        return pqrs;
    }

    public void setPqrs(String pqrs) {
        this.pqrs = pqrs;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public OffsetDateTime getFechaUtc() {
        return fechaUtc;
    }

    public void setFechaUtc(OffsetDateTime fechaUtc) {
        this.fechaUtc = fechaUtc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public String getTituloIa() {
        return tituloIa;
    }

    public void setTituloIa(String tituloIa) {
        this.tituloIa = tituloIa;
    }

    public String getResumenIa() {
        return resumenIa;
    }

    public void setResumenIa(String resumenIa) {
        this.resumenIa = resumenIa;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Boolean getIrrespetuosa() {
        return irrespetuosa;
    }

    public void setIrrespetuosa(Boolean irrespetuosa) {
        this.irrespetuosa = irrespetuosa;
    }

    public Boolean getResuelta() {
        return resuelta;
    }

    public void setResuelta(Boolean resuelta) {
        this.resuelta = resuelta;
    }
}
