/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.modelo;

import java.util.Date;

/**
 *
 * @author El Router
 */
public class Registros {
    
    private Long idRegistro;
    private Date data;
    private String registro;
    private int farmanceutico;

    public Registros(Long idRegistro, Date data, String registro, int farmanceutico) {
        this.idRegistro = idRegistro;
        this.data = data;
        this.registro = registro;
        this.farmanceutico = farmanceutico;
    }

    public Registros() {
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getFarmanceutico() {
        return farmanceutico;
    }

    public void setFarmanceutico(int farmanceutico) {
        this.farmanceutico = farmanceutico;
    }
    
    
}
