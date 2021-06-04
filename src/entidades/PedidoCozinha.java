/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;


/**
 *
 * @author Devtec
 */
public class PedidoCozinha {

    private int Codigo;
    private String produto,mesa,atendente,qtd;

    public PedidoCozinha() {
    }

    public PedidoCozinha(int Codigo, String Quantidade, String produto, String mesa, String atendente) {
        this.Codigo = Codigo;
        this.qtd = Quantidade;
        this.produto = produto;
        this.mesa = mesa;
        this.atendente = atendente;
    }

    public PedidoCozinha(String Quantidade, String produto, String mesa, String atendente) {
        this.qtd = Quantidade;
        this.produto = produto;
        this.mesa = mesa;
        this.atendente = atendente;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    
   
}
