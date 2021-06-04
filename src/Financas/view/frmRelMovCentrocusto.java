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
public class frmRelMovCentrocusto extends javax.swing.JFrame {

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
    private ctr_Contas contas;

    public frmRelMovCentrocusto() {
    }

    public frmRelMovCentrocusto(JFrame control, Connection conn, int flag) {
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
        contas = new ctr_Contas(conn);

        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.insertItemAt("TODOS", 0);
        jComboBoxCaixa.setSelectedIndex(0);

//        jComboBoxCentros.setModel(new DefaultComboBoxModel(controllerCentros.getDescricao().toArray()));
        jComboBoxCentros.insertItemAt("Pesquisar Centros", 0);
        jComboBoxCentros.setSelectedIndex(0);

        jComboBoxConta.insertItemAt("Pesquisar Conta", 0);
        jComboBoxConta.setSelectedIndex(0);
       iconeSistema();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

//    public frmRelMovCentrocusto(JFrame c_form, Connection conn, int flag) {
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
////        jComboBoxCentros.setModel(new DefaultComboBoxModel(controllerCentros.getDescricao().toArray()));
//        jComboBoxCentros.insertItemAt("Pesquisar Centro", 0);
//        jComboBoxCentros.setSelectedIndex(0);
//        
//        jComboBoxConta.insertItemAt("Pesquisar Conta", 0);
//        jComboBoxConta.setSelectedIndex(0);
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
        return controllerMovimento.getEntradasByCaixaAndCentro(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()),jComboBoxConta.getSelectedItem().toString());
    }

    public double getTotalSaidas() {
        return controllerMovimento.getSaidasByCaixaAndCentro(Data.getData(jDateChooserDataInicial), Data.getData(jDateChooserDataFinal), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()), jComboBoxConta.getSelectedItem().toString());
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
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCentros = new javax.swing.JComboBox<>();
        jComboBoxConta = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emissão de Relatório do Plano de Contas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Printer_32px.png"))); // NOI18N
        jButton2.setText("Imprimir");
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Periodo"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jDateChooserDataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 134, -1));

        jLabel10.setText("Informe a Data Final do Relatório");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 192, 19));
        jPanel3.add(jDateChooserDataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 134, -1));

        jLabel2.setText("Informe a Data Inicial do Relatório");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Plano de Contas"));

        jLabel4.setText("C. Custo");

        jComboBoxCentros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMPRAS", "RECEITAS", "DESPESAS", "CUSTOS" }));
        jComboBoxCentros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCentrosActionPerformed(evt);
            }
        });

        jComboBoxConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Conta");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(jComboBoxCentros, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxConta, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCentros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        if (jRadioButtonPorcaixa.isSelected()) {
            if (jComboBoxCaixa.getSelectedIndex() != 0 && jComboBoxConta.getSelectedIndex()!=0) {
//                System.out.println("primeiro ....");
                rel.relatorioMovimentoCentroCusto(util.converteDataSql2(jDateChooserDataInicial.getDate()), util.converteDataSql2(jDateChooserDataFinal.getDate()), getCodCaixa(), getTotalEntradas(), getTotalSaidas(), jComboBoxConta.getSelectedItem().toString());
            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }

        }
        if (jRadioButtonTodoscaixas.isSelected()) {
            
            if (jComboBoxConta.getSelectedIndex()!=0) {
               System.out.println("segundo ....");
                rel.relatorioMovimentoCentroCusto(util.converteDataSql2(jDateChooserDataInicial.getDate()), util.converteDataSql2(jDateChooserDataFinal.getDate()), 0, getTotalEntradas(), getTotalSaidas(), jComboBoxConta.getSelectedItem().toString());
            } else {
                JOptionPane.showMessageDialog(null, "<html><font color = \"#000000\"> <left>Atenção, Não foi encontrado nenhuma conta do Plano de Contas neste Periodo ou Caixa. <br>Por Favor rever informação. </br></html> ", "Aviso do Sistema", 1);
            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonTodoscaixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodoscaixasActionPerformed
        jComboBoxCaixa.setEnabled(false);
        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxCaixa.insertItemAt("TODOS", 0);
        jComboBoxCaixa.setSelectedIndex(0);
    }//GEN-LAST:event_jRadioButtonTodoscaixasActionPerformed

    private void jComboBoxCentrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCentrosActionPerformed
        jComboBoxConta.setModel(new DefaultComboBoxModel(contas.getDescricaoByCentroCusto(jComboBoxCentros.getSelectedItem().toString()).toArray()));
    }//GEN-LAST:event_jComboBoxCentrosActionPerformed

    private void jRadioButtonPorcaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPorcaixaActionPerformed
        jComboBoxCaixa.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonPorcaixaActionPerformed

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
    private javax.swing.JComboBox<String> jComboBoxCentros;
    private javax.swing.JComboBox<String> jComboBoxConta;
    public static com.toedter.calendar.JDateChooser jDateChooserDataFinal;
    public static com.toedter.calendar.JDateChooser jDateChooserDataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonPorcaixa;
    private javax.swing.JRadioButton jRadioButtonTodoscaixas;
    // End of variables declaration//GEN-END:variables
}
