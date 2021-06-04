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
public class StockMovimento {

    private int Codigo, CodigoStock;
    private int CodigoProduto, codigoArmazem;
    private int quantidadeMin, quantidadeMax;

    public StockMovimento() {
    }

    public StockMovimento(int Codigo, int CodigoStock, int CodigoProduto, int codigoArmazem ) {
        this.Codigo = Codigo;
        this.CodigoStock = CodigoStock;
        this.CodigoProduto = CodigoProduto;
        this.codigoArmazem = codigoArmazem;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoStock() {
        return CodigoStock;
    }

    public void setCodigoStock(int CodigoStock) {
        this.CodigoStock = CodigoStock;
    }

    public int getCodigoProduto() {
        return CodigoProduto;
    }

    public void setCodigoProduto(int CodigoProduto) {
        this.CodigoProduto = CodigoProduto;
    }

    public int getCodigoArmazem() {
        return codigoArmazem;
    }

    public void setCodigoArmazem(int codigoArmazem) {
        this.codigoArmazem = codigoArmazem;
    }

    public int getQuantidadeMin() {
        return quantidadeMin;
    }

    public void setQuantidadeMin(int quantidadeMin) {
        this.quantidadeMin = quantidadeMin;
    }

    public int getQuantidadeMax() {
        return quantidadeMax;
    }

    public void setQuantidadeMax(int quantidadeMax) {
        this.quantidadeMax = quantidadeMax;
    }
    
    
}
