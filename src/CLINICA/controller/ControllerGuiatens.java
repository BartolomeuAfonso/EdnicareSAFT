/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.GuiaItens;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerGuiatens {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

     Connection con;

    public ControllerGuiatens(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarItens(GuiaItens guiaItens) {
       // conexao.Connectando();
        sql = "INSERT INTO guia_itens(codigoServico,codigoGuia,quantidade,totalGeral,elegibilidade)values(?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, guiaItens.getCodigoServico());
            ps.setInt(2, guiaItens.getCodigoGuia());
            ps.setInt(3, guiaItens.getQuantidade());
            ps.setDouble(4, guiaItens.getTotalgeral());
            ps.setString(5, guiaItens.getElegilibidade());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
     //   conexao.DesconectaBanco();
    }

    public void salvarItens1(GuiaItens guiaItens, int codigoGuia) {
       // conexao.Connectando();
        sql = "INSERT INTO guia_itens(codigoServico,codigoGuia,quantidade,totalGeral,elegibilidade)values(?,'" + codigoGuia + "',?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, guiaItens.getCodigoServico());
            //  ps.setInt(2, guiaItens.getCodigoGuia());
            ps.setInt(2, guiaItens.getQuantidade());
            ps.setDouble(3, guiaItens.getTotalgeral());
            ps.setString(4, guiaItens.getElegilibidade());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void updateGuiaItens(GuiaItens guiaItens, int codigoGuia, int codigoProduto) {
        //conexao.Connectando();
        sql = "UPDATE guia_itens set quantidade=?,totalGeral=? WHERE codigoGuia=" + codigoGuia + " AND codigoServico=" + codigoProduto;
        System.out.println("Update:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, guiaItens.getQuantidade());
            ps.setDouble(2, guiaItens.getTotalgeral());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void eliminar(int codigoGuia) {
        //conexao.Connectando();
        sql = "DELETE FROM guia_itens WHERE codigoGuia=" + codigoGuia;
        System.out.println("Update:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void elimarItensGuia(int codigoGuia, int codigoServico) {
       // conexao.Connectando();
        sql = "DELETE FROM guia_itens WHERE codigoGuia =" + codigoGuia + " AND  codigoServico=" + codigoServico;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Item eliminado com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }
}
