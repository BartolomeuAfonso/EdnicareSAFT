/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author 
 */
public class ItensCompra {
   private int Codigo,idCompra,idProduto,qtd;
   private double valorUnitario,valorTotal;

    public ItensCompra() {
    }

    public ItensCompra(int Codigo, int idCompra, int idProduto, int qtd, double valorUnitario, double valorTotal) {
        this.Codigo = Codigo;
        this.idCompra = idCompra;
        this.idProduto = idProduto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public ItensCompra(int idCompra, int idProduto, int qtd, double valorUnitario, double valorTotal) {
        this.idCompra = idCompra;
        this.idProduto = idProduto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    
    
}
