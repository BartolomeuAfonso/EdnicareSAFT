/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Parametros;

/**
 *
 * @author Eletronicos
 */
public class ParametrosController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ParametrosController(Connection con) {
        this.con = con;
    }

    public boolean InserirParametro(Parametros modelo) {

        sql = "INSERT INTO parametros(parametro,valor,descricao)VALUES(?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modelo.getParametro());
            ps.setDouble(2, modelo.getValor());
            ps.setString(3, modelo.getDescricao());
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException ex) {
             new RuntimeException(ex);
        }
        return false;
    }

    public boolean AlterarParametro(Parametros modelo) {

        sql = "UPDATE  Parametros set parametro = ?, valor = ? ,descricao = ? WHERE  \"Codigo\" =?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modelo.getParametro());
            ps.setDouble(2, modelo.getValor());
            ps.setString(3, modelo.getDescricao());
            ps.setInt(4, modelo.getCodigo());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return false;
    }

    public boolean AlterarValorParametro(String parametro, double valor) {

        sql = "UPDATE  Parametros set valor = ? WHERE Parametro =?";
        PreparedStatement ps;
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setString(2, parametro);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return false;
    }
    public boolean AlterarDescricao(String valor) {

        sql = "UPDATE  parametros set \"descricao\" = ? WHERE \"Codigo\" = ?";
        System.out.println(" sql " + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, valor);
            ps.setInt(2, 11);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(""+ex);
        }
        return false;
    }
    public boolean AlterarValorIva(double valor) {

        sql = "UPDATE  Parametros set \"Valor\" = ? WHERE \"Codigo\" =10";
        PreparedStatement ps;
//        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.execute();
//            ps.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO " +ex);
        }
        return false;
    }

    public List RetornarObjectoParametro() {
        List ObjectoCompleto = new ArrayList();
        sql = "SELECT * FROM Parametros order by 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                ObjectoCompleto.add(rs.getString("Parametro"));
            }
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return ObjectoCompleto;
    }

    public int RetornarCodigoStatus(String Nome) {

        sql = "SELECT * FROM Parametros WHERE Parametro ='" + Nome + "'";
        int Codigo = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Codigo = rs.getInt("Codigo");

            }
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return Codigo;
    }

    public String getLogotipo() {

        sql = "SELECT \"descricao\" FROM parametros WHERE  \"Codigo\"= 11 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return "";
    }
    public int getLimiteIncremento() {

        sql = "SELECT \"Valor\" FROM parametros WHERE  \"Codigo\"= 7 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return 0;
    }

    public int getIRT() {

        sql = "SELECT \"Valor\" FROM parametros WHERE  \"Codigo\"= 3 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public double getIVA() {

        sql = "SELECT \"Valor\" FROM parametros WHERE  \"Codigo\"= 10 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getINSS() {

        sql = "SELECT \"Valor\" FROM parametros WHERE  \"Codigo\"= 4 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getFaltas() {

        sql = "SELECT \"Valor\" FROM parametros WHERE  \"Codigo\"= 9 ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getValor(String parametro) {

        String sql = "SELECT \"Valor\" FROM parametros WHERE \"Parametro\"='" + parametro + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
