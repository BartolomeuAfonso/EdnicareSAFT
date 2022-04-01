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
public class RelatorioRaiox {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getRaioX(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/resultadoRaiox.jasper";
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

  
}
