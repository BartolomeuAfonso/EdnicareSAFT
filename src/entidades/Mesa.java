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
public class Mesa {

    private int Codigo, nOCupante, CodigoStatusMesa, codigoTipoMesa;
    private String Descricao;

    public Mesa() {
    }

    public Mesa(String Descricao,int CodigoStatusMesa) {
        this.CodigoStatusMesa = CodigoStatusMesa;
        this.Descricao = Descricao;
    }

    public Mesa(int Codigo, int nOCupante, int CodigoStatusMesa, String Descricao) {
        this.Codigo = Codigo;
        this.nOCupante = nOCupante;
        this.CodigoStatusMesa = CodigoStatusMesa;
        this.Descricao = Descricao;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getnOCupante() {
        return nOCupante;
    }

    public void setnOCupante(int nOCupante) {
        this.nOCupante = nOCupante;
    }

    public int getCodigoStatusMesa() {
        return CodigoStatusMesa;
    }

    public void setCodigoStatusMesa(int CodigoStatusMesa) {
        this.CodigoStatusMesa = CodigoStatusMesa;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getCodigoTipoMesa() {
        return codigoTipoMesa;
    }

    public void setCodigoTipoMesa(int codigoTipoMesa) {
        this.codigoTipoMesa = codigoTipoMesa;
    }
   
}
