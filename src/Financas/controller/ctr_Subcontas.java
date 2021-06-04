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
import Financas.Modelo.Subcontas;

/**
 *
 * @author El Router
 */
public class ctr_Subcontas {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Subcontas(Connection con) {
        this.con = con;
    }

    public boolean save(Subcontas model) {
        sql = "INSERT INTO subcontas(\"designacao\", \"numero\", \"conta\") VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDesignacao());
            ps.setString(2, model.getNumero());
            ps.setInt(3, model.getConta());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean Editar(Subcontas model, int codigo) {

        sql = "UPDATE subcontas SET \"designacao\"=?, \"numero\"=?, \"conta\"=? WHERE \"codigo\" =?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDesignacao());
            ps.setString(2, model.getNumero());
            ps.setInt(3, model.getConta());
            ps.setInt(4, codigo);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean Eliminar(int Codigo) {
        sql = "DELETE FROM subcontas  WHERE  \"codigo\" =" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public int getCodByNome(String nome) {

        sql = "SELECT \"codigo\" FROM  \"subcontas\" WHERE  \"designacao\"  like '" + nome + "'";
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
        sql = "SELECT \"designacao\" FROM subcontas WHERE codigo =" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("designacao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getContaByDescricao(String descricao) {
        sql = "SELECT c.\"descricao\" FROM  \"subcontas\" s inner join contas c on c.codigo=s.conta where s.\"designacao\" like '" + descricao + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public ArrayList<String> getDescricao() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT \"designacao\" AS Descricao FROM  \"subcontas\"";
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

    public ArrayList<String> getDescricaoByCodigo(int codigo) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT \"designacao\" AS Descricao FROM  \"subcontas\" WHERE codigo =" + codigo;
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

    public ArrayList<Subcontas> getAll(int order) {
        ArrayList<Subcontas> list = new ArrayList<>();
        sql = "SELECT c.\"codigo\" as id ,c.\"descricao\" as descricao,c.\"obs\" as obs,c.\"centro_custo\" as centro FROM  \"contas\" c  order by " + order + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subcontas(rs.getInt("id"), rs.getString("designacao"), rs.getString("numero"), rs.getInt("conta")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
