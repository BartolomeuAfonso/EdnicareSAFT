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
public class Recibos {
    
    private int codigo;
    private String titulo;
    private Date data;
    private double valor;
    private String conta;
    private String caixa;
    private String historico1;
    private String historico2;
    private String historico3;
    private String hora;
    private String tipo;//Recebimento ou Pagamento

    public Recibos() {
    }

    public Recibos(int codigo, String titulo, Date data, double valor, String conta, String caixa, String historico1, String historico2, String historico3, String tipo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.data = data;
        this.valor = valor;
        this.conta = conta;
        this.caixa = caixa;
        this.historico1 = historico1;
        this.historico2 = historico2;
        this.historico3 = historico3;
        this.tipo = tipo;
    }

    public Recibos(int codigo, String titulo, Date data, double valor, String historico1, String hora) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.data = data;
        this.valor = valor;
        this.historico1 = historico1;
        this.hora = hora;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getHistorico1() {
        return historico1;
    }

    public void setHistorico1(String historico1) {
        this.historico1 = historico1;
    }

    public String getHistorico2() {
        return historico2;
    }

    public void setHistorico2(String historico2) {
        this.historico2 = historico2;
    }

    public String getHistorico3() {
        return historico3;
    }

    public void setHistorico3(String historico3) {
        this.historico3 = historico3;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
    
}
