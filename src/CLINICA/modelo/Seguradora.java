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
public class Seguradora {

    private int Codigo;
    private String NomeEmpresa, Endereco, Telefone, Email, Nif;

    public Seguradora() {
    }

    public Seguradora(int Codigo, String NomeEmpresa, String Endereco, String Telefone, String Email, String Nif) {
        this.Codigo = Codigo;
        this.NomeEmpresa = NomeEmpresa;
        this.Endereco = Endereco;
        this.Telefone = Telefone;
        this.Email = Email;
        this.Nif = Nif;
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

    public String getNif() {
        return Nif;
    }

    public void setNif(String Nif) {
        this.Nif = Nif;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

}
