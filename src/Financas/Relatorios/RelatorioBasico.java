/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Relatorios;

import sf.ce.conexao.ConexaoBancos;
import sf.ce.conexao.BDConexao;
import sf.ce.utilizacoes.ParametrosController;
import java.sql.Connection;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author
 */
public class RelatorioBasico {

//    private BDConexao conexao;
    private Connection conexao;
    private ParametrosController controller;

    public RelatorioBasico(Connection con) {
        this.conexao = con;
        controller = new ParametrosController(conexao);
    }

    public void listaUtilizador() {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/listaUtilizadores.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Lista de Utilizador");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Utilizador!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void listaClientes() {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/listaClientes.jasper";
        hashMap.put("Logo", controller.getLogotipo());
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Lista de Utilizador");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Utilizador!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void listaPontoVenda() {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        String relatorio = "Relatorios/listaPontoVenda.jasper";
        hashMap.put("Logo", controller.getLogotipo());
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Ponto de Venda");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Utilizador!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void enviarPedidoCozinha(int codigo, String produto, int qtd) {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO_PEDIDO", codigo);
        hashMap.put("PRODUTO", qtd);
        hashMap.put("qtd", codigo);
        String relatorio = "Relatorios/pedidoCozinha.jasper";
        hashMap.put("Logo", controller.getLogotipo());
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, true) ;
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Conta da mesa");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem pedido!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }
//    public void enviarPedidoCozinha(int codigo) {
////        con = new Conexao().ConexaoBD();
//
//        HashMap hashMap = new HashMap();
//        hashMap.put("CODIGO_PEDIDO", codigo);
//        String relatorio = "Relatorios/pedidoCozinha.jasper";
//        System.out.println("Relatorio:" + relatorio);
//        File file = new File(relatorio).getAbsoluteFile();
//        String obterCaminho = file.getAbsolutePath();
//        try {
//            JasperFillManager.fillReport(relatorio, hashMap, conexao);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
//            if (jasperPrint.getPages().size() >= 1) {
////                JasperPrintManager.printReport(jasperPrint, true) ;
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Conta da mesa");
//                jasperViewer.setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(null, "Nao Existem pedido!...");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
//        }
//
//    }

    public void FechodeCaixa(int codigo) {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigo);
        String relatorio = "Relatorios/FechoCaixa.jasper";
        hashMap.put("Logo", controller.getLogotipo());
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, true);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Ponto de Venda");
//                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Informações neste periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "SEM INFORMAÇÔES !..." + ex);
        }

    }

    public void FechodeCaixaPorData(int codigo, Date data) {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigo);
        hashMap.put("DATA", data);
        String relatorio = "Relatorios/FechoCaixaPorData.jasper";
        hashMap.put("Logo", controller.getLogotipo());
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Fecho de Caixa do Usuário");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem informações neste periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO !..." + ex);
        }

    }

    public void FechodeCaixaPorData2(int codigo, Date data, Date data2) {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigo);
        hashMap.put("DATA", data);
        hashMap.put("DATA2", data2);
//        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "";
        if (codigo == 0) {
            relatorio = "Relatorios/FechoCaixaPorDataTodos.jasper";
        } else {
            relatorio = "Relatorios/FechoCaixaPorData.jasper";
        }

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Fecho de Caixa");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem informação!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO !..." + ex);
        }

    }

    public void recibosEmitidos() {
//        con = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
//        hashMap.put("CODIGO", codigo);
//        hashMap.put("DATA", data);
//        hashMap.put("DATA2", data2);
        String relatorio = "";
        hashMap.put("Logo", controller.getLogotipo());

        relatorio = "Relatorios/recibosEmitidos.jasper";

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        try {
            JasperFillManager.fillReport(relatorio, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, hashMap, conexao);
            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Emissão de Recibos");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Recibos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Vazio !..." + ex);
        }

    }
}
