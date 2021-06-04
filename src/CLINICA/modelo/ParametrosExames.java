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
public class ParametrosExames {
    private long idParamentoExames;
    private String designacao, unidade, referencia;
    private int codigoServico, codigoStatus, codigoExameIntegrado;

    public ParametrosExames() {
    }

    public ParametrosExames(long idParamentoExames, String designacao, String unidade, String referencia, int codigoServico, int codigoStatus, int codigoExameIntegrado) {
        this.idParamentoExames = idParamentoExames;
        this.designacao = designacao;
        this.unidade = unidade;
        this.referencia = referencia;
        this.codigoServico = codigoServico;
        this.codigoStatus = codigoStatus;
        this.codigoExameIntegrado = codigoExameIntegrado;
    }

    public long getIdParamentoExames() {
        return idParamentoExames;
    }

    public void setIdParamentoExames(long idParamentoExames) {
        this.idParamentoExames = idParamentoExames;
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

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public int getCodigoExameIntegrado() {
        return codigoExameIntegrado;
    }

    public void setCodigoExameIntegrado(int codigoExameIntegrado) {
        this.codigoExameIntegrado = codigoExameIntegrado;
    }
    
    
    
}
