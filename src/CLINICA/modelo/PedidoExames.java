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
public class PedidoExames {

    private long idPedidoExame;
    private int codigoPaciente, codigoServico, codigoTriagem, codigoMedico;
    private int codigoFactura;
    private Date dataPedido;
    private String hora, descricao, diagnostico;

    public PedidoExames() {
    }

    public PedidoExames(long idPedidoExame, int codigoPaciente, int codigoServico, int codigoTriagem, int codigoMedico, Date dataPedido) {
        this.idPedidoExame = idPedidoExame;
        this.codigoPaciente = codigoPaciente;
        this.codigoServico = codigoServico;
        this.codigoTriagem = codigoTriagem;
        this.codigoMedico = codigoMedico;
        this.dataPedido = dataPedido;
    }

    public long getIdPedidoExame() {
        return idPedidoExame;
    }

    public void setIdPedidoExame(long idPedidoExame) {
        this.idPedidoExame = idPedidoExame;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public int getCodigoTriagem() {
        return codigoTriagem;
    }

    public void setCodigoTriagem(int codigoTriagem) {
        this.codigoTriagem = codigoTriagem;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

}
