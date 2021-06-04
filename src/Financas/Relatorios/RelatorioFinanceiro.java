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
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ramos Soft
 */
public class RelatorioFinanceiro {

    private BDConexao conexao;
    Connection con;
    private ParametrosController controller;

    public void lrelatorioDetalhado(Date data1, Date data2) {
        con = new ConexaoBancos().ConexaoBD();
        controller = new ParametrosController(con);

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportMovimentoTodosDetalhado.jasper";
        
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //    jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda neste Périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatorioSintetico(Date data1, Date data2) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportMovimentoTodosUtilizadorSintetico.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //   jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda neste Périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatorioDetalhadoporUtilizador(Date data1, Date data2, int utilizador) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_USUARIO", utilizador);
        hashMap.put("Logo", controller.getLogotipo());
//        System.out.println("Utilizador:" + utilizador);
        String relatorio = "Relatorios/reportMovimentoPorUtilizador.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //    jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda por este Utilizador!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatoriosSinteticoporUtilizador(Date data1, Date data2, int utilizador) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_USUARIO", utilizador);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportMovimentoPorUtilizadorSintetico.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda por este Utilizador!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatorioDetalhadoporFormaPagamento(Date data1, Date data2, int utilizador, int formaPagamento, String user, String forma) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_USUARIO", utilizador);
        hashMap.put("CODIGO_FORMA", formaPagamento);
        hashMap.put("FORMA", forma);
        hashMap.put("USER", user);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportMovimentoForma.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //      jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda por esta Forma!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatorioDetalhadoporFormaPagamentoTodosUser(Date data1, Date data2, int formaPagamento, String user, String forma) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
//        hashMap.put("CODIGO_USUARIO", utilizador);
        hashMap.put("CODIGO_FORMA", formaPagamento);
        hashMap.put("FORMA", forma);
        hashMap.put("USER", user);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportMovimentoFormaTodosUser.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //      jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda por esta Forma!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void lrelatoriosSinteticoporForma(Date data1, Date data2, int utilizador, int formaPagamento) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_USUARIO", utilizador);
        hashMap.put("FORMA", formaPagamento);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportMovimentoPorUtilizadorSinteticoForma.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                // jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda por esta Forma!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void recibo(int cod, String valor, String conta, String caixa, String doc, String descricao, String dizer, String r1, String r2, int control) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", cod);
        hashMap.put("VALOR", valor);
        hashMap.put("CAIXA", caixa);
        hashMap.put("REFERENCIA", doc);
        hashMap.put("DIZER", dizer);
        hashMap.put("H1", descricao);
        hashMap.put("H2", "");
        hashMap.put("H3", "");
        hashMap.put("R1", r1);
        hashMap.put("R2", r2);
//        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "";
        if (control == 1) {
            relatorio = "Relatorios/recibo.jasper";
        } else if (control == 2) {
            relatorio = "Relatorios/recibo2.jasper";
        }
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                // jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "O recibo atual não está salvo. Por favor salvar antes de visualizar!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO !..." + ex);
        }

    }
}
