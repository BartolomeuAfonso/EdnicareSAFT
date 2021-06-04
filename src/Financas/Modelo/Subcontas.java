/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Modelo;

/**
 *
 * @author El Router
 */
public class Subcontas {

    private int codigo;
    private String designacao;
    private String numero;
    private int conta;

    public Subcontas() {
    }

    public Subcontas(int codigo, String designacao, String numero, int conta) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.numero = numero;
        this.conta = conta;
    }

    public Subcontas(String designacao, String numero, int conta) {
        this.designacao = designacao;
        this.numero = numero;
        this.conta = conta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }
    
    
}
