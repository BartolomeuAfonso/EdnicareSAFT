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
public class JustificativoMedico {
    
    private long idJustificativo;
    private String designacao,grau;
    private String acompanhante;
    private int codigoMedico, codigoPaciente;
    private int diaReposuo;
    private int codigoCids;
    private Date dataConsulta;
    private String datafim;

    public JustificativoMedico() {
    }

    public JustificativoMedico(long idJustificativo, int codigoMedico, int codigoPaciente, int diaReposuo, int codigoCids, Date dataConsulta) {
        this.idJustificativo = idJustificativo;
        this.codigoMedico = codigoMedico;
        this.codigoPaciente = codigoPaciente;
        this.diaReposuo = diaReposuo;
        this.codigoCids = codigoCids;
        this.dataConsulta = dataConsulta;
    }

    public long getIdJustificativo() {
        return idJustificativo;
    }

    public void setIdJustificativo(long idJustificativo) {
        this.idJustificativo = idJustificativo;
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

    public int getDiaReposuo() {
        return diaReposuo;
    }

    public void setDiaReposuo(int diaReposuo) {
        this.diaReposuo = diaReposuo;
    }

    public int getCodigoCids() {
        return codigoCids;
    }

    public void setCodigoCids(int codigoCids) {
        this.codigoCids = codigoCids;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(String acompanhante) {
        this.acompanhante = acompanhante;
    }

    public String getDatafim() {
        return datafim;
    }

    public void setDatafim(String datafim) {
        this.datafim = datafim;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }
    
    
    
    
}
