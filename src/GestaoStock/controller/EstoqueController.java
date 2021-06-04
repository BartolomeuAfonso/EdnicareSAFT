/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.EstoqueAtual;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class EstoqueController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarEstoque(EstoqueAtual estoque) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO estoque(codProduto,codFornecedor,estoqueCx,estoqueUn) VALUES(?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, estoque.getProduto());
            prepara.setInt(2, estoque.getFornecedor());
            prepara.setInt(3, estoque.getEstoqueCx());
            prepara.setInt(4, estoque.getEstoqueUn());
            
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean atualizarEstoque(int produto, int qtde) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  estoque set estoqueCx = ?,estoqueUn = ? WHERE codProduto=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, qtde);
            prepara.setInt(2, produto);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public void operarEstoque(int qtdeCX,int qtdUN,int produto, int fornecedor) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  estoque set estoqueCx = ?,estoqueUn  = ? WHERE codProduto=? and codFornecedor =?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, qtdeCX);
            prepara.setInt(2, qtdUN);
            prepara.setInt(3, produto);
            prepara.setInt(4, fornecedor);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

    }
    public void operarEstoque2(int qtdeCX,int qtdUN,int produto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  estoque set estoqueCx = ?,estoqueUn  = ? WHERE codProduto=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, qtdeCX);
            prepara.setInt(2, qtdUN);
            prepara.setInt(3, produto);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

    }

    public int getUnidadeByFornecedor(int produto, int fornecedor) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM estoque WHERE(codProduto = " + produto + " and codFornecedor= " + fornecedor + ") ";
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueUn");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    public int getCXByFornecedor(int produto, int fornecedor) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM estoque WHERE(codProduto = " + produto + " and codFornecedor= " + fornecedor + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueUn");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getUnidadeProduto(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " select sum(estoqueUn) as estoque FROM estoque WHERE(codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoque");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    public ArrayList getQuantidadeByunidade(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " select sum(estoqueCX) as cx,sum(estoqueUN) as un FROM estoque WHERE(codProduto = " + produto + ") ";
        ArrayList lista = new ArrayList<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(rs.getString("cx"));
                lista.add(rs.getString("un"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
   
    public int getAllEstoque() throws SQLException {
        conexao.Connectando();
        String sql = " select sum(estoqueUn) as estoque FROM estoque ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoque");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    public int getEstoqueBaixo() throws SQLException {
        conexao.Connectando();
        String sql = " SELECT count(e.codProduto) as estoquesBaixo FROM estoque e inner join produto p on p.codProduto=e.codProduto where e.estoqueUn = p.estoqueMin ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoquesBaixo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    public int getCXProduto(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM estoque WHERE(codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueCX");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

}
