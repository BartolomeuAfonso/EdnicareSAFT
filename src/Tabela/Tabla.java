/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabela;

import Render.Render;
import sf.ce.conexao.ConexaoBancos;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Centrocusto;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Movimentacoes;
import Financas.controller.ctr_Recibo;
import Financas.controller.ctr_TipoCaixa;
import Financas.controller.ctr_TipoCentrocusto;
import java.awt.Color;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import Financas.Modelo.Caixa;
import Financas.Modelo.CentroCustos;
import Financas.Modelo.Contas;
import Financas.Modelo.Movimentacoes;
import Financas.Modelo.Recibos;

/**
 *
 * @author
 */
public class Tabla {

    ctr_Centrocusto centros;
    ctr_Contas contas;
    ctr_Caixa caixas;
    ctr_Movimentacoes movimentos;
    ctr_Recibo recibos;
    ctr_TipoCentrocusto tipo;
    ctr_TipoCaixa tipoCaixa;
    private Connection conexao;
    Connection con;

    public Tabla(Connection con) {
        this.con = con;
        conexao = new ConexaoBancos().ConexaoBD();
    }

    private boolean[] editable = {false, false, false, false, false, false, false, false, false, false};
    private String sql;

    public void visualizar(JTable tabla) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Descrição da Caixa", "Tipo Caixa", "Abrir e Fechar", "Saldo Atual", "Abertura", "Fecho", "Situação", "Editar", "Eliminar"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        tabla.setRowHeight(18);
        JButton btn_visualizar = new JButton("");
        btn_visualizar.setName("edit");
        btn_visualizar.setBackground(Color.GREEN);
        btn_visualizar.setForeground(Color.black);
        JButton btn_eliminar = new JButton("");
        btn_eliminar.setName("v");
        btn_eliminar.setBackground(Color.red);
        btn_eliminar.setForeground(Color.black);

        btn_visualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/edit.png"))); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/delete.png"))); // NOI18N

//        controllerCliente = new ClienteController(conexao);
//        Clientes cl = new Clientes();
//        
//        ArrayList<Clientes> list = controllerCliente.getAllClientes(query);
        ArrayList<String> list = new ArrayList();
        list.add("BIC");
        list.add("BAI");
        list.add("BFA");

        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
//                cl = list.get(i);

                fila[0] = list.get(i);
                fila[1] = list.get(i);
                fila[2] = list.get(i);
                fila[3] = list.get(i);
                fila[4] = list.get(i);
                fila[5] = list.get(i);
                fila[6] = list.get(i);
                fila[7] = list.get(i);
                fila[8] = btn_visualizar;
                fila[9] = btn_eliminar;
//                fila[4] = list.get(i);
//
//                int ac = cl.getIdStatus();
//                if (ac == 1) {
//                    fila[2] = true;
//                } else {
//                    fila[2] = false;
//                }

                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setResizable(false);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(4).setResizable(false);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(5).setResizable(false);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(6).setResizable(false);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setResizable(false);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(8).setResizable(false);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(9).setResizable(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarContas(JTable tabla, int order) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Descrição da Conta", "Tipo Conta", "Nº C.Custo", "Descrição do Centro de Custo", "Observações", "", ""}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        tabla.setRowHeight(18);
        JButton btn_visualizar = new JButton("");
        btn_visualizar.setName("edit");
        btn_visualizar.setBackground(Color.GREEN);
        btn_visualizar.setForeground(Color.black);
        JButton btn_eliminar = new JButton("");
        btn_eliminar.setName("v");
        btn_eliminar.setBackground(Color.red);
        btn_eliminar.setForeground(Color.black);

        btn_visualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/Edit16.png"))); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/delete16.png"))); // NOI18N

        contas = new ctr_Contas(conexao);
        centros = new ctr_Centrocusto(conexao);

        ArrayList<Contas> list = contas.getAll(order);
        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];

                fila[0] = list.get(i).getCodigo();
                fila[1] = list.get(i).getDescricao();
                fila[2] = centros.getTipoByDescricao(contas.getCentroByDescricao(list.get(i).getDescricao()));
                fila[3] = centros.getCodByNome(contas.getCentroByDescricao(list.get(i).getDescricao()));
                fila[4] = contas.getCentroByDescricao(list.get(i).getDescricao());
                fila[5] = list.get(i).getObs();
                fila[6] = btn_visualizar;
                fila[7] = btn_eliminar;
                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(1).setResizable(true);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setResizable(true);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(3).setResizable(true);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(4).setResizable(true);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setResizable(true);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setResizable(true);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(7).setResizable(true);
        tabla.getTableHeader().setReorderingAllowed(true);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarCaixas(JTable tabla, int order) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Descrição do Caixa", "Tipo do Caixa", "Número de Conta", "Agencia", "Gerente", "Abertura/Fecho", "Saldo Atual", "Abertura", "Fecho", "Situação Atual", "Saldo Inicial"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        tabla.setRowHeight(18);
        JButton btn_visualizar = new JButton("");
        btn_visualizar.setName("edit");
        btn_visualizar.setBackground(Color.GREEN);
        btn_visualizar.setForeground(Color.black);
        JButton btn_eliminar = new JButton("");
        btn_eliminar.setName("v");
        btn_eliminar.setBackground(Color.red);
        btn_eliminar.setForeground(Color.black);

        btn_visualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/edit.png"))); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/delete.png"))); // NOI18N

        caixas = new ctr_Caixa(conexao);
        tipoCaixa = new ctr_TipoCaixa(conexao);
        ArrayList<Caixa> list;
        if (order == 3) {
            list = caixas.getAllCancelados();
        } else {
            list = caixas.getAll(order);
        }

        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[15];

                fila[0] = list.get(i).getCodigo();
                fila[1] = list.get(i).getDescricao();
                fila[2] = caixas.getTipoByDescricao(list.get(i).getDescricao());
                fila[3] = list.get(i).getNumero_conta();
                fila[4] = list.get(i).getAgencia();
                fila[5] = list.get(i).getGerente();
                fila[6] = list.get(i).getOperacao();
                fila[7] = list.get(i).getSaldo_atual();
                fila[8] = list.get(i).getData_abertura();
                fila[9] = list.get(i).getData_fecho();
                fila[10] = list.get(i).getSitacao_atual();
                fila[11] = list.get(i).getSaldo_inicial();
//                fila[12] = btn_visualizar;
//                fila[13] = btn_eliminar;
                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(1).setResizable(true);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setResizable(true);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setResizable(true);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(4).setResizable(true);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(5).setResizable(true);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setResizable(true);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setResizable(true);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setResizable(false);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setResizable(true);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setResizable(true);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setResizable(true);
//        tabla.getColumnModel().getColumn(12).setPreferredWidth(50);
//        tabla.getColumnModel().getColumn(12).setResizable(true);
//        tabla.getColumnModel().getColumn(13).setPreferredWidth(50);
//        tabla.getColumnModel().getColumn(13).setResizable(true);
        tabla.getTableHeader().setReorderingAllowed(true);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarCentroCusto(JTable tabla, int order) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Descrição do Centro Custo", "Tipo", "Obs", "", ""}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        tabla.setRowHeight(18);
        JButton btn_visualizar = new JButton("");
        btn_visualizar.setName("edit");
        btn_visualizar.setBackground(Color.GREEN);
        btn_visualizar.setForeground(Color.black);
        JButton btn_eliminar = new JButton("");
        btn_eliminar.setName("v");
        btn_eliminar.setBackground(Color.red);
        btn_eliminar.setForeground(Color.black);

        btn_visualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/Edit16.png"))); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/delete16.png"))); // NOI18N

//        conexao = new Conexao().ConexaoBD();
        centros = new ctr_Centrocusto(conexao);
        tipo = new ctr_TipoCentrocusto(conexao);

        ArrayList<CentroCustos> list = centros.getAll(order);

        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];

                fila[0] = list.get(i).getCodigo();
                fila[1] = list.get(i).getDescricao();
                fila[2] = tipo.getDescricaoByCod(list.get(i).getTipo_centro());
                fila[3] = list.get(i).getObs();
                fila[4] = btn_visualizar;
                fila[5] = btn_eliminar;
                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(1).setResizable(true);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setResizable(true);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setResizable(true);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(4).setResizable(true);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setResizable(true);
        tabla.getTableHeader().setReorderingAllowed(true);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarLancamentosCaixa(JTable tabla, int caixa, int order, String d1, String d2, String conta) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Data", "Titulo", "Descrição da Conta do Plano de Contas", "Descrição do Movimento", "Entrada", "Saida", "Saldo", "Descrição do Cliente", "Descrição do Fornecedor", "Descrição do Centro de Custo"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
//                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
//                java.lang.Object.class, java.lang.Object.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        DecimalFormat decimalformat = new DecimalFormat("#,##0.00");
        tabla.setRowHeight(18);
        movimentos = new ctr_Movimentacoes(conexao);
        contas = new ctr_Contas(conexao);
        centros = new ctr_Centrocusto(conexao);
        ArrayList<Movimentacoes> list;
        if (!conta.equalsIgnoreCase("Todas")) {
            list = movimentos.getAllByDataAndCaixaAnsConta(d1, d2, caixa, conta);
        } else {
            list = movimentos.getAllByDataAndCaixa(d1, d2, caixa);
        }

//        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[15];
                fila[0] = list.get(i).getCodigo();
                fila[1] = list.get(i).getDate();
                fila[2] = list.get(i).getTitulo();
                fila[3] = contas.getDescricaoByCod(list.get(i).getConta());
                fila[4] = list.get(i).getDescricao();
                if (list.get(i).getTipo_movimento() == 1) {
                    fila[5] = decimalformat.format(list.get(i).getValor());
                    fila[6] = "";
                } else {
                    fila[5] = "";
                    fila[6] = decimalformat.format(list.get(i).getValor());
                }

                fila[7] = decimalformat.format(list.get(i).getSaldo());
                fila[8] = "";
                fila[9] = "";
                fila[10] = contas.getCentroByDescricao(contas.getDescricaoByCod(list.get(i).getConta()));
                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(1).setResizable(true);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setResizable(true);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(3).setResizable(true);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(4).setResizable(true);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(5).setResizable(true);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(6).setResizable(true);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(7).setResizable(true);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(8).setResizable(false);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(9).setResizable(false);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(10).setResizable(false);

        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarRecibos(JTable tabla, int flag, String data1, String data2) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Nº", "Titulo", "Data", "Hora", "Valor", "Descrição", "Editar", "Excluir", "Imprimir"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class

            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        DecimalFormat df = new DecimalFormat("#,##0.00");
        tabla.setRowHeight(18);

        JButton btn_editar = new JButton("");
        btn_editar.setName("edit");
//        btn_visualizar.setBackground(Color.GREEN);
        btn_editar.setForeground(Color.black);

        JButton btn_eliminar = new JButton("");
        btn_eliminar.setName("delete");
//        btn_eliminar.setBackground(Color.red);
        btn_eliminar.setForeground(Color.black);

        JButton btn_imprimir = new JButton("");
        btn_imprimir.setName("print");
//        btn_eliminar.setBackground(Color.red);
        btn_imprimir.setForeground(Color.black);

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/Edit16.png"))); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/delete16.png"))); // NOI18N
        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/print16.png"))); // NOI18N

        recibos = new ctr_Recibo(conexao);

        ArrayList<Recibos> list;
        if (flag == 1) {
            list = recibos.getRecibos();
        } else {
            list = recibos.getRecibosByData(data1, data2);
        }

        System.out.println("indice " + list.size());

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[15];
                fila[0] = list.get(i).getCodigo();
                fila[1] = list.get(i).getTitulo();
                fila[2] = list.get(i).getData();
                fila[3] = list.get(i).getHora();
                fila[4] = df.format(list.get(i).getValor());
                fila[5] = list.get(i).getHistorico1();
                fila[6] = btn_editar;
                fila[7] = btn_eliminar;
                fila[8] = btn_imprimir;
                dt.addRow(fila);

            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(1).setResizable(true);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setResizable(true);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(3).setResizable(true);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setResizable(true);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(245);
        tabla.getColumnModel().getColumn(5).setResizable(true);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setResizable(true);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(7).setResizable(true);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(8).setResizable(false);

        tabla.getTableHeader().setReorderingAllowed(true);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

}
