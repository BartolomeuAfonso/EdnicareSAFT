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
import CLINICA.controller.ControllerParametros;
import GestaoStock.controller.ProdutoController;
import GestaoStock.controller.VendaController;
import GestaoStock.controller.UsuarioController;
import GestaoStock.controller.FormapagamentoController;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Desenvolvimento
 */
public class Tesouraria extends javax.swing.JFrame {

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
    JPasswordField dados = new JPasswordField();
    ControllerUtente utentes;
    ControllerEstatista controllerEstatista = new ControllerEstatista(con);
    ProdutoController produtoController = new ProdutoController();
    UsuarioController usuarioController = new UsuarioController();
    ControllerParametros controllerParametros;
    int tipoCodigoArea;
    EntradaController control = new EntradaController();
    DecimalFormat df = new DecimalFormat("#,###.00");
    int codigoTipoUtilizador;

    public Tesouraria(int codigo, int codigoArea) {
        initComponents();
        this.user = codigo;
        System.out.println("User:" + codigo);
        con = new ConexaoBancos().ConexaoBD();
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        iconeSistema();
        this.tipoCodigoArea = codigoArea;
        jTextFieldTroco.setVisible(false);
        //   jLabel9.setVisible(false);
        jTextField8.setText("0.0");
//        jLabel13.setVisible(false);
//        jTextField5.setVisible(false);
        if (tipoCodigoArea == 1) {
            // Gestão Hospitalar
            utentes = new ControllerUtente(con);
            controllerParametros = new ControllerParametros(con);

            controllerFactura = new ControllerFactura(con);
            controllerFacturaItens = new ControllerFacturaItens(con);
            controllerEmpresa = new ControllerEmpresa(con);
            formaPagamento = new ControllerFormaPagamento(con);
            controllerUsuario = new ControllerUsuario(con);
            controllerServico = new ControllerServico(con);

            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioUltimoParticular().toArray()));
            int codigoCliente = utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
            jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoFormaPagamento().toArray()));
            jComboBox1.setModel(new DefaultComboBoxModel(controllerUsuario.getNomeporCodigo(user).toArray()));
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));

        }
        codigoTipoUtilizador = controllerUsuario.getTipoUtilizadorporID(user);
        if (codigoTipoUtilizador == 4) {
            jLabel15.setVisible(false);
            jTextField7.setVisible(false);
            //  jLabel16.setVisible(false);
            jTextField8.setVisible(false);
            jTextField8.setText("0.0");
        }
        if (codigoTipoUtilizador == 9) {
            jLabel15.setVisible(false);
            jTextField7.setVisible(false);
            //   jLabel16.setVisible(false);
            jTextField8.setVisible(false);
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getPacienteDiveros().toArray()));
            jTextField8.setText("0.0");
        }
        jTextField3.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    getInserirProduto(jTextField3.getText().trim());
                }
            }
        });

        addKeyListener(new LeitorTeclas());
        teclaInser();
        teclaInser1();
//        teclaInseri();
        teclaInserQuantidade();
        teclaImprimirNumerario();
        teclaImprimirMulticaixa();
        setLocationRelativeTo(null);
    }

    public void getInserirProduto(String codigoBarra) {

        if (!codigoBarra.isEmpty()) {
            int codigoProduto = controllerServico.getCodigoServicoCodigoBarra(codigoBarra);
            String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
            int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
            if (tipoCodigoArea == 1) {
                if (codigoCategoria.equals("SIM")) {
                    int quantidade = controllerServico.getQuantidadeProdutoCodigoBarra(codigoBarra);
                    System.out.println("Quandidade Existente:" + quantidade);
                    System.out.println("Quandidade Sair:" + quantidadeEntrada);
                    if (quantidadeEntrada <= quantidade) {
                        inserirTabelaAdd(codigoBarra);
                        jTextField3.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                    }

                } else {
                    inserirTabelaAdd(codigoBarra);
                    jTextField3.setText("");
                }

            }
        }

    }

    public final void iconeSistema() {
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public void setDesconto(String nome) {
        jTextFieldDesconto.setText(String.valueOf(utentes.getDescontoUtente(nome)));
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldTroco = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jTextFieldMulticaixa = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldValorEntregue = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldTotaPagar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURAÇÃO");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Utente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel11.setText("Por código");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel12.setText("Nome do(a) Cliente Cadastrado :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 20));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 320, 30));

        jTextField2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 210, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel14.setText("Filtrar o nome do(a) Cliente:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 170, -1));

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));

        jButton8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cash-register-32.png"))); // NOI18N
        jButton8.setText("Fecho ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-invoice-filled-32.png"))); // NOI18N
        jButton9.setText("Re-imprimir ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel2.setText("Designação:");

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

        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox3.setLightWeightPopupEnabled(false);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel5.setText("Forma de Pagamento");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel6.setText("Buscar designação");

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGap(0, 9, Short.MAX_VALUE))
        );

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1040, 220));

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 470, 30));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jLabel9.setText(" Remover Serviço");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });
        jPanel13.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 151, -1));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 1040, 30));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel15.setText("Encomenda nº:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 110, -1));
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 200, 20));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setText("Aplicar Desconto");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, 100, -1));
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 250, 200, 20));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Troco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11), new java.awt.Color(0, 102, 255))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(140, 47));

        jTextFieldTroco.setEditable(false);
        jTextFieldTroco.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldTroco.setForeground(new java.awt.Color(0, 102, 255));
        jTextFieldTroco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTroco.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldTroco, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-invoice-filled-32.png"))); // NOI18N
        jButton6.setText("Proforma (F6)");
        jButton6.setEnabled(false);

        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton5.setText("Salvar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Multicaixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jTextFieldMulticaixa.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldMulticaixa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldMulticaixa.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldMulticaixa, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jTextFieldMulticaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Númerario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jTextFieldValorEntregue.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldValorEntregue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldValorEntregue.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldValorEntregue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldValorEntregue, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
            .addComponent(jTextFieldTotaPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Vendedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton1.setText("Factura Crédito");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo de Papel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton5.setText("A5");

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton6.setSelected(true);
        jRadioButton6.setText("Ticket");

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton7.setText("A4");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jRadioButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new Reemprimir(getCodigoUtilizador()).setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField6CaretUpdate
        calculoTotal();
    }//GEN-LAST:event_jTextField6CaretUpdate

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed

    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Gestão Hospital
        int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
        //int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
        String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
        int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
        if (tipoCodigoArea == 1) {
            if (codigoCategoria.equals("SIM")) {
                int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
                System.out.println("Quandidade Existente:" + quantidade);
                System.out.println("Quandidade Sair:" + quantidadeEntrada);
                if (quantidadeEntrada <= quantidade) {
                    inserirTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                }

            } else {
                inserirTabela();
            }

        }
        // gestão de Stock
        if (tipoCodigoArea == 2) {
            if (codigoCategoria.equals("SIM")) {
                inserirTabela();
            } else {
                inserirTabela();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            defaultTableModel.removeRow(jTable1.getSelectedRow());
//            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
            troco();
            calculoTroco();
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

    public final void teclaInser() {
        jTextField4.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
                    int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
                    String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
                    if (tipoCodigoArea == 1) {
                        if (codigoCategoria.equals("SIM")) {
                            int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
                            if (quantidadeEntrada <= quantidade) {
                                inserirTabela();
                            } else {
                                JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                            }

                        } else {
                            inserirTabela();
                        }

                    }
                    // gestão de Stock
                    if (tipoCodigoArea == 2) {
                        if (codigoCategoria.equals("SIM")) {
                            inserirTabela();
                        } else {
                            inserirTabela();
                        }
                    }
                }
            }

        });
    }

    public final void teclaInser1() {
        jComboBox3.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
                    int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
                    String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
                    if (tipoCodigoArea == 1) {
                        if (codigoCategoria.equals("SIM")) {
                            int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
                            if (quantidadeEntrada <= quantidade) {
                                inserirTabela();
                            } else {
                                JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                            }

                        } else {
                            inserirTabela();
                        }

                    }
                    // gestão de Stock
                    if (tipoCodigoArea == 2) {
                        if (codigoCategoria.equals("SIM")) {
                            inserirTabela();
                        } else {
                            inserirTabela();
                        }
                    }
                }
            }

        });
    }

//    public final void teclaInseri() {
//        jTextField3.addKeyListener(
//                new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent evt) {
//                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                    int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
//                    String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
//                    if (tipoCodigoArea == 1) {
//                        if (codigoCategoria.equals("SIM")) {
//                            int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
//                            if (quantidade > Integer.parseInt(jTextField6.getText())) {
//                                inserirTabelaAdd();
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
//                            }
//
//                        } else {
//                            inserirTabelaAdd();
//                            jTextField3.setText("");
//                        }
//
//                    }
//                }
//            }
//        });
//    }
//    public void addByBarcode(String barcode) {
//        int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
//        String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
//        if (tipoCodigoArea == 1) {
//            if (codigoCategoria.equals("SIM")) {
//                int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
//                if (quantidade > Integer.parseInt(jTextField6.getText())) {
//                    inserirTabela();
//                  
//                } else {
//                    JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
//                }
//
//            }
//        }
//    }
    public final void teclaInserQuantidade() {
        jTextField6.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
                    String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
                    int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
                    if (tipoCodigoArea == 1) {
                        if (codigoCategoria.equals("SIM")) {
                            int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
                            System.out.println("Quandidade Existente:" + quantidade);
                            System.out.println("Quandidade Sair:" + quantidadeEntrada);
                            if (quantidadeEntrada <= quantidade) {
                                inserirTabela();
                            } else {
                                JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                            }

                        } else {
                            inserirTabela();
                        }

                    }
                    // gestão de Stock
                    if (tipoCodigoArea == 2) {
                        if (codigoCategoria.equals("SIM")) {
                            inserirTabela();
                        } else {
                            inserirTabela();
                        }
                    }
                    // Para o código de Barra
//                    jTextField4.requestFocus();
//                    jTextField4.setText("A");
                }
            }

        });
    }

    public final void teclaImprimirNumerario() {
        jTextFieldValorEntregue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (tipoCodigoArea == 1) {
                        if (jRadioButton1.isSelected()) {
                            salvarFactura();
                            salvarItemFactura();
                            int codigoFactura = controllerFactura.getLastFactura();
                            // Salvar Estatistica
//                            salvarEstatistica(codigoFactura);
//                            salvarEstatisticaItens();
                            limparVenda();
                            relatorioVenda.getFactura1(codigoFactura);
                        } else {
                            if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
                                salvarFactura();
                                salvarItemFactura();
                                int codigoFactura = controllerFactura.getLastFactura();
                                // Salvar Estatistica
//                                salvarEstatistica(codigoFactura);
//                                salvarEstatisticaItens();
                                limparVenda();
                                relatorioVenda.getFactura(codigoFactura);
                            } else {
                                JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }

                }
            }
        });
    }

    public final void teclaImprimirMulticaixa() {
        jTextFieldMulticaixa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (tipoCodigoArea == 1) {
                        if (jRadioButton1.isSelected()) {
                            salvarFactura();
                            salvarItemFactura();
                            int codigoFactura = controllerFactura.getLastFactura();
                            // Salvar Estatistica
//                            salvarEstatistica(codigoFactura);
//                            salvarEstatisticaItens();
                            limparVenda();
                            relatorioVenda.getFactura1(codigoFactura);
                        } else {
                            if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
                                salvarFactura();
                                salvarItemFactura();
                                int codigoFactura = controllerFactura.getLastFactura();
                                // Salvar Estatistica
////                                salvarEstatistica(codigoFactura);
////                                salvarEstatisticaItens();
                                limparVenda();
                                relatorioVenda.getFactura(codigoFactura);
                            } else {
                                JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
//                    if (tipoCodigoArea == 2) {
//                        if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
//                            try {
//                                salvarFacturaStock();
//                                salvarFacturaItensStock();
//                                int codigoFactura = vendaController.getLastVenda();
////                    salvarEstatistica(codigoFactura);
////                    salvarEstatisticaItens();
//                                limparVenda();
//                                relatoriosVenda.getFactura(codigoFactura);
//                            } catch (SQLException ex) {
//                                Logger.getLogger(Tesouraria.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (NoSuchAlgorithmException ex) {
//                                Logger.getLogger(Tesouraria.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (UnsupportedEncodingException ex) {
//                                Logger.getLogger(Tesouraria.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
//
//                    }
                }
            }

        });
    }

    public void actualizar() {
        if (tipoCodigoArea == 1) {
//            System.out.println("desconto" + totalDesconto());
            BigDecimal res2 = new BigDecimal((totalGeral()));
            // BigDecimal res2 = new BigDecimal((totalGeral() - totalDesconto()));
            jTextFieldTotaPagar.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));
//        jLabel7.setText("" + (jTable1.getRowCount()));
            calculoTroco();
            actualizarValorIva();
        }
        if (tipoCodigoArea == 2) {
//            System.out.println("desconto" + totalDesconto());
            BigDecimal res2 = new BigDecimal((totalGeral()));
            jTextFieldTotaPagar.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));
//        jLabel7.setText("" + (jTable1.getRowCount()));
            calculoTroco();
        }
    }

    public void calculoTroco() {
//  l8      double total_antes = removerPattern(jTextFieldTotaPagar.getText());
        double total_antes = Double.parseDouble(jTextFieldTotaPagar.getText());
        double entregue_antes = 0;
        double entregue = total_antes;
        double troco = 0;
        if ("0".equals(jTextFieldValorEntregue.getText())) {
            troco = -(total_antes);
        } else {
            if (jTextFieldValorEntregue.getText().isEmpty()) {
                entregue_antes = 0;
            } else {
                entregue_antes = Double.parseDouble(jTextFieldValorEntregue.getText());
            }
            troco = entregue_antes - total_antes;
        }

//        jTextFieldTroco.setText(decimalformat.format(troco));
        BigDecimal res2 = new BigDecimal(troco);
        jTextFieldTroco.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));

    }

    public void calculoTroco1() {
//  l8      double total_antes = removerPattern(jTextFieldTotaPagar.getText());
        double total_antes = Double.parseDouble(jTextFieldTotaPagar.getText());
        double entregue_antes = 0;
        double entregue = total_antes;
        double troco = 0;
        if ("0".equals(jTextFieldMulticaixa.getText())) {
            troco = -(total_antes);
        } else {
            if (jTextFieldMulticaixa.getText().isEmpty()) {
                entregue_antes = 0;
            } else {
                entregue_antes = Double.parseDouble(jTextFieldMulticaixa.getText());
            }
            troco = entregue_antes - total_antes;
        }

//        jTextFieldTroco.setText(decimalformat.format(troco));
        BigDecimal res2 = new BigDecimal(troco);
        jTextFieldTroco.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));

    }

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
                if (jRadioButton1.isSelected()) {
                    salvarFactura();
                    salvarItemFactura();
                    int codigoFactura = controllerFactura.getLastFactura();
                    // Salvar Estatistica
                    salvarEstatistica(codigoFactura);
                    salvarEstatisticaItens();
                    limparVenda();
                    if (jRadioButton5.isSelected()) {
                        // Factura A5
                        relatorioVenda.getFactura1(codigoFactura);
                    }
                    if (jRadioButton7.isSelected()) {
                        // Factura A4
                        relatorioVenda.getFacturaGuia2(codigoFactura);
                    }

                } else {
                    if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
                        salvarFactura();
                        salvarItemFactura();
                        int codigoFactura = controllerFactura.getLastFactura();
                        salvarEstatistica(codigoFactura);
                        salvarEstatisticaItens();
                        limparVenda();

                        int quantidade = controllerParametros.getValorImpressao();
                        if (jRadioButton6.isSelected()) {
                            // Factura Ticket
                            for (int i = 0; i < quantidade; i++) {
                                relatorioVenda.getFacturaticket(codigoFactura);
                            }

                        }
                        if (jRadioButton5.isSelected()) {
                            // Factura A5
                            relatorioVenda.getFactura(codigoFactura);
                        }
                        if (jRadioButton7.isSelected()) {
                            // Factura A4
                            relatorioVenda.getFacturaGuia1(codigoFactura);
                        }

                        // Ticket
//                    relatorioVenda.getFacturaticket(codigoFactura);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
            if (tipoCodigoArea == 2) {
                if (jRadioButton1.isSelected()) {
                    salvarFactura();
                    salvarItemFactura();
                    int codigoFactura = controllerFactura.getLastFactura();
//                    System.out.println("Codigo da Factura:" + codigoFactura);
                    // Salvar Estatistica
//                salvarEstatistica(codigoFactura);
//                salvarEstatisticaItens();
                    limparVenda();
                    if (jRadioButton5.isSelected()) {
                        // Factura A5
                        relatorioVenda.getFactura1(codigoFactura);
                    }
                    if (jRadioButton7.isSelected()) {
                        // Factura A4
                        relatorioVenda.getFacturaGuia2(codigoFactura);
                    }

                } else {
                    if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {

                        salvarFactura();
                        salvarItemFactura();
                        int codigoFactura = controllerFactura.getLastFactura();
                        // Salvar Estatistica
//                    salvarEstatistica(codigoFactura);
//                    salvarEstatisticaItens();
                        limparVenda();
                        if (jRadioButton6.isSelected()) {
                            // Factura Ticket
                            int quantidade = controllerParametros.getValorImpressao();
                            for (int i = 0; i < quantidade; i++) {
                                relatorioVenda.getFacturaticket(codigoFactura);
                            }

                        }
                        if (jRadioButton5.isSelected()) {
                            // Factura A5
                            relatorioVenda.getFactura(codigoFactura);
                        }
                        if (jRadioButton7.isSelected()) {
                            // Factura A4
                            relatorioVenda.getFacturaGuia1(codigoFactura);
                        }

                        // Ticket
//                    relatorioVenda.getFacturaticket(codigoFactura);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não existe Produto no carrinho de Compra!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        jTextField8.setText("0.0");
        jTextField8.setVisible(false);

    }//GEN-LAST:event_jButton5ActionPerformed

    public void salvarEstatistica(int codigoFactura) {
        ControllerEstatista controllerEstatista = new ControllerEstatista(con);
        ModeloEstatistica modeloEstatistica = new ModeloEstatistica();
        modeloEstatistica.setCodigoFactura(String.valueOf(codigoFactura));
        modeloEstatistica.setCodigoPaciente(getCodigoCliente());
        controllerEstatista.salvar(modeloEstatistica);
    }

    public void salvarEstatisticaItens() {
        ControllerEstatista controllerEstatista = new ControllerEstatista(con);
        int codigoFactura = controllerEstatista.getLastEstatistica();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 1, 0, 0, 0, 0, 0, 0, 0, 0, codigoFactura);
            controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
        }

    }
    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
//        if (tipoCodigoArea == 1) {
//            if (!jTextField3.getText().isEmpty()) {
//                //    int codigo = Integer.parseInt(jTextField3.getText());
//                jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicoporCodigoBarra(jTextField3.getText()).toArray()));
//            }
//        }
//        if (tipoCodigoArea == 2) {
//            if (!jTextField3.getText().isEmpty()) {
//                int codigo = Integer.parseInt(jTextField3.getText());
//                jComboBox3.setModel(new DefaultComboBoxModel(produtoController.getNomeServicoporCodigoBarra(jTextField3.getText()).toArray()));
//            }
//        }
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        if (tipoCodigoArea == 1) {
            if (!jTextField4.getText().isEmpty()) {
                jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicosPorlike(jTextField4.getText()).toArray()));
                jLabel8.setText("PREÇO UNITÁRIO:" + controllerServico.getNomeServicosPorlikePreco(jTextField4.getText()));
            }
        }
        if (tipoCodigoArea == 2) {
            if (!jTextField4.getText().isEmpty()) {
                jComboBox3.setModel(new DefaultComboBoxModel(produtoController.getNomeServicosPorlike(jTextField4.getText()).toArray()));
            }
        }
    }//GEN-LAST:event_jTextField4CaretUpdate

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        if (tipoCodigoArea == 1) {
            if (!jTextField2.getText().isEmpty()) {
                jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioBuscar(jTextField2.getText()).toArray()));
            }
        }
        if (tipoCodigoArea == 2) {
            if (!jTextField2.getText().isEmpty()) {
                jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioBuscar(jTextField2.getText()).toArray()));
            }
        }
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new FechoConta().setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jTextFieldValorEntregue.setEnabled(false);
            jTextFieldMulticaixa.setEnabled(false);
            jTextFieldTroco.setEnabled(false);
            jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoCredito().toArray()));
            limparVenda();

        } else {
            jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoFormaPagamento().toArray()));
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(true);
            jTextFieldTroco.setEnabled(true);

        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox2.getSelectedItem().equals("NUMERARIO")) {
            jTextFieldValorEntregue.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setText("0.0");
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(false);
        }
        if (jComboBox2.getSelectedItem().equals("MULTICAIXA")) {
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(false);
            jTextFieldMulticaixa.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setEnabled(true);

        }
        if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(false);
            jTextFieldMulticaixa.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setEnabled(true);
//            jLabel13.setVisible(true);
//            jTextField5.setVisible(true);
        }
        if (jComboBox2.getSelectedItem().equals("PARCIAL")) {
            jTextFieldMulticaixa.setText("0.0");
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        jLabel8.setText("PREÇO UNITÁRIO:" + controllerServico.getNomeServicosPorlikePreco(jComboBox3.getSelectedItem().toString()));

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            int codigo = Integer.parseInt(jTextField1.getText());
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getCodigo(codigo).toArray()));
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
//        JPasswordField jpf = new JPasswordField();
//        JLabel label = new JLabel("Digita a Senha do Administrador");
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja remover o produto na carrinha de Compras", "Atenção", JOptionPane.YES_NO_OPTION);
////            if (codigoTipoUtilizador == 4 || codigoTipoUtilizador == 9 || codigoTipoUtilizador == 10) {
            if (resposta == JOptionPane.YES_OPTION) {

//                    JOptionPane.showConfirmDialog(null,
//                            new Object[]{label, jpf}, "Senha:",
//                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                    String senha = new String(jpf.getPassword());
//                    if (senha.equals("2011987")) {
                defaultTableModel.removeRow(jTable1.getSelectedRow());
                jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
                troco();
                calculoTroco();
                actualizarValorApagar();
                actualizarValorIva();
                actualizar();
            }

        } //  } 
        //        else {
        //            defaultTableModel.removeRow(jTable1.getSelectedRow());
        ////            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
        //            troco();
        //            calculoTroco();
        //            actualizarValorApagar();
        //            actualizarValorIva();
        //            actualizar();
        //        }
        //    }
        else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }

    }//GEN-LAST:event_jLabel9MousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

        JPasswordField jpf = new JPasswordField();
        JLabel label = new JLabel("Digita a Senha do Administrador");
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja remover o produto na carrinha de Compras", "Atenção", JOptionPane.YES_NO_OPTION);
            //       if (codigoTipoUtilizador == 4 || codigoTipoUtilizador == 9 || codigoTipoUtilizador == 10) {
            if (resposta == JOptionPane.YES_OPTION) {

//                JOptionPane.showConfirmDialog(null,
//                        new Object[]{label, jpf}, "",
//                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                String senha = new String(jpf.getPassword());
//                if (senha.equals("2011987")) {
                defaultTableModel.removeRow(jTable1.getSelectedRow());
            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
                troco();
                calculoTroco();
                actualizarValorApagar();
                actualizarValorIva();
                actualizar();
//                }

            } //
            //            } else {
            //                defaultTableModel.removeRow(jTable1.getSelectedRow());
            ////            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
            //                troco();
            //                calculoTroco();
            //                actualizarValorApagar();
            //                actualizarValorIva();
            //                actualizar();
            //            }
            // }
            else {
                JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
            }

    }//GEN-LAST:event_jTable1MousePressed
    }

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        JPasswordField jpf = new JPasswordField();
        JLabel label = new JLabel("Digita a Senha do Administrador");
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja adicionar um desconto", "Atenção", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            String senha = new String(jpf.getPassword());
            if (senha.equals("2011987")) {
                jTextField8.setVisible(true);
            }
        }
    }//GEN-LAST:event_jLabel16MousePressed

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
        try {
            if (!jTextFieldValorEntregue.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldValorEntregue.getText());
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public int getCodigoUtilizador() {
        return controllerUsuario.getCodigoUtilizador(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoFormaPagamento() {
        return formaPagamento.getCodigoFormaPagamento(jComboBox2.getSelectedItem().toString());
    }

    public int getCodigoCliente() {
        return utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
    }

    public void inserirTabela() {
        Object fila[] = new Object[6];
        int codigoServico = 0;
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
                Logger.getLogger(Tesouraria.class
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

    public void inserirTabelaAdd(String codigoBarra) {
        Object fila[] = new Object[6];
        int codigoServico = 0;
        double preco = 0.0;
        String IVA = null;
        String designacao = null;
        double totalPreco = 0.0;
        // Gestão Hospitalar
        int quantidade = Integer.parseInt(jTextField6.getText());
        if (tipoCodigoArea == 1) {
            codigoServico = controllerServico.getCodigoServicoCodigoBarra(codigoBarra);
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
            designacao = controllerServico.getServicoCodigoBarra(codigoServico);
            if (codigoTaxa == 14) {
                IVA = "14%";
                preco = controllerServico.getPrecoporCodigoBarra(codigoBarra);
                // controllerServico.getPrecoporCodigoBarra(codigoBarra)
                totalPreco = preco * quantidade + preco * quantidade * 0.14;
                fila[0] = codigoServico;
            } else {
                IVA = "0%";
                preco = controllerServico.getPrecoporCodigoBarra(codigoBarra);
                totalPreco = preco * quantidade;
                fila[0] = codigoServico;
            }

        }
        // Gestão de Stock
        fila[1] = designacao;
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
        try {
            if (!jTextFieldDesconto.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldDesconto.getText());
//                System.out.println("Valor do Desconto:" + valor);
            } else {
                jTextFieldDesconto.setText("0");
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public double getValorEntregueMulticaixa() {
        double valor = 0;
        try {
            if (!jTextFieldMulticaixa.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldMulticaixa.getText());
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public void troco() {
        try {
            double troco = ((getValorEntregue() + getValorEntregueMulticaixa()) - (getValorApagar() - getDescontoPersentagem()));
            jTextFieldTroco.setText("" + troco);
//            Extenso ext = new Extenso(new BigDecimal(getTotalApagar()));
//            lbExtenso.setText(ext.toString());
        } catch (NumberFormatException ex) {
        }
    }

    public String getTroco() {
        try {
            double troco = ((getValorEntregue() + getValorEntregueMulticaixa()) - (getValorApagar() - getDescontoPersentagem()));
            jTextFieldTroco.setText("" + troco);
        } catch (NumberFormatException ex) {
        }
        return "" + 0;
    }

    public double getTrocoReceber() {
        double valor = 0;
        try {
            if (!jTextFieldTroco.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldTroco.getText());
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public double getTotalDesconto() {
        return Double.valueOf(jTextField8.getText());
    }

    public double getDescontoIVA() {
        double valor = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            if (codigoTaxa == 14) {
                valor = valor + (valorUnitario * 0.14);
            } else {
                valor = valor + (valorUnitario * 0.14);
            }
        }

        return valor;
    }

    public void salvarFactura() {
        factura.setCodigoCliente(getCodigoCliente());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setTroco(getTrocoReceber());
        factura.setDesconto(getTotalDesconto());
        factura.setTotalFactura(totalGeral());
        factura.setNomeCliente(jComboBox5.getSelectedItem().toString());
        factura.setQuantidadeItens(getQuantidadeGeral());
        factura.setValorMulticaixa(getValorEntregueMulticaixa());
        factura.setValorNumerario(getValorEntregue());
        factura.setValorApagar(getValorApagar());
        factura.setnEcomenda(jTextField7.getText());
        factura.setVaorporExtenso("");
        factura.setCodigoSeguro(8);
        factura.setDescontoIVA(getDescontoIVA());
        //factura.setnIBAN(jTextField5.getText());
        factura.setCodigoFormaPagamento(getCodigoFormaPagamento());
        int codigoEmpresa = utentes.getCodigoSeguro(getCodigoCliente());
        if (jRadioButton1.isSelected()) {
            factura.setEstado("FACTURA CRÉDITO");
        } else {
            factura.setEstado("FACTURA PRONTO");
        }
        controllerFactura.salvar(factura);

    }

    public final String getHora() {
        String hora;
        Calendar ne = Calendar.getInstance();
        return String.format("%1$tH:%1$tM:%1$tS", ne);

    }

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
                jTextFieldMulticaixa.setEnabled(false);
                jTextFieldValorEntregue.setEnabled(true);

            }
            if (jComboBox2.getSelectedItem().equals("MULTICAIXA")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(valorUnitario);
                jTextFieldMulticaixa.setEnabled(true);
                jTextFieldValorEntregue.setEnabled(false);

            }
            if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
                facturaItens.setTotalGeral(0.0);
                facturaItens.setTotalTPA(valorUnitario);
                jTextFieldMulticaixa.setEnabled(true);
                jTextFieldValorEntregue.setEnabled(false);
            }
            if (jComboBox2.getSelectedItem().equals("PARCIAL")) {
                facturaItens.setTotalGeral(Double.valueOf(jTextFieldValorEntregue.getText()));
                facturaItens.setTotalTPA(Double.valueOf(jTextFieldMulticaixa.getText()));
                jTextFieldValorEntregue.setEnabled(true);
                jTextFieldMulticaixa.setEnabled(true);
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

    public void calculoTotal() {
        //double antes = removerPattern(jTextField5.getText());
        // double valor_unitario = antes;
        double valor_unitario = 0;
        double qtd = 0;
        if (jTextField6.getText().isEmpty()) {
            qtd = 1;
        } else {
            qtd = Double.parseDouble(jTextField6.getText());
        }
        double total = qtd * valor_unitario;
        //  jTextField7.setText(decimalformat.format(total));
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
        jTextFieldValorEntregue.setText("0");
        jTextFieldTroco.setText("0");
        // jTextField1.setText("1");
        jTextField3.setText("");
        jTextField8.setText("");
//        jTextField7.setText("0");
        jTextField6.setText("1");
        //    jTextField5.setText("0");
//        jLabel6.setText("0");
        jTextFieldMulticaixa.setText("0");
        jTextFieldDesconto.setText("0");
        defaultTableModel.setRowCount(0);
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
            java.util.logging.Logger.getLogger(Tesouraria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tesouraria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tesouraria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tesouraria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.plaf.JCalendarTheme jCalendarTheme1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldMulticaixa;
    private javax.swing.JTextField jTextFieldTotaPagar;
    private javax.swing.JTextField jTextFieldTroco;
    private javax.swing.JTextField jTextFieldValorEntregue;
    // End of variables declaration//GEN-END:variables
private class LeitorTeclas implements KeyListener {

        public void keyPressed(KeyEvent arg0) {
            switch (arg0.getKeyCode()) {
                case (KeyEvent.VK_F5):
                    if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
                        salvarFactura();
                        salvarItemFactura();
                        // new Cambio().setVisible(true);
                        int codigoFactura = controllerFactura.getLastFactura();
                        limparVenda();
                        relatorioVenda.getFactura(codigoFactura);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
            }
            System.out.println("Código da tecla: " + arg0.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent arg0) {

        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }
    }

}
