/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.EntradaItems;
import GestaoStock.modelo.Produto;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Utilitarios;

/**
 *
 * @author El Router
 */
public class ProdutoController {

    ConexaoBancos conexao = new ConexaoBancos();
    PreparedStatement ps;
    ResultSet rs;
    Utilitarios util = new Utilitarios();

    public boolean salvarProduto(Produto produto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO produto(descricao,estoqueCritico,estoqueMin,dataCadastro,codCategoria,descricaoAdicional,codigoProduto,estoqueAtual) VALUES(?,?,?,?,?,?,?,?)";

        try {
            ps = conexao.conn.prepareStatement(inserir);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getEstoqueCritico());
            ps.setDouble(3, produto.getEstoqueMin());
            ps.setString(4, produto.getDataDadastro());
//            ps.setInt(5, produto.getCodUnidade());
            ps.setInt(5, produto.getCodCategoria());
            ps.setString(6, produto.getDescricaoAdicional());
            ps.setString(7, produto.getCodigoProduto());
            ps.setInt(8, produto.getEstoqueAtual());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean actualizarProduto(Produto produto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE produto set descricao = ?,estoqueCritico=?,estoqueMin=?,codCategoria=?,descricaoAdicional=?,codigoProduto=?"
                + "WHERE codProduto = ?";

        try {
            PreparedStatement ps = conexao.conn.prepareStatement(inserir);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getEstoqueCritico());
            ps.setDouble(3, produto.getEstoqueMin());
            ps.setInt(4, produto.getCodCategoria());
            ps.setString(5, produto.getDescricaoAdicional());
            ps.setString(6, produto.getCodigoProduto());
            ps.setInt(7, produto.getCodProduto());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean salvarPrecoProduto(int cod, double compra, double venda) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO produtovenda(codProduto,valorCompra,valorUnitario) VALUES(?,?,?)";
        System.out.println("Inserir Produto Venda:" + inserir);
        try {
            PreparedStatement ps = conexao.conn.prepareStatement(inserir);
            ps.setInt(1, cod);
            ps.setDouble(2, compra);
            ps.setDouble(3, venda);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO 404:" + ex);
        }

        return true;
    }

    public boolean actualizarPrecoProduto(int cod, double compra, double venda) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE produtovenda set valorCompra = ?, valorUnitario = ? WHERE codProduto = ?";
        System.out.println("Actualizar preço Produto Venda:" + inserir);
        try {
            PreparedStatement ps = conexao.conn.prepareStatement(inserir);
            ps.setDouble(1, compra);
            ps.setDouble(2, venda);
            ps.setInt(3, cod);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO 404:" + ex);
        }

        return true;
    }

    public double getPrecoCompra(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produtovenda WHERE(codProduto = " + produto + ") ";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valorCompra");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public double getPrecoUnitario(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produtovenda WHERE(codProduto = " + produto + ") ";
        System.out.println("Preço de Venda:" + sql);
        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valorUnitario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public double getPrecoUnitarioByCodBarras(String produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produtovenda WHERE(codigoProduto = '" + produto + "') ";
        System.out.println("Preço de Venda:" + sql);
        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valorUnitario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public double getPrecoUnitario1(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM item_entrada WHERE(codProduto = " + produto + ") ";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("valorVenda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getEstoqueAtual(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produto WHERE(codProduto = " + produto + ") ";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueAtual");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getEstoqueMin(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produto WHERE(codProduto = " + produto + ") ";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueMin");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getEstoqueCritico(int produto) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM produto WHERE(codProduto = " + produto + ") ";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("estoqueCritico");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getAllProdutos() throws SQLException {
        conexao.Connectando();
        String sql = " SELECT count(codproduto) as todos FROM produto";

        PreparedStatement ps = conexao.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                return rs.getInt("todos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public boolean atualizarEstoqueAtual(Produto produto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  produto set estoqueatual = estoqueatual+ ?  WHERE codProduto=? ";

        try {
            PreparedStatement ps = conexao.conn.prepareStatement(inserir);
            ps.setInt(1, produto.getEstoqueAtual());
            ps.setInt(2, produto.getCodProduto());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean operarEstoqueAtual(int produto, int qtde) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  produto set estoqueatual = ?  WHERE codProduto=? ";

        try {
            PreparedStatement ps = conexao.conn.prepareStatement(inserir);
            ps.setInt(1, qtde);
            ps.setInt(2, produto);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean existeProduto(int produto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        conexao.Connectando();
        String inserir = "SELECT * FROM produtovenda p where codProduto =" + produto;
        System.out.println("Verificar se Existe produto:" + inserir);
        PreparedStatement ps = conexao.conn.prepareStatement(inserir);
        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public int getLastProduto() {
        try {
            conexao.Connectando();
            String sql = "select max(CodProduto) as max from produto";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int codProduto(String descricao) {
        try {
            conexao.Connectando();
            String sql = "select *  from produto where descricao = '" + descricao + "'";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt("codproduto");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<String> getNomeServicoporCodigoBarra(String codigo) {
        conexao.Connectando();
        String sql = "select *  from produto where codigoProduto = '" + codigo + "'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String codBarras(String descricao) {
        try {
            conexao.Connectando();
            String sql = "select *  from produto where descricao = '" + descricao + "'";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("Código de Barra:" + sql);
            try {
                if (rs.next()) {
                    return rs.getString("codigoProduto");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String getdescricao(String descricao) {
        try {
            conexao.Connectando();
            String sql = "select *  from produto where descricao = '" + descricao + "'";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getString("descricaoAdicional");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public ArrayList<String> getNomeServicosPorlike(String designacao) {
        conexao.Connectando();
        String sql = "SELECT  descricao FROM produto s where descricao like '%" + designacao + "%' ";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String getdescricaoByCod(int cod) {
        try {
            conexao.Connectando();
            String sql = "select *  from produto where codProduto = " + cod + "";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getString("descricao");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public int codigoByCodBarras(String descricao) {
        try {
            conexao.Connectando();
            String sql = "select *  from produto where codigoproduto = '" + descricao + "'";
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt("codProduto");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Venda Média Diária
    public double calculoVMD(int produto, String d1, String d2) {
        try {
            double media = 0, soma = 0, result = 0;
            int count = util.getQtdVendas(produto, d1, d2).size();
            for (int i = 0; i < util.getQtdVendas(produto, d1, d2).size(); i++) {
                soma = soma + Integer.parseInt(util.getQtdVendas(produto, d1, d2).get(i).toString());
//                System.out.println("elemento " + util.getQtdVendas(produto).get(i));
//                System.out.println("soma " + soma);
                if (count == 0) {
                    result = 0;
                } else {
                    result = (soma / count);
                }
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double calculoVMD1(int produto) {
        try {
            double media = 0, soma = 0, result = 0;
            int count = util.getQtdVendas1(produto).size();
            for (int i = 0; i < util.getQtdVendas1(produto).size(); i++) {
                soma = soma + Integer.parseInt(util.getQtdVendas1(produto).get(i).toString());
//                System.out.println("soma " + soma);
                if (count == 0) {
                    result = 0;
                } else {
                    result = (soma / count);
                }
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    //Verificação do Nível de Serviço Praticado
    public double calculoVNSP(int produto, String d1, String d2) {
        double result = 0;
        try {
            double vmd = calculoVMD(produto, d1, d2);
//            System.out.println("VMD " + vmd);
            int estoque_atual = getEstoqueAtual(produto);
//            System.out.println("E.A.U " + estoque_atual);
            if (vmd == 0) {
                result = 0;
            } else {
                result = (estoque_atual / vmd);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Verificação do Nível de Serviço Praticado
    public double calculoVNSP1(int produto) {
        double result = 0;
        try {
            double vmd = calculoVMD1(produto);
//            System.out.println("VMD " + vmd);
            int estoque_atual = getEstoqueAtual(produto);
//            System.out.println("E.A.U " + estoque_atual);
            if (vmd == 0) {
                result = 0;
            } else {
                result = (estoque_atual / vmd);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Produto> getAll() {
        conexao.Connectando();
        try {
            String sql = "SELECT * FROM produto ";
            ArrayList lista = new ArrayList<>();
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(new Produto(
                            rs.getInt("codProduto"), rs.getString("descricao"),
                            rs.getInt("estoqueAtual")
                    )
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<EntradaItems> getAllEntrados() {
        conexao.Connectando();
        try {
            String sql = "SELECT\n"
                    + "     *\n"
                    + "FROM\n"
                    + "     `entrada_produto` entrada_produto INNER JOIN `item_entrada` item_entrada ON entrada_produto.`codEntrada` = item_entrada.`codEntrada` ";
            ArrayList lista = new ArrayList<>();
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(new EntradaItems(rs.getInt("codProduto"), rs.getString("dataValidade")));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList getAllEntrados2(int cod) {
        conexao.Connectando();
        try {
            String sql = "SELECT max(dataValidade) as validade,max(codItem) as item FROM item_entrada i where codProduto=" + cod + "";
            ArrayList lista = new ArrayList<>();
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(rs.getInt("item"));
                    lista.add(rs.getString("validade"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
