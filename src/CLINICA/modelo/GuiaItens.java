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
public class GuiaItens {

    private int idGuiaItens;
    private int codigoGuia, codigoServico,codigoCategoriaServico;
    private int quantidade;
    private double totalgeral, desconto,DescontoIVA, preco;
    private String elegilibidade;

    public GuiaItens() {
    }

    public GuiaItens(int idGuiaItens, int codigoGuia, int codigoServico,int codigoCategoriaServico, int quantidade, double totalgeral, String elegilibidade) {
        this.idGuiaItens = idGuiaItens;
        this.codigoGuia = codigoGuia;
        this.codigoServico = codigoServico;
        this.quantidade = quantidade;
        this.codigoCategoriaServico = codigoCategoriaServico;
        this.totalgeral = totalgeral;
        this.elegilibidade = elegilibidade;
    }

    public int getIdGuiaItens() {
        return idGuiaItens;
    }

    public void setIdGuiaItens(int idGuiaItens) {
        this.idGuiaItens = idGuiaItens;
    }

    public int getCodigoGuia() {
        return codigoGuia;
    }

    public void setCodigoGuia(int codigoGuia) {
        this.codigoGuia = codigoGuia;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotalgeral() {
        return totalgeral;
    }

    public void setTotalgeral(double totalgeral) {
        this.totalgeral = totalgeral;
    }

    public String getElegilibidade() {
        return elegilibidade;
    }

    public void setElegilibidade(String elegilibidade) {
        this.elegilibidade = elegilibidade;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
     public int getCodigoCategoriaServico() {
        return codigoCategoriaServico;
    }

    public void setCodigoCategoriaServico(int codigoCategoriaServico) {
        this.codigoCategoriaServico = codigoCategoriaServico;
    }

    public double getDescontoIVA() {
        return DescontoIVA;
    }

    public void setDescontoIVA(double DescontoIVA) {
        this.DescontoIVA = DescontoIVA;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    

}
