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
public class FechoRecepcao {

    int idfechoConta;
    private double valorMedico, valorEcografia, valorLaboratorio, valorRaiox, valorInternamento, valorOutros;
    String data;
    double valorTotal;

    public FechoRecepcao() {
    }

    public FechoRecepcao(int idfechoConta, double valorMedico, double valorEcografia, double valorLaboratorio, String data, double valorTotal) {
        this.idfechoConta = idfechoConta;
        this.valorMedico = valorMedico;
        this.valorEcografia = valorEcografia;
        this.valorLaboratorio = valorLaboratorio;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public int getIdfechoConta() {
        return idfechoConta;
    }

    public void setIdfechoConta(int idfechoConta) {
        this.idfechoConta = idfechoConta;
    }

    public double getValorMedico() {
        return valorMedico;
    }

    public void setValorMedico(double valorMedico) {
        this.valorMedico = valorMedico;
    }

    public double getValorEcografia() {
        return valorEcografia;
    }

    public void setValorEcografia(double valorEcografia) {
        this.valorEcografia = valorEcografia;
    }

    public double getValorLaboratorio() {
        return valorLaboratorio;
    }

    public void setValorLaboratorio(double valorLaboratorio) {
        this.valorLaboratorio = valorLaboratorio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorRaiox() {
        return valorRaiox;
    }

    public void setValorRaiox(double valorRaiox) {
        this.valorRaiox = valorRaiox;
    }

    public double getValorInternamento() {
        return valorInternamento;
    }

    public void setValorInternamento(double valorInternamento) {
        this.valorInternamento = valorInternamento;
    }

    public double getValorOutros() {
        return valorOutros;
    }

    public void setValorOutros(double valorOutros) {
        this.valorOutros = valorOutros;
    }
    

}
