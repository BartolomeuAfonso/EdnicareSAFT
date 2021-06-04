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
public class Acesso {
    
    private Long codAcesso;
    private String descricaoAcesso;

    public Acesso() {
    }

    public Acesso(Long idCategoria, String categoria) {
        this.codAcesso = idCategoria;
        this.descricaoAcesso = categoria;
    }

    public Acesso(String categoria) {
        this.descricaoAcesso = categoria;
    }

    public Long getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(Long codAcesso) {
        this.codAcesso = codAcesso;
    }

    public String getAcesso() {
        return descricaoAcesso;
    }

    public void setDescricaoAcesso(String descricaoAcesso) {
        this.descricaoAcesso = descricaoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codAcesso);
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
        final Acesso other = (Acesso) obj;
        if (!Objects.equals(this.codAcesso, other.codAcesso)) {
            return false;
        }
        return true;
    }
    
    
 
}
