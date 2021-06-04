/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Internamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Probook
 */
public class ControllerInternamento {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerInternamento(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void InsertInternamento(Internamento internamento) {
        //  conexao.Connectando();
        sql = "INSERT INTO internamento(codigoMedico,codigoPaciente,cama,quarto,acompanhante,medicoAssistente,dataSaida,diagnostico,areaInternado,dataEntrada)values(?,?,?,?,?,?,?,?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, internamento.getCodigoMedico());
            ps.setInt(2, internamento.getCodigoPaciente());
            ps.setString(3, internamento.getCama());
            ps.setString(4, internamento.getQuarto());
            ps.setString(5, internamento.getAcompanhamento());
            ps.setString(6, internamento.getMedicoAssistente());
            ps.setString(7, internamento.getDataSaida());
            ps.setString(8, internamento.getDiagnostico());
            ps.setString(9, internamento.getAreaInternado());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Paciente Internado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();

    }

    public ArrayList<String> getPacienteporInternar() {
        //  conexao.Connectando();
        sql = "SELECT p.nomeCompleto as nome FROM historicoclinico h inner join pacientes p on h.codigoUtente =p.idPaciente\n"
                + "where date(h.dataAtendimento) = current_Date";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;

    }

    public ArrayList<String> getPacienteporInternado() {
        //conexao.Connectando();
        sql = "SELECT p.nomeCompleto as nome FROM internamento i inner join pacientes p on\n"
                + "i.codigoPaciente = p.idPaciente inner join medicos m on i.codigoMedico= m.idMedico";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;

    }

    public ArrayList<String> getPacienteporInternadoporLike(String nome) {
        //conexao.Connectando();
        sql = "SELECT p.nomeCompleto as nome FROM internamento i inner join pacientes p on\n"
                + "i.codigoPaciente = p.idPaciente inner join medicos m on i.codigoMedico= m.idMedico\n"
                + "where p.nomeCompleto like '" + nome + "%'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;

    }

}
