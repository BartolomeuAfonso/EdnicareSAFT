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
public class PedidoProduto {
    private long idPedidoProduto;
    private int codigoCliente,Utilizador;
    private Date dataPedido;

    public PedidoProduto() {
    }

    
    public PedidoProduto(long idPedidoProduto, int codigoCliente, int Utilizador, Date dataPedido) {
        this.idPedidoProduto = idPedidoProduto;
        this.codigoCliente = codigoCliente;
        this.Utilizador = Utilizador;
        this.dataPedido = dataPedido;
    }
    public PedidoProduto(int codigoCliente, int Utilizador, Date dataPedido) {
        this.idPedidoProduto = idPedidoProduto;
        this.codigoCliente = codigoCliente;
        this.Utilizador = Utilizador;
        this.dataPedido = dataPedido;
    }

    public long getIdPedidoProduto() {
        return idPedidoProduto;
    }

    public void setIdPedidoProduto(long idPedidoProduto) {
        this.idPedidoProduto = idPedidoProduto;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getUtilizador() {
        return Utilizador;
    }

    public void setUtilizador(int Utilizador) {
        this.Utilizador = Utilizador;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
    
    
    
}
