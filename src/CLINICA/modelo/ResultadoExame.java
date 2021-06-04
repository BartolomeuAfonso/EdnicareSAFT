/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

/**
 *
 * @author Probook
 */
public class ResultadoExame {

    private int codigo;
    private String exame, reultado, dataPedido, dataResultado, referencia;
    private String factor_rh;

    public ResultadoExame(int codigo, String exame, String reultado, String dataPedido, String dataResultado) {
        this.codigo = codigo;
        this.exame = exame;
        this.reultado = reultado;
        this.dataPedido = dataPedido;
        this.dataResultado = dataResultado;
    }

    public ResultadoExame(int codigo, String exame, String reultado, String dataPedido, String dataResultado, String referencia) {
        this.codigo = codigo;
        this.exame = exame;
        this.reultado = reultado;
        this.dataPedido = dataPedido;
        this.dataResultado = dataResultado;
        this.referencia = referencia;
    }

    public ResultadoExame(int codigo, String exame, String resultado, String referencia) {
        this.exame = exame;
        this.reultado = resultado;
        this.referencia = referencia;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getReultado() {
        return reultado;
    }

    public void setReultado(String reultado) {
        this.reultado = reultado;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(String dataResultado) {
        this.dataResultado = dataResultado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFactor_rh() {
        return factor_rh;
    }

    public void setFactor_rh(String factor_rh) {
        this.factor_rh = factor_rh;
    }

}
