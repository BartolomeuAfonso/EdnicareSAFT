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
import Financas.Modelo.Contas;

/**
 *
 * @author El Router
 */
public class ctr_Contas {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Contas(Connection con) {
        this.con = con;
    }

    public boolean save(Contas model) {
        sql = "INSERT INTO contas(descricao, centro_custo,obs) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDescricao());
            ps.setInt(2, model.getCentro_custo());
            ps.setString(3, model.getObs());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean Editar(Contas model, int codigo) {

        sql = "UPDATE contas SET descricao=?, centro_custo=?, obs=? WHERE codigo=?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
           ps.setString(1, model.getDescricao());
            ps.setInt(2, model.getCentro_custo());
            ps.setString(3, model.getObs());
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
        sql = "DELETE FROM contas  WHERE codigo=" + Codigo;
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

        sql = "SELECT codigo FROM  contas WHERE descricao like '" + nome + "'";
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
        sql = "SELECT descricao FROM contas WHERE codigo =" + Codigo;
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
    public String getCentroByDescricao(String descricao) {
        sql = "SELECT t.descricao FROM  contas c inner join centro_custo t on c.centro_custo=t.codigo where c.descricao like '%"+descricao+"%'";
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
        sql = "SELECT descricao AS Descricao FROM  contas";
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
        sql = "SELECT descricao AS Descricao FROM  contas WHERE codigo=" + codigo;
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
    public ArrayList<String> getDescricaoByCentroCusto(String centro) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT c.descricao AS Descricao FROM  contas c INNER JOIN centro_custo cc on cc.codigo=c.centro_custo WHERE cc.descricao like '%" + centro+"%'";
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
    public ArrayList<Contas> getAll(int order) {
        ArrayList<Contas> list = new ArrayList<>();
        sql = "SELECT c.codigo as id ,c.descricao as descricao,c.obs as obs,c.centro_custo as centro FROM  contas c  order by "+order+"";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add( new Contas(rs.getInt("id"), rs.getString("descricao"), rs.getInt("centro"), rs.getString("obs")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
