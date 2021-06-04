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
public class RelatorioVenda {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;

    public void getFactura(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/FacturaA5.jasper";
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
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFactura1(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/FacturaCotacao.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);

                // Para imprimir direito.
                //  jasperViewer.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaGuia(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getRecibo(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedicaRecibo.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

     public void getRecibo1(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedicaRecibo1.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }
    public void getFacturaGuiaSegundaVia(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedicaSegundaVia.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaGuia1(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica1.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaSegundaVia(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica1SegundaVia.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaGuia3(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica3.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaGuia2(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/guiaMedica2.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaCaucao(int codigoFactura, double valor) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("VALOR", valor);
        System.out.println("Valor:" + valor);

        String relatorio = "relatorios/FacturaCaucao.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaCaucaoTicket(int codigoFactura, double valor) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("VALOR", valor);
        System.out.println("Valor:" + valor);

        String relatorio = "relatorios/ticketFacturaCaucao.jasper";
        System.out.println("Relatorio:" + relatorio);

        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho:" + obterCaminho);
        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL, VERIFIQUE SE A IMPRESSORA ESTÁ REALMENTE CONECTADA !...");
        }
    }

    public void getFacturaCaucao1(int codigoFactura, double valor, String servico) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);
        hashMap.put("VALOR", valor);
        hashMap.put("SERVICO", servico);
        System.out.println("Valor:" + valor);

        String relatorio = "relatorios/FacturaPagamento.jasper";
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
//                jasperViewer.setAlwaysOnTop(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaDuplicado(int codigoFactura) {

        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/FacturaA5Duplicado.jasper";
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
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }
    }

    public void getFacturaticket(int codigoFactura) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/ticketFactura1.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho2:" + obterCaminho);

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL, VERIFIQUE SE A IMPRESSORA ESTÁ REALMENTE CONECTADA !...");
        }

    }

     public void getFacturaticketSegundaVia(int codigoFactura) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/ticketFactura1Segunda.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho2:" + obterCaminho);

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL, VERIFIQUE SE A IMPRESSORA ESTÁ REALMENTE CONECTADA !...");
        }

    }
    public void getFacturaticketseguradora(int codigoFactura) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/ticketFactura3.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho2:" + obterCaminho);

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL, VERIFIQUE SE A IMPRESSORA ESTÁ REALMENTE CONECTADA !...");
        }

    }

    public void getFacturaticketAnulado(int codigoFactura) {
        con = new ConexaoBancos().ConexaoBD();

        HashMap hashMap = new HashMap();
        hashMap.put("CODIGO", codigoFactura);

        String relatorio = "relatorios/ticketFactura2.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();
        System.out.println("Caminho2:" + obterCaminho);

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);
            int nPaginas = jasperPrint.getPages().size();
            if (jasperPrint.getPages().size() >= 1) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a factura?");
                if (opcao == JOptionPane.YES_OPTION) {
//                  JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                    //   JasperPrintManager.printReport(jasperPrint, true);
                    JasperPrintManager.printPages(jasperPrint, 0, nPaginas - 1, false);
                } else {
                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle("Factura");
                    jasperViewer.setVisible(true);
                }

//                }
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Factura!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "INFORMAÇÂO INDISPONIVEL, VERIFIQUE SE A IMPRESSORA ESTÁ REALMENTE CONECTADA !...");
        }

    }

}
