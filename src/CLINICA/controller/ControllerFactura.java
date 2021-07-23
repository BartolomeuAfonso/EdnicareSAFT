/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Factura;
import CLINICA.modelo.Guia;
import CLINICA.modelo.TipoTaxaModelo;
import CLINICA.modelo.MotivoModelo;
import CLINICA.modelo.OficioModelo;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import sf.ce.utilizacoes.Data;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import json_xml_iva.RSA;
import sun.security.provider.SHA;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerFactura {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql, hashAnterior, hashcode_actual;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    DecimalFormat df = new DecimalFormat("#,###.00");
    TipoTaxaModelo modelo1;
    ControllerUsuario controllerUsuario = new ControllerUsuario(con);
    Data d = new Data();

    public ControllerFactura(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Factura factura) {
        LocalDate dateActual = LocalDate.now();
        java.sql.Date data_venda = new java.sql.Date(getDataTime().getTime());
        LocalDate dataVencimento;
        hashcode_actual = "";
        hashAnterior = "";
        String nRef;
//        if (this.getLastFactura() > 0) {
        int soma = 1;
        df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        String str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
        String grosstotal = (str.replace(',', '.'));
        String datav = String.valueOf(getDataTime()).substring(0, 10);
        String data_hora = datav + "T" + getHora();

        if ("FACTURA PRONTO".equals(factura.getEstado())) {
            soma = soma + getLastCodigo("FR", "FR");
            hashAnterior = getLasHash("FR", "FACTURA ANULADA");
            nRef = "FR " + getAnoemCurso() + "/" + soma;

        } else {
            hashAnterior = getLasHash("FP", "FP");
            soma = soma + getLastCodigo("FP", "FP");
            nRef = "FP " + getAnoemCurso() + "/" + soma;
        }
        try {
            System.out.println("Data: " + datav);
            System.out.println("Hora:" + data_hora);
            System.out.println("Referencia:" + nRef);
            System.out.println("GrossTotal:" + grosstotal);
            System.out.println("Hash Anterior:" + hashAnterior);
            hashcode_actual = RSA.getHash(datav
                    + ";" + data_hora + ";"
                    + nRef + ";"
                    + grosstotal + ";" + hashAnterior);
            System.out.println("hashcode_actual:" + hashcode_actual);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        sql = "INSERT INTO factura(dataFactura,valorNumerario,valorMulticaixa,nomeClientes,quantidadeItens,codigoUtilizador,codigoFormaPagamento,codigoCliente,troco,desconto,valorApagar,valorApagarExtenso,hashValor,facturaReference,estado,codigoSeguro,descontoIVA,nEcomenda,nIBAN,descontoFactura,subTotal,nRef,numerador,nif)values(now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //  System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            //ps.setDate(1, (Date) factura.getDataFactura());
            ps.setDouble(1, factura.getValorNumerario());
            ps.setDouble(2, factura.getValorMulticaixa());
            ps.setString(3, factura.getNomeCliente());
            ps.setInt(4, factura.getQuantidadeItens());
            ps.setInt(5, factura.getCodigoUtilizador());
            ps.setInt(6, factura.getCodigoFormaPagamento());
            ps.setInt(7, factura.getCodigoCliente());
            ps.setDouble(8, factura.getTroco());
            ps.setDouble(9, factura.getDesconto());
            ps.setDouble(10, factura.getValorApagar());
            ps.setString(11, factura.getVaorporExtenso());
            ps.setString(12, hashcode_actual);
            ps.setString(13, getValorCaracterHash(hashcode_actual));
            ps.setString(14, factura.getEstado());
            ps.setInt(15, factura.getCodigoSeguro());
            ps.setDouble(16, factura.getDescontoIVA());
            ps.setString(17, factura.getnEcomenda());
            ps.setString(18, factura.getnIBAN());
            ps.setDouble(19, factura.getDescontoFactura());
            ps.setDouble(20, factura.getValorApagar() - (factura.getDescontoIVA() + factura.getDescontoFactura()));
            ps.setString(21, nRef);
            ps.setString(22, "" + soma);
            ps.setString(23, factura.getNif());
            ps.execute();
            //       JOptionPane.showMessageDialog(null, "Venda realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public String getReferenciaFactura(int codigo) {
        String sql = "SELECT nRef FROM factura where idFactura =" + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nRef");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getDataFactura(int codigo) {
        String sql = "SELECT DATE(dataFactura) as nRef  FROM factura where idFactura =" + codigo;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nRef");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getDataActura(int codigo) {
        String sql = "SELECT Date(dataFactura) as data FROM factura where idFactura =" + codigo;
        System.out.println("SQL>::" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("data");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getLastFactura() {
        try {
            sql = "select max(idfactura) as max from factura";
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public double getvalorApagar(int codigo) {
        try {
            sql = "SELECT valorApagar as Total FROM factura f where idFactura=" + codigo;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("Total");
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void update(double valor, double desconto, int codigo, double descontoTotal, double descontoIva) {
        sql = "UPDATE factura SET valorApagar =" + valor + ", desconto =" + desconto + ",descontoIVA=" + descontoIva + ",descontoFactura =" + descontoTotal + " WHERE idFactura=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia Actualizado");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void updateRecibo(int codigoFactura) {
        sql = "UPDATE factura SET Guia =1 WHERE idFactura=" + codigoFactura;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Rebibo Emitido Actualizado");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void update2(double valor, int codigo, double descontoTotal) {
        sql = "UPDATE factura SET valorApagar =" + valor + ",descontoFactura =" + descontoTotal + " WHERE idFactura=" + codigo;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia Actualizado");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void updateCrédito(int codigo, double valor, double valor1, int codigoForma) {
        sql = "UPDATE factura SET troco =0, valorNumerario ='" + valor + "',valorMulticaixa ='" + valor1 + "', estado ='FACTURA PRONTO',"
                + "codigoFormaPagamento='" + codigoForma + "' WHERE idFactura=" + codigo;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia Actualizado");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
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

    public void getAnularFactura(int codigo) {
        sql = "update factura set estado ='ANULADO' WHERE idFactura=" + codigo;
        System.out.println("Feito:" + sql);
        try {
            PreparedStatement prepara = con.prepareStatement(sql);
            prepara.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        JOptionPane.showMessageDialog(null, "Factura eliminado com Sucesso");

    }

    public TipoTaxaModelo findTaxa(int codigo) {

        sql = "SELECT * FROM tipotaxa where codigo = " + codigo;
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                TipoTaxaModelo tipo = new TipoTaxaModelo();
                tipo.setCodigo(rs.getInt(1));
                tipo.setTaxa(rs.getInt(2));
                tipo.setMotivo(new MotivoModelo(rs.getInt(3)));
                tipo.setCodigoStatus(rs.getInt(4));
                tipo.setDescricao(rs.getString(5));
                return tipo;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return null;
    }

    public int getMotivoTaxa(int codigo) {
        try {
            sql = "SELECT * FROM tipotaxa where codigo=" + codigo;
            System.out.println("Consulta:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt("codigoMotivo");
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public MotivoModelo findMotivoByCodigo(int codigo) {
        sql = "SELECT * from motivo where codigo = " + codigo + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                MotivoModelo modelo = new MotivoModelo();
                modelo.setCodigo(rs.getInt("codigo"));
                modelo.setDescricao(rs.getString("descricacao"));
                modelo.setCodigoStatus(rs.getInt("codigoStatus"));
                modelo.setCodigoMotivo(rs.getString("codigoMotivo"));

                return modelo;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
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

    public Date getDataTime() {
        sql = "SELECT CURRENT_TIMESTAMP as DATAHORA";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("DATAHORA");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public String getHora() {
        sql = "SELECT CURRENT_TIME as TIME";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TIME");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public String getData() {
        sql = "SELECT CURRENT_DATE as DATAHORA";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("DATAHORA");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public double getTotalDebito(String data, String data1) {
        sql = "select (sum(ValorApagar)-sum(desconto)) as total from factura where date(DataFactura) between '" + data + "' and '" + data1 + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getTotalCredit(String data, String data1) {
        sql = "select sum(ValorApagar) as total from factura where date(dataFactura) between '" + data + "' and '" + data1 + "'";
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

    public String getSerie() {
        String codigo = "";
        sql = "SELECT Designacao FROM serie where Status = 2";
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

    public int getNsequencial() {

        sql = "SELECT Ano FROM serie where Status = 2";
        try {
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

    public int getNsequencial1() {

        sql = "SELECT * FROM serie s inner join ano a\n"
                + "on s.Ano=a.Codigo";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("a.Ano");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Factura findFacturaByFactura(int codigo) {
        sql = "SELECT * from factura where idfactura =" + codigo;
        System.out.println("Código Factura:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                Factura factura = new Factura();
                factura.setValorApagar(rs.getDouble("ValorAPagar"));
                factura.setDesconto(rs.getDouble("desconto"));
                return factura;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String getValorCaracterHash(String str) {
        return "" + str.charAt(0) + str.charAt(10) + str.charAt(20) + str.charAt(30);
    }

    /// SEGURADORA
    public void salvarGuia(Factura factura) {
        LocalDate dateActual = LocalDate.now();
        java.sql.Date data_venda = new java.sql.Date(getDataTime().getTime());
        LocalDate dataVencimento;
        hashcode_actual = "";
        hashAnterior = "";
        String nRef, nRecibo = null;
//        if (this.getLastFactura() > 0) {
        int soma = 1;
        df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        String str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
        String grosstotal = (str.replace(',', '.'));
        String datav = String.valueOf(getDataTime()).substring(0, 10);
        System.out.println("Data Venda:" + datav);
        String data_hora = datav + "T" + getHora();
        String novaData = datav + " " + getHora();
        String dataFactura;
        if (factura.getDataVencimento().equals(datav)) {
            dataFactura = datav;
        } else {
            dataFactura = factura.getDataVencimento();
        }

//        String data_hora = "2021-04-11T20:12:10";
        if ("FACTURA CRÉDITO".equals(factura.getEstado())) {
            soma = soma + getLastCodigo("FT", "FT");
            hashAnterior = getLasHash("FT", "FT");
            nRef = "FT " + getAnoemCurso() + "/" + soma;
            nRecibo = "RC " + getAnoemCurso() + "/" + soma;

        } else {
            hashAnterior = getLasHash("FP", "FP");
            soma = soma + getLastCodigo("FP", "FP");
            nRef = "FP " + getAnoemCurso() + "/" + soma;
        }
        try {
            System.out.println("Data: " + datav);
            System.out.println("Hora:" + data_hora);
            System.out.println("Referencia:" + nRef);
            System.out.println("GrossTotal:" + grosstotal);
            System.out.println("Hash Anterior:" + hashAnterior);
            hashcode_actual = RSA.getHash(datav
                    + ";" + data_hora + ";"
                    + nRef + ";"
                    + grosstotal + ";" + hashAnterior);
            System.out.println("hashcode_actual:" + hashcode_actual);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "INSERT INTO factura(codigoUtilizador,codigoCliente,valorApagar,nomeClientes,numerador,desconto,codigoSeguro,codigoFormaPagamento,estado,hashValor,facturaReference,Guia,descontoFactura,dataFactura,nIBAN,descontoIVA,subTotal,nRef,nRecibo)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        System.out.println("sql");
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getCodigoUtilizador());
            ps.setInt(2, factura.getCodigoCliente());
            ps.setDouble(3, factura.getValorApagar());
            ps.setString(4, factura.getNomeCliente());
            ps.setInt(5, soma);
            ps.setDouble(6, factura.getDesconto());
            ps.setInt(7, factura.getCodigoSeguro());
            ps.setInt(8, factura.getCodigoFormaPagamento());
            ps.setString(9, factura.getEstado());
            ps.setString(10, hashcode_actual);
            ps.setString(11, getValorCaracterHash(hashcode_actual).toUpperCase());
            ps.setString(12, factura.getNumOrdem());
            ps.setDouble(13, factura.getDescontoFactura());
            ps.setString(14, novaData);
            // ps.setString(14, dataFactura);
            ps.setString(15, factura.getAviaoNavio());
            ps.setDouble(16, factura.getDescontoIVA());
            ps.setDouble(17, factura.getValorApagar() - (factura.getDescontoIVA() + factura.getDescontoFactura()));
            ps.setString(18, nRef);
            ps.setString(19, nRecibo);
            ps.execute();
            //     JOptionPane.showMessageDialog(null, "Guia realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void getAnularGuia(int codigo) {

        //    conexao.Connectando();
        String sql = "update guia set estado ='ANULADO' WHERE idGuia=" + codigo;
        System.out.println("Feito:" + sql);
        try {
            PreparedStatement prepara = con.prepareStatement(sql);
            prepara.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<String> getNomeGuia(String empresa) {
        ///  conexao.Connectando();
        sql = "SELECT DISTINCT g.nomeClientes as designacao FROM factura g inner join pacientes p on g.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros=p.codigoSeguro where Date(dataFactura)=Current_date and e.designacao='" + empresa + "'";
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

    public ArrayList<OficioModelo> getOficio(String empresa) {
        //     conexao.Connectando();
        sql = "SELECT DISTINCT g.idfactura as guia,e.designacao as empresa, p.cartaoPS as cartao, p.nomecompleto as nome,date(g.dataFactura) as dataGuia, g.valorApagar as valor FROM factura g inner join pacientes p on g.codigoCliente=p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros =p.codigoSeguro\n"
                + "where e.designacao ='" + empresa + "'";
        System.out.println("Consulta:" + sql);
        OficioModelo oficioModelo = new OficioModelo();
        ArrayList<OficioModelo> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                oficioModelo.setGuia(rs.getString("guia"));
                oficioModelo.setEmpresa(rs.getString("empresa"));
                oficioModelo.setCartao(rs.getString("cartao"));
                oficioModelo.setNome(rs.getString("nome"));
                oficioModelo.setData(rs.getString("dataGuia"));
                oficioModelo.setValor(rs.getString("valor"));
                lista.add(oficioModelo);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeGuiaporData(String data) {
        //  conexao.Connectando();
        sql = "SELECT DISTINCT g.nomeClientes as designacao FROM factura g where Date(dataFactura)='" + data + "' AND g.codigoSeguro<>8";
        System.out.println("Consulta Seguro Data:" + sql);
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

    public ArrayList<String> getNumeroGuia(String data, String nome) {
        //conexao.Connectando();
        sql = "SELECT distinct g.idfactura as guia FROM factura g where date(g.dataFactura) ='" + data + "' and g.nomeclientes ='" + nome + "'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("guia"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<Factura> getListaFactura(String data, String data1) {
        sql = "SELECT distinct g.idfactura as guia FROM factura g where date(dataFactura) between '" + data + "' and '" + data1 + "'";
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setIdFactura(rs.getInt("guia"));
                lista.add(factura);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public boolean existeGuia(int numero_guia) {
        // conexao.Connectando();
        sql = "SELECT idfactura FROM factura g where idfactura=" + numero_guia;
        System.out.println("Nº Guia:" + numero_guia);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return true;
        }

        return false;
    }

    public boolean existeGuiaProduto(int numero_guia, int produto) {
        //  conexao.Connectando();
        sql = "SELECT codigoServico FROM factura g where idGuia=" + numero_guia + " AND codigoServico=" + produto;
        System.out.println("Nº Guia:" + numero_guia);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return true;
        }

        return false;
    }

    public int getCodigoNumeradorEmpresa(String codigoEmpresa) {
        // conexao.();
        sql = "SELECT max(numerador) as max FROM factura g inner join pacientes p on g.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros = p.codigoSeguro\n"
                + "where e.designacao='" + codigoEmpresa + "';";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public void salvarEmpresa(Factura factura) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        LocalDate dateActual = LocalDate.now();
        java.sql.Date data_venda = new java.sql.Date(getDataTime().getTime());
        LocalDate dataVencimento;
        hashcode_actual = "";
        hashAnterior = "";
        String nRef;
        int soma = 1;
//        if (this.getLastFactura() > 0) {
        df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        String str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
        String grosstotal = (str.replace(',', '.'));
        String datav = String.valueOf(getDataTime()).substring(0, 10);
        String data_hora = datav + "T" + getHora();

        if ("FACTURA PRONTO".equals(factura.getEstado())) {
            hashAnterior = getLasHash("FR", "FR");
            soma = soma + getLastCodigo("FR", "FR");
            nRef = "FR " + getAnoemCurso() + "/" + soma;
        } else {
            hashAnterior = getLasHash("FP", "FP");
            soma = soma + getLastCodigo("FP", "FP");
            nRef = "FP " + getAnoemCurso() + "/" + soma;
        }
        hashcode_actual = RSA.getHash(datav + ";" + data_hora + ";" + nRef + ";" + grosstotal + ";" + hashAnterior);
        //$this->hash_str = strval($this->InvoiceDate . ";" . $this->SystemEntryDate . ";" . $this->InvoiceNo . ";" . $this->GrossTotal . ";" . $this->hash_posterior);        
// hashcode_actual = RSA.getHash(factura.getDataFactura() + "" + data_hora + "" + getValorCaracterHash(hashAnterior) + "" + grosstotal + "" + hashAnterior);

        sql = "INSERT INTO factura(dataFactura,valorNumerario,valorMulticaixa,nomeClientes,quantidadeItens,codigoUtilizador,codigoFormaPagamento,codigoEmpresa,troco,desconto,valorApagar,valorApagarExtenso,hashValor,facturaReference,estado,codigoSeguro,descontoIVA,nEcomenda,nIBAN,descontoFactura,subTotal,nRef,numerador)values(now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //  System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            // ps.setDate(1, (Date) factura.getDataFactura());
            ps.setDouble(1, factura.getValorNumerario());
            ps.setDouble(2, factura.getValorMulticaixa());
            ps.setString(3, factura.getNomeCliente());
            ps.setInt(4, factura.getQuantidadeItens());
            ps.setInt(5, factura.getCodigoUtilizador());
            ps.setInt(6, factura.getCodigoFormaPagamento());
            ps.setInt(7, factura.getCodigoCliente());
            ps.setDouble(8, factura.getTroco());
            ps.setDouble(9, factura.getDesconto());
            ps.setDouble(10, factura.getValorApagar());
            ps.setString(11, factura.getVaorporExtenso());
            ps.setString(12, hashcode_actual);
            ps.setString(13, getValorCaracterHash(hashcode_actual).toUpperCase());
            ps.setString(14, factura.getEstado());
            ps.setInt(15, factura.getCodigoSeguro());
            ps.setDouble(16, factura.getDescontoIVA());
            ps.setString(17, factura.getnEcomenda());
            ps.setString(18, factura.getnIBAN());
            ps.setDouble(19, factura.getDescontoFactura());
            ps.setDouble(20, factura.getValorApagar() - (factura.getDescontoIVA() + factura.getDescontoFactura()));
            ps.setString(21, nRef);
            ps.setString(22, "" + soma);
            ps.execute();
            //    JOptionPane.showMessageDialog(null, "Venda realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public String getAnoFiscal(String tipo, String ano) {

        return tipo + " AGT" + ano;
    }

    public String getTipoDocumento() {

        return "FR";
    }

    public int getCodigoFacturaByNref(String nRef) {
        String sql = "SELECT idFactura factura where nRef like '" + nRef + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_venda");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getLasHash(String status, String status2) {
        try {
            String sql = "select hashValor as hash from factura where LEFT(nRef,2)='" + status + "'  order by idFactura desc limit 1";
            System.out.println("Hash anterior:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getString("hash");
                }
            } catch (SQLException ex) {

                ex.printStackTrace();
            }
            return "";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public int getLastCodigo(String status, String status2) {
        try {
            String sql = "select numerador as numerador from factura where LEFT(nRef,2)='" + status + "' order by idFactura desc limit 1";
            System.out.println("numerador:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt("numerador");
                }
            } catch (SQLException ex) {

                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getNome() {
        sql = "SELECT f.nomeClientes as guia FROM factura f where date(dataFactura)=CURRENT_DATE";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("guia"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeLike(String nome) {
        sql = "SELECT nomeClientes FROM factura f where nomeClientes LIKE '%" + nome + "%'";
        System.out.println("" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeClientes"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getFactura(int codigoCliente) {
        sql = "SELECT idFactura FROM factura f where date(f.dataFactura)=CURRENT_DATE AND f.estado<>'FACTURA ANULADA' AND codigoCliente=" + codigoCliente;
        System.out.println("Codigo:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("idFactura"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getFacturaItens(int factura) {
        sql = "SELECT * FROM factura f where idFactura=" + factura;
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                lista.add(rs.getString("guia"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String getCodigoRecibo(int codigo) {
        String sql = "SELECT Guia from factura where idFactura=" + codigo;
        System.out.println("Para recibo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Guia");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getCodigoReferencia(String codigo) {
        String sql = "SELECT nRef from factura where idFactura=" + codigo;
        System.out.println("Para recibo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nRef");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Date getDataStistema() {
        sql = "SELECT NOW() as DataSistema";
        //     sql = "SELECT CURRENT_DATE as DataSistema";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("DataSistema");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public Time getDataHoraStistema() {
        sql = "SELECT TIME(NOW()) as DataSistema";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getTime("DataSistema");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public Date getDataUltimaFactura() {

        sql = "SELECT dataFactura as dataFactura FROM factura order by idFactura desc limit 1";
        //   sql = "SELECT DATE(dataFactura) as dataFactura FROM factura order by idFactura desc limit 1";
        // sql = "SELECT dataFactura FROM factura order by idFactura desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("dataFactura");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public String getHoraUltimaFactura() {

        sql = "SELECT DATE_FORMAT(dataFactura,'%H:%i') as dataFactura FROM factura WHERE DATE(dataFactura)=current_date order by idFactura desc limit 1";
        System.out.println("Sql:" + sql);
        //sql = "SELECT dataFactura FROM factura order by idFactura desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("dataFactura");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return "";
    }

    public Date getDataHoraUltimaFactura() {

        sql = "SELECT dataFactura FROM factura order by idFactura desc limit 1";
        // sql = "SELECT dataFactura FROM factura order by idFactura desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("dataFactura");
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

}
