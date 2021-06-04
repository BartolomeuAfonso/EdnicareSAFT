/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author Agostinho
 */
public class FechoCaixa {

    private int Codigo, CodigoCaixa,CodigoUtilizador ;
    private Date dataFecho;
    private String Descricao, HoraFecho;
    private Double ValorAbertura, ValorFecho, valorCash, valorMulticaixa;

    public FechoCaixa() {
    }

    public FechoCaixa(int Codigo, int CodigoCaixa, int CodigoUtilizador, String Descricao, Date dataFecho, String HoraFecho, Double ValorAbertura, Double ValorFecho, Double valorCash, Double ValorMulticaixa) {
        this.Codigo = Codigo;
        this.CodigoCaixa = CodigoCaixa;
        this.CodigoUtilizador = CodigoUtilizador;
        this.Descricao = Descricao;
        this.dataFecho = dataFecho;
        //   this.HoraAbertura = HoraAbertura;
        this.HoraFecho = HoraFecho;
        this.ValorAbertura = ValorAbertura;
        this.ValorFecho = ValorFecho;
        this.valorCash = valorCash;
        this.valorMulticaixa = ValorMulticaixa;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoCaixa() {
        return CodigoCaixa;
    }

    public void setCodigoCaixa(int CodigoCaixa) {
        this.CodigoCaixa = CodigoCaixa;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Date getDataFecho() {
        return dataFecho;
    }

    public void setDataFecho(Date dataFecho) {
        this.dataFecho = dataFecho;
    }



    public String getHoraFecho() {
        return HoraFecho;
    }

    public void setHoraFecho(String HoraFecho) {
        this.HoraFecho = HoraFecho;
    }

    public Double getValorAbertura() {
        return ValorAbertura;
    }

    public void setValorAbertura(Double ValorAbertura) {
        this.ValorAbertura = ValorAbertura;
    }

    public Double getValorFecho() {
        return ValorFecho;
    }

    public void setValorFecho(Double ValorFecho) {
        this.ValorFecho = ValorFecho;
    }

    public Double getValorCash() {
        return valorCash;
    }

    public void setValorCash(Double valorCash) {
        this.valorCash = valorCash;
    }

    public Double getValorMulticaixa() {
        return valorMulticaixa;
    }

    public void setValorMulticaixa(Double valorMulticaixa) {
        this.valorMulticaixa = valorMulticaixa;
    }

    public int getCodigoUtilizador() {
        return CodigoUtilizador;
    }

    public void setCodigoUtilizador(int CodigoUtilizador) {
        this.CodigoUtilizador = CodigoUtilizador;
    }

    
}
