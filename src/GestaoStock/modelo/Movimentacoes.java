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
public class Movimentacoes {
    
    private int cod;
    private String data;
    private int tipo;
    private String item;
    private int qtdAnteriorCX;
    private int qtdAnteriorUN;
    private int qtdMovimentadaCX;
    private int qtdMovimentadaUN;
    private int qtdDisponivelCX;
    private int qtdDisponivelUN;
    private int codUtilizador;
    private String loteProduto;
    private int codProduto;

    public Movimentacoes() {
    }

    public Movimentacoes(int cod, String data, int tipo, String item, int qtdAnteriorCX, int qtdAnteriorUN, int qtdMovimentadaCX, int qtdMovimentadaUN, int qtdDisponivelCX, int qtdDisponivelUN, int codUtilizador, String loteProduto, int codProduto) {
        this.cod = cod;
        this.data = data;
        this.tipo = tipo;
        this.item = item;
        this.qtdAnteriorCX = qtdAnteriorCX;
        this.qtdAnteriorUN = qtdAnteriorUN;
        this.qtdMovimentadaCX = qtdMovimentadaCX;
        this.qtdMovimentadaUN = qtdMovimentadaUN;
        this.qtdDisponivelCX = qtdDisponivelCX;
        this.qtdDisponivelUN = qtdDisponivelUN;
        this.codUtilizador = codUtilizador;
        this.loteProduto = loteProduto;
        this.codProduto = codProduto;
    }

    public Movimentacoes(String data, int tipo, String item, int qtdAnteriorCX, int qtdAnteriorUN, int qtdMovimentadaCX, int qtdMovimentadaUN, int qtdDisponivelCX, int qtdDisponivelUN, int codUtilizador, String loteProduto, int codProduto) {
        this.data = data;
        this.tipo = tipo;
        this.item = item;
        this.qtdAnteriorCX = qtdAnteriorCX;
        this.qtdAnteriorUN = qtdAnteriorUN;
        this.qtdMovimentadaCX = qtdMovimentadaCX;
        this.qtdMovimentadaUN = qtdMovimentadaUN;
        this.qtdDisponivelCX = qtdDisponivelCX;
        this.qtdDisponivelUN = qtdDisponivelUN;
        this.codUtilizador = codUtilizador;
        this.loteProduto = loteProduto;
        this.codProduto = codProduto;
    }

   
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQtdAnteriorCX() {
        return qtdAnteriorCX;
    }

    public void setQtdAnteriorCX(int qtdAnteriorCX) {
        this.qtdAnteriorCX = qtdAnteriorCX;
    }

    public int getQtdAnteriorUN() {
        return qtdAnteriorUN;
    }

    public void setQtdAnteriorUN(int qtdAnteriorUN) {
        this.qtdAnteriorUN = qtdAnteriorUN;
    }

    public int getQtdMovimentadaCX() {
        return qtdMovimentadaCX;
    }

    public void setQtdMovimentadaCX(int qtdMovimentadaCX) {
        this.qtdMovimentadaCX = qtdMovimentadaCX;
    }

    public int getQtdMovimentadaUN() {
        return qtdMovimentadaUN;
    }

    public void setQtdMovimentadaUN(int qtdMovimentadaUN) {
        this.qtdMovimentadaUN = qtdMovimentadaUN;
    }

    public int getQtdDisponivelCX() {
        return qtdDisponivelCX;
    }

    public void setQtdDisponivelCX(int qtdDisponivelCX) {
        this.qtdDisponivelCX = qtdDisponivelCX;
    }

    public int getQtdDisponivelUN() {
        return qtdDisponivelUN;
    }

    public void setQtdDisponivelUN(int qtdDisponivelUN) {
        this.qtdDisponivelUN = qtdDisponivelUN;
    }

    public int getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(int codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getLoteProduto() {
        return loteProduto;
    }

    public void setLoteProduto(String loteProduto) {
        this.loteProduto = loteProduto;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    
    
}
