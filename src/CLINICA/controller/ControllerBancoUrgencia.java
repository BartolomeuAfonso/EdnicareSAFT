/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.bancoUrgenciaModelo;
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
public class ControllerBancoUrgencia {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public ControllerBancoUrgencia(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarServico(bancoUrgenciaModelo urgenciaModelo) {
        // conexao.Connectando();
        sql = "INSERT INTO bancourgencia(designacao,nomePaciente,acompanhante,codigoUtilizador,dataOperacao)values(?,?,?,?,now())";
        // System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, urgenciaModelo.getDesignacao());
            ps.setString(2, urgenciaModelo.getPaciente());
            ps.setString(3, urgenciaModelo.getAcompanhte());
            ps.setInt(4, urgenciaModelo.getCodigoUtilizador());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public void salvarProcedimento(bancoUrgenciaModelo urgenciaModelo) {
        // conexao.Connectando();
        sql = "INSERT INTO procedimentoBanco(designacao,nomePaciente,acompanhante,codigoUtilizador,dataOperacao)values(?,?,?,?,now())";
        // System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, urgenciaModelo.getDesignacao());
            ps.setString(2, urgenciaModelo.getPaciente());
            ps.setString(3, urgenciaModelo.getAcompanhte());
            ps.setInt(4, urgenciaModelo.getCodigoUtilizador());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeCategoria() {
        // conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM categoriaservico s where codigoStatus=1";
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

    public int getCodigoCategoria(String designacao) {
//        conexao.Connectando();
        sql = "SELECT idcategoriaServico from categoriaservico where designacao ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idcategoriaServico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int eliminar(int codigo) {
        //  con.Connectando();
        sql = "delete from categoriaservico where idcategoriaServico =" + codigo;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
