/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

/**
 *
 * @author Ba
 */
import static CLINICA.controller.ControllerFactura.getValorCaracterHash;
import CLINICA.modelo.Notacredito;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import json_xml_iva.RSA;
import sf.ce.conexao.ConexaoBancos;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthException;
import net.oauth.signature.OAuthSignatureMethod;
import sf.ce.utilizacoes.Data;
import sun.security.provider.SHA;

public class ControllerNotas {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    DecimalFormat df = new DecimalFormat("#,###.00");
    public Double valorTotalsemIVA = 0.0;
    public Double TotalDebito = 0.0;
    Data d = new Data();

    public ControllerNotas(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Notacredito factura, String reffactura) {
        java.sql.Date data_venda = new java.sql.Date(getDataTime().getTime());
        String hashcode_actual = "";
        String hashAnterior = getLasHash();
        int soma = 1;
        String nRef = null;
        String nRef1 = reffactura;
        df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
        String str = getValorCasaDecimal(df.format(factura.getValorApagar()), ".");
        String grosstotal = (str.replace(',', '.'));
        String datav = String.valueOf(getDataTime()).substring(0, 10);
        String data_hora = datav + "T" + getHora();

        try {
            soma = soma + getCountFactura();
            nRef = "NC " + getAnoemCurso() + "/" + soma;
            System.out.println("Data: " + datav);
            System.out.println("Hora:" + data_hora);
            System.out.println("Referencia:" + nRef);
            System.out.println("GrossTotal:" + grosstotal);
            System.out.println("Hash Anterior:" + hashAnterior);
            hashcode_actual = RSA.getHash(datav
                    + ";" + data_hora + ";"
                    + nRef + ";"
                    + grosstotal + ";" + hashAnterior);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(ControllerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "INSERT INTO notaCredito(dataFactura,nomeClientes,quantidadeItens,codigoUtilizador,codigoFormaPagamento,codigoCliente,valorApagar,hashValor,facturaReference,estado,descontoIVA,descontoFactura,subTotal,nRef,codigoFactura,motivo,refFactura,contador,dataFacturaElimina)values(now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, factura.getNomeCliente());
            ps.setInt(2, factura.getQuantidadeItens());
            ps.setInt(3, factura.getCodigoUtilizador());
            ps.setInt(4, factura.getCodigoFormaPagamento());
            ps.setInt(5, factura.getCodigoCliente());
            //  ps.setDouble(6, factura.getDesconto());
            ps.setDouble(6, factura.getValorApagar());
            ps.setString(7, hashcode_actual);
            ps.setString(8, getValorCaracterHash(hashcode_actual).toUpperCase());
            ps.setString(9, factura.getEstado());
            ps.setDouble(10, factura.getDescontoIVA());
            ps.setDouble(11, factura.getDescontoFactura());
            ps.setDouble(12, factura.getValorApagar() - (factura.getDescontoIVA() + factura.getDescontoFactura()));
            ps.setString(13, nRef);
            ps.setInt(14, factura.getIdFactura());
            ps.setString(15, factura.getnEcomenda());
            ps.setString(16, nRef1);
            ps.setInt(17, soma);
            ps.setString(18, factura.getDataFacturaEliminada());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Nota de Crédito realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

////        }
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

    public String getNentradasNotasCredito(String data, String data1) {
        String sql = "select count(idNota) as total from notaCredito where date(dataFactura) between  '" + data + "' and '" + data1 + "' and codigo_status <> 2";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return String.valueOf(rs.getInt("total"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "0";
    }

    public String getLasHash() {
        try {
            String sql = "select hashValor as hash from notacredito order by idNota desc limit 1";
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

    public int getCountFactura() {
        try {
            sql = "select contador as numerador from notacredito order by idnota desc limit 1";
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

    public Notacredito getNotaByCodigo(int codigo) {
        String sql = "select * from notaCredito where idNota=" + codigo;
        System.out.println("Consulta:" + sql);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Notacredito notas = new Notacredito();
                notas.setCodigoCliente(rs.getInt("codigoCliente"));
                notas.setCodigoSeguro(rs.getInt("codigoUtilizador"));
                notas.setNomeCliente(rs.getString("nomeClientes"));
                notas.setDataVencimento(rs.getString("dataFactura"));
                notas.setValorApagar(rs.getDouble("valorApagar"));
                notas.setDesconto(rs.getDouble("descontoFactura"));
                notas.setSubtotal(rs.getDouble("subtotal"));
                notas.setnRef(rs.getString("nRef"));
                notas.setHashValor(rs.getString("hashValor"));
                notas.setDescontoIVA(rs.getDouble("descontoIVa"));
                notas.setEstado(rs.getString("estado"));
                return notas;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String getValorCaracterHash(String str) {
        return "" + str.charAt(0) + str.charAt(10) + str.charAt(20) + str.charAt(30);
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
}
