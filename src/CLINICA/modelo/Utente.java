/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class Utente {

    private int idBeneficiario;
    private String nomeCompleto;
    private String idade;
    private String nomePai;
    private String nomeMae;
    private Date nascimento;
    private String morada;
    private String data;
    private String telefone;
    private String contacto;
    private String email;
    private String genero;
    private String estado_civil;
    private String naturalidade;
    private String BI;
    private String nif;
    private int empresa;
    private String apn;
    private String tomadorSegurado;
    private int codigoNaturalidade, codigoSexo;
    private Date dataNascimento;

    public Utente() {
    }

    public Utente(int idBeneficiario, String nomeCompleto, String idade, String nomePai, String nomeMae, Date nascimento, String morada, String telefone, String email, String genero, String estado_civil, String naturalidade, String BI, String raca, String numPasse, String categoria, String tipoBeneficiario) {
        this.idBeneficiario = idBeneficiario;
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
        this.BI = BI;

    }

    public Utente(int codigo) {
        this.idBeneficiario = codigo;
    }

    public Utente(String nomeCompleto, String idade, String nomePai, String nomeMae, Date nascimento, String morada, String telefone, String email, String genero, String estado_civil, String naturalidade, String BI, String raca, String tipoBeneficiario) {
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.naturalidade = naturalidade;
        this.BI = BI;

    }

    public Utente(String nomeCompleto, String nomePai, String nomeMae, String telefone, String contacto, String BI, String tomador, String apn) {
        this.nomeCompleto = nomeCompleto;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.telefone = telefone;
        this.contacto = contacto;
        this.BI = BI;
        this.tomadorSegurado = tomador;
        this.apn = apn;

    }

    public Utente(String nomeCompleto, String nomePai, String nomeMae, String telefone, String contacto, String BI, String tomador, String apn, String morada, Date dataNascimento, String email) {
        this.nomeCompleto = nomeCompleto;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.telefone = telefone;
        this.contacto = contacto;
        this.BI = BI;
        this.tomadorSegurado = tomador;
        this.apn = apn;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.email = email;

    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getTomadorSegurado() {
        return tomadorSegurado;
    }

    public void setTomadorSegurado(String tomadorSegurado) {
        this.tomadorSegurado = tomadorSegurado;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getBI() {
        return BI;
    }

    public void setBI(String BI) {
        this.BI = BI;
    }

    public int getCodigoNaturalidade() {
        return codigoNaturalidade;
    }

    public void setCodigoNaturalidade(int codigoNaturalidade) {
        this.codigoNaturalidade = codigoNaturalidade;
    }

    public int getCodigoSexo() {
        return codigoSexo;
    }

    public void setCodigoSexo(int codigoSexo) {
        this.codigoSexo = codigoSexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

}
