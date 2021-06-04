/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Agostinho
 */
public class Empresa {

    private int Codigo;
    private String NomeEmpresa, Endereco, Telefone,website, Email, RazaoSocial, Nif, Conta1, Conta2, Conta3, Logotipo;
    private Double desconto;

    public Empresa() {
    }

    public Empresa(int Codigo,String NomeEmpresa, String Endereco,String Telefone, String Email, String RazaoSocial, String Nif, String Conta1, String Conta2, String Conta3, String Logotipo) {
        this.Codigo = Codigo;
        this.NomeEmpresa = NomeEmpresa;
        this.Endereco = Endereco;
        this.Email = Email;
        this.RazaoSocial = RazaoSocial;
        this.Nif = Nif;
        this.Conta1 = Conta1;
        this.Conta2 = Conta2;
        this.Conta3 = Conta3;
        this.Logotipo = Logotipo;
        this.Telefone = Telefone;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNomeEmpresa() {
        return NomeEmpresa;
    }

    public void setNomeEmpresa(String NomeEmpresa) {
        this.NomeEmpresa = NomeEmpresa;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getNif() {
        return Nif;
    }

    public void setNif(String Nif) {
        this.Nif = Nif;
    }

    public String getConta1() {
        return Conta1;
    }

    public void setConta1(String Conta1) {
        this.Conta1 = Conta1;
    }

    public String getConta2() {
        return Conta2;
    }

    public void setConta2(String Conta2) {
        this.Conta2 = Conta2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getConta3() {
        return Conta3;
    }

    public void setConta3(String Conta3) {
        this.Conta3 = Conta3;
    }

    public String getLogotipo() {
        return Logotipo;
    }

    public void setLogotipo(String Logotipo) {
        this.Logotipo = Logotipo;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    

}
