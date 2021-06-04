/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Modelo;

/**
 *
 * @author El Router
 */
public class CentroCustos {

    private int codigo;
    private String descricao;
    private String obs;
    private int tipo_centro;

    public CentroCustos() {
    }

    public CentroCustos(int codigo, String descricao, String obs, int tipo_centro) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.obs = obs;
        this.tipo_centro = tipo_centro;
    }
    public CentroCustos(String descricao, String obs, int tipo_centro) {
        this.descricao = descricao;
        this.obs = obs;
        this.tipo_centro = tipo_centro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getTipo_centro() {
        return tipo_centro;
    }

    public void setTipo_centro(int tipo_centro) {
        this.tipo_centro = tipo_centro;
    }
    
    
}
