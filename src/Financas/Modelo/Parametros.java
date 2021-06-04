/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Modelo;

/**
 *
 * @author Eletronicos
 */
public class Parametros {

    private int codigo;
    private double valor;
    private String parametro;
    private String descricao;

    public Parametros() {
    }

    public Parametros(int codigo, double valor, String parametro, String descricao) {
        this.codigo = codigo;
        this.valor = valor;
        this.parametro = parametro;
        this.descricao = descricao;
    }
    public Parametros(String parametro, String descricao,double valor ) {
        this.valor = valor;
        this.parametro = parametro;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
