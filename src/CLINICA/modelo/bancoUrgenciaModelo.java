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
public class bancoUrgenciaModelo {

    int codigo, codigoUtilizador;
    String designacao, paciente, acompanhte;

    public bancoUrgenciaModelo() {
    }

    public bancoUrgenciaModelo(int codigo, int codigoUtilizador, String designacao, String paciente, String acompanhte) {
        this.codigo = codigo;
        this.codigoUtilizador = codigoUtilizador;
        this.designacao = designacao;
        this.paciente = paciente;
        this.acompanhte = acompanhte;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoUtilizador() {
        return codigoUtilizador;
    }

    public void setCodigoUtilizador(int codigoUtilizador) {
        this.codigoUtilizador = codigoUtilizador;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getAcompanhte() {
        return acompanhte;
    }

    public void setAcompanhte(String acompanhte) {
        this.acompanhte = acompanhte;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
   
    

}
