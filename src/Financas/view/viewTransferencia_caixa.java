/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Centrocusto;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Movimentacoes;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import Financas.Modelo.Movimentacoes;

/**
 *
 * @author El Router
 */
public class viewTransferencia_caixa extends javax.swing.JInternalFrame {

    private ctr_Caixa controller;
    private ctr_Contas controllerContas;
    private ctr_Centrocusto controllerCentro;
    private ctr_Movimentacoes controllerMovimento;
    Movimentacoes modelo;
    private Connection conn;
    private DecimalFormat decimalformat;
    public String v1, v2;
    Date dataAtual = new Date();
    Data d = new Data();
    JDesktopPane desktop;

    public viewTransferencia_caixa(Connection conn, JDesktopPane desktop) {
        initComponents();
        desktop.add(this);
        jDateChooserDataLancamento.setDate(new Date());
        this.conn = conn;
        controller = new ctr_Caixa(conn);
        controllerContas = new ctr_Contas(conn);
        controllerCentro = new ctr_Centrocusto(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);
        modelo = new Movimentacoes();
        decimalformat = new DecimalFormat("#,##0.00");
       // this.control = control;
        jComboBoxCaixaOrigem.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixaDestino.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixaDestino.insertItemAt("", 0);
        jComboBoxCaixaDestino.setSelectedIndex(0);
        jComboBoxCaixaOrigem.insertItemAt("", 0);
        jComboBoxCaixaOrigem.setSelectedIndex(0);

        jTextFieldValorOrigem.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldValorOrigem.getText().isEmpty()) {
                        if (!jTextFieldValorOrigem.getText().equals("0,00")) {
//                            if (jComboBoxCaixaOrigem.getSelectedItem().toString().equals(jComboBoxCaixaDestino.getSelectedItem().toString())) {
//                                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
//                            } else {
                            double v = Double.parseDouble(jTextFieldValorOrigem.getText());
                            jTextFieldValorOrigem.setText(decimalformat.format(v));
                            jTextFieldValorDestino.setText(decimalformat.format(v));
//                            }
                        } else {

                        }
                    }
                }
            }

        });

    }

    public int getTipoMovimentoOrigem() {//Saindo
        return 2;
    }

    public int getTipoMovimentoDestino() {
        return 1;
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

    public double getValor() {
        double valor = 0;
        v1 = jTextFieldValorOrigem.getText().replaceAll(",00", "");
        v2 = removerPonto(v1);
        System.out.println("v2 " + v2);
        valor = Double.parseDouble(v2);

        return valor;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooserDataLancamento = new com.toedter.calendar.JDateChooser();
        jTextFieldDocumento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCaixaOrigem = new javax.swing.JComboBox<>();
        jTextFieldValorOrigem = new javax.swing.JTextField();
        jTextFieldDescricaoOrigem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldValorDestino = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDescricaodestino = new javax.swing.JTextField();
        jComboBoxCaixaDestino = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Transferência entre Caixa");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Cash on Delivery_32px.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Data do Lançamento");

        jLabel3.setText("Nº Documento");

        jTextFieldDocumento.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa de Origem"));

        jLabel5.setText("Valor de Saida");

        jLabel6.setText("Descrição da Saida");

        jLabel1.setText("Caixa Selecionado");

        jComboBoxCaixaOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldValorOrigem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValorOrigem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldValorOrigem.setText("0,00");
        jTextFieldValorOrigem.setToolTipText("Limpe o campo para inserir o valor de ENTRADA");
        jTextFieldValorOrigem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldValorOrigemMouseClicked(evt);
            }
        });
        jTextFieldValorOrigem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorOrigemKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(23, 23, 23)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldValorOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(213, 213, 213))
                            .addComponent(jComboBoxCaixaOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDescricaoOrigem)))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCaixaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldValorOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldDescricaoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa Destino"));

        jLabel7.setText("Caixa Selecionado");

        jLabel8.setText("Valor de Entrada");

        jTextFieldValorDestino.setEditable(false);
        jTextFieldValorDestino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValorDestino.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldValorDestino.setText("0,00");
        jTextFieldValorDestino.setToolTipText("Limpe o campo para inserir o valor de SAIDA");
        jTextFieldValorDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldValorDestinoMouseClicked(evt);
            }
        });
        jTextFieldValorDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorDestinoKeyTyped(evt);
            }
        });

        jLabel9.setText("Descrição de Entrada");

        jComboBoxCaixaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCaixaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCaixaDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDescricaodestino)
                            .addComponent(jTextFieldValorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBoxCaixaDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxCaixaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldValorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldDescricaodestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Checkmark_32px.png"))); // NOI18N
        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Forward_32px.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        setBounds(250, 70, 509, 450);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldValorOrigemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldValorOrigemMouseClicked

    }//GEN-LAST:event_jTextFieldValorOrigemMouseClicked

    private void jTextFieldValorOrigemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorOrigemKeyTyped

    }//GEN-LAST:event_jTextFieldValorOrigemKeyTyped

    private void jTextFieldValorDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldValorDestinoMouseClicked

    }//GEN-LAST:event_jTextFieldValorDestinoMouseClicked

    private void jTextFieldValorDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorDestinoKeyTyped

    }//GEN-LAST:event_jTextFieldValorDestinoKeyTyped

    private void jComboBoxCaixaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCaixaDestinoActionPerformed
        if (!jTextFieldValorOrigem.getText().equals("0,00")) {
            if (jComboBoxCaixaOrigem.getSelectedItem().toString().equals(jComboBoxCaixaDestino.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
            } else {
                double v = Double.parseDouble(jTextFieldValorOrigem.getText());
                jTextFieldValorOrigem.setText(decimalformat.format(v));
                jTextFieldValorDestino.setText(decimalformat.format(v));
            }
        }
    }//GEN-LAST:event_jComboBoxCaixaDestinoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jComboBoxCaixaOrigem.getSelectedItem().toString().equals(jComboBoxCaixaDestino.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
        } else {
            salvarOrigem();
            salvarDestino();
            habilitarLancamento();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void habilitarLancamento() {
        jTextFieldValorOrigem.setText("");
        jTextFieldValorDestino.setText("");
        jTextFieldDocumento.setText("");
        jTextFieldDescricaoOrigem.setText("");
        jTextFieldValorOrigem.setEditable(true);
        jTextFieldValorDestino.setEditable(true);
    }

    public int getCodCaixaOrigem() {
        return controller.getCodByNome(jComboBoxCaixaOrigem.getSelectedItem().toString());
    }

    public int getCodCaixaDestino() {
        return controller.getCodByNome(jComboBoxCaixaDestino.getSelectedItem().toString());
    }

    public double getSaldoAnteriorOrigem() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixaOrigem());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
    }

    public double getSaldoAnteriorDestino() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixaDestino());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
    }

    public void salvarOrigem() {
        modelo.setConta(13);
        modelo.setCaixa(controller.getCodByNome(jComboBoxCaixaOrigem.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao("TRANSFERENCIA - " + jTextFieldDescricaodestino.getText());
        modelo.setDestinatario("ORIGEM " + jComboBoxCaixaOrigem.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimentoOrigem());
        modelo.setTitulo("TRANF" + jTextFieldDocumento.getText());
        modelo.setSaldo(getSaldoAnteriorOrigem() - getValor());
        modelo.setSaldo_anterior(getSaldoAnteriorOrigem());
        modelo.setValor(getValor());
        controller.updateSaldo(2, getValor(), controller.getCodByNome(jComboBoxCaixaOrigem.getSelectedItem().toString()));

        if (jTextFieldValorOrigem.getText().equals("0,00")) {
            if (!jTextFieldDocumento.getText().isEmpty()) {
                if (jComboBoxCaixaOrigem.getSelectedItem().toString().equals(jComboBoxCaixaDestino.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
                } else {
                    if (controllerMovimento.save(modelo)) {
//                        habilitarLancamento();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Os valores do lançamento devem ser informados <br>por favor informar os valores para conclusão do Lançamento!!! </br></html> ", "Aviso do Sistema", 1);
            }
        } else {
            int op = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left> Não foi informado Nº DOC.<br>Deseja Continuar? </br></html> ", "Atenção", 1);
            if (op == 0) {
                if (controllerMovimento.save(modelo)) {
//                    habilitarLancamento();
                }
            } else {

            }
        }
    }

    public void salvarDestino() {
        modelo.setConta(13);
        modelo.setCaixa(controller.getCodByNome(jComboBoxCaixaDestino.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao("RECEBIMENTO " + jTextFieldDescricaodestino.getText() + "Valor tranferido do CAIXA:" + jComboBoxCaixaOrigem.getSelectedItem().toString());
        modelo.setDestinatario("ORIGEM " + jComboBoxCaixaDestino.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimentoDestino());
        modelo.setValor(getValor());
        modelo.setTitulo("TRANF" + jTextFieldDocumento.getText());
        modelo.setSaldo(getSaldoAnteriorOrigem() + getValor());
        modelo.setSaldo_anterior(getSaldoAnteriorOrigem());
        controller.updateSaldo(1, getValor(), controller.getCodByNome(jComboBoxCaixaDestino.getSelectedItem().toString()));

        if (jTextFieldValorOrigem.getText().equals("0,00")) {
            if (!jTextFieldDocumento.getText().isEmpty()) {
                if (jComboBoxCaixaOrigem.getSelectedItem().toString().equals(jComboBoxCaixaDestino.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
                } else {
                    if (controllerMovimento.save(modelo)) {

                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Os valores do lançamento devem ser informados <br>por favor informar os valores para conclusão do Lançamento!!! </br></html> ", "Aviso do Sistema", 1);
            }
        } else {
            int op = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left> Não foi informado Nº DOC.<br>Deseja Continuar? </br></html> ", "Atenção", 1);
            if (op == 0) {
                if (controllerMovimento.save(modelo)) {
//                    habilitarLancamento();
                }
            } else {

            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCaixaDestino;
    private javax.swing.JComboBox<String> jComboBoxCaixaOrigem;
    private com.toedter.calendar.JDateChooser jDateChooserDataLancamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldDescricaoOrigem;
    private javax.swing.JTextField jTextFieldDescricaodestino;
    private javax.swing.JTextField jTextFieldDocumento;
    private javax.swing.JTextField jTextFieldValorDestino;
    private javax.swing.JTextField jTextFieldValorOrigem;
    // End of variables declaration//GEN-END:variables
}