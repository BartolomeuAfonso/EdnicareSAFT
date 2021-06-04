/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author Desenvolvimento
 */
public class Medico {

    private long idMedico;
    private String nomeCompleto, username, senha, contacto, morada, email, numeroOrdem;
    private Double percetagemAGanhar;
    private int codigoTipoUtilizador;
    private int codigoEspecialidade;
    private int limite;
    private Date dataCadastro;

    public Medico() {
    }

    public Medico(long idMedico, String nomeCompleto, String username, String senha, String contacto, String morada, String email, String numeroOrdem, Double percetagemAGanhar, int codigoTipoUtilizador, int codigoEspecialidade, Date dataCadastro) {
        this.idMedico = idMedico;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.senha = senha;
        this.contacto = contacto;
        this.morada = morada;
        this.email = email;
        this.numeroOrdem = numeroOrdem;
        this.percetagemAGanhar = percetagemAGanhar;
        this.codigoTipoUtilizador = codigoTipoUtilizador;
        this.codigoEspecialidade = codigoEspecialidade;
        this.dataCadastro = dataCadastro;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroOrdem() {
        return numeroOrdem;
    }

    public void setNumeroOrdem(String numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Double getPercetagemAGanhar() {
        return percetagemAGanhar;
    }

    public void setPercetagemAGanhar(Double percetagemAGanhar) {
        this.percetagemAGanhar = percetagemAGanhar;
    }

    public int getCodigoTipoUtilizador() {
        return codigoTipoUtilizador;
    }

    public void setCodigoTipoUtilizador(int codigoTipoUtilizador) {
        this.codigoTipoUtilizador = codigoTipoUtilizador;
    }

    public int getCodigoEspecialidade() {
        return codigoEspecialidade;
    }

    public void setCodigoEspecialidade(int codigoEspecialidade) {
        this.codigoEspecialidade = codigoEspecialidade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

}
