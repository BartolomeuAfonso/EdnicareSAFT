/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.TipoFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class TipoFuncionarioController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public TipoFuncionarioController(Connection con) {
        this.con = con;
    }

    public void Salvar(entidades.TipoFuncionario c) {
        sql = "INSERT INTO tipoFuncionarios(\"Descricao\")VALUES (?)";
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

    public ArrayList<entidades.TipoFuncionario> listar() {
        sql = "SELECT * FROM \"tipoFuncionarios\" order by 1;";
        ArrayList<entidades.TipoFuncionario> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoFuncionario tipoFuncionario = new TipoFuncionario();
                tipoFuncionario.setCodigo(rs.getInt("Codigo"));
                tipoFuncionario.setDescricao(rs.getString("Descricao"));
                list.add(tipoFuncionario);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }

    public ArrayList<String> getTipoFuncionario() {
        sql = "SELECT * FROM \"tipoFuncionarios\" order by 1;";
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
        sql = "DELETE FROM tipoFuncionarios WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public int CodigoTipoFuncionario(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM \"tipoFuncionarios\" WHERE \"Descricao\" ='" + nome + "'";
        System.out.println("Teste" + sql);
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

    public String TipoFuncionario(int Codigo) {
        sql = "SELECT \"Descricao\" FROM tipoFuncionarios WHERE Codigo =" + Codigo;
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

}
