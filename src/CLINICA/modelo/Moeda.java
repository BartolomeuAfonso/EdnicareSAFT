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
public class Moeda {
    
     int codigo;
    String designacao;
    float cambio;

    public Moeda() {
    }

    public Moeda(int codigo) {
        this.codigo = codigo;
    }

    public Moeda(int codigo, String designacao, float cambio) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.cambio = cambio;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
    
    
    
}
