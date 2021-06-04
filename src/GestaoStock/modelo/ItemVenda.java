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
public class ItemVenda {

    private int codItemvenda;
    private int codVenda;
    private int codProduto;
    private double qtdItem;
    private double valorUnitario, descontoIVa, totalGeral, totalTPA;

    public ItemVenda() {
    }

    public ItemVenda(int codItemvenda, int codVenda, int codProduto, double qtdItem, double valorUnitario) {
        this.codItemvenda = codItemvenda;
        this.codVenda = codVenda;
        this.codProduto = codProduto;
        this.qtdItem = qtdItem;
        this.valorUnitario = valorUnitario;
    }

    public ItemVenda(int codVenda, int codProduto, double qtdItem, double valorUnitario) {
        this.codVenda = codVenda;
        this.codProduto = codProduto;
        this.qtdItem = qtdItem;
        this.valorUnitario = valorUnitario;
    }

    public int getCodItemvenda() {
        return codItemvenda;
    }

    public void setCodItemvenda(int codItemvenda) {
        this.codItemvenda = codItemvenda;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public double getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(double qtdItem) {
        this.qtdItem = qtdItem;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getDescontoIVa() {
        return descontoIVa;
    }

    public void setDescontoIVa(double descontoIVa) {
        this.descontoIVa = descontoIVa;
    }

    public double getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(double totalGeral) {
        this.totalGeral = totalGeral;
    }

    public double getTotalTPA() {
        return totalTPA;
    }

    public void setTotalTPA(double totalTPA) {
        this.totalTPA = totalTPA;
    }
    

}
