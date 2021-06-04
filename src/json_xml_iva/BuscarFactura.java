/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

import CLINICA.controller.ControllerNotas;
import CLINICA.controller.ControllerNotasItens;
import CLINICA.modelo.Factura;
import CLINICA.modelo.Taxa;
import CLINICA.controller.MotivoController;
import CLINICA.modelo.MotivoModelo;
import CLINICA.modelo.Notacredito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import sf.ce.conexao.ConexaoBancos;

public class BuscarFactura {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    private PreparedStatement command;
    private ResultSet query;
    ControllerNotasItens controllerNotasItens = new ControllerNotasItens(con);

    public BuscarFactura() {

        this.con = conexao.ConexaoBD();
    }

    public List<TaxTableItem> listaIsencao(String data1, String data2) {

        List<TaxTableItem> pitem = new ArrayList<>();
        System.out.println("data ::: " + data1 + " :::: " + data2);
        List<ProdutItem> todosProdutosVendidos = getTodosProdutosVendidos(data1, data2);

        for (ProdutItem produtItem : todosProdutosVendidos) {

            int productTaxa = getTaxaProdutoByCodigo(Integer.parseInt(produtItem.getProduct().getProductCode()));

            TaxTableItem.TaxTableEntry tax = new TaxTableItem().getTaxTableEntry();
            tax.setTaxPercentage(String.valueOf(productTaxa));

            if (Integer.parseInt(tax.getTaxPercentage()) == 14) {

                if (!verificarTaxTableEntryExiste(pitem, "IVA")) {

                    tax.setTaxType("IVA");
                    tax.setTaxCode("NOR");
                    tax.setDescription("Taxa Normal");
                    TaxTableItem titem = new TaxTableItem();
                    titem.setTaxTableEntry(tax);
                    pitem.add(titem);
                }

            } else if (Integer.parseInt(tax.getTaxPercentage()) == 5) {

                if (!verificarTaxTableEntryExiste(pitem, "IVA")) {

                    tax.setTaxType("IVA");
                    tax.setTaxCode("RED");
                    tax.setDescription("Taxa Reduzida");
                    TaxTableItem titem = new TaxTableItem();
                    titem.setTaxTableEntry(tax);
                    pitem.add(titem);
                }

            } else {

                if (!verificarTaxTableEntryExiste(pitem, "NS")) {

                    tax.setTaxType("NS");
                    tax.setTaxCode("NS");
                    tax.setDescription("Isenta");
                    //tax.setTaxAmount("0.00");
                    tax.setTaxPercentage("0.00");
                    TaxTableItem titem = new TaxTableItem();
                    titem.setTaxTableEntry(tax);

                    pitem.add(titem);
                }
            }

        }

        return pitem;
    }

    public int getTaxaProdutoByCodigo(int codigo) {
        String sql = "select t.Taxa from servicos p join tipotaxa t on t.codigo = p.codigoTipoTaxa  where p.idServico=" + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return 0;
    }

    public String totalFactura(String data, String data1) {

        String sql = "select count(idfactura) as total from factura where (estado ='FACTURA PRONTO' or estado='FACTURA CRÉDITO' or estado='FACTURA ANULADA') and date(dataFactura) between '" + data + "' and '" + data1 + "'";
        System.out.println("Contacto de Factura:" + sql);
        try {
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

    public String getAnoemCurso() {
        sql = " Select year(now()) as ano";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ano");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return "0";
    }

    public String totalFacturaProforma(String data, String data1) {
        String sql = "select count(idfactura) as total from factura where (estado='FACTURA PROFORMA') and date(dataFactura) between '" + data + "' and '" + data1 + "'";
        String valor = "0";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                valor = String.valueOf(rs.getInt("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public String totalFacturaRecibo(String data, String data1) {
        String sql = "select count(idfactura) as total from factura where (estado='FACTURA CRÉDITO') and date(dataFactura) between '" + data + "' and '" + data1 + "' AND Guia=1";
        String valor = "0";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                valor = String.valueOf(rs.getInt("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public Double totalCreditoProforma(String data, String data1) {
        String sql = "SELECT sum(valorApagar) as total from factura f.estado='FACTURA PROFORMA' AND date(f.dataFactura) between '" + data + "' and '" + data1 + "'";
        String valor = "0";
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                valor = String.valueOf(rs.getDouble("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            conFactory.close(con, command, query);
//        }
        return Double.parseDouble(valor);
    }

    public List<ProdutItem> listaProdutosVendidos(String data1, String data2) {
        String sql = "select distinct c.idcategoriaServico, c.designacao, p.idServico, p.designacao from factura_itens f join servicos p on p.idServico = f.CodigoProduto\n"
                + "inner join categoriaservico c on p.codigoCategoria =c.idcategoriaServico inner join factura fa on fa.idfactura = f.CodigoFactura\n"
                + "where date(fa.dataFactura) between '" + data1 + "' and '" + data2 + "'";
        List<ProdutItem> pitem = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                ProdutItem.Product produto = new ProdutItem().getProduct();
                produto.setProductCode(String.valueOf(rs.getInt("p.idServico")));
                produto.setProductDescription(rs.getString("p.designacao"));
                ProdutItem modelo = new ProdutItem();
                modelo.setProduct(produto);
                pitem.add(modelo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pitem;
    }

    public List<CustomerIten> listaClientes(String data1, String data2) {
        String sql = "select DISTINCT c.idPaciente,c.nif, c.nomeCompleto, c.morada, c.telefone from factura f\n"
                + "inner join pacientes c on c.idPaciente = f.CodigoCliente\n"
                + "where date(f.dataFactura) between '" + data1 + "' and '" + data2 + "'";

        System.out.println("Pessoal:" + sql);
        List<CustomerIten> item = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CustomerIten modelo = new CustomerIten();
                CustomerIten.Customer ci = new CustomerIten().getCustomer();
                ci.setCustomerID(String.valueOf(rs.getInt("c.idPaciente")));
                String custormerTaxID = rs.getString("c.Nif");

                if (custormerTaxID != null) {
                    custormerTaxID = custormerTaxID.trim();
                    if (!custormerTaxID.equals("")
                            && !custormerTaxID.equals("999999999")) {
                        ci.setCustomerTaxID(custormerTaxID);
                        ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                        //  ci.setCustomerTaxID(custormerTaxID);
                    } else {
                        ci.setCustomerTaxID("999999999");
                        ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                    }
                } else {
                    ci.setCustomerTaxID("999999999");
                    ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));

                }

                ci.setCompanyName(rs.getString("c.nomeCompleto"));

                if (ci.getCompanyName().trim().equalsIgnoreCase("DIVERSOS")) {

                    ci.setCompanyName("Consumidor final");
                }

                ci.getBillingAddress().setAddressDetail(rs.getString("c.morada"));
                modelo.setCustomer(ci);
//                ci.getBillingAddress()
                item.add(modelo);
            }

            //   item.add(getClienteDIVERSO(con));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return item;
    }

    public CustomerIten getClienteDIVERSO(Connection con) {
        String sql = "select DISTINCT c.idPaciente, c.Nif, c.nomeCompleto, c.Morada from pacientes c";
        CustomerIten modelo = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                modelo = new CustomerIten();
                CustomerIten.Customer ci = new CustomerIten().getCustomer();
                ci.setCustomerID(String.valueOf(rs.getInt("c.idPaciente")));
                String custormerTaxID = rs.getString("c.Nif");

                if (custormerTaxID != null) {
                    custormerTaxID = custormerTaxID.trim();
                    if (!custormerTaxID.equals("")
                            && !custormerTaxID.equals("999999999")) {
                        ci.setCustomerTaxID(custormerTaxID);
                        ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                    } else {
                        ci.setCustomerTaxID("999999999");
                        ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                    }
                } else {
                    ci.setCustomerTaxID("999999999");
                    ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                }

                ci.setCompanyName(rs.getString("c.nomeCompleto"));

                if (ci.getCompanyName().trim().equalsIgnoreCase("DIVERSOS")) {

                    ci.setCompanyName("Consumidor final");
                }

                ci.getBillingAddress().setAddressDetail(rs.getString("c.Morada"));
                modelo.setCustomer(ci);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            conFactory.close(con, command, query);
//        }
        return modelo;
    }

    public List<ProdutItem> getTodosProdutosVendidos(String data, String data1) {

        List<ProdutItem> productItem = new ArrayList<>();
        productItem = listaProdutosVendidos(data, data1);

        return productItem;
    }

    public boolean verificarTaxTableEntryExiste(List<TaxTableItem> list, String type) {
        for (TaxTableItem value : list) {
            if (value.getTaxTableEntry().getTaxType() == type) {
                return true;
            }
        }

        return false;
    }

    /*    public List<ProdutItem> listaProdutosVendidos(String data1, String data2) {

        String sql = "select Distinct  p.Id, p.Designacao "
                + "from facturaitem fi, produto p,factura f "
                + "where (p.Id = fi.IdProduto) and (date(f.Data) between '" + data1 + "' and '" + data2 + "')";

        List<ProdutItem> pitem = new ArrayList<>();
        try {
            con = conFactory.open();
            command = con.prepareCall(sql);
            query = command.executeQuery();

            while (query.next()) {

                ProdutItem.Product produto = new ProdutItem().getProduct();
                produto.setProductCode(String.valueOf(query.getInt("p.Id")));
                produto.setProductDescription(query.getString("p.Designacao"));
                ProdutItem modelo = new ProdutItem();
                modelo.setProduct(produto);
                pitem.add(modelo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pitem;
    }*/
    public double getTotalCredit(String data, String data1) {

        String sql = "select sum(subTotal) as total from factura where (estado<>'FACTURA PROFORMA' AND estado<>'FACTURA ANULADA')\n"
                + "AND date(dataFactura) between '" + data + "' and '" + data1 + "'";
        System.out.println("Total Crédito:" + sql);
        double valor = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = (rs.getDouble("total"));
                //   valor = (rs.getDouble("total") - rs.getDouble("imposto"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            conFactory.close(con, command, query);
//        }
        return valor;
    }

    public double getTotalCreditPerfoma(String data, String data1) {

        String sql = "select sum(ValorApagar) as total, SUM(descontoIva) as imposto from factura where  (estado='FACTURA PROFORMA')\n"
                + "AND date(dataFactura) between '" + data + "' and '" + data1 + "'";
        double valor = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = rs.getDouble("total") - rs.getDouble("imposto");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return valor;
    }

    public double getTotalCreditRecibo(String data, String data1) {

        String sql = "select sum(subTotal) as total from factura where  (estado='FACTURA CRÉDITO')\n"
                + "AND date(dataFactura) between '" + data + "' and '" + data1 + "' AND Guia=1";
        double valor = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = rs.getDouble("total");
                //- rs.getDouble("imposto");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return valor;
    }

    public double getTotalDebito(String data, String data1) {

        String sql = "select sum(ValorApagar) as total, SUM(descontoIva) as imposto from notacredito where date(dataFactura) between '" + data + "' and '" + data1 + "'";
        System.out.println("Total de Débito:" + sql);
        double total = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total") - rs.getDouble("imposto");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            conFactory.close(con, command, query);
//        }
        return total;
    }

    public double getTotalCreditoND(String data, String data1) {
        sql = "SELECT sum(mi.Total) total,sum(mi.Iva) imposto FROM `movimento` m \n"
                + "inner join factura f  on f.Id = m.IdFactura\n"
                + "inner join movimentoitem mi on mi.IdMovimento = m.Id where TipoMovimento = 'D' AND Date(m.Data) Between '" + data + "' and  '" + data1 + "'";

        double total = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total") - rs.getDouble("imposto");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public List<Invoices> getDocumentosCormercias(String data, String data1) {
        List<Invoices> invoiceses = new ArrayList<>();
        invoiceses = getFacturaGerais(data, data1);
        System.out.println("invoiceses: " + invoiceses.size());
//
//        for (int i = 0; i < invoiceses.size(); i++) {
////            System.out.println("invoiceses: " + invoiceses.get(i).getInvoice().get(0).getInvoiceNo());
//
//        }
        for (Invoices invoicese : getNotasDeCreditoGerais(data, data1)) {
            invoiceses.add(invoicese);
        }
  
        return invoiceses;
    }

    public Double totalIvaMovimento(int id) throws Exception {
        String sql = "SELECT SUM(descontoIVA) from notacredito_itens f where codigoNota=" + id;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getDouble(1);
        }
        return 0.0;

    }

    public Double totalDescontoMovimento(int id, Connection con) throws Exception {

        String sql = "SELECT SUM(descontoProduto) from factura_itens f where codigoNota =" + id;
        PreparedStatement command = con.prepareStatement(sql);
        ResultSet query = command.executeQuery();

        if (query.next()) {

            return query.getDouble(1);
        }
        return 0.0;

    }

    public Double totalSubTotalMovimento(int id, Connection con) throws Exception {

        String sql = "SELECT sum(totalGeral - descontoIVA) iva FROM factura_itens where codigoNota =" + id;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getDouble(1);
        }
        return 0.0;

    }

    public Double totalApagarMovimento(int id) throws Exception {

        String sql = "SELECT sum(totalGeral) as total FROM notaCredito_itens where codigoNota =" + id;
        System.out.println("Total de Nota de Crédito:" + sql);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getDouble(1);
        }
        return 0.0;

    }

    public Double totalQtdMovimento(int id, Connection con) throws Exception {

        String sql = "SELECT sum(quantidade) as iva FROM notacredito_itens where codigoNota=" + id;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getDouble(1);
        }
        return 0.0;

    }

    public List<Invoices> getNotasDeCreditoGerais(String data, String data1) {

        String sql = "select * from notaCredito f INNER JOIN pacientes c ON c.idPaciente = f.codigoCliente\n"
                + "where date(f.dataFactura) between '" + data + "' and '" + data1 + "' ";
        System.out.println("Nota de Crédito:" + sql);
        List<Invoices> invoiceses = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String dataFactura2 = "";
            while (rs.next()) {
                Integer codigoFatura = Integer.parseInt(rs.getString("f.codigoFactura"));
                int codigoNC = rs.getInt("f.idNota");
                ControllerNotas controllerNotas = new ControllerNotas(con);
                Notacredito factura = new Notacredito();
                ControllerNotasItens miController = new ControllerNotasItens(con);
                factura.setTotalFactura(miController.getTotalCredit(codigoNC));
                factura.setDescontoIVA(miController.getTotalCreditoIva(codigoNC));
                factura.setSubtotal(miController.getTotalCredit(codigoNC) - miController.getTotalCreditoIva(codigoNC));
               factura.setDescontoFactura(miController.getSubTotalDescontoMovimento(codigoNC));
                factura.setCodigoCliente(rs.getInt("f.codigoCliente"));
                ControllerNotas mController = new ControllerNotas(con);
                Notacredito modelo = mController.getNotaByCodigo(codigoNC);
              String dataFactura = rs.getString("f.dataFactura").substring(0, 10);
                String dataNotaCredito = rs.getString("f.dataFactura").substring(0, 10);

                Invoice element = new Invoice();
                element.setInvoiceDate(dataNotaCredito);
                element.setHash("" + modelo.getHashValor());
                String datav = rs.getString("f.dataFactura").substring(0, 19);
                String[] vet = datav.split(" ");
                element.setSystemEntryDate(vet[0] + "T" + vet[1]);
                if (rs.getString("c.Nif").equals("999999999")) {

                    element.setCustomerID("" + rs.getString("f.codigoCliente"));

                } else {
                    element.setCustomerID("" + rs.getString("f.codigoCliente"));
                }

                Date hora = new Date();
                SimpleDateFormat hora_formato = new SimpleDateFormat("dd/MM/yyyy");

                String vet2[] = hora_formato.format(hora).split("/");

                int mes = Integer.parseInt(vet2[1]);
                element.setPeriod(mes < 9 ? vet2[1].substring(1) : vet2[1]);

                element.setInvoiceType("NC");
                element.setInvoiceNo(rs.getString("f.nRef"));

                DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));

                element.getDocumentStatus().setInvoiceStatusDate(vet[0] + "T" + vet[1]);
                element.getDocumentStatus().setSourceID("" + rs.getString("f.codigoUtilizador"));
                element.setSourceID("" + rs.getString("f.codigoUtilizador"));

                element.setLineItens(getItemNC(codigoFatura, dataFactura, modelo.getObs(), codigoNC));

                Double taxPayable = totalIvaMovimento(codigoNC);

                String str = getValorCasaDecimal(df.format(taxPayable), ".");
                element.getDocumentTotals().setTaxPayable(str.replace(',', '.'));

                double total = totalApagarMovimento(codigoNC);
                double imposto = taxPayable;
                double qtd = totalQtdMovimento(codigoNC, con);
                double novoPreco = 0;
                double novoSubTotal = 0;

                novoPreco = (total - imposto) / qtd;
                novoSubTotal = novoPreco * qtd;

                str = getValorCasaDecimal(df.format(rs.getDouble("f.subTotal")), ".");
                element.getDocumentTotals().setNetTotal(str.replace(',', '.'));
               
                String grossTotal = getValorCasaDecimal(df.format(rs.getDouble("f.valorApagar")), ".");
                element.getDocumentTotals().setGrossTotal(grossTotal.replace(',', '.'));
                str = getValorCasaDecimal(df.format(rs.getDouble("f.valorApagar")), ".");
                
                List<LineItensWithholding.WithholdingTax> listaWithholdingTax = new ArrayList<>();
                element.setWithholdingTax(listaWithholdingTax);

                List<Invoice> lista = new ArrayList<>();
                lista.add(element);
                invoiceses.add(new Invoices(lista));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return invoiceses;
    }

    public List<LineItens> getItemNC(int codigoFatura, String dataFactura, String motivo, int codigoMovimento) {
        ControllerNotasItens controllerNotasItens = new ControllerNotasItens(con);
        List<LineItens> itemsNC = controllerNotasItens.getItemNotas(codigoMovimento);
        return itemsNC;
    }

    public List<Invoices> getNotasDebitoGerais(String data, String data1) {

        String sql = "select * from movimento m  "
                + "inner join factura f on f.Id = m.IdFactura"
                + " inner join cliente c on c.Id = f.IdCliente"
                + " where  TipoMovimento = 'D' "
                + "and (f.NextFactura Like 'FR%' or f.NextFactura Like 'FT%') "
                + " and date(m.Data) between '" + data + "' and '" + data1 + "' ";
        // CRIAR UMA LISTA QUE ARMAZENA AS FATURAS
        List<Invoices> invoiceses = new ArrayList<>();

        try {
            PreparedStatement command = con.prepareCall(sql);
            ResultSet query = command.executeQuery();
            String dataFactura2 = "";
            while (query.next()) {
                Integer codigoFatura = Integer.parseInt(query.getString("IdFactura"));
                int codigoNC = query.getInt("m.Id");
                Factura factura = new Factura();
                //  FacturaModel valoresTotalNC = new FacturaModel();
                factura = findFacturaByFactura(codigoFatura, con);

                ControllerNotasItens miController = new ControllerNotasItens(con);

                factura.setTotalFactura(miController.getTotalCredit(codigoNC));
                factura.setDescontoIVA(miController.getTotalCreditoIva(codigoNC));
                factura.setSubTotal(miController.getTotalCredit(codigoNC) - miController.getTotalCreditoIva(codigoNC));
                factura.setDescontoFactura(miController.getSubTotalDescontoMovimento(codigoNC));
                factura.setCodigoCliente(query.getInt("n.codigoCliente"));
                ControllerNotas mController = new ControllerNotas(con);

                Notacredito modelo = mController.getNotaByCodigo(codigoNC);

                String data_factura1 = rs.getString("dataFactura").substring(0, 19);
                String dataFactura = rs.getString("dataFactura").substring(0, 10);
                String dataNotaCredito = rs.getString("dataFactura").substring(0, 10);

                Invoice element = new Invoice();
                element.setInvoiceDate(dataNotaCredito);
                element.setHash("" + modelo.getHashValor());

                String datav = rs.getString("dataFactura").substring(0, 19);
                String[] vet = datav.split(" ");
                element.setSystemEntryDate(vet[0] + "T" + vet[1]);

                if (query.getString("c.Nif").equals("999999999")) {

                    element.setCustomerID("1");

                } else {
                    element.setCustomerID(factura.getCodigoCliente() + "");
                }

                Date hora = new Date();
                SimpleDateFormat hora_formato = new SimpleDateFormat("dd/MM/yyyy");

                String vet2[] = hora_formato.format(hora).split("/");

                int mes = Integer.parseInt(vet2[1]);
                element.setPeriod(mes < 9 ? vet2[1].substring(1) : vet2[1]);

                element.setInvoiceType("ND");
                element.setInvoiceNo(OrganizarRefFactura.saft(modelo.getNext()));
                //  CRIAR UMA LISTA DE FATURA COM TODOS OS SEUS ITENS
                DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));

                /* if (facturaOriginalController.getDocumentoRectificativos(codigoFatura).equalsIgnoreCase(rs.getString("NextMovimento"))) {
                    element.getDocumentStatus().setInvoiceStatus("N");
                } else {
                    element.getDocumentStatus().setInvoiceStatus("A");
                }
                 */
                element.getDocumentStatus().setInvoiceStatusDate(vet[0] + "T" + vet[1]);
                element.getDocumentStatus().setSourceID(modelo.getCodigoUtilizador() + "");
                element.setSourceID(modelo.getCodigoUtilizador() + "");

                element.setLineItens(getItemNC(codigoFatura, dataFactura, modelo.getObs(), codigoNC));
                //  element.setLineItens(getItemNC(codigoFatura, dataFactura, modelo.getObs(), codigoNC));
                Double taxPayable = totalIvaMovimento(codigoNC);

                String str = getValorCasaDecimal(df.format(taxPayable), ".");
                element.getDocumentTotals().setTaxPayable(str.replace(',', '.'));

                double total = totalApagarMovimento(codigoNC);
                double imposto = taxPayable;
                double qtd = totalQtdMovimento(codigoNC, con);
//                double desconto = totalDescontoMovimento(codigoNC, con);

//              Double[] valores = getValores(codigoFatura, dataFactura);
                double novoPreco = 0;
                double novoSubTotal = 0;

                novoPreco = (total - imposto) / qtd;
                novoSubTotal = novoPreco * qtd;

                str = getValorCasaDecimal(df.format(novoSubTotal), ".");
                element.getDocumentTotals().setNetTotal(str.replace(',', '.'));

                Double netTotal = novoSubTotal;
                Double grossTotal = netTotal + taxPayable;

                str = getValorCasaDecimal(df.format(grossTotal), ".");
                element.getDocumentTotals().setGrossTotal(str.replace(',', '.'));

                str = getValorCasaDecimal(df.format(total), ".");
//                element.getDocumentTotals().getPayment().setPaymentAmount(str.replace(',', '.'));

                //   element.getDocumentTotals().getPayment().setPaymentDate(vet[0]);
                List<LineItensWithholding.WithholdingTax> listaWithholdingTax = new ArrayList<>();

                element.setWithholdingTax(listaWithholdingTax);

                List<Invoice> lista = new ArrayList<>();
                lista.add(element);
                invoiceses.add(new Invoices(lista));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return invoiceses;
    }

    public List<WorkDocuments> getProformas(String data, String data1) {

        String sql = "select * from factura f "
                + " INNER JOIN pacientes c ON c.idPaciente = f.codigoCliente "
                + " where date(f.dataFactura) between '" + data + "' and '" + data1 + "' and estado = 'FACTURA PROFORMA'";
        // CRIAR UMA LISTA QUE ARMAZENA AS FATURAS
        List<WorkDocuments> workDocumentses = new ArrayList<>();
        String hashAnterior = "";
        String hash = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet query = ps.executeQuery();
            String dataFactura2 = "";
            while (query.next()) {

                String dataFactura = query.getString("f.dataFactura").substring(0, 10);
                Integer codigoFatura = query.getInt("f.idFactura");

                Factura factura = new Factura();
                factura = findFacturaByFactura(query.getInt("f.idFactura"), con);
                hash = query.getString("hashValor");
                WorkDocument element = new WorkDocument();
                element.setWorkDate(dataFactura);

//                if (hashAnterior.trim().isEmpty()) {
//                    element.setHash("" + hash);
//                }
                String datav = query.getString("f.dataFactura").substring(0, 19);
                String[] vet = datav.split(" ");
                element.setSystemEntryDate(vet[0] + "T" + vet[1]);

                if (query.getString("c.Nif").equals("999999999")) {

                    element.setCustomerID("1");

                } else {
                    element.setCustomerID(query.getString("idPaciente"));
                }

                Date hora = new Date();
                SimpleDateFormat hora_formato = new SimpleDateFormat("dd/MM/yyyy");

                String vet2[] = hora_formato.format(hora).split("/");

                int mes = Integer.parseInt(vet2[1]);
                element.setPeriod(mes < 9 ? vet2[1].substring(1) : vet2[1]);

                Double totalCredito = getTotalCreditoByFactura(codigoFatura);
                element.setLineItens(getItemFaturaProforma(codigoFatura, dataFactura, totalCredito));

                element.setWorkType("FP");
                element.setDocumentNumber("" + query.getString("f.nRef"));
                //  element.setDocumentNumber("FP " + OrganizarRefFactura.saft(query.getString("nextFactura")).trim().split(" ")[1]);

                //  CRIAR UMA LISTA DE FATURA COM TODOS OS SEUS ITENS
                DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));

                String str = getValorCasaDecimal(df.format(query.getDouble("f.descontoIVA")), ".");
                // String str = getValorCasaDecimal(df.format(factura.getDescontoIVA()), ".");
                element.getDocumentTotals().setTaxPayable(str.replace(',', '.'));
                element.getDocumentStatus().setWorkStatus("N");
                element.getDocumentStatus().setWorkStatusDate(vet[0] + "T" + vet[1]);
                element.getDocumentStatus().setSourceID(factura.getCodigoUtilizador() + "");
                element.setSourceID(factura.getCodigoUtilizador() + "");

                String workDate = vet[0];

                Double[] valores = getValores(codigoFatura, dataFactura);

                double novoPreco = 0;
                double novoSubTotal = 0;
                if (valores.length > 0) {

                    novoPreco = (valores[0] - valores[1]) / valores[2];
                    novoSubTotal = novoPreco * valores[2];

                }

                str = getValorCasaDecimal(df.format(novoSubTotal), ".");
                element.getDocumentTotals().setNetTotal(str.replace(',', '.'));

                str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
//                str = getValorCasaDecimal(df.format(factura.getTotalApagar() + factura.getrV()), ".");
                //str = getValorCasaDecimal(df.format(factura.getValorAPagar() + factura.getDesconto() + fiController.getTotalDescontoItem(codigoFatura) + factura.getrV()), ".");
                element.getDocumentTotals().setGrossTotal(str.replace(',', '.'));

//                hash = RSA.getHash(element.getWorkDate()
//                        + ";" + element.getDocumentStatus().getWorkStatusDate() + ";"
//                        + element.getDocumentNumber() + ";"
//                        + element.getDocumentTotals().getGrossTotal() + ";" + hashAnterior);
                element.setHash("" + hash);

                List<WorkDocument> lista = new ArrayList<>();
                lista.add(element);
//                hashAnterior = hash;
                workDocumentses.add(new WorkDocuments(lista));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return workDocumentses;
    }

    public List<Payments> getRecibo(String data, String data1) {

        String sql = "select * from factura f "
                + " INNER JOIN pacientes c ON c.idPaciente = f.codigoCliente "
                + " where date(f.dataFactura) between '" + data + "' and '" + data1 + "' and estado = 'FACTURA CRÉDITO' AND Guia=1";
        // CRIAR UMA LISTA QUE ARMAZENA AS FATURAS
        List<Payments> workDocumentses = new ArrayList<>();
        String hashAnterior = "";
        String hash = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet query = ps.executeQuery();
            String dataFactura2 = "";
            while (query.next()) {

                String dataFactura = query.getString("f.dataFactura").substring(0, 10);
                Integer codigoFatura = query.getInt("f.idFactura");

                Factura factura = new Factura();
                factura = findFacturaByFactura(query.getInt("f.idFactura"), con);
                hash = query.getString("hashValor");
                Payment element = new Payment();
                element.setTransactionDate(dataFactura);
                element.getPaymentMethod().setPaymentAmount(query.getString("f.valorApagar"));
//                if (hashAnterior.trim().isEmpty()) {
//                    element.setHash("" + hash);
//                }
                String datav = query.getString("f.dataFactura").substring(0, 19);
                String[] vet = datav.split(" ");
                element.setSystemEntryDate(vet[0] + "T" + vet[1]);

                if (query.getString("c.Nif").equals("999999999")) {

                    element.setCustomerID(query.getString("idPaciente"));

                } else {
                    element.setCustomerID(query.getString("idPaciente"));
                }

                Date hora = new Date();
                SimpleDateFormat hora_formato = new SimpleDateFormat("dd/MM/yyyy");

                String vet2[] = hora_formato.format(hora).split("/");

                int mes = Integer.parseInt(vet2[1]);
                element.setPeriod(mes < 9 ? vet2[1].substring(1) : vet2[1]);

                Double totalCredito = getTotalReciboByFactura(codigoFatura);
                element.setLineItens(getItemFaturaRecibo(codigoFatura, dataFactura, totalCredito));

                element.setPaymentType("RC");
                element.setPaymentRefNo(query.getString("f.nRecibo"));
                // element.getPaymentMethod().setPaymentDate(query.getString("f.dataFactura"));
                element.getPaymentMethod().setPaymentDate("" + vet[0]);
                //  element.setDocumentNumber("FP " + OrganizarRefFactura.saft(query.getString("nextFactura")).trim().split(" ")[1]);

                //  CRIAR UMA LISTA DE FATURA COM TODOS OS SEUS ITENS
                DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));

                String str = getValorCasaDecimal(df.format(query.getDouble("f.descontoIVA")), ".");
                // String str = getValorCasaDecimal(df.format(factura.getDescontoIVA()), ".");
                element.getDocumentTotals().setTaxPayable(str.replace(',', '.'));
                element.getDocumentStatus().setPaymentStatus("N");

                element.getDocumentStatus().setPaymentStatusDate(vet[0] + "T" + vet[1]);
                element.getDocumentStatus().setSourceID(factura.getCodigoUtilizador() + "");
                element.setSourceID(factura.getCodigoUtilizador() + "");

                String workDate = vet[0];

                Double[] valores = getValores(codigoFatura, dataFactura);

                double novoPreco = 0;
                double novoSubTotal = 0;
                if (valores.length > 0) {

                    novoPreco = (valores[0] - valores[1]) / valores[2];
                    novoSubTotal = novoPreco * valores[2];

                }

                str = getValorCasaDecimal(df.format(novoSubTotal), ".");
                element.getDocumentTotals().setNetTotal(str.replace(',', '.'));

                str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
//                str = getValorCasaDecimal(df.format(factura.getTotalApagar() + factura.getrV()), ".");
                //str = getValorCasaDecimal(df.format(factura.getValorAPagar() + factura.getDesconto() + fiController.getTotalDescontoItem(codigoFatura) + factura.getrV()), ".");
                element.getDocumentTotals().setGrossTotal(str.replace(',', '.'));

//                hash = RSA.getHash(element.getWorkDate()
//                        + ";" + element.getDocumentStatus().getWorkStatusDate() + ";"
//                        + element.getDocumentNumber() + ";"
//                        + element.getDocumentTotals().getGrossTotal() + ";" + hashAnterior);
                //  element.setHash("" + hash);
                List<Payment> lista = new ArrayList<>();
                lista.add(element);
//                hashAnterior = hash;
                workDocumentses.add(new Payments(lista));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return workDocumentses;
    }

    public List<LineItensWorkDocument> getItemFaturaProforma(int codigoFatura, String dataFactura, Double totalCredito) {

        String sql = "SELECT f.*, p.*,t.*,mo.* FROM factura_itens f join servicos p on p.idServico = f.codigoProduto\n"
                + "join tipotaxa t on t.codigo = p.codigoTipoTaxa join motivo mo on mo.codigo = t.codigoMotivo\n"
                + "where f.codigoFactura=" + codigoFatura;
        System.out.println("::" + sql);
        List<LineItensWorkDocument> lista = new ArrayList<>();
        totalCredito = 0.0;
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            // Connection con = conFactory.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            while (rs.next()) {
                cont++;

                LineItensWorkDocument.LineItem item = new LineItensWorkDocument().getLine();
                item.setLineNumber(cont.toString());
                item.setProductCode(rs.getString("p.idServico"));
                item.setDescription(rs.getString("p.designacao"));
                item.setProductDescription(rs.getString("p.designacao"));
                item.setQuantity(df.format(rs.getDouble("f.quantidade")).replace(',', '.'));
                item.setUnitOfMeasure("*");

                //double total = rs.getDouble("f.preco") - rs.getDouble("f.DescontoProduto");
                double total2 = rs.getDouble("f.preco");
                double desconto = rs.getDouble("f.DescontoProduto");
                double imposto = rs.getDouble("f.descontoIVA");
                double qtd = rs.getDouble("f.quantidade");
                double total = total2 - desconto;
                System.out.println("Desconto Ba:" + total);

                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;

                item.setUnitPrice(getValorCasaDecimal(df.format(total), ".").replace(',', '.'));
                item.setTaxPointDate(dataFactura);

                //List<LineItensReferences.References> listaReferences = new ArrayList<>();
                item.setCreditAmount(getValorCasaDecimal(df.format(total), ".").replace(',', '.'));
                //item.setReferences(listaReferences);
                totalCredito += Double.parseDouble(getValorCasaDecimal(df.format(novoSubTotal), ".").replace(',', '.'));

                Taxa modelo1 = new Taxa();//(rs.getInt("t.Id"), rs.getString("t.Descricao"), rs.getDouble("t.Taxa"));
                if (modelo1.getTaxa() != 0) {

                    if (modelo1.getTaxa() == 14) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("NOR");
                    } else if (modelo1.getTaxa() == 5) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("RED");
                    }

                    item.getTax().setTaxPercentage("" + modelo1.getTaxa());

                } else {

                    int codigoMotivo = rs.getInt("p.codigoTipoTaxa");
                    MotivoController motivoController = new MotivoController(con);
                    MotivoModelo modelo = motivoController.getById(codigoMotivo);
                    String movivo = rs.getString("mo.codigoMotivo");
                    String movivo1 = rs.getString("mo.descricacao");
                    item.getTax().setTaxType("NS");
                    item.getTax().setTaxCode("NS");
                    item.setTaxExemptionReason("" + movivo1);
                    item.setTaxExemptionCode("" + movivo);
                    item.getTax().setTaxPercentage("0");
                }

                item.setSettlementAmount(rs.getString("f.descontoProduto"));
                LineItensWorkDocument linha = new LineItensWorkDocument();
                linha.setLine(item);
                lista.add(linha);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return lista;
    }

    public List<LineItensPayment> getItemFaturaRecibo(int codigoFatura, String dataFactura, Double totalCredito) {

        String sql = "SELECT f1.*,f.*, p.*,t.*,mo.* FROM factura f1 join factura_itens f on f1.idFactura=f.codigoFactura join servicos p on p.idServico = f.codigoProduto\n"
                + "join tipotaxa t on t.codigo = p.codigoTipoTaxa join motivo mo on mo.codigo = t.codigoMotivo\n"
                + "where f.codigoFactura=" + codigoFatura;
        System.out.println("Lista de Recibos:" + sql);
        List<LineItensPayment> lista = new ArrayList<>();
        totalCredito = 0.0;
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            // Connection con = conFactory.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            while (rs.next()) {
                cont++;

                LineItensPayment.LineItem item = new LineItensPayment().getLine();
                item.setLineNumber(cont.toString());
//                item.setProductCode(rs.getString("p.idServico"));
                //  item.setDebitAmount(rs.getString("p.designacao"));
//                item.setDescription(rs.getString("p.designacao"));
//                item.setQuantity(df.format(rs.getDouble("f.quantidade")).replace(',', '.'));
//                item.setUnitOfMeasure("*");
                double total2 = rs.getDouble("f.preco");
                double desconto = rs.getDouble("f.DescontoProduto");
                double imposto = rs.getDouble("f.descontoIVA");
                double qtd = rs.getDouble("f.quantidade");
                double total = total2 - desconto;
                System.out.println("Desconto Ba:" + total);

                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;

//                item.setUnitPrice(getValorCasaDecimal(df.format(total), ".").replace(',', '.'));
//                item.setTaxPointDate(dataFactura);
                item.getSourceDocumentID().setOriginatingON("" + rs.getString("f1.nRef"));
                item.getSourceDocumentID().setInvoiceDate(dataFactura);
                //List<LineItensReferences.References> listaReferences = new ArrayList<>();
                item.setCreditAmount(getValorCasaDecimal(df.format(total), ".").replace(',', '.'));

                //item.setReferences(listaReferences);
                totalCredito += Double.parseDouble(getValorCasaDecimal(df.format(novoSubTotal), ".").replace(',', '.'));

                //       Taxa modelo1 = new Taxa();
                Taxa modelo1 = findTaxa(rs.getInt("p.codigoTipoTaxa"), con);
                // System.out.println("Código Taxa:" + modelo1.getTaxa());
                if (modelo1.getTaxa() != 0) {

                    if (modelo1.getTaxa() == 14) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("NOR");
                    } else if (modelo1.getTaxa() == 5) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("RED");
                    }

                    item.getTax().setTaxPercentage("" + modelo1.getTaxa());

                } else {

                    int codigoMotivo = rs.getInt("p.codigoTipoTaxa");
                    MotivoController motivoController = new MotivoController(con);
                    MotivoModelo modelo = motivoController.getById(codigoMotivo);
                    String movivo = rs.getString("mo.codigoMotivo");
                    String movivo1 = rs.getString("mo.descricacao");
                    item.getTax().setTaxType("NS");
                    item.getTax().setTaxCode("NA");
                    item.setTaxExemptionReason("" + movivo1);
                    item.setTaxExemptionCode("" + movivo);
                    item.getTax().setTaxPercentage("0");
                }

                // item.SettlementAmount(rs.getString("f.descontoProduto"));
                LineItensPayment linha = new LineItensPayment();
                linha.setLine(item);
                lista.add(linha);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return lista;
    }

    public double getTotalCreditoByFactura(int codigoFatura) {

        String sql = "SELECT sum(f.totalGeral) as total,sum(f.descontoIVA) as imposto, sum(f.quantidade) as qtd,sum(f.descontoProduto) desconto FROM factura_itens f where f.codigoFactura=" + codigoFatura;

        double totalCredito = 0.0;
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            // Connection con = conFactory.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            if (rs.next()) {
                double total = rs.getDouble("total");
                double imposto = rs.getDouble("imposto");
                double qtd = rs.getDouble("qtd");
                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;
                totalCredito = Double.parseDouble(getValorCasaDecimal(df.format(novoSubTotal), ".").replace(',', '.'));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return totalCredito;
    }

    public double getTotalReciboByFactura(int codigoFatura) {

        String sql = "SELECT sum(f.totalGeral) as total,sum(f.descontoIVA) as imposto, sum(f.quantidade) as qtd,sum(f.descontoProduto) desconto FROM factura_itens f where f.codigoFactura=" + codigoFatura;

        double totalCredito = 0.0;
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            // Connection con = conFactory.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            if (rs.next()) {
                double total = rs.getDouble("total");
                double imposto = rs.getDouble("imposto");
                double qtd = rs.getDouble("qtd");
                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;
                totalCredito = Double.parseDouble(getValorCasaDecimal(df.format(novoSubTotal), ".").replace(',', '.'));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return totalCredito;
    }

    public Double[] getValores(int codigoFatura, String dataFactura) {

        String sql = "SELECT sum(f.totalGeral) as total,sum(f.descontoIVA) as imposto, sum(f.quantidade) as qtd,sum(f.descontoProduto) as desconto FROM factura_itens f\n"
                + " where f.codigoFactura=" + codigoFatura;
        Double[] valores = new Double[4];
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {
            // Connection con = conFactory.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            if (rs.next()) {

                double total = rs.getDouble("total");
                double imposto = rs.getDouble("imposto");
                double qtd = rs.getDouble("qtd");
                double desconto = rs.getDouble("desconto");

                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;

                valores[0] = total;
                valores[1] = imposto;
                valores[2] = qtd;
                valores[3] = desconto;

            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return valores;
    }

    public int getNsequencial(Connection con) {
        sql = "SELECT ano FROM serie where status = 2";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("ano");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getSerie(Connection con) {
        String codigo = "";
        sql = "SELECT designacao FROM serie where status = 2";
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getString("designacao");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return codigo;
        }
        return codigo;
    }

    public String getSerie(int id) {
        String codigo = "";
        String sql = "SELECT Designacao FROM serie where codigo = " + id;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getString("Designacao");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return codigo;
        }
        return codigo;
    }

    public int getNsequencial(int codigo) {

        sql = "SELECT Ano FROM serie where codigo =" + codigo;

        try {
            // con = conFactory.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Ano");
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

    public Taxa findTaxa(int codigo, Connection con) {

        sql = "SELECT * FROM tipotaxa where codigo = " + codigo;
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Taxa tipo = new Taxa();
                tipo.setCodigo(rs.getInt(1));
                tipo.setTaxa(rs.getInt(2));
                tipo.setMotivo(rs.getString(3));
                tipo.setCodigoStatus(rs.getInt(4));
                tipo.setDescricao(rs.getString(5));
                return tipo;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return null;
    }

    public List<LineItens> getItemFatura(int codigoFatura, String dataFactura, Connection con) {
        String sql = "SELECT f.*, p.* FROM factura_itens f join servicos p on p.idServico = f.codigoProduto\n"
                + "where f.codigoFactura=" + codigoFatura;
        System.out.println("Teste:" + sql);
        int codigoMotivo = 0;
        List<LineItens> lista = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer cont = 0;
            while (rs.next()) {
                cont++;
                LineItens.LineItem item = new LineItens().getLine();
                item.setLineNumber(cont.toString());

                item.setProductCode(rs.getString("p.idServico"));
                item.setProductDescription(rs.getString("p.designacao"));
                System.out.println("Produto:" + rs.getString("p.designacao"));
                item.setQuantity(df.format(rs.getDouble("f.quantidade")).replace(',', '.'));
                item.setUnitOfMeasure("*");
                item.setDescription(rs.getString("p.designacao"));
                
                //  item.setUnitOfMeasure(rs1.getString("unidadeMedida"));
                //double total = rs.getDouble("f.preco") - rs.getDouble("f.DescontoProduto");
                double total2 = rs.getDouble("f.preco");
                double desconto = rs.getDouble("f.DescontoProduto");
                double imposto = rs.getDouble("f.descontoIVA");
                double qtd = rs.getDouble("f.quantidade");
                double total = total2 - desconto;
                System.out.println("Desconto Ba:" + total);
                double creditAmount = total2 * qtd;
                item.setCreditAmount("" + creditAmount);
                double novoPreco = (total - imposto) / qtd;
                double novoSubTotal = novoPreco * qtd;

                item.setUnitPrice(getValorCasaDecimal(df.format(total2), ".").replace(',', '.'));
                //  item.setUnitPrice(getValorCasaDecimal(df.format(novoPreco), ".").replace(',', '.'));
//                item.setUnitPrice(getValorCasaDecimal(df.format(rs.getDouble("f.Total") / rs.getDouble("f.Qtd")), ".").replace(',', '.'));
                item.setTaxPointDate(dataFactura);
//                String descricao = rs.getString("p.DescricaoProduto");

                // List<LineItensReferences.getSourceDocumentIDs> listaReferences = new ArrayList<>();
                //listaReferences.set
                //   item.setDebitAmount(getValorCasaDecimal(df.format(total), ".").replace(',', '.'));
                //item.setCreditAmount(getValorCasaDecimal(df.format(novoSubTotal), ".").replace(',', '.'));
//                item.setCreditAmount(getValorCasaDecimal(df.format(rs.getDouble("f.Total")), ".").replace(',', '.'));
                // item.setReferences(listaReferences);
                item.setSettlementAmount(rs.getString("f.descontoProduto"));

                Taxa modelo1 = findTaxa(rs.getInt("p.codigoTipoTaxa"), con);

                if (modelo1.getTaxa() != 0) {

                    if (modelo1.getTaxa() == 14) {
                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("NOR");
                    } else if (modelo1.getTaxa() == 5) {

                        item.getTax().setTaxType("IVA");
                        item.getTax().setTaxCode("RED");
                    }

                    item.getTax().setTaxPercentage("" + modelo1.getTaxa());
                    //  item.setSettlementAmount(rs.getString("f.descontoProduto"));

                } else {
                    // codigoMotivo = rs.getInt("p.codigoTipoTaxa");
                    System.out.println("Codigo Tipo" + modelo1.getCodigo());
                    MotivoController motivoController = new MotivoController(con);
                    MotivoModelo modelo = motivoController.getById(Integer.parseInt(modelo1.getMotivo()));

                    item.getTax().setTaxType("NS");
                    item.getTax().setTaxCode("NS");
                    item.setTaxExemptionReason(modelo.getDescricao());
                    item.setTaxExemptionCode(modelo.getCodigoMotivo());
                    item.getTax().setTaxPercentage("0");
                    //  item.setSettlementAmount(rs.getString("f.descontoProduto"));
                }

//                item.setSettlementAmount(rs.getString("f.descontoProduto"));
                LineItens linha = new LineItens();
                linha.setLine(item);
                lista.add(linha);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return lista;
    }

    public Factura findFacturaByFactura(int codigo, Connection con) {
        String sql = "SELECT * from factura where idFactura = " + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("idFactura"));
                factura.setDataFactura(rs.getDate("dataFactura"));
                factura.setValorApagar(rs.getDouble("valorApagar"));
                factura.setDescontoIVA(rs.getDouble("descontoIVA"));
                factura.setValorNumerario(rs.getDouble("valorNumerario"));
                factura.setDescontoFactura(rs.getDouble("descontoFactura"));
                //factura.setrV(rs.getDouble("retencaoV"));
                factura.setEstado(rs.getString("estado"));
                // factura.setNif(rs.getString("nifCliente"));
                factura.setCodigoUtilizador(rs.getInt("codigoUtilizador"));
                factura.setCodigoCliente(rs.getInt("codigoCliente"));
                factura.setNextFactura("0");
                factura.setCodigoFormaPagamento(rs.getInt("codigoFormaPagamento"));
                return factura;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String getReferenciaFactura(int codigo) {
        String sql = "SELEC facturaReference FROM factura where idFactura =" + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("facturaReference");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Invoices> getFacturaGerais(String data, String data1) {
        String sql = "select * from factura f where date(f.dataFactura) between '" + data + "' and '" + data1 + "' ORDER BY idFactura";
        List<Invoices> invoiceses = new ArrayList<>();
        System.out.println("sql ::: " + sql);
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dataAux = rs.getString("f.dataFactura");
                String dataFactura = dataAux.substring(0, 10);
                Integer codigoFatura = rs.getInt("f.idFactura");
                String tipoFactura = rs.getString("f.estado");
                Invoice element = new Invoice();
                element.setCustomerID(rs.getString("f.codigoCliente"));
                element.setInvoiceNo(rs.getString("f.nRef"));
                String organiza = rs.getString("f.nRef");
                element.setInvoiceDate(dataFactura);
                element.setHash("" + rs.getString("hashValor"));
                String datav = rs.getString("f.dataFactura").substring(0, 19);
                String[] vet = datav.split(" ");
                element.setSystemEntryDate(vet[0] + "T" + vet[1]);
                Date hora = new Date();
                SimpleDateFormat hora_formato = new SimpleDateFormat("dd/MM/yyyy");
                String vet2[] = hora_formato.format(hora).split("/");
                int mes = Integer.parseInt(vet2[1]);
                element.setPeriod(mes < 9 ? vet2[1].substring(1) : vet2[1]);
//                element.setInvoiceNo(OrganizarRefFactura.saft(organiza.trim()));
                //  Factura factura = new Factura();
                if (tipoFactura.equals("FACTURA PRONTO")) {
                    element.setInvoiceType("FR");
                } else {
                    element.setInvoiceType("FR");
                }
                element.setLineItens(getItemFatura(codigoFatura, dataFactura, con));
                Factura factura = findFacturaByFactura(codigoFatura, con);
                DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
                String str = getValorCasaDecimal(df.format(factura.getDescontoIVA()), ".");
                element.getDocumentTotals().setTaxPayable(str.replace(',', '.'));
                if (tipoFactura.equals("FACTURA ANULADA")) {
                    element.getDocumentStatus().setInvoiceStatus("A");
                } else {
                    element.getDocumentStatus().setInvoiceStatus("N");
                }
                element.getDocumentStatus().setInvoiceStatusDate(vet[0] + "T" + vet[1]);
                element.getDocumentStatus().setSourceID(factura.getCodigoUtilizador() + "");
                element.setSourceID(factura.getCodigoUtilizador() + "");

                str = getValorCasaDecimal(df.format(factura.getValorApagar() - (factura.getDescontoFactura() + factura.getDescontoIVA())), ".");
                element.getDocumentTotals().setNetTotal(str.replace(',', '.'));

                str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
                //str = getValorCasaDecimal(df.format(factura.getValorAPagar() + factura.getDesconto() + fiController.getTotalDescontoItem(codigoFatura) + factura.getrV()), ".");
                element.getDocumentTotals().setGrossTotal(str.replace(',', '.'));

                str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
                //   element.getDocumentTotals().getPayment().setPaymentAmount(str.replace(',', '.'));
                // element.getDocumentTotals().getPayment().setPaymentDate(vet[0]);

                List<Invoice> lista = new ArrayList<>();
                lista.add(element);
                System.out.println("passou por aqui");
                invoiceses.add(new Invoices(lista));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return invoiceses;
    }

    public Factura findFacturaByFactura(int codigo) {
        String sql = "SELECT * from factura v where v.idFactura = " + codigo + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Factura factura = new Factura();
                factura.setValorApagar(rs.getDouble("valorApagar"));
                factura.setDesconto(rs.getDouble("desconto"));
                return factura;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    ////////// Notas de CreditoItens
    public Double totalQtdMovimento(int id) throws Exception {

        String sql = "SELECT sum(quantidade) iva FROM notaCreditoItens where codigoNota =" + id;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getDouble(1);
        }
        return 0.0;
    }

//    public double totalApagarMovimento(int id) {
//        String sql = "SELECT sum(totalGeral) as total FROM notaCreditoItens where codigoNota =" + id;
//        try {
//            System.out.println(sql);
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//
//                return rs.getDouble("total");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return 0;
//    }
//
//    public double totalIvaMovimento(int id) {
//        String sql = "SELECT sum(descontoIVA) iva as  FROM notaCreditoItens where codigoNota =" + id;
//        try {
//            System.out.println(sql);
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//
//                return rs.getDouble("total");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return 0;
//    }
}
