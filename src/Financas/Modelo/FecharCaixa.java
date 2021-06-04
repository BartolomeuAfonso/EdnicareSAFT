/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class FecharCaixa {
    
    private int codigo,caixa,utilizador;
    private Date data;
    private String hora,caixaTransferir,observacao;
    private double saldoCaixa,valorFecho,quebra;

    public FecharCaixa() {
    }

    public FecharCaixa(int codigo, int caixa, Date data, String hora, String caixaTransferir, String observacao, double saldoCaixa, double valorFecho, double quebra,int utilizador) {
        this.codigo = codigo;
        this.caixa = caixa;
        this.data = data;
        this.hora = hora;
        this.caixaTransferir = caixaTransferir;
        this.observacao = observacao;
        this.saldoCaixa = saldoCaixa;
        this.valorFecho = valorFecho;
        this.quebra = quebra;
        this.utilizador = utilizador;
    }

    public FecharCaixa(int caixa, Date data, String hora, String caixaTransferir, String observacao, double saldoCaixa, double valorFecho, double quebra) {
        this.caixa = caixa;
        this.data = data;
        this.hora = hora;
        this.caixaTransferir = caixaTransferir;
        this.observacao = observacao;
        this.saldoCaixa = saldoCaixa;
        this.valorFecho = valorFecho;
        this.quebra = quebra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCaixaTransferir() {
        return caixaTransferir;
    }

    public void setCaixaTransferir(String caixaTransferir) {
        this.caixaTransferir = caixaTransferir;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getSaldoCaixa() {
        return saldoCaixa;
    }

    public void setSaldoCaixa(double saldoCaixa) {
        this.saldoCaixa = saldoCaixa;
    }

    public double getValorFecho() {
        return valorFecho;
    }

    public void setValorFecho(double valorFecho) {
        this.valorFecho = valorFecho;
    }

    public double getQuebra() {
        return quebra;
    }

    public void setQuebra(double quebra) {
        this.quebra = quebra;
    }

    public int getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(int utilizador) {
        this.utilizador = utilizador;
    }
    
    
}
