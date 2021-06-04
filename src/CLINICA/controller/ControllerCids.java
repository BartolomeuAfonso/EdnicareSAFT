/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.ModeloEstatistica;
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
public class ControllerCids {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerCids(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public ArrayList<String> getNomeCids() {
        //   conexao.Connectando();
        sql = "SELECT * FROM cids s";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.cid"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeHospital() {
        //   conexao.Connectando();
        sql = "SELECT * FROM hospital h";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("h.descricao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomePorLike(String nome) {
        // conexao.Connectando();
        sql = "SELECT p.cid as cid from cids p where cid like  '%" + nome + "%' ";
        System.out.println("Consulta:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("cid"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigoCids(String designacao) {
        //   conexao.Connectando();
        sql = "SELECT idCids from cids where cid ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idCids");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoHospital(String designacao) {
        // conexao.Connectando();
        sql = "SELECT idHospital from hospital where descricao ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idHospital");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public ArrayList<String> getEstadoDoente() {
        //conexao.Connectando();
        sql = "SELECT * FROM estadodoente e group by 1 desc";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.estado"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigoEstado(String designacao) {
        //  conexao.Connectando();
        sql = "SELECT idestadoDoente from estadodoente where estado ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idestadoDoente");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    // Salvar parametros
    public void salvarParametro(ModeloEstatistica categoriaServicos) {
        //   conexao.Connectando();
        sql = "INSERT INTO parametro(descricao,valor,percentagem) VALUE(?,?,?)";
        // System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoriaServicos.getDesignacao());
            ps.setString(2, categoriaServicos.getCodigoFactura());
            ps.setString(3, categoriaServicos.getPercentagem());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void editarParametro(ModeloEstatistica categoriaServicos, int codigo) {
        //   conexao.Connectando();
        sql = "update parametro set descricao ='" + categoriaServicos.getDesignacao() + "', valor='" + categoriaServicos.getCodigoFactura() + "',percentagem='" + categoriaServicos.getPercentagem()+ "' WHERE codParametro=" + codigo;
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Parametros alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }
}
