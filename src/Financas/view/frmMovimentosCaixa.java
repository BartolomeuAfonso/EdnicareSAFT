/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.RelatoriosMovimentoCaixa;
import Tabela.Tabla;
import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Movimentacoes;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static Financas.view.frmLancamentoMovimento.jComboBoxContas;
import static Financas.view.frmLancamentoMovimento.jTextAreaDescricao;
import static Financas.view.frmLancamentoMovimento.jTextFieldCentrocusto;
import static Financas.view.frmLancamentoMovimento.jTextFieldConta;
import static Financas.view.frmLancamentoMovimento.jTextFieldDescricaoconta;
import static Financas.view.frmLancamentoMovimento.jTextFieldDocumento;
import static Financas.view.frmLancamentoMovimento.jTextFieldEntrada;
import static Financas.view.frmLancamentoMovimento.jTextFieldSaida;
import static Financas.view.frmLancamentoMovimento.lbCod;
import static Financas.view.viewRelMovimentoCaixa.jDateChooserDataFinal;
import static Financas.view.viewRelMovimentoCaixa.jDateChooserDataInicial;

/**
 *
 * @author El Router
 */
public class frmMovimentosCaixa extends javax.swing.JFrame {

    /**
     * Creates new form frmCadastroCaixa
     */
    private frmMovimentoCaixa controladora;
    private int flag;
    private Connection conexao;
    private ctr_Caixa controller;
    private ctr_Contas contas;
    private ctr_Movimentacoes controllerMovimento;
    private DecimalFormat decimalformat;
    private Tabla t;
    public String v1, v2, va4, aux1, aux2, caixa, conta;
    private double valor = 0;
    private RelatoriosMovimentoCaixa rel;
    private Data util;

    public frmMovimentosCaixa() {
    }

    public frmMovimentosCaixa(Connection conexao, frmMovimentoCaixa control, String caixa, Date data, Date data2, String conta) {
        this();
        initComponents();
        this.conexao = conexao;
        controller = new ctr_Caixa(conexao);
        contas = new ctr_Contas(conexao);
        this.controladora = control;
        this.caixa = caixa;
        this.conta = conta;
        rel = new RelatoriosMovimentoCaixa(conexao);
        controllerMovimento = new ctr_Movimentacoes(conexao);
        util = new Data();
        jDateChooser1.setDate(data);
        jDateChooser2.setDate(data2);
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao2(caixa).toArray()));
        jComboBoxCaixa.insertItemAt(caixa, 0);
        jComboBoxCaixa.setSelectedIndex(0);
        jLabelStatus.setText(controller.getDescricaoSituacao(controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString())));
        decimalformat = new DecimalFormat("#,##0.00");
        t = new Tabla(conexao);
        jButtonRecibo.setEnabled(false);
        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
        ativarRecibo();
        setarTotais(conta);
        selectTable();
        iconeSistema();

        jDateChooser1.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                setarTotais("TODAS");
                ativarRecibo();
            }
        });
        jDateChooser2.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                setarTotais("TODAS");
                ativarRecibo();
            }
        });

    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public frmMovimentosCaixa(Connection conexao, String caixa, Date data, Date data2, String conta) {
        this();
        initComponents();
        this.conexao = conexao;
        controller = new ctr_Caixa(conexao);
        contas = new ctr_Contas(conexao);
        this.caixa = caixa;
        this.conta = conta;
        util = new Data();
        rel = new RelatoriosMovimentoCaixa(conexao);
        controllerMovimento = new ctr_Movimentacoes(conexao);
        jDateChooser1.setDate(data);
        jDateChooser2.setDate(data2);
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao2(caixa).toArray()));
        jComboBoxCaixa.insertItemAt(caixa, 0);
        jComboBoxCaixa.setSelectedIndex(0);
        jLabelStatus.setText(controller.getDescricaoSituacao(controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString())));
        decimalformat = new DecimalFormat("#,##0.00");
        t = new Tabla(conexao);
        jButtonRecibo.setEnabled(false);
        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
        ativarRecibo();
        setarTotais(conta);
        selectTable();
        iconeSistema();

        jDateChooser1.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                setarTotais("TODAS");
                ativarRecibo();
            }
        });
        jDateChooser2.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                setarTotais("TODAS");
                ativarRecibo();
            }
        });

    }

//    public void totalTabela(){
//        for (int i = 0; i < jTableLancamentos.getRowCount(); i++) {
//            va4 = "" + jTableRecibos.getValueAt(i, 4);
//            aux1 = va4.replaceAll(",00", "");
//            aux2 = removerPonto(aux1);
//            valor += Double.parseDouble(aux2);
//        }
//        jTextFieldValor.setText(df.format(valor));
//    }
    public void selectTable() {
        if (jTableLancamentos.getRowCount() != 0) {
            jTableLancamentos.setRowSelectionInterval(0, 0);
        }
    }

    public double getTotalEntradas() {
        return controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
    }

    public double getTotalSaidas() {
        return controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
    }

    public String tipoSaldo() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        if (controllerMovimento.getLastSaldoByCaixa(lastMovimento) > 0) {
            return "POSITIVO";
        } else {
            return "NEGATIVO";
        }
    }

    public int getCodCaixa() {
        return controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString());
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoAnteriorByCaixa(lastMovimento);

        return saldo;
    }

    public int verificar() {
        int row = jTableLancamentos.getSelectedRow();
        if (row != -1) {
            if (!jTableLancamentos.getValueAt(row, 5).equals("")) {
                return 1;// entrada
            } else {
                return 2;//saida
            }
        }
        return 2;

    }

    public void setarTotais(String conta) {
        jTextFieldSaldoAnterior.setForeground(Color.BLUE);
        jTextFieldSaldoAnterior.setText(decimalformat.format(getSaldoAnterior()));
        if (conta.equalsIgnoreCase("Todas")) {
            jTextField2.setText(decimalformat.format(controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))));
            jTextField3.setText(decimalformat.format(controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))));

            if ((controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))
                    - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))) < 0) {
                jTextField4.setForeground(Color.red);
//                jTextFieldSaldoAnterior.setForeground(Color.red);
                jTextField4.setText(decimalformat.format(
                        controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))
                        - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))));
                jTextFieldSaldoAnterior.setText(decimalformat.format(getSaldoAnterior()));
            } else {
                jTextField4.setForeground(Color.BLUE);
                jTextField4.setText(decimalformat.format(
                        controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))
                        - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()))));
            }

        } else {
            if (controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta)) == 0 && controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta)) == 0) {
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setForeground(Color.BLUE);
                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
            } else {
                jTextField2.setText(decimalformat.format(controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))));
                jTextField3.setText(decimalformat.format(controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))));
                if ((controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))
                        - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))) < 0) {
                    jTextField4.setForeground(Color.red);
//                jTextFieldSaldoAnterior.setForeground(Color.red);
                    jTextField4.setText(decimalformat.format(
                            controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))
                            - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))));
//                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
                } else {
                    jTextField4.setForeground(Color.BLUE);
                    jTextField4.setText(decimalformat.format(
                            controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))
                            - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), contas.getCodByNome(conta))));
//                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
                }
            }

        }

    }

    public void setarCampos() {
        int row = jTableLancamentos.getSelectedRow();

        lbCod.setText("" + controllerMovimento.getCodByTitulo(jTableLancamentos.getValueAt(row, 2).toString()));
        jTextFieldDocumento.setText("" + jTableLancamentos.getValueAt(row, 2));
        if (!String.valueOf(jTableLancamentos.getValueAt(row, 5)).equals("")) {
            jTextFieldEntrada.setText("" + jTableLancamentos.getValueAt(row, 5));
        } else {
            jTextFieldEntrada.setText("0,00");
        }
        if (!String.valueOf(jTableLancamentos.getValueAt(row, 6)).equals("")) {
            jTextFieldSaida.setText("" + jTableLancamentos.getValueAt(row, 6));
        } else {
            jTextFieldSaida.setText("0,00");
        }
        jComboBoxContas.setModel(new DefaultComboBoxModel(controller.getDescricao2(String.valueOf(jTableLancamentos.getValueAt(row, 10))).toArray()));
        jComboBoxContas.insertItemAt(String.valueOf(jTableLancamentos.getValueAt(row, 10)), 0);
        jComboBoxContas.setSelectedIndex(0);
        jTextFieldDescricaoconta.setText("" + jTableLancamentos.getValueAt(row, 3));
        jTextFieldConta.setText("" + contas.getCodByNome(jTableLancamentos.getValueAt(row, 3).toString()));
        jTextFieldCentrocusto.setText("" + String.valueOf(jTableLancamentos.getValueAt(row, 10)));
//        jTextFieldCheque.setText("" + jTableLancamentos.getValueAt(row, 1));
        jTextAreaDescricao.setText("" + jTableLancamentos.getValueAt(row, 4));

    }

    public double getValor() {
        double valor = 0;
        int row = jTableLancamentos.getSelectedRow();
        String v = "" + jTableLancamentos.getValueAt(row, 5);
        String v3 = "" + jTableLancamentos.getValueAt(row, 6);
        String exp = "";
        if (v.isEmpty()) {
            v1 = v3.replaceAll(",00", "");
        } else {
            v1 = v.replaceAll(",00", "");
        }

        v2 = removerPonto(v1);
        valor = Double.parseDouble(v2);

        return valor;
    }

    public void updateSaldoCaixa() {
        int op = 0;
        int row = jTableLancamentos.getSelectedRow();
        String v = "" + jTableLancamentos.getValueAt(row, 5);
        if (v.isEmpty()) {
            op = 1;
        } else {
            op = 2;
        }
        controller.updateSaldo(op, getValor(), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCaixa = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLancamentos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonRecibo = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldSaldoAnterior = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Movimento de Caixa");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Data Inicial:");

        jDateChooser2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jDateChooser2MouseDragged(evt);
            }
        });
        jDateChooser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser2MouseClicked(evt);
            }
        });

        jLabel2.setText("Data Final:");

        jComboBoxCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCaixaActionPerformed(evt);
            }
        });

        jLabel3.setText("Selecione o Caixa:");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/saindo.png"))); // NOI18N
        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 35, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap())
        );

        jTableLancamentos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Data", "Tipo", "Descrição da Conta no Plano de Contas", "Descrição do Historico", "Descrição do Cliente", "Entrada", "Saida", "Saldo", "Descrição do Fornecedor", "Descrição do Centro de Custo", "Observação Geral"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLancamentos.getTableHeader().setResizingAllowed(false);
        jTableLancamentos.getTableHeader().setReorderingAllowed(false);
        jTableLancamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLancamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLancamentos);
        if (jTableLancamentos.getColumnModel().getColumnCount() > 0) {
            jTableLancamentos.getColumnModel().getColumn(0).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(1).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(2).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(3).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(4).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(5).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(6).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(7).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(8).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(9).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(10).setResizable(false);
            jTableLancamentos.getColumnModel().getColumn(11).setResizable(false);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/novo.png"))); // NOI18N
        jButton1.setText("Incluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/editar.png"))); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/cancelar.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/faturasemitidas.png"))); // NOI18N
        jButtonRecibo.setText("Recibo");
        jButtonRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReciboActionPerformed(evt);
            }
        });

        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Printer_32px.png"))); // NOI18N
        btnRelatorio.setText("Relatório");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        jLabel4.setText("Saldo Anterior");

        jLabel5.setText("Total Entradas");

        jLabel6.setText("Total Saidas");

        jLabel7.setText("Saldo Atual");

        jTextFieldSaldoAnterior.setEditable(false);
        jTextFieldSaldoAnterior.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldSaldoAnterior.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 102, 204));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 51, 51));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addComponent(jTextField4))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRecibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(300, 110, 1041, 607);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        controladora.setEnabled(true);
//        this.controladora.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

        frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 2, jComboBoxCaixa.getSelectedItem().toString(), verificar());
        int row = jTableLancamentos.getSelectedRow();
        if (row != -1) {
            this.setEnabled(false);
            icliente.setVisible(true);
            icliente.toFront();
            setarCampos();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Por favor, Selecione um Lançamento!");
        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setEnabled(false);
        frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 1, jComboBoxCaixa.getSelectedItem().toString(), verificar());
        icliente.setVisible(true);
        icliente.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed


    public void ativarRecibo() {
        if (jTableLancamentos.getRowCount() == 0) {
            jButtonRecibo.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonAlterar.setEnabled(false);
        } else {
            jButtonRecibo.setEnabled(true);
            jButtonExcluir.setEnabled(true);
            jButtonAlterar.setEnabled(true);
        }

    }

    public String removerPonto(String num) {
        String novoNumero = "";

        for (int i = 0; i < num.length(); ++i) {
            if (num.charAt(i) == ',') {
                break;
            } else if (num.charAt(i) != '.') {
                novoNumero += num.charAt(i);
            }
        }

        return novoNumero;
    }
    private void jButtonReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReciboActionPerformed
        int row = jTableLancamentos.getSelectedRow();
        String v = "" + jTableLancamentos.getValueAt(row, 5);
        String v3 = "" + jTableLancamentos.getValueAt(row, 6);
        String exp = "";
        if (v.isEmpty()) {
            v1 = v3.replaceAll(",00", "");
            exp = "Recebimento";
        } else {
            v1 = v.replaceAll(",00", "");
            exp = "Pagamento";
        }

        v2 = removerPonto(v1);
        double valor = Double.parseDouble(v2);
        String conta = "" + jTableLancamentos.getValueAt(row, 10);
        String caixa = jComboBoxCaixa.getSelectedItem().toString();
        String doc = "" + jTableLancamentos.getValueAt(row, 2);
        String descricao = "" + jTableLancamentos.getValueAt(row, 3);

        if (row != -1) {
//            if (jTable1.getRowCount() == 0) {
            new frmRecibos(this, conexao, 1, valor, conta, caixa, doc, descricao, exp).setVisible(true);
//            }  
        } else {
            if (jTableLancamentos.getRowCount() == 0) {
                new frmRecibos(this, conexao, 2, valor, conta, caixa, doc, descricao, exp).setVisible(true);
            } else {
                new frmRecibos(this, conexao, 2, valor, conta, caixa, doc, descricao, exp).setVisible(true);
            }

        }


    }//GEN-LAST:event_jButtonReciboActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        int row = jTableLancamentos.getSelectedRow();
        String titulo = "", conta = "";
        int opc, codMovimento = 0;
        if (row != -1) {
            if (jTableLancamentos.getValueAt(row, 1).equals("")) {
                opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Nº do Plano ou Titulo não informado.<br><br>Deseja continuar? </br></html> ", "Aviso do Sistema", 1);
                if (opc == 0) {
                    codMovimento = (int) jTableLancamentos.getValueAt(row, 0);
                    if (controllerMovimento.delete(codMovimento)) {
                        updateSaldoCaixa();
                        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                    }

                }
            } else {
                titulo = "" + jTableLancamentos.getValueAt(row, 2);
                conta = "" + jTableLancamentos.getValueAt(row, 3);
                opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Deseja realmente Excluir ?<br><br><br>" + titulo + " - " + conta + " </br></html> ", "Aviso do Sistema", 1);
                if (opc == 0) {
                    codMovimento = (int) jTableLancamentos.getValueAt(row, 0);
                    if (controllerMovimento.delete(codMovimento)) {
                        updateSaldoCaixa();
                        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(caixa), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), conta);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLancamentosMouseClicked
        if (evt.getClickCount() == 2) {
            frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 2, jComboBoxCaixa.getSelectedItem().toString(), verificar());
            int row = jTableLancamentos.getSelectedRow();
            if (row != -1) {
                this.setEnabled(false);
                icliente.setVisible(true);
                icliente.toFront();
                setarCampos();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Por favor, Selecione um Lançamento!");
            }
        }
    }//GEN-LAST:event_jTableLancamentosMouseClicked

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
//        System.out.println("1 " + util.converteDataSql(jDateChooser1.getDate()));
//        System.out.println("2 " + getCodCaixa());
//        System.out.println("3 " + getSaldoAnterior());
//        System.out.println("4 " + getTotalEntradas());
//        System.out.println("5 " + getTotalSaidas());
//        System.out.println("6 " + tipoSaldo());

        rel.relatorioMovimentoCaixa(util.converteDataSql(jDateChooser1.getDate()),
                util.converteDataSql(jDateChooser2.getDate()),
                getCodCaixa(), jComboBoxCaixa.getSelectedItem().toString(),
                getSaldoAnterior(), getTotalEntradas(), getTotalSaidas(), tipoSaldo());
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBoxCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCaixaActionPerformed
        t = new Tabla(conexao);
        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), "Todas");
        ativarRecibo();
        selectTable();
    }//GEN-LAST:event_jComboBoxCaixaActionPerformed

    private void jDateChooser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseClicked

    }//GEN-LAST:event_jDateChooser2MouseClicked

    private void jDateChooser2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMovimentosCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMovimentosCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMovimentosCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMovimentosCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMovimentosCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRelatorio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonRecibo;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLancamentos;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldSaldoAnterior;
    // End of variables declaration//GEN-END:variables
}
