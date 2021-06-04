/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Categorias;
import entidades.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class StatusController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public StatusController(Connection con) {
        this.con = con;
    }

    public void Salvar(Status c) {
        sql = "INSERT INTO status(\"Descricao\") VALUES (?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDescricao());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public ArrayList<String> listar() {
        sql = "SELECT * FROM status order by 1";
        ArrayList<String> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<String> getMesalistar() {
        sql = "SELECT * FROM \"statusMesa\" ORDER BY 1";
        System.out.println("Teste:" + sql);
        ArrayList<String> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM status WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public int Codigo(String nome) {
        sql = "SELECT \"Codigo\" FROM status s where \"Descricao\" ='" + nome + "'";
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int CodigoMesa(String nome) {
        sql = "SELECT \"Codigo\" FROM  \"statusMesa\"  where \"Descricao\" ='" + nome + "'";
        System.out.println("Teste:" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String NomeCategoria(int Codigo) {
        sql = "SELECT \"Descricao\" FROM status WHERE Codigo =" + Codigo;
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public int CodigoStatus(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM status WHERE \"Descricao\" ='" + nome + "'";
        System.out.println("Teste:" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    
     public List getAllAnos() {
        List ObjectoCompleto = new ArrayList();
        sql = "SELECT * FROM ano order by 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                ObjectoCompleto.add(rs.getString("Designacao"));
            }
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return ObjectoCompleto;
    }

}
