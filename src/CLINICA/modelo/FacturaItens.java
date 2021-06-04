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
public class FacturaItens {

    private int codigoFactura, codigoServico, codigoCategoriaServico, quantidade;
    private Double totalGeral, totalTPA, descontoIVA;
    private Double preco, descontoProduto,percentagem, laboratorio, consulta, raioX, ecografia, ecografia5, ecografia7, ecografiaMorfologia, ecocardiograma, electrocardiograma;

    public FacturaItens() {
    }

    public FacturaItens(int codigoFactura, int codigoServico, int codigoCategoriaServico, int quantidade, Double totalGeral) {
        this.codigoFactura = codigoFactura;
        this.codigoServico = codigoServico;
        this.codigoCategoriaServico = codigoCategoriaServico;
        this.quantidade = quantidade;
        this.totalGeral = totalGeral;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoCategoriaServico() {
        return codigoCategoriaServico;
    }

    public void setCodigoCategoriaServico(int codigoCategoriaServico) {
        this.codigoCategoriaServico = codigoCategoriaServico;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(Double totalGeral) {
        this.totalGeral = totalGeral;
    }

    public Double getTotalTPA() {
        return totalTPA;
    }

    public void setTotalTPA(Double totalTPA) {
        this.totalTPA = totalTPA;
    }

    public Double getDescontoIVA() {
        return descontoIVA;
    }

    public void setDescontoIVA(Double descontoIVA) {
        this.descontoIVA = descontoIVA;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(Double percentagem) {
        this.percentagem = percentagem;
    }

    public Double getDescontoProduto() {
        return descontoProduto;
    }

    public void setDescontoProduto(Double descontoProduto) {
        this.descontoProduto = descontoProduto;
    }

    public Double getRaioX() {
        return raioX;
    }

    public void setRaioX(Double raioX) {
        this.raioX = raioX;
    }

    public Double getEcografia() {
        return ecografia;
    }

    public void setEcografia(Double ecografia) {
        this.ecografia = ecografia;
    }

    public Double getEcografia7() {
        return ecografia7;
    }

    public void setEcografia7(Double ecografia7) {
        this.ecografia7 = ecografia7;
    }

    public Double getEcografiaMorfologia() {
        return ecografiaMorfologia;
    }

    public void setEcografiaMorfologia(Double ecografiaMorfologia) {
        this.ecografiaMorfologia = ecografiaMorfologia;
    }

    public Double getEcocardiograma() {
        return ecocardiograma;
    }

    public void setEcocardiograma(Double ecocardiograma) {
        this.ecocardiograma = ecocardiograma;
    }

    public Double getElectrocardiograma() {
        return electrocardiograma;
    }

    public void setElectrocardiograma(Double electrocardiograma) {
        this.electrocardiograma = electrocardiograma;
    }

    public Double getConsulta() {
        return consulta;
    }

    public void setConsulta(Double consulta) {
        this.consulta = consulta;
    }

    public Double getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Double laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Double getEcografia10() {
        return ecografia5;
    }

    public void setEcografia10(Double ecografia5) {
        this.ecografia5 = ecografia5;
    }

}
