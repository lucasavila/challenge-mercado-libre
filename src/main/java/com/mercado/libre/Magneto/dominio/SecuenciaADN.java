package com.mercado.libre.Magneto.dominio;

import javax.persistence.*;

@Entity
public class SecuenciaADN{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Boolean esMutante;

    private String [] dna;

    public SecuenciaADN(String[] dna, Boolean esMutante) {
        this.dna = dna;
        this.esMutante = esMutante;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEsMutante() {
        return esMutante;
    }

    public void setEsMutante(Boolean esMutante) {
        this.esMutante = esMutante;
    }
}