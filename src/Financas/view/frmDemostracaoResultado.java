/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.RelatoriosMovimentoCaixa;
import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Centrocusto;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Movimentacoes;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author El Router
 */
public class frmDemostracaoResultado extends javax.swing.JFrame {

    /**
     * Creates new form frmLancamentoMovimento
     */
    private ctr_Caixa controller;

    private Connection conn;
    private int flag;
    JFrame control;
    frmMenu c_form;
    private RelatoriosMovimentoCaixa rel;
    Date data = new Date();
    Data util = new Data();
    private ctr_Movimentacoes controllerMovimento;
    private ctr_Centrocusto controllerCentros;

    public frmDemostracaoResultado() {
    }

    public frmDemostracaoResultado(JFrame control, Connection conn, int flag) {
        this();
        initComponents();
        jDateChooserDataInicial.setDate(new Date());
        jDateChooserDataFinal.setDate(new Date());
        this.conn = conn;
        this.flag = flag;
        controller = new ctr_Caixa(conn);
        this.control = control;
        rel = new RelatoriosMovimentoCaixa(conn);
        controllerCentros = new ctr_Centrocusto(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);

        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.insertItemAt("TODOS", 0);
        jComboBoxCaixa.setSelectedIndex(0);

        jComboBoxContas.setModel(new DefaultComboBoxModel(controllerCentros.getDescricao().toArray()));
        jComboBoxContas.insertItemAt("Pesquisar Centro", 0);
        jComboBoxContas.setSelectedIndex(0);
        iconeSistema();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

//    public frmDemostracaoResultado(frmMenu c_form, Connection conn, int flag) {
//        this();
//        initComponents();
//        jDateChooserDataInicial.setDate(new Date());
//        jDateChooserDataFinal.setDate(new Date());
//        this.conn = conn;
//        this.flag = flag;
//        controller = new ctr_Caixa(conn);
//        controllerCentros = new ctr_Centrocusto(conn);
//        controllerMovimento = new ctr_Movimentacoes(conn);
//        this.c_form = c_form;
//
//        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
//        jComboBoxCaixa.insertItemAt("TODOS", 0);
//        jComboBoxCaixa.setSelectedIndex(0);
//
//        jComboBoxContas.setModel(new DefaultComboBoxModel(controllerCentros.getDescricao().toArray()));
//        jComboBoxContas.insertItemAt("Pesquisar Centro", 0);
//        jComboBoxContas.setSelectedIndex(0);
//        iconeSistema();
//    }

    public int getCodCaixa() {
        return controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString());
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
    }

    public double getTotalEntradas() {
        return controllerMovimento.getEntradasByCaixaAndCentro(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), jTextFieldDescricaoConta.getText());
    }

    public double getTotalSaidas() {
        return controllerMovimento.getSaidasByCaixaAndCentro(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), jTextFieldDescricaoConta.getText());
    }

    public String tipoSaldo() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        if (controllerMovimento.getLastSaldoByCaixa(lastMovimento) > 0) {
            return "POSITIVO";
        } else {
            return "NEGATIVO";
        }
    }

//    public String getConta(){
//        return controller.getAllCancelados();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCaixa = new javax.swing.JComboBox<>();
        jRadioButtonPorcaixa = new javax.swing.JRadioButton();
        jRadioButtonTodoscaixas = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDateChooserDataInicial = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jDateChooserDataFinal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldConta = new javax.swing.JTextField();
        jComboBoxContas = new javax.swing.JComboBox<>();
        jTextFieldDescricaoConta = new javax.swing.JTextField();
        jRadioButtonCentro = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emiss??o de Relat??rio do Plano de Contas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Caixa Selecionado");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 20));

        jComboBoxCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCaixa.setEnabled(false);
        jPanel2.add(jComboBoxCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 270, -1));

        buttonGroup1.add(jRadioButtonPorcaixa);
        jRadioButtonPorcaixa.setText("Pesquisar por Caixa");
        jRadioButtonPorcaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPorcaixaActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButtonPorcaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 20, 160, -1));

        buttonGroup1.add(jRadioButtonTodoscaixas);
        jRadioButtonTodoscaixas.setSelected(true);
        jRadioButtonTodoscaixas.setText("Pesquisar em  Todos Caixas");
        jRadioButtonTodoscaixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodoscaixasActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButtonTodoscaixas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 200, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 110));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Printer_32px.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 108, 46));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Forward_32px.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 108, 46));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Periodo"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jDateChooserDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 134, -1));

        jLabel10.setText("Informe a Data Final do Relat??rio");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 192, 19));
        jPanel3.add(jDateChooserDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 134, -1));

        jLabel2.setText("Informe a Data Inicial do Relat??rio");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 390, 90));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Plano de Contas"));

        jLabel3.setText("N?? da Conta");

        jLabel4.setText("C. Custo");

        jTextFieldConta.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldConta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldConta.setText("0");
        jTextFieldConta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldContaKeyTyped(evt);
            }
        });

        jComboBoxContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContasActionPerformed(evt);
            }
        });

        jTextFieldDescricaoConta.setEditable(false);

        jRadioButtonCentro.setText("Todos C.Custo");
        jRadioButtonCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCentroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonCentro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(12, 12, 12)
                            .addComponent(jTextFieldConta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel4)
                            .addGap(11, 11, 11)
                            .addComponent(jComboBoxContas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextFieldDescricaoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jRadioButtonCentro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addGap(16, 16, 16)
                .addComponent(jTextFieldDescricaoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 390, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(425, 455));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (flag == 1) {//form principal
            control.setEnabled(true);
            control.toFront();
        } else {
            c_form.setEnabled(true);
            c_form.toFront();
        }

    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        if (jRadioButtonPorcaixa.isSelected() && jRadioButtonCentro.isSelected()) {
//            if (jComboBoxCaixa.getSelectedIndex() != 0 && !jTextFieldDescricaoConta.getText().equalsIgnoreCase("Pesquisar Centro")) {
//                System.out.println("sdfsdfsdf");
//                rel.relatorioDemonstracaoResultado(util.converteData(jDateChooserDataInicial.getDate()), util.converteData(jDateChooserDataFinal.getDate()), getCodCaixa(), getTotalEntradas(), getTotalSaidas(), jComboBoxCaixa.getSelectedItem().toString(), jTextFieldDescricaoConta.getText());
//            } else {
//                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Aten????o, N??o foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informa????o. </br></html> ", "Aviso do Sistema", 1);
//            }
//
//        }
//        if (jRadioButtonPorcaixa.isSelected() && !jRadioButtonCentro.isSelected()) {
//            if (jComboBoxCaixa.getSelectedIndex() != 0 && !jTextFieldDescricaoConta.getText().equalsIgnoreCase("Pesquisar Centro")) {
//                rel.relatorioDemonstracaoResultado2(util.converteData(jDateChooserDataInicial.getDate()), util.converteData(jDateChooserDataFinal.getDate()), getCodCaixa(), getTotalEntradas(), getTotalSaidas(), jComboBoxCaixa.getSelectedItem().toString(), jTextFieldDescricaoConta.getText());
//            } else {
//                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Aten????o, N??o foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informa????o. </br></html> ", "Aviso do Sistema", 1);
//            }
//        }
//        if (!jRadioButtonPorcaixa.isSelected() && !jRadioButtonCentro.isSelected()) {
////            if (jComboBoxCaixa.getSelectedIndex() != 0 && !jTextFieldDescricaoConta.getText().equalsIgnoreCase("Pesquisar Centro")) {
//                rel.relatorioDemonstracaoResultado(util.converteData(jDateChooserDataInicial.getDate()), util.converteData(jDateChooserDataFinal.getDate()), getCodCaixa(), getTotalEntradas(), getTotalSaidas(), jComboBoxCaixa.getSelectedItem().toString(), jTextFieldDescricaoConta.getText());
////            } else {
////                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Aten????o, N??o foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informa????o. </br></html> ", "Aviso do Sistema", 1);
////            }
//        }
//        if (jRadioButtonTodoscaixas.isSelected()) {
//            if (!jTextFieldDescricaoConta.getText().equalsIgnoreCase("Pesquisar Centro")) {
//                rel.relatorioDemonstracaoResultado(util.converteData(jDateChooserDataInicial.getDate()), util.converteData(jDateChooserDataFinal.getDate()), 0, getTotalEntradas(), getTotalSaidas(), "TODOS CAIXAS", jTextFieldDescricaoConta.getText());
//            } else {
//                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Aten????o, N??o foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informa????o. </br></html> ", "Aviso do Sistema", 1);
//            }
//
//        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonTodoscaixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodoscaixasActionPerformed
        jComboBoxCaixa.setEnabled(false);
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.insertItemAt("TODOS", 0);
        jComboBoxCaixa.setSelectedIndex(0);
    }//GEN-LAST:event_jRadioButtonTodoscaixasActionPerformed

    private void jTextFieldContaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContaKeyTyped
        if (!jTextFieldConta.getText().isEmpty()) {
            jTextFieldDescricaoConta.setText(controllerCentros.getDescricaoByCod(Integer.parseInt(jTextFieldConta.getText())));
            jComboBoxContas.setModel(new DefaultComboBoxModel(controllerCentros.getDescricaoByCodigo(Integer.parseInt(jTextFieldConta.getText())).toArray()));
        }
    }//GEN-LAST:event_jTextFieldContaKeyTyped

    private void jComboBoxContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContasActionPerformed
        jTextFieldConta.setText("" + controllerCentros.getCodByNome(jComboBoxContas.getSelectedItem().toString()));
        jTextFieldDescricaoConta.setText(jComboBoxContas.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxContasActionPerformed

    private void jRadioButtonPorcaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPorcaixaActionPerformed
        jComboBoxCaixa.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonPorcaixaActionPerformed

    private void jRadioButtonCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCentroActionPerformed
        if (jRadioButtonCentro.isSelected()) {
            jComboBoxContas.setEnabled(false);
            jTextFieldDescricaoConta.setText("TODOS C.CUSTO");
        } else {
            jComboBoxContas.setEnabled(true);
            jTextFieldConta.setText("" + controllerCentros.getCodByNome(jComboBoxContas.getSelectedItem().toString()));
            jTextFieldDescricaoConta.setText(jComboBoxContas.getSelectedItem().toString());
        }

    }//GEN-LAST:event_jRadioButtonCentroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmLancamentoMovimento().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    private javax.swing.JComboBox<String> jComboBoxContas;
    public static com.toedter.calendar.JDateChooser jDateChooserDataFinal;
    public static com.toedter.calendar.JDateChooser jDateChooserDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonCentro;
    private javax.swing.JRadioButton jRadioButtonPorcaixa;
    private javax.swing.JRadioButton jRadioButtonTodoscaixas;
    private javax.swing.JTextField jTextFieldConta;
    private javax.swing.JTextField jTextFieldDescricaoConta;
    // End of variables declaration//GEN-END:variables
}
