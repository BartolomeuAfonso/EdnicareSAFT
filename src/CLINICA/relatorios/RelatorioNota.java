/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.relatorios;

import java.sql.Connection;
import sf.ce.conexao.ConexaoBancos;
import java.io.File;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Familia do Fresco
 */
public class RelatorioNota {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getFacturaNotaA5(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/FacturaA5.jasper";
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
                //  jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaA4Nota(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        String relatorio = "relatorios/guiaMedica1Nota.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaticketNotaCredito(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        String relatorio = "relatorios/ticketFactura1Nota.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaA4Nota2Via(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        String relatorio = "relatorios/guiaMedica1Nota2via.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaA4NotaSeguradora(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica1.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaticketNota(int codigoFactura) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/ticketFactura1.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho2:" + obterCaminho);

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMA????O INDISPONIVEL, VERIFIQUE SE A IMPRESSORA EST?? REALMENTE CONECTADA !...");
        }
    }

}
