/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Desenvolvimento
 */
public class EstadoCivil {

    private long idEstadoCivil;
    private String designacao;

    public EstadoCivil() {
    }

    public EstadoCivil(long idEstadoCivil, String designacao) {
        this.idEstadoCivil = idEstadoCivil;
        this.designacao = designacao;
    }

    public long getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(long idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    

}
