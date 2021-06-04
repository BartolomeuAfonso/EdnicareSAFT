/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Probook
 */
public class SinaisVitais {

    private int idSinaisVitais;
    private String temperatura;
    private String tensao, frequenciaCardiaca, pulso,codigUsuario;
    private int codigoInternamento;

    public SinaisVitais() {
    }

    public SinaisVitais(int idSinaisVitais, String temperatura, String tensao, String frequenciaCardiaca, String pulso, int codigoInternamento) {
        this.idSinaisVitais = idSinaisVitais;
        this.temperatura = temperatura;
        this.tensao = tensao;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pulso = pulso;
        this.codigoInternamento = codigoInternamento;
    }

    public int getIdSinaisVitais() {
        return idSinaisVitais;
    }

    public void setIdSinaisVitais(int idSinaisVitais) {
        this.idSinaisVitais = idSinaisVitais;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
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

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public int getCodigoInternamento() {
        return codigoInternamento;
    }

    public void setCodigoInternamento(int codigoInternamento) {
        this.codigoInternamento = codigoInternamento;
    }

    public String getCodigUsuario() {
        return codigUsuario;
    }

    public void setCodigUsuario(String codigUsuario) {
        this.codigUsuario = codigUsuario;
    }

}
