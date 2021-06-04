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
public class Caixa {
    
    
    private int codigo;
    private String descricao;
    private int tipo_caixa;// FINANCEIRO
    private String operacao ;//VALOR SIM OU NÃO
    private double saldo_atual;
    private Date data_abertura;
    private Date data_fecho;
    private String sitacao_atual;// ABERTO OU FECHADO
    private double saldo_inicial;
    private String numero_conta;
    private String agencia;
    private String gerente;
    private Date data_cadastro;

    public Caixa() {
    }

    public Caixa(int codigo, String descricao, int tipo_caixa, String operacao, double saldo_atual, Date data_abertura, Date data_fecho, String sitacao_atual, double saldo_inicial, String numero_conta, String agencia, String gerente,Date data_cadastro) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipo_caixa = tipo_caixa;
        this.operacao = operacao;
        this.saldo_atual = saldo_atual;
        this.data_abertura = data_abertura;
        this.data_fecho = data_fecho;
        this.sitacao_atual = sitacao_atual;
        this.saldo_inicial = saldo_inicial;
        this.numero_conta = numero_conta;
        this.agencia = agencia;
        this.gerente = gerente;
        this.data_cadastro = data_cadastro;
    }

    public Caixa(String descricao, int tipo_caixa, String operacao, double saldo_atual, Date data_abertura, Date data_fecho, String sitacao_atual, double saldo_inicial, String numero_conta, String agencia, String gerente,Date data_cadastro) {
        this.descricao = descricao;
        this.tipo_caixa = tipo_caixa;
        this.operacao = operacao;
        this.saldo_atual = saldo_atual;
        this.data_abertura = data_abertura;
        this.data_fecho = data_fecho;
        this.sitacao_atual = sitacao_atual;
        this.saldo_inicial = saldo_inicial;
        this.numero_conta = numero_conta;
        this.agencia = agencia;
        this.gerente = gerente;
        this.data_cadastro = data_cadastro;
    }

   
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo_caixa() {
        return tipo_caixa;
    }

    public void setTipo_caixa(int tipo_caixa) {
        this.tipo_caixa = tipo_caixa;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getSaldo_atual() {
        return saldo_atual;
    }

    public void setSaldo_atual(double saldo_atual) {
        this.saldo_atual = saldo_atual;
    }

    public Date getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(Date data_abertura) {
        this.data_abertura = data_abertura;
    }

    public Date getData_fecho() {
        return data_fecho;
    }

    public void setData_fecho(Date data_fecho) {
        this.data_fecho = data_fecho;
    }

    public String getSitacao_atual() {
        return sitacao_atual;
    }

    public void setSitacao_atual(String sitacao_atual) {
        this.sitacao_atual = sitacao_atual;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
    
    
    
}
