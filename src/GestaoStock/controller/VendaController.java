/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.ItemVenda;
import GestaoStock.modelo.Venda;
import json_xml_iva.CustomerIten;
import json_xml_iva.ProdutItem;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class VendaController {

    ConexaoBancos conexao = new ConexaoBancos();
    ResultSet rs;
    String sql;

    public boolean salvarVenda(Venda modelo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO venda(dataVenda,desconto,valorTotal,formaPagamento,statusVenda,codBeneficiario,codUtilizador,valorEntregue,troco,hora,valorTPA) VALUES(now(),?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Venda:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setDouble(1, modelo.getDesconto());
            prepara.setDouble(2, modelo.getValorTotal());
            prepara.setInt(3, modelo.getFormaPagamento());
            prepara.setInt(4, 1);
            prepara.setInt(5, modelo.getCodBeneficiario());
            prepara.setInt(6, modelo.getCodUtilizador());
            prepara.setDouble(7, modelo.getValorEntregue());
            prepara.setDouble(8, modelo.getTroco());
            prepara.setString(9, modelo.getHora());
            prepara.setDouble(10, modelo.getMulticaixa());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERROsss:" + ex);
        }

        return true;
    }

    public boolean converterFatura(Venda modelo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO venda(dataVenda,desconto,valorTotal,formaPagamento,statusVenda,codBeneficiario,codUtilizador,valorEntregue,tipoVenda,troco) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, modelo.getDataVenda());
            prepara.setDouble(2, modelo.getValorVenda());
            prepara.setDouble(3, modelo.getDesconto());
            prepara.setDouble(4, modelo.getValorTotal());
            prepara.setInt(5, modelo.getFormaPagamento());
            prepara.setInt(6, 1);
            prepara.setInt(7, modelo.getCodBeneficiario());
            prepara.setInt(8, modelo.getCodUtilizador());
            prepara.setInt(9, modelo.getTipoVenda());
            prepara.setDouble(10, modelo.getTroco());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean salvarItemVenda(ItemVenda itemVenda) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO item_venda(codVenda,codProduto,qtdItem,valorUnitario,valorTPA,desconto) VALUES(?,?,?,?,?,?)";
        System.out.println("SQL:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setLong(1, itemVenda.getCodVenda());
            prepara.setInt(2, itemVenda.getCodProduto());
            prepara.setDouble(3, itemVenda.getQtdItem());
            prepara.setDouble(4, itemVenda.getValorUnitario());
            prepara.setDouble(5, itemVenda.getTotalTPA());
            prepara.setDouble(6, itemVenda.getDescontoIVa());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public int getAllVendaByBeneficiario(int beneficiario) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM venda WHERE(codBeneficiario = " + beneficiario + " and statusVenda=1) ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("codVenda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public String getBeneficiarioByVenda(int venda) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM venda v inner join pacientes p on p.idPaciente=v.codBeneficiario WHERE(codVenda = " + venda + " and statusVenda=1) ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("p.nomeCompleto");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public int getAllVendas(String data1, String data2) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM venda WHERE dataVenda between '" + data1 + "' and '" + data2 + "') and statusVenda=1";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("codVenda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getAllItem(int cod) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT sum(iv.qtdItem) as item FROM venda v inner join item_venda iv on iv.codVenda=v.codVenda where v.codvenda=" + cod;

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("item");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getAllValor(int cod) throws SQLException {
        conexao.Connectando();
        String sql = "SELECT valorvenda FROM venda v inner join item_venda iv on iv.codVenda=v.codVenda where v.codvenda=" + cod;

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("valorvenda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getAllVendaByBeneficiarioAnData(int beneficiario, String data1, String data2) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT * FROM venda WHERE(codBeneficiario = " + beneficiario + " and (dataVenda between '" + data1 + "' and '" + data2 + "') and statusVenda=1) ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("codVenda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int getLastVendaByBeneficiario(int beneficiario) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT Max(codVenda) as fatura FROM venda WHERE(codBeneficiario = " + beneficiario + " and statusVenda=1) ";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("fatura");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getLastVenda() {
        try {
            conexao.Connectando();
            sql = "select max(codVenda) as max from venda";
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
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

     public List<CustomerIten> listaClientes(String data1, String data2) {
        sql = "select DISTINCT c.idPaciente, c.nif, c.nomeCompleto, c.morada from venda f join pacientes c  on c.idPaciente = f.codBeneficiario where date(dataFactura) between '" + data1 + "' and '" + data2 + "'";
        System.out.println("status ---> " + sql);
        rs = conexao.executeQuery(sql);

        List<CustomerIten> item = new ArrayList<>();

        try {
            while (rs.next()) {
                CustomerIten modelo = new CustomerIten();
                CustomerIten.Customer ci = new CustomerIten().getCustomer();
                ci.setCustomerID(String.valueOf(rs.getInt("c.idPaciente")));
                String custormerTaxID = rs.getString("c.nif");

                if (custormerTaxID != null) {
                    custormerTaxID.trim();
                    if (custormerTaxID.equals("")) {
                        ci.setCustomerTaxID(custormerTaxID);
                    } else {
                        ci.setCustomerTaxID("Consumidor final");
                    }
                } else {
                    ci.setCustomerTaxID("Consumidor final");
                }

                ci.setCompanyName(rs.getString("c.nomeCompleto"));
//                ci.getBillingAddress().setAddressDetail(rs.getString("c.morada"));
                modelo.setCustomer(ci);
//                ci.getBillingAddress()
                item.add(modelo);
            }
            return item;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }
    public List<ProdutItem> listaProdutosVendidos(String data1, String data2) {
        sql = "select DISTINCT  p.codProduto, p.descricao from item_venda f join produto p on p.codProduto = f.codProduto  join venda fa on fa.codVenda = f.codVenda where date(fa.dataFactura) between '" + data1 + "' and '" + data2 + "'";
        rs = conexao.executeQuery(sql);
        List<ProdutItem> pitem = new ArrayList<>();
        try {
            while (rs.next()) {
                System.out.println("Entrou");
                ProdutItem.Product produto = new ProdutItem().getProduct();
                produto.setProductCode(String.valueOf(rs.getInt("p.codProduto")));
                produto.setProductDescription(rs.getString("p.descricao"));
                ProdutItem modelo = new ProdutItem();
                modelo.setProduct(produto);
                pitem.add(modelo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pitem;
    }
}
