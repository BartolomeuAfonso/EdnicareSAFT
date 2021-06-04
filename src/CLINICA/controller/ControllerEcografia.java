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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Ba
 */
public class ControllerEcografia {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerEcografia(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public String getDesignacao(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT designacao from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("designacao");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getConclusao(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT conclusao from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("conclusao");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getBiometria(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT biometria from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("biometria");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getAbdomen(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT abdomen from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("abdomen");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getAnexofetal(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT anexoFetais from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("anexoFetais");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getCranio(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT cranio from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("cranio");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getFace(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT face from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("face");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getOvario(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT ovarios from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("ovarios");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getResultado(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT conclusao from resutado_ecografia where codigoProduto=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("conclusao");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public int getLastFactura() {
        try {
            sql = "select max(idresultadoEcografia) as max from resultadoecografia";
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void alter(String codigo) {

//        conexao.Connectando();
        sql = "UPDATE pedidoecografia SET estado='SIM' WHERE idPedido=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }
}
