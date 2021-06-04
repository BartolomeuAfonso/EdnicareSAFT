/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

/**
 *
 * @author El Router
 */
public class EstoqueAtual {

    private int produto;
    private int estoqueCx;
    private int estoqueUn;
    private int fornecedor;

    public EstoqueAtual() {}

    public EstoqueAtual(int produto, int estoqueCx, int estoqueUn, int fornecedor) {
        this.produto = produto;
        this.estoqueCx = estoqueCx;
        this.estoqueUn = estoqueUn;
        this.fornecedor = fornecedor;
    }
    
   
    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getEstoqueCx() {
        return estoqueCx;
    }

    public void setEstoqueCx(int estoqueCx) {
        this.estoqueCx = estoqueCx;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getEstoqueUn() {
        return estoqueUn;
    }

    public void setEstoqueUn(int estoqueUn) {
        this.estoqueUn = estoqueUn;
    }
    
    

}
