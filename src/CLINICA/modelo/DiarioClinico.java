/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Probook
 */
public class DiarioClinico {
    
    private int idDiario, codigoInternamento;
    private String descricao;

    public DiarioClinico() {
    }

    public DiarioClinico(int idDiario, int codigoInternamento, String descricao) {
        this.idDiario = idDiario;
        this.codigoInternamento = codigoInternamento;
        this.descricao = descricao;
    }

    public int getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(int idDiario) {
        this.idDiario = idDiario;
    }

    public int getCodigoInternamento() {
        return codigoInternamento;
    }

    public void setCodigoInternamento(int codigoInternamento) {
        this.codigoInternamento = codigoInternamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
