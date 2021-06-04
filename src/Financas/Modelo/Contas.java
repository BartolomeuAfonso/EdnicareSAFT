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
public class Contas {

    private int codigo;
    private String descricao;
    private int centro_custo;
    private String obs;

    public Contas() {
    }

    public Contas(int codigo, String descricao, int centro_custo, String obs) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.centro_custo = centro_custo;
        this.obs = obs;
    }
    public Contas(String descricao, int centro_custo, String obs) {
        this.descricao = descricao;
        this.centro_custo = centro_custo;
        this.obs = obs;
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

    public int getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(int centro_custo) {
        this.centro_custo = centro_custo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
}
