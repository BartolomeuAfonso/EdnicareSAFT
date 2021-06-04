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
public class AberturaCaixa {

    private int Codigo, CodigoCaixa, CodigoUtilizador;
    private String Descricao;
    private String statusCaixa;
    private Date dataAbertura;
    private String HoraAbertura;
    private Double ValorAbertura;
   
    public AberturaCaixa() {
    }

    public AberturaCaixa(int Codigo, int CodigoCaixa, int CodigoUtilizador, String Descricao, String statusCaixa, String HoraAbertura, Date dataAbertura, Double ValorAbertura) {
        this.Codigo = Codigo;
        this.CodigoCaixa = CodigoCaixa;
        this.CodigoUtilizador = CodigoUtilizador;
        this.Descricao = Descricao;
        this.statusCaixa = statusCaixa;
        this.HoraAbertura = HoraAbertura;
        this.ValorAbertura = ValorAbertura;
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

    public String getHoraAbertura() {
        return HoraAbertura;
    }

    public void setHoraAbertura(String HoraAbertura) {
        this.HoraAbertura = HoraAbertura;
    }

    public Double getValorAbertura() {
        return ValorAbertura;
    }

    public void setValorAbertura(Double ValorAbertura) {
        this.ValorAbertura = ValorAbertura;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getCodigoUtilizador() {
        return CodigoUtilizador;
    }

    public void setCodigoUtilizador(int CodigoUtilizador) {
        this.CodigoUtilizador = CodigoUtilizador;
    }

    public String getStatusCaixa() {
        return statusCaixa;
    }

    public void setStatusCaixa(String statusCaixa) {
        this.statusCaixa = statusCaixa;
    }

    
      

}
