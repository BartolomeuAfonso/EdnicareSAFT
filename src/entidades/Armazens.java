/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Ramos Soft
 */
public class Armazens {
    private int codigoArmazens, codigoUsuario, codigoStatus;
    private String descricao;

    public Armazens() {
    }

    public Armazens(int codigoArmazens, int codigoUsuario, int ocdigoStatus, String descricao) {
        this.codigoArmazens = codigoArmazens;
        this.codigoUsuario = codigoUsuario;
        this.codigoStatus = ocdigoStatus;
        this.descricao = descricao;
    }

    public int getCodigoArmazens() {
        return codigoArmazens;
    }

    public void setCodigoArmazens(int codigoArmazens) {
        this.codigoArmazens = codigoArmazens;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
