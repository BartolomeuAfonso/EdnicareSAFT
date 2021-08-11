/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Probook
 */
public class ControllerPossiveisResultados {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
     Connection con;

    public ControllerPossiveisResultados(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public int getCodigo(String designacao) {
      //  conexao.Connectando();
        sql = "SELECT examesintegrado.idExamesIntegrado FROM examesintegrado where examesintegrado.designacao = '" + designacao + "' ";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return 0;
    }
     public int getCodigoporProduto(String designacao,int codigoServico) {
      //  conexao.Connectando();
        sql = "SELECT examesintegrado.idExamesIntegrado FROM examesintegrado where examesintegrado.designacao = '" + designacao + "' AND codigoServico="+codigoServico;
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return 0;
    }

    public Vector listarPossiveisResultados(int codigoProdutoItem) {
     //   conexao.Connectando();
        sql = "SELECT designacao FROM resultados_exames_possivel where codigoExameIntegrado = " + codigoProdutoItem;
        System.out.println("Sql:" + sql);
        Vector lista = new Vector();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    
    public Vector listarResultados(String exame) {
    //    conexao.Connectando();
        sql = "SELECT r.designacao from resultados_exames_possivel r inner join servicos s\n"
                + "on r.codigoServico=s.idSErvico\n"
                + "where s.designacao ='" + exame + "'";
        Vector lista = new Vector();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }

            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
