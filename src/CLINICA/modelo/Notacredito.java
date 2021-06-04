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
public class Notacredito {

    private int idFactura;
    private String nomeCliente, vaorporExtenso, hora, nif, endereco, telefone, estado;
    private Double valorNumerario, valorMulticaixa, valorApagar, desconto, troco, totalFactura, descontoIVA;
    private Date dataFactura;
    private int quantidadeItens, codigoCliente, codigoUtilizador, codigoFormaPagamento, codigoSeguro;
    private Moeda moeda;
    private String dataVencimento;
    private String emailCliente;
    private String contaCorrente;
    private String nEcomenda, HashValor;
    private String moradaCliente;
    private String telefoneCliente;
    private String dataViagem, origemViagem, nRef, destinoViagem, numContetor, motorista, viatura, nIBAN;
    private String numProcesso, Obs, Next, pesoBruto, Flag, LocalEmissao, LocalEntrega, Cabecalho, aviaoNavio, Tipo_movimento;
    private double quantidadeServico;
    private double valorCambio, subbtotal, valorAduaneiro, descontoFactura;

    public Notacredito() {
    }

    public Notacredito(int idFactura, String nomeCliente, String vaorporExtenso, Double valorNumerario, Double valorMulticaixa, Double valorApagar, Double desconto, Double troco, Double totalFactura, Date dataFactura, int quantidadeItens, int codigoCliente, int codigoUtilizador, int codigoFormaPagamento) {
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

    public String getnRef() {
        return nRef;
    }

    public void setnRef(String nRef) {
        this.nRef = nRef;
    }

    public double getSubbtotal() {
        return subbtotal;
    }

    public void setSubbtotal(double subbtotal) {
        this.subbtotal = subbtotal;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public String getObs() {
        return Obs;
    }

    public String getNext() {
        return Next;
    }

    public void setNext(String Next) {
        this.Next = Next;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getLocalEntrega() {
        return LocalEntrega;
    }

    public void setLocalEntrega(String LocalEntrega) {
        this.LocalEntrega = LocalEntrega;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getHashValor() {
        return HashValor;
    }

    public void setHashValor(String HashValor) {
        this.HashValor = HashValor;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getVaorporExtenso() {
        return vaorporExtenso;
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

    public String getLocalEmissao() {
        return LocalEmissao;
    }

    public void setLocalEmissao(String designacaoMercadoria) {
        this.LocalEmissao = designacaoMercadoria;
    }

    public String getAviaoNavio() {
        return aviaoNavio;
    }

    public void setAviaoNavio(String aviaoNavio) {
        this.aviaoNavio = aviaoNavio;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getCabecalho() {
        return Cabecalho;
    }

    public void setCabecalho(String Cabecalho) {
        this.Cabecalho = Cabecalho;
    }

    public String getTipo_movimento() {
        return Tipo_movimento;
    }

    public void setTipo_movimento(String Tipo_movimento) {
        this.Tipo_movimento = Tipo_movimento;
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

    public double getSubtotal() {
        return subbtotal;
    }

    public void setSubtotal(double subbtotal) {
        this.subbtotal = subbtotal;
    }

}
