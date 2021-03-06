/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.views;

import GestaoStock.controller.EntradaController;
import GestaoStock.controller.EstoqueController;
import GestaoStock.controller.ItemSaidaController;
import GestaoStock.controller.MovimentacoesController;
import GestaoStock.controller.ProdutoController;
import GestaoStock.controller.SaidaController;
import GestaoStock.modelo.ItemSaida;
import GestaoStock.modelo.Movimentacoes;
import GestaoStock.modelo.Produto;
import GestaoStock.modelo.Saida;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import sf.ce.utilizacoes.Utilitarios;

/**
 *
 * @author El Router
 */
public class frmSaida extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;
    private DecimalFormat decimalformat;
    Utilitarios util = new Utilitarios();
    private Saida saida = new Saida();
    private ProdutoController produtos = new ProdutoController();
    private SaidaController controller = new SaidaController();
    private ItemSaida Itemsaida = new ItemSaida();
    private ItemSaidaController Itemcontroller = new ItemSaidaController();
    private int usuario, quantidade, codProduto, codSaida, estoqueProduto, codUnidade;
    MovimentacoesController movimentacoes = new MovimentacoesController();
    private EstoqueController estoque = new EstoqueController();
    Movimentacoes mov = new Movimentacoes();
    private EntradaController entradaController = new EntradaController();
    Produto p = new Produto();
    private int estoqueUN, estoqueCX;
    private String produto, obs, data1, data2;
    private HomeFarma controladora;

    public frmSaida() {

    }

    public frmSaida(HomeFarma controladora, int usuario) {
        this();
        initComponents();
        this.controladora = controladora;
        this.usuario = usuario;
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        decimalformat = new DecimalFormat("#,##0.00");
        try {
            util.preencherCombobox(jComboBox1, "Produto", "descricao");
            util.preencherCombobox(jComboBox2, "unidademedida", "descricaoUnidade");
        } catch (SQLException ex) {
            Logger.getLogger(frmSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos ");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 101));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Saida de Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 11))); // NOI18N

        jLabel2.setText("Quantidade existente:");

        jLabel3.setText("Descri????o do produto:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        jTable1.setBackground(new java.awt.Color(0, 153, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(110, 188, 153));
        jTable1.setSelectionForeground(new java.awt.Color(110, 188, 153));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton4.setText("Adicionar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-close-window-32.png"))); // NOI18N
        jButton5.setText("Excluir");

        jLabel5.setText("Quantidade a sair:");

        jLabel7.setText("Unidade Medida:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox2, 0, 384, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(606, 459));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (Integer.parseInt(jTextField1.getText()) <= Integer.parseInt(jTextField3.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Quantidade indisponivel para saida!");
        } else {
            inserirTabela();
            clearFields2();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // saida.setCodFarmanceutico(1);
            saida.setCodFarmanceutico(usuario);
            saida.setDataSaida(Data.getDataActual());
            if (controller.salvarSaida(saida)) {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    codProduto = util.getCodigo("produto", "descricao", jTable1.getValueAt(i, 0).toString(), "codProduto");
                    codUnidade = util.getCodigo("unidademedida", "descricaounidade", jComboBox2.getSelectedItem().toString(), "codUnidade");
                    String qt = jTable1.getValueAt(i, 1).toString();
                    Itemsaida.setCodSaida(util.getLastInsert("saidas", "codSaida", 3));
                    Itemsaida.setCodProduto(codProduto);
                    Itemsaida.setQtdeSaida(Integer.parseInt(qt));
                    Itemsaida.setCodUnidade(codUnidade);
                    Itemcontroller.salvarItemSaida(Itemsaida);
                    estoqueUN = estoque.getUnidadeProduto(codProduto);
                    estoqueCX = estoque.getCXProduto(codProduto);
                    estoqueProduto = produtos.getEstoqueAtual(codProduto);
                    produtos.operarEstoqueAtual(codProduto, (estoqueProduto - Integer.parseInt(qt)));
                    mov.setData(Data.getDataActual());
                    mov.setTipo(2);//2-Saida e 1- Entrada
                    mov.setItem(jTable1.getValueAt(i, 0).toString());
                    mov.setQtdAnteriorCX(estoqueCX);
                    mov.setQtdAnteriorUN(estoque.getUnidadeProduto(codProduto));
                    mov.setQtdMovimentadaCX(0);
                    mov.setQtdMovimentadaUN(Integer.parseInt((jTextField3.getText())));
                    mov.setQtdDisponivelCX(estoqueCX + 0);
                    mov.setQtdDisponivelUN(estoqueUN - Integer.parseInt(qt));
                    //  mov.setCodUtilizador(1);
                    System.out.println("Us??ario:" + usuario);
                    mov.setCodUtilizador(usuario);
                    mov.setLoteProduto(entradaController.getLoteProduto(codProduto));
                    mov.setCodProduto(codProduto);
                    movimentacoes.salvarMovimentacoes(mov);
                    System.out.println("Produto " + codProduto + " atual " + estoqueUN + "retirado " + Integer.parseInt(qt));
                    estoque.operarEstoque2(estoqueCX, (estoqueUN - Integer.parseInt(qt)), codProduto);
                }
                JOptionPane.showMessageDialog(rootPane, "Saida " + util.getLastInsert("saidas", "codSaida", 3) + " Efectuada!");
                clearFields1();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(frmSaida.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(frmSaida.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frmSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            util.preencherCombobox(jComboBox1, "Produto", "descricao");
        } catch (SQLException ex) {
            Logger.getLogger(frmSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            codProduto = util.getCodigo("produto", "descricao", jComboBox1.getSelectedItem().toString(), "codProduto");
            ArrayList lista = estoque.getQuantidadeByunidade(codProduto);
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("CX = " + lista.get(0) + "UN = " + lista.get(1));
                if (jComboBox2.getSelectedItem().toString().equals("UN")) {
                    if (lista.get(1) == null) {
                        estoqueProduto = 0;
                    } else {
                        estoqueProduto = Integer.parseInt(lista.get(1).toString());
                    }

                } else {
                    if (lista.get(1) == null) {
                        estoqueProduto = 0;
                    } else {
                        estoqueProduto = Integer.parseInt(lista.get(0).toString());
                    }
                }
            }
            jTextField1.setText("" + estoqueProduto);
        } catch (SQLException ex) {
//            Logger.getLogger(formVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        controladora.setEnabled(true);
        this.controladora.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        try {
            codProduto = util.getCodigo("produto", "descricao", jComboBox1.getSelectedItem().toString(), "codProduto");
            ArrayList lista = estoque.getQuantidadeByunidade(codProduto);
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("CX = " + lista.get(0) + "UN = " + lista.get(1));
                if (jComboBox2.getSelectedItem().toString().equals("UN")) {
                    if (lista.get(1) == null) {
                        estoqueProduto = 0;
                    } else {
                        estoqueProduto = Integer.parseInt(lista.get(1).toString());
                    }
                } else {
                    if (lista.get(1) == null) {
                        estoqueProduto = 0;
                    } else {
                        estoqueProduto = Integer.parseInt(lista.get(0).toString());
                    }
                }
            }
            jTextField1.setText("" + estoqueProduto);
        } catch (SQLException ex) {
//            Logger.getLogger(formVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    public void clearFields1() {
        jTextField1.setText("");
//        jTextField2.setText("");
        jTextField3.setText("");
        defaultTableModel.setRowCount(0);
    }

    public void clearFields2() {
        jTextField1.setText("");
//        jTextField2.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmSaidas().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    public void inserirTabela() {

        Object fila[] = new Object[3];

        fila[0] = jComboBox1.getSelectedItem().toString();
        fila[1] = jTextField3.getText();
//        fila[2] = jTextField2.getText();

        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(205);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
//        jTable1.getColumnModel().getColumn(2).setPreferredWidth(270);
//        jTable1.getColumnModel().getColumn(2).setResizable(false);

        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
    }

    public void MostrarEntradas(String sql) {
        ConexaoBancos conexao = new ConexaoBancos();
        try {
            conexao.Connectando();
            PreparedStatement ps;
            ResultSet rs;
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setResizable(true);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(2).setResizable(true);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setResizable(false);

            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{
                    rs.getString("codEntrada"),
                    rs.getString("descricao"),
                    rs.getString("descricaounidade"),
                    rs.getString("nomefornecedor"),
                    String.valueOf(decimalformat.format(Double.parseDouble(rs.getString("qtdeEntrada")))),
                    String.valueOf(decimalformat.format(Double.parseDouble(rs.getString("valorVenda")))),
                    rs.getString("datavalidade")});
            }

        } catch (Exception ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

}
