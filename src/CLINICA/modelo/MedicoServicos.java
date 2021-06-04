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
public class MedicoServicos {

    private int idMedicoServico, codigoMedico, codigoServico, codigoMedicoServico;
    private String designacao;
    private double preco, preconormal, precopercentual;

    public MedicoServicos() {
    }

    public MedicoServicos(int codigoMedico, int codigoServico, double preconormal, double precopercentual) {
        this.codigoMedico = codigoMedico;
        this.codigoServico = codigoServico;
        this.preconormal = preconormal;
        this.precopercentual = precopercentual;
    }
    

    public int getIdMedicoServico() {
        return idMedicoServico;
    }

    public void setIdMedicoServico(int idMedicoServico) {
        this.idMedicoServico = idMedicoServico;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoMedicoServico() {
        return codigoMedicoServico;
    }

    public void setCodigoMedicoServico(int codigoMedicoServico) {
        this.codigoMedicoServico = codigoMedicoServico;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreconormal() {
        return preconormal;
    }

    public void setPreconormal(double preconormal) {
        this.preconormal = preconormal;
    }

    public double getPrecopercentual() {
        return precopercentual;
    }

    public void setPrecopercentual(double precopercentual) {
        this.precopercentual = precopercentual;
    }

}
