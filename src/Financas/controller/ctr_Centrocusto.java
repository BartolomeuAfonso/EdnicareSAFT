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
import javax.swing.JOptionPane;
import Financas.Modelo.CentroCustos;

/**
 *
 * @author El Router
 */
public class ctr_Centrocusto {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Centrocusto(Connection con) {
        this.con = con;
    }

    public boolean save(CentroCustos model) {
        sql = "INSERT INTO centro_custo(descricao, obs, tipo_centro) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDescricao());
            ps.setInt(3, model.getTipo_centro());
            ps.setString(2, model.getObs());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean Editar(CentroCustos model, int codigo) {

        sql = "UPDATE centro_custo SET descricao=?,obs=?,tipo_centro=? WHERE codigo =?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDescricao());
            ps.setInt(3, model.getTipo_centro());
            ps.setString(2, model.getObs());
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
        sql = "DELETE FROM centro_custo  WHERE  codigo =" + Codigo;
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

        sql = "SELECT codigo FROM centro_custo WHERE descricao like '" + nome + "%'";
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
        sql = "SELECT descricao FROM centro_custo WHERE codigo =" + Codigo;
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

    public String getTipoByDescricao(String descricao) {
        sql = "SELECT t.descricao FROM centro_custo c inner join tipo_centro t on c.tipo_centro=t.codigo where c.descricao like '" + descricao + "%'";
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

    public String getTipoByConta(String conta) {
        sql = "SELECT t.descricao FROM  centro_custo c inner join tipo_centro t on c.tipo_centro=t.codigo  inner join contas conta on conta.centro_custo=c.codigo where conta.descricao like '" + conta + "%'";
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
        sql = "SELECT descricao AS Descricao FROM centro_custo";
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
        sql = "SELECT descricao AS Descricao FROM centro_custo WHERE codigo=" + codigo;
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

    public ArrayList<String> getDescricao2(String d) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM centro_custo where not descricao like '" + d + "%' ";
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

    public ArrayList<CentroCustos> getAll(int order) {
        ArrayList<CentroCustos> list = new ArrayList<>();
        sql = "SELECT c.codigo as id ,c.descricao as descricao,c.obs as obs,c.tipo_centro as tipo FROM  centro_custo c  order by " + order + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CentroCustos(rs.getInt("id"), rs.getString("descricao"), rs.getString("obs"), rs.getInt("tipo")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
