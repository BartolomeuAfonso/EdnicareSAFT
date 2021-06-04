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
public class ControllerSexo {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerSexo(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    
    
    public ArrayList<String> getSexo() {
      //  conexao.Connectando();
        sql = "SELECT * FROM sexo s";
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

     public int getCodigoSexo(String designacao) {
      //  conexao.Connectando();
        sql = "SELECT idsexo from sexo where designacao ='"+designacao+"'";
        try {
            ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idsexo");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
