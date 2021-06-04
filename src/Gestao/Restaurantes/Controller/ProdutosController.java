/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Produtos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class ProdutosController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    BDConexao conexao;

    public ProdutosController(Connection con) {
        this.con = con;
    }

    public boolean Salvar(Produtos produtos) {
        //,\"Categoria\"
//        sql = "INSERT INTO produtos(\"Descricao\",\"CodigoBarra\",\"PrecoVenda\",\"PrecoCompra\",\"MargemLucro\",\"DataCadastro\",\"DataExpiracao\",\"Stocavel\",\"CodigoCategoria\",\"DescricaoAdicional\",\"logotipo\",\"CodigoStatus\",\"CodigoUnidade\",\"Iva\",\"valorIva\")VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sql = "INSERT INTO produtos(\n"
                + "            \"Descricao\", \"CodigoBarra\", \"PrecoVenda\", \"PrecoCompra\", \n"
                + "            \"MargemLucro\", \"DataCadastro\", \"DataExpiracao\", \"Stocavel\", logotipo, \n"
                + "            \"CodigoUsuario\", \"CodigoStatus\", \"CodigoCategoria\", \"CodigoUnidade\", \n"
                + "            \"ValorIpc\", \"DescricaoAdicional\", \"EstoqueMin\", \"EstoqueCrit\", \n"
                + "            \"EstoqueAtual\", \"EstoqueZero\",\"margemGrosso\",\"vendaGrosso\",\"fabrico\","
                + "\"qtd\",\"margemPromocao\",\"diasAlerta\")\n"
                + "    VALUES (?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?, ?,?,?,?,?,?,?)";
        try {
//            con = new Conexao().ConexaoBD();
//            ps = con.prepareStatement(sql);
            java.sql.Date data = new java.sql.Date(produtos.getDataCadastro().getTime());
            java.sql.Date data1 = new java.sql.Date(produtos.getDataExpiracao().getTime());
            ps = con.prepareStatement(sql);
            ps.setString(1, produtos.getDescricao());
            ps.setString(2, produtos.getCodigoBarra());
            ps.setDouble(3, produtos.getVendaR());
            ps.setDouble(4, produtos.getPrecoCompra());
            ps.setDouble(5, produtos.getMargemR());
            ps.setDate(6, data);
            ps.setDate(7, data1);
            ps.setString(8, produtos.getStocavel());
            ps.setString(9, produtos.getLogotipo());
            ps.setInt(10, produtos.getCodigoUsuario());
            ps.setInt(11, 1);
            ps.setInt(12, produtos.getCateg1());
            ps.setInt(13, produtos.getCodigoUnidade());
            ps.setDouble(14, 0);
            ps.setString(15, produtos.getDescricaAct());
            ps.setDouble(16, produtos.getEstoqueMin());
            ps.setDouble(17, produtos.getEstoqueCrit());
            ps.setDouble(18, produtos.getEstoqueAct());
            ps.setString(19, produtos.getEstoqueZero());
            ps.setDouble(20, produtos.getMargemG());
            ps.setDouble(21, produtos.getVendaG());
            ps.setDate(22, (Date) produtos.getDataFabrico());
            ps.setDouble(23, produtos.getQtdCalculada());
            ps.setDouble(24, produtos.getPercPromocao());
            ps.setInt(25, produtos.getDiasAlerta());
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de Pesquisa!!" + e);
        }
        return false;

    }

    public boolean update(Produtos produtos, int codigo) {

        sql = "UPDATE produtos\n"
                + "   SET \"Descricao\"=?, \"CodigoBarra\"=?, \"PrecoVenda\"=?, \"PrecoCompra\"=?, \n"
                + "       \"MargemLucro\"=?,\"Stocavel\"=?, \n"
                + "       logotipo=?,\"CodigoCategoria\"=?, \n"
                + "       \"CodigoUnidade\"=?, \"ValorIpc\"=?, \"DescricaoAdicional\"=?, \"EstoqueMin\"=?, \n"
                + "       \"EstoqueCrit\"=?, \"EstoqueAtual\"=?, \"EstoqueZero\"=?"
                + ",\"margemGrosso\"=?,\"vendaGrosso\"=?,\"fabrico\"=?,"
                + "\"qtd\"=?,\"margemPromocao\"=?,\"DataExpiracao\"=?,\"diasAlerta\"=? Where \"Codigo\"=?";

        try {
//            con = new Conexao().ConexaoBD();
            Date data = new Date(produtos.getDataFabrico().getTime());
            Date data1 = new Date(produtos.getDataExpiracao().getTime());
            ps = con.prepareStatement(sql);
            ps.setString(1, produtos.getDescricao());
            ps.setString(2, produtos.getCodigoBarra());
            ps.setDouble(3, produtos.getVendaR());
            ps.setDouble(4, produtos.getPrecoCompra());
            ps.setDouble(5, produtos.getMargemR());
            ps.setString(6, produtos.getStocavel());
            ps.setString(7, produtos.getLogotipo());
            ps.setInt(8, produtos.getCateg1());
            ps.setInt(9, produtos.getCodigoUnidade());
            ps.setDouble(10, 0);
            ps.setString(11, produtos.getDescricaAct());
            ps.setDouble(12, produtos.getEstoqueMin());
            ps.setDouble(13, produtos.getEstoqueCrit());
            ps.setDouble(14, produtos.getEstoqueAct());
            ps.setString(15, produtos.getEstoqueZero());
            ps.setDouble(16, produtos.getMargemG());
            ps.setDouble(17, produtos.getVendaG());
            ps.setDate(18, data);
            ps.setDouble(19, produtos.getQtdCalculada());
            ps.setDouble(20, produtos.getPercPromocao());
            ps.setDate(21, data1);
            ps.setInt(22, produtos.getDiasAlerta());
            ps.setInt(23, codigo);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public void updateQtd1(int Codigo, int qtd) {
        sql = "UPDATE produtos Set \"EstoqueAtual\" =" + qtd + " WHERE \"Codigo\"=" + Codigo + "";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
//            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
    }

    public void updateQtd(int Codigo, int qtd) {
        sql = "UPDATE produtos Set \"EstoqueAtual\" =(\"EstoqueAtual\"- " + qtd + ") WHERE \"Codigo\"=" + Codigo + "";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
//            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
    }

    public void updateQtdMais(int Codigo, int qtd) {
        sql = "UPDATE produtos Set \"EstoqueAtual\" =(\"EstoqueAtual\"+ " + qtd + ") WHERE \"Codigo\"=" + Codigo + "";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
//            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
    }

    public void Eliminar(int Codigo) {
        sql = "UPDATE produtos Set \"CodigoStatus\" =2  WHERE \"Codigo\"=" + Codigo + "";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
    }

    public String Descricao(int Codigo) {
        sql = "SELECT \"Descricao\" FROM produtos WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return "";
    }

    public String DescricaoByBarcode(String barcode) {
        sql = "SELECT \"Descricao\" FROM produtos WHERE \"CodigoBarra\" like '" + barcode + "'";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return "";
    }

    public String getCategoria(int Codigo) {
        sql = "SELECT\n"
                + "     categorias.\"Descricao\" AS categorias_Descricao,\n"
                + "     produtos.\"Codigo\" AS produtos_Codigo\n"
                + "FROM\n"
                + "     \"public\".\"categorias\" categorias INNER JOIN \"public\".\"produtos\" produtos ON categorias.\"Codigo\" = produtos.\"CodigoCategoria\"\n"
                + "WHERE\n"
                + "     produtos.\"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("categorias_Descricao");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return "";
    }

    public int Codigo(String Nome) {
        sql = "SELECT \"Codigo\" as Codigo FROM produtos WHERE  \"Descricao\" like '" + Nome + "'";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public int CodigoByBarcode(String Nome) {
        sql = "SELECT \"Codigo\" as Codigo FROM produtos WHERE  \"CodigoBarra\" like '" + Nome + "'";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public int last() {
        sql = "SELECT MAX(\"Codigo\") as Codigo FROM produtos ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public double getPrecoVenda(String Codigo) {
        sql = "SELECT \"PrecoVenda\" as PrecoVenda FROM produtos WHERE \"Descricao\" like '" + Codigo + "'";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("PrecoVenda");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }
    public double getVendaGrosso(int Codigo) {
        sql = "SELECT \"vendaGrosso\" as PrecoVenda FROM produtos WHERE \"Codigo\"=" + Codigo + "";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("PrecoVenda");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public double getPrecoVendaByBarcode(String barcode) {
        sql = "SELECT \"PrecoVenda\" as PrecoVenda FROM produtos WHERE \"CodigoBarra\" like '" + barcode + "'";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("PrecoVenda");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public double getPrecoCompra(String Codigo) {
        sql = "SELECT \"PrecoCompra\" as PrecoCompra FROM produtos WHERE \"Descricao\"='" + Codigo + "'";
        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("PrecoCompra");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return 0;
    }

    public ArrayList<entidades.Produtos> listar() {
        ArrayList<entidades.Produtos> list = new ArrayList<>();
        sql = "SELECT * FROM produtos ";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setCodigo(rs.getInt("Codigo"));
                produtos.setDescricao(rs.getString("Descricao"));
                produtos.setLogotipo(rs.getString("logotipo"));
                list.add(produtos);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<String> getDescricaoProduto(String nome) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT \"CodigoBarra\" as CodigoBarra ,\"Descricao\" as Descricao ,\"PrecoVenda\" as PrecoVenda FROM produtos WHERE \"Descricao\" ='" + nome + "' AND \"CodigoStatus\"=1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("CodigoBarra"));
                list.add(rs.getString("Descricao"));
                list.add(rs.getString("PrecoVenda"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<String> getlistarDescricao(int pontoVenda) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT p.\"Descricao\" as Descricao\n"
                + "  FROM produtos p inner join categorias c on p.\"CodigoCategoria\"=c.\"Codigo\"  inner join \"pontoVendas\" pv on pv.\"Codigo\"=c.\"codigo_pontoVenda\"\n"
                + "  WHERE c.\"codigo_pontoVenda\"=" + pontoVenda;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<String> getlistarDescricaoLike(String designacao) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT * FROM produtos where \"Descricao\" LIKE '" + designacao + "%' ";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<String> getlistarDescricaoEstocavel() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT * FROM produtos WHERE \"Stocavel\"='SIM' order by 2";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<String> getlistarDescricao() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT * FROM produtos order by 2 ";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<entidades.Produtos> listarPorCategoria(int Codigo) {
        ArrayList<entidades.Produtos> list = new ArrayList<>();
        sql = "SELECT * FROM produtos p WHERE p.CodigoCategoria =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setCodigo(rs.getInt("p.Codigo"));
                produtos.setDescricao(rs.getString("p.Descricao"));
                produtos.setCodigoBarra(rs.getString("p.CodigoBarra"));
                produtos.setDescricao(rs.getString("p.DataExpiracao"));
                produtos.setCodigoBarra(rs.getString("p.DataCadastro"));
                produtos.setPrecoCompra(rs.getDouble("p.PrecoCompra"));
                produtos.setVendaR(rs.getDouble("p.precoVenda"));
                produtos.setStocavel(rs.getString("p.Stocavel"));
                produtos.setLogotipo(rs.getString("p.logotipo"));
                produtos.setMargemR(rs.getDouble("p.MargemLucro"));
                produtos.setCodigoStatus(rs.getInt("p.CodigoStatus"));
                produtos.setCodigoStatus(rs.getInt("p.CodigoCategoria"));
                produtos.setCodigoUsuario(rs.getInt("p.CodigoUsuario"));
                produtos.setMargemG(rs.getDouble("margemGrosso"));
                produtos.setVendaG(rs.getDouble("vendaGrosso"));
                produtos.setDataFabrico(rs.getDate("fabrico"));
                produtos.setQtdCalculada(rs.getDouble("qtd"));
                produtos.setPercPromocao(rs.getDouble("margemPromocao"));
                list.add(produtos);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<entidades.Produtos> listarTodos(int Codigo) {
        ArrayList<entidades.Produtos> list = new ArrayList<>();
        sql = "SELECT * FROM produtos p WHERE \"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setCodigo(rs.getInt("p.Codigo"));
                produtos.setDescricao(rs.getString("p.Descricao"));
                produtos.setCodigoBarra(rs.getString("p.CodigoBarra"));
                produtos.setDescricao(rs.getString("p.DataExpiracao"));
                produtos.setCodigoBarra(rs.getString("p.DataCadastro"));
                produtos.setPrecoCompra(rs.getDouble("p.PrecoCompra"));
                produtos.setVendaR(rs.getDouble("p.precoVenda"));
                produtos.setStocavel(rs.getString("p.Stocavel"));
                produtos.setLogotipo(rs.getString("p.logotipo"));
                produtos.setMargemR(rs.getDouble("p.MargemLucro"));
                produtos.setCodigoStatus(rs.getInt("p.CodigoStatus"));
                produtos.setCodigoStatus(rs.getInt("p.CodigoCategoria"));
                produtos.setCodigoUsuario(rs.getInt("p.CodigoUsuario"));
                produtos.setVendaG(rs.getDouble("vendaGrosso"));
                produtos.setDataFabrico(rs.getDate("fabrico"));
                produtos.setQtdCalculada(rs.getDouble("qtd"));
                produtos.setPercPromocao(rs.getDouble("margemPromocao"));
                list.add(produtos);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<entidades.Produtos> listarPorCodigo(int Codigo) {
        ArrayList<entidades.Produtos> list = new ArrayList<>();
        sql = "SELECT * FROM produtos p WHERE p.Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setCodigo(rs.getInt("p.Codigo"));
                produtos.setDescricao(rs.getString("p.Descricao"));
                produtos.setCodigoBarra(rs.getString("p.CodigoBarra"));
                produtos.setVendaR(rs.getDouble("P.precoVenda"));
                list.add(produtos);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public ArrayList<entidades.Produtos> listarProdutoporCategoria(String designacao) {
        ArrayList<entidades.Produtos> list = new ArrayList<>();
        sql = "SELECT\n"
                + "     produtos.\"Descricao\" AS produtos_Descricao,\n"
                + "     produtos.\"logotipo\" AS produtos_logotipo\n"
                + "FROM\n"
                + "     \"public\".\"categorias\" categorias INNER JOIN \"public\".\"produtos\" produtos ON categorias.\"Codigo\" = produtos.\"CodigoCategoria\"\n"
                + "WHERE  categorias.\"Descricao\"='" + designacao + "'";
        System.out.println("Sql:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setDescricao(rs.getString("produtos_Descricao"));
                produtos.setLogotipo(rs.getString("produtos_logotipo"));
                list.add(produtos);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro de Pesquisa!!:" + e);
        }
        return list;
    }

    public String caminho(int Codigo) {

        sql = "SELECT * FROM produtos  WHERE \"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();

                return rs.getString("logotipo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS2,Deve Contactar com a DEVTEC:" + e);
        }

        return "";
    }

    public int getEstoque(int Codigo) {

        sql = "SELECT * FROM produtos  WHERE \"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();

                return rs.getInt("EstoqueAtual");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }

        return 0;
    }

    public String getEstocavel(int Codigo) {

        sql = "SELECT * FROM produtos  WHERE \"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();

                return rs.getString("Stocavel");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS2,Deve Contactar com a DEVTEC:" + e);
        }

        return "";
    }

    public String getEstoqueZero(int Codigo) {

        sql = "SELECT * FROM produtos  WHERE \"Codigo\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produtos produtos = new Produtos();

                return rs.getString("EstoqueZero");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS2,Deve Contactar com a DEVTEC:" + e);
        }

        return "";
    }

    public double RetornarPrecoProduto(String Nome) {
//        conexao.ConexaoBD();

        sql = "SELECT * FROM produtos WHERE \"Descricao\" ='" + Nome + "'";

        double Preco = 0;
        try {

//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Preco = rs.getInt("PrecoVenda");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }

    public int getStoqueMin(String Nome) {
        sql = "SELECT * FROM produtos WHERE \"Descricao\" ='" + Nome + "'";

        int Preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Preco = rs.getInt("EstoqueMin");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }

    public int getStoqueCrit(String Nome) {
        sql = "SELECT * FROM produtos WHERE \"Descricao\" ='" + Nome + "'";

        int Preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Preco = rs.getInt("EstoqueCrit");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }

    public int getStoqueAtual(String Nome) {

        sql = "SELECT * FROM produtos WHERE \"Descricao\" like '" + Nome + "'";

        int Preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                Preco = rs.getInt("EstoqueAtual");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }
    public int getStoqueAtualByCod(int cod) {

        sql = "SELECT * FROM produtos WHERE \"Codigo\" like '" + cod + "'";

        int Preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                Preco = rs.getInt("EstoqueAtual");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }

    public int getStoqueAtualByBarcode(String barcode) {

        sql = "SELECT * FROM produtos WHERE \"CodigoBarra\" like '" + barcode + "'";

        int preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                preco = rs.getInt("EstoqueAtual");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return preco;
    }

    public double getMargem(String Nome) {

        sql = "SELECT * FROM produtos WHERE \"Descricao\" ='" + Nome + "'";

        double Preco = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Preco = rs.getDouble("MargemLucro");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao inserir Produto!!!" + ex);
        }
        return Preco;
    }

    public List RetornarObjectoProduto(int codCategoria) {
//        conexao.ConexaoBD();
        List ObjectoCompleto = new ArrayList();
        sql = "SELECT \"Descricao\" as Descricao FROM produtos where \"CodigoCategoria\"=" + codCategoria + " and \"CodigoStatus\"=1 order by 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ObjectoCompleto.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return ObjectoCompleto;
    }

    public List RetornarObjectoPreco(String produto, int codCategoria) {
//        conexao.ConexaoBD();
        List ObjectoCompleto = new ArrayList();
        sql = "SELECT \"PrecoVenda\" as preco FROM produtos where \"Descricao\"='" + produto + "' and  \"CodigoCategoria\"=" + codCategoria + " and \"CodigoStatus\"=1 order by 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ObjectoCompleto.add(rs.getString("preco"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return ObjectoCompleto;
    }

    public ArrayList<String> RetornarImagem(String produto, int codCategoria) {
//        conexao.ConexaoBD();
        ArrayList<String> cl = new ArrayList<>();
        sql = "SELECT \"logotipo\" as imagem FROM produtos where \"Descricao\"='" + produto + "' and \"CodigoCategoria\"=" + codCategoria + " ";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("imagem"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return cl;
    }

    public String RetornarImagem(int cod) {
//        conexao.ConexaoBD();
        sql = "SELECT \"logotipo\" as imagem FROM produtos where \"Codigo\"=" + cod + "";
        try {

//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Imagem");
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return "";
    }

    public String RetornarImagemByBarcode(String cod) {
//        conexao.ConexaoBD();
        sql = "SELECT \"logotipo\" as imagem FROM produtos where \"CodigoBarra\" like '" + cod + "'";
        try {

//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Imagem");
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return "";
    }

    public String getBarCode(int cod) {
        sql = "SELECT \"CodigoBarra\"  FROM produtos where \"Codigo\"=" + cod + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return "";
    }

    public String getDescricaoAdicional(int cod) {
        sql = "SELECT \"DescricaoAdicional\"  FROM produtos where \"Codigo\"=" + cod + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return "";
    }

    public boolean produtoStocavel(int codigo) {
        sql = "SELECT \"Stocavel\"  from produtos where  \"Codigo\" = " + codigo;
        String aux;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                aux = rs.getString(1);
//                System.out.println("Stocavel " + aux);
                if (aux.equalsIgnoreCase("SIM")) {
                    return true;
                }
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int getExistenciaStock(int codigoProduto) {
        int existencia = 0;
        sql = "SELECT\n"
                + "     stockMovimento.\"QuantidadeMax\" AS stock_QuantidadeActual\n"
                + "FROM\n"
                + "     \"public\".\"produtos\" produtos INNER JOIN \"public\".\"stockMovimento\" stockMovimento ON produtos.\"Codigo\" = stockMovimento.\"CodigoProduto\"\n"
                + "WHERE produtos.\"Codigo\"=" + codigoProduto + " AND (stockMovimento.\"QuantidadeMax\">0 OR  produtos.\"Stocavel\"='NAO') ";
//        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                existencia = rs.getInt("stock_QuantidadeActual");
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return existencia;
    }

    public int verificarProduto(String descriccao) {
        sql = "SELECT\n"
                + "     categorias.\"Codigo\" AS categorias_Codigo\n"
                + "     FROM\n"
                + "     \"public\".\"categorias\" categorias INNER JOIN \"public\".\"produtos\" produtos ON categorias.\"Codigo\" = produtos.\"CodigoCategoria\"\n"
                + "WHERE  produtos.\"Descricao\" ='" + descriccao + "' ";
//        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("categorias_Codigo");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0;
    }

    public String getCategoriaProduto(int codigo) {
        sql = "SELECT\n"
                + "     categorias.\"Descricao\" AS categorias_Descricao\n"
                + "     FROM\n"
                + "     \"public\".\"categorias\" categorias INNER JOIN \"public\".\"produtos\" produtos ON categorias.\"Codigo\" = produtos.\"CodigoCategoria\"\n"
                + "WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("categorias_Descricao");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return "";
    }

    public String getCategoriaProduto(String p) {
        sql = "SELECT\n"
                + "     categorias.\"Descricao\" AS categorias_Descricao\n"
                + "     FROM\n"
                + "     \"public\".\"categorias\" categorias INNER JOIN \"public\".\"produtos\" produtos ON categorias.\"Codigo\" = produtos.\"CodigoCategoria\"\n"
                + "WHERE  produtos.\"Descricao\" like '" + p + "' ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("categorias_Descricao");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return "";
    }

    public Date getDataExpiracao(int codigo) {
        sql = "SELECT  \"DataExpiracao\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return null;
    }

    public Date getDataFabrico(int codigo) {
        sql = "SELECT  \"fabrico\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return null;
    }
    public Double getMargemGrosso(int codigo) {
        sql = "SELECT  \"margemGrosso\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0.0;
    }
    public Double getVendaGrossoByBarcode(int codigo) {
        sql = "SELECT  \"vendaGrosso\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"CodigoBarra\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0.0;
    }
    public Double getPromocao(int codigo) {
        sql = "SELECT  \"margemPromocao\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0.0;
    }
    public Double getPromocaoByBarcode(int codigo) {
        sql = "SELECT  \"margemPromocao\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"CodigoBarra\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0.0;
    }
    public Double getQtd(int codigo) {
        sql = "SELECT  \"qtd\" AS Data\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Data");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0.0;
    }
    public int getDias(int codigo) {
        sql = "SELECT  \"diasAlerta\" AS dias\n"
                + "  FROM produtos WHERE  produtos.\"Codigo\" =" + codigo + " ";
        //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("dias");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Erro" + ex);
        }
        return 0;
    }

}
