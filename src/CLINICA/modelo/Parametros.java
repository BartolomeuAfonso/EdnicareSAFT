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
public class Parametros {
    private long idParamento;
    private String designacao, sigla, valor;

    public Parametros() {
    }

    public Parametros(long idParamento, String designacao, String sigla, String valor) {
        this.idParamento = idParamento;
        this.designacao = designacao;
        this.sigla = sigla;
        this.valor = valor;
    }

    public long getIdParamento() {
        return idParamento;
    }

    public void setIdParamento(long idParamento) {
        this.idParamento = idParamento;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
