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
public class Pedido {
    
    private int codPedido;
    private String dataPedido;
    private String dataEntregar;
    private String observacao;
    private int status;
    private int codFarmanceutico;
    private int codFornecedor;

    public Pedido() {}

    public Pedido(int codPedido, String dataPedido, String dataEntregar, String observacao, int status, int codFarmanceutico, int codFornecedor) {
        this.codPedido = codPedido;
        this.dataPedido = dataPedido;
        this.dataEntregar = dataEntregar;
        this.observacao = observacao;
        this.status = status;
        this.codFarmanceutico = codFarmanceutico;
        this.codFornecedor = codFornecedor;
    }

    public Pedido(String dataPedido, String dataEntregar, String observacao, int status, int codFarmanceutico, int codFornecedor) {
        this.dataPedido = dataPedido;
        this.dataEntregar = dataEntregar;
        this.observacao = observacao;
        this.status = status;
        this.codFarmanceutico = codFarmanceutico;
        this.codFornecedor = codFornecedor;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }
    
    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntregar() {
        return dataEntregar;
    }

    public void setDataEntregar(String dataEntregar) {
        this.dataEntregar = dataEntregar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getCodFarmanceutico() {
        return codFarmanceutico;
    }

    public void setCodFarmanceutico(int codFarmanceutico) {
        this.codFarmanceutico = codFarmanceutico;
    }
    
   
    
    
    
    
}
