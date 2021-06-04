/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.RelatoriosMovimentoCaixa;
import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Movimentacoes;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

/**
 *
 * @author El Router
 */
public class frmRelCaixa extends javax.swing.JFrame {

    /**
     * Creates new form frmLancamentoMovimento
     */
    private ctr_Caixa controller;

    private Connection conn;
    private int flag;
    JFrame control;
    JFrame c_form;
    private RelatoriosMovimentoCaixa rel;
    Date data = new Date();
    Data util = new Data();
    private ctr_Movimentacoes controllerMovimento;

    public frmRelCaixa() {
    }

    public frmRelCaixa(JFrame control, Connection conn, int flag) {
        this();
        initComponents();
        jDateChooserDataInicial.setDate(new Date());
        jDateChooserDataFinal.setDate(new Date());
        this.conn = conn;
        this.flag = flag;
        controller = new ctr_Caixa(conn);
        this.control = control;
        rel = new RelatoriosMovimentoCaixa(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);

        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.insertItemAt("", 0);
        jComboBoxCaixa.setSelectedIndex(0);
       iconeSistema();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

//    public frmRelCaixa(JFrame c_form, Connection conn, int flag) {
//        this();
//        initComponents();
//        jDateChooserDataInicial.setDate(new Date());
//        jDateChooserDataFinal.setDate(new Date());
//        this.conn = conn;
//        this.flag = flag;
//        controller = new ctr_Caixa(conn);
//        controllerMovimento = new ctr_Movimentacoes(conn);
//        this.c_form = c_form;
//
//        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
//        jComboBoxCaixa.insertItemAt("", 0);
//        jComboBoxCaixa.setSelectedIndex(0);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCaixa = new javax.swing.JComboBox<>();
        jDateChooserDataInicial = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jDateChooserDataFinal = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emissão de Relatório de CAIXA");
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
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 164, -1));

        jLabel2.setText("Informe a Data Inicial do Relatório");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jComboBoxCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBoxCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 13, 162, -1));
        jPanel2.add(jDateChooserDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 51, 134, -1));

        jLabel10.setText("Informe a Data Final do Relatório");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 77, 192, 19));
        jPanel2.add(jDateChooserDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 77, 134, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 120));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Printer_32px.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 108, 46));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Forward_32px.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 108, 46));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(425, 245));
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
        rel.relatorioMovimentoCaixa(util.converteDataSql(jDateChooserDataInicial.getDate()), util.converteDataSql(jDateChooserDataFinal.getDate()), getCodCaixa(), jComboBoxCaixa.getSelectedItem().toString(), getSaldoAnterior(), getTotalEntradas(), getTotalSaidas(),tipoSaldo());
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    public static com.toedter.calendar.JDateChooser jDateChooserDataFinal;
    public static com.toedter.calendar.JDateChooser jDateChooserDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}