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
public class PontoVenda {
   private int Codigo,CodigoStatus,CodigoArmazem;
   private String Descricao;

    public PontoVenda() {
    }

    public PontoVenda(int Codigo, int CodigoStatus, String Descricao, int CodigoArmazem) {
        this.Codigo = Codigo;
        this.CodigoStatus = CodigoStatus;
        this.CodigoArmazem = CodigoArmazem;
        this.Descricao = Descricao;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoStatus() {
        return CodigoStatus;
    }

    public void setCodigoStatus(int CodigoStatus) {
        this.CodigoStatus = CodigoStatus;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getCodigoArmazem() {
        return CodigoArmazem;
    }

    public void setCodigoArmazem(int CodigoArmazem) {
        this.CodigoArmazem = CodigoArmazem;
    }
     
    
}
