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
public class RelatorioMedicos {

    private int codigo, codigoMedico, codigoPaciente;
    String laboratorio, raioX, ecografia, ecg, ecocardiograma, diagnostico, conduta;
    String hda, examegeral;
    String descricao;

    public RelatorioMedicos() {
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

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getRaioX() {
        return raioX;
    }

    public void setRaioX(String raioX) {
        this.raioX = raioX;
    }

    public String getEcografia() {
        return ecografia;
    }

    public void setEcografia(String ecografia) {
        this.ecografia = ecografia;
    }

    public String getEcg() {
        return ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public String getEcocardiograma() {
        return ecocardiograma;
    }

    public void setEcocardiograma(String ecocardiograma) {
        this.ecocardiograma = ecocardiograma;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getConduta() {
        return conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public String getHda() {
        return hda;
    }

    public void setHda(String hda) {
        this.hda = hda;
    }

    public String getExamegeral() {
        return examegeral;
    }

    public void setExamegeral(String examegeral) {
        this.examegeral = examegeral;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
