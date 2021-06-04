/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author Desenvolvimento
 */
public class Servicos {
    private long idServicos;
    private String designacao,codigoBarra,stocavel;
    private Double preco;
    private Double preco1;
    private Double preco2;
    private Double preco3;
    private Double preco4;
    private Double preco5;
    private Double preco6;
    private Double preco7;
    private Double precoVenda;
    private Double margemLucro;
   
    private Date dataCadastro,dataFabricacao;
    private int codigoCategoriaServico,codigoStatus,codigoUtilizador;

    public Servicos() {
    }

    public Servicos(long idServicos, String designacao, String codigoBarra, Double preco, Date dataCadastro, int codigoCategoriaServico, int codigoStatus, int codigoUtilizador) {
        this.idServicos = idServicos;
        this.designacao = designacao;
        this.codigoBarra = codigoBarra;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.codigoCategoriaServico = codigoCategoriaServico;
        this.codigoStatus = codigoStatus;
        this.codigoUtilizador = codigoUtilizador;
    }

    
    public long getIdServicos() {
        return idServicos;
    }

    public void setIdServicos(long idServicos) {
        this.idServicos = idServicos;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getCodigoCategoriaServico() {
        return codigoCategoriaServico;
    }

    public void setCodigoCategoriaServico(int codigoCategoriaServico) {
        this.codigoCategoriaServico = codigoCategoriaServico;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public Double getPreco1() {
        return preco1;
    }

    public void setPreco1(Double preco1) {
        this.preco1 = preco1;
    }

    public Double getPreco2() {
        return preco2;
    }

    public void setPreco2(Double preco2) {
        this.preco2 = preco2;
    }

    public Double getPreco3() {
        return preco3;
    }

    public void setPreco3(Double preco3) {
        this.preco3 = preco3;
    }

    public Double getPreco4() {
        return preco4;
    }

    public void setPreco4(Double preco4) {
        this.preco4 = preco4;
    }

    public Double getPreco5() {
        return preco5;
    }

    public void setPreco5(Double preco5) {
        this.preco5 = preco5;
    }

    public Double getPreco6() {
        return preco6;
    }

    public void setPreco6(Double preco6) {
        this.preco6 = preco6;
    }

    public Double getPreco7() {
        return preco7;
    }

    public void setPreco7(Double preco7) {
        this.preco7 = preco7;
    }

    public String getStocavel() {
        return stocavel;
    }

    public void setStocavel(String stocavel) {
        this.stocavel = stocavel;
    }

    public Double getPrecoCompra() {
        return precoVenda;
    }

    public void setPrecoCompra(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }
    
}
