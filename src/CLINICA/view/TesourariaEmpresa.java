/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerFormaPagamento;
import CLINICA.controller.ControllerFactura;
import CLINICA.controller.ControllerFacturaItens;
import CLINICA.controller.ControllerEmpresa;
import CLINICA.controller.ControllerEstatista;
import GestaoStock.controller.ProdutoController;
import GestaoStock.controller.VendaController;
import GestaoStock.controller.UsuarioController;
import GestaoStock.relatorios.RelatoriosVenda;
import CLINICA.modelo.Factura;
import CLINICA.modelo.FacturaItens;
import CLINICA.modelo.ModeloEstatistica;
import CLINICA.relatorios.RelatorioVenda;
import GestaoStock.controller.EntradaController;
import GestaoStock.modelo.ItemVenda;
import GestaoStock.modelo.Venda;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import CLINICA.controller.ControllerSeguradora;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.sql.PreparedStatement;
import java.util.GregorianCalendar;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Desenvolvimento
 */
public class TesourariaEmpresa extends javax.swing.JFrame {

    /**
     * Creates new form Tesouraria
     */
    Connection con;
    private final DefaultTableModel defaultTableModel;
    Factura factura = new Factura();
    FacturaItens facturaItens = new FacturaItens();
    Venda venda = new Venda();
    ItemVenda itemVenda = new ItemVenda();
    private DecimalFormat decimalformat;
    ControllerUsuario controllerUsuario;
    ControllerFormaPagamento formaPagamento;
    ControllerServico controllerServico;
    ControllerFactura controllerFactura;
    ControllerFacturaItens controllerFacturaItens;
    VendaController vendaController = new VendaController();
    Data d = new Data();
    ControllerEmpresa controllerEmpresa;
    RelatorioVenda relatorioVenda = new RelatorioVenda();
    RelatoriosVenda relatoriosVenda = new RelatoriosVenda();
    double totalApagar = 0;
    int user;
    ControllerUtente utentes;
    ControllerEstatista controllerEstatista = new ControllerEstatista(con);
    ProdutoController produtoController = new ProdutoController();
    UsuarioController usuarioController = new UsuarioController();
    int tipoCodigoArea;
    EntradaController control = new EntradaController();
    DecimalFormat df = new DecimalFormat("#,###.00");
    ControllerSeguradora controllerSeguradora;

    public TesourariaEmpresa(int codigo, int codigoArea) {
        initComponents();
        this.user = codigo;
        System.out.println("User:" + codigo);
        con = new ConexaoBancos().ConexaoBD();
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        iconeSistema();
        this.tipoCodigoArea = codigoArea;
        jLabel13.setVisible(false);
        jTextField5.setVisible(false);
        utentes = new ControllerUtente(con);
        controllerFactura = new ControllerFactura(con);
        controllerFacturaItens = new ControllerFacturaItens(con);
        controllerEmpresa = new ControllerEmpresa(con);
        formaPagamento = new ControllerFormaPagamento(con);
        controllerUsuario = new ControllerUsuario(con);
        controllerServico = new ControllerServico(con);
        controllerSeguradora = new ControllerSeguradora(con);
        jComboBox1.setModel(new DefaultComboBoxModel(controllerUsuario.getNomeporCodigo(user).toArray()));
        jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoFormaPagamento().toArray()));
        jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
        jComboBox5.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeCategoria().toArray()));
        setLocationRelativeTo(null);
        teclaInser();
        teclaInsera();
        teclaInserirQuantidade();
        if (jRadioButton1.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA PRONTO'");
        }
        if (jRadioButton2.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA CRÉDITO'");
        }

    }

    public final void teclaInser() {
        jTextField4.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabela();
                }
            }

        });
    }

    public final void teclaInserirQuantidade() {
        jTextField6.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabela();
                }
            }

        });
    }

    public final void teclaInsera() {
        jComboBox3.addKeyListener(
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

    public void setDesconto(String nome) {
//        jTextFieldDesconto.setText(String.valueOf(utentes.getDescontoUtente(nome)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendarTheme1 = new com.toedter.plaf.JCalendarTheme();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldTotaPagar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURAÇÃO");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel12.setText("Cliente");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 320, 20));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 320, 20));

        jTextField2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel14.setText("Filtrar o nome do Cliente:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-add-32.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 41, -1, 20));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel5.setText("Forma de Pagamento");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 220, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Produtos selecionados na venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Artigo", "Quantidade", "Preço Unitário", "IVA", "Valor a Pagar"
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
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1040, 220));

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jLabel8.setText("Remover");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 110, -1));
        jPanel13.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 300, 30));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel13.setText("Nº Comprovativo Bancário:");
        jPanel13.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 150, 20));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setText("Encomenda nº:");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));
        jPanel13.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 210, 30));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 255, 1040, 50));

        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton5.setText("Salvar ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Total a Pagar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11), new java.awt.Color(204, 0, 0))); // NOI18N

        jTextFieldTotaPagar.setEditable(false);
        jTextFieldTotaPagar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldTotaPagar.setForeground(new java.awt.Color(204, 0, 0));
        jTextFieldTotaPagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTotaPagar.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldTotaPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTextFieldTotaPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "IVA"));

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldDesconto.setText("0.0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("%");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Vendedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, 0, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Escolha o Serviço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel1.setText("Por código");

        jTextField3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel2.setText("Designação");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel4.setText("Qtde.");

        jTextField6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("1");
        jTextField6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField6CaretUpdate(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel6.setText("Serviço");

        jTextField4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField4CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Facturação", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº da Factura", "Caixa", "Paciente", "Data", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 1, true), "Buscar por Data/Nome"));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel7.setText("Data");

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-search-24.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Código"));
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));
        jTextField7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField7CaretUpdate(evt);
            }
        });
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Factura Pronto");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Factura Crédito");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Facturação"));

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-sign-32 (1).png"))); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jButton6.setText("Anular");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-pdf-32 (2).png"))); // NOI18N
        jButton7.setText("Visualizar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo de Papel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jRadioButton5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton5.setText("A5");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jRadioButton7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton7.setSelected(true);
        jRadioButton7.setText("A4");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton5)
                .addComponent(jRadioButton7))
        );

        jButton8.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-pdf-32 (2).png"))); // NOI18N
        jButton8.setText("Créditar a Factura");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista de Factura", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            defaultTableModel.removeRow(jTable1.getSelectedRow());
            troco();
            actualizarValorApagar();
            actualizarValorIva();
            actualizar();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
//        int codigoCliente = utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
//        jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getServico(codigoCliente).toArray()));
//        String desconto = "" + utentes.getDescontoUtente(jComboBox5.getSelectedItem().toString());
//        jTextFieldDesconto.setText(desconto);
//        this.setDesconto(jComboBox5.getSelectedItem().toString());
//        double preco = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
//        actualizar();

    }//GEN-LAST:event_jComboBox5ActionPerformed

    public double totalGeral() {
        double total = 0, d = 0, d1 = 0;
        if (tipoCodigoArea == 1) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                // int codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                int quantidade = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
//                System.out.println("Codigo Servico:" + codigoServico);
                int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
                if (codigoTaxa == 14) {
                    d = Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 3))) * quantidade + Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 3))) * quantidade * 0.14;
                    total += d;
                } else {
                    d = Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 3))) * quantidade;
                    total = total + d;
                }

            }
        }
        if (tipoCodigoArea == 2) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                d = Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 5))) + Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 5))) * 0.14;
                total += d;
            }
        }
//        System.out.println("TOTAL GERAL:" + total);
        return total;

    }

    public double totalDesconto() {
        double desc = 0.0, d = 0.0;
        String letra = jTextFieldDesconto.getText().substring(0, 2);
        double desconto = Double.valueOf(letra);
        desc = (totalGeral() * desconto) / 100;

        return desc;
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int codigoProduto = jTable1.getRowCount();
        if (codigoProduto != 0) {
            if (tipoCodigoArea == 1) {
                salvarFactura();
                salvarItemFactura();
                int codigoFactura = controllerFactura.getLastFactura();
                limparVenda();
                relatorioVenda.getFacturaGuia3(codigoFactura);
            }

            // Ticket
//                    relatorioVenda.getFacturaticket(codigoFactura);
        } else {
            JOptionPane.showMessageDialog(null, "Não existe Produto no carrinho de Compra!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

//    public void salvarEstatistica(int codigoFactura) {
//        ControllerEstatista controllerEstatista = new ControllerEstatista(con);
//        ModeloEstatistica modeloEstatistica = new ModeloEstatistica();
//        modeloEstatistica.setCodigoFactura(String.valueOf(codigoFactura));
//        modeloEstatistica.setCodigoPaciente(getCodigoCliente());
//        controllerEstatista.salvar(modeloEstatistica);
//    }
//
//    public void salvarEstatisticaItens() {
//        ControllerEstatista controllerEstatista = new ControllerEstatista(con);
//        int codigoFactura = controllerEstatista.getLastEstatistica();
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//            ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 1, 0, 0, 0, 0, 0, 0, 0, 0, codigoFactura);
//            controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
//        }
//
//    }
    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        if (tipoCodigoArea == 1) {
            if (!jTextField2.getText().isEmpty()) {
                jComboBox5.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeEmpresa(jTextField2.getText()).toArray()));
            }
        }
        if (tipoCodigoArea == 2) {
            if (!jTextField2.getText().isEmpty()) {
                jComboBox5.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeEmpresa(jTextField2.getText()).toArray()));
            }
        }
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 1) {

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        if (!jTextField3.getText().isEmpty()) {
            int codigo = Integer.parseInt(jTextField3.getText());
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicoporCodigo(codigo).toArray()));
        }
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField6CaretUpdate
        //calculoTotal();
    }//GEN-LAST:event_jTextField6CaretUpdate

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed

    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inserirTabela();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        if (!jTextField4.getText().isEmpty()) {
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicosPorlike(jTextField4.getText()).toArray()));
        }
    }//GEN-LAST:event_jTextField4CaretUpdate

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Seguradoras().setVisible(true);
        jComboBox5.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeCategoria().toArray()));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
            jLabel13.setVisible(true);
            jTextField5.setVisible(true);
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    public String getData() {
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jRadioButton1.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f.dataFactura) ='" + getData() + "' and f.estado ='FACTURA PRONTO' ");
        }
        if (jRadioButton2.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f.dataFactura) ='" + getData() + "' and f.estado ='FACTURA CRÉDITO' ");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            int codigo = Integer.parseInt(jTextField1.getText());
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura , f.nomeclientes , u.nomecompleto, f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where f.idfactura like '" + codigo + "%'");
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField7CaretUpdate

        if (jRadioButton1.isSelected()) {
            if (!jTextField2.getText().isEmpty()) {
                mostraFactura("SELECT f.idfactura, date(f. dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                        + "on u.idUtilizador = f.codigoUtilizador where f.nomeclientes like '" + jTextField2.getText() + "%' AND f.estado ='FACTURA PRONTO'");
            }
        }
        if (jRadioButton2.isSelected()) {
            if (!jTextField2.getText().isEmpty()) {
                mostraFactura("SELECT f.idfactura, date(f. dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                        + "on u.idUtilizador = f.codigoUtilizador where f.nomeclientes like '" + jTextField2.getText() + "%' AND f.estado ='FACTURA CRÉDITO'");
            }
        }

    }//GEN-LAST:event_jTextField7CaretUpdate

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jButton5.setEnabled(false);
        if (jRadioButton1.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA PRONTO'");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jButton5.setEnabled(true);
        if (jRadioButton2.isSelected()) {
            mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                    + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA CRÉDITO'");
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int codigo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        controllerFactura.getAnularFactura(codigo);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int linha = jTable2.getSelectedRow();
        if (linha != -1) {
            String estado = jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString();
            if (jRadioButton5.isSelected()) {
                // Visualizar Factura A5
                if (estado.equals("FACTURA PRONTO")) {
                    System.out.println("Teste:" + jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
                    int codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    relatorioVenda.getFactura(codigoFactura);
                }
                // Factura a Crédito
                if (estado.equals("FACTURA CRÉDITO") || estado.equals("COTAÇÃO")) {
                    System.out.println("Teste de Vida:" + jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
                    int codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    RelatorioVenda relatorio = new RelatorioVenda();
                    relatorio.getFactura1(codigoFactura);
                }
            }
            if (jRadioButton7.isSelected()) {
                // Visualizar Factura A4
                if (estado.equals("FACTURA PRONTO")) {
                    System.out.println("Teste de Vida:" + jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
                    int codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    RelatorioVenda relatorio = new RelatorioVenda();
                    relatorioVenda.getFacturaGuia3(codigoFactura);
                }
                if (estado.equals("FACTURA CRÉDITO")) {
                    System.out.println("Teste de Vida:" + jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
                    int codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
                    RelatorioVenda relatorio = new RelatorioVenda();
                    relatorioVenda.getFacturaGuia2(codigoFactura);
                }

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma factura Desejada!");
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            int codigoFactura = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            if (jRadioButton2.isSelected()) {
                double valor = controllerFactura.getvalorApagar(codigoFactura);
                System.out.println("Teste:" + valor);
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja Pagar a Dinheiro?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    // Pagamento Numerário
                    controllerFactura.updateCrédito(codigoFactura, valor, 0, 1);
                    RelatorioVenda relatorio = new RelatorioVenda();
                    relatorioVenda.getFacturaGuia1(codigoFactura);
                    mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                            + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA CRÉDITO'");

                } else if (resposta == JOptionPane.NO_OPTION) {
                    int teste = JOptionPane.showConfirmDialog(null, "Deseja Pagar a Multicaixa?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (teste == JOptionPane.YES_OPTION) {
                        // Pagamento Multicaixa
                        controllerFactura.updateCrédito(codigoFactura, 0, valor, 2);
                        RelatorioVenda relatorio = new RelatorioVenda();
                        relatorioVenda.getFacturaGuia1(codigoFactura);
                        mostraFactura("SELECT f.idfactura, date(f.dataFactura) as dataFactura, f.nomeclientes , u.nomecompleto,f.estado from factura f inner join utilizadores u\n"
                                + "on u.idUtilizador = f.codigoUtilizador where date(f. dataFactura) = current_date and f.estado = 'FACTURA CRÉDITO'");
                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Deve selecionar a factura Desejada que pretende liquidar");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    public void actualizarValorApagar() {

        double valor, valorTotal = 0;
        double valorIVA = 0;
        double troco = 0;
        if (tipoCodigoArea == 1) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
                if (codigoTaxa == 14) {
                    valor = Double.valueOf(jTable1.getValueAt(i, 3).toString()) + Double.valueOf(jTable1.getValueAt(i, 5).toString()) * 0.14;
                    valorTotal = valorTotal + valor;
//                    System.out.println("Total:" + valorTotal);
                    jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
                    troco = Double.parseDouble(getTroco());
                } else {
                    valor = Double.valueOf(jTable1.getValueAt(i, 3).toString());
                    valorTotal = valorTotal + valor;
                    System.out.println("Total:" + valorTotal);
                    jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
                    troco = Double.parseDouble(getTroco());
                }

            }
        }
        if (tipoCodigoArea == 2) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                valor = Double.valueOf(jTable1.getValueAt(i, 5).toString()) + Double.valueOf(jTable1.getValueAt(i, 5).toString()) * 0.14;
                valorTotal = valorTotal + valor;
                System.out.println("Total:" + valorTotal);
                jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
                troco = Double.parseDouble(getTroco());
            }
        }

    }

    public void actualizarValorIva() {

        double valor, valorTotal = 0;
        String troco = null;
        if (tipoCodigoArea == 2) {
            int codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                valor = Double.valueOf(jTable1.getValueAt(i, 3).toString());
                valorTotal = valorTotal + valor;
                jTextFieldDesconto.setText(String.valueOf(valorTotal * 0.14));
                //  troco = String.valueOf(getDesconto());
            }
        }
        if (tipoCodigoArea == 1) {
//            System.out.println("Chegou aqui");
            if (jTable1.getRowCount() != 0) {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                    int quantidade = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
                    int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
                    if (codigoTaxa == 14) {
                        valor = Double.valueOf(jTable1.getValueAt(i, 3).toString()) * 14 * quantidade;
                        valorTotal = valorTotal + valor;
//                        System.out.println("Teste Desconto:" + valorTotal);
                        jTextFieldDesconto.setText(String.valueOf(valorTotal / 100));
                        troco = String.valueOf(getDesconto());
                        // Por analizar
                    } else {
                        jTextFieldDesconto.setText("0.0");
                        //    jTextField3.setText("0.0");
                        //     jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
                    }
                }

            } else {
                // Por analizar
                //  jTextField3.setText("0.0");
                //     jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
            }
        }

    }

    public double getDescontoPersentagem() {
        double percet = 0;
        try {
            if (!jTextFieldDesconto.getText().equals("")) {
                double desconto = Double.parseDouble(jTextFieldDesconto.getText());
                if (desconto != 0) {
                    desconto = (desconto / 100);
                    percet = (double) (getValorApagar() * desconto);
                }
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return percet;
    }

    public double getValorApagar() {
        double valor = 0;
        try {
            if (!jTextFieldTotaPagar.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldTotaPagar.getText());
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public double getValorEntregue() {
        double valor = 0;
//        try {
//            if (!jTextFieldValorEntregue.getText().equals("")) {
//                valor = Double.parseDouble(jTextFieldValorEntregue.getText());
//            }
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
        return valor;
    }

    public int getCodigoUtilizador() {
        return controllerUsuario.getCodigoUtilizador(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoFormaPagamento() {
        return 1;//formaPagamento.getCodigoFormaPagamento(jComboBox2.getSelectedItem().toString());
    }

//    public int getCodigoCliente() {
//        return utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
//    }
    public int getCodigoCliente() {
        return controllerSeguradora.getCodigoSeguradora(jComboBox5.getSelectedItem().toString());
    }

    public void inserirTabela() {
        Object fila[] = new Object[6];
        int codigoServico;
        double preco = 0.0;
        String IVA = null;
        double totalPreco = 0.0;
        // Gestão Hospitalar
        int quantidade = Integer.parseInt(jTextField6.getText());
        if (tipoCodigoArea == 1) {
            codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
            if (codigoTaxa == 14) {
                IVA = "14%";
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                fila[0] = codigoServico;
            } else {
                IVA = "0%";
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade;
                fila[0] = codigoServico;
            }

        }
        // Gestão de Stock
        if (tipoCodigoArea == 2) {
            try {
                IVA = "14%";
                String codigoBarra = produtoController.codBarras(jComboBox3.getSelectedItem().toString());
                preco = produtoController.getPrecoUnitario(Integer.parseInt(codigoBarra));
                fila[0] = codigoBarra;

            } catch (SQLException ex) {
                Logger.getLogger(TesourariaEmpresa.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        fila[1] = jComboBox3.getSelectedItem().toString();
        fila[2] = quantidade;
        fila[3] = preco;
        fila[4] = IVA;
        fila[5] = totalPreco;
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(130);
        jTable1.getColumnModel().getColumn(3).setResizable(!false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        actualizarValorIva();
        actualizarValorApagar();
        actualizar();

    }

    public int getQuantidadeGeral() {
        int total = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int quant = Integer.parseInt((jTable1.getValueAt(i, 2).toString()));
            total = total + quant;
        }
        return total;
    }

    public double getDesconto() {
        double valor = 0;
//        try {
//            if (!jTextFieldDesconto.getText().equals("")) {
//                valor = Double.parseDouble(jTextFieldDesconto.getText());
////                System.out.println("Valor do Desconto:" + valor);
//            } else {
//                jTextFieldDesconto.setText("0");
//            }
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
        return valor;
    }

    public double getValorEntregueMulticaixa() {
        double valor = 0;
//        try {
//            if (!jTextFieldMulticaixa.getText().equals("")) {
//                valor = Double.parseDouble(jTextFieldMulticaixa.getText());
//            }
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
        return valor;
    }

    public void troco() {
//        try {
//            double troco = ((getValorEntregue() + getValorEntregueMulticaixa()) - (getValorApagar() - getDescontoPersentagem()));
//            jTextFieldTroco.setText("" + troco);
////            Extenso ext = new Extenso(new BigDecimal(getTotalApagar()));
////            lbExtenso.setText(ext.toString());
//        } catch (NumberFormatException ex) {
//        }
    }

    public String getTroco() {
//        try {
//            double troco = ((getValorEntregue() + getValorEntregueMulticaixa()) - (getValorApagar() - getDescontoPersentagem()));
//            jTextFieldTroco.setText("" + troco);
//        } catch (NumberFormatException ex) {
//        }
        return "" + 0;
    }

    public double getTrocoReceber() {
        double valor = 0;
//        try {
//            if (!jTextFieldTroco.getText().equals("")) {
//                valor = Double.parseDouble(jTextFieldTroco.getText());
//            }
//
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
        return valor;
    }

    public void salvarFactura() {
        factura.setCodigoCliente(getCodigoCliente());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setTroco(getTrocoReceber());
        factura.setDesconto(0.0);
        factura.setTotalFactura(totalGeral());
        factura.setNomeCliente(jComboBox5.getSelectedItem().toString());
        factura.setQuantidadeItens(getQuantidadeGeral());
        if (jComboBox2.getSelectedItem().equals("NUMERARIO")) {
            factura.setValorMulticaixa(0.0);
            factura.setValorNumerario(getValorApagar());
        }
        if (jComboBox2.getSelectedItem().equals("MULTICAIXA")) {
            factura.setValorMulticaixa(getValorApagar());
            factura.setValorNumerario(0.0);
        }
        if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
            factura.setValorMulticaixa(getValorApagar());
            factura.setValorNumerario(0.0);
        }
        factura.setValorApagar(getValorApagar());
        factura.setnEcomenda(jTextField8.getText());
        factura.setVaorporExtenso("");
        factura.setCodigoSeguro(getCodigoCliente());
        factura.setDescontoIVA(getDesconto());
        factura.setnIBAN(jTextField5.getText());
        factura.setCodigoFormaPagamento(getCodigoFormaPagamento());
        factura.setEstado("FACTURA PRONTO");
        try {
            controllerFactura.salvarEmpresa(factura);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TesourariaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(TesourariaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(TesourariaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public final String getHora() {
        String hora;
        Calendar ne = Calendar.getInstance();
        return String.format("%1$tH:%1$tM:%1$tS", ne);

    }

//    public void salvarFacturaStock() throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        FormapagamentoController forma = new FormapagamentoController();
//        venda.setCodBeneficiario(getCodigoCliente());
//        venda.setFormaPagamento(forma.getCodigoFormaPagamento(jComboBox2.getSelectedItem().toString()));
//        venda.setCodUtilizador(usuarioController.getCodigoUtilizador1(jComboBox1.getSelectedItem().toString()));
//        venda.setTroco(getTrocoReceber());
//        venda.setNomeCliente(jComboBox5.getSelectedItem().toString());
//        venda.setQuantidadeItens(getQuantidadeGeral());
//        venda.setMulticaixa(getValorEntregueMulticaixa());
//        venda.setNumerario(getValorEntregue());
//        venda.setValorTotal(getValorEntregue() + getValorEntregueMulticaixa());
//        venda.setValorporExtenso("");
//        venda.setDesconto(getTotalIva());
//        venda.setHora(getHora());
//        vendaController.salvarVenda(venda);
//
//    }
    public double getTotalIva() {
        double d = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            d = d + Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 5))) * 0.14;
        }
        return d;
    }

    public void salvarItemFactura() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerFactura.getLastFactura();
            //  System.out.println("Codigo da Factura:" + codigoFactura);
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            if (codigoTaxa == 14) {
                facturaItens.setDescontoIVA(valorUnitario * 0.14);
            } else {

                facturaItens.setDescontoIVA(valorUnitario * 0);
            }
//            System.out.println("Teste:" + valorUnitario);
            facturaItens.setCodigoFactura(codigoFactura);
            facturaItens.setCodigoServico(codigoProduto);
            facturaItens.setCodigoCategoriaServico(codigoCategoria);
            facturaItens.setQuantidade(quantidadde);
            if (jComboBox2.getSelectedItem().equals("NUMERARIO")) {
                facturaItens.setTotalGeral(valorUnitario);
                facturaItens.setTotalTPA(0.0);
            }
            if (jComboBox2.getSelectedItem().equals("MULTICAIXA")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(valorUnitario);
            }
            if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(valorUnitario);
            }
            if (jComboBox2.getSelectedItem().equals("CRÉDITO")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(0.0);
            }
            if (jComboBox2.getSelectedItem().equals("COTACAO")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(0.0);
            }
            controllerFacturaItens.salvarItens(facturaItens);
            if (controllerServico.getProdutoEstocavel(jTable1.getValueAt(i, 1).toString())) {
                try {
                    control.diminuirEstoque(codigoProduto, quantidadde);

                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    Logger.getLogger(Servico.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

//    public void salvarFacturaItensStock() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        double d = 0;
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            d = d + Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 5))) * 0.14;
//            int codigoFactura = vendaController.getLastVenda();
////            System.out.println("Codigo da Factura:" + codigoFactura);
//            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
//            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
//            System.out.println("Teste:" + valorUnitario);
//            itemVenda.setCodVenda(codigoFactura);
//            itemVenda.setCodProduto(codigoProduto);
//            itemVenda.setQtdItem(quantidadde);
//            itemVenda.setDescontoIVa(d);
//
//            if (jComboBox2.getSelectedItem().equals("NUMERARIO")) {
//                itemVenda.setValorUnitario(valorUnitario);
//                itemVenda.setTotalTPA(0.0);
//            } else {
//                itemVenda.setValorUnitario(0.0);
//                itemVenda.setTotalTPA(valorUnitario);
//            }
//
//            vendaController.salvarItemVenda(itemVenda);
//
//        }
//    }
    public void calculoTotal() {
        //double antes = removerPattern(jTextField5.getText());
//        // double valor_unitario = antes;
//        double valor_unitario = 0;
//        double qtd = 0;
//        if (jTextField6.getText().isEmpty()) {
//            qtd = 1;
//        } else {
//            qtd = Double.parseDouble(jTextField6.getText());
//        }
//        double total = qtd * valor_unitario;
        //  jTextField7.setText(decimalformat.format(total));
    }

    public void actualizar() {
        if (tipoCodigoArea == 1) {
//            System.out.println("desconto" + totalDesconto());
            BigDecimal res2 = new BigDecimal((totalGeral()));
            // BigDecimal res2 = new BigDecimal((totalGeral() - totalDesconto()));
            jTextFieldTotaPagar.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));
//        jLabel7.setText("" + (jTable1.getRowCount()));
            //  calculoTroco();
            actualizarValorIva();
        }
        if (tipoCodigoArea == 2) {
//            System.out.println("desconto" + totalDesconto());
            BigDecimal res2 = new BigDecimal((totalGeral()));
            jTextFieldTotaPagar.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));
//        jLabel7.setText("" + (jTable1.getRowCount()));
            //    calculoTroco();
        }
    }

//    public void calculoTroco() {
//        double total_antes = removerPattern(jTextFieldTotaPagar.getText());
//        double entregue_antes = 0;
//        double entregue = total_antes;
//        double troco = 0;
//        if (jTextFieldValorEntregue.getText() == "0") {
//            troco = -(total_antes);
//        } else {
//            if (jTextFieldValorEntregue.getText().isEmpty()) {
//                entregue_antes = 0;
//            } else {
//                entregue_antes = Double.parseDouble(jTextFieldValorEntregue.getText());
//            }
//            troco = entregue_antes - total_antes;
//        }
//
////        jTextFieldTroco.setText(decimalformat.format(troco));
//    }
//    public Double removerPattern(String valor_recebido) {
//        String valor = valor_recebido.replaceAll("\\.", "").replace(",", ".");
//        return Double.parseDouble(valor);
//    }
    public void limparVenda() {
        jTextFieldTotaPagar.setText("0");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField8.setText("");
        jTextField6.setText("1");
        jTextFieldDesconto.setText("0");
        defaultTableModel.setRowCount(0);
    }

    public final void mostraFactura(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(85);
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("f.idfactura"), rs.getString("u.nomecompleto"), rs.getString("f.nomeclientes"), rs.getString("datafactura"), rs.getString("f.estado")
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
            java.util.logging.Logger.getLogger(TesourariaEmpresa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesourariaEmpresa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesourariaEmpresa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesourariaEmpresa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  new Tesouraria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.plaf.JCalendarTheme jCalendarTheme1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldTotaPagar;
    // End of variables declaration//GEN-END:variables

}
