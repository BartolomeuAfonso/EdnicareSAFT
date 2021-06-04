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
public class Especialidade {
    
    private Long idEspecialidade;
    private int idGrupoEspecialidade;
   
    
    private String designacao;

    public Especialidade() {
    }

    public Especialidade(Long idEspecialidade, int idGrupoEspecialidade, String designacao) {
        this.idEspecialidade = idEspecialidade;
        this.idGrupoEspecialidade = idGrupoEspecialidade;
        this.designacao = designacao;
       
    }

    public Especialidade(int idGrupoEspecialidade, String designacao) {
        this.idGrupoEspecialidade = idGrupoEspecialidade;
        this.designacao = designacao;
       
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public int getIdGrupoEspecialidade() {
        return idGrupoEspecialidade;
    }

    public void setIdGrupoEspecialidade(int idGrupoEspecialidade) {
        this.idGrupoEspecialidade = idGrupoEspecialidade;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

   
    
    
}
