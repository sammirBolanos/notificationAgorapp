package com.agorapp.notificationagorapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "pqrs_procesada")
public class QPR {

    @Id
    @Column(name = "radicado", length = 28)
    private String radicado;

    @Column(name = "pqrs", nullable = false, columnDefinition = "TEXT")
    private String pqrs;

    @Column(name = "canal", length = 50)
    private String canal;

    @Column(name = "fecha_utc")
    private OffsetDateTime fechaUtc;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "secretaria", length = 150)
    private String secretaria;

    @Column(name = "titulo_ia", columnDefinition = "TEXT")
    private String tituloIa;

    @Column(name = "resumen_ia", columnDefinition = "TEXT")
    private String resumenIa;

    @Column(name = "clasificacion", length = 50)
    private String clasificacion;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "irresponsable")
    private Boolean irresponsable;

    @Column(name = "resultado")
    private Boolean resultado = false;

    @Column(name = "resuelta")
    private Boolean resuelta = false;

    @Column(name = "respuesta_sugerida", columnDefinition = "TEXT")
    private String respuestaSugerida;

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
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

    public Boolean getIrresponsable() {
        return irresponsable;
    }

    public void setIrresponsable(Boolean irresponsable) {
        this.irresponsable = irresponsable;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public Boolean getResuelta() {
        return resuelta;
    }

    public void setResuelta(Boolean resuelta) {
        this.resuelta = resuelta;
    }

    public String getRespuestaSugerida() {
        return respuestaSugerida;
    }

    public void setRespuestaSugerida(String respuestaSugerida) {
        this.respuestaSugerida = respuestaSugerida;
    }
}
