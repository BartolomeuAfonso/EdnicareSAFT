/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerFactura;
import CLINICA.controller.ControllerFacturaItens;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerParametros;
import CLINICA.modelo.Factura;
import CLINICA.modelo.FacturaItens;
import CLINICA.relatorios.RelatorioEstatistica;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Probook
 */
public class HonorarioColaboradores extends javax.swing.JFrame {

    /**
     * Creates new form HonorarioColaboradores
     */
    Connection con;
    private final DefaultTableModel defaultTableModel;
    ControllerServico controllerServico;
    ControllerUsuario controllerUsuario;
    ControllerMedico controllerMedico;
    ControllerParametros controllerParametros;
    ControllerFactura controllerFactura;
    Factura factura = new Factura();
    FacturaItens facturaItens = new FacturaItens();
    ControllerFacturaItens controllerFacturaItens;
    RelatorioEstatistica relatorioEstatistica = new RelatorioEstatistica();
    int codigoUser;
    ConexaoBancos conexao;
    PreparedStatement ps;
    ResultSet rs;

    public HonorarioColaboradores(int codigo) {
        initComponents();

        con = new ConexaoBancos().ConexaoBD();
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        iconeSistema();
        this.codigoUser = codigo;
        setLocationRelativeTo(null);
        controllerParametros = new ControllerParametros(con);
        controllerServico = new ControllerServico(con);
        controllerMedico = new ControllerMedico(con);
        controllerFactura = new ControllerFactura(con);
        controllerFacturaItens = new ControllerFacturaItens(con);
        controllerParametros = new ControllerParametros(con);
        jComboBox2.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedicoColaboradores().toArray()));
        jComboBox3.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedicoColaboradores().toArray()));
        jComboBox1.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        jDateChooser3.setDate(new Date());

        mostrarProduto("SELECT\n"
                + "     facturacolaboradores.`codigo` AS codigo,\n"
                + "     servicos.`designacao` AS designacao,\n"
                + "     facturacolaboradoresitens.`preco` AS preco,\n"
                + "     facturacolaboradoresitens.`percentagem` AS percentagem,\n"
                + "     medicocolaboradores.`nomeCompleto` AS nomeMedico\n"
                + "FROM\n"
                + "     `facturacolaboradores` facturacolaboradores INNER JOIN `facturacolaboradoresitens` facturacolaboradoresitens ON facturacolaboradores.`codigo` = facturacolaboradoresitens.`codigoFactura1`\n"
                + "     INNER JOIN `servicos` servicos ON facturacolaboradoresitens.`codigoServico` = servicos.`idServico`\n"
                + "     INNER JOIN `medicocolaboradores` medicocolaboradores ON facturacolaboradores.`codigoColaboradores` = medicocolaboradores.`idMedicoColaboradores` WHERE data=CURRENT_DATE");

        jTextField2.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabela();
                }
            }
        });
    }

    public final void iconeSistema() {
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public String getData1() {
        String dataSelecionada = "2015-03-07";

        if (jDateChooser1.getDate() != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(jDateChooser1.getDate());
            dataSelecionada = gc.get(GregorianCalendar.YEAR) + "-"
                    + (gc.get(GregorianCalendar.MONTH) + 1) + "-"
                    + gc.get(GregorianCalendar.DATE);
        }
        return dataSelecionada;
    }

    public String getData3() {
        String dataSelecionada = "2015-03-07";

        if (jDateChooser3.getDate() != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(jDateChooser3.getDate());
            dataSelecionada = gc.get(GregorianCalendar.YEAR) + "-"
                    + (gc.get(GregorianCalendar.MONTH) + 1) + "-"
                    + gc.get(GregorianCalendar.DATE);
        }
        return dataSelecionada;
    }

    public String getData2() {
        String dataSelecionada = "2015-03-07";

        if (jDateChooser2.getDate() != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(jDateChooser2.getDate());
            dataSelecionada = gc.get(GregorianCalendar.YEAR) + "-"
                    + (gc.get(GregorianCalendar.MONTH) + 1) + "-"
                    + gc.get(GregorianCalendar.DATE);
        }
        return dataSelecionada;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lançamento de Colaboradores");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setText("Nº Factura");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setText("Serviços:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Serviço", "Preço", "Percentagem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-ok-filled-32.png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setText("Colaboradores:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setText("Data:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel78))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel77)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel80)))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(346, 346, 346)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel80))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lançamento", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Serviço", "Preço", "Percentagem", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listas", jPanel2);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setText("Data Inicial:");

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setText("Data Final:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-pdf-32.png"))); // NOI18N
        jButton3.setText("PDF");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Todos");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Por Colaboradores");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81)
                            .addComponent(jRadioButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel82))
                            .addComponent(jRadioButton2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Visualizar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        // TODO add your handling code here:
        if (!jTextField2.getText().isEmpty()) {
            jComboBox1.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicosPorlike(jTextField2.getText()).toArray()));
        }
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        inserirTabela();
    }//GEN-LAST:event_jButton2ActionPerformed

    public final void mostrarProduto(String sql) {
        //   System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(4).setResizable(false);

            jTable2.getTableHeader().setReorderingAllowed(false);
            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("codigo"), rs.getString("designacao"), rs.getString("preco"),
                    rs.getString("percentagem"), rs.getString("nomeMedico")

                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void limparVenda() {
        jTextField1.setText("");
        defaultTableModel.setRowCount(0);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jComboBox2.getSelectedItem().equals("")) {
            salvarColaboradores();
            salvarColaboradoresIntes();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
            limparVenda();
            mostrarProduto("SELECT\n"
                    + "     facturacolaboradores.`codigo` AS codigo,\n"
                    + "     servicos.`designacao` AS designacao,\n"
                    + "     facturacolaboradoresitens.`preco` AS preco,\n"
                    + "     facturacolaboradoresitens.`percentagem` AS percentagem,\n"
                    + "     medicocolaboradores.`nomeCompleto` AS nomeMedico\n"
                    + "FROM\n"
                    + "     `facturacolaboradores` facturacolaboradores INNER JOIN `facturacolaboradoresitens` facturacolaboradoresitens ON facturacolaboradores.`codigo` = facturacolaboradoresitens.`codigoFactura1`\n"
                    + "     INNER JOIN `servicos` servicos ON facturacolaboradoresitens.`codigoServico` = servicos.`idServico`\n"
                    + "     INNER JOIN `medicocolaboradores` medicocolaboradores ON facturacolaboradores.`codigoColaboradores` = medicocolaboradores.`idMedicoColaboradores` WHERE date(data)=CURRENT_DATE");
        } else {
            JOptionPane.showMessageDialog(null, "Não existe nome dos Colaboradores Selecionado");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jRadioButton2.isSelected()) {
            relatorioEstatistica.IrelatorioHonorario1(getData1(), getData2(), getCodigoMedico1());
        }
        if (jRadioButton1.isSelected()) {
            double irt = controllerParametros.getValorIrt();
            relatorioEstatistica.IrelatorioHonorario2(getData1(), getData2(), irt);
            // JOptionPane.showMessageDialog(null, "BREVEMENTE");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            defaultTableModel.removeRow(jTable1.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public int getCodigoUtilizador() {
        return codigoUser;
    }

    public int getCodigoServico() {
        return controllerServico.getCodigoServico(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoMedico() {
        return controllerMedico.getCodigoMedicoColaboradores(jComboBox2.getSelectedItem().toString());
    }

    public int getCodigoMedico1() {
        return controllerMedico.getCodigoMedicoColaboradores(jComboBox3.getSelectedItem().toString());
    }

    public double totalGeral() {
        double total = 0, d = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            d = Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 3)));
            total = total + d;
        }

        return total;

    }

    public void salvarColaboradores() {
        factura.setCodigoCliente(getCodigoMedico());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setTotalFactura(totalGeral());
        factura.setEstado(jTextField1.getText());
        factura.setDataVencimento(getData3());
        controllerMedico.salvarHonorario(factura);

    }

    public void salvarColaboradoresIntes() {
        double lab = 0, consulta = 0, raioX = 0, ecografia = 0, ecografia7 = 0, ecografia10 = 0, ecografiamorfologica = 0, ecocardiograma = 0, electrocardiograma = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerMedico.getLastFacturaHonorario();
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            // Consulta
            if (codigoCategoria == 1) {
                consulta = consulta + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getValorConsulta();
                lab = 0;
                raioX = 0;
                ecografia = 0;
                ecografia7 = 0;
                ecocardiograma = 0;
                electrocardiograma = 0;
                ecografiamorfologica = 0;
            }
            // Laboratorio
            if (codigoCategoria == 2) {
                lab = lab + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getValorExames();
                consulta = 0;
                raioX = 0;
                ecografia = 0;
                ecografia10 = 0;
                ecografia7 = 0;
                ecocardiograma = 0;
                electrocardiograma = 0;
                ecografiamorfologica = 0;
            }
            // Raio X
            if (codigoCategoria == 3) {
                raioX = raioX + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getValorRaioX();
                consulta = 0;
                lab = 0;
                ecografia = 0;
                ecografia10 = 0;
                ecografia7 = 0;
                ecografiamorfologica = 0;
                electrocardiograma = 0;
                ecocardiograma = 0;
            }
            // Ecografia
            if (codigoCategoria == 5) {

                ecografia = ecografia + controllerParametros.getValorEcografiaCola5();
                consulta = 0;
                raioX = 0;
                lab = 0;
                ecografia10 = 0;
                ecografia7 = 0;
                ecografiamorfologica = 0;
                ecocardiograma = 0;
                electrocardiograma = 0;
            }
            // ElectroCardiograma
            if (codigoCategoria == 19) {
                electrocardiograma = electrocardiograma + controllerParametros.getValorElectrocardiograma();
                consulta = 0;
                raioX = 0;
                lab = 0;
                ecografia = 0;
                ecografia = 0;
                ecografia7 = 0;
                ecografiamorfologica = 0;
                ecocardiograma = 0;
            }
            // Ecografia 7
            if (codigoCategoria == 21) {
                ecografia7 = ecografia7 + controllerParametros.getValorEcografiaCola5();
                consulta = 0;
                raioX = 0;
                lab = 0;
                ecografia = 0;
                electrocardiograma = 0;
                ecografiamorfologica = 0;
                ecocardiograma = 0;
            }
            // Ecografia 10
            if (codigoCategoria == 22) {
                ecografia10 = ecografia10 + controllerParametros.getValorEcografiaCola5();
                consulta = 0;
                raioX = 0;
                lab = 0;
                ecografia = 0;
                electrocardiograma = 0;
                ecografia7 = 0;
                ecocardiograma = 0;
            }
            // Ecografia 20
            if (codigoCategoria == 23) {
                ecografiamorfologica = ecografiamorfologica + controllerParametros.getValorEcografiaCola20();
                consulta = 0;
                raioX = 0;
                lab = 0;
                ecografia = 0;
                electrocardiograma = 0;
                ecografia7 = 0;
                ecografia10 = 0;
                ecocardiograma = 0;
            }

            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            facturaItens.setCodigoFactura(codigoFactura);
            facturaItens.setCodigoServico(codigoProduto);
            facturaItens.setPreco(valorUnitario);
            //facturaItens.setLaboratorio(lab);
            facturaItens.setRaioX(raioX);
            facturaItens.setPercentagem(lab);
            facturaItens.setConsulta(consulta);
            facturaItens.setEcocardiograma(ecocardiograma);
            facturaItens.setElectrocardiograma(electrocardiograma);
            facturaItens.setEcografia(ecografia);
            facturaItens.setEcografia7(ecografia7);
            facturaItens.setEcografia10(ecografia10);
            facturaItens.setEcografiaMorfologia(ecografiamorfologica);
            controllerMedico.salvarHonorarioItens(facturaItens);

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
            java.util.logging.Logger.getLogger(HonorarioColaboradores.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HonorarioColaboradores.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HonorarioColaboradores.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HonorarioColaboradores.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HonorarioColaboradores().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public void inserirTabela() {
        Object fila[] = new Object[6];
        int codigoServico = 0;
        double percetagem = 0;
        codigoServico = controllerServico.getCodigoServico(jComboBox1.getSelectedItem().toString());
        double preco = controllerServico.getPreco(jComboBox1.getSelectedItem().toString());
        int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoServico);
        if (codigoCategoria == 1) {
            percetagem = percetagem + preco * controllerParametros.getValorConsulta();

        }
        if (codigoCategoria == 2) {
            percetagem = percetagem + preco * controllerParametros.getValorExames();

        }
        if (codigoCategoria == 3) {
            percetagem = percetagem + preco * controllerParametros.getValorRaioX();

        }
        if (codigoCategoria == 5) {
            percetagem = percetagem + preco * controllerParametros.getValorEcografia5();

        }
        if (codigoCategoria == 18) {
            percetagem = percetagem + preco * controllerParametros.getValorCardiologia();

        }

        fila[0] = codigoServico;
        fila[1] = jComboBox1.getSelectedItem().toString();
        fila[2] = preco;
        fila[3] = percetagem;
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setResizable(!false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

    }
}
