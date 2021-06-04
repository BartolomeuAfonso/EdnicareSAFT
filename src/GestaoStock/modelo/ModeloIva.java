/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

/**
 *
 * @author Probook
 */
public class ModeloIva {

    private int id;
    private String designacao;
    private int valorDesconto;

    public ModeloIva() {
    }

    public ModeloIva(int id, String designacao, int valorDesconto) {
        this.id = id;
        this.designacao = designacao;
        this.valorDesconto = valorDesconto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(int valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

}
