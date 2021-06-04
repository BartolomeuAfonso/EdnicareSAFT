/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

import java.util.Objects;

/**
 *
 * @author El Router
 */
public class Unidade {
    
    private Long idUnidade;
    private String unidade;

    public Unidade() {
    }

    public Unidade(Long idCategoria, String categoria) {
        this.idUnidade = idCategoria;
        this.unidade = categoria;
    }

    public Unidade(String categoria) {
        this.unidade = categoria;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idUnidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unidade other = (Unidade) obj;
        if (!Objects.equals(this.idUnidade, other.idUnidade)) {
            return false;
        }
        return true;
    }
    
    
 
}
