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
public class HistoricaInterno {

    private int codigoHistorico, codigoPaciente;
    private String doencas, alergias,setUltimoTratamento;

    public HistoricaInterno() {
    }

    public HistoricaInterno(int codigoHistorico, int codigoPaciente,String setUltimoTratamento, String doencas, String alergias) {
        this.codigoHistorico = codigoHistorico;
        this.codigoPaciente = codigoPaciente;
        this.setUltimoTratamento = setUltimoTratamento;
        this.doencas = doencas;
        this.alergias = alergias;
    }

    public int getCodigoHistorico() {
        return codigoHistorico;
    }

    public void setCodigoHistorico(int codigoHistorico) {
        this.codigoHistorico = codigoHistorico;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getSetUltimoTratamento() {
        return setUltimoTratamento;
    }

    public void setSetUltimoTratamento(String setUltimoTratamento) {
        this.setUltimoTratamento = setUltimoTratamento;
    }
   
}
