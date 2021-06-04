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
public class Triagem {

    private int idTriagem;
    private String temperatura;
    private String tensao,peso, frequenciaCardiaca, pulso, guiaFactura, fumo, altura,bebe;
    private double IMC;
    private int codigoPaciente, codigoUtilizador, codigoMedico;
    private Date dataCadastro;

    public Triagem() {
    }

    public Triagem(int idTriagem, String peso, String altura, String tensao, String temperatura, String frequenciaCardiaca, String pulso, double IMC, int codigoPaciente, int codigoUtilizador, Date dataCadastro) {
        this.idTriagem = idTriagem;
        this.peso = peso;
        this.altura = altura;
        this.tensao = tensao;
        this.temperatura = temperatura;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pulso = pulso;
        this.IMC = IMC;
        this.codigoPaciente = codigoPaciente;
        this.codigoUtilizador = codigoUtilizador;
        this.dataCadastro = dataCadastro;
    }

    public Triagem(int codigo, String peso, String altura, String tensao, String fumo, String bebe, String temperatura, String pulso, double IMC) {
        this.idTriagem = codigo;
        this.peso = peso;
        this.altura = altura;
        this.tensao = tensao;
        this.fumo = fumo;
        this.bebe = bebe;
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.IMC = IMC;

    }

    public int getIdTriagem() {
        return idTriagem;
    }

    public void setIdTriagem(int idTriagem) {
        this.idTriagem = idTriagem;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public String getGuiaFactura() {
        return guiaFactura;
    }

    public void setGuiaFactura(String guiaFactura) {
        this.guiaFactura = guiaFactura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(String frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFumo() {
        return fumo;
    }

    public void setFumo(String fumo) {
        this.fumo = fumo;
    }

    public String getBebe() {
        return bebe;
    }

    public void setBebe(String bebe) {
        this.bebe = bebe;
    }

}
