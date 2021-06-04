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
public class FolhaTeraupetica {
    private int codigo;
    private int codigoInternamento;
    private String descricao;

    public FolhaTeraupetica() {
    }

    public FolhaTeraupetica(int codigo, int codigoInternamento, String descricao) {
        this.codigo = codigo;
        this.codigoInternamento = codigoInternamento;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
