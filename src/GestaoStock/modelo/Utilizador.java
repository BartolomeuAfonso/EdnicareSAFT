/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

/**
 *
 * @author Eletronicos
 */
public class Utilizador {
    
    private int codUtilizador,
            acesso,areafuncional,status;
    private String nomeCompleto;
    private String userName;
    private String senha;

    public Utilizador() {
    }

    public Utilizador(int codUtilizador, int acesso, int areafuncional, int status, String nomeCompleto, String userName, String senha) {
        this.codUtilizador = codUtilizador;
        this.acesso = acesso;
        this.areafuncional = areafuncional;
        this.status = status;
        this.nomeCompleto = nomeCompleto;
        this.userName = userName;
        this.senha = senha;
    }

    public Utilizador(int acesso, int areafuncional, int status, String nomeCompleto, String userName, String senha) {
        this.acesso = acesso;
        this.areafuncional = areafuncional;
        this.status = status;
        this.nomeCompleto = nomeCompleto;
        this.userName = userName;
        this.senha = senha;
    }

    public int getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(int codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public int getAcesso() {
        return acesso;
    }

    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }

    public int getAreafuncional() {
        return areafuncional;
    }

    public void setAreafuncional(int areafuncional) {
        this.areafuncional = areafuncional;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    
}
