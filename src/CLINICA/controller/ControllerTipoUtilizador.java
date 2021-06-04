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
import CLINICA.modelo.TipoUtilizador;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerTipoUtilizador {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
     Connection con;

    public ControllerTipoUtilizador(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarMedico(TipoUtilizador tipoUtilizador) {
     //   conexao.Connectando();
        sql = "INSERT INTO tipoutilizador(designacao)values(?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tipoUtilizador.getDesignacao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
  //      conexao.DesconectaBanco();
    }

    public ArrayList<String> getTipoUtilizador() {
     //   conexao.Connectando();
        sql = "SELECT * FROM tipoutilizador ";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getTipoUtilizadorRecepcao() {
     //   conexao.Connectando();
        sql = "SELECT * FROM tipoutilizador where idTipoUtilizador >=2 and idTipoUtilizador <=8";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getTipoLoja() {
    //    conexao.Connectando();
        sql = "SELECT * FROM tipoutilizador where idTipoUtilizador >8";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getTipoUsuario() {
    //    conexao.Connectando();
        sql = "SELECT * FROM tipoutilizador";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("idTipoUtilizador"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigoTipoUtilizador(String designacao) {
   //     conexao.Connectando();
        sql = "SELECT idTipoUtilizador from tipoutilizador where designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idTipoUtilizador");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
