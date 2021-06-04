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
public class TipoFaturacao {
    
    private Long idTipoFatura;
    private String tipoFatura;

    public TipoFaturacao() {
    }

    public TipoFaturacao(Long idCategoria, String categoria) {
        this.idTipoFatura = idCategoria;
        this.tipoFatura = categoria;
    }

    public TipoFaturacao(String categoria) {
        this.tipoFatura = categoria;
    }

    public Long getIdTipoFatura() {
        return idTipoFatura;
    }

    public void setIdTipoFatura(Long idTipoFatura) {
        this.idTipoFatura = idTipoFatura;
    }

    public String getTipoFatura() {
        return tipoFatura;
    }

    public void setTipoFatura(String tipoFatura) {
        this.tipoFatura = tipoFatura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idTipoFatura);
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
        final TipoFaturacao other = (TipoFaturacao) obj;
        if (!Objects.equals(this.idTipoFatura, other.idTipoFatura)) {
            return false;
        }
        return true;
    }
    
    
 
}
