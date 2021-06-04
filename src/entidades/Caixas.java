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
public class Caixas {

    private int Codigo, CodigoFuncionario;
    private String Descricao, HoraAbertura,DataAbertura;

    public Caixas() {
    }

    public Caixas(int Codigo, int CodigoFuncionario, String Descricao, String HoraAbertura, String DataAbertura ) {
        this.Codigo = Codigo;
        this.CodigoFuncionario = CodigoFuncionario;
        this.Descricao = Descricao;
        this.HoraAbertura = HoraAbertura;
        this.DataAbertura = DataAbertura;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoFuncionario() {
        return CodigoFuncionario;
    }

    public void setCodigoFuncionario(int CodigoFuncionario) {
        this.CodigoFuncionario = CodigoFuncionario;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getHoraAbertura() {
        return HoraAbertura;
    }

    public void setHoraAbertura(String HoraAbertura) {
        this.HoraAbertura = HoraAbertura;
    }

    public String getDataAbertura() {
        return DataAbertura;
    }

    public void setDataAbertura(String DataAbertura) {
        this.DataAbertura = DataAbertura;
    }
    

}
