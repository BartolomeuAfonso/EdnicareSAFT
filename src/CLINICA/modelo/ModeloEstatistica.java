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
public class ModeloEstatistica {

    private int idestatistica;
    private String designacao, codigoFactura,percentagem;
    private int codigoServico, codigoPaciente;
    private int particular, advance, unisaude, saudemais, fidelidade, masterplus, saham, angolatecom, empresa;

    public ModeloEstatistica() {
    }

    public ModeloEstatistica(String designacao, int codigoServico, int particular, int advance, int unisaude, int saudemais, int fidelidade, int masterplus, int saham, int angolatecom, int empresa, int idestatistica) {
        this.designacao = designacao;
        this.codigoServico = codigoServico;
        this.particular = particular;
        this.advance = advance;
        this.unisaude = unisaude;
        this.saudemais = saudemais;
        this.fidelidade = fidelidade;
        this.masterplus = masterplus;
        this.saham = saham;
        this.angolatecom = angolatecom;
        this.empresa = empresa;
        this.idestatistica = idestatistica;
    }

    public int getIdestatistica() {
        return idestatistica;
    }

    public void setIdestatistica(int idestatistica) {
        this.idestatistica = idestatistica;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getParticular() {
        return particular;
    }

    public void setParticular(int particular) {
        this.particular = particular;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public int getUnisaude() {
        return unisaude;
    }

    public void setUnisaude(int unisaude) {
        this.unisaude = unisaude;
    }

    public int getSaudemais() {
        return saudemais;
    }

    public void setSaudemais(int saudemais) {
        this.saudemais = saudemais;
    }

    public int getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(int fidelidade) {
        this.fidelidade = fidelidade;
    }

    public int getMasterplus() {
        return masterplus;
    }

    public void setMasterplus(int masterplus) {
        this.masterplus = masterplus;
    }

    public int getSaham() {
        return saham;
    }

    public void setSaham(int saham) {
        this.saham = saham;
    }

    public int getAngolatecom() {
        return angolatecom;
    }

    public void setAngolatecom(int angolatecom) {
        this.angolatecom = angolatecom;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(String percentagem) {
        this.percentagem = percentagem;
    }

}
