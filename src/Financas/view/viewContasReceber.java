/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Tabela.Tabla;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Movimentacoes;
//import Financas.controller.ctr_Fornecedor;
import java.awt.Color;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
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

/**
 *
 * @author El Router
 */
public class viewContasReceber extends javax.swing.JInternalFrame {

    private int flag;
    private Connection conexao;
//    private ctr_Fornecedor controller;
    private ctr_Contas contas;
    private ctr_Movimentacoes controllerMovimento;
    private DecimalFormat decimalformat;
    private Tabla t;
    public String v1, v2, va4, aux1, aux2;
    private double valor = 0;
    private JDesktopPane desktop;

    public viewContasReceber(Connection conexao, JDesktopPane desktop) {
        initComponents();
        desktop.add(this);
        this.conexao = conexao;
       // controller = new ctr_Fornecedor(conexao);

        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
     //   jComboBoxFornecedor.setModel(new DefaultComboBoxModel(controller.getlistaFornecedores().toArray()));
        jComboBoxFornecedor.insertItemAt("TODOS ", 0);
        jComboBoxFornecedor.setSelectedIndex(0);
    }

    public void selectTable() {
        if (jTableLancamentos.getRowCount() != 0) {
            jTableLancamentos.setRowSelectionInterval(0, 0);
        }
    }

    public int verificar() {
        int row = jTableLancamentos.getSelectedRow();
        if (!jTableLancamentos.getValueAt(row, 4).equals("")) {
            return 1;// entrada
        } else {
            return 2;//saida
        }
    }

    public int getCodCaixa() {
//        return controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString());
        return 1;
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoAnteriorByCaixa(lastMovimento);

        return saldo;
    }

    public void setarTotais(String conta) {
        jTextFieldSaldoAnterior.setForeground(Color.BLUE);
        jTextFieldSaldoAnterior.setText(decimalformat.format(getSaldoAnterior()));
        if (conta.equalsIgnoreCase("Todas")) {
//            jTextField2.setText(decimalformat.format(controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))));
//            jTextField3.setText(decimalformat.format(controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))));

//            if ((controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))
//                    - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))) < 0) {
//                jTextField4.setForeground(Color.red);
////                jTextFieldSaldoAnterior.setForeground(Color.red);
//                jTextField4.setText(decimalformat.format(
//                        controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))
//                        - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))));
//                jTextFieldSaldoAnterior.setText(decimalformat.format(getSaldoAnterior()));
//            } else {
//                jTextField4.setForeground(Color.BLUE);
//                jTextField4.setText(decimalformat.format(
//                        controllerMovimento.getEntradasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))
//                        - controllerMovimento.getSaidasByCaixa(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()))));
//            }
        } else {
//            if (controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta)) == 0 && controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta)) == 0) {
//                jTextField2.setText("");
//                jTextField3.setText("");
//                jTextField4.setForeground(Color.BLUE);
//                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
//            } else {
//                jTextField2.setText(decimalformat.format(controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))));
//                jTextField3.setText(decimalformat.format(controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))));
//                if ((controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))
//                        - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))) < 0) {
//                    jTextField4.setForeground(Color.red);
////                jTextFieldSaldoAnterior.setForeground(Color.red);
//                    jTextField4.setText(decimalformat.format(
//                            controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))
//                            - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))));
////                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
//                } else {
//                    jTextField4.setForeground(Color.BLUE);
//                    jTextField4.setText(decimalformat.format(
//                            controllerMovimento.getEntradasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))
//                            - controllerMovimento.getSaidasByCaixaAndConta(Data.getData(jDateChooser1), Data.getData(jDateChooser2), controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), contas.getCodByNome(conta))));
////                jTextField4.setText(decimalformat.format(getSaldoAnterior()));
//                }
//            }

        }

    }

    public void setarCampos() {
        int row = jTableLancamentos.getSelectedRow();

        lbCod.setText("" + controllerMovimento.getCodByTitulo(jTableLancamentos.getValueAt(row, 1).toString()));
        jTextFieldDocumento.setText("" + jTableLancamentos.getValueAt(row, 1));
        if (!String.valueOf(jTableLancamentos.getValueAt(row, 4)).equals("")) {
            jTextFieldEntrada.setText("" + jTableLancamentos.getValueAt(row, 4));
        } else {
            jTextFieldEntrada.setText("0,00");
        }
        if (!String.valueOf(jTableLancamentos.getValueAt(row, 5)).equals("")) {
            jTextFieldSaida.setText("" + jTableLancamentos.getValueAt(row, 5));
        } else {
            jTextFieldSaida.setText("0,00");
        }
//        jComboBoxContas.setModel(new DefaultComboBoxModel(controller.getDescricao2(String.valueOf(jTableLancamentos.getValueAt(row, 9))).toArray()));
        jComboBoxContas.insertItemAt(String.valueOf(jTableLancamentos.getValueAt(row, 9)), 0);
        jComboBoxContas.setSelectedIndex(0);
        jTextFieldDescricaoconta.setText("" + jTableLancamentos.getValueAt(row, 2));
        jTextFieldConta.setText("" + contas.getCodByNome(jTableLancamentos.getValueAt(row, 2).toString()));
        jTextFieldCentrocusto.setText("" + String.valueOf(jTableLancamentos.getValueAt(row, 9)));
//        jTextFieldCheque.setText("" + jTableLancamentos.getValueAt(row, 1));
        jTextAreaDescricao.setText("" + jTableLancamentos.getValueAt(row, 4));

    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLancamentos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonRecibo = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldSaldoAnterior = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        setClosable(true);
        setTitle("CONTROLO DE CONTAS A RECEBER");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/icons8_US_Dollar_32px_1.png"))); // NOI18N

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

        jComboBoxFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFornecedorActionPerformed(evt);
            }
        });

        jLabel3.setText("Selecionar Nome:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Update_32px.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/saindo.png"))); // NOI18N
        jButton4.setText("Sair");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setText("Tipo Pesquisa");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GERAL", "ABERTOS", "PAGOS" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(390, 390, 390))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxFornecedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jTableLancamentos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Descrição do Fornecedor", "Vencimento", "Forma de Pagmt.", "Valor do Titulo", "Juros/Multa", "Vlr. Desconto", "Vlr. Pago", "Data Pagmt.", "Saldo atual", "Historico", "Descrição do Plano de Contas"
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
        jButtonExcluir.setText("Raltório");
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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/icons8_US_Dollar_32px.png"))); // NOI18N
        jButton7.setText("Receber");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel4.setText("Titulos Anteriores");

        jLabel5.setText("Total Titulos");

        jLabel6.setText("Titulos Pagos");

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
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 981, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldSaldoAnterior))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4)))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRecibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSaldoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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

        setBounds(30, 20, 1017, 598);
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2MouseDragged

    private void jDateChooser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseClicked

    }//GEN-LAST:event_jDateChooser2MouseClicked

    private void jComboBoxFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFornecedorActionPerformed
        t = new Tabla(conexao);
//        t.visualizarLancamentosCaixa(jTableLancamentos, controller.getCodByNome(jComboBoxFornecedor.getSelectedItem().toString()), 1, Data.getData(jDateChooser1), Data.getData(jDateChooser2), "Todas");
        ativarRecibo();
        selectTable();

    }//GEN-LAST:event_jComboBoxFornecedorActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setarTotais("TODAS");
        ativarRecibo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTableLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLancamentosMouseClicked
        if (evt.getClickCount() == 2) {
//            frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 2, jComboBoxCaixa.getSelectedItem().toString(), verificar());
//            int row = jTableLancamentos.getSelectedRow();
//            if (row != -1) {
//                this.setEnabled(false);
//                icliente.setVisible(true);
//                icliente.toFront();
//                setarCampos();
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "Por favor, Selecione um Lançamento!");
//            }
        }
    }//GEN-LAST:event_jTableLancamentosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        this.setEnabled(false);
//        frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 1, jComboBoxCaixa.getSelectedItem().toString(), verificar());
//        icliente.setVisible(true);
//        icliente.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

//        frmLancamentoMovimento icliente = new frmLancamentoMovimento(this, conexao, 2, jComboBoxCaixa.getSelectedItem().toString(), verificar());
//        int row = jTableLancamentos.getSelectedRow();
//        if (row != -1) {
//            this.setEnabled(false);
//            icliente.setVisible(true);
//            icliente.toFront();
//            setarCampos();
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Por favor, Selecione um Lançamento!");
//        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

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

//        v2 = removerPonto(v1);
        double valor = Double.parseDouble(v2);
        String conta = "" + jTableLancamentos.getValueAt(row, 2);
        String caixa = jComboBoxFornecedor.getSelectedItem().toString();
        String doc = "" + jTableLancamentos.getValueAt(row, 1);
        String descricao = "" + jTableLancamentos.getValueAt(row, 3);

        if (row != -1) {
            //            if (jTable1.getRowCount() == 0) {
//            new frmRecibos(this, conexao, 1, valor, conta, caixa, doc, descricao, exp).setVisible(true);
            //            }
        } else {
//            if (jTableLancamentos.getRowCount() == 0) {
//                new frmRecibos(this, conexao, 2, valor, conta, caixa, doc, descricao, exp).setVisible(true);
//            } else {
//                new frmRecibos(this, conexao, 2, valor, conta, caixa, doc, descricao, exp).setVisible(true);
//            }

        }

    }//GEN-LAST:event_jButtonReciboActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        int row = jTableLancamentos.getSelectedRow();
        String titulo = "", conta = "";
        int opc;
        if (row != -1) {
            if (jTableLancamentos.getValueAt(row, 1).equals("")) {
//                opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Nº do Plano ou Titulo não informado.<br><br>Deseja continuar? </br></html> ", "Aviso do Sistema", 1);
//                if (opc == 0) {
//
//                }
            } else {
                titulo = "" + jTableLancamentos.getValueAt(row, 1);
                conta = "" + jTableLancamentos.getValueAt(row, 2);
//                opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Deseja realmente Excluir ?<br><br><br>" + titulo + " - " + conta + " </br></html> ", "Aviso do Sistema", 1);
//                if (opc == 0) {
//
//                }
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int row = jTableLancamentos.getSelectedRow();
        String titulo = "", fornecedor = "";
        int opc;
        if (row == -1) {
//            titulo = "" + jTableLancamentos.getValueAt(row, 0);
//            fornecedor = "" + jTableLancamentos.getValueAt(row, 1);
            titulo = "0001" ;
            fornecedor = "DIVERSOS" ;
            opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Deseja realmente pagar ou actualizar o Titulo/Conta?<br><br><br>" + titulo + " - " + fornecedor + " </br></html> ", "Aviso do Sistema", 1);
            if (opc == 0) {
                new viewPagarConta().show();
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonRecibo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxFornecedor;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
