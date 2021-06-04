/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.CategoriaServicos;
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
public class ControllerCategoria {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public ControllerCategoria(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(CategoriaServicos categoriaServicos) {
        // conexao.Connectando();
        sql = "INSERT INTO categoriaservico(designacao,codigoStatus)values(?,?)";
       // System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoriaServicos.getDesignacao());
            ps.setInt(2, categoriaServicos.getCodigoStatus());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeCategoria() {
        // conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM categoriaservico s where codigoStatus=1";
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

    public int getCodigoCategoria(String designacao) {
//        conexao.Connectando();
        sql = "SELECT idcategoriaServico from categoriaservico where designacao ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idcategoriaServico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int eliminar(int codigo) {
        //  con.Connectando();
        sql = "delete from categoriaservico where idcategoriaServico =" + codigo;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
