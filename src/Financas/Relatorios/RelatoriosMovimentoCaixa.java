/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Relatorios;

import sf.ce.utilizacoes.ParametrosController;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author El Router
 */
public class RelatoriosMovimentoCaixa {

    private Connection conexao;
    private ParametrosController controller;

    public RelatoriosMovimentoCaixa(Connection conn) {
        this.conexao = conn;
//        controller = new ParametrosController(conexao);
    }

//    public void relatorioMovimentoCaixa(Date data1,Date data2,int codCaixa,String caixa,String conta) {
    public void relatorioMovimentoCaixa(Date data1, Date data2, int codCaixa, String caixa, double saldo, double entradas, double saidas, String tipoSaldo) {

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CAIXA", caixa);
        hashMap.put("SALDO", saldo);
        hashMap.put("ENTRADAS", entradas);
        hashMap.put("SAIDAS", saidas);
        hashMap.put("TIPO_SALDO", tipoSaldo);
        hashMap.put("Logo", "Relatorios/logo1.jpg");
//        hashMap.put("Logo", controller.getLogotipo());

//        hashMap.put("CONTA", conta);
        hashMap.put("COD_CAIXA", codCaixa);

        String relatorio = "Relatorios/reportMovimentoCaixa.jasper";
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
                jasperViewer.setTitle("Relatório do Movimento do Caixa");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "SEM COMPROVATIVO!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioMovimentoCentroCusto(Date data1, Date data2, int codCaixa, double entradas, double saidas, String centro) {
        String relatorio;
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("ENTRADAS", entradas);
        hashMap.put("SAIDAS", saidas);
        hashMap.put("CONTA", centro);
//        hashMap.put("Logo", controller.getLogotipo());

        if (codCaixa == 0) {
            relatorio = "Relatorios/reportMovimentoContaAndCentro.jasper";
        } else {
            hashMap.put("COD_CAIXA", codCaixa);
            relatorio = "Relatorios/reportMovimentoCaixaAndCentro.jasper";
        }

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório do Movimento do Caixa");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioDemonstracaoResultado(Date data1, Date data2, int codCaixa, double entradas, double saidas, String caixa, String centro, int flag) {
        String relatorio = "";
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("ENTRADAS", entradas);
        hashMap.put("SAIDAS", saidas);
        hashMap.put("CAIXA", caixa);
        hashMap.put("COD_CAIXA", codCaixa);
        hashMap.put("CENTRO_CUSTO", centro);
//        hashMap.put("Logo", controller.getLogotipo());

//        System.out.println("caixa " + codCaixa);
//        System.out.println("custo " + centro);
//        System.out.println("ENTRADAS " + entradas);
//        System.out.println("SAIDAS " + saidas);
        if (flag == 1) {//Todos centros e um caixa
            relatorio = "Relatorios/reportDemostracaoResultado.jasper";
        }
        if (flag == 2) {//Por Caixa e por Centro
            relatorio = "Relatorios/reportDemostracaoResultadoCentro.jasper";
        }
        if (flag == 3) {
            relatorio = "Relatorios/reportDemostracaoResultadoTodos.jasper";
        }
        if (flag == 4) {
            relatorio = "Relatorios/reportDemostracaoResultadoTodosDetalhado.jasper";
        }
//        if (codCaixa != 0 && centro.equals("TODOS C.CUSTO")) {//Todos
//            relatorio = "Relatorios/reportDemostracaoResultado.jasper";
//        } 
//        if(codCaixa != 0 && !centro.equals("TODOS C.CUSTO")){
//            hashMap.put("COD_CAIXA", codCaixa);
//            relatorio = "Relatorios/reportDemostracaoResultado.jasper";
//        }

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório Demonstrativo de Resultados");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
//            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioDemonstracaoResultado2(Date data1, Date data2, int codCaixa, double entradas, double saidas, String caixa, String centro) {
        String relatorio;
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("ENTRADAS", entradas);
        hashMap.put("SAIDAS", saidas);
        hashMap.put("CAIXA", caixa);
        hashMap.put("COD_CAIXA", codCaixa);
        hashMap.put("CENTRO_CUSTO", centro);
//        hashMap.put("Logo", controller.getLogotipo());

        relatorio = "Relatorios/reportDemostracaoResultadoCentro.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório Demonstrativo de Resultados");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioDemonstracaoResultadoCaixas(Date data1, Date data2, double entradas, double saidas) {
        String relatorio;
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("ENTRADAS", entradas);
        hashMap.put("SAIDAS", saidas);
//        hashMap.put("Logo", controller.getLogotipo());
//        hashMap.put("CAIXA", caixa);
//        hashMap.put("COD_CAIXA", codCaixa);
//        hashMap.put("CENTRO_CUSTO", centro);

//        if (codCaixa == 0) {//Todos
//            relatorio = "Relatorios/reportDemostracaoResultadoTodos.jasper";
//        } else {
//            hashMap.put("COD_CAIXA", codCaixa);
        relatorio = "Relatorios/reportDemostracaoResultadoCaixas.jasper";
//        }

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório Demonstrativo de Resultados");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioMovimentoConta(Date data1, Date data2, int codConta, String conta, String centro) {

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CONTA", conta);
        hashMap.put("COD_CONTA", codConta);
        hashMap.put("CENTRO", centro);
//        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportMovimentoConta.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório das Contas do Plano de Contas");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void relatorioMovimentoContaAndCaixa(Date data1, Date data2, int codConta, String conta, int codCaixa) {

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CONTA", conta);
        hashMap.put("COD_CONTA", codConta);
        hashMap.put("COD_CAIXA", codCaixa);
//        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportMovimentoContaAndCaixa.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório das Contas do Plano de Contas");
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
}
