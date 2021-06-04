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
public class ItemReserva {

    private int Codigo;
    int CodigoReserva, CodigoProduto;

    public ItemReserva() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoReserva() {
        return CodigoReserva;
    }

    public void setCodigoReserva(int CodigoReserva) {
        this.CodigoReserva = CodigoReserva;
    }

    public int getCodigoProduto() {
        return CodigoProduto;
    }

    public void setCodigoProduto(int CodigoProduto) {
        this.CodigoProduto = CodigoProduto;
    }

}
