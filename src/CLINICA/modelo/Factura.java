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
public class Factura {

    private int idFactura;
    private String nomeCliente, vaorporExtenso, hora, nif, endereco, telefone, estado;
    private Double valorNumerario, valorMulticaixa, valorApagar, desconto, troco, totalFactura, descontoIVA;
    private Date dataFactura;
    private int quantidadeItens, Numerador, codigoCliente, codigoUtilizador, codigoFormaPagamento, codigoSeguro;
    private Moeda moeda;
    private String dataVencimento, NextFactura;
    private String emailCliente;
    private String contaCorrente;
    private String nEcomenda;
    private String moradaCliente;
    private String telefoneCliente;
    private String dataViagem, origemViagem, destinoViagem, numContetor, motorista, viatura, nIBAN;
    private String numProcesso, facturaReferente, pesoBruto, designacaoMercadoria, docTransporte, numOrdem, aviaoNavio, delegacaoAduaneira;
    private double quantidadeServico;
    private double valorCambio, SubTotal, valorAduaneiro, descontoFactura;
    Utente utente = new Utente();

    public Factura() {
    }

    public Factura(int idFactura, String nomeCliente, String vaorporExtenso, Double valorNumerario, Double valorMulticaixa, Double valorApagar, Double desconto, Double troco, Double totalFactura, Date dataFactura, int quantidadeItens, int codigoCliente, int codigoUtilizador, int codigoFormaPagamento) {
        this.idFactura = idFactura;
        this.nomeCliente = nomeCliente;
        this.vaorporExtenso = vaorporExtenso;
        this.valorNumerario = valorNumerario;
        this.valorMulticaixa = valorMulticaixa;
        this.valorApagar = valorApagar;
        this.desconto = desconto;
        this.troco = troco;
        this.totalFactura = totalFactura;
        this.dataFactura = dataFactura;
        this.quantidadeItens = quantidadeItens;
        this.codigoCliente = codigoCliente;
        this.codigoUtilizador = codigoUtilizador;
        this.codigoFormaPagamento = codigoFormaPagamento;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getNextFactura() {
        return NextFactura;
    }

    public void setNextFactura(String NextFactura) {
        this.NextFactura = NextFactura;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getVaorporExtenso() {
        return vaorporExtenso;
    }

    public int getNumerador() {
        return Numerador;
    }

    public void setNumerador(int Numerador) {
        this.Numerador = Numerador;
    }

    public void setVaorporExtenso(String vaorporExtenso) {
        this.vaorporExtenso = vaorporExtenso;
    }

    public Double getValorNumerario() {
        return valorNumerario;
    }

    public void setValorNumerario(Double valorNumerario) {
        this.valorNumerario = valorNumerario;
    }

    public Double getValorMulticaixa() {
        return valorMulticaixa;
    }

    public void setValorMulticaixa(Double valorMulticaixa) {
        this.valorMulticaixa = valorMulticaixa;
    }

    public Double getValorApagar() {
        return valorApagar;
    }

    public void setValorApagar(Double valorApagar) {
        this.valorApagar = valorApagar;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }

    public void setCodigoFormaPagamento(int codigoFormaPagamento) {
        this.codigoFormaPagamento = codigoFormaPagamento;
    }

    public int getCodigoSeguro() {
        return codigoSeguro;
    }

    public void setCodigoSeguro(int codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getMoradaCliente() {
        return moradaCliente;
    }

    public void setMoradaCliente(String moradaCliente) {
        this.moradaCliente = moradaCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getOrigemViagem() {
        return origemViagem;
    }

    public void setOrigemViagem(String origemViagem) {
        this.origemViagem = origemViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public void setDestinoViagem(String destinoViagem) {
        this.destinoViagem = destinoViagem;
    }

    public String getNumContetor() {
        return numContetor;
    }

    public void setNumContetor(String numContetor) {
        this.numContetor = numContetor;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getViatura() {
        return viatura;
    }

    public void setViatura(String viatura) {
        this.viatura = viatura;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public String getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(String pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getDesignacaoMercadoria() {
        return designacaoMercadoria;
    }

    public void setDesignacaoMercadoria(String designacaoMercadoria) {
        this.designacaoMercadoria = designacaoMercadoria;
    }

    public String getDocTransporte() {
        return docTransporte;
    }

    public void setDocTransporte(String docTransporte) {
        this.docTransporte = docTransporte;
    }

    public String getNumOrdem() {
        return numOrdem;
    }

    public void setNumOrdem(String numOrdem) {
        this.numOrdem = numOrdem;
    }

    public String getAviaoNavio() {
        return aviaoNavio;
    }

    public void setAviaoNavio(String aviaoNavio) {
        this.aviaoNavio = aviaoNavio;
    }

    public String getDelegacaoAduaneira() {
        return delegacaoAduaneira;
    }

    public void setDelegacaoAduaneira(String delegacaoAduaneira) {
        this.delegacaoAduaneira = delegacaoAduaneira;
    }

    public double getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(double quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public double getValorCambio() {
        return valorCambio;
    }

    public void setValorCambio(double valorCambio) {
        this.valorCambio = valorCambio;
    }

    public double getValorAduaneiro() {
        return valorAduaneiro;
    }

    public void setValorAduaneiro(double valorAduaneiro) {
        this.valorAduaneiro = valorAduaneiro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getDescontoIVA() {
        return descontoIVA;
    }

    public void setDescontoIVA(Double descontoIVA) {
        this.descontoIVA = descontoIVA;
    }

    public String getnEcomenda() {
        return nEcomenda;
    }

    public void setnEcomenda(String nEcomenda) {
        this.nEcomenda = nEcomenda;
    }

    public String getnIBAN() {
        return nIBAN;
    }

    public void setnIBAN(String nIBAN) {
        this.nIBAN = nIBAN;
    }

    public double getDescontoFactura() {
        return descontoFactura;
    }

    public void setDescontoFactura(double descontoFactura) {
        this.descontoFactura = descontoFactura;
    }

    public String getFacturaReferente() {
        return facturaReferente;
    }

    public void setFacturaReferente(String facturaReferente) {
        this.facturaReferente = facturaReferente;
    }

}
