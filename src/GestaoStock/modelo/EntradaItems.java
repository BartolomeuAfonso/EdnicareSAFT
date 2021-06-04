/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class EntradaItems {

    private int codItem;
    private int idEntrada;
    private int idFornecedor;
    private int idProduto;
    private int idUnidade;
    private int idCentroEstoque;
    private double quantidadeEntrada;
    private int quantidadeUnidade;
    private double valorEntrada;
    private double precoEntradaItem;
    private double precoSaidaItem;
    private double lucroUnitario;
    private double lucroGeral;
    private String lote;
    private String dataValidade;
    private String dataFabricacao;
    private Date date;
    private Date date1;
    private int prasoValidade;

    public EntradaItems() {
    }

    public EntradaItems(int codItem, int idEntrada, int idFornecedor, int idProduto, int idUnidade, int idCentroEstoque, double quantidadeEntrada, int quantidadeUnidade, double valorEntrada, double precoEntradaItem, double precoSaidaItem, double lucroUnitario, double lucroGeral, String lote, String dataValidade, String dataFabricacao, int prasoValidade) {
        this.codItem = codItem;
        this.idEntrada = idEntrada;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
        this.idUnidade = idUnidade;
        this.idCentroEstoque = idCentroEstoque;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeUnidade = quantidadeUnidade;
        this.valorEntrada = valorEntrada;
        this.precoEntradaItem = precoEntradaItem;
        this.precoSaidaItem = precoSaidaItem;
        this.lucroUnitario = lucroUnitario;
        this.lucroGeral = lucroGeral;
        this.lote = lote;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.prasoValidade = prasoValidade;
    }

    public EntradaItems(int idEntrada, int idFornecedor, int idProduto, int idUnidade, int idCentroEstoque, double quantidadeEntrada, int quantidadeUnidade, double valorEntrada, double precoEntradaItem, double precoSaidaItem, double lucroUnitario, double lucroGeral, String lote, String dataValidade, String dataFabricacao, int prasoValidade) {
        this.idEntrada = idEntrada;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
        this.idUnidade = idUnidade;
        this.idCentroEstoque = idCentroEstoque;
        this.quantidadeEntrada = quantidadeEntrada;
        this.quantidadeUnidade = quantidadeUnidade;
        this.valorEntrada = valorEntrada;
        this.precoEntradaItem = precoEntradaItem;
        this.precoSaidaItem = precoSaidaItem;
        this.lucroUnitario = lucroUnitario;
        this.lucroGeral = lucroGeral;
        this.lote = lote;
        this.dataValidade = dataValidade;
        this.dataFabricacao = dataFabricacao;
        this.prasoValidade = prasoValidade;
    }

    public EntradaItems(int codProduto, String dataValidade) {
        this.dataValidade = dataValidade;
        this.idProduto = idProduto;
    }

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        this.codItem = codItem;
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public int getIdCentroEstoque() {
        return idCentroEstoque;
    }

    public void setIdCentroEstoque(int idCentroEstoque) {
        this.idCentroEstoque = idCentroEstoque;
    }

    public double getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(double quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public int getQuantidadeUnidade() {
        return quantidadeUnidade;
    }

    public void setQuantidadeUnidade(int quantidadeUnidade) {
        this.quantidadeUnidade = quantidadeUnidade;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getPrecoEntradaItem() {
        return precoEntradaItem;
    }

    public void setPrecoEntradaItem(double precoEntradaItem) {
        this.precoEntradaItem = precoEntradaItem;
    }

    public double getPrecoSaidaItem() {
        return precoSaidaItem;
    }

    public void setPrecoSaidaItem(double precoSaidaItem) {
        this.precoSaidaItem = precoSaidaItem;
    }

    public double getLucroUnitario() {
        return lucroUnitario;
    }

    public void setLucroUnitario(double lucroUnitario) {
        this.lucroUnitario = lucroUnitario;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getPrasoValidade() {
        return prasoValidade;
    }

    public void setPrasoValidade(int prasoValidade) {
        this.prasoValidade = prasoValidade;
    }

    public double getLucroGeral() {
        return lucroGeral;
    }

    public void setLucroGeral(double lucroGeral) {
        this.lucroGeral = lucroGeral;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    
}
