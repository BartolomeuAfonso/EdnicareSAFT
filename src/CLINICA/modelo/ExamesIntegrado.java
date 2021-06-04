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
public class ExamesIntegrado {

    private long idExamesIntegrado;
    private String designacao, unidade, referencia, resultado;
    private int codigoStatus;

    public ExamesIntegrado() {
    }

    public ExamesIntegrado(long idExamesIntegrado, String designacao, String unidade, String referencia, String resultado, int codigoStatus) {
        this.idExamesIntegrado = idExamesIntegrado;
        this.designacao = designacao;
        this.unidade = unidade;
        this.referencia = referencia;
        this.resultado = resultado;
        this.codigoStatus = codigoStatus;
    }

    public long getIdExamesIntegrado() {
        return idExamesIntegrado;
    }

    public void setIdExamesIntegrado(long idExamesIntegrado) {
        this.idExamesIntegrado = idExamesIntegrado;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
