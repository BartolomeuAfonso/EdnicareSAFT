/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.TipoTaxaModelo;
import CLINICA.modelo.NotaCreditoItens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import json_xml_iva.InvoicesNota;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import json_xml_iva.LineItens;
import json_xml_iva.LineItens.LineItem;

/**
 *
 * @author Ba
 */
public class ControllerNotasItens {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    DecimalFormat df = new DecimalFormat("#,###.00");
    TipoTaxaModelo modelo1;
    ControllerUsuario controllerUsuario = new ControllerUsuario(con);
    Data d = new Data();
    LineItem item;

    public ControllerNotasItens(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(NotaCreditoItens notas) {
        sql = "INSERT INTO notacredito_itens(codigoProduto,totalGeral,quantidade,codigoNota,descontoIVA,subTotal)values(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, notas.getCodigoServico());
            ps.setDouble(2, notas.getTotalGeral());
            ps.setInt(3, notas.getQuantidade());
            ps.setDouble(4, notas.getCodigoFactura());
            ps.setDouble(5, notas.getDescontoIVA());
            ps.setDouble(6, notas.getTotalGeral() - notas.getDescontoIVA());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public int getLastNota() {
        try {
            sql = "select max(idNota) as max from notaCredito";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
            ex.printStackTrace();
        }
        return 0;
    }

    public String geTotalNota(String data1, String data2) {
        sql = "select count(idNota) as total from notaCredito n where date(dataFactura) between '" + data1 + "' and '" + data2 + "'";
        try {
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getString("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<LineItens> getItemNotas(int codigo) {
        String sql = "SELECT n.idNota as id_venda,n.dataFactura,n.refFactura,n.motivo,p.idServico as id_produto,p.designacao as descricao,ni.quantidade as quantidade, n.valorApagar as valor_pagar,\n"
                + "p.codigoTipoTaxa as codigoTipoTaxa, t.taxa as taxa,m.codigo as codigo,m.descricacao as motivoIsencao,m.codigoMotivo as codigoMotivo , ni.totalGeral as preco, ni.subtotal as subtotal, ni.descontoProduto as desconto\n"
                + "FROM notaCredito n inner join notaCredito_itens ni on n.idNota = ni.codigoNota inner join servicos p on p.idServico = ni.codigoProduto\n"
                + "inner join tipotaxa t on t.codigo = p.codigoTipoTaxa\n"
                + "inner join motivo m on t.codigoMotivo= m.codigo where idNota=" + codigo;

        System.out.println("Nota de Créditos Itens:" + sql);
        ArrayList<LineItens> lista = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            while (rs.next()) {
                cont++;
                LineItens.LineItem item = new LineItens().getLine();
                item.setLineNumber(String.valueOf(cont));
                String dataAux = rs.getString("n.dataFactura");
                String dataFactura = dataAux.substring(0, 10);
                item.getOrderReferences().setOrderDate(dataFactura);
                item.getOrderReferences().setOriginatingON(rs.getString("n.refFactura"));
                item.setProductCode(rs.getString("id_produto"));
                item.setProductDescription(rs.getString("descricao"));
                item.setQuantity(rs.getString("quantidade"));
                item.setUnitOfMeasure("*");
                item.setUnitPrice(rs.getString("preco"));
                item.getReferences().setReference(rs.getString("n.refFactura"));
                item.getReferences().setReason(rs.getString("n.motivo"));
                item.setDescription(rs.getString("motivoIsencao"));
                String descricao = rs.getString("descricao");
                if (!Objects.equals(descricao, null) && !Objects.equals(descricao, "")) {
                    item.setDescription(descricao);
                }
                item.setDebitAmount(rs.getString("preco"));
                //item.setDebitAmount(getValorCasaDecimal(df.format(rs.getDouble("preco")), ".").replace(',', '.'));
                System.out.println("Subtotal:" + rs.getDouble("subtotal"));
                int codigoTaxa = rs.getInt("codigoTipoTaxa");
                int taxa = rs.getInt("taxa");
                String motivo = rs.getString("motivoIsencao");
                String codigoMotivo = rs.getString("codigoMotivo");
                if (codigoTaxa != 0) {
                    if (taxa == 14) {
                        // if (modelo1.getTaxa() == 14) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("NOR");
                        item.getTax().setTaxPercentage("" + 14);
                    } else if (taxa == 5) {
                        // if (modelo1.getTaxa() == 14) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("RED");
                        item.getTax().setTaxPercentage("" + 5);

                    } else {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxType("NS");
                        item.getTax().setTaxCode("NS");
                        item.setTaxExemptionReason("" + motivo);
                        item.setTaxExemptionCode("" + codigoMotivo);
                        item.getTax().setTaxPercentage("0");
                    }

                }
                //item.setCreditAmount("0.00");
                LineItens linha = new LineItens();
                item.setSettlementAmount("0");
                linha.setLine(item);
                lista.add(linha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public double getTotalCreditsemIva(int codigoFactura) {
        String sql = "select sum(subtotal-desconto) as total from notaCredito where idNota=" + codigoFactura;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getTotalCreditoIva(int codigoFactura) {
        String sql = "select sum(descontoIVa) as total from notaCredito where idNota=" + codigoFactura;
        try {
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getTotalCredit(int codigo) {
        sql = "select sum(ValorApagar) as total from notaCredito where idNota=" + codigo;
//        sql = "select sum(ValorApagar) as total from factura where date(dataFactura) between '" + data + "' and '" + data1 + "' AND idFactura=" + codigo;
        try {
            System.out.println("Consulta:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getSubTotalDescontoMovimento(int codigo) {
        String sql = "select sum(descontoFactura+descontoIVA) as total from notaCredito where idNota=" + codigo;
//        sql = "select sum(ValorApagar) as total from factura where date(dataFactura) between '" + data + "' and '" + data1 + "' AND idFactura=" + codigo;
        try {
            System.out.println("Consulta:" + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getValorCasaDecimal(String str, String expressao) {
        String output = "";
        if (str.charAt(0) == ',') {
            output += "0";
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                output += str.charAt(i);
            }
        }
        return output;
    }

    public double totalApagarMovimento(int id) {
        String sql = "SELECT sum(totalGeral) as total FROM notaCreditoItens where codigoNota =" + id;
        try {
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double totalIvaMovimento(int id) {
        String sql = "SELECT sum(descontoIVA) iva as  FROM notaCreditoItens where codigoNota =" + id;
        try {
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getDouble("total");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
