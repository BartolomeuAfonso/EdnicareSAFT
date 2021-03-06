/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerServico;
import CLINICA.relatorios.RelatorioLaboratorio;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Probook
 */
public class ResultadoPronto1 extends javax.swing.JFrame {

    /**
     * Creates new form ResultadoPronto
     */
    PreparedStatement ps;
    ResultSet rs;
    String nomeProduto;
    Connection con;
    Data d = new Data();
    ControllerUtente controllerUtente;
    ControllerUsuario controllerUsuario;
    RelatorioLaboratorio laboratorio = new RelatorioLaboratorio();
    ControllerExamesporFazer controllerExamesporFazer;
    ControllerServico controllerServico;

    public ResultadoPronto1(String nome) {
        initComponents();
        con = new ConexaoBancos().ConexaoBD();
        controllerUsuario = new ControllerUsuario(con);
        controllerUtente = new ControllerUtente(con);
        int tipoUtilizador = controllerUsuario.getTipoUtilizador(nome);
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        System.out.println("Nome do Medico:" + nome);
        int codigoMedico = controllerUsuario.getCodigoMedico(nome);
        controllerServico = new ControllerServico(con);
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        mostrarExame("SELECT distinct e.idexamesporFazer,p.nomeCompleto,s1.designacao, s.designacao,e1.dataResultado\n"
                + "FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                + "inner join servicos s1 on s1.idServico = e1.codigoProduto\n"
                + "inner join examesintegrado i on i.codigoServico = s1.idServico\n"
                + "where s.designacao ='Pronto' and e1.dataResultado =current_date\n"
                + "and i.codigoCategoria <>0");
        setLocationRelativeTo(null);
        iconeSistema();
    }

    public final void iconeSistema() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Resultados do Laborat??rio");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Buscar Anal??ses Prontos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 3, 11))); // NOI18N

        jButton4.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-google-web-search-26.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jLabel2.setText("Data Inicial");

        jLabel3.setText("Data Final");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??digo", "Nome", "An??lise", "Estado do Exame", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        }

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jRadioButton5.setText("A5");

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        jRadioButton6.setSelected(true);
        jRadioButton6.setText("A4");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("2- Dois click para actualizar resultado");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-pdf-32.png"))); // NOI18N
        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jRadioButton5)
                                .addGap(41, 41, 41)
                                .addComponent(jRadioButton6))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mostrarExame("SELECT distinct e.idexamesporFazer,p.nomeCompleto,s1.designacao,s.designacao,e1.dataResultado\n"
                + "FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                + "inner join servicos s1 on s1.idServico = e1.codigoProduto\n"
                + "inner join examesintegrado i on i.codigoServico = s1.idServico\n"
                + "where s.designacao ='Pronto' and (e1.dataResultado) between '" + getData() + "' and '" + getData1() + "'\n"
                + "and i.codigoCategoria <>0");


    }//GEN-LAST:event_jButton4ActionPerformed

    public int getCodigoPaciente() {
        return controllerUtente.getCodigoUtente(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int codigoExame = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            String codigoProduto = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
            String nomePaciente = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            new MicroBiologiaEditar(codigoExame, nomePaciente, codigoProduto).setVisible(true);

        }


    }//GEN-LAST:event_jTable1MouseClicked


    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            mostrarExame("SELECT distinct e.idexamesporFazer,p.nomeCompleto,s1.designacao, s.designacao,e1.dataResultado\n"
                    + "FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                    + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                    + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                    + "inner join servicos s1 on s1.idServico = e1.codigoProduto\n"
                    + "inner join examesintegrado i on i.codigoServico = s1.idServico\n"
                    + "where p.nomeCompleto LIKE '%" + jTextField1.getText() + "%' AND s.designacao ='Pronto' and e1.dataResultado =current_date\n"
                    + "and i.codigoCategoria <>0");
        } else {
            mostrarExame("SELECT distinct e.idexamesporFazer,p.nomeCompleto,s1.designacao, s.designacao,e1.dataResultado\n"
                    + "FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                    + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                    + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                    + "inner join servicos s1 on s1.idServico = e1.codigoProduto\n"
                    + "inner join examesintegrado i on i.codigoServico = s1.idServico\n"
                    + "where s.designacao ='Pronto' and e1.dataResultado =current_date\n"
                    + "and i.codigoCategoria <>0");
        }


    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int codigoExame = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int codigoProduto = controllerServico.getCodigoServico(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        laboratorio.getResultadoExamesA6semData(getCodigoPaciente(), codigoExame, codigoProduto);
    }//GEN-LAST:event_jButton1ActionPerformed

    public Date getData() {
        return d.converteDataSql(jDateChooser1.getDate());
    }

    public Date getData1() {
        return d.converteDataSql(jDateChooser2.getDate());
    }

    public final void mostrarExame(String sql) {
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
//            jTable1.getColumnModel().getColumn(3).setPreferredWidth(300);
//            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("e.idexamesporFazer"), rs.getString("p.nomeCompleto"), rs.getString("s1.designacao"), rs.getString("s.designacao"), rs.getString("e1.dataResultado")

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
            java.util.logging.Logger.getLogger(ResultadoPronto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadoPronto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadoPronto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadoPronto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ResultadoPronto1("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
