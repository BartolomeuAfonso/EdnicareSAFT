/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Desenvolvimento
 */
public class Parente {
    private long idParente;
    private String designacao;

    public Parente() {
    }

    public Parente(long idParente, String designacao) {
        this.idParente = idParente;
        this.designacao = designacao;
    }

    public long getIdParente() {
        return idParente;
    }

    public void setIdParente(long idParente) {
        this.idParente = idParente;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    
    
}
