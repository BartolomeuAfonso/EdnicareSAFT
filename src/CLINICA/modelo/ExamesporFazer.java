/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Desenvolvimento
 */
public class ExamesporFazer {

    private int codigo;
    private int codigoUtilizador, codigoPagamento, codigoHistorico;
    private String dataPedido, prescricao;
    private int codigoMedico;
    private int codigoPaciente;
    private int quantidade;
    private String pacienteInterno,colaborador,cids;

    public ExamesporFazer() {
    }

    public ExamesporFazer(int codigoUtilizador, String dataPedido, int codigoHistorico, String pacienteInterno) {
        this.codigoPagamento = codigoPagamento;
        this.codigoUtilizador = codigoUtilizador;
        this.dataPedido = dataPedido;
        this.codigoHistorico = codigoHistorico;
        this.pacienteInterno = pacienteInterno;
    }

    public ExamesporFazer(int codigoPaciente, int codigoUtilizador, String dataPedido) {

        this.codigoUtilizador = codigoUtilizador;
        this.dataPedido = dataPedido;

    }

    public int getCodigoHistorico() {
        return codigoHistorico;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public void setCodigoHistorico(int codigoHistorico) {
        this.codigoHistorico = codigoHistorico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoPagamento() {
        return codigoPagamento;
    }

    public void setCodigoPagamento(int codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getPacienteInterno() {
        return pacienteInterno;
    }

    public void setPacienteInterno(String pacienteInterno) {
        this.pacienteInterno = pacienteInterno;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }
    
}
