/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Receita;
import java.sql.Connection;
import java.sql.Date;
import static java.time.LocalDate.now;

import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerReceita {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerReceita(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Receita receita) {
        // conexao.Connectando();
        sql = "INSERT INTO receitas(codigoPaciente,codigoMedico,designacao,dataCadastro,codigoHistorico)values(?,?,?,now(),?)";
        System.out.println("TESTE:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, receita.getCodigoPaciente());
            ps.setInt(2, receita.getCodigoMedico());
            ps.setString(3, receita.getDesignacao());
            ps.setInt(4, receita.getCodigoHistorico());
            // ps.setDate(4, (Date) receita.getData());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void editar(Receita receita, int codigo) {
        // conexao.Connectando();
        sql = "UPDATE receitas SET designacao='" + receita.getDesignacao() + "' WHERE idReceita =" + codigo;
        System.out.println("TESTE:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public int getCodigoReceita(int paciente, int codigoHistorico) {
        // conexao.Connectando();
        sql = "SELECT idReceita FROM receitas r where codigoHistorico=" + codigoHistorico + " and codigoPaciente=" + paciente;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idReceita");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public boolean getCodigoReceitaporData(int paciente, int codigoHistorico) {
        // conexao.Connectando();
        sql = "SELECT idReceita FROM receitas r where codigoHistorico=" + codigoHistorico + " and codigoPaciente=" + paciente;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return false;
    }

    public int getCodigoReceita1(int paciente, int codigoHistorico) {
//        conexao.Connectando();
        sql = "SELECT idReceita FROM receitas r where codigoHistorico=" + codigoHistorico + " and codigoPaciente=" + paciente + " AND dataCadastro =CURRENT_DATE";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idReceita");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getLastInsert() {
        // conexao.Connectando();
        sql = "select max(idReceita) as last from receitas";
        System.out.println("Ultimo exame lançado:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("last");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getReceita(int paciente) {
//        conexao.Connectando();
        sql = "SELECT designacao FROM receitas r where codigoPaciente=" + paciente + " AND dataCadastro=Current_date";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("designacao");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return "";
    }

}
