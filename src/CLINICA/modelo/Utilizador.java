/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Desenvolvimento
 */
public class Utilizador {

    private int idUtlizador;
    private String nomeCompleto, username, senha, morada, contacto, email;
    private int codigoStatus, codigoTipoUtilizador;

    public Utilizador() {
    }

    public Utilizador(int idUtlizador) {
        this.idUtlizador = idUtlizador;
    }

    public Utilizador(int idUtlizador, String nomeCompleto, String username, String senha, String morada, String contacto, String email, int codigoStatus, int codigoTipoUtilizador) {
        this.idUtlizador = idUtlizador;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.senha = senha;
        this.morada = morada;
        this.contacto = contacto;
        this.email = email;
        this.codigoStatus = codigoStatus;
        this.codigoTipoUtilizador = codigoTipoUtilizador;
    }

    public long getIdUtlizador() {
        return idUtlizador;
    }

    public void setIdUtlizador(int idUtlizador) {
        this.idUtlizador = idUtlizador;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public int getCodigoTipoUtilizador() {
        return codigoTipoUtilizador;
    }

    public void setCodigoTipoUtilizador(int codigoTipoUtilizador) {
        this.codigoTipoUtilizador = codigoTipoUtilizador;
    }

}
