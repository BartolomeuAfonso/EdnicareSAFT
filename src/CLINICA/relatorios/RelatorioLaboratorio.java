/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.relatorios;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.conexao.BDConexao;
import sf.ce.conexao.ConnectionFactory;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Familia do Fresco
 */
public class RelatorioLaboratorio {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getExames() {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        String relatorio = "relatorios/exames.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Dados da Triagem");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getHistorialClingico(int codigoClinico) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        System.out.println("Codigo:" + codigoClinico);
        hashMap.put("CODIGO", codigoClinico);

        String relatorio = "relatorios/reportHistoricoClinico.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Dados Clínicos");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExames(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_exames3.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExamesporParametro(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_examesParametro.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExamesA5(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_exames5.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExamesA4(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_exames4.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExamesA6(String data1, String data2, int codigoPaciente, int codigoExame, int codigoProduto) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        hashMap.put("PRODUTO", codigoProduto);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);
        System.out.println("Codigo do Produto:" + codigoProduto);
        String relatorio = "relatorios/resultado_exames6.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExamesA6semData(int codigoPaciente, int codigoExame, int codigoProduto) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        hashMap.put("PRODUTO", codigoProduto);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);
        System.out.println("Codigo do Produto:" + codigoProduto);
        String relatorio = "relatorios/resultado_exames6semDatata.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExames1(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_exames.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getResultadoExames1A4(String data1, String data2, int codigoPaciente, int codigoExame) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("CODIGO_PACIENTE", codigoPaciente);
        hashMap.put("CODIGO_EXAME", codigoExame);
        System.out.println("Data1:" + data1);
        System.out.println("Data2:" + data2);
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        System.out.println("Codigo do Exame:" + codigoExame);

        String relatorio = "relatorios/resultado_examesA4.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Resultado dos Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getReceita(int codigoReceita) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        System.out.println("Codigo:" + codigoReceita);
        hashMap.put("CODIGO", codigoReceita);

        String relatorio = "relatorios/reportReceita.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Receita Médica");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getReceitaSeguro(int codigoReceita) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        System.out.println("Codigo:" + codigoReceita);
        hashMap.put("CODIGO", codigoReceita);

        String relatorio = "relatorios/receitaSeguro.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Receita Médica");
                jasperViewer.setVisible(true);
            } else {
                //JOptionPane.showMessageDialog(null, "!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getPedidoExames(int codigoTriagem) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        System.out.println("CODIGO DA TRIAGEM:" + codigoTriagem);
        hashMap.put("CODIGO", codigoTriagem);

        String relatorio = "relatorios/PedidoExames.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Pedido de Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe pedido de exame nesta Data!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getPedidoExames1(int codigoTriagem) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        System.out.println("CODIGO DA TRIAGEM:" + codigoTriagem);
        hashMap.put("CODIGO", codigoTriagem);

        String relatorio = "relatorios/PedidoExames1.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Pedido de Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe pedido de exame nesta Data!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getExamesFeios(String data, String data1) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();

        hashMap.put("DATA1", data);
        hashMap.put("DATA2", data1);

        String relatorio = "relatorios/folhaMovimento.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Pedido de Exames");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe pedido de exame nesta Data!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

}
