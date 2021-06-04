/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.HistorialClinico;
import CLINICA.modelo.HistorialGinecologica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Ba
 */
public class ControllerGinecolgia {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public ControllerGinecolgia(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(HistorialGinecologica historialGinecologica) {

        sql = "";

        try {
            ps = con.prepareStatement(sql);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public void editar(HistorialGinecologica historialGinecologica, int codigoClinico) {

        sql = "UPDATE historicoclinico SET queixaPresente=?,historiaDoencaActual=?,exameFisico=?,hipoteseDiagnostico=?, apf=? WHERE idHistoricoClinico=" + codigoClinico;
        try {
            ps = con.prepareStatement(sql);

            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getLastCodigoGinecologico() {
        sql = "select max(idHistoricoClinico) as max from historicoclinico";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public int getLastCodigobyHystoricoGinecologico(int triagem, int codigoPaciente) {
        sql = "select max(idHistoricoClinico) as max from historicoclinico where codigoConsulta= " + triagem + " AND codigoUtente=" + codigoPaciente;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public HistorialGinecologica getDadosClinico(int codigoClinico) {
        sql = "SELECT * FROM historicoclinico h where idHistoricoClinico=" + codigoClinico;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            HistorialClinico historialClinico;
            if (rs.next()) {
//                historialClinico = new HistorialClinico(rs.getInt("idHistoricoClinico"), rs.getInt("codigoConsulta"), rs.getString("queixaPresente"), rs.getString("historiaDoencaActual"), rs.getString("exameFisico"), rs.getString("hipoteseDiagnostico"), rs.getString("apf"));
//                return historialClinico;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
