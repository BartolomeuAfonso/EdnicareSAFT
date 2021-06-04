/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author Familia do Fresco
 */
public class Receita {

    private int codigo, codigoPaciente, codigoMedico, codigoHistorico;
    private String designacao;
    private Date data;

    public Receita() {
    }

    public Receita(int codigo, int codigoPaciente, int codigoMedico, String designacao) {
        this.codigo = codigo;
        this.codigoPaciente = codigoPaciente;
        this.codigoMedico = codigoMedico;
        this.designacao = designacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getCodigoHistorico() {
        return codigoHistorico;
    }

    public void setCodigoHistorico(int codigoHistorico) {
        this.codigoHistorico = codigoHistorico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
