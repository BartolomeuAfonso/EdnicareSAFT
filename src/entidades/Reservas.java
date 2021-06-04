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
public class Reservas {
    private int Codigo,CodigoMesa,CodigoCliente;			
    private String HoraAbertura,HoraFecho;

    public Reservas() {
    }

    public Reservas(int Codigo, int CodigoMesa, int CodigoCliente, String HoraAbertura, String HoraFecho) {
        this.Codigo = Codigo;
        this.CodigoMesa = CodigoMesa;
        this.CodigoCliente = CodigoCliente;
        this.HoraAbertura = HoraAbertura;
        this.HoraFecho = HoraFecho;
    }

    public String getHoraFecho() {
        return HoraFecho;
    }

    public void setHoraFecho(String HoraFecho) {
        this.HoraFecho = HoraFecho;
    }

    public String getHoraAbertura() {
        return HoraAbertura;
    }

    public void setHoraAbertura(String HoraAbertura) {
        this.HoraAbertura = HoraAbertura;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoMesa() {
        return CodigoMesa;
    }

    public void setCodigoMesa(int CodigoMesa) {
        this.CodigoMesa = CodigoMesa;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }
    
    

    
}
