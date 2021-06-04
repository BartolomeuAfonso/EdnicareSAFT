/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Servicos;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerServico {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerServico(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Servicos servicos) {

        // conexao.Connectando();
        sql = "INSERT INTO servicos(designacao,preco,codigoCategoria,codigoUtilizador,dataCadastro,codigoStatus,preco1,preco2,preco3,preco4,preco5,preco6,preco7,codigoBarra,stocavel)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, servicos.getDesignacao());
            ps.setDouble(2, servicos.getPreco());
            ps.setInt(3, servicos.getCodigoCategoriaServico());
            ps.setInt(4, servicos.getCodigoUtilizador());
            ps.setDate(5, (Date) servicos.getDataCadastro());
            ps.setInt(6, servicos.getCodigoStatus());
            ps.setDouble(7, servicos.getPreco1());
            ps.setDouble(8, servicos.getPreco2());
            ps.setDouble(9, servicos.getPreco3());
            ps.setDouble(10, servicos.getPreco4());
            ps.setDouble(11, servicos.getPreco5());
            ps.setDouble(12, servicos.getPreco6());
            ps.setDouble(13, servicos.getPreco7());
            ps.setString(14, servicos.getCodigoBarra());
            ps.setString(15, servicos.getStocavel());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void salvarProduto(Servicos servicos) {

        // conexao.Connectando();
        sql = "INSERT INTO servicos(designacao,preco,codigoCategoria,codigoUtilizador,dataCadastro,codigoStatus,preco1,codigoBarra,stocavel,precoCompra,margemLucro,dataFabricacao)values(?,?,?,?,?,?,?,?,?,?,?,?)";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, servicos.getDesignacao());
            ps.setDouble(2, servicos.getPreco());
            ps.setInt(3, servicos.getCodigoCategoriaServico());
            ps.setInt(4, servicos.getCodigoUtilizador());
            ps.setDate(5, (Date) servicos.getDataCadastro());
            ps.setInt(6, servicos.getCodigoStatus());
            ps.setDouble(7, servicos.getPreco1());
            ps.setString(8, servicos.getCodigoBarra());
            ps.setString(9, servicos.getStocavel());
            ps.setDouble(10, servicos.getPrecoCompra());
            ps.setDouble(11, servicos.getMargemLucro());
            ps.setDate(12, (Date) servicos.getDataFabricacao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void editar(Servicos servicos, int codigo) {

//        conexao.Connectando();
        sql = "UPDATE servicos SET designacao=?,preco=?,preco1=?,preco2=?,preco3=?,preco4=?,preco5=?,preco6=?,preco7=?,codigoBarra=?,codigoCategoria=?,codigoUtilizador=?,stocavel=? WHERE idServico=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, servicos.getDesignacao());
            ps.setDouble(2, servicos.getPreco());
            ps.setDouble(3, servicos.getPreco1());
            ps.setDouble(4, servicos.getPreco2());
            ps.setDouble(5, servicos.getPreco3());
            ps.setDouble(6, servicos.getPreco4());
            ps.setDouble(7, servicos.getPreco5());
            ps.setDouble(8, servicos.getPreco6());
            ps.setDouble(9, servicos.getPreco7());
            ps.setString(10, servicos.getCodigoBarra());
            ps.setInt(11, servicos.getCodigoCategoriaServico());
            ps.setInt(12, servicos.getCodigoUtilizador());
            ps.setString(13, servicos.getStocavel());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void editarProduto(Servicos servicos, int codigo) {

//        conexao.Connectando();
        sql = "UPDATE servicos SET designacao=?,preco=?,preco1=?,precoCompra=?,margemLucro=?,codigoBarra=?,codigoCategoria=?,codigoUtilizador=?,stocavel=?,dataCadastro=?,dataFabricacao=? WHERE idServico=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, servicos.getDesignacao());
            ps.setDouble(2, servicos.getPreco());
            ps.setDouble(3, servicos.getPreco1());
            ps.setDouble(4, servicos.getPrecoCompra());
            ps.setDouble(5, servicos.getMargemLucro());
            ps.setString(6, servicos.getCodigoBarra());
            ps.setInt(7, servicos.getCodigoCategoriaServico());
            ps.setInt(8, servicos.getCodigoUtilizador());
            ps.setString(9, servicos.getStocavel());
            ps.setDate(10, (Date) servicos.getDataCadastro());
            ps.setDate(11, (Date) servicos.getDataFabricacao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeServicos() {
        //    conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM servicos s where codigoStatus=1 AND designacao <> 'CO - PAGAMENTO' AND designacao <> 'CAUCAO'";
//        sql = "SELECT UPPER(designacao) AS designacao FROM servicos s where codigoCategoria =1 or codigoCategoria =5 and codigoStatus=1 AND designacao <> 'CO - PAGAMENTO' AND designacao <> 'CAUCAO'";
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

    public ArrayList<String> getNomeProdutos() {
        //    conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM servicos s where codigoStatus=1";
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

    public ArrayList<String> getNomeProdutosExistenteStock() {
        //    conexao.Connectando();
        sql = "SELECT DISTINCT UPPER(s.designacao) as designacao FROM existencia e join servicos s on e.codigoProduto=s.idServico where codigoStatus=1";
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

    public ArrayList<String> getNomeProdutosStocavel() {
        //    conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM servicos s where s.stocavel='SIM' where codigoStatus=1";
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
//    public ArrayList<String> getNomeProdutos() {
//            conexao.Connectando();
//        sql = "SELECT UPPER(designacao) AS designacao FROM servicos s where codigoStatus=1 AND codigoCategoria > 12";
//        ArrayList<String> lista = new ArrayList<>();
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                lista.add(rs.getString("designacao"));
//            }
//        } catch (SQLException ex) {
//        }
//        return lista;
//    }

    public ArrayList<String> getNomeCaucao() {
        //conexao.Connectando();
        sql = "SELECT s.designacao FROM servicos s where s.designacao ='CO - PAGAMENTO' or s.designacao ='CAUCAO'  and codigoStatus=1";
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

    public ArrayList<String> getNomeCoPagamento() {
        //conexao.Connectando();
        sql = "SELECT s.designacao FROM servicos s where s.designacao='CO - PAGAMENTO' and codigoStatus=1";
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

    public ArrayList<String> getNomeServicosPorlike(String designacao) {
        //conexao.Connectando();
        sql = "SELECT  designacao FROM servicos s where designacao like '%" + designacao + "%' and codigoStatus=1 ";
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

    public String getNomeServicosPorlikePreco(String designacao) {
        //conexao.Connectando();
        sql = "SELECT  preco FROM servicos s where designacao like '%" + designacao + "%' and codigoStatus=1";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("preco");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<String> getNomeServicosPorlikeStocavel(String designacao) {
        //conexao.Connectando();
        sql = "SELECT  designacao FROM servicos s where designacao like '%" + designacao + "%' AND s.stocavel='SIM' and codigoStatus=1";
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

    public ArrayList<String> getNomeServicosPorlikeStocavelSock(String designacao) {
        //conexao.Connectando();
        sql = "SELECT DISTINCT UPPER(s.designacao) as designacao FROM existencia e join servicos s on e.codigoProduto=s.idServico\n"
                + "where s.designacao like '%" + designacao + "%' and codigoStatus=1";
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

    public ArrayList<String> getNomeServicoporCodigo(int codigo) {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where idServico=" + codigo + " and codigoStatus=1";
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

    public ArrayList<String> getNomeServicoporCodigoBarra(String codigo) {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where codigoBarra ='" + codigo + "' and codigoStatus=1";
        System.out.println("Sql:" + sql);
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

    public ArrayList<String> getNomeServicoHonorario() {
        //conexao.Connectando();
        sql = "SELECT designacao FROM servicos s where codigoCategoria <>2 and codigoCategoria <>3 and codigoStatus=1";
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

    public ArrayList<String> getCodigoBuscarExame(int codigo) {
        //conexao.Connectando();
        sql = "SSELECT designacao FROM servicos s where codigoCategoria=2 and idServico=" + codigo;
//        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getCodigoRaio(int codigo) {
        // conexao.Connectando();
        sql = "SSELECT designacao FROM servicos s where codigoCategoria=3 and idServico=" + codigo;
//        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getCodigoBuscarExamePorDesginacao(String nome) {
        //conexao.Connectando();
        sql = "SELECT designacao FROM servicos s where codigoCategoria=2 and designacao LIKE '" + nome + "%'";
//        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getCodigoBuscarRaixoPorDesginacao(String nome) {
        //conexao.Connectando();
        sql = "SELECT designacao FROM servicos s where codigoCategoria=3 and designacao LIKE '" + nome + "%'";
//        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getNomeExames() {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where codigoCategoria=2";
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

    public ArrayList<String> getNomeExamesPorLike(String designacao) {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where designacao like '" + designacao + "%' and codigoCategoria=2";
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

    public ArrayList<String> getNomeRadiografia() {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where codigoCategoria=3";
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

    public ArrayList<String> getNomeRadiografiaporLike(String designacao) {
        //conexao.Connectando();
        sql = "SELECT * FROM servicos s where designacao like '" + designacao + "%' and codigoCategoria=3";
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

    public ArrayList<String> getNomecografia() {
        //  conexao.Connectando();
        sql = "SELECT * FROM servicos s where codigoCategoria=5";
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

    public ArrayList<String> getNomecografialike(String designacao) {
        // conexao.Connectando();
        sql = "SELECT s.designacao as designacao FROM servicos s where s.designacao like '" + designacao + "%' and codigoCategoria=5";
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

    public ArrayList<String> getExamesGeral() {
        conexao.Connectando();
        sql = "SELECT * FROM servicos s where codigoCategoria <>2 and codigoCategoria <>3";
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

    public ArrayList<String> getConsultaMarcao() {
        //conexao.Connectando();
        sql = "SELECT s.designacao FROM servicos s where s.codigoCategoria=1 or s.codigoCategoria=3 or codigoCategoria=2";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

//    public ArrayList<String> getServicoAtendido(int codigoCliente) {
//        conexao.Connectando();
//        sql = "SELECT designacao from pedidoproduto p\n"
//                + "inner join pedidoprodutoitens i on p.idPedido=codigoPedidoProduto\n"
//                + "inner join servicos s on s.idServico=i.codigoProduto\n"
//                + "WHERE p.dataPedido=CURDATE() AND p.codigoCliente=" + codigoCliente;
//        System.out.println("sql:" + sql);
//        ArrayList<String> lista = new ArrayList<>();
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                lista.add(rs.getString(" designacao "));
//
//            }
//        } catch (SQLException ex) {
//        }
//        return lista;
//    }
    public int getCodigoProdutoCategoriaExames(String produto) {
        //conexao.Connectando();
        sql = "select codigo from categoria_exames where designacao = '" + produto.trim() + "'";
        System.out.println("sql ----> " + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return 0;
    }

    public float getPrecoAdvance(String produto) {
        // conexao.Connectando();
        sql = "select preco1 from servicos where designacao = '" + produto + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco1");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public float getPrecoUnisaude(String produto) {
        //conexao.Connectando();
        sql = "select preco2 from servicos where designacao = '" + produto + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco2");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public float getPrecoSaudemais(String produto) {
        // conexao.Connectando();
        sql = "select preco3 from servicos where designacao = '" + produto + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco3");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public float getPrecoFidelidade(String produto) {
        //   conexao.Connectando();
        sql = "select preco4 from servicos where designacao = '" + produto + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco4");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public float getPrecoAngolaTelecom(String produto) {
        //     conexao.Connectando();
        sql = "select preco6 from servicos where designacao = '" + produto + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco6");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;

    }

    public float getPrecoEmpresa(String produto) {
        //conexao.Connectando();
        sql = "select preco7 from servicos where designacao = '" + produto + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco7");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;

    }

    public float getPrecoSaham(String produto) {
        //conexao.Connectando();
        sql = "select preco5 from servicos where designacao = '" + produto + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("preco5");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;

    }

    public float getPrecoParticular(String produto) {
        //conexao.Connectando();
        sql = "select preco from servicos where designacao = '" + produto + "'";
        System.out.println("");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<String> getServico(int codigoCliente) {
        //conexao.Connectando();
        sql = "SELECT designacao from pedidoproduto p\n"
                + "inner join pedidoprodutoitens i on p.idPedido=codigoPedidoProduto\n"
                + "inner join servicos s on s.idServico=i.codigoProduto\n"
                + "WHERE p.dataPedido=CURDATE() AND p.codigoCliente=" + codigoCliente;
        System.out.println("sql:" + sql);
        // ArrayList<String> lista = new ArrayList<>();
        List<String> items = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                items.add(rs.getString("designacao"));

            }
        } catch (SQLException ex) {
        }
        return items;
    }

    public void eliminar(int codigo) {
        //  conexao.Connectando();
        sql = "update servicos set codigoStatus=2 where idServico=" + codigo;
//        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public int getCodigoServico(String designacao) {

        sql = "SELECT idServico from servicos where designacao ='"+designacao+"'";
        System.out.println("Codigo de Serviço:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idServico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

      public int getCodigoServicoprLike(String designacao) {

        sql = "SELECT idServico from servicos where designacao LIKE '%" + designacao + "%'";
        System.out.println("Codigo de Serviço:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idServico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoServicoCodigoBarra(String designacao) {
        //    conexao.Connectando();
        sql = "SELECT idServico from servicos where codigoBarra ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idServico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public String getServicoCodigoBarra(int designacao) {
        //    conexao.Connectando();
        sql = "SELECT designacao from servicos where idServico =" + designacao;
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

    public String getCodigoBarra(String designacao) {
        //    conexao.Connectando();
        sql = "SELECT codigoBarra from servicos where designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("codigoBarra");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getProdutoEstocavel(int designacao) {
        //    conexao.Connectando();
        sql = "SELECT stocavel from servicos where idServico=" + designacao;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("stocavel");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public int getCodigoTaxa(int designacao) {
        //    conexao.Connectando();
        sql = "SELECT t.taxa as taxa from servicos s inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
                + "inner join motivo m on t.codigoMotivo = m.codigo\n"
                + "where s.idServico =" + designacao;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("taxa");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getQuantidadeServico() {

        sql = "SELECT count(idServico) as Total FROM servicos s";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getQuantidadeProduto(String designacao) {

        sql = "SELECT e.existencia as quantidade FROM servicos s inner join existencia e on s.idServico=e.codigoProduto where s.designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getQuantidadeProdutoPorCodigoBarra(String designacao) {

        sql = "SELECT e.existencia as quantidade FROM servicos s inner join existencia e on s.idServico=e.codigoProduto where s.codigoBarra ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getQuantidadeProdutoCodigoBarra(String designacao) {

        sql = "SELECT e.existencia as quantidade FROM servicos s inner join existencia e on s.idServico=e.codigoProduto where s.codigoBarra ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoCategoriaServico(int designacao) {
        //conexao.Connectando();
        sql = "SELECT codigoCategoria from servicos where idServico =" + designacao;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoCategoria");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public double getPreco(String designacao) {
        // conexao.Connectando();
        sql = "SELECT preco from servicos where designacao ='" + designacao + "'";
       System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public double getPrecoporCodigoBarra(String designacao) {
        // conexao.Connectando();
        sql = "SELECT preco from servicos where codigoBarra ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoProdutoItemByName(String produto) {
        // conexao.Connectando();
        sql = "select distinct s.idSErvico AS ID from servicos s inner join examesintegrado p on s.idServico = p.codigoServico where s.designacao = '" + produto + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        return 0;
    }

    public int getCodigoProdutoItemByNameMicro(String produto) {
        // conexao.Connectando();
        sql = "select distinct s.idSErvico AS ID from servicos s inner join examesintegrado p on s.idServico = p.codigoServico\n"
                + "inner join categoria_exames cx on cx.codigo = p.codigoCategoria\n"
                + "where cx.designacao = '" + produto + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        return 0;
    }

    public int getCodigoProdutoItemByNameMicrobiologia(String produto) {
        // conexao.Connectando();
        sql = "select distinct s.idSErvico AS ID from servicos s inner join examesintegrado p on s.idServico = p.codigoServico\n"
                + "inner join categoria_exames cx on cx.codigo = p.codigoCategoria\n"
                + "where cx.designacao = '" + produto + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        return 0;
    }

    public int getCodigoProdutoReferencia(String produto) {
        // conexao.Connectando();
        sql = "select distinct s.idSErvico AS ID from servicos s inner join examesintegrado p on s.idServico = p.codigoServico\n"
                + "where s.designacao = '" + produto + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        return 0;
    }

    public int getCodigoProdutoItem(int codigo) {
        // conexao.Connectando();
        sql = "SELECT idExamesIntegrado FROM examesintegrado where codigoServico = " + codigo;
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;
    }

    public Vector getExamesSolicitados(int triagem, int codigoPaciente) {
        //  conexao.Connectando();
        sql = "SELECT * FROM servicos s inner join  pedidos_exames p on s.idservico =p.codigoServico\n"
                + "inner join triagem t on t.idTriagem=p.codigoTriagem\n"
                + "inner join pacientes pa on pa.idPaciente = p.codigoPaciente\n"
                + "where t.idTraigem =" + triagem + " and pa.codigoPaciente =" + codigoPaciente + "";

        System.out.println("sql: " + sql);
        Vector items = new Vector();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                items.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public Vector getCodigosProdutoItem(int codigo) {
        // conexao.Connectando();
        sql = "SELECT e.idExamesIntegrado from examesintegrado e inner join servicos s on e.codigoServico=s.idServico where s.idServico=" + codigo;
        System.out.println("Vector:" + sql);
        Vector items = new Vector();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                items.add(rs.getInt("e.idExamesIntegrado"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public int getCodigosProdutoItem(String nome, String produto) {
        //   conexao.Connectando();
        sql = "SELECT\n"
                + "     servicos.`idServico` AS servicos_idServico,\n"
                + "     examesintegrado.`idExamesIntegrado` AS codigo,\n"
                + "     examesintegrado.`designacao` AS examesintegrado_designacao\n"
                + "FROM\n"
                + "     `servicos` servicos INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "WHERE examesintegrado.`designacao` ='" + nome + "' AND servicos.`designacao` ='" + produto + "'";
        System.out.println("Nova:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return 0;
    }

    public int getCodigosProdutoItemActualizado(String nome, int codigoProduto) {
        //   conexao.Connectando();
        sql = "SELECT\n"
                + "     servicos.`idServico` AS servicos_idServico,\n"
                + "     examesintegrado.`idExamesIntegrado` AS codigo,\n"
                + "     examesintegrado.`designacao` AS examesintegrado_designacao\n"
                + "FROM\n"
                + "     `servicos` servicos INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "WHERE examesintegrado.`designacao` ='" + nome + "' AND servicos.`idServico`=" + codigoProduto;
        System.out.println("Nova:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return 0;
    }

    public boolean getProdutoEstocavel(String produto) {
        //   conexao.Connectando();
        sql = "SELECT stocavel FROM servicos s where designacao ='" + produto + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {

            return false;
        }
        return false;
    }

    ///////// Exames com parametros
    public void editarReferenciaNormal(String servicos, int codigo) {

//        conexao.Connectando();
        sql = "update servicos set referencia ='" + servicos + "' where idServico=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void editarReferencia(String servicos, int codigo) {

//        conexao.Connectando();
        sql = "update examesintegrado set referencia ='" + servicos + "' where idExamesIntegrado=" + codigo;
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
