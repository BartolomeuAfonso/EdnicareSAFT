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
public class MarcacaoConsulta {

    private long idMarcacao;
    private int codigoMedico, codigoPaciente, codigoEstado, codigoUtlizador, codigoServico;
    private Date dataAtendimento;
    private String observacao;
    private String dataMarcacao;
    private String estado;
    private String hora;
    private double preco;
    private String pago;

    public MarcacaoConsulta() {
    }

    public MarcacaoConsulta(long idMarcacao, int codigoMedico, int codigoPaciente, int codigoUtlizador, String dataMarcacao, Date dataAtendimento, String observacao) {
        this.idMarcacao = idMarcacao;
        this.codigoMedico = codigoMedico;
        this.codigoPaciente = codigoPaciente;
        this.codigoUtlizador = codigoUtlizador;
        this.dataMarcacao = dataMarcacao;
        this.dataAtendimento = dataAtendimento;
        this.observacao = observacao;
        this.estado = estado;
    }

    public long getIdMarcacao() {
        return idMarcacao;
    }

    public void setIdMarcacao(long idMarcacao) {
        this.idMarcacao = idMarcacao;
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

    public int getCodigoUtlizador() {
        return codigoUtlizador;
    }

    public void setCodigoUtlizador(int codigoUtlizador) {
        this.codigoUtlizador = codigoUtlizador;
    }

    public String getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(String dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

}
