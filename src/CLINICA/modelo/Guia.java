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
public class Guia {

    private int idGuia, numerador;
    private String dataGuia;
    private String nomecliente, elegilibidade, estado;
    private int codigoCliente, codigoUser, codigoFormaPagamento;
    private int codigoSeguro;
    private double valorApagar, desconto, descontoFactura;
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

    public Guia() {
    }

    public Guia(int idGuia, String dataGuia, String nomecliente, String elegilibidade, int codigoUser, int codigoCliente, int codigoFormaPagamento, double valorApagar) {
        this.idGuia = idGuia;
        this.dataGuia = dataGuia;
        this.nomecliente = nomecliente;
        this.elegilibidade = elegilibidade;
        this.codigoCliente = codigoCliente;
        this.codigoUser = codigoUser;
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.valorApagar = valorApagar;
    }

    public Guia(int idGuia, int numerador, String dataGuia, String nomecliente, String elegilibidade, int codigoCliente, int codigoUser, int codigoFormaPagamento, double valorApagar) {
        this.idGuia = idGuia;
        this.numerador = numerador;
        this.dataGuia = dataGuia;
        this.nomecliente = nomecliente;
        this.elegilibidade = elegilibidade;
        this.codigoCliente = codigoCliente;
        this.codigoUser = codigoUser;
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.valorApagar = valorApagar;
    }

    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public String getDataGuia() {
        return dataGuia;
    }

    public void setDataGuia(String dataGuia) {
        this.dataGuia = dataGuia;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getElegilibidade() {
        return elegilibidade;
    }

    public void setElegilibidade(String elegilibidade) {
        this.elegilibidade = elegilibidade;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }

    public void setCodigoFormaPagamento(int codigoFormaPagamento) {
        this.codigoFormaPagamento = codigoFormaPagamento;
    }

    public double getValorApagar() {
        return valorApagar;
    }

    public void setValorApagar(double valorApagar) {
        this.valorApagar = valorApagar;
    }

    public int getCodigoUser() {
        return codigoUser;
    }

    public void setCodigoUser(int codigoUser) {
        this.codigoUser = codigoUser;
    }

    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getCodigoSeguro() {
        return codigoSeguro;
    }

    public void setCodigoSeguro(int codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
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

    public double getDescontoFactura() {
        return descontoFactura;
    }

    public void setDescontoFactura(double descontoFactura) {
        this.descontoFactura = descontoFactura;
    }

}
