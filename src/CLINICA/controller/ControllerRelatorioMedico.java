/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.RelatorioMedicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerRelatorioMedico {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerRelatorioMedico(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarRelatorio(RelatorioMedicos relatorioMedico) {
       // conexao.Connectando();
        sql = "INSERT INTO relatoriomedico(codigoMedico,codigoPaciente,laboratorio,raioX,ecografia,ecg,eco,diagnostico,conduta,HDA,examegeral,data)values(?,?,?,?,?,?,?,?,?,?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, relatorioMedico.getCodigoMedico());
            ps.setInt(2, relatorioMedico.getCodigoPaciente());
            ps.setString(3, relatorioMedico.getLaboratorio());
            ps.setString(4, relatorioMedico.getRaioX());
            ps.setString(5, relatorioMedico.getEcografia());
            ps.setString(6, relatorioMedico.getEcg());
            ps.setString(7, relatorioMedico.getEcocardiograma());
            ps.setString(8, relatorioMedico.getDiagnostico());
            ps.setString(9, relatorioMedico.getConduta());
            ps.setString(10, relatorioMedico.getHda());
            ps.setString(11, relatorioMedico.getExamegeral());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
     //   conexao.DesconectaBanco();
    }

    public int getLastRelatorioMedico() {
        try {
         //   conexao.Connectando();
            sql = "select max(idrelatorio) as max from relatoriomedico";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void salvarRelatorioNormal(RelatorioMedicos relatorioMedico) {
      //  conexao.Connectando();
        sql = "INSERT INTO relatoriomediconormal(codigoMedico,codigoPaciente,descricao,data)values(?,?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, relatorioMedico.getCodigoMedico());
            ps.setInt(2, relatorioMedico.getCodigoPaciente());
            ps.setString(3, relatorioMedico.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public int getLastRelatorioMedicoNormal() {
        try {
           // conexao.Connectando();
            sql = "select max(idRelatorio) as max from relatoriomediconormal";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }
}
