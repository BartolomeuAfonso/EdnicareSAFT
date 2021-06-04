/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

/**
 *
 * @author El Router
 */
public class Saida {
    
    private int idSaida;
    private String dataSaida;
    private int codFarmanceutico;
    private String descricao;

    public Saida() {
    }

    public Saida(int idSaida, String dataSaida, int codFarmanceutico, String descricao) {
        this.idSaida = idSaida;
        this.dataSaida = dataSaida;
        this.codFarmanceutico = codFarmanceutico;
        this.descricao = descricao;
    }

    public Saida(String dataSaida, int codFarmanceutico, String descricao) {
        this.dataSaida = dataSaida;
        this.codFarmanceutico = codFarmanceutico;
        this.descricao = descricao;
    }

    public int getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(int idSaida) {
        this.idSaida = idSaida;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public int getCodFarmanceutico() {
        return codFarmanceutico;
    }

    public void setCodFarmanceutico(int codFarmanceutico) {
        this.codFarmanceutico = codFarmanceutico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
