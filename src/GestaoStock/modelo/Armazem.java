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
public class Armazem {
    
    private Long idArmazem;
    private String categoria;

    public Armazem() {
    }

    public Armazem(Long idArmazem, String categoria) {
        this.idArmazem = idArmazem;
        this.categoria = categoria;
    }

    public Armazem(String categoria) {
        this.categoria = categoria;
    }

    public Long getIdArmazem() {
        return idArmazem;
    }

    public void setIdArmazem(Long idArmazem) {
        this.idArmazem = idArmazem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idArmazem);
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
        final Armazem other = (Armazem) obj;
        if (!Objects.equals(this.idArmazem, other.idArmazem)) {
            return false;
        }
        return true;
    }
    
    
 
}
