/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author DEVTEC
 */
public class CompraProduto {
    
   private int id;
   private Date  dataCompra;
   private String hora;
   private String fatura;
   private Date dataFatura;
   private int fornecedor;
   private int formaPagamento;
   private Date dataEntrega;
   private String horaEntrega;
   private double valorCompra;

    public CompraProduto() {
    }

    public CompraProduto(int id, Date dataCompra, String hora, String fatura, Date dataFatura, int fornecedor, int formaPagamento, Date dataEntrega, String horaEntrega, double valorCompra) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.hora = hora;
        this.fatura = fatura;
        this.dataFatura = dataFatura;
        this.fornecedor = fornecedor;
        this.formaPagamento = formaPagamento;
        this.dataEntrega = dataEntrega;
        this.horaEntrega = horaEntrega;
        this.valorCompra = valorCompra;
    }

    public CompraProduto(Date dataCompra, String hora, String fatura, Date dataFatura, int fornecedor, int formaPagamento, Date dataEntrega, String horaEntrega, double valorCompra) {
        this.dataCompra = dataCompra;
        this.hora = hora;
        this.fatura = fatura;
        this.dataFatura = dataFatura;
        this.fornecedor = fornecedor;
        this.formaPagamento = formaPagamento;
        this.dataEntrega = dataEntrega;
        this.horaEntrega = horaEntrega;
        this.valorCompra = valorCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
    }

    public Date getDataFatura() {
        return dataFatura;
    }

    public void setDataFatura(Date dataFatura) {
        this.dataFatura = dataFatura;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
   
   
   
    
    
    
}
