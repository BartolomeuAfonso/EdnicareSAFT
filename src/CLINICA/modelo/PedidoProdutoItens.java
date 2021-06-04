/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Desenvolvimento
 */
public class PedidoProdutoItens {

    private int codigoProduto;
    private int codigoPedidoProduto;
    private int quantidade;

    public PedidoProdutoItens() {
    }

    public PedidoProdutoItens(int codigoProduto, int codigoPedidoProduto, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.codigoPedidoProduto = codigoPedidoProduto;
        this.quantidade = quantidade;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoPedidoProduto() {
        return codigoPedidoProduto;
    }

    public void setCodigoPedidoProduto(int codigoPedidoProduto) {
        this.codigoPedidoProduto = codigoPedidoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
