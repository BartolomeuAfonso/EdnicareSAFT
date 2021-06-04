/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.RelatorioFinanceiro;
import sf.ce.utilizacoes.Data;
import sf.ce.conexao.Extenso1;
import Financas.controller.ctr_Recibo;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import Financas.Modelo.Recibos;

/**
 *
 * @author El Router
 */
public class frmRecibos extends javax.swing.JFrame {

    /**
     * Creates new form frmRecibos
     */
    private int flag;
    private DecimalFormat df;
    private frmMovimentosCaixa cont;
    private formGerenciarRecibos cont1;

    Extenso1 extenso = new Extenso1();
    Recibos model = new Recibos();
    ctr_Recibo controller;
    Connection conexao;
    private double valorRecibo;
    private String conta, caixa, documento, historico, tipo;
    RelatorioFinanceiro rel;
    Date dataAtual = new Date();
    Data d = new Data();

    public frmRecibos(frmMovimentosCaixa cont, Connection conn, int flag, double valor, String conta, String caixa, String doc, String descricao, String exp) {
        initComponents();
        this.valorRecibo = valor;
        this.conta = conta;
        this.caixa = caixa;
        this.documento = doc;
        this.cont = cont;
        this.historico = descricao;
        this.tipo = exp;
        controller = new ctr_Recibo(conn);
        rel = new RelatorioFinanceiro();
        codRecibo.setText("" + (controller.getLast() + 1));
        df = new DecimalFormat("#,##0.00");
        if (flag == 1) {
            textoRecibo(valor, conta, caixa, doc, descricao, exp);
        } else {
            textoRecibo(0, "", "", "", "", "");
        }

        iconeSistema();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public frmRecibos(Connection conn, int flag, double valor, String conta, String caixa, String doc, String descricao, String exp, String cod) {
        initComponents();
        this.valorRecibo = valor;
        this.conta = conta;
        this.caixa = caixa;
        this.documento = doc;
        this.historico = descricao;
        this.tipo = exp;
        controller = new ctr_Recibo(conn);
        rel = new RelatorioFinanceiro();
        codRecibo.setText("" + (controller.getLast() + 1));
        df = new DecimalFormat("#,##0.00");
        if (flag == 1) {
            codRecibo.setText("" + cod);
            textoRecibo(valor, conta, caixa, doc, descricao, exp);
        } else {
            textoRecibo(0, "", "", "", "", "");
        }
        iconeSistema();
    }
//    public frmRecibos(formGerenciarRecibos cont1,Connection conn, int flag, double valor, String conta, String caixa, String doc, String descricao, String exp,String cod) {
//        initComponents();
//        this.valorRecibo = valor;
//        this.conta = conta;
//        this.caixa = caixa;
//        this.documento = doc;
//        this.cont1 = cont1;
//        this.historico = descricao;
//        this.tipo = exp;
//        controller = new ctr_Recibo(conn);
//        rel = new RelatorioFinanceiro();
//        codRecibo.setText("" + (controller.getLast() + 1));
//        df = new DecimalFormat("#,##0.00");
//        if (flag == 1) {
//            codRecibo.setText("" + cod);
//            textoRecibo(valor, conta, caixa, doc, descricao, exp);
//        } else {
//            textoRecibo(0, "", "", "", "", "");
//        }
//
//    }

    public void textoRecibo(double valor, String conta, String caixa, String doc, String descricao, String exp) {
        extenso = new Extenso1(new BigDecimal(valor));

        if (extenso.toString().equalsIgnoreCase(" um mil kwanzas")) {
            jTextFieldValorExtenso.setText("a importância SUPRA de AKZ " + df.format(valor) + "(Mil Kwanzas)");
        } else {
            jTextFieldValorExtenso.setText("a importância SUPRA de AKZ " + df.format(valor) + "(" + extenso.toString() + ")");
        }
        jTextFieldReferenciaDocumento.setText("Recibo de " + exp + " Referente ao DOCUMENTO " + doc + " do dia " + Data.getDataActual());

        jTextFieldConta.setText("CONTA.: " + conta + " MOVIMENTO DE CAIXA.: " + caixa + "");
        jTextFieldDescrição1.setText("HISTÓRICO.: " + descricao + " ");
        jTextFieldDescricao2.setText("HISTÓRICO.:    ");
        jTextFieldDescricao3.setText("HISTÓRICO.:    ");
        jTextFieldDizer.setText("PELO QUE FIRMAMOS O PRESENTE RECIBO DANDO PLENA E IRREVOGÁVEL QUITAÇÃO.");
        jTextFieldRodape1.setText("LUANDA - LDA " + new Date());
        jTextFieldRodape2.setText("<<< --GREST FINANÇAS & RH V3.55 2018-- >>>");
        jTextFieldValor.setText(df.format(valor));

    }

    private int variavelControl() {
        if (jRvia1.isSelected()) {
            return 1;
        } else if (jRvia2.isSelected()) {
            return 2;
        }
        return 0;
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codRecibo = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jRvia2 = new javax.swing.JRadioButton();
        jRvia1 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldReferenciaDocumento = new javax.swing.JTextField();
        jTextFieldValorExtenso = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextFieldConta = new javax.swing.JTextField();
        jTextFieldDescrição1 = new javax.swing.JTextField();
        jTextFieldDescricao2 = new javax.swing.JTextField();
        jTextFieldDescricao3 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextFieldDizer = new javax.swing.JTextField();
        jTextFieldRodape1 = new javax.swing.JTextField();
        jTextFieldRodape2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TELA DE RECIBOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº RECIBO");

        jLabel2.setText("VALOR RECIBO(AKZ)");

        codRecibo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codRecibo.setForeground(new java.awt.Color(102, 102, 102));
        codRecibo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        codRecibo.setText("1");
        codRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codReciboActionPerformed(evt);
            }
        });

        jTextFieldValor.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldValor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldValor.setText("0,00");

        buttonGroup1.add(jRvia2);
        jRvia2.setText("2 Vias");

        buttonGroup1.add(jRvia1);
        jRvia1.setSelected(true);
        jRvia1.setText("1 Via");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRvia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRvia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(codRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRvia1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRvia2)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldReferenciaDocumento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldReferenciaDocumento.setText("Recibo de Recebimento Referente ao DOCUMENTO             do dia / /");
        jTextFieldReferenciaDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldReferenciaDocumentoActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldReferenciaDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 2, 585, -1));

        jTextFieldValorExtenso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextFieldValorExtenso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 585, -1));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 585, -1));

        jTextFieldConta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextFieldConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 585, -1));

        jTextFieldDescrição1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextFieldDescrição1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 80, 585, -1));

        jTextFieldDescricao2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextFieldDescricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 100, 585, -1));

        jTextFieldDescricao3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextFieldDescricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 120, 585, -1));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 140, 585, -1));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 160, 585, -1));

        jTextFieldDizer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldDizer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldDizer.setText("PELO QUE FIRMAMOS O PRESENTE RECIBO DANDO PLENA E IRREVOGÁVEL QUITAÇÃO.");
        jPanel3.add(jTextFieldDizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 180, 585, -1));

        jTextFieldRodape1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldRodape1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(jTextFieldRodape1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 220, 585, -1));

        jTextFieldRodape2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldRodape2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(jTextFieldRodape2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 240, 585, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Printer_32px.png"))); // NOI18N
        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Checkmark_32px.png"))); // NOI18N
        jButton2.setText("Salvar Recibo");
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(28, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(624, 478));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void codReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codReciboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codReciboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        rel.recibo(
                Integer.parseInt(codRecibo.getText()),
                jTextFieldValorExtenso.getText(),
                conta,
                jTextFieldConta.getText(),
                jTextFieldReferenciaDocumento.getText(),
                jTextFieldDescrição1.getText(),
                jTextFieldDizer.getText(),
                jTextFieldRodape1.getText(),
                jTextFieldRodape2.getText(), variavelControl());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldReferenciaDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldReferenciaDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldReferenciaDocumentoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("doc " +documento);
        if (!controller.exist(documento)) {
            System.out.println("cá estou!!!");
            model.setTitulo(documento);
            model.setCaixa(caixa);
            model.setConta(conta);
            model.setData(d.converteDataSql(dataAtual));
            model.setHistorico1(historico);
            model.setHistorico2("");
            model.setHistorico3("");
            model.setTipo(tipo);
            model.setValor(valorRecibo);
            controller.save(model);
            codRecibo.setText("" + (controller.getLast()));
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (flag == 1) {
            cont.setEnabled(true);
            cont.toFront();
        } else {
            cont1.setEnabled(true);
            cont1.toFront();
        }
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(frmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRecibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmRecibos().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmRecibos().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField codRecibo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRvia1;
    private javax.swing.JRadioButton jRvia2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldConta;
    private javax.swing.JTextField jTextFieldDescricao2;
    private javax.swing.JTextField jTextFieldDescricao3;
    private javax.swing.JTextField jTextFieldDescrição1;
    private javax.swing.JTextField jTextFieldDizer;
    private javax.swing.JTextField jTextFieldReferenciaDocumento;
    private javax.swing.JTextField jTextFieldRodape1;
    private javax.swing.JTextField jTextFieldRodape2;
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTextField jTextFieldValorExtenso;
    // End of variables declaration//GEN-END:variables
}
