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
public class Movimentacoes {

    private int codigo;
    private Date date;
    private int tipo_movimento;
    private int conta;
    private int caixa;
    private String titulo;
    private String descricao;
    private String destinatario;
    private double valor;
    private double saldo;
    private double saldo_anterior;

    public Movimentacoes() {
    }

    public Movimentacoes(int codigo, Date date, int tipo_movimento, int conta, int caixa, String titulo, String descricao, String destinatario, double valor, double saldo,double saldo_anterior) {
        this.codigo = codigo;
        this.date = date;
        this.tipo_movimento = tipo_movimento;
        this.conta = conta;
        this.caixa = caixa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.destinatario = destinatario;
        this.valor = valor;
        this.saldo = saldo;
        this.saldo_anterior = saldo_anterior;
    }

    public Movimentacoes(Date date, int tipo_movimento, int conta, int caixa, String titulo, String descricao, String destinatario, double valor, double saldo,double saldo_anterior) {
        this.date = date;
        this.tipo_movimento = tipo_movimento;
        this.conta = conta;
        this.caixa = caixa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.destinatario = destinatario;
        this.valor = valor;
        this.saldo = saldo;
        this.saldo_anterior = saldo_anterior;
    }
    public Movimentacoes(int codigo,Date date, String titulo,int conta,int tipo_movimento,String descricao,double valor,double saldo_anterior,double saldo) {
        this.date = date;
        this.codigo = codigo;
        this.tipo_movimento = tipo_movimento;
        this.conta = conta;
        this.titulo = titulo;
        this.valor = valor;
        this.saldo = saldo;
        this.saldo_anterior = saldo_anterior;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTipo_movimento() {
        return tipo_movimento;
    }

    public void setTipo_movimento(int tipo_movimento) {
        this.tipo_movimento = tipo_movimento;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(double saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }

    
}
