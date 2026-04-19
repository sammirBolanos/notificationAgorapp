package com.agorapp.notificationagorapp.qpr;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pqrs")
public class QPR {

    @Id
    @Column(name = "radicado")
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "pqrs", nullable = false)
    private String pqrs;

    @Column(nullable = false)
    private String canal;

    @Column(nullable = false)
    private String username;

    @Column(name = "fecha_utc", nullable = false)
    private LocalDateTime fechaUtc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getFechaUtc() {
        return fechaUtc;
    }

    public void setFechaUtc(LocalDateTime fechaUtc) {
        this.fechaUtc = fechaUtc;
    }
}
