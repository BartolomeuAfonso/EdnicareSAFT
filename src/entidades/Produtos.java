/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author 
 */
public class Produtos {
   private int Codigo,CodigoUsuario,CodigoStatus,CodigoUnidade;
   private Date DataCadastro,DataExpiracao,dataFabrico;
   private String Descricao,CodigoBarra,Stocavel,logotipo,descricaAct, categoria,EstoqueZero;
   private Double vendaR,estoqueMin, estoqueCrit,estoqueAct,PrecoCompra,margemR,margemG,vendaG,qtdCalculada,percPromocao;
   private int categ1,diasAlerta;

    public Produtos() {
    }

   

    public Produtos(double estoqueMin, double estoqueMax, double estoqueAct, String descricaAct,String categoria) {
        this.estoqueMin = estoqueMin;
        this.estoqueCrit = estoqueMax;
        this.estoqueAct = estoqueAct;
        this.descricaAct = descricaAct;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(int CodigoUsuario) {
        this.CodigoUsuario = CodigoUsuario;
    }

    public int getCodigoStatus() {
        return CodigoStatus;
    }

    public void setCodigoStatus(int CodigoStatus) {
        this.CodigoStatus = CodigoStatus;
    }

    public int getCodigoUnidade() {
        return CodigoUnidade;
    }

    public void setCodigoUnidade(int CodigoUnidade) {
        this.CodigoUnidade = CodigoUnidade;
    }

    public Date getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(Date DataCadastro) {
        this.DataCadastro = DataCadastro;
    }

    public Date getDataExpiracao() {
        return DataExpiracao;
    }

    public void setDataExpiracao(Date DataExpiracao) {
        this.DataExpiracao = DataExpiracao;
    }

    public Date getDataFabrico() {
        return dataFabrico;
    }

    public void setDataFabrico(Date dataFabrico) {
        this.dataFabrico = dataFabrico;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getCodigoBarra() {
        return CodigoBarra;
    }

    public void setCodigoBarra(String CodigoBarra) {
        this.CodigoBarra = CodigoBarra;
    }

    public String getStocavel() {
        return Stocavel;
    }

    public void setStocavel(String Stocavel) {
        this.Stocavel = Stocavel;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getDescricaAct() {
        return descricaAct;
    }

    public void setDescricaAct(String descricaAct) {
        this.descricaAct = descricaAct;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstoqueZero() {
        return EstoqueZero;
    }

    public void setEstoqueZero(String EstoqueZero) {
        this.EstoqueZero = EstoqueZero;
    }

    public Double getVendaR() {
        return vendaR;
    }

    public void setVendaR(Double vendaR) {
        this.vendaR = vendaR;
    }

    public Double getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(Double estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public Double getEstoqueCrit() {
        return estoqueCrit;
    }

    public void setEstoqueCrit(Double estoqueCrit) {
        this.estoqueCrit = estoqueCrit;
    }

    public Double getEstoqueAct() {
        return estoqueAct;
    }

    public void setEstoqueAct(Double estoqueAct) {
        this.estoqueAct = estoqueAct;
    }

    public Double getPrecoCompra() {
        return PrecoCompra;
    }

    public void setPrecoCompra(Double PrecoCompra) {
        this.PrecoCompra = PrecoCompra;
    }

    public Double getMargemR() {
        return margemR;
    }

    public void setMargemR(Double margemR) {
        this.margemR = margemR;
    }

    public Double getMargemG() {
        return margemG;
    }

    public void setMargemG(Double margemG) {
        this.margemG = margemG;
    }

    public Double getVendaG() {
        return vendaG;
    }

    public void setVendaG(Double vendaG) {
        this.vendaG = vendaG;
    }

    public Double getQtdCalculada() {
        return qtdCalculada;
    }

    public void setQtdCalculada(Double qtdCalculada) {
        this.qtdCalculada = qtdCalculada;
    }

    public Double getPercPromocao() {
        return percPromocao;
    }

    public void setPercPromocao(Double percPromocao) {
        this.percPromocao = percPromocao;
    }

    public int getCateg1() {
        return categ1;
    }

    public void setCateg1(int categ1) {
        this.categ1 = categ1;
    }

    public int getDiasAlerta() {
        return diasAlerta;
    }

    public void setDiasAlerta(int diasAlerta) {
        this.diasAlerta = diasAlerta;
    }

    
  
  
   
}
