/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Ramossoft
 */
public class MotivoModelo {

    int codigo;
    String codigoMotivo;
    String descricao;
    int codigoStatus;

    public MotivoModelo() {

    }

    public MotivoModelo(int codigo) {
        this.codigo = codigo;
    }

    public MotivoModelo(int codigo, String codigoMotivo, String descricao, int codigoStatus) {
        this.codigo = codigo;
        this.codigoMotivo = codigoMotivo;
        this.descricao = descricao;
        this.codigoStatus = codigoStatus;
    }

    public MotivoModelo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(String codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

}
