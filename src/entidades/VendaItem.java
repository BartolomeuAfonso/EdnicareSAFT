/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Agostinho
 */
public class VendaItem {
    private int Codigo,CodigoVenda,Quantidade, codigoProduto;
   private Double ValorTotal,precoUnitario;

    public VendaItem() {
    }

    public VendaItem(int Codigo, int CodigoVenda, int codigoProduto, int Quantidade, Double ValorPAgar, Double precoUnitario) {
        this.Codigo = Codigo;
        this.CodigoVenda = CodigoVenda;
        this.codigoProduto = codigoProduto;
        this.Quantidade = Quantidade;
        this.ValorTotal = ValorPAgar;
        this.precoUnitario = precoUnitario;
      
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoVenda() {
        return CodigoVenda;
    }

    public void setCodigoVenda(int CodigoVenda) {
        this.CodigoVenda = CodigoVenda;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public Double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Double ValorPAgar) {
        this.ValorTotal = ValorPAgar;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

  
   
    
}
