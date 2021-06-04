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
public class TipoTaxaModelo {
    int codigo;
    int taxa;
    String descricao;
    MotivoModelo motivo;
    int codigoStatus;

    public TipoTaxaModelo() {
    }

    public TipoTaxaModelo(int codigo) {
        this.codigo = codigo;
    }
    
    public TipoTaxaModelo(int codigo, int taxa, String descricao, MotivoModelo motivo, int codigoStatus) {
        this.codigo = codigo;
        this.taxa = taxa;
        this.descricao = descricao;
        this.motivo = motivo;
        this.codigoStatus = codigoStatus;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTaxa() {
        return taxa;
    }

    public void setTaxa(int taxa) {
        this.taxa = taxa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MotivoModelo getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoModelo motivo) {
        this.motivo = motivo;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

   
}
