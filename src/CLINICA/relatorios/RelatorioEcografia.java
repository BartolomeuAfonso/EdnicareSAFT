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
public class RelatorioEcografia {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getEcografiaPelvica(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaPelvica.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaAbdominal(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaAbdominal.jasper";
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
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaObstetrica(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaObstetrica.jasper";
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
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaMama(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaMama.jasper";
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
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaMorfologica(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaMorfologica.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaTesticular(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaTesticular.jasper";
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
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEcografiaTiroide(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoEcografiaTiroide.jasper";
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
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }
}
