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
public class FolhaHonorario {

    private int id;
    private String designacao;
    private Double precoNormal, IRT, impostoSelo, valorReceber;
    private Double percentagem;
    private int codigoEmpresa, codigoServico;

    public FolhaHonorario() {
    }

    public FolhaHonorario(int id, String designacao, Double precoNormal, Double IRT, Double impostoSelo, Double valorReceber, Double percentagem, int codigoEmpresa) {
        this.id = id;
        this.designacao = designacao;
        this.precoNormal = precoNormal;
        this.IRT = IRT;
        this.impostoSelo = impostoSelo;
        this.impostoSelo = impostoSelo;
        this.valorReceber = valorReceber;
        this.percentagem = percentagem;
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Double getPrecoNormal() {
        return precoNormal;
    }

    public void setPrecoNormal(Double precoNormal) {
        this.precoNormal = precoNormal;
    }

    public Double getIRT() {
        return IRT;
    }

    public void setIRT(Double IRT) {
        this.IRT = IRT;
    }

    public Double getImpostoSelo() {
        return impostoSelo;
    }

    public void setImpostoSelo(Double impostoSelo) {
        this.impostoSelo = impostoSelo;
    }

    public Double getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(Double percentagem) {
        this.percentagem = percentagem;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(Double valorReceber) {
        this.valorReceber = valorReceber;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

}
