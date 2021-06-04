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
public class DiagnosticoInternamento {

    private int id, codigoInternamento;
    private String descricao1, descricao2, descricao3, descricao4, descricao5;

    public DiagnosticoInternamento() {
    }

    public DiagnosticoInternamento(int id, int codigoInternamento, String descricao1, String descricao2, String descricao3, String descricao4, String descricao5) {
        this.id = id;
        this.codigoInternamento = codigoInternamento;
        this.descricao1 = descricao1;
        this.descricao2 = descricao2;
        this.descricao3 = descricao3;
        this.descricao4 = descricao4;
        this.descricao5 = descricao5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoInternamento() {
        return codigoInternamento;
    }

    public void setCodigoInternamento(int codigoInternamento) {
        this.codigoInternamento = codigoInternamento;
    }

    public String getDescricao1() {
        return descricao1;
    }

    public void setDescricao1(String descricao1) {
        this.descricao1 = descricao1;
    }

    public String getDescricao2() {
        return descricao2;
    }

    public void setDescricao2(String descricao2) {
        this.descricao2 = descricao2;
    }

    public String getDescricao3() {
        return descricao3;
    }

    public void setDescricao3(String descricao3) {
        this.descricao3 = descricao3;
    }

    public String getDescricao4() {
        return descricao4;
    }

    public void setDescricao4(String descricao4) {
        this.descricao4 = descricao4;
    }

    public String getDescricao5() {
        return descricao5;
    }

    public void setDescricao5(String descricao5) {
        this.descricao5 = descricao5;
    }

}
