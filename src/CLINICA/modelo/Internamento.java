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
public class Internamento {

    private int codigo, codigoMedico, codigoPaciente;
    private String cama, quarto, diagnostico, acompanhamento, medicoAssistente, areaInternado;
    private String dataEntrada, dataSaida;

    public Internamento() {
    }

    
    public Internamento(int codigo, int codigoMedico, int codigoPaciente, String cama, String quarto, String diagnostico, String acompanhamento, String medicoAssistente, String dataEntrada, String dataSaida) {
        this.codigo = codigo;
        this.codigoMedico = codigoMedico;
        this.codigoPaciente = codigoPaciente;
        this.cama = cama;
        this.quarto = quarto;
        this.diagnostico = diagnostico;
        this.acompanhamento = acompanhamento;
        this.medicoAssistente = medicoAssistente;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(String acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public String getMedicoAssistente() {
        return medicoAssistente;
    }

    public void setMedicoAssistente(String medicoAssistente) {
        this.medicoAssistente = medicoAssistente;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getAreaInternado() {
        return areaInternado;
    }

    public void setAreaInternado(String areaInternado) {
        this.areaInternado = areaInternado;
    }

}
