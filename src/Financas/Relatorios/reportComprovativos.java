/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Relatorios;

import sf.ce.utilizacoes.ParametrosController;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author El Router
 */
public class reportComprovativos {
    
    private Connection conexao;
    private ParametrosController controller;

    public reportComprovativos(Connection conexao) {
        this.conexao = conexao;
        controller = new ParametrosController(conexao);
    }

    public void getComprovativo(String data,String hora,String caixa,String saldoAtual,String valor,String descricao,String operador) {

        HashMap hashMap = new HashMap();
        hashMap.put("DATA", data);
        hashMap.put("HORA", hora);
        hashMap.put("CAIXA", caixa);
        hashMap.put("SALDO_ATUAL", saldoAtual);
        hashMap.put("VALOR", valor);
        hashMap.put("DESCRICAO", descricao);
        hashMap.put("OPERADOR", operador);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reforcoCaixa.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "SEM COMPROVATIVO!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
    public void reportAberturaCaixa(int last) {

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", last);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportAberturaCaixa.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("COMPROVATIVO DE ABERTURA DE CAIXA");
                jasperViewer.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "SEM COMPROVATIVO!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
}
