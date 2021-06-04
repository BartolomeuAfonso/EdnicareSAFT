/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.util.Date;

/**
 *
 * @author Desenvolvimento
 */
public class ConsultasAtendidas {
   private long idConsultasAtendidas;
   private int codigoTriagem, codigoPaciente, codigoMedico;
   private String orientacaoMedica;
   private Date dataAtendimento;
 

    public ConsultasAtendidas() {
    }

    public ConsultasAtendidas(long idConsultasAtendidas, int codigoTriagem, int codigoPaciente, int codigoMedico, String orientacaoMedica, Date dataAtendimento) {
        this.idConsultasAtendidas = idConsultasAtendidas;
        this.codigoTriagem = codigoTriagem;
        this.codigoPaciente = codigoPaciente;
        this.codigoMedico = codigoMedico;
        this.orientacaoMedica = orientacaoMedica;
        this.dataAtendimento = dataAtendimento;
    }

    public long getIdConsultasAtendidas() {
        return idConsultasAtendidas;
    }

    public void setIdConsultasAtendidas(long idConsultasAtendidas) {
        this.idConsultasAtendidas = idConsultasAtendidas;
    }

    public int getCodigoTriagem() {
        return codigoTriagem;
    }

    public void setCodigoTriagem(int codigoTriagem) {
        this.codigoTriagem = codigoTriagem;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getOrientacaoMedica() {
        return orientacaoMedica;
    }

    public void setOrientacaoMedica(String orientacaoMedica) {
        this.orientacaoMedica = orientacaoMedica;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }
   
   
   
}
