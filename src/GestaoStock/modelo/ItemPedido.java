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
public class ItemPedido {
    private int codItem;
    private int codPedido;
    private int codProduto;
    private int codUnidade;
    private double qtdePedida;
    private String obs;
    private int status;

    public ItemPedido() {
    }

    public ItemPedido(int codItem,int codPedido, int codProduto, double qtdePedida, String obs, int status,int codUnidade) {
        this.codPedido = codPedido;
        this.codProduto = codProduto;
        this.qtdePedida = qtdePedida;
        this.obs = obs;
        this.status = status;
        this.codUnidade = codUnidade;
    }
    public ItemPedido(int codPedido, int codProduto, double qtdePedida, String obs, int status,int codUnidade) {
        this.codPedido = codPedido;
        this.codProduto = codProduto;
        this.qtdePedida = qtdePedida;
        this.obs = obs;
        this.status = status;
        this.codUnidade = codUnidade;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public double getQtdePedida() {
        return qtdePedida;
    }

    public void setQtdePedida(double qtdePedida) {
        this.qtdePedida = qtdePedida;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        this.codItem = codItem;
    }

    public int getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(int codUnidade) {
        this.codUnidade = codUnidade;
    }
    
    
}
