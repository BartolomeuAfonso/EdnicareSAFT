/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Probook
 */
public class PedidoItensRaioX {

    private int id, codigoServico, codigoRaio;
    private int quantidade;
    private String designacao, diagnostico,estado;

    public PedidoItensRaioX() {
    }

    public PedidoItensRaioX(int id, int codigoServico, int codigoRaio, int quantidade) {
        this.id = id;
        this.codigoServico = codigoServico;
        this.codigoRaio = codigoRaio;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoRaio() {
        return codigoRaio;
    }

    public void setCodigoRaio(int codigoRaio) {
        this.codigoRaio = codigoRaio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
