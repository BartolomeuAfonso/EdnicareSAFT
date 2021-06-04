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
public class ExamesPorFazerItem {

    private int codigoExame, quantidade, codigoServico, codigoStatusExame, codigoExameItengrado;
    private Date dataResultado;
    private Date pedido;
    private String designacao, refrencia, resultado;

    public ExamesPorFazerItem() {
    }

    public ExamesPorFazerItem(int codigoExame, int codigoServico, int codigoStatusExame, int codigoExameItengrado, Date dataResultado, String refrencia, String resultado) {
        this.codigoExame = codigoExame;
        this.codigoServico = codigoServico;
        this.codigoStatusExame = codigoStatusExame;
        this.codigoExameItengrado = codigoExameItengrado;
        this.dataResultado = dataResultado;
        this.refrencia = refrencia;
        this.resultado = resultado;
    }

    public ExamesPorFazerItem(int codigoExame, int codigoServico, int codigoStatusExame, int codigoExameItengrado, int quantidade) {
        this.codigoExame = codigoExame;
        this.codigoServico = codigoServico;
        this.codigoStatusExame = codigoStatusExame;
        this.codigoExameItengrado = codigoExameItengrado;
        this.dataResultado = dataResultado;
        this.refrencia = refrencia;
        this.resultado = resultado;
        this.quantidade = quantidade;
    }

    public ExamesPorFazerItem(String designacao, String resultado, Date pedido, Date dataResultado, String refrencia) {

        this.designacao = designacao;
        this.dataResultado = dataResultado;
        this.pedido = pedido;
        this.refrencia = refrencia;
        this.resultado = resultado;
    }

    public int getCodigoExame() {
        return codigoExame;
    }

    public void setCodigoExame(int codigoExame) {
        this.codigoExame = codigoExame;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoStatusExame() {
        return codigoStatusExame;
    }

    public void setCodigoStatusExame(int codigoStatusExame) {
        this.codigoStatusExame = codigoStatusExame;
    }

    public int getCodigoExameItengrado() {
        return codigoExameItengrado;
    }

    public void setCodigoExameItengrado(int codigoExameItengrado) {
        this.codigoExameItengrado = codigoExameItengrado;
    }

    public Date getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
    }

    public String getRefrencia() {
        return refrencia;
    }

    public void setRefrencia(String refrencia) {
        this.refrencia = refrencia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getPedido() {
        return pedido;
    }

    public void setPedido(Date pedido) {
        this.pedido = pedido;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
