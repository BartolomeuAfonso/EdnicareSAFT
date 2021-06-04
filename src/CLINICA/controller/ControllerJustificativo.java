/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.JustificativoMedico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerJustificativo {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerJustificativo(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(JustificativoMedico justificativoMedico) {
        //   conexao.Connectando();
        sql = "INSERT INTO justificativomedico(codigoPaciente,codigoMedico,codigoCid,designacao,dataConsulta,acompanhante,dataFim,grau)values(?,?,?,?,now(),?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, justificativoMedico.getCodigoPaciente());
            ps.setInt(2, justificativoMedico.getCodigoMedico());
            ps.setInt(3, justificativoMedico.getCodigoCids());
            ps.setString(4, justificativoMedico.getDesignacao());
            ps.setString(5, justificativoMedico.getAcompanhante());
            ps.setString(6, justificativoMedico.getDatafim());
            ps.setString(7, justificativoMedico.getGrau());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Justificativo salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public int getLastCodigo() {
        //  conexao.Connectando();
        sql = "select max(idjustificativoMedico) as max from justificativomedico";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCodigoJustificativo(String data, int codigoPaciente) {
        // conexao.Connectando();
        sql = "select j.idjustificativoMedico as max from justificativomedico j where Date(j.dataConsulta)='" + data + "' AND j.codigoPaciente=" + codigoPaciente;
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
