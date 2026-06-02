package com.agorapp.notificationagorapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "secretarias_alcaldia")
public class Secretaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_dependencia", length = 100, nullable = false)
    private String nombreDependencia;

    @Column(name = "sector_administrativo", length = 100)
    private String sectorAdministrativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public String getSectorAdministrativo() {
        return sectorAdministrativo;
    }

    public void setSectorAdministrativo(String sectorAdministrativo) {
        this.sectorAdministrativo = sectorAdministrativo;
    }
}
