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
public class Produto {
    
    private int codProduto;
    private String descricao;
    private double estoqueCritico;
    private double estoqueMin;
    private String dataDadastro;
    private int codCategoria;
//    private int codUnidade;
    private String descricaoAdicional;
    private String codigoProduto;
    private int estoqueAtual;


    public Produto() {
    }

    public Produto(int codProduto, String descricao, double estoqueMax, double estoqueMin, String dataDadastro, int codCategoria, String descricaoAdicional, String codigoProduto, int estoqueAtual) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.estoqueCritico = estoqueMax;
        this.estoqueMin = estoqueMin;
        this.dataDadastro = dataDadastro;
        this.codCategoria = codCategoria;
//        this.codUnidade = codUnidade;
        this.descricaoAdicional = descricaoAdicional;
        this.codigoProduto = codigoProduto;
        this.estoqueAtual = estoqueAtual;
    }

    public Produto(int codProduto, String descricao, int estoqueAtual) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.estoqueAtual = estoqueAtual;
    }

    public Produto(String descricao, double estoqueMax, double estoqueMin, String dataDadastro, int codCategoria, String descricaoAdicional, String codigoProduto, int estoqueAtual) {
        this.descricao = descricao;
        this.estoqueCritico = estoqueMax;
        this.estoqueMin = estoqueMin;
        this.dataDadastro = dataDadastro;
        this.codCategoria = codCategoria;
//        this.codUnidade = codUnidade;
        this.descricaoAdicional = descricaoAdicional;
        this.codigoProduto = codigoProduto;
        this.estoqueAtual = estoqueAtual;
    }

   
    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getEstoqueCritico() {
        return estoqueCritico;
    }

    public void setEstoqueCritico(double estoqueCritico) {
        this.estoqueCritico = estoqueCritico;
    }

    public double getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(double estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public String getDataDadastro() {
        return dataDadastro;
    }

    public void setDataDadastro(String dataDadastro) {
        this.dataDadastro = dataDadastro;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getDescricaoAdicional() {
        return descricaoAdicional;
    }

    public void setDescricaoAdicional(String descricaoAdicional) {
        this.descricaoAdicional = descricaoAdicional;
    }

//    public int getCodUnidade() {
//        return codUnidade;
//    }
//
//    public void setCodUnidade(int codUnidade) {
//        this.codUnidade = codUnidade;
//    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    
    
    
    
}
