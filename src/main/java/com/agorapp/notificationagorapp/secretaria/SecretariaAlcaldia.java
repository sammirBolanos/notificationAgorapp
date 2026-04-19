package com.agorapp.notificationagorapp.secretaria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "secretarias_alcaldia")
public class SecretariaAlcaldia {

    @Id
    @Column(name = "id_secretaria")
    private Integer idSecretaria;

    @Column(name = "nombre_dependencia", nullable = false)
    private String nombreDependencia;

    @Column(name = "sector_administrativo", nullable = false)
    private String sectorAdministrativo;

    public Integer getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Integer idSecretaria) {
        this.idSecretaria = idSecretaria;
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
