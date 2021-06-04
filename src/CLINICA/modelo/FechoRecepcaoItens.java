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
public class FechoRecepcaoItens {

    int idfechoConta, codigoConta, codigoMedico;
    private double valorItens, valorTotal;
    private String descricao;

    public FechoRecepcaoItens() {
    }

    public FechoRecepcaoItens(int idfechoConta, int codigoConta, int codigoMedico, double valorItens, double valorTotal) {
        this.idfechoConta = idfechoConta;
        this.codigoConta = codigoConta;
        this.codigoMedico = codigoMedico;
        this.valorItens = valorItens;
        this.valorTotal = valorTotal;
    }

    public int getIdfechoConta() {
        return idfechoConta;
    }

    public void setIdfechoConta(int idfechoConta) {
        this.idfechoConta = idfechoConta;
    }

    public int getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(int codigoConta) {
        this.codigoConta = codigoConta;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public double getValorItens() {
        return valorItens;
    }

    public void setValorItens(double valorItens) {
        this.valorItens = valorItens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

}
