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
 * @author Desenvolvimento
 */
public class ControllerRaca {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    
     Connection con;

    public ControllerRaca(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public ArrayList<String> getRaca() {
    //    conexao.Connectando();
        sql = "SELECT * FROM raca p";
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

    public int getCodigoRaca(String designacao) {
        conexao.Connectando();
        sql = "SELECT idraca from raca where designacao ='" + designacao + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idraca");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
