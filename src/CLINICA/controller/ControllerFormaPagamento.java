/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerFormaPagamento {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerFormaPagamento(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public ArrayList<String> getDesignacaoForma() {
        //  conexao.Connectando();
        sql = "SELECT * FROM formapagamento s";
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

    public ArrayList<String> getDesignacaoFormaPagamento() {
        //conexao.Connectando();
        sql = "SELECT * FROM formapagamento s where s.codForma<>3 AND s.designacao <> 'CRÉDITO' AND s.designacao <> 'PRO-FORMA' ";
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

    public ArrayList<String> getDesignacaoCredito() {
        //conexao.Connectando();
        sql = "SELECT * FROM formapagamento s where s.designacao='FACTURA PRO-FORMA' OR s.designacao='CRÉDITO'";
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

    public int getCodigoFormaPagamento(String designacao) {
        try {
            // conexao.Connectando();
            sql = "SELECT codForma from formapagamento where designacao ='" + designacao + "'";
            System.out.println("Teste:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codForma");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
