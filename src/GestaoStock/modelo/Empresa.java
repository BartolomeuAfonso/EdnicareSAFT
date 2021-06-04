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
public class Empresa {
    
    private Long idFornecedor;
    private String nome;
    private String endereco;
    private String email;
    private String contacto;
    private String nif;
    private String website;

    public Empresa() {}

    public Empresa(Long idFornecedor, String nome, String endereco, String email, String contacto, String nif, String pais) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.contacto = contacto;
        this.nif = nif;
        this.website = pais;
    }

    public Empresa(String nome, String endereco, String email, String contacto, String nif, String pais) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.contacto = contacto;
        this.nif = nif;
        this.website = pais;
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Long idFornecedor) {
        this.idFornecedor = idFornecedor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
   
    
    
}
