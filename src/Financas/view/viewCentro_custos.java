/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Alertas.frmAlertaExcluzao;
import Tabela.Tabla;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author El Router
 */
public class viewCentro_custos extends javax.swing.JInternalFrame {

    private Tabla t;
    private Connection conexao;
    int rown = -1;
    JDesktopPane desktop;

    public viewCentro_custos(Connection conexao, JDesktopPane desktop) {
        initComponents();
        desktop.add(this);
        this.conexao = conexao;
        t = new Tabla(conexao);
     //   visualizar();
    }

    public void visualizar() {
        if (jComboBox1.getSelectedIndex() == 0) {
            t.visualizarCentroCusto(jTable1, 1);
        } else {
            t.visualizarCentroCusto(jTable1, 2);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Pesquisa do Centro de Custos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Caretaker_32px.png"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/novo.png"))); // NOI18N
        jButton1.setText("Novo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordenar por C??digo", "Ordenar por Descri????o" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ordenar Por");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 214, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        setBounds(150, 50, 508, 464);
    }// </editor-fold>//GEN-END:initComponents

    public void getEnviarDados() {

        frmCadastroCentrocusto.jTextFieldCodigo.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        frmCadastroCentrocusto.jTextFieldDescricao.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
//        frmCadastroCentrocusto.jTextFieldTipo.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        frmCadastroCentrocusto.jTextFieldObs.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
//        jComboBoxCentro.insertItemAt(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString(), 0);

    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        rown = jTable1.rowAtPoint(evt.getPoint());

        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTable1.getRowHeight();

        if (row < jTable1.getRowCount() && row >= 0 && column < jTable1.getColumnCount() && column >= 0) {
            Object value = jTable1.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                String va1 = "" + jTable1.getValueAt(rown, 0);
                String va2 = "" + jTable1.getValueAt(rown, 1);
                String va3 = "" + jTable1.getValueAt(rown, 2);
                String va4 = "" + jTable1.getValueAt(rown, 3);
                String va5 = "" + jTable1.getValueAt(rown, 4);
                if (boton.getName().equals("edit")) {

                    frmCadastroCentrocusto hst = new frmCadastroCentrocusto(conexao, 1, va3);
                    int opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#08088A\"> <left> Aten????o, voc?? estas prestes a alterar o Centro de Custo<br> Se for alterar o NOME DO CENTRO DE CUSTO ter??<br>que ajustar todas as contas correspondentes a este centro de custo,<br>se tiver d??vida, por favor consultar o administrador do sistema.<br><br>Deseja mesmo alterar o Centro de Custo? </br></html>", "Aviso do Sistema", 1);
                    if (opc == 0) {
                        this.setEnabled(false);
                        hst.setVisible(true);
                        hst.toFront();
                        getEnviarDados();
                    } else {

                    }

                } else if (boton.getName().equals("v")) {
                    try {
                        JOptionPane.showMessageDialog(rootPane, "Estamos verificando se existem lan??amentos desse Centro Custo no Movimento de Caixa...");
                        new frmAlertaExcluzao(conexao, "Deseja realmente excluir o Centro de Custo?", "C??digo.:", va1, "Registro exluido!!!", "centro_custo").setVisible(true);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        this.setEnabled(false);
//        frmCadastroCentrocusto hst = new frmCadastroCentrocusto(conexao, this,0,"");
        new frmCadastroCentrocusto(conexao, 0, "").setVisible(true);
//        hst.setVisible(true);
//        hst.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        visualizar();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
