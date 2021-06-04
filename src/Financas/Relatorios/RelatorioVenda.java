/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.Relatorios;

import Gestao.Restaurantes.Controller.EmpresasController;
import Gestao.Restaurantes.Controller.ParametrosController;
import java.sql.Connection;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author
 */
public class RelatorioVenda {

//    private BDConexao conexaoexao;
    private Connection conexao;
    private ParametrosController controller;
    private EmpresasController emp;

    public RelatorioVenda(Connection conexao) {
        this.conexao = conexao;
        controller = new ParametrosController(conexao);
        emp = new EmpresasController(conexao);
    }

    public void getFacturaReemprimir(int codigoFactura,int flag) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());

        for (int i = 0; i < emp.listar().size(); i++) {
            System.out.println("dddd " +emp.listar().get(i).getNif());
            hashMap.put("EMPRESA", emp.getNomeEmpresa());
            hashMap.put("NIF", emp.listar().get(i).getNif());
            hashMap.put("EMAIL", emp.listar().get(i).getEmail());
            hashMap.put("TELEFONE", emp.listar().get(i).getTelefones());
            hashMap.put("ENDERECO", emp.listar().get(i).getEndereco());
        }
        String relatorio = "";
        if (flag == 1) {
           relatorio = "Relatorios/ticketFactura.jasper"; 
        }
        if (flag == 2) {
            relatorio = "Relatorios/ticketFacturaDuplo.jasper";
        }
        if (flag == 3) {
            relatorio = "Relatorios/ticketFacturaMulticaixa.jasper";
        }
        
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
//                JasperPrintManager.printReport(jasperPrint, false);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Reemprimindo a Factura Nº " +codigoFactura);
                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
    public void getFactura(int codigoFactura) {
//        conexao = new Conexao().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());
        for (int i = 0; i < emp.listar().size(); i++) {
            System.out.println("dddd " +emp.listar().get(i).getNif());
            hashMap.put("EMPRESA", emp.getNomeEmpresa());
            hashMap.put("NIF", emp.listar().get(i).getNif());
            hashMap.put("EMAIL", emp.listar().get(i).getEmail());
            hashMap.put("TELEFONE", emp.listar().get(i).getTelefones());
            hashMap.put("ENDERECO", emp.listar().get(i).getEndereco());
        }
        

//        String relatorio = "Relatorios/ticketFacturaIVA.jasper";
        String relatorio = "Relatorios/ticketFactura.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, false);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
    public void getFacturaMulticaixa(int codigoFactura) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());
        for (int i = 0; i < emp.listar().size(); i++) {
            hashMap.put("EMPRESA", emp.getNomeEmpresa());
            hashMap.put("NIF", emp.listar().get(i).getNif());
            hashMap.put("EMAIL", emp.listar().get(i).getEmail());
            hashMap.put("TELEFONE", emp.listar().get(i).getTelefones());
            hashMap.put("ENDERECO", emp.listar().get(i).getEndereco());
        }

//        String relatorio = "Relatorios/ticketFacturaIVA.jasper";
        String relatorio = "Relatorios/ticketFacturaMulticaixa.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, false);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }
    public void getFacturaDuplo(int codigoFactura) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());
        for (int i = 0; i < emp.listar().size(); i++) {
            hashMap.put("EMPRESA", emp.getNomeEmpresa());
            hashMap.put("NIF", emp.listar().get(i).getNif());
            hashMap.put("EMAIL", emp.listar().get(i).getEmail());
            hashMap.put("TELEFONE", emp.listar().get(i).getTelefones());
            hashMap.put("ENDERECO", emp.listar().get(i).getEndereco());
        }

//        String relatorio = "Relatorios/ticketFacturaIVA.jasper";
        String relatorio = "Relatorios/ticketFacturaDuplo.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, false);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getContaMesa(int codMesa, Date data) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codMesa);
        hashMap.put("DATA", data);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/conta.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, true);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem histórico!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getProdutoMaisvendido(Date data1, Date data2, int ponto, String p) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        hashMap.put("PONTO_VENDA", ponto);
        hashMap.put("PONTO", p);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportProdutosMaisVendidos.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem histórico!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getHistoricoVendas(Date data1, Date data2, String ponto) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("Data1", data1);
        hashMap.put("Data2", data2);
        hashMap.put("PONTO_VENDA", ponto);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportHistorico.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem histórico!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getHistoricoLucros(Date data1, Date data2, String ponto) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("Data1", data1);
        hashMap.put("Data2", data2);
        hashMap.put("PONTO_VENDA", ponto);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportLucros.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem histórico!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getHistoricoLucros(Date data1, Date data2) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("Data1", data1);
        hashMap.put("Data2", data2);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportLucrosGerais.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Sem histórico!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getGraficoLucros(Date data1, Date data2, String ponto) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("Data1", data1);
        hashMap.put("Data2", data2);
        hashMap.put("PONTO_VENDA", ponto);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/reportGraficoLucros.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getGraficoLucros(Date data1, Date data2) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("Data1", data1);
        hashMap.put("Data2", data2);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/reportGraficoLucrosGerais.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }

    }

    public void getFactura2(int codigoFactura) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());
        String relatorio = "Relatorios/ticketFactura2.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, false);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }
//        try {
//            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
//
//            if (jasperPrint.getPages().size() >= 1) {
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
//        }

    }

    public void getFacturaAdmin(int codigoFactura) {
//        conexao = new Conexao().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/ticketFactura2.jasper";
//        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
//       
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            if (jasperPrint.getPages().size() >= 1) {
                JasperPrintManager.printReport(jasperPrint, false);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Factura");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
        }
//        try {
//            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
//
//            if (jasperPrint.getPages().size() >= 1) {
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle("Factura");
//                jasperViewer.setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL !..." + ex);
//        }

    }

    public void mostrarRecibo(int nRecibo) {
//        conexao = new Conexao().ConexaoBD();
//        ConfiguracoesController cc = new ConfiguracoesController(new BDConexao());
//        String formaImpressao = cc.getFormaImpressao();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", nRecibo);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/ticketFactura.jasper";
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            int nPaginas = jasperPrint.getPages().size();
//            int nFacturas = new Definicoes().getQuantidadeFacturas();

            if (nPaginas >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("GRest/Factura");
//
//                if (formaImpressao.equalsIgnoreCase("SIM. IMPRIMIR APOIS SALVAR")) {
//                    for (int i = 0; i < nFacturas; i++) {
                JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
//                    }
//                } else {
//                    jasperViewer.setVisible(true);
//                }

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Items Na Factura!...");
            }
        } catch (JRException jex) {
            jex.printStackTrace();
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR MOSTRAR A FACTURA!...");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR A FACTURA !...");
        }
    }

    public void mostrarRecibo2(int nRecibo) {
//        conexao = new Conexao().ConexaoBD();
//        ConfiguracoesController cc = new ConfiguracoesController(new BDConexao());
//        String formaImpressao = cc.getFormaImpressao();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", nRecibo);
        hashMap.put("Logo", controller.getLogotipo());

        String relatorio = "Relatorios/ticketFactura2.jasper";
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, conexao);
            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, conexao);

            int nPaginas = jasperPrint.getPages().size();
//            int nFacturas = new Definicoes().getQuantidadeFacturas();

            if (nPaginas >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("GRest/Factura");
//
//                if (formaImpressao.equalsIgnoreCase("SIM. IMPRIMIR APOIS SALVAR")) {
//                    for (int i = 0; i < nFacturas; i++) {
                JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
//                    }
//                } else {
//                    jasperViewer.setVisible(true);
//                }

            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Items Na Factura!...");
            }
        } catch (JRException jex) {
            jex.printStackTrace();
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR MOSTRAR A FACTURA!...");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR A FACTURA !...");
        }
    }

}
