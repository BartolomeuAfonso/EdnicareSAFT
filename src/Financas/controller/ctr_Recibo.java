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
import java.util.ArrayList;
import Financas.Modelo.Recibos;

/**
 *
 * @author El Router
 */
public class ctr_Recibo {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Recibo(Connection con) {
        this.con = con;
    }

    public boolean save(Recibos model) {
        sql = "INSERT INTO recibo(\"titulo\", \"valor\", \"conta\", \"caixa\", \"historico1\","
                + " \"historico2\",\"historico3\", \"tipo\", \"data\", \"hora\") VALUES (?,?, ?, ?, ?, ?, ?, ?,?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getTitulo());
            ps.setDouble(2, model.getValor());
            ps.setString(3, model.getConta());
            ps.setString(4, model.getCaixa());
            ps.setString(5, model.getHistorico1());
            ps.setString(6, model.getHistorico2());
            ps.setString(7, model.getHistorico3());
            ps.setString(8, model.getTipo());
            ps.setDate(9, (java.sql.Date) model.getData());
            ps.setString(10, model.getHora());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean exist(String doc) {

        sql = "SELECT \"codigo\" FROM  \"recibo\" WHERE  \"titulo\"  like '" + doc + "'";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }
    public boolean existRecibo(String doc) {

        sql = "SELECT \"codigo\" FROM  \"recibo\" WHERE  \"codigo\"  = " + doc + "";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public int getLast() {

        sql = "SELECT MAX(\"codigo\") FROM  \"recibo\" ";

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
    public String getConta(int recibo) {

        sql = "SELECT \"conta\" FROM  \"recibo\"  where \"codigo\"="+recibo+"";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return "";
    }
    public String getTitulo(int recibo) {

        sql = "SELECT \"titulo\" FROM  \"recibo\"  where \"codigo\"="+recibo+"";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return "";
    }
    public String getCaixa(int recibo) {

        sql = "SELECT \"caixa\" FROM  \"recibo\" where \"codigo\"="+recibo+" ";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return "";
    }

     public boolean delete(int Codigo) {
        sql = "delete from recibo  WHERE  \"codigo\" =" + Codigo + "";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean update(Recibos model, int codigo) {

        sql = "UPDATE recibo SET \"valor\"=?, \"historico1\"=?,\"historico2\"=?, \"historico3\"=? WHERE \"codigo\"=?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
//            ps.setString(1, model.getTitulo());
            ps.setDouble(1, model.getValor());
//            ps.setString(3, model.getConta());
//            ps.setString(4, model.getCaixa());
            ps.setString(2, model.getHistorico1());
            ps.setString(3, model.getHistorico2());
            ps.setString(4, model.getHistorico3());
//            ps.setString(8, model.getTipo());
//            ps.setDate(9, (java.sql.Date) model.getData());
//            ps.setString(10, model.getHora());
            ps.setInt(5, codigo);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public ArrayList<Recibos> getReciboByCodigo(int codigo) {
        ArrayList<Recibos> list = new ArrayList<>();
        sql = "SELECT *FROM  \"recibo\" WHERE \"codigo\" =  " + codigo;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        new Recibos(rs.getInt("codigo"),
                                rs.getString("titulo"), rs.getDate("data"),
                                rs.getDouble("valor"), rs.getString("conta"),
                                rs.getString("caixa"), rs.getString("historico1"),
                                rs.getString("historico2"), rs.getString("historico3"),
                                rs.getString("tipo")
                        )
                );
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<Recibos> getRecibos() {
        ArrayList<Recibos> list = new ArrayList<>();
        sql = "SELECT *FROM  \"recibo\" ";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        new Recibos(rs.getInt("codigo"),
                                rs.getString("titulo"), rs.getDate("data"),
                                rs.getDouble("valor"), rs.getString("historico1"),
                                rs.getString("hora")
                        )
                );
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
    public ArrayList<Recibos> getRecibosByData(String d1,String d2) {
        ArrayList<Recibos> list = new ArrayList<>();
        sql = "SELECT *FROM  \"recibo\" WHERE \"data\" between '" + d1 + "' AND '" + d2 + "'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        new Recibos(rs.getInt("codigo"),
                                rs.getString("titulo"), rs.getDate("data"),
                                rs.getDouble("valor"), rs.getString("historico1"),
                                rs.getString("hora")
                        )
                );
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

}
