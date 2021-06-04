/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class ParametroController {
    
    ConexaoBancos conexao = new ConexaoBancos();
     
    public double getValorParametroByCodigo(int parametro) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM parametro WHERE(codParametro = "+parametro+") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valor");
            }
        } catch (SQLException ex) {
        }

        return 0;
    }
    public double getValorParametroBySigla(int parametro) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM parametro WHERE(sigla = '"+parametro+"') ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valor");
            }
        } catch (SQLException ex) {
        }

        return 0;
    }
    public int getCodParametroBySigla(String sigla) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produto WHERE(sigla = '"+sigla+"') ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("codParametro");
            }
        } catch (SQLException ex) {
        }

        return 0;
    }
   
    public ArrayList<Double> getAllValores() throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DISTINCT* FROM parametro";
        ArrayList<Double> lista = new ArrayList<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            while (rs.next()) {
                lista.add(rs.getDouble("valor"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

}
