/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.utilizacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class Utilitarios {

    Connection con;
    ConexaoBancos conexao = new ConexaoBancos();

    public Vector preencherCombobox(JComboBox cmb, String tabela, String campo) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DISTINCT* FROM " + tabela + " order by 2";
        Vector lista = new Vector<>();
        ResultSet rs = conexao.ejecutarSQLSelect(sql);
//        PreparedStatement prepara = con.prepareStatement(sql);
//        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString(campo));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cmb.setModel(new DefaultComboBoxModel(lista));
        return lista;
    }

    public Vector preencherCombobox1(JComboBox cmb, String tabela, String campo) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DISTINCT* FROM " + tabela + " WHERE codForma <>3 order by 1";
        Vector lista = new Vector<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString(campo));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cmb.setModel(new DefaultComboBoxModel(lista));
        return lista;
    }

    public Vector preencherCombobox(JComboBox cmb, String query) throws SQLException {
        conexao.Connectando();
        Vector lista = new Vector<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(query);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cmb.setModel(new DefaultComboBoxModel(lista));
        return lista;
    }

    public Vector preencherComboboxVenda(JComboBox cmb, String tabela, String campo) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DISTINCT* FROM " + tabela + " where estoqueAtual > estoqueCritico order by 2";
        Vector lista = new Vector<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString(campo));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cmb.setModel(new DefaultComboBoxModel(lista));
        return lista;
    }

    public Vector preencherReemprimir(JComboBox cmb, String tabela, String data1, String data2) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DISTINCT* FROM " + tabela + " where dataVenda between '" + data1 + "' and '" + data2 + "' and statusVenda=1 order by 2";
        Vector lista = new Vector<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString("codVenda"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cmb.setModel(new DefaultComboBoxModel(lista));
        return lista;
    }

    public ArrayList getQtdVendas(int codProduto, String d1, String d2) throws SQLException {
        conexao.Connectando();
        String sql = "Select dataVenda,codProduto,sum(qtdItem) as qt FROM venda v inner join item_venda i on i.codVenda=v.codVenda\n"
                + "where codProduto=" + codProduto + " and datavenda between '" + d1 + "' and '" + d2 + "' group by i.codProduto, datavenda order by qt DESC";
        ArrayList lista = new ArrayList();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getInt("qt"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public ArrayList getQtdVendas1(int codProduto) throws SQLException {
        conexao.Connectando();
        String sql = "Select dataVenda,codProduto,sum(qtdItem) as qt FROM venda v inner join item_venda i on i.codVenda=v.codVenda\n"
                + "where codProduto=" + codProduto + " group by i.codProduto, datavenda order by qt DESC";
        ArrayList lista = new ArrayList();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getInt("qt"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int getItemsVendidos(int codProduto) throws SQLException {
        conexao.Connectando();
        String sql = "Select sum(qtdItem) as qt FROM venda v inner join item_venda i on i.codVenda=v.codVenda\n"
                + "where codProduto=" + codProduto + "";
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                return (rs.getInt("qt"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCodigo(String tabela, String campo, String descricao, String valor) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT * FROM " + tabela + " WHERE(" + campo + " = '" + descricao.trim() + "')";
        System.out.println("SQL:" + sql);
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            if (rs.next()) {
                return rs.getInt(valor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 1;
    }

    public int getDiferencaDatas(String data1, String data2) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT DATEDIFF('" + data2 + "','" + data1 + "')";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 1;
    }

    public int getLastInsert(String tabela, String campo, int t) throws SQLException {
        String sql = "";
        if (t == 3) {
            sql = "SELECT max(" + campo + ") as ultimo FROM " + tabela + "";
        } else {
            sql = "SELECT max(" + campo + ") as ultimo FROM venda where tipovenda = " + t + "";
        }
        System.out.println("query : " + sql);
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            if (rs.next()) {
                return rs.getInt("ultimo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int quantidadeDias(String data1, String data2) {
        try {
            conexao.Connectando();

            String sql = "SELECT DATEDIFF(day,'" + data1 + "', '" + data2 + "') AS dias";
            PreparedStatement prepara = conexao.conn.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            int next = 0;
            try {
                if (rs.next()) {
                    return rs.getInt("dias");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean existe(String sql) {
        try {
            System.out.println("Sql:" + sql);
            conexao.Connectando();
            PreparedStatement prepara = conexao.conn.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                return false;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
