/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerTriagem;
import CLINICA.controller.ControllerPedidoExames;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.modelo.ExamesPorFazerItem;
import CLINICA.relatorios.RelatorioHistoricoClinico;
import CLINICA.modelo.ExamesporFazer;
import java.sql.Connection;
import CLINICA.modelo.PedidoExames;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author Probook
 */
public class PedidoExame extends javax.swing.JFrame {

    /**
     * Creates new form PedidoExame
     */
    ControllerServico controllerServico;
    ControllerUtente controllerBeneficiario;
    ControllerUsuario controllerUsuario;
    Data d = new Data();
    private final DefaultTableModel defaultTableModel;
    ConexaoBancos conexao;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ControllerPedidoExames controllerPedidoExames;
    PedidoExames pedidoExames = new PedidoExames();
    private DefaultTableModel dataModel;
    ExamesporFazer exame;
    int codigoUsuario = 0, codigoUltimoExamePorFazer;
    ControllerExamesporFazerItens controllerExamesporFazerItens;
    ControllerExamesporFazer controllerExamesporFazer;

    public PedidoExame(int codigoUtilizador) {
        initComponents();
        codigoUsuario = codigoUtilizador;
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        System.out.println("Teste:" + codigoUtilizador);
        controllerServico = new ControllerServico(con);
        controllerExamesporFazerItens = new ControllerExamesporFazerItens(con);
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        controllerBeneficiario = new ControllerUtente(con);
        controllerPedidoExames = new ControllerPedidoExames(con);
        jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
        jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getNomeExames().toArray()));
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        mostrarExame("SELECT distinct p.idexamesPorFazer as codigo,pa.nomeCompleto as paciente,e.designacao as seguro,m.nomeCompleto as medico,s.designacao as estado FROM examesporfazer p inner join examesporfazeritems e2 on p.idexamesPorFazer = e2.codigoExames\n"
                + "inner join pacientes pa on p.codigoPaciente =pa.idPaciente inner join medicos m on p.codigoMedico = m.idMedico\n"
                + "inner join status_exames s on s.idstatus_exames = p.codigoStatus\n"
                + "inner join empresaseguros e on e.idSeguros =pa.codigoSeguro\n"
                + "where DATE(p.dataPedido) = current_date and s.designacao ='Por Fazer' AND p.PacienteInterno='SIM' order by p.idexamesPorFazer");
        mostrarExame1("SELECT distinct e.idexamesporFazer,p.nomeCompleto, s.designacao,e1.dataResultado FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                + "where s.designacao ='Pronto' and e1.dataResultado =current_date\n"
                + "group by e.idexamesporFazer");
        teclaInser();
        //  jTabbedPane1.setEnabledAt(2, false);
        teclaInserQuantidade();
        setLocationRelativeTo(null);
        iconeSistema();
    }

    public final void iconeSistema() {
        // URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public final void teclaInser() {
        jTextField4.addKeyListener(
                new KeyAdapter() {
            public final void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabela();
                }
            }

        });
    }

    public final void teclaInserQuantidade() {
        jTextField3.addKeyListener(
                new KeyAdapter() {
            public final void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabela();
                }
            }

        });
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Requisição de Exames", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText(" Código:");

        jLabel2.setText(" Nome:");

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        jLabel3.setText("Paciente:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Código:");

        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });

        jLabel5.setText("Nome:");

        jTextField4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField4CaretUpdate(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Exames:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-add-shopping-cart-32.png"))); // NOI18N
        jButton5.setText("Carregar Exames");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Designação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jButton2.setText("Remover Exames");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addComponent(jTextField4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Pedido Externo", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Paciente", "Entidade", "Médico", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
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

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("Nota: Click duas vezes em seguida para obter relatório.");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton6.setText("Enviar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(275, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(435, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Pedido Interno (Médico(a))", jPanel3);

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Estado do Exame", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jRadioButton4.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("2. Por Data");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("1. Por Data");

        jButton7.setFont(new java.awt.Font("Century Gothic", 3, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-google-web-search-26.png"))); // NOI18N
        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton4)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton7)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        jTabbedPane1.addTab("Resultado Prontos", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        // TODO add your handling code here:
        if (!jTextField2.getText().isEmpty()) {
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNome(jTextField2.getText()).toArray()));
        }
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getCodigo(Integer.parseInt(jTextField1.getText().trim())).toArray()));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        // TODO add your handling code here:
        if (!jTextField3.getText().isEmpty()) {
            jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getCodigoBuscarExame(Integer.parseInt(jTextField3.getText().trim())).toArray()));
        } else {
            jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getNomeExames().toArray()));
        }
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        if (!jTextField4.getText().isEmpty()) {
            jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getCodigoBuscarExamePorDesginacao(jTextField4.getText()).toArray()));
        } else {
            jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getNomeExames().toArray()));
        }
    }//GEN-LAST:event_jTextField4CaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        inserirTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            defaultTableModel.removeRow(jTable1.getSelectedRow());
//            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!jTextField1.getText().isEmpty() || !jTextField2.getText().isEmpty()) {
            mostrarExames("");
        } else {

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        codigoUltimoExamePorFazer = 0;
        boolean itemGravado = false;
        boolean salvo = false;
        int quantidade = 1;
        exame = new ExamesporFazer();
        exame.setCodigoPaciente(getCodigoPaciente());
        exame.setCodigoUtilizador(codigoUsuario);
        exame.setDataPedido(getDataActual());
        exame.setQuantidade(quantidade);
        System.out.println("Codigo do Utilizador:" + codigoUsuario);
        controllerExamesporFazer.create(exame);
        salvo = true;
        if (salvo) {
            codigoUltimoExamePorFazer = controllerExamesporFazer.getLastInsert();
            for (int i = 0; i < jTable1.getRowCount(); i++) {

                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                System.out.println("Codigo do Serviço:" + codigoServico);
                int codigoProdutoItem = controllerServico.getCodigoProdutoItem(codigoServico);
                Vector vectProdutosItem;
                vectProdutosItem = controllerServico.getCodigosProdutoItem(codigoServico);

                for (int j = 0; j < vectProdutosItem.size(); j++) {

                    System.out.println("Quantidade de Pedido:" + quantidade);
                    if (codigoServico != 0) {
                        System.out.println("Codigo do Serviço:" + codigoServico);
                        if (salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, Integer.parseInt(vectProdutosItem.elementAt(j).toString().trim()), quantidade)) {
                            itemGravado = true;
//                        }
                        } else {
                            salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, 0, quantidade);
                        }
                    }
//                    pedidoExames.setCodigoServico(codigoServico);
//                    controllerPedidoExames.SalvarParticular(pedidoExames);
                }
                if (codigoProdutoItem == 0) {
                    salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, 0, quantidade);
                    itemGravado = true;
                }

            }

            if (itemGravado) {
                JOptionPane.showMessageDialog(null, "Pedido(s) Salvo com Sucesso!!!");
                //limpar();
                RelatorioHistoricoClinico relatorioHistoricoClinico = new RelatorioHistoricoClinico();
                relatorioHistoricoClinico.getPedidoExames1(codigoUltimoExamePorFazer);

            } else {
                JOptionPane.showMessageDialog(null, "O seu pedid(s) não foi salvo com Sucesso!!!");
            }

        }

        //      ExamesporFazer exame = new ExamesporFazer(codigoPagamento, ICONIFIED, dataPedido, HEIGHT, pacienteInterno);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        RelatorioHistoricoClinico relatorioHistoricoClinico = new RelatorioHistoricoClinico();
        int codigo = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        int codigoPaciente = controllerBeneficiario.getCodigoUtente(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(codigoPaciente);
        if (evt.getClickCount() == 2) {
            if (codigoSeguradora != 8) {
                relatorioHistoricoClinico.getPedidoExames(codigo);
            } else {
                relatorioHistoricoClinico.getPedidoExames1(codigo);
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int codigo = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        controllerExamesporFazer.updatePago(codigo);
        mostrarExame("SELECT distinct p.idexamesPorFazer as codigo,pa.nomeCompleto as paciente,e.designacao as seguro,m.nomeCompleto as medico,s.designacao as estado FROM examesporfazer p inner join examesporfazeritems e2 on p.idexamesPorFazer = e2.codigoExames\n"
                + "inner join pacientes pa on p.codigoPaciente =pa.idPaciente inner join medicos m on p.codigoMedico = m.idMedico\n"
                + "inner join status_exames s on s.idstatus_exames = p.codigoStatus\n"
                + "inner join empresaseguros e on e.idSeguros =pa.codigoSeguro\n"
                + "where DATE(p.dataPedido) = current_date and s.designacao ='Por Fazer' AND p.PacienteInterno='SIM' order by p.idexamesPorFazer");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        if (evt.getClickCount() == 2) {
            JOptionPane.showMessageDialog(null, "Não lhe é permitido ver Resultado. Obrigado");

        }
//        jLabel1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
//        if (evt.getClickCount() == 2) {
//            int codigoExame = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//            int codigoExameItengrado = controllerExamesporFazer.getCodigoExameIntegrado(codigoExame);
//            String data = controllerExamesporFazer.getDataPedido(codigoExame);
//            if (codigoExameItengrado != 0) {
//                if (jRadioButton5.isSelected()) {
//
//                    laboratorio.getResultadoExames(data, data, getCodigoPaciente(), codigoExame);
//                }
//                if (jRadioButton6.isSelected()) {
//                    if (jRadioButton2.isSelected()) {
//                        laboratorio.getResultadoExamesporParametro(data, data, getCodigoPaciente(), codigoExame);
//                        laboratorio.getResultadoExamesA5(data, data, getCodigoPaciente(), codigoExame);
//                    }
//                    if (jRadioButton3.isSelected()) {
//                        laboratorio.getResultadoExamesA6(data, data, getCodigoPaciente(), codigoExame);
//
//                    }
//
//                }
//            } else {
//                if (jRadioButton5.isSelected()) {
//                    laboratorio.getResultadoExames1(data, data, getCodigoPaciente(), codigoExame);
//                }
//                if (jRadioButton6.isSelected()) {
//                    laboratorio.getResultadoExames1A4(data, data, getCodigoPaciente(), codigoExame);
//                }
//
//            }
//        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        mostrarExame1("SELECT DISTINCT e.idexamesporFazer,p.nomeCompleto, s.designacao,e1.dataResultado FROM examesporfazer e inner join examesporfazeritems e1 on e.idexamesPorFazer=e1.codigoExames\n"
                + "inner join pacientes p on e.codigoPaciente = p.idPaciente\n"
                + "inner join status_exames s on s.idstatus_exames = e1.codigoStatusExame\n"
                + "where date(e.dataPedido) between '" + getData() + "' and '" + getData1() + "' and s.designacao ='Pronto'");
    }//GEN-LAST:event_jButton7ActionPerformed
    public Date getData() {
        return d.converteDataSql(jDateChooser1.getDate());
    }

    public Date getData1() {
        return d.converteDataSql(jDateChooser2.getDate());
    }

    public final void mostrarExame1(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getTableHeader().setReorderingAllowed(false);
            jTable3.setAutoResizeMode(jTable3.AUTO_RESIZE_OFF);
            jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("e.idexamesporFazer"), rs.getString("p.nomeCompleto"), rs.getString("s.designacao"), rs.getString("e1.dataResultado")

                });
            }
        } catch (Exception ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void limparAnalise(int numeroLinha) {
        for (int i = 0; i < numeroLinha; i++) {
            dataModel.removeRow(0);
        }
    }

    public int getCodigoPaciente() {
        System.out.println("Paciente:" + controllerBeneficiario.getCodigoUtente(jComboBox1.getSelectedItem().toString()));
        return controllerBeneficiario.getCodigoUtente(jComboBox1.getSelectedItem().toString());

    }

    public void limparTabela() {
        jTable1.removeAll();
    }

    public boolean salvarPedidoExameItem(int codigoExame, int codProduto, int codigoStatus, int codigoProdutoIem, int quantidade) {
        ExamesPorFazerItem item = new ExamesPorFazerItem(codigoExame, codProduto, codigoStatus, codigoProdutoIem, quantidade);
        controllerExamesporFazerItens.create(item);
        return true;
    }

    public Vector carregarPedidosSolicitados(String paciente) {
        int codigoCliente = controllerBeneficiario.getCodigoUtente(paciente);

        ControllerTriagem controllerTriagem = new ControllerTriagem(con);

        int codigoTriagem = controllerTriagem.getLastCodigoByPacienteToday(codigoCliente, getDataActual());
        controllerServico.getExamesSolicitados(codigoTriagem, codigoCliente);

        return controllerServico.getExamesSolicitados(codigoTriagem, codigoCliente);

    }

    public String getDataActual() {
        Calendar calendario = Calendar.getInstance();

        //buscar data
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);

        String data = ano + "-" + (mes + 1) + "-" + dia;
        //String data = ano + "-" + (mes + 1) + "-" + dia;

        return data;

    }

    public String pacienteInterno() {
        return "SIM";
    }

    public void salvarPedido() {
        pedidoExames.setCodigoPaciente(getCodigoPaciente());
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            pedidoExames.setCodigoServico(codigoServico);
            controllerPedidoExames.SalvarParticular(pedidoExames);
        }
    }

    public void inserirTabela() {
        Object fila[] = new Object[2];
        //    System.out.println("Entrou aqui");
        int codigoServico = controllerServico.getCodigoServico(jComboBox2.getSelectedItem().toString());
        fila[0] = codigoServico;
        fila[1] = jComboBox2.getSelectedItem().toString();
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(470);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

    }

    public void mostrarExames(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("m.idMarcacao"), rs.getString("p.nomeCompleto")});
            }
        } catch (Exception ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public final void mostrarExame(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(170);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(170);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getTableHeader().setReorderingAllowed(false);
            jTable2.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("codigo"), rs.getString("paciente"), rs.getString("seguro"),
                    rs.getString("medico"), rs.getString("estado")

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidoExames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoExames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoExames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoExames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PedidoExame().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
