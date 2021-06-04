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
public class TipoFormaPagamento {
   private int Codigo,CodigoVenda,CodigoPagamento;

    public TipoFormaPagamento() {
    }

    public TipoFormaPagamento(int Codigo, int CodigoVenda, int CodigoPagamento) {
        this.Codigo = Codigo;
        this.CodigoVenda = CodigoVenda;
        this.CodigoPagamento = CodigoPagamento;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoVenda() {
        return CodigoVenda;
    }

    public void setCodigoVenda(int CodigoVenda) {
        this.CodigoVenda = CodigoVenda;
    }

    public int getCodigoPagamento() {
        return CodigoPagamento;
    }

    public void setCodigoPagamento(int CodigoPagamento) {
        this.CodigoPagamento = CodigoPagamento;
    }
  
    
}
