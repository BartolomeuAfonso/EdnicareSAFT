/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.Seguradora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerSeguradora {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerSeguradora(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Seguradora seguradora) {
        //conexao.Connectando();
        sql = "INSERT INTO empresaseguros(designacao,telefone,nif,endereco,email)values(?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, seguradora.getNomeEmpresa());
            ps.setString(2, seguradora.getTelefone());
            ps.setString(3, seguradora.getNif());
            ps.setString(4, seguradora.getEndereco());
            ps.setString(5, seguradora.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeCategoria() {
        //   conexao.Connectando();
        sql = "SELECT * FROM empresaseguros e WHERE e.designacao <>'PARTICULAR'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getParticular() {
        // conexao.Connectando();
        sql = "SELECT * FROM empresaseguros e WHERE e.designacao ='PARTICULAR'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getTodasSeguradoras() {
        //conexao.Connectando();
        sql = "SELECT * FROM empresaseguros e ";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeEmpresa(String nome) {
//        conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM empresaseguros p\n"
                + "WHERE designacao LIKE '%" + nome + "%'";
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

    public ArrayList<String> getTodasArea() {
        // conexao.Connectando();
        sql = "SELECT * FROM areafuncional a";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("a.descricaoArea"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getTodaporCodigo(int codigo) {
        // conexao.Connectando();
        sql = "SELECT e.designacao from empresaseguros e order by idSeguros ="+codigo+" desc";
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }
    public ArrayList<String> getTodaporNome(String codigo) {
        // conexao.Connectando();
        sql = "SELECT e.designacao from empresaseguros e order by designacao ='"+codigo+"' desc";
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigoSeguradora(String designacao) {
        //conexao.Connectando();
        sql = "SELECT idSeguros from empresaseguros where designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idSeguros");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public double getDesconto(int codigo) {
        //conexao.Connectando();
        sql = "SELECT desconto from empresaseguros where idSeguros =" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("desconto");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoNumerador(String designacao) {
        //conexao.Connectando();
        sql = "SELECT numeradorConsulta from empresaseguros where designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("numeradorConsulta");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
