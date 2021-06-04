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
public class FormaPagamento {

    private long idFormagamento;
    private String designacao;
    private int codigoStatus;

    public FormaPagamento() {
    }

    public FormaPagamento(long idFormagamento, String designacao, int codigoStatus) {
        this.idFormagamento = idFormagamento;
        this.designacao = designacao;
        this.codigoStatus = codigoStatus;
    }

    public long getIdFormagamento() {
        return idFormagamento;
    }

    public void setIdFormagamento(long idFormagamento) {
        this.idFormagamento = idFormagamento;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }
    
}
