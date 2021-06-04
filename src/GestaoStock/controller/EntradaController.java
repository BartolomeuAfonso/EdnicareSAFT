/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.EntradaItems;
import GestaoStock.modelo.EntradaProduto;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class EntradaController {

    ConexaoBancos conexao = new ConexaoBancos();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    String sql;

    public boolean salvarEntrada(EntradaProduto modelo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO entrada_produto(dataEntrada,codFornecedor,fatura,datafatura,codUser) VALUES(now(),?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            //  prepara.setString(1, modelo.getCadastro());
            prepara.setInt(1, modelo.getFornecedor());
            prepara.setString(2, modelo.getFatura());
            prepara.setString(3, modelo.getDataFatura());
            prepara.setInt(4, modelo.getFarmanceutico());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean salvarEntrada1(EntradaProduto modelo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO entrada(dataEntrada,codFornecedor,factura,codigoUser) VALUES(now(),?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            //  prepara.setString(1, modelo.getCadastro());
            prepara.setInt(1, modelo.getFornecedor());
            prepara.setString(2, modelo.getFatura());
            // prepara.setString(3, modelo.getDataFatura());
            prepara.setInt(3, modelo.getFarmanceutico());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public int getLastFactura() {
        conexao.Connectando();
        try {
            sql = "select max(identrada) as max from entrada";
            PreparedStatement prepara = conexao.conn.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public boolean salvarEntradaItem(EntradaItems modeloItem) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO item_entrada("
                + "codentrada,codProduto,qtdeEntrada,dataValidade,lote,valorCompraUnitario,"
                + "valorVendaUnitario,lucroVendaUnitario,codCentroestoque,dataFabricacao,"
                + "prasoValidade,codUnidade,qtdeUnidade,valorEntrada,lucroGeral) "
                + "VALUES(?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Insert:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setLong(1, modeloItem.getIdEntrada());
            prepara.setInt(2, modeloItem.getIdProduto());
            prepara.setDouble(3, modeloItem.getQuantidadeEntrada());
            // prepara.setString(4, modeloItem.getDataValidade());
            prepara.setString(4, modeloItem.getLote());
            prepara.setDouble(5, modeloItem.getPrecoEntradaItem());
            prepara.setDouble(6, modeloItem.getPrecoSaidaItem());
            prepara.setDouble(7, modeloItem.getLucroUnitario());
            prepara.setInt(8, modeloItem.getIdCentroEstoque());
            prepara.setString(9, modeloItem.getDataFabricacao());
            prepara.setInt(10, modeloItem.getPrasoValidade());
            prepara.setInt(11, modeloItem.getIdUnidade());
            prepara.setInt(12, modeloItem.getQuantidadeUnidade());
            prepara.setDouble(13, modeloItem.getValorEntrada());
            prepara.setDouble(14, modeloItem.getLucroGeral());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean salvarEntradaItem1(EntradaItems modeloItem) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO existencia(codigoEntrada,codigoProduto,existencia,codigoCusto,codigoUnidade,dataFabricacao,dataExpiracao) VALUES(?,?,?,?,?,?,?)";
        System.out.println("Insert:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setLong(1, modeloItem.getIdEntrada());
            prepara.setInt(2, modeloItem.getIdProduto());
            prepara.setDouble(3, modeloItem.getQuantidadeEntrada());
            prepara.setInt(4, modeloItem.getIdCentroEstoque());
            prepara.setInt(5, modeloItem.getIdUnidade());
            prepara.setDate(6, (Date) modeloItem.getDate());
            prepara.setDate(7, (Date) modeloItem.getDate1());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean actualizarPraso(EntradaItems modeloItem) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE item_entrada set prasoValidade where codProduto=? and codItem = ?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, modeloItem.getPrasoValidade());
            prepara.setInt(2, modeloItem.getIdProduto());
            prepara.setInt(3, modeloItem.getCodItem());

            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean diminuirEstoque(int codigoProduto, int quantidade) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE existencia set existencia =existencia - '" + quantidade + "' where codigoProduto=" + codigoProduto;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean aumentarEstoque(int codigoProduto, int quantidade) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE existencia set existencia =existencia + '" + quantidade + "' where codigoProduto=" + codigoProduto;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean actualizarEstoque(int codigoProduto, int quantidade) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE existencia set existencia ='" + quantidade + "' where codigoProduto=" + codigoProduto;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public String getLoteProduto(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT max(lote) as lote FROM item_entrada WHERE (codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("lote");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public int getPrasoValidade(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT max(prasoValidade) as praso FROM item_entrada WHERE (codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("praso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public String getCentroCusto(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT max(ce.descricao) as centro FROM item_entrada i inner join centro_estoque ce on i.codCentroestoque=ce.codCentrocusto WHERE (i.codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("centro");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public String getUnidade(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT max(un.descricaounidade) as un FROM item_entrada i inner join unidademedida un on i.codUnidade=un.codUnidade WHERE (i.codProduto = " + produto + ") ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("un");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
