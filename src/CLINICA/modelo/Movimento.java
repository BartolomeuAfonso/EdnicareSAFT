/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class Movimento {
    
    private Long idmovimento;
    private Date cadastro;
    private String descicao;

    public Movimento() {
       
    }

     public Movimento(Long idmovimento, Date cadastro, String descicao) {
        this.idmovimento = idmovimento;
        this.cadastro = cadastro;
        this.descicao = descicao;
    }
    public Long getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(Long idmovimento) {
        this.idmovimento = idmovimento;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }
   
    
    
}
