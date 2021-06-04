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
public class TipoUtilizador {
    
    private long idTipoUtilizador;
    private String designacao;

    public TipoUtilizador() {
    }

    public TipoUtilizador(long idTipoUtilizador, String designacao) {
        this.idTipoUtilizador = idTipoUtilizador;
        this.designacao = designacao;
    }

    public long getIdTipoUtilizador() {
        return idTipoUtilizador;
    }

    public void setIdTipoUtilizador(long idTipoUtilizador) {
        this.idTipoUtilizador = idTipoUtilizador;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    
    
    
}
