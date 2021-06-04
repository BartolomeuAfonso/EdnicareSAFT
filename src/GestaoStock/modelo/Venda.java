/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

import CLINICA.modelo.Moeda;

/**
 *
 * @author El Router
 */
public class Venda {

    private Long idVenda;
    private String dataVenda;
    private int tipoFaturacao;//Tipos são(Internos e Externos).
    private int codBeneficiario;//Paciente(Interno,particular ou parente).
    private int codUtilizador;//Usuário do sistema.
    private int formaPagamento;//Numerário,multicaixa.
    private double valorVenda;
    private double desconto;
    private double valorTotal;
    private int statusVenda;
    private double valorEntregue, numerario, multicaixa;
    private int tipoVenda, quantidadeItens;
    private double troco;
    private String hora, NomeCliente, valorporExtenso;
    private Moeda moeda;
    private String dataVencimento;
    private String emailCliente;
    private String contaCorrente;
    private String moradaCliente;
    private String telefoneCliente;
    private String dataViagem, origemViagem, destinoViagem, numContetor, motorista, viatura;
    private String numProcesso, pesoBruto, designacaoMercadoria, docTransporte, numOrdem, aviaoNavio, delegacaoAduaneira;
    private double quantidadeServico;
    private double valorCambio, valorAduaneiro;
   

    public Venda() {
    }

    public Venda(Long idVenda, String dataVenda, int tipoFaturacao, int codBeneficiario, int codUtilizador, int formaPagamento, double valorVenda, double desconto, double valorTotal, int statusVenda, double valorEntregue, int tipoVenda, double troco, String hora) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.tipoFaturacao = tipoFaturacao;
        this.codBeneficiario = codBeneficiario;
        this.codUtilizador = codUtilizador;
        this.formaPagamento = formaPagamento;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.statusVenda = statusVenda;
        this.valorEntregue = valorEntregue;
        this.tipoVenda = tipoVenda;
        this.troco = troco;
        this.hora = hora;
    }

    public Venda(String dataVenda, int tipoFaturacao, int codBeneficiario, int codUtilizador, int formaPagamento, double valorVenda, double desconto, double valorTotal, int statusVenda, double valorEntregue, int tipoVenda, double troco, String hora) {
        this.dataVenda = dataVenda;
        this.tipoFaturacao = tipoFaturacao;
        this.codBeneficiario = codBeneficiario;
        this.codUtilizador = codUtilizador;
        this.formaPagamento = formaPagamento;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.statusVenda = statusVenda;
        this.valorEntregue = valorEntregue;
        this.tipoVenda = tipoVenda;
        this.troco = troco;
        this.hora = hora;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getTipoFaturacao() {
        return tipoFaturacao;
    }

    public void setTipoFaturacao(int tipoFaturacao) {
        this.tipoFaturacao = tipoFaturacao;
    }

    public int getCodBeneficiario() {
        return codBeneficiario;
    }

    public void setCodBeneficiario(int codBeneficiario) {
        this.codBeneficiario = codBeneficiario;
    }

    public int getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(int codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public int getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(int statusVenda) {
        this.statusVenda = statusVenda;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    public void setValorEntregue(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public int getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(int tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }

    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public double getNumerario() {
        return numerario;
    }

    public void setNumerario(double numerario) {
        this.numerario = numerario;
    }

    public double getMulticaixa() {
        return multicaixa;
    }

    public void setMulticaixa(double multicaixa) {
        this.multicaixa = multicaixa;
    }

    public String getValorporExtenso() {
        return valorporExtenso;
    }

    public void setValorporExtenso(String valorporExtenso) {
        this.valorporExtenso = valorporExtenso;
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

}
