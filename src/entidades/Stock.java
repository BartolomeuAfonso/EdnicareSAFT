/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Agostinho
 */
public class Stock {
   private int Codigo,QuantidadeMin,QuantidadeMax;
   private int QuantidadeActual,CodigoUsuario;
   private String dataCompra, dataEntreda,numeroFactura;

    public Stock() {
    }

    public Stock(int Codigo, int QuantidadeMin, int QuantidadeMax, String numeroFactura, int QuantidadeActual, int CodigoUsuario, String dataCompra,String dataEntreda) {
        this.Codigo = Codigo;
        this.QuantidadeMin = QuantidadeMin;
        this.QuantidadeMax = QuantidadeMax;
        this.numeroFactura = numeroFactura;
        this.QuantidadeActual = QuantidadeActual;
        this.CodigoUsuario = CodigoUsuario;
        this.dataCompra = dataCompra;
        this.dataEntreda = dataEntreda;
    }

    
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getQuantidadeMin() {
        return QuantidadeMin;
    }

    public void setQuantidadeMin(int QuantidadeMin) {
        this.QuantidadeMin = QuantidadeMin;
    }

    public int getQuantidadeMax() {
        return QuantidadeMax;
    }

    public void setQuantidadeMax(int QuantidadeMax) {
        this.QuantidadeMax = QuantidadeMax;
    }

    public int getQuantidadeActual() {
        return QuantidadeActual;
    }

    public void setQuantidadeActual(int QuantidadeActual) {
        this.QuantidadeActual = QuantidadeActual;
    }

    public int getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(int CodigoUsuario) {
        this.CodigoUsuario = CodigoUsuario;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getDataEntreda() {
        return dataEntreda;
    }

    public void setDataEntreda(String dataEntreda) {
        this.dataEntreda = dataEntreda;
    }
    
   
    
}
