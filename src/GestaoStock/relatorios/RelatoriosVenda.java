/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.relatorios;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sf.ce.conexao.BDConexao;

import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class RelatoriosVenda {

    private ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getFactura(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        String relatorio = "relatorios/ticketFactura.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void reportFechoCaixa(int user) {
        con = new ConexaoBancos().ConexaoBD();
        Map hashMap = new HashMap();
        hashMap.put("CODIGO", user);
        String relatorio = "relatorios/reportFechoCaixa.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "FATURA NÃO ENCONTRADA!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR A FATURA !...");
        }
    }

    public void reportMovimentoVendasSintetico(String data1, String data2) {
        con = new ConexaoBancos().ConexaoBD();
        Map hashMap = new HashMap();
        hashMap.put("DATA_INICIO", data1);
        hashMap.put("DATA_FIM", data2);
        String relatorio = "relatorios/reportMovimentoSintetico.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "FATURA NÃO ENCONTRADA!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR A FATURA !...");
        }
    }

    public void reportMovimentoVendasDetalhado(String data1, String data2) {
        con = new ConexaoBancos().ConexaoBD();
        Map hashMap = new HashMap();
        hashMap.put("DATA_INICIO", data1);
        hashMap.put("DATA_FIM", data2);
        String relatorio = "relatorios/reportMovimentoDetalhado.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "FATURA NÃO ENCONTRADA!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR A FATURA !...");
        }
    }

    public void reportVendaPorUtente(String utente, String data1, String data2) {
        Map hashMap = new HashMap();
        hashMap.put("DATA_INICIO", data1);
        hashMap.put("DATA_FIM", data2);
        hashMap.put("UTENTE", utente);
        String relatorio = "relatorios/reportVendasPorPaciente.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "FATURA NÃO ENCONTRADA!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR A FATURA !...");
        }
    }

    public void reportVendaPorUser(String user, String data1, String data2) {
        Map hashMap = new HashMap();
        hashMap.put("DATA_INICIO", data1);
        hashMap.put("DATA_FIM", data2);
        hashMap.put("UTENTE", user);
        String relatorio = "relatorios/reportMovimentoDetalhadopORuSER.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "FATURA NÃO ENCONTRADA!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO VISUALIZAR A FATURA !...");
        }
    }

}
