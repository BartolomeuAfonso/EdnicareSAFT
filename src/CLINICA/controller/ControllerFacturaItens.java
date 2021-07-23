/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.FacturaItens;
import CLINICA.modelo.GuiaItens;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerFacturaItens {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerFacturaItens(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarItens(FacturaItens facturaItens) {
        sql = "INSERT INTO factura_itens(codigoProduto,codigoFactura,quantidade,totalGeral,codigoCategoria,totalTPA,descontoIVA,descontoProduto,preco)values(?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, facturaItens.getCodigoServico());
            ps.setInt(2, facturaItens.getCodigoFactura());
            ps.setInt(3, facturaItens.getQuantidade());
            ps.setDouble(4, facturaItens.getTotalGeral());
            ps.setInt(5, facturaItens.getCodigoCategoriaServico());
            ps.setDouble(6, facturaItens.getTotalTPA());
            ps.setDouble(7, facturaItens.getDescontoIVA());
            ps.setDouble(8, facturaItens.getDescontoProduto());
            ps.setDouble(9, facturaItens.getPreco());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public int getValorTotalNumerario(String data1, String data2) {
        int cont = 0;
        try {
            // conexao.Connectando();
            sql = "SELECT f.`dataFactura` AS factura_dataFactura,\n"
                    + "     sum(f.`valorNumerario`-(f.`desconto`+f.`descontoIVA`)) AS totalGeral from factura f\n"
                    + "     INNER JOIN `formapagamento` formapagamento ON f.`codigoFormaPagamento` = formapagamento.`codForma`\n"
                    + "WHERE\n"
                    + "      date(f.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND formapagamento.`designacao`='NUMERARIO' GROUP BY 1";

            System.out.println("Consulta Numerario:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cont = cont + rs.getInt("totalGeral");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return cont;
    }

    public int getValorTotalMulticaixa(String data1, String data2) {
        int cont1 = 0;
        try {
            //  conexao.Connectando();
            sql = "SELECT f.`dataFactura` AS factura_dataFactura,\n"
                    + " sum(f.`valorMulticaixa`-(f.`desconto`+f.`descontoIVA`)) AS totalGeral from factura f\n"
                    + "     INNER JOIN `formapagamento` formapagamento ON f.`codigoFormaPagamento` = formapagamento.`codForma`\n"
                    + "WHERE\n"
                    + "      date(f.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND formapagamento.`designacao`='MULTICAIXA' GROUP BY 1";

            System.out.println("Total Multicaixa:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cont1 = cont1 + rs.getInt("totalGeral");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return cont1;
    }

    public int getValorTotalTransferencia(String data1, String data2) {
        int cont2 = 0;
        try {
            //  conexao.Connectando();
            sql = "SELECT f.`dataFactura` AS factura_dataFactura,\n"
                    + "    sum(f.`valorMulticaixa`-(f.`desconto`+f.`descontoIVA`)) AS totalGeral from factura f\n"
                    + "     INNER JOIN `formapagamento` formapagamento ON f.`codigoFormaPagamento` = formapagamento.`codForma`\n"
                    + "WHERE\n"
                    + "      date(f.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND formapagamento.`designacao`='TRANSFERENCIA BANCARIA' GROUP BY 1";

            System.out.println("Transferência:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cont2 = cont2 + rs.getInt("totalGeral");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return cont2;
    }

    public int getValorSeguradora(String data1, String data2) {
        int cont3 = 0;
        try {
            //  conexao.Connectando();
            sql = "SELECT f.`dataFactura` AS factura_dataFactura,sum(f.`valorApagar`) - sum(f.`desconto`) AS totalGeral from factura f\n"
                    + "INNER JOIN `formapagamento` formapagamento ON f.`codigoFormaPagamento` = formapagamento.`codForma`\n"
                    + "WHERE\n"
                    + "date(f.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND\n"
                    + "f.`codigoSeguro`<>8 group by 1";

            System.out.println("Total Seguradora:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cont3 = cont3 + rs.getInt("totalGeral");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return cont3;
    }

    // SEGURADORA
    public void salvarItensGuia(GuiaItens guiaItens) {
        // conexao.Connectando();
        sql = "INSERT INTO factura_itens(codigoProduto,codigoFactura,quantidade,totalGeral,elegibilidade,descontoProduto,codigoCategoria,descontoIVA,preco)values(?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, guiaItens.getCodigoServico());
            ps.setInt(2, guiaItens.getCodigoGuia());
            ps.setInt(3, guiaItens.getQuantidade());
            ps.setDouble(4, guiaItens.getTotalgeral());
            ps.setString(5, guiaItens.getElegilibidade());
            ps.setDouble(6, guiaItens.getDesconto());
            ps.setInt(7, guiaItens.getCodigoCategoriaServico());
            ps.setDouble(8, guiaItens.getDescontoIVA());
            ps.setDouble(9, guiaItens.getPreco());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarItensGuia1(GuiaItens guiaItens, int codigoGuia) {
        // conexao.Connectando();
        sql = "INSERT INTO factura_itens(codigoProduto,codigoFactura,quantidade,totalGeral,elegibilidade,descontoProduto)values(?,'" + codigoGuia + "',?,?,?,?)";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, guiaItens.getCodigoServico());
            //  ps.setInt(2, guiaItens.getCodigoGuia());
            ps.setInt(2, guiaItens.getQuantidade());
            ps.setDouble(3, guiaItens.getTotalgeral());
            ps.setString(4, guiaItens.getElegilibidade());
            ps.setDouble(5, guiaItens.getDesconto());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void updateGuiaItens(GuiaItens guiaItens, int codigoGuia, int codigoProduto) {
        //conexao.Connectando();
        sql = "UPDATE factura_itens set quantidade=?,totalGeral=? WHERE codigoFactura=" + codigoGuia + " AND codigoProduto=" + codigoProduto;
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
        sql = "DELETE FROM factura_itens WHERE codigoFactura=" + codigoGuia;
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
        sql = "DELETE FROM factura_itens WHERE codigoFactura =" + codigoGuia + " AND  codigoProduto=" + codigoServico;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            //        JOptionPane.showMessageDialog(null, "Item eliminado com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public FacturaItens findVendaItemByFactura(int codigo) {
        sql = "SELECT * FROM factura_itens where codigoFactura = " + codigo + "";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                FacturaItens vendasItem = new FacturaItens();
                return vendasItem;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void desativarProduto(String codigoGuia, int codigoProduto) {
        //conexao.Connectando();
        sql = "UPDATE factura_itens set flag=0 WHERE codigoFactura=" + codigoGuia + " AND codigoProduto=" + codigoProduto;
        System.out.println("Update:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void desativarFactura(String codigoGuia) {
        //conexao.Connectando();
        sql = "UPDATE factura set estado='FACTURA ANULADA' WHERE idFactura=" + codigoGuia;
        System.out.println("Update:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }
}
