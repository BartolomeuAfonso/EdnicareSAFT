/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author El Router
 */
public class CategoriaServicos {

      private Long idGrupo;
      private String designacao;
       private int codigoStatus, codigoArea;

    public CategoriaServicos() {
    }

    public CategoriaServicos(Long idGrupo, String designacao, int codigoStatus) {
        this.idGrupo = idGrupo;
        this.designacao = designacao;
        this.codigoStatus = codigoStatus;
    }

    
    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
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

    public int getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }

   
}
