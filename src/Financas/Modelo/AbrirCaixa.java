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
public class AbrirCaixa {
  private int codigo ;
  private Date data;
  private String hora;
  private int caixa ;
  private double saldoCaixa ;
  private double valor_abertura ;
  private double total ;
  private String historico;
  private int utilizador;

    public AbrirCaixa() {
    }

    public AbrirCaixa(int codigo, Date data, String hora, int caixa, double saldoCaixa, double valor_abertura, double total, String historico, int utilizador) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.caixa = caixa;
        this.saldoCaixa = saldoCaixa;
        this.valor_abertura = valor_abertura;
        this.total = total;
        this.historico = historico;
        this.utilizador = utilizador;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public double getSaldoCaixa() {
        return saldoCaixa;
    }

    public void setSaldoCaixa(double saldoCaixa) {
        this.saldoCaixa = saldoCaixa;
    }

    public double getValor_abertura() {
        return valor_abertura;
    }

    public void setValor_abertura(double valor_abertura) {
        this.valor_abertura = valor_abertura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public int getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(int utilizador) {
        this.utilizador = utilizador;
    }
  
  
}
