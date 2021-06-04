/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.math.BigDecimal;

/**
 *
 * @author El Router
 */
public class MovimentoItem {
    
    private Long idItem;
    private int movimento;
    private int contacorrente;
    private BigDecimal movimentar;
    private String natureza;

    public MovimentoItem() {
     
    }
    public MovimentoItem(Long idItem, int movimento, int contacorrente, BigDecimal movimentar, String natureza) {
        this.idItem = idItem;
        this.movimento = movimento;
        this.contacorrente = contacorrente;
        this.movimentar = movimentar;
        this.natureza = natureza;
    }
    public MovimentoItem(int movimento, int contacorrente, BigDecimal movimentar, String natureza) {
        this.movimento = movimento;
        this.contacorrente = contacorrente;
        this.movimentar = movimentar;
        this.natureza = natureza;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public int getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    public int getContacorrente() {
        return contacorrente;
    }

    public void setContacorrente(int contacorrente) {
        this.contacorrente = contacorrente;
    }

    public BigDecimal getMovimentar() {
        return movimentar;
    }

    public void setMovimentar(BigDecimal movimentar) {
        this.movimentar = movimentar;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }
    
    
    
}
