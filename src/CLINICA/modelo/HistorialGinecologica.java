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
public class HistorialGinecologica {

    private long idHistoricaGinecologia;
    private int codigoconsulta, codigoPaciente, codigoDoenca;
    private String queixaPrincipal, motivo;
    private String menarca, ciclosMenstruais, Dismenorreia, intensidadeFluxo, DUM, Premenstruais, tratamentoGinecologico, DTS;
    private String actividaSexual, n_parceiro, tempo, metodoConceptivo, libimoOrgasmo, menoPausa, sintomaCliterico, THR;

    public HistorialGinecologica() {
    }

    public HistorialGinecologica(long idHistoricaGinecologia, int codigoconsulta, int codigoPaciente, int codigoDoenca, String queixaPrincipal, String motivo, String menarca, String ciclosMenstruais, String Dismenorreia, String intensidadeFluxo, String DUM, String Premenstruais, String tratamentoGinecologico, String DTS, String actividaSexual, String n_parceiro, String tempo, String metodoConceptivo, String libimoOrgasmo, String menoPausa, String sintomaCliterico, String THR) {
        this.idHistoricaGinecologia = idHistoricaGinecologia;
        this.codigoconsulta = codigoconsulta;
        this.codigoPaciente = codigoPaciente;
        this.codigoDoenca = codigoDoenca;
        this.queixaPrincipal = queixaPrincipal;
        this.motivo = motivo;
        this.menarca = menarca;
        this.ciclosMenstruais = ciclosMenstruais;
        this.Dismenorreia = Dismenorreia;
        this.intensidadeFluxo = intensidadeFluxo;
        this.DUM = DUM;
        this.Premenstruais = Premenstruais;
        this.tratamentoGinecologico = tratamentoGinecologico;
        this.DTS = DTS;
        this.actividaSexual = actividaSexual;
        this.n_parceiro = n_parceiro;
        this.tempo = tempo;
        this.metodoConceptivo = metodoConceptivo;
        this.libimoOrgasmo = libimoOrgasmo;
        this.menoPausa = menoPausa;
        this.sintomaCliterico = sintomaCliterico;
        this.THR = THR;
    }

    public HistorialGinecologica(int codigoconsulta, int codigoPaciente, int codigoDoenca, String queixaPrincipal, String motivo, String menarca, String ciclosMenstruais, String Dismenorreia, String intensidadeFluxo, String DUM, String Premenstruais, String tratamentoGinecologico, String DTS, String actividaSexual, String n_parceiro, String tempo, String metodoConceptivo, String libimoOrgasmo, String menoPausa, String sintomaCliterico, String THR) {
        this.codigoconsulta = codigoconsulta;
        this.codigoPaciente = codigoPaciente;
        this.codigoDoenca = codigoDoenca;
        this.queixaPrincipal = queixaPrincipal;
        this.motivo = motivo;
        this.menarca = menarca;
        this.ciclosMenstruais = ciclosMenstruais;
        this.Dismenorreia = Dismenorreia;
        this.intensidadeFluxo = intensidadeFluxo;
        this.DUM = DUM;
        this.Premenstruais = Premenstruais;
        this.tratamentoGinecologico = tratamentoGinecologico;
        this.DTS = DTS;
        this.actividaSexual = actividaSexual;
        this.n_parceiro = n_parceiro;
        this.tempo = tempo;
        this.metodoConceptivo = metodoConceptivo;
        this.libimoOrgasmo = libimoOrgasmo;
        this.menoPausa = menoPausa;
        this.sintomaCliterico = sintomaCliterico;
        this.THR = THR;
    }

    public long getIdHistoricaGinecologia() {
        return idHistoricaGinecologia;
    }

    public void setIdHistoricaGinecologia(long idHistoricaGinecologia) {
        this.idHistoricaGinecologia = idHistoricaGinecologia;
    }

    public int getCodigoconsulta() {
        return codigoconsulta;
    }

    public void setCodigoconsulta(int codigoconsulta) {
        this.codigoconsulta = codigoconsulta;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoDoenca() {
        return codigoDoenca;
    }

    public void setCodigoDoenca(int codigoDoenca) {
        this.codigoDoenca = codigoDoenca;
    }

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMenarca() {
        return menarca;
    }

    public void setMenarca(String menarca) {
        this.menarca = menarca;
    }

    public String getCiclosMenstruais() {
        return ciclosMenstruais;
    }

    public void setCiclosMenstruais(String ciclosMenstruais) {
        this.ciclosMenstruais = ciclosMenstruais;
    }

    public String getDismenorreia() {
        return Dismenorreia;
    }

    public void setDismenorreia(String Dismenorreia) {
        this.Dismenorreia = Dismenorreia;
    }

    public String getIntensidadeFluxo() {
        return intensidadeFluxo;
    }

    public void setIntensidadeFluxo(String intensidadeFluxo) {
        this.intensidadeFluxo = intensidadeFluxo;
    }

    public String getDUM() {
        return DUM;
    }

    public void setDUM(String DUM) {
        this.DUM = DUM;
    }

    public String getPremenstruais() {
        return Premenstruais;
    }

    public void setPremenstruais(String Premenstruais) {
        this.Premenstruais = Premenstruais;
    }

    public String getTratamentoGinecologico() {
        return tratamentoGinecologico;
    }

    public void setTratamentoGinecologico(String tratamentoGinecologico) {
        this.tratamentoGinecologico = tratamentoGinecologico;
    }

    public String getDTS() {
        return DTS;
    }

    public void setDTS(String DTS) {
        this.DTS = DTS;
    }

    public String getActividaSexual() {
        return actividaSexual;
    }

    public void setActividaSexual(String actividaSexual) {
        this.actividaSexual = actividaSexual;
    }

    public String getN_parceiro() {
        return n_parceiro;
    }

    public void setN_parceiro(String n_parceiro) {
        this.n_parceiro = n_parceiro;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getMetodoConceptivo() {
        return metodoConceptivo;
    }

    public void setMetodoConceptivo(String metodoConceptivo) {
        this.metodoConceptivo = metodoConceptivo;
    }

    public String getLibimoOrgasmo() {
        return libimoOrgasmo;
    }

    public void setLibimoOrgasmo(String libimoOrgasmo) {
        this.libimoOrgasmo = libimoOrgasmo;
    }

    public String getMenoPausa() {
        return menoPausa;
    }

    public void setMenoPausa(String menoPausa) {
        this.menoPausa = menoPausa;
    }

    public String getSintomaCliterico() {
        return sintomaCliterico;
    }

    public void setSintomaCliterico(String sintomaCliterico) {
        this.sintomaCliterico = sintomaCliterico;
    }

    public String getTHR() {
        return THR;
    }

    public void setTHR(String THR) {
        this.THR = THR;
    }

}
