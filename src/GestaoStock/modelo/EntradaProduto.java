/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

import java.util.Objects;

/**
 *
 * @author El Router
 */
public class EntradaProduto {
    
    private Long idEntrada;
    private String cadastro;
    private int fornecedor;
    private String fatura;
    private String dataFatura;
    private int farmanceutico;


    public EntradaProduto() {
    }

    public EntradaProduto(Long idEntrada, String cadastro, int fornecedor, String fatura, String dataFatura, int farmanceutico) {
        this.idEntrada = idEntrada;
        this.cadastro = cadastro;
        this.fornecedor = fornecedor;
        this.fatura = fatura;
        this.dataFatura = dataFatura;
        this.farmanceutico = farmanceutico;
    }

    public EntradaProduto(String cadastro, int fornecedor, String fatura, String dataFatura, int farmanceutico) {
        this.cadastro = cadastro;
        this.fornecedor = fornecedor;
        this.fatura = fatura;
        this.dataFatura = dataFatura;
        this.farmanceutico = farmanceutico;
    }

    public Long getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Long idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
    }

    public String getDataFatura() {
        return dataFatura;
    }

    public void setDataFatura(String dataFatura) {
        this.dataFatura = dataFatura;
    }

    public int getFarmanceutico() {
        return farmanceutico;
    }

    public void setFarmanceutico(int farmanceutico) {
        this.farmanceutico = farmanceutico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idEntrada);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntradaProduto other = (EntradaProduto) obj;
        if (!Objects.equals(this.idEntrada, other.idEntrada)) {
            return false;
        }
        return true;
    }

   
    
    
}
