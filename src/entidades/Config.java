/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;


/**
 *
 * @author Amarildo Xpecialista
 */
public class Config implements Serializable{
    private String ip;
    private String senha;
    private String usuario;
    private String mc;
    private String vaidadeI;
    private String vaidadeF;

    public Config() {
    }
    public Config(String ip, String senha, String usuario, String mc, String vaidadeI, String vaidadeF) {
        this.ip = ip;
        this.senha = senha;
        this.usuario = usuario;
        this.mc = mc;
        this.vaidadeI = vaidadeI;
        this.vaidadeF = vaidadeF;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getVaidadeI() {
        return vaidadeI;
    }

    public void setVaidadeI(String vaidadeI) {
        this.vaidadeI = vaidadeI;
    }

    public String getVaidadeF() {
        return vaidadeF;
    }

    public void setVaidadeF(String vaidadeF) {
        this.vaidadeF = vaidadeF;
    }
    
    
    
}
