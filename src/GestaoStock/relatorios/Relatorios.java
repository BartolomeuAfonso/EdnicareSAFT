/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.relatorios;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
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
public class Relatorios {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void reportEstoque(int estoque, int baixo, int pedidos, int produtos) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("TODOS", estoque);
        hashMap.put("BAIXO", baixo);
        hashMap.put("PEDIDOS", pedidos);
        hashMap.put("PRODUTOS", produtos);
        String relatorio = "Relatorios/reportEstoque.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR O ESTOQUE !..." + ex);
        }

    }

    public void reportMovimentacoes() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportMovimentacoes.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS MOVIMENTAÇÕES !..." + ex);
        }

    }

    public void reportProdutos() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportProdutos.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS PRODUTOS !..." + ex);
        }

    }

    public void reportEntradas() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportEntradas.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void reportSituacaoProduto(String d1, String d2) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", d1);
        hashMap.put("DATA2", d2);

        String relatorio = "Relatorios/reportSituacaoPorEntrada.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR A SITUAÇÃO DOS PRODUTOS !..." + ex);
        }

    }

    public void reportCalculosProduto(int cod, String nome, double vmd, double vnsp, String d1, String d2) {
        con = new ConexaoBancos().ConexaoBD();
        Map hashMap = new HashMap();
        hashMap.put("DATA1", d1);
        hashMap.put("DATA2", d2);
        hashMap.put("NOME", nome);
        hashMap.put("VMD", vmd);
        hashMap.put("VNSP", vnsp);
        hashMap.put("COD", cod);
        hashMap.put("DATA1", d1);
        hashMap.put("DATA2", d2);

        String relatorio = "Relatorios/reportCalculoProduto.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS CALCULOS !..." + ex);
        }

    }
    public void reportDetalheProduto(
            int estoque_atual, int estoque_minimo, int estoque_critico, 
            String lote,int praso_validade,String centro_custo,String unidade
            ,double vmd,double vnps,int items_vendido,String produto) {
        con = new ConexaoBancos().ConexaoBD();
        Map hashMap = new HashMap();
        hashMap.put("PRODUTO", produto);
        hashMap.put("ESTOQUE_ATUAL", estoque_atual);
        hashMap.put("ESTOQUE_MINIMO", estoque_minimo);
        hashMap.put("ESTOQUE_CRITICO", estoque_critico);
        hashMap.put("VMD", vmd);
        hashMap.put("VNSP", vnps);
        hashMap.put("LOTE", lote);
        hashMap.put("PRASO", praso_validade);
        hashMap.put("CENTRO", centro_custo);
        hashMap.put("UNIDADE", unidade);
        hashMap.put("ITEMS", items_vendido);

        String relatorio = "Relatorios/reportDetalhesProduto.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS CALCULOS !..." + ex);
        }

    }

    public void reportPedidos() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportPedido.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Pedidos!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS PEDIDOS !..." + ex);
        }

    }

    public void reportSaidas() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportSaidas.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS SAIDAS !..." + ex);
        }

    }

    public void reportUtilizadores() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportUtilizador.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS UTILIZADORES !..." + ex);
        }

    }

    public void reportFornecedor() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportFornecedores.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR FORNECEDORES !..." + ex);
        }

    }

    public void reportProdutosArmazenados() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportProdutosArmazenados.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR OS PRODUTOS ARMAZENADOS !..." + ex);
        }

    }

    public void reportInventario() {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        String relatorio = "Relatorios/reportInventario.jasper";
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
                JOptionPane.showMessageDialog(null, "Não existem dados neste Periodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR O INVENTÁRIO !..." + ex);
        }

    }

}
