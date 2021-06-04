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
public class HistorialClinico {

    private long idHistorialClinico;
    private int codigoconsulta, codigoPaciente, codigoDoenca;
    private String queixaPrincipal, exameFisico, historialDoenca, apf,cabeca,pescoco,torax,abdomem,orgaogenital,membroinferior;
    private String hipoteseDiagnostico;
    private int codigoCids, codigoMedico;
    private Date dataAtendimento;

    public HistorialClinico() {
    }

    public HistorialClinico(long idHistorialClinico, int codigoconsulta, String queixaPrincipal, String exameFisico, String historialDoenca, String hipoteseDiagnostico, String apf) {
        this.idHistorialClinico = idHistorialClinico;
        this.codigoconsulta = codigoconsulta;
        this.queixaPrincipal = queixaPrincipal;
        this.exameFisico = exameFisico;
        this.historialDoenca = historialDoenca;
        this.hipoteseDiagnostico = hipoteseDiagnostico;
        this.apf = apf;

    }

    public HistorialClinico(String queixaPrincipal, String historialDoenca, String exameFisico, String hipoteseDiagnostico, String apf,String cabeca,String pescoco,String torax,String abdomem,String orgaogenital,String membroinferior) {
        this.queixaPrincipal = queixaPrincipal;
        this.historialDoenca = historialDoenca;
        this.exameFisico = exameFisico;
        this.hipoteseDiagnostico = hipoteseDiagnostico;
        this.apf = apf;
        this.cabeca =cabeca;
        this.pescoco = pescoco;
        this.torax = torax;
        this.abdomem = abdomem;
        this.orgaogenital = orgaogenital;
        this.membroinferior = membroinferior;
        

    }

    public long getIdHistorialClinico() {
        return idHistorialClinico;
    }

    public void setIdHistorialClinico(long idHistorialClinico) {
        this.idHistorialClinico = idHistorialClinico;
    }

    public int getCodigoconsulta() {
        return codigoconsulta;
    }

    public String getApf() {
        return apf;
    }

    public void setApf(String apf) {
        this.apf = apf;
    }

    public void setCodigoconsulta(int codigoconsulta) {
        this.codigoconsulta = codigoconsulta;
    }

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getExameFisico() {
        return exameFisico;
    }

    public void setExameFisico(String exameFisico) {
        this.exameFisico = exameFisico;
    }

    public String getHistorialDoenca() {
        return historialDoenca;
    }

    public void setHistorialDoenca(String historialDoenca) {
        this.historialDoenca = historialDoenca;
    }

    public String getHipoteseDiagnostico() {
        return hipoteseDiagnostico;
    }

    public void setHipoteseDiagnostico(String hipoteseDiagnostico) {
        this.hipoteseDiagnostico = hipoteseDiagnostico;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoCids() {
        return codigoCids;
    }

    public void setCodigoCids(int codigoCids) {
        this.codigoCids = codigoCids;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public int getCodigoDoenca() {
        return codigoDoenca;
    }

    public void setCodigoDoenca(int codigoDoenca) {
        this.codigoDoenca = codigoDoenca;
    }

    public String getCabeca() {
        return cabeca;
    }

    public void setCabeca(String cabeca) {
        this.cabeca = cabeca;
    }

    public String getPescoco() {
        return pescoco;
    }

    public void setPescoco(String pescoco) {
        this.pescoco = pescoco;
    }

    public String getTorax() {
        return torax;
    }

    public void setTorax(String torax) {
        this.torax = torax;
    }

    public String getAbdomem() {
        return abdomem;
    }

    public void setAbdomem(String abdomem) {
        this.abdomem = abdomem;
    }

    public String getOrgaogenital() {
        return orgaogenital;
    }

    public void setOrgaogenital(String orgaogenital) {
        this.orgaogenital = orgaogenital;
    }

    public String getMembroinferior() {
        return membroinferior;
    }

    public void setMembroinferior(String membroinferior) {
        this.membroinferior = membroinferior;
    }
    

}
