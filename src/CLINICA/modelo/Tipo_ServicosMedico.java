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
public class Tipo_ServicosMedico {
    
    /*
        Os tipos de serviços médicos podem ser:
        1-CONSULTAS.
        2-SERVIÇOS.
    */
    
    private Long idGrupo;
    private String designacao;

    public Tipo_ServicosMedico() {
    }

    public Tipo_ServicosMedico(Long idGrupo, String designacao) {
        this.idGrupo = idGrupo;
        this.designacao = designacao;
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
    
    
    
}
