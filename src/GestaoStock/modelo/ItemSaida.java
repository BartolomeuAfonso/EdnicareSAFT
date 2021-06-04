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
public class ItemSaida {
    
    private int codItem;
    private int codSaida;
    private int codProduto;
    private int qtdeSaida;
    private int codUnidade;

    public ItemSaida() {
    }

    public ItemSaida(int codItem, int codSaida, int codProduto, int qtdeSaida, int codUnidade) {
        this.codItem = codItem;
        this.codSaida = codSaida;
        this.codProduto = codProduto;
        this.qtdeSaida = qtdeSaida;
        this.codUnidade = codUnidade;
    }

    public ItemSaida(int codSaida, int codProduto, int qtdeSaida, int codUnidade) {
        this.codSaida = codSaida;
        this.codProduto = codProduto;
        this.qtdeSaida = qtdeSaida;
        this.codUnidade = codUnidade;
    }

    

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        this.codItem = codItem;
    }

    public int getCodSaida() {
        return codSaida;
    }

    public void setCodSaida(int codSaida) {
        this.codSaida = codSaida;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQtdeSaida() {
        return qtdeSaida;
    }

    public void setQtdeSaida(int qtdeSaida) {
        this.qtdeSaida = qtdeSaida;
    }

    public int getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(int codUnidade) {
        this.codUnidade = codUnidade;
    }
    
    
}
