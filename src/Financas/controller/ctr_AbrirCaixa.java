/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Financas.Modelo.AbrirCaixa;

/**
 *
 * @author El Router
 */
public class ctr_AbrirCaixa {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_AbrirCaixa(Connection con) {
        this.con = con;
    }

    public boolean save(AbrirCaixa model) {
        sql = "INSERT INTO abrirCaixa (data, hora, caixa, saldo,valor_abertura,total,historico,utilizador) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, (Date)model.getData());
            ps.setString(2, model.getHora());
            ps.setInt(3, model.getCaixa());
            ps.setDouble(4, model.getSaldoCaixa());
            ps.setDouble(5, model.getValor_abertura());
            ps.setDouble(6,model.getTotal());
            ps.setString(7,model.getHistorico());
            ps.setInt(8,model.getUtilizador());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public int getLast() {

        sql = "SELECT MAX(codigo) FROM  abrirCaixa";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    
}
