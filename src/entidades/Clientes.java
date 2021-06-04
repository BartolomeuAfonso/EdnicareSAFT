/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Router
 */
public class Clientes {

    private int Codigo,CodigoStatus;
    private String Nome,Endereco,Email,DataCadastro;
    private int Telefone;
    private double saldo;

    public Clientes() {
    }

    public Clientes(int Codigo, int CodigoStatus, String Nome, String Endereco, String Email, String DataCadastro, int Telefone, double saldo) {
        this.Codigo = Codigo;
        this.CodigoStatus = CodigoStatus;
        this.Nome = Nome;
        this.Endereco = Endereco;
        this.Email = Email;
        this.DataCadastro = DataCadastro;
        this.Telefone = Telefone;
        this.saldo = saldo;
    }

    
    public Clientes(int Codigo, int CodigoStatus, String Nome, String Endereco, String Email, String DataCadastro, int Telefone) {
        this.Codigo = Codigo;
        this.CodigoStatus = CodigoStatus;
        this.Nome = Nome;
        this.Endereco = Endereco;
        this.Email = Email;
        this.DataCadastro = DataCadastro;
        this.Telefone = Telefone;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoStatus() {
        return CodigoStatus;
    }

    public void setCodigoStatus(int CodigoStatus) {
        this.CodigoStatus = CodigoStatus;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
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

    public String getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(String DataCadastro) {
        this.DataCadastro = DataCadastro;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    
}
