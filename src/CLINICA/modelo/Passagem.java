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
public class Passagem {
    
    private int idPassagem;
    private String designacao;
    private int codigoUtilizador;
    private String data;

    public Passagem() {
    }

    public Passagem(int idPassagem, String designacao, int codigoUtilizador, String data) {
        this.idPassagem = idPassagem;
        this.designacao = designacao;
        this.codigoUtilizador = codigoUtilizador;
        this.data = data;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
