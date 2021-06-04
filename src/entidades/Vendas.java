/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author Agostinho
 */
public class Vendas {
    
   private int Codigo,CodigoStatusVenda,CodigoCliente, CodigoPontoVenda, codigoMesa, codigoFormaPagamento, codigoUtilizador;
   private String NomeCliente,garcon;
   private Date DataFactura;
   private  Double ValorPagar,ValorEntregue,ValorEntregueMulticaixa,Desconto,Troco,TotalIva;
   private int Ipc;

    public Vendas() {
    }

    public Vendas( int CodigoPontoVenda, int codigoMesa, Double ValorPagar) {

        this.CodigoPontoVenda = CodigoPontoVenda;
        this.codigoMesa = codigoMesa;
        this.ValorPagar = ValorPagar;
    }

    
    
    public Vendas(int Codigo, int CodigoStatusVenda, String CodigoFuncionario, int CodigoCliente,int codigoUtilizador, int CodigoPontoVenda, int codigoFormaPagamento , int codigoMesa, String NomeCliente, Date DataFactura, Double ValorPagar, Double ValorEntregue, Double ValorEntregueMulticaixa,Double Desconto, Double Troco, int Ipc,double TotalIva) {
        this.Codigo = Codigo;
        this.CodigoStatusVenda = CodigoStatusVenda;
        this.codigoUtilizador = codigoUtilizador;
        this.garcon = CodigoFuncionario;
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.CodigoCliente = CodigoCliente;
        this.CodigoPontoVenda = CodigoPontoVenda;
        this.codigoMesa = codigoMesa;
        this.NomeCliente = NomeCliente;
        this.DataFactura = DataFactura;
        this.ValorPagar = ValorPagar;
        this.ValorEntregue = ValorEntregue;
        this.ValorEntregueMulticaixa = ValorEntregueMulticaixa;
        this.Desconto = Desconto;
        this.Troco = Troco;
        this.Ipc = Ipc;
        this.TotalIva = TotalIva;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoStatusVenda() {
        return CodigoStatusVenda;
    }

    public void setCodigoStatusVenda(int CodigoStatusVenda) {
        this.CodigoStatusVenda = CodigoStatusVenda;
    }

  

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getCodigoPontoVenda() {
        return CodigoPontoVenda;
    }

    public void setCodigoPontoVenda(int CodigoPontoVenda) {
        this.CodigoPontoVenda = CodigoPontoVenda;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }

    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public Date getDataFactura() {
        return DataFactura;
    }

    public void setDataFactura(Date DataFactura) {
        this.DataFactura = DataFactura;
    }

    public Double getValorPagar() {
        return ValorPagar;
    }

    public void setValorPagar(Double ValorPagar) {
        this.ValorPagar = ValorPagar;
    }

    public Double getValorEntregue() {
        return ValorEntregue;
    }

    public void setValorEntregue(Double ValorEntregue) {
        this.ValorEntregue = ValorEntregue;
    }

    public Double getDesconto() {
        return Desconto;
    }

    public void setDesconto(Double Desconto) {
        this.Desconto = Desconto;
    }

    public Double getTroco() {
        return Troco;
    }

    public void setTroco(Double Troco) {
        this.Troco = Troco;
    }

    public int getIpc() {
        return Ipc;
    }

    public void setIpc(int Ipc) {
        this.Ipc = Ipc;
    }

    public int getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(int codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }

    public void setCodigoFormaPagamento(int codigoFormaPagamento) {
        this.codigoFormaPagamento = codigoFormaPagamento;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public Double getValorEntregueMulticaixa() {
        return ValorEntregueMulticaixa;
    }

    public void setValorEntregueMulticaixa(Double ValorEntregueMulticaixa) {
        this.ValorEntregueMulticaixa = ValorEntregueMulticaixa;
    }

    public String getGarcon() {
        return garcon;
    }

    public void setGarcon(String garcon) {
        this.garcon = garcon;
    }

    public Double getTotalIva() {
        return TotalIva;
    }

    public void setTotalIva(Double TotalIva) {
        this.TotalIva = TotalIva;
    }
    
   
 
}
