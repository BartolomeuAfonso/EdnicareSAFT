/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class Licenca {
    private int codigo;
    private String mac;
    private Date dataInicial,dataFinal;

    public Licenca() {
    }

    public Licenca(int codigo, String mac, Date dataInicial, Date dataFinal) {
        this.codigo = codigo;
        this.mac = mac;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Licenca(String mac, Date dataInicial, Date dataFinal) {
        this.mac = mac;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Licenca(Date dataInicial, Date dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    
}
