/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author Amarildo Xpecialista
 */
public class Existencia {
    private int idExistencia;
    private int codigoArmazem;
    private int existencia;
    private int idUtilizador;
    private Date dataActualizacao;
    private int idProduto;
    public Existencia() {
    }

    public Existencia(int idExistencia, int codigoArmazem, int existencia, int idUtilizador, Date dataActualizacao, int idProduto) {
        this.idExistencia = idExistencia;
        this.codigoArmazem = codigoArmazem;
        this.existencia = existencia;
        this.idUtilizador = idUtilizador;
        this.dataActualizacao = dataActualizacao;
        this.idProduto = idProduto;
    }

    public Existencia(int codigoArmazem, int existencia, int idUtilizador, Date dataActualizacao, int idProduto) {
        this.codigoArmazem = codigoArmazem;
        this.existencia = existencia;
        this.idUtilizador = idUtilizador;
        this.dataActualizacao = dataActualizacao;
        this.idProduto = idProduto;
    }

    public int getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(int idExistencia) {
        this.idExistencia = idExistencia;
    }

    public int getCodigoArmazem() {
        return codigoArmazem;
    }

    public void setCodigoArmazem(int codigoArmazem) {
        this.codigoArmazem = codigoArmazem;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public Date getDataActualizacao() {
        return dataActualizacao;
    }

    public void setDataActualizacao(Date dataActualizacao) {
        this.dataActualizacao = dataActualizacao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
}
