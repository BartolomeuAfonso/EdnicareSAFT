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
public class FormaPagamento {

    private int Codigo,CodigoStatus;
    private String Descricao;

    public FormaPagamento() {
    }

    public FormaPagamento(int Codigo, String Descricao,int CodigoStatus) {
        this.Codigo = Codigo;
        this.Descricao = Descricao;
        this.CodigoStatus=CodigoStatus;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getCodigoStatus() {
        return CodigoStatus;
    }

    public void setCodigoStatus(int CodigoStatus) {
        this.CodigoStatus = CodigoStatus;
    }

    

}
