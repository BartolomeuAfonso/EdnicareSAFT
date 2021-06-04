/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author El Router
 */
public class ctr_TipoCentrocusto {
    
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    
    public ctr_TipoCentrocusto(Connection con) {
        this.con = con;
    }
    
    public int getCodByNome(String nome) {

        sql = "SELECT \"codigo\" FROM  \"tipo_centro\" WHERE  \"descricao\"  like '" + nome + "'";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String getDescricaoByCod(int Codigo) {
        sql = "SELECT \"descricao\" FROM tipo_centro WHERE codigo =" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("descricao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public ArrayList<String> getAll() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT \"descricao\" AS Descricao FROM  \"tipo_centro\"";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
    public ArrayList<String> getAll2(String desc) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT distinct \"descricao\" AS Descricao FROM  \"tipo_centro\" where not \"descricao\"  like '"+desc+"' ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
