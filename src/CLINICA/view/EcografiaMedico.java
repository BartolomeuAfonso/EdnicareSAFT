/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.URL.BareBonesBrowserLaunch;
import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerResultadoRaioX;
import CLINICA.controller.ControllerServico;
import CLINICA.relatorios.RelatorioEcografia;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Probook
 */
public class EcografiaMedico extends javax.swing.JFrame {

    /**
     * Creates new form RaioXMedico
     */
    PreparedStatement ps;
    ResultSet rs;
    String nomeProduto;
    Connection con;
    Data d = new Data();
    ControllerUtente controllerUtente;
    ControllerUsuario controllerUsuario;
    RelatorioEcografia ecografia = new RelatorioEcografia();
    ControllerResultadoRaioX controllerEcografia;
    ControllerServico controllerServico;

    public EcografiaMedico(String nome) {
        initComponents();
        //con = new ConexaoBancos().ConexaoBD();
        controllerUsuario = new ControllerUsuario(con);
        controllerUtente = new ControllerUtente(con);
        controllerEcografia = new ControllerResultadoRaioX(con);
        controllerServico = new ControllerServico(con);
        int tipoUtilizador = controllerUsuario.getTipoUtilizador(nome);
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        mostrarExame("SELECT DISTINCT r.idresultadoEcografia as id,r.descricao,r.nomePaciente,s.designacao as designacao, r.imagem,r.data  FROM resultadoecografia r INNER JOIN servicos INNER JOIN servicos s ON r.codigoServico = s.idServico where r.data=current_date");
        setLocationRelativeTo(null);
        iconeSistema();
    }

    public void iconeSistema() {
        // URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Resultados Prontos");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 204), 1, true), "Buscar Ecografia Prontos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 3, 11))); // NOI18N

        jButton4.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-google-web-search-26.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Data Inicial");

        jLabel3.setText("Data Fim");

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jTextField1)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1)))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "C??digo", "Nome do Paciente", "Ecografia", "Designa????o da Ecografia", "Imagem", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/iconfinder_multimedia-37_2849799.png"))); // NOI18N
        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-as-filled-32.png"))); // NOI18N
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrarExame("SELECT DISTINCT r.idresultadoEcografia as id,r.descricao,r.nomePaciente,s.designacao as designacao, r.imagem,r.data  FROM resultadoecografia r INNER JOIN servicos INNER JOIN servicos s ON r.codigoServico = s.idServico where data between '" + getData() + "' and '" + getData1() + "'");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int codigoRaixo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        String designacao = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        if (evt.getClickCount() == 2) {
            if (designacao.equals("Ecografia Pelvica")) {

                ecografia.getEcografiaPelvica(codigoRaixo);
            } else if (designacao.equals("Ecografia Morfologica do segundo trimestre")) {

                ecografia.getEcografiaMorfologica(codigoRaixo);
            } else if (designacao.equals("Ecografia Abdominal")) {

                ecografia.getEcografiaAbdominal(codigoRaixo);
            } else if (designacao.equals("Ecografia Obst??trica") || designacao.equals("Ecografia Obst??trica do segundo e Terceiro Trimestre") || designacao.equals("Ecografia Obst??trica do Primeiro Trimestre") || designacao.equals("Ecografia Obst??trica Gemelar")) {
                ecografia.getEcografiaObstetrica(codigoRaixo);
            } else if (designacao.equals("Ecografia da Mama")) {

                ecografia.getEcografiaMama(codigoRaixo);
            } else if (designacao.equals("Ecografia Testicular")) {

                ecografia.getEcografiaTesticular(codigoRaixo);
            } else if (designacao.equals("Ecografia da Tiroide")) {

                ecografia.getEcografiaTiroide(codigoRaixo);
            } else if (designacao.equals("Ecografia Morfologica do primeiro trimestre")) {
                ecografia.getEcografiaAbdominal(codigoRaixo);
            } else {
                ecografia.getEcografiaAbdominal(codigoRaixo);
            }
        }
//       BareBonesBrowserLaunch browserLaunch = new BareBonesBrowserLaunch();
//        jLabel1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
//        String url =jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString() ;
//        int codigoRaixo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//        browserLaunch.openURL(url.trim());
//        RelatorioEcografia ecografia = new RelatorioEcografia();

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        int codigoRaixo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//        int codigoPaciente = controllerUtente.getCodigoUtente(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
//        try {
//            String imagem = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
//            String descricao = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
//            File out = new File(imagem);
//            Desktop.getDesktop().open(out);
//
//        } catch (IOException ex) {
//            Logger.getLogger(RaioXMedico.class.getName()).log(Level.SEVERE, null, ex);
//        }

        JOptionPane.showMessageDialog(rootPane, "N??o existe comunica????o com a m??quina de ecografia");
        // new SlideEcografia(codigoPaciente, jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), codigoRaixo, imagem, descricao).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            mostrarExame("SELECT DISTINCT r.idresultadoEcografia as id,r.descricao,r.nomePaciente,s.designacao as designacao, r.imagem,r.data  \n"
                    + "FROM resultadoecografia r INNER JOIN servicos INNER JOIN servicos s ON r.codigoServico = s.idServico\n"
                    + "WHERE r.nomePaciente LIKE '%" + jTextField1.getText() + "%'");
        }

    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int exame = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        String descricao = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String nomePaciente = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String codigo = "" + controllerUtente.getCodigoUtente(nomePaciente);
        int idade = controllerUtente.getIdade(nomePaciente);
        if (descricao.equalsIgnoreCase("Ecografia Pelvica")) {
            int codigoProduto = controllerServico.getCodigoServico(descricao);
            String resultado = controllerEcografia.getOficio(exame).get(0).getDescricao();
            String ovario = controllerEcografia.getOficio(exame).get(0).getOvarios();
            String conclusao = controllerEcografia.getOficio(exame).get(0).getConclusao();
            new ProcessarEcografia(nomePaciente, idade, descricao, resultado, ovario, conclusao, 1, codigoProduto, codigo, 2, exame).setVisible(true);
        } else {
            int codigoProduto = controllerServico.getCodigoServico(descricao);
            String resultado = controllerEcografia.getOficio(exame).get(0).getDescricao();
            String ovario = controllerEcografia.getOficio(exame).get(0).getOvarios();
            String conclusao = controllerEcografia.getOficio(exame).get(0).getConclusao();
            new ProcessarAbdominal(nomePaciente, idade, descricao, resultado, conclusao, 1, codigoProduto, codigo, 2, exame).setVisible(true);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    public Date getData() {
        return d.converteDataSql(jDateChooser1.getDate());
    }

    public Date getData1() {
        return d.converteDataSql(jDateChooser2.getDate());
    }

    public void mostrarExame(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("id"), rs.getString("r.nomePaciente"), rs.getString("s.designacao"), rs.getString("r.descricao"), rs.getString("r.imagem"), rs.getString("r.data")

                });
            }
        } catch (Exception ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

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
            java.util.logging.Logger.getLogger(EcografiaMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EcografiaMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EcografiaMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EcografiaMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EcografiaMedico("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
