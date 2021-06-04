/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class Fornecedor {

    private int codigo;
    private String nome;
    private String endereco;
    private String contacto;
    private String email;
    private String website;
    private Date data_cadastro;

    public Fornecedor() {
    }

    public Fornecedor(int codigo, String nome, String endereco, String contacto, String email, String website, Date data_cadastro) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.contacto = contacto;
        this.email = email;
        this.website = website;
        this.data_cadastro = data_cadastro;
    }

    public Fornecedor(String nome, String endereco, String contacto, String email, String website, Date data_cadastro) {
        this.nome = nome;
        this.endereco = endereco;
        this.contacto = contacto;
        this.email = email;
        this.website = website;
        this.data_cadastro = data_cadastro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
    
    
}
