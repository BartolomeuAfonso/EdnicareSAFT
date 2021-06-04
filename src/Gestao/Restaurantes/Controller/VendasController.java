/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class VendasController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public VendasController(Connection con) {
        this.con = con;
    }

    public double valorIva(double valorVenda, double percentagemIva) {
        double resultado = 0;

        resultado = (percentagemIva * valorVenda) / 100;

        return resultado;
    }

    public double totalIva(double valorVenda, double percentagemIva) {
        double resultado = 0;

        resultado = (percentagemIva * valorVenda) / 100 + valorIva(valorVenda, percentagemIva);

        return resultado;
    }

    public boolean Salvar(entidades.Vendas vendas) {
        sql = "INSERT INTO vendas(\"NomeCliente\",\"ValorPagar\",\"ValorEntregue\",\"Desconto\",\"Troco\",\"Ipc\",\"CodigoPontoVenda\",\"CodigoCliente\",\"CodigoStatusVenda\",\"CodigoMesa\",  \"CodigoFormaPagamento\", \"CodigoUtilizador\",\"DataFactura\",\"ValorEntregueMulticaixa\",\"TotalIva\")VALUES (?,?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?,?)";
//        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, vendas.getNomeCliente());
            ps.setDouble(2, vendas.getValorPagar());
            ps.setDouble(3, vendas.getValorEntregue());
            ps.setDouble(4, vendas.getDesconto());
            ps.setDouble(5, vendas.getTroco());
            ps.setInt(6, vendas.getIpc());
            ps.setInt(7, vendas.getCodigoPontoVenda());
            ps.setInt(8, vendas.getCodigoCliente());
            //ps.setInt(10, vendas.getCodigoStatusVenda());
            ps.setInt(9, 1);
            //  ps.setInt(10, vendas.getCodigoFuncionario());
            ps.setInt(10, vendas.getCodigoMesa());
            ps.setInt(11, vendas.getCodigoFormaPagamento());
            ps.setInt(12, vendas.getCodigoUtilizador());
            ps.setDate(13, (Date) vendas.getDataFactura());
            ps.setDouble(14, vendas.getValorEntregueMulticaixa());
            ps.setDouble(15, vendas.getTotalIva());
            ps.execute();
            ps.close();
            return true;
            //       JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
//            System.out.println("aqui,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public void Salvar(entidades.Vendas vendas, int Codigo) {
        sql = "UPDATE vendas SET \"NomeCliente\"=?,\"DataFactura\"=?,\"ValorPagar\"=?,\"ValorEntregue\"=?,\"Desconto\"=?,\"Troco\"=?,\"Ipc\"=?,\"CodigoPontoVenda\"=?,\"CodigoCliente\"=?,\"CodigoStatusVenda\"=?,\"CodigoFuncionario\"=? WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, vendas.getNomeCliente());
            ps.setDate(2, (Date) vendas.getDataFactura());
            ps.setDouble(3, vendas.getValorPagar());
            ps.setDouble(4, vendas.getValorEntregue());
            ps.setDouble(5, vendas.getDesconto());
            ps.setDouble(6, vendas.getTroco());
            ps.setInt(7, vendas.getIpc());
            ps.setInt(8, vendas.getCodigoPontoVenda());
            ps.setInt(9, vendas.getCodigoCliente());
            ps.setInt(10, vendas.getCodigoStatusVenda());
            ps.setString(11, vendas.getGarcon());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM vendas Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
        } catch (SQLException e) {
            //        System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public int getLastInsert() {
        String sql = "SELECT MAX(\"Codigo\") as UltimoCodigo FROM vendas";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UltimoCodigo");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getForma(int cod) {
        String sql = "SELECT p.\"Descricao\" as f "
                + "FROM public.vendas v inner join public.\"formaPagamento\" p on v.\"CodigoFormaPagamento\"=p.\"Codigo\" "
                + "where v.\"Codigo\"=" + cod + "";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("f");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public double getSubTotal(int cod) {
        String sql = "SELECT \"ValorPagar\" as ValorPagar FROM public.vendas where \"Codigo\"=" + cod + "";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorPagar");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getValorTotalporUtilizador(int user, Date data) {
        String sql = "SELECT sum(\"ValorPagar\") as ValorPagar FROM public.vendas where \"CodigoUtilizador\"=" + user + " and \"DataFactura\"='" + data + "' ";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorPagar");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getValorTotaNumerario(int user, Date data) {
        String sql = "SELECT sum(\"ValorPagar\") as ValorPagar FROM public.vendas where \"CodigoUtilizador\"=" + user + " and \"DataFactura\"='" + data + "' AND \"CodigoFormaPagamento\" =1";
        //  System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorPagar");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getValorTotaNumerarioPagamentoDuplo(int user, Date data) {
        String sql = "SELECT sum(\"ValorEntregue\") as ValorEntregue FROM public.vendas where \"CodigoUtilizador\"=" + user + " and \"DataFactura\"='" + data + "' AND \"CodigoFormaPagamento\" =4";
        //    System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorEntregue");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getValorTotalMulticaixa(int user, Date data) {
        String sql = "SELECT sum(\"ValorEntregueMulticaixa\") as ValorEntregueMulticaixa FROM public.vendas where \"CodigoUtilizador\"=" + user + " and \"DataFactura\"='" + data + "' AND \"CodigoFormaPagamento\" =2";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorEntregueMulticaixa");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getValorTotalMulticaixaPagamentoDuplo(int user, Date data) {
        String sql = "SELECT sum(\"ValorEntregueMulticaixa\") as ValorEntregueMulticaixa FROM public.vendas where \"CodigoUtilizador\"=" + user + " and \"DataFactura\"='" + data + "' AND \"CodigoFormaPagamento\" =4";
        // System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("ValorEntregueMulticaixa");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void eliminar(int Codigo) {
        sql = "UPDATE vendas SET \"CodigoStatusVenda\"= ? WHERE \"Codigo\"=" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Factura Eliminada com Sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public ArrayList<String> getlistaCodigosByData(Date d1, Date d2) {
        sql = " SELECT \"Codigo\"\n"
                + "  FROM vendas\n"
                + "  WHERE \"CodigoStatusVenda\" = 1 AND  \"DataFactura\" BETWEEN '" + d1 + "' and '" + d2 + "' ORDER BY 1 DESC ";
//        System.out.println(sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Codigo"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<String> getlistaCodigos() {
        sql = " SELECT \"Codigo\"\n"
                + "  FROM vendas\n"
                + "  WHERE \"CodigoStatusVenda\" = 1; ";
        System.out.println(sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Codigo"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<String> getCodigoFactura(int codigo) {
        sql = " SELECT \"Codigo\"\n"
                + "  FROM vendas\n"
                + "  WHERE \"Codigo\" = " + codigo + " and \"CodigoStatusVenda\" = 1 ";
        System.out.println(sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Codigo"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

}
