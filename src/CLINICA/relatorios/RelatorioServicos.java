/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.relatorios;

import java.sql.Connection;
import sf.ce.conexao.ConexaoBancos;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Familia do Fresco
 */
public class RelatorioServicos {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String obterCaminho;

    public void getTabelaPreco() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPreco.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoPorCategoria() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/listaPrecoCategoria.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoProduto() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/listaPrecoProduto.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getPrecoProdutoLucro() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/listaLucroProduto.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getMatadeStock() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/listaVendaLucroProduto.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Controlo de Stock");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoRaio() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPrecoRaioX.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoEcografia() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPrecoEcografia.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoExames() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPrecoExames.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getTabelaPrecoConsulta() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPrecoConsulta.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getEstatisticaRaio() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/tabelaPrecoRaioX.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getCategoria() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        //hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/listaCategoria.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Tabela de Preço");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getMarcacao(int codigo) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigo);
        System.out.println("Codigo:" + codigo);
        String relatorio = "relatorios/marcarConsulta.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Marcação");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getMarcacaoHoje() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        String relatorio = "relatorios/lista_consultas.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Consulta Marcada");
                jasperViewer.setVisible(true);
                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Consultas Marcadas!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getListaMarcadaporMedico(Date data, String medico) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA", data);
        hashMap.put("MEDICO", medico);
        //  System.out.println("Codigo:"+codig_o);
        String relatorio = "relatorios/lista_consultaporData.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Marcação");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getListaAgenda() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        String relatorio = "relatorios/AgendaMedica.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Agenda do Dr.");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getListaMarcadaporMedico1(Date data, Date dat2, String medico) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data);
        hashMap.put("DATA2", data);
        hashMap.put("MEDICO", medico);
        //  System.out.println("Codigo:"+codig_o);
        String relatorio = "relatorios/lista_consultaporData.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Agenda do Dr." + medico);
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Consulta para o Dr.:" + medico);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getListaAgendaporData(Date data) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA", data);
        String relatorio = "relatorios/lista_consultaporData1.jasper";
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Agenda do Dr.");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

}
