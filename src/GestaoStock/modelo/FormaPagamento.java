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
public class FormaPagamento {
    
    private Long codForma;
    private String descicao;

    public FormaPagamento() {
    }

    public FormaPagamento(Long idCategoria, String categoria) {
        this.codForma = idCategoria;
        this.descicao = categoria;
    }

    public FormaPagamento(String categoria) {
        this.descicao = categoria;
    }

    public Long getCodForma() {
        return codForma;
    }

    public void setCodForma(Long codForma) {
        this.codForma = codForma;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codForma);
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
        final FormaPagamento other = (FormaPagamento) obj;
        if (!Objects.equals(this.codForma, other.codForma)) {
            return false;
        }
        return true;
    }
    
    
 
}
