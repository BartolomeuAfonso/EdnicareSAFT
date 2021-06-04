/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Eletronicos
 */
public class Utilizadores {
    
    private int Codigo,CodigoStaus,CodigoTipoUsuario;
    private String NomeCompleto, NomeUsuario,SenhaUsuario, SenhaAdmin;
    private int vendas, pontoVenda;

    public Utilizadores() {
         
    }
    public Utilizadores(String nome, int vendas) {
           this.NomeCompleto = nome;
           this.vendas = vendas;
    }

    public Utilizadores(int Codigo,int CodigoStaus, String NomeCompleto, String NomeUsuario, String SenhaUsuario, String SenhaAdmin, int CodigoTipoUsuario, int vendas) {
        this.Codigo = Codigo;
        this.CodigoStaus = CodigoStaus;
        this.NomeCompleto = NomeCompleto;
        this.NomeUsuario = NomeUsuario;
        this.SenhaUsuario = SenhaUsuario;
        this.SenhaAdmin = SenhaAdmin;
        this.CodigoTipoUsuario = CodigoTipoUsuario;
        this.vendas = vendas;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoStaus() {
        return CodigoStaus;
    }

    public void setCodigoStaus(int CodigoStaus) {
        this.CodigoStaus = CodigoStaus;
    }

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String NomeCompleto) {
        this.NomeCompleto = NomeCompleto;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String NomeUsuario) {
        this.NomeUsuario = NomeUsuario;
    }

    public String getSenhaUsuario() {
        return SenhaUsuario;
    }

    public void setSenhaUsuario(String SenhaUsuario) {
        this.SenhaUsuario = SenhaUsuario;
    }

    public String getSenhaAdmin() {
        return SenhaAdmin;
    }

    public void setSenhaAdmin(String SenhaAdmin) {
        this.SenhaAdmin = SenhaAdmin;
    }

    public int getCodigoTipoUsuario() {
        return CodigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(int CodigoTipoUsuario) {
        this.CodigoTipoUsuario = CodigoTipoUsuario;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }  
    
}
