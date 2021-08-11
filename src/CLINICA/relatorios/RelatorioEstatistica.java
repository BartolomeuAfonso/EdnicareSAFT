/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.relatorios;

import java.sql.Connection;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Probook
 */
public class RelatorioEstatistica {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void IrelatorioHonorario(String data1, String data2, int codigoMedico, double preco, double irt, double imposto, double totalImposto) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGOUTILIZADOR", codigoMedico);
        hashMap.put("PRECO", preco);
        hashMap.put("IRT", irt);
        hashMap.put("IMPOSTO", imposto);
        hashMap.put("TOTALIMPOSTO", totalImposto);
        String relatorio = "Relatorios/honorarioMedico.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioHonorario1(String data1, String data2, int codigoMedico) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGOUTILIZADOR", codigoMedico);
        String relatorio = "Relatorios/honorarioMedico1.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioHonorario2(String data1, String data2, double irt) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("IRT", irt);
        String relatorio = "Relatorios/honorarioMedico2.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioHonorario3(String data1, String data2, int codigo) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGOUTILIZADOR", codigo);
        System.out.println("Data 1:" + data1);
        System.out.println("Data 2:" + data2);
        System.out.println("Código do Médico:" + codigo);
        String relatorio = "Relatorios/honorarioMedico3.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioJustificativo(int codigoPaciente, int codigoJustificativo) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        hashMap.put("CODIGO", codigoJustificativo);
        hashMap.put("CODIGOPACIENTE", codigoPaciente);

        String relatorio = "Relatorios/justificativo_faltas.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioJustificativo1(int codigoPaciente, int codigoJustificativo) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        hashMap.put("CODIGO", codigoJustificativo);
        hashMap.put("CODIGOPACIENTE", codigoPaciente);
        String relatorio = "Relatorios/justificativo_faltas1.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

    public void IrelatorioEstatistico(String data1, String data2, String area) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("AREA", area);
        String relatorio = "Relatorios/estatisticaRecepcao.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }
    
     public void Irelatoriolaboratorio(String data1, String data2, int area) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGOPRODUTO", area);
        String relatorio = "Relatorios/estatisticaLaboratorioPP.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Paciente Atendido neste périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }
}
