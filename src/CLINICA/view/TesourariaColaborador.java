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
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerMarcarcaoConsulta;
import CLINICA.controller.ControllerParametros;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerPedidoExames;
import CLINICA.modelo.ExamesPorFazerItem;
import CLINICA.modelo.ExamesporFazer;
import GestaoStock.controller.ProdutoController;
import GestaoStock.controller.VendaController;
import GestaoStock.controller.UsuarioController;
import GestaoStock.relatorios.RelatoriosVenda;
import CLINICA.modelo.Factura;
import CLINICA.modelo.FacturaItens;
import CLINICA.modelo.MarcacaoConsulta;
import CLINICA.modelo.ModeloEstatistica;
import CLINICA.modelo.PedidoExames;
import CLINICA.relatorios.RelatorioHistoricoClinico;
import CLINICA.relatorios.RelatorioVenda;
import GestaoStock.controller.EntradaController;
import GestaoStock.modelo.ItemVenda;
import GestaoStock.modelo.Venda;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import json_xml_iva.Calculo;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Desenvolvimento
 */
public class TesourariaColaborador extends javax.swing.JFrame {

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
    SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");

    ControllerEmpresa controllerEmpresa;
    ControllerPedidoExames controllerPedidoExames;
    RelatorioVenda relatorioVenda = new RelatorioVenda();
    RelatoriosVenda relatoriosVenda = new RelatoriosVenda();
    ControllerMarcarcaoConsulta controllerMarcarcaoConsulta;
    MarcacaoConsulta marcacaoConsulta = new MarcacaoConsulta();
    double totalApagar = 0;
    int user;
    ExamesporFazer exame;
    int codigoUsuario = 0, codigoUltimoExamePorFazer;
    ControllerUtente utentes;
    ControllerMedico controllerMedico;
    ControllerEstatista controllerEstatista = new ControllerEstatista(con);
    ProdutoController produtoController = new ProdutoController();
    UsuarioController usuarioController = new UsuarioController();
    ControllerParametros controllerParametros;
    int tipoCodigoArea;
    EntradaController control = new EntradaController();
    DecimalFormat df = new DecimalFormat("#,###.00");
    int codigoTipoUtilizador = 1;
    ControllerExamesporFazerItens controllerExamesporFazerItens;
    ControllerExamesporFazer controllerExamesporFazer;
    PedidoExames pedidoExames = new PedidoExames();
    double impostoMedico = 0;
    int desconto = 0, valor = 0;
    int total = 0;
    Calculo calculo = new Calculo();

    public TesourariaColaborador(int codigo, int codigoArea) {
        initComponents();
        this.user = codigo;
        con = new ConexaoBancos().ConexaoBD();
        controllerMedico = new ControllerMedico(con);
        controllerPedidoExames = new ControllerPedidoExames(con);
        controllerExamesporFazerItens = new ControllerExamesporFazerItens(con);
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
        controllerParametros = new ControllerParametros(con);
        impostoMedico = controllerParametros.getValorIrtMedico();
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        iconeSistema();
        this.tipoCodigoArea = codigoArea;
        jTextFieldTroco.setVisible(false);
        jTextField8.setText("0.0");
        if (tipoCodigoArea == 1) {
            utentes = new ControllerUtente(con);
            controllerFactura = new ControllerFactura(con);
            controllerFacturaItens = new ControllerFacturaItens(con);
            controllerEmpresa = new ControllerEmpresa(con);
            formaPagamento = new ControllerFormaPagamento(con);
            controllerUsuario = new ControllerUsuario(con);
            controllerServico = new ControllerServico(con);

            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioUltimoParticular().toArray()));
            int codigoCliente = utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
            jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoFormaPagamento().toArray()));
            jComboBox1.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedico().toArray()));
            jComboBox4.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedicoColaboradores().toArray()));
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));

        }
        codigoTipoUtilizador = controllerUsuario.getTipoUtilizadorporID(user);
        if (codigoTipoUtilizador == 4) {
            jLabel15.setVisible(false);
            jTextField7.setVisible(false);
            jLabel16.setVisible(false);
            // jTextField8.setVisible(false);
            jTextField8.setText("0.0");
            jRadioButton1.setVisible(true);
        }
        if (codigoTipoUtilizador == 9) {
            jLabel15.setVisible(false);
            jTextField7.setVisible(false);
            jLabel16.setVisible(false);
            // jTextField8.setVisible(false);
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getPacienteDiveros().toArray()));
            jTextField8.setText("0.0");
            jRadioButton1.setVisible(false);
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
        teclaInser();
        teclaInserQuantidade();
        teclaImprimirNumerario();
        teclaImprimirMulticaixa();
        setLocationRelativeTo(null);
    }

    public void getInserirProduto(String codigoBarra) {

        if (!codigoBarra.isEmpty()) {
            int codigoProduto = controllerServico.getCodigoServicoCodigoBarra(codigoBarra);
            int quandadeEntrade = Integer.parseInt(jTextField6.getText());
            String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
            if (tipoCodigoArea == 1) {
                if (codigoCategoria.equals("SIM")) {
                    int quantidade = controllerServico.getQuantidadeProdutoCodigoBarra(codigoBarra);
                    if (quandadeEntrade <= quantidade) {
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
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldTroco = new javax.swing.JTextField();
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
        jPanel8 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();

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

        jComboBox3.setFocusable(false);
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
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
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
                "Item", "Artigo", "Quantidade", "Preço Unitário", "IVA", "Desconto", "Valor a Pagar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1040, 210));

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jLabel8.setText(" Remover Serviço");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 151, -1));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 1040, 30));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel15.setText("Encomenda nº:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 110, -1));
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 200, 20));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setText("Total Desconto");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, 80, 30));

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 160, 30));

        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton1.setText("Factura Pro-forma");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 207, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Troco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11), new java.awt.Color(0, 102, 255))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(140, 47));

        jTextFieldTroco.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldTroco.setForeground(new java.awt.Color(0, 102, 255));
        jTextFieldTroco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTroco.setText("0");
        jTextFieldTroco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTrocoMouseClicked(evt);
            }
        });
        jTextFieldTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTrocoActionPerformed(evt);
            }
        });

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
        jTextFieldValorEntregue.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldValorEntregueCaretUpdate(evt);
            }
        });

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "IVA"));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Médicos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, 0, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Tipo de Papel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

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
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Colaboradores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField5CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        int quant = 0;
        int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());

        int quantidadeEntrada = Integer.parseInt(jTextField6.getText());
        String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
        if (tipoCodigoArea == 1) {

            if (codigoCategoria.equals("SIM")) {
                int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());

                if (quantidadeEntrada <= quantidade) {
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        if (jTable1.getValueAt(i, 1).equals(jComboBox3.getSelectedItem().toString())) {
                            System.out.println("Entrouuuuu");
                            inserirTabela();
                        } else {
                            inserirTabela();
                            System.out.println("Entrouuuuu");
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Não existe produto suficiente no Stock");
                }

            } else {

                inserirTabela();
                // gestão de Stock
            }
        }
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
            actualizaPreco();
            actualizar();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }
    }//GEN-LAST:event_jLabel8MousePressed

    public final void teclaInser() {
        jTextField4.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    int codigoProduto = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
                    String codigoCategoria = controllerServico.getProdutoEstocavel(codigoProduto);
                    int quantidadeEntrade = Integer.parseInt(jTextField6.getText());
                    if (tipoCodigoArea == 1) {
                        if (codigoCategoria.equals("SIM")) {
                            int quantidade = controllerServico.getQuantidadeProduto(jComboBox3.getSelectedItem().toString());
                            if (quantidadeEntrade <= quantidade) {
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
                            //         calculoTroco();
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
                            //       calculoTroco();
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
                                calculoTroco();
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
        double entregue_antes = Double.parseDouble(jTextFieldValorEntregue.getText());
        double entregue = total_antes;
        double troco = 0;
        if ("0".equals(jTextFieldValorEntregue.getText())) {
            troco = 0;
        } else {
            if (jTextFieldValorEntregue.getText().isEmpty()) {
                entregue_antes = 0;
            } else {
                entregue_antes = Double.parseDouble(jTextFieldValorEntregue.getText());
            }
            troco = entregue_antes - total_antes;
        }

        jTextFieldTroco.setText(Calculo.converterCash(troco));
        //   BigDecimal res2 = new BigDecimal(troco);
        //    jTextFieldTroco.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));

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
        if (controllerFactura.getDataStistema().after(controllerFactura.getDataUltimaFactura()) || controllerFactura.getDataUltimaFactura().equals(controllerFactura.getDataStistema())) {
            String horaUltimaFactura = controllerFactura.getHoraUltimaFactura();
            // System.out.println("Data da Ultima Facutra:" + horaUltimaFactura);
            if (getHoraMaior(horaUltimaFactura)) {
                if (codigoProduto != 0) {
                    if (!jComboBox1.getSelectedItem().equals("") && !jComboBox4.getSelectedItem().equals("")) {
                        if (tipoCodigoArea == 1) {
                            if (jRadioButton1.isSelected()) {
                                int resposta = JOptionPane.showConfirmDialog(null, "Queira por favor confirmar o nome do Paciente antes de salvar", "Atenção", JOptionPane.YES_NO_OPTION);
                                if (resposta == JOptionPane.YES_OPTION) {
                                    salvarFactura();
                                    salvarItemFactura();
                                    //salvarMedicoHonorario();
                                    //    salvarServicoMedicosIntesSantaMarta();
                                    int codigoFactura = controllerFactura.getLastFactura();
                                    //  salvarEstatistica(codigoFactura);
                                    //  salvarEstatisticaItens();
                                    limparVenda();
//                                if (jRadioButton5.isSelected()) {
//                                    // Factura A5
//                                    relatorioVenda.getFactura1(codigoFactura);
//                                }
                                    if (jRadioButton7.isSelected()) {
                                        // Factura A4
                                        relatorioVenda.getFacturaGuia2(codigoFactura);
                                    }
                                    jComboBox1.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedico().toArray()));
                                    jComboBox4.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedicoColaboradores().toArray()));
                                }

                            } else {
                                inserirExame();
                                int resposta = JOptionPane.showConfirmDialog(null, "Queira por favor confirmar o nome do Paciente antes de salvar", "Atenção", JOptionPane.YES_NO_OPTION);
                                if (resposta == JOptionPane.YES_OPTION) {
                                    if (getValorEntregue() + getValorEntregueMulticaixa() >= getValorApagar()) {
                                        salvarFactura();
                                        salvarItemFactura();
                                        int codigoFactura = controllerFactura.getLastFactura();
                                        salvarColaboradores();
                                        salvarColaboradoresIntes();
                                        salvarMarcacao();
                                        salvarEstatistica(codigoFactura);
                                        salvarEstatisticaItens();
                                        salvarMedicoHonorario();
                                        salvarServicoMedicosIntesSantaMarta();
                                        inserirEcografia(codigoFactura);
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
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Valor Entregue deve Maior que o valor Total!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }

                                }
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insira nome do Médico", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    if (tipoCodigoArea == 2) {
                        if (jRadioButton1.isSelected()) {
                            int resposta = JOptionPane.showConfirmDialog(null, "Queira por favor confirmar o nome do Paciente antes de salvar", "Atenção", JOptionPane.YES_NO_OPTION);
                            if (resposta == JOptionPane.YES_OPTION) {
                                salvarFactura();
                                salvarItemFactura();
                                int codigoFactura = controllerFactura.getLastFactura();
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
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não existe Produto no carrinho de Compra!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é permitido emitir factura com data anterior, verifica a data do computador!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        //   jTextField8.setText("0.0");

    }//GEN-LAST:event_jButton5ActionPerformed

    public void salvarEstatistica(int codigoFactura) {
        controllerEstatista = new ControllerEstatista(con);
        ModeloEstatistica modeloEstatistica = new ModeloEstatistica();
        modeloEstatistica.setCodigoFactura(String.valueOf(codigoFactura));
        modeloEstatistica.setCodigoPaciente(getCodigoCliente());
        controllerEstatista.salvar(modeloEstatistica);
    }

    public void salvarEstatisticaItens() {
        controllerEstatista = new ControllerEstatista(con);
        int codigoFactura = controllerEstatista.getLastEstatistica();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 1, 0, 0, 0, 0, 0, 0, 0, 0, codigoFactura);
            controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
        }

    }
    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        if (tipoCodigoArea == 1) {
            if (!jTextField3.getText().isEmpty()) {
                jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicoporCodigo(Integer.parseInt(jTextField3.getText())).toArray()));
            }
        }
        if (tipoCodigoArea == 2) {
            if (!jTextField3.getText().isEmpty()) {
                int codigo = Integer.parseInt(jTextField3.getText());
                jComboBox3.setModel(new DefaultComboBoxModel(produtoController.getNomeServicoporCodigoBarra(jTextField3.getText()).toArray()));
            }
        }
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        if (tipoCodigoArea == 1) {
            if (!jTextField4.getText().isEmpty()) {
                jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicosPorlike(jTextField4.getText()).toArray()));
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
            jRadioButton7.setSelected(true);
            limparVenda();

        } else {
            jComboBox2.setModel(new DefaultComboBoxModel(formaPagamento.getDesignacaoFormaPagamento().toArray()));
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(true);
            jTextFieldTroco.setEnabled(true);
            jRadioButton6.setSelected(true);

        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox2.getSelectedItem().equals("NUMERARIO")) {
            jTextFieldValorEntregue.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setText("0.0");
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(false);
            //   calculoTroco();
        }
        if (jComboBox2.getSelectedItem().equals("MULTICAIXA")) {
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(false);
            jTextFieldMulticaixa.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setEnabled(true);
            //        calculoTroco();

        }
        if (jComboBox2.getSelectedItem().equals("TRANSFERENCIA BANCARIA")) {
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(false);
            jTextFieldMulticaixa.setText(jTextFieldTotaPagar.getText());
            jTextFieldMulticaixa.setEnabled(true);
            //       calculoTroco();

        }
        if (jComboBox2.getSelectedItem().equals("PARCIAL")) {
            jTextFieldMulticaixa.setText("0.0");
            jTextFieldValorEntregue.setText("0.0");
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(true);
            //  calculoTroco();
        }
        if (jComboBox2.getSelectedItem().equals("PRO-FORMA")) {
            jTextFieldMulticaixa.setText("0.0");
            jTextFieldValorEntregue.setText("0.0");
            jTextField8.setText("0");
            jTextFieldValorEntregue.setEnabled(true);
            jTextFieldMulticaixa.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            int codigo = Integer.parseInt(jTextField1.getText());
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getCodigo(codigo).toArray()));
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField5CaretUpdate
        jComboBox4.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedicoColaboradoresporLike(jTextField5.getText()).toArray()));
    }//GEN-LAST:event_jTextField5CaretUpdate

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        jTable1.setValueAt(getValorMonetario(getDescontoMeto(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString(), getValorNormal(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()))), jTable1.getSelectedRow(), 5);
        updateDesconto();
        actualizarValorIva();
        actualizarValorApagar();
        actualizaPreco();

    }//GEN-LAST:event_jTable1KeyReleased


    private void jTextFieldValorEntregueCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldValorEntregueCaretUpdate
        //  calculoTroco();

    }//GEN-LAST:event_jTextFieldValorEntregueCaretUpdate

    private void jTextFieldTrocoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTrocoMouseClicked
        //  calculoTroco();
    }//GEN-LAST:event_jTextFieldTrocoMouseClicked

    private void jTextFieldTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTrocoActionPerformed
        calculoTroco();
        System.out.println("Entrou");
    }//GEN-LAST:event_jTextFieldTrocoActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

//    public void getDesconto() {
//
//    }
    public String getValorMonetario(Double valor) {
        return Calculo.converterCash(valor);
    }

    public double getValorNormal(String valor) {
        return Calculo.getValueNormal(valor);
    }

    public double getDescontoMeto(String value, Double preco) {
        return Calculo.desconto(value, preco, this);
    }

    public void actualizaPreco() {
        double toal_geral = 0, subTotal = 0;
        int codigoServico, codigoTaxa;
        double total = 0, total_iva = 0, iva = 0, total_iva1 = 0, desconto = 0;
        double Totalgeral = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            System.out.println("Para ver o erro:" + jTable1.getValueAt(0, i).toString());
//            codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
//            //  codigoServico = controllerServico.getCodigoServico(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString());
//            //  codigoTaxa = controllerServico.getCodigoTaxa(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//            codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
//
//            if (codigoTaxa == 14) {
//
//                iva = 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
//                total_iva = total_iva + iva; //(getValorNormal(jTable1.getValueAt(i, 6).toString()));
//                toal_geral = toal_geral + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
//                System.out.println("Total IVA:" + total_iva);
//                System.out.println("Total Geral:" + toal_geral);
//            } else {
            desconto += getValorNormal(jTable1.getValueAt(i, 5).toString());
//                //iva += 0 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
//                iva = 0;
//                total_iva = total_iva + iva; //(getValorNormal(jTable1.getValueAt(i, 6).toString()));
//                toal_geral = toal_geral + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
        }

        //  Totalgeral = toal_geral- desconto;
        //  jTextFieldTotaPagar.setText("" + getValorMonetario(toal_geral));
        // jTextFieldDesconto.setText("" + getValorMonetario(total_iva));
        jTextField8.setText("" + getValorMonetario(desconto));
    }
//    public void actualizaPreco() {
//        double toal_geral = 0, subTotal = 0;
//        int codigoServico, codigoTaxa;
//        double total = 0, total_iva = 0, desconto = 0;
//        double Totalgeral = 0;
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            codigoServico = controllerServico.getCodigoServico(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString());
//            codigoTaxa = controllerServico.getCodigoTaxa(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
//            // codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
//            toal_geral += getValorNormal(jTable1.getValueAt(i, 6).toString());
//            if (codigoTaxa == 14) {
//                // total_iva += (getValorNormal(jTable1.getValueAt(i, 4).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
//                total_iva += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
//                System.out.println("Total IVA:" + total_iva);
//                //*0.14;
//            }
//
//            desconto += getValorNormal(jTable1.getValueAt(i, 5).toString());
//        }
//        //  Totalgeral = toal_geral- desconto;
//        jTextFieldTotaPagar.setText("" + getValorMonetario(toal_geral));
//        jTextFieldDesconto.setText("" + getValorMonetario(total_iva));
//        jTextField8.setText("" + getValorMonetario(desconto));
//    }

    public void updateDesconto() {
        double desconto = getValorNormal(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        double subTotal = getValorNormal(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        double precoTotal = 0;
        double iva = 0;
        int codigoServico = controllerServico.getCodigoServico(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
        if (codigoTaxa == 14) {
            precoTotal = (((subTotal - desconto)) * 0.14) + (subTotal - desconto);
            System.out.println("Preço Total:" + precoTotal);
            iva = ((subTotal - desconto)) * 0.14;
        } else {
            precoTotal = (((subTotal - desconto)) * 0) + (subTotal - desconto);
            System.out.println("Preço Total:" + precoTotal);
            iva = ((subTotal - desconto)) * 0;
        }
        jTable1.setValueAt(getValorMonetario(iva), jTable1.getSelectedRow(), 4);
        jTable1.setValueAt(getValorMonetario(precoTotal), jTable1.getSelectedRow(), 6);
    }

    public void actualizarValorApagar() {

        double valor, valorTotal = 0, valorTotal1 = 0;
        double valorIVA = 0;
        double troco = 0;
        if (tipoCodigoArea == 1) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//                int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
//                if (codigoTaxa == 14) {
//                    valor = (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString())) * 0.14;
//                    valorTotal = valorTotal + valor;
////                    System.out.println("Total:" + valorTotal);
//                    jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
//                    troco = Double.parseDouble(getTroco());
//                } else {
//                    //valor = getValorNormal(jTable1.getValueAt(i, 6).toString());
//                   // valor = Double.valueOf(jTable1.getValueAt(i, 3).toString()) - Double.valueOf(jTable1.getValueAt(i, 5).toString()) * 0;
//                    valor = (getValorNormal(jTable1.getValueAt(i, 3).toString())-getValorNormal(jTable1.getValueAt(i, 5).toString())) * 0;
                valor = getValorNormal(jTable1.getValueAt(i, 6).toString());
                valorTotal1 = valorTotal1 + valor;
//                    System.out.println("Total:" + valorTotal);
//                    jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
//                    troco = Double.parseDouble(getTroco());
//                }
                jTextFieldTotaPagar.setText(String.valueOf(valorTotal + valorTotal1));
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

        double valor = 0, valorTotal = 0;
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
//                    int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//                    int quantidade = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
//                    int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
//                    if (codigoTaxa == 14) {
//                        valor = Double.valueOf(jTable1.getValueAt(i, 3).toString()) * 14 * quantidade;
//                        valorTotal = valorTotal + valor;
////                        System.out.println("Teste Desconto:" + valorTotal);
                    valor = getValorNormal(jTable1.getValueAt(i, 4).toString());
                    valorTotal = valorTotal + valor;
                    jTextFieldDesconto.setText(String.valueOf(valorTotal));
                    //jTextFieldDesconto.setText(String.valueOf(valorTotal / 100));
                    // troco = String.valueOf(getDesconto());
                }
                // Por analizar
//                    } else {
//                        jTextFieldDesconto.setText("0.0");
//                        //    jTextField3.setText("0.0");
//                        //     jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
//                    }
            }

        } else {
            // Por analizar
            //  jTextField3.setText("0.0");
            //     jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
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
                valor = Calculo.getValueNormal(jTextFieldTotaPagar.getText());
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
                valor = Calculo.getValueNormal(jTextFieldValorEntregue.getText());
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    public int getCodigoUtilizador() {
        return user;

    }

    public int getCodigoFormaPagamento() {
        return formaPagamento.getCodigoFormaPagamento(jComboBox2.getSelectedItem().toString());
    }

    public int getCodigoCliente() {
        return utentes.getCodigoUtente(jComboBox5.getSelectedItem().toString());
    }

    public String getNifCliente() {
        return utentes.getNif(getCodigoCliente());
    }

    public void inserirTabela() {
        Object fila[] = new Object[7];
        int codigoServico = 0;
        double preco = 0.0, desconto = 0.0;
        double IVA = 0;
        double totalPreco = 0.0;
        // Gestão Hospitalar
        int quantidade = Integer.parseInt(jTextField6.getText());
        if (tipoCodigoArea == 1) {
            codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
            if (codigoTaxa == 14) {

                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                IVA = (preco * quantidade) * 0.14;
                // IVA = (preco) * 0.14;
                totalPreco = preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                fila[0] = codigoServico;
            } else {
                IVA = 0;
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade;
                fila[0] = codigoServico;
            }

        }
        // Gestão de Stock
        if (tipoCodigoArea == 2) {
            try {
                int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
                if (codigoTaxa == 14) {

                    String codigoBarra = produtoController.codBarras(jComboBox3.getSelectedItem().toString());
                    preco = produtoController.getPrecoUnitario(Integer.parseInt(codigoBarra));
                    totalPreco = (preco * quantidade) + (preco * quantidade * 0.14);
                    IVA = preco * 0.14;
                    fila[0] = codigoBarra;
                } else {
                    IVA = 0;
                    String codigoBarra = produtoController.codBarras(jComboBox3.getSelectedItem().toString());
                    preco = produtoController.getPrecoUnitario(Integer.parseInt(codigoBarra));
                    fila[0] = codigoBarra;
                }

            } catch (SQLException ex) {
                Logger.getLogger(TesourariaColaborador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        fila[1] = jComboBox3.getSelectedItem().toString();
        fila[2] = quantidade;
        fila[3] = preco;
        fila[4] = getValorMonetario(IVA);
        fila[5] = desconto;
        fila[6] = totalPreco;
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setResizable(!false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        actualizarValorIva();
        actualizarValorApagar();
        actualizar();
        // actualizaPreco();

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

    public double getDescontoIVA() {
        double valor = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            double valorUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 3).toString());
            double descontoUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 5).toString());
            if (codigoTaxa == 14) {
                valor = valor + ((valorUnitario - descontoUnitario) * 0.14);
                System.out.println("Total IVA:" + valor);
            } else {
                valor = valor + (valorUnitario - descontoUnitario) * 0;
            }
        }

        return valor;
    }

    public double getValorEntregueMulticaixa() {
        double valor = 0;
        try {
            if (!jTextFieldMulticaixa.getText().equals("")) {
                valor = Calculo.getValueNormal(jTextFieldMulticaixa.getText());
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
        return Calculo.getValueNormal(jTextField8.getText());
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
        factura.setNif(getNifCliente());
        factura.setDescontoIVA(Double.parseDouble(jTextFieldDesconto.getText()));
        factura.setnIBAN(jComboBox1.getSelectedItem().toString());
        factura.setCodigoFormaPagamento(getCodigoFormaPagamento());
        factura.setDataVencimento(getDataActual());
        int codigoEmpresa = utentes.getCodigoSeguro(getCodigoCliente());
        if (jRadioButton1.isSelected()) {
            if (jComboBox2.getSelectedItem().equals("PRO-FORMA")) {
                factura.setEstado("FACTURA PROFORMA");
            }
            if (jComboBox2.getSelectedItem().equals("CRÉDITO")) {
                factura.setEstado("FACTURA CREDITO");
            }
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
            int tamanho = jTable1.getRowCount();
            System.out.println("Tamanho:" + tamanho);
            int codigoFactura = controllerFactura.getLastFactura();
            //  System.out.println("Codigo da Factura:" + codigoFactura);
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
            double valorUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 3).toString());
            double descontoUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 5).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            if (codigoTaxa == 14) {
                facturaItens.setDescontoIVA((valorUnitario - descontoUnitario) * quantidadde * 0.14);
                //  facturaItens.setDescontoIVA((valorUnitario - descontoUnitario) * 0.14) ;
            } else {

                facturaItens.setDescontoIVA(valorUnitario * 0);
            }
//            System.out.println("Teste:" + valorUnitario);
            facturaItens.setCodigoFactura(codigoFactura);
            facturaItens.setCodigoServico(codigoProduto);
            facturaItens.setCodigoCategoriaServico(codigoCategoria);
            facturaItens.setQuantidade(quantidadde);
            facturaItens.setPreco(valorUnitario - descontoUnitario);
            facturaItens.setDescontoProduto(descontoUnitario);
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
                facturaItens.setTotalGeral(Double.valueOf(jTextFieldValorEntregue.getText()) / tamanho);
                facturaItens.setTotalTPA(Double.valueOf(jTextFieldMulticaixa.getText()) / tamanho);
                jTextFieldValorEntregue.setEnabled(true);
                jTextFieldMulticaixa.setEnabled(true);
            }
            if (jComboBox2.getSelectedItem().equals("CRÉDITO") || jComboBox2.getSelectedItem().equals("PRO-FORMA")) {
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

// Para colaboradores
    public int getCodigoMedico() {
        return controllerUsuario.getCodigoUtilizador(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoMedico1() {
        return controllerUsuario.getCodigoMedico(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoMedicoColaboradores() {
        return controllerMedico.getCodigoMedicoColaboradores(jComboBox4.getSelectedItem().toString());
    }

    public void salvarColaboradores() {
        int codigoFactura = controllerFactura.getLastFactura();
        factura.setCodigoCliente(getCodigoMedicoColaboradores());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setTotalFactura(totalGeral());
        factura.setDataVencimento(getDataActual());
        factura.setEstado(String.valueOf(codigoFactura));
        controllerMedico.salvarHonorario(factura);

    }

    public void salvarColaboradoresIntes() {
        double lab = 0, consulta = 0, raioX = 0, ecografia = 0, ecografia7 = 0, ecografia10 = 0, ecografiamorfologica = 0, ecocardiograma = 0, electrocardiograma = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerMedico.getLastFacturaHonorario();
// int codigoFactura = controllerFactura.getLastFactura();
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            // Consulta
            if (codigoCategoria == 1) {
                consulta = controllerParametros.getValorConsulta() + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getPercentagemConsulta();
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
                lab = controllerParametros.getValorExames() + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getPercentagemExames();
                consulta = 0;
                System.out.println("Laboratorio:" + lab);
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
                raioX = controllerParametros.getValorRaioX() + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * controllerParametros.getPercentagemRaioX();
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

                ecografia = controllerParametros.getValorEcografiaCola5() + controllerParametros.getPercentagemEcografia5() * Double.parseDouble(jTable1.getValueAt(i, 3).toString());
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
                electrocardiograma = controllerParametros.getElectroColaborador() + controllerParametros.getPercentagemElectroCardiograma() * Double.parseDouble(jTable1.getValueAt(i, 3).toString());;
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
                ecografia7 = controllerParametros.getValorEcografia7() + controllerParametros.getPercentagemEcografia7() * Double.parseDouble(jTable1.getValueAt(i, 3).toString());;
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
                ecografia10 = controllerParametros.getValorEcografia10() + controllerParametros.getPercentagemEcografia10() * Double.parseDouble(jTable1.getValueAt(i, 3).toString());;
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
                ecografiamorfologica = controllerParametros.getValorEcografiaCola20() + controllerParametros.getPercentagemEcografia20() * Double.parseDouble(jTable1.getValueAt(i, 3).toString());;
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

    public void salvarMedicoHonorario() {

        int codigoFactura = controllerFactura.getLastFactura();
        factura.setCodigoCliente(getCodigoMedico1());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setEstado(String.valueOf(codigoFactura));
        controllerMedico.salvarHonorarioMedico(factura);

    }

    public void salvarServicoMedicosIntes() {
        double consulta = 0;
        double percentagem = 0;
        double irt = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerMedico.getLastMedicoFacturaHonorario();
            percentagem = controllerMedico.getPercentagem(getCodigoMedico());
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            System.out.println("Percentagem:" + percentagem);
            // Consulta
            if (codigoCategoria == 1) {
                consulta = Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * percentagem;
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }
            // Ecografia 5
            if (codigoCategoria == 5) {
                consulta = controllerParametros.getValorEcografia5();
                // = consulta + Double.parseDouble(jTable1.getValueAt(i, 3).toString()) * percentagem;
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }
            // Ecografia 7
            if (codigoCategoria == 21) {
                consulta = controllerParametros.getValorEcografia7();
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }
            // Ecografia 10
            if (codigoCategoria == 22) {
                consulta = controllerParametros.getValorEcografia10();
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }
            // Ecografia 20
            if (codigoCategoria == 23) {
                consulta = controllerParametros.getValorEcografia20();
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }

// Electrocardiograma
            if (codigoCategoria == 19) {
                consulta = controllerParametros.getValorElectrocardiograma();
                System.out.println("Percentagem:" + consulta);
                double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                irt = irt + consulta * controllerParametros.getValorIrtMedico();
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(valorUnitario);
                facturaItens.setPercentagem(consulta);
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }

        }
    }

    public void salvarServicoMedicosIntesSantaMarta() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerMedico.getLastMedicoFacturaHonorario();
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            String codigoServico = controllerMedico.getMedicoServico(getCodigoMedico(), codigoProduto);
            System.out.println("Codigo Servico:" + codigoServico);
            if (codigoServico != null) {
                facturaItens.setCodigoFactura(codigoFactura);
                facturaItens.setCodigoServico(codigoProduto);
                facturaItens.setPreco(controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreco());
                System.out.println("Preço:" + facturaItens.getPreco());
                facturaItens.setPercentagem((controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreconormal() + controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPrecopercentual()));
                System.out.println("Percentagem:" + facturaItens.getPercentagem());
                double irt = (controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreconormal() + controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPrecopercentual()) * impostoMedico;
                facturaItens.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(facturaItens);
            }

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
//        jTextFieldTroco.setText(decimalformat.format(troco));
//    }

    public Double removerPattern(String valor_recebido) {
        String valor = valor_recebido.replaceAll("\\.", "").replace(",", ".");
        return Double.parseDouble(valor);
    }

    public void limparVenda() {
        jTextFieldTotaPagar.setText("0");
        jTextFieldValorEntregue.setText("0");
        jTextFieldTroco.setText("0");
        // jTextField1.setText("1");
        jTextField3.setText("");
        //  jTextField8.setText("");
        jTextField8.setText("0.0");
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
            java.util.logging.Logger.getLogger(TesourariaColaborador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesourariaColaborador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesourariaColaborador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesourariaColaborador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                //  new Tesouraria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.plaf.JCalendarTheme jCalendarTheme1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldMulticaixa;
    private javax.swing.JTextField jTextFieldTotaPagar;
    private javax.swing.JTextField jTextFieldTroco;
    private javax.swing.JTextField jTextFieldValorEntregue;
    // End of variables declaration//GEN-END:variables

    public boolean salvarPedidoExameItem(int codigoExame, int codProduto, int codigoStatus, int codigoProdutoIem, int quantidade) {
        ExamesPorFazerItem item = new ExamesPorFazerItem(codigoExame, codProduto, codigoStatus, codigoProdutoIem, quantidade);
        controllerExamesporFazerItens.create(item);
        return true;
    }

    public void inserirEcografia(int codigoFactura) {
        pedidoExames.setCodigoPaciente(getCodigoCliente());
        pedidoExames.setCodigoMedico(getCodigoMedico1());
        pedidoExames.setColaborador(jComboBox4.getSelectedItem().toString());
        controllerPedidoExames.SalvarEcografiaItens(pedidoExames);
        int codigoSeguradora = utentes.getCodigoSeguro(getCodigoCliente());
        int codigoEcografia = controllerPedidoExames.getLastInsertEcografiaItens();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            pedidoExames.setCodigoServico(codigoServico);
            pedidoExames.setCodigoTriagem(codigoEcografia);
            //      pedidoExames.setCodigoFactura(codigoEcografia);
            if (codigoCategoria == 5 || codigoCategoria == 21 || codigoCategoria == 22 || codigoCategoria == 23) {
                controllerPedidoExames.SalvarEcografia(pedidoExames);
            }

        }
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

    public void inserirExame() {
        codigoUltimoExamePorFazer = 0;
        boolean itemGravado = false;
        boolean salvo = false;
        int quantidade = 1;
        exame = new ExamesporFazer();
        exame.setCodigoPaciente(getCodigoCliente());
        exame.setCodigoUtilizador(getCodigoUtilizador());
        exame.setCodigoMedico(getCodigoMedico1());
        exame.setDataPedido(getDataActual());
        exame.setQuantidade(quantidade);
        exame.setColaborador(jComboBox4.getSelectedItem().toString());
        controllerExamesporFazer.create(exame);
        salvo = true;
        if (salvo) {
            codigoUltimoExamePorFazer = controllerExamesporFazer.getLastInsert();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoServico);
                if (codigoCategoria == 2) {
                    System.out.println("Codigo do Serviço:" + codigoServico);

                    int codigoProdutoItem = controllerServico.getCodigoProdutoItem(codigoServico);
                    Vector vectProdutosItem;
                    vectProdutosItem = controllerServico.getCodigosProdutoItem(codigoServico);

                    for (int j = 0; j < vectProdutosItem.size(); j++) {
                        if (codigoServico != 0) {
                            if (salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, Integer.parseInt(vectProdutosItem.elementAt(j).toString().trim()), quantidade)) {
                                itemGravado = true;

                            } else {
                                salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, 0, quantidade);
                            }
                        }

                    }
                    if (codigoProdutoItem == 0) {
                        salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, 0, quantidade);
                        itemGravado = true;
                    }

                }
            }

        }

    }

    public void salvarMarcacao() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoServico);
            if (codigoCategoria == 1) {
                int limitemedico = controllerMarcarcaoConsulta.getLimiteDiario(getCodigoMedico());
                System.out.println("Limite do medico:" + limitemedico);
                int limiteconsulta = controllerMarcarcaoConsulta.getTotalConsultasAgendadasPorMedico(getCodigoMedico(), getDataActual());
                int codigoSeguro = utentes.getCodigoSeguro(getCodigoCliente());
                double precoParticular = controllerServico.getPrecoParticular(jTable1.getValueAt(i, 1).toString());
                double ensa = controllerServico.getPrecoAdvance(jTable1.getValueAt(i, 1).toString());
                double saham = controllerServico.getPrecoSaham(jTable1.getValueAt(i, 1).toString());
                double angolatelecom = controllerServico.getPrecoAngolaTelecom(jTable1.getValueAt(i, 1).toString());
                double saudemais = controllerServico.getPrecoSaudemais(jTable1.getValueAt(i, 1).toString());
                double unisaude = controllerServico.getPrecoUnisaude(jTable1.getValueAt(i, 1).toString());
                double fidelidade = controllerServico.getPrecoFidelidade(jTable1.getValueAt(i, 1).toString());
                double anst = controllerServico.getPrecoEmpresa(jTable1.getValueAt(i, 1).toString());
                if (!controllerMarcarcaoConsulta.consultaExistente(getCodigoCliente(), getCodigoMedico1(), codigoServico, getDataActual())) {
                    if (limiteconsulta <= limitemedico) {
                        marcacaoConsulta.setCodigoUtlizador(user);
                        marcacaoConsulta.setCodigoMedico(getCodigoMedico1());
                        marcacaoConsulta.setCodigoPaciente(getCodigoCliente());
                        marcacaoConsulta.setCodigoServico(codigoServico);
                        //    marcacaoConsulta.setDataAtendimento(String.);
                        marcacaoConsulta.setDataMarcacao(getDataActual());
                        marcacaoConsulta.setHora(getDataActual());
                        marcacaoConsulta.setObservacao("");
                        marcacaoConsulta.setEstado("Não urgente (Azul) - Atendimento em até 4 horas.");
                        marcacaoConsulta.setCodigoEstado(1);
                        if (codigoSeguro == 8 || codigoSeguro == 19) {
                            marcacaoConsulta.setPreco(precoParticular);
                            marcacaoConsulta.setPago("Pagou Sim com:" + precoParticular);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7 || codigoSeguro == 15) {
                            marcacaoConsulta.setPreco(ensa);
                            marcacaoConsulta.setPago("Pagou Sim com:" + ensa);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 9) {
                            marcacaoConsulta.setPreco(angolatelecom);
                            marcacaoConsulta.setPago("Pagou Sim com:" + angolatelecom);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);

                        }
                        if (codigoSeguro == 10) {
                            marcacaoConsulta.setPreco(anst);
                            marcacaoConsulta.setPago("Pagou Sim com:" + anst);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 11) {
                            marcacaoConsulta.setPreco(saham);
                            marcacaoConsulta.setPago("Pagou Sim com:" + saham);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 12) {
                            marcacaoConsulta.setPreco(unisaude);
                            marcacaoConsulta.setPago("Pagou Sim com:" + unisaude);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 13) {
                            marcacaoConsulta.setPreco(saudemais);
                            marcacaoConsulta.setPago("Pagou Sim com:" + saudemais);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 14) {
                            marcacaoConsulta.setPreco(fidelidade);
                            marcacaoConsulta.setPago("Pagou Sim com:" + fidelidade);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 16) {
                            marcacaoConsulta.setPreco(anst);
                            marcacaoConsulta.setPago("Pagou Sim com:" + anst);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Consulta já foi marcada");
                }

            }
        }
    }

    public static Integer[] getHorAndMinute(String hora) {
        //  System.out.println("Data passada:" + hora);
        int horario, minutos;
        String ba = null;
        if (hora.isEmpty()) {

            String horaAtual = new SimpleDateFormat("HH:mm").format(new Date().getTime());
            horario = Integer.parseInt(horaAtual.substring(0, 2));
            minutos = Integer.parseInt(horaAtual.substring(3, 5));
            return new Integer[]{horario, minutos};
        } else {
            horario = Integer.parseInt(hora.substring(0, 2));
            minutos = Integer.parseInt(hora.substring(3, 5));
            return new Integer[]{horario, minutos};
        }

    }

    public static boolean getHoraMaior(String horaFecha) {
        System.out.println("Passada para comparar:" + horaFecha);
        String horaAtual = new SimpleDateFormat("HH:mm").format(new Date().getTime());// Pega hora atual do Sistema
        System.out.println("Hora do Sistema:" + horaAtual);
        Integer horarioFecha[] = getHorAndMinute(horaFecha);
        System.out.println("Hora Passada:" + horarioFecha[0]);
        Integer horarioAtual[] = getHorAndMinute(horaAtual);
        System.out.println("Hora Passada do sistema:" + horarioAtual[0]);

        if (horarioAtual[0] >= horarioFecha[0]) {

            System.out.println("Em dia");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Não é permitido emitir factura com hora inferior que a hora da Ultima Factura, verifica a Hora do seu Computador!", "Mind Vision Tecnology - Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
