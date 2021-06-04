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
public class Fabricantes {
    private long idFabricante;
    private String designacao;
    private int codigoStatus;

    public Fabricantes() {
    }

    public Fabricantes(long idFabricante, String designacao, int codigoStatus) {
        this.idFabricante = idFabricante;
        this.designacao = designacao;
        this.codigoStatus = codigoStatus;
    }

    public long getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }
    
    
}
