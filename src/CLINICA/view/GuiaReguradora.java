/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerFacturaItens;
import CLINICA.controller.ControllerSeguradora;
import CLINICA.controller.ControllerFactura;
import CLINICA.controller.ControllerEstatista;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerParametros;
import CLINICA.controller.ControllerMarcarcaoConsulta;
import CLINICA.controller.ControllerPedidoExames;
import CLINICA.modelo.ExamesPorFazerItem;
import CLINICA.modelo.ExamesporFazer;
import CLINICA.modelo.Factura;
import CLINICA.modelo.FacturaItens;
import CLINICA.modelo.Guia;
import CLINICA.modelo.GuiaItens;
import CLINICA.modelo.MarcacaoConsulta;
import CLINICA.modelo.ModeloEstatistica;
import CLINICA.modelo.PedidoExames;
import CLINICA.relatorios.RelatorioVenda;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import GestaoStock.controller.EntradaController;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import json_xml_iva.Calculo;

/**
 *
 * @author Desenvolvimento
 */
public class GuiaReguradora extends javax.swing.JFrame {

    /**
     * Creates new form Tesouraria
     */
    private final DefaultTableModel defaultTableModel;
    Factura factura = new Factura();
    ExamesporFazer exame;
    Factura factura1 = new Factura();
    GuiaItens facturaItens = new GuiaItens();
    FacturaItens facturaItens1 = new FacturaItens();
    private DecimalFormat decimalformat;
    ControllerUsuario controllerUsuario;
    ControllerServico controllerServico;
    ControllerFactura controllerGuia;
    ControllerFacturaItens controllerGuiatens;
    double impostoMedico = 0;
    Data d = new Data();
    RelatorioVenda relatorioVenda = new RelatorioVenda();
    double totalApagar = 0, preco;
    int user, codigo, codigoFactura;
    int codigoSeguro, codigoServico;
    ConexaoBancos conexao = new ConexaoBancos();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ControllerUtente utentes;
    ControllerSeguradora controllerSeguradora;
    ControllerEstatista controllerEstatista;
    EntradaController control = new EntradaController();
    int codigoUsuario = 0, codigoUltimoExamePorFazer;
    int flag = 1;
    double total;
    ControllerExamesporFazerItens controllerExamesporFazerItens;
    ControllerExamesporFazer controllerExamesporFazer;
    ControllerMedico controllerMedico;
    ControllerMarcarcaoConsulta controllerMarcarcaoConsulta;
    MarcacaoConsulta marcacaoConsulta = new MarcacaoConsulta();
    ControllerParametros controllerParametros;
    PedidoExames pedidoExames = new PedidoExames();
    ControllerPedidoExames controllerPedidoExames;

    public GuiaReguradora(int codigo) {
        initComponents();
        this.user = codigo;
//        con = new ConexaoBancos().ConexaoBD();
        controllerPedidoExames = new ControllerPedidoExames(con);
        controllerExamesporFazerItens = new ControllerExamesporFazerItens(con);
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        controllerUsuario = new ControllerUsuario(con);
        utentes = new ControllerUtente(con);
        controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
        controllerParametros = new ControllerParametros(con);
        controllerMedico = new ControllerMedico(con);
        controllerGuia = new ControllerFactura(con);
        controllerGuiatens = new ControllerFacturaItens(con);
        controllerSeguradora = new ControllerSeguradora(con);
        controllerServico = new ControllerServico(con);
        controllerEstatista = new ControllerEstatista(con);
        impostoMedico = controllerParametros.getValorIrtMedico();
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        jDateChooser3.setDate(new Date());

        jComboBox4.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodasSeguradoras().toArray()));
        jComboBox1.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodasSeguradoras().toArray()));
        jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicos().toArray()));
        jComboBox6.setModel(new DefaultComboBoxModel(controllerServico.getNomeCoPagamento().toArray()));
        jComboBox7.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedico().toArray()));
        //jComboBox2.setModel(new DefaultComboBoxModel(controllerGuia.getAnoemCurso().toUpperCase()));
        jComboBox2.addItem(controllerGuia.getAnoemCurso().toUpperCase());
        codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
        jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioSeguradora(codigoSeguro).toArray()));
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        mostrarGuiaProntas("SELECT f.idFactura as codigo,p.nomeCompleto as nome,date(dataFactura) as data, e.designacao as empresa, u.nomeCompleto as username FROM factura f inner join pacientes p on f.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on p.codigoSeguro = e.idSeguros\n"
                + "inner join utilizadores u on u.idUtilizador=f.codigoUtilizador\n"
                + "where f.codigoSeguro <>8 and f.estado='FACTURA CRÉDITO' AND date(dataFactura) = current_date order by 4");
        teclaInser();
        teclaInserCopagamento();
        teclaInserCopagamento1();
        addKeyListener(new LeitorTeclas());
        setLocationRelativeTo(null);
        iconeSistema();
    }

    public final void iconeSistema() {
        // URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public void setDesconto(String nome) {
        //  jTextFieldDesconto.setText(String.valueOf(utentes.getDescontoUtente(nome)));
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
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
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextPreco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldTotaPagar = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guia Médica");
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
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel11.setText("Por código");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel12.setText("Nome do(a) Cliente Cadastrado :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 350, -1));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel13.setText("Filtrar o nome do(a) Cliente:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 160, -1));

        jTextField2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 230, -1));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Seguradoras/Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel5.setText("Guia Referente ");

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
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
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
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1))))
                    .addComponent(jLabel5))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Co - Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        jTextPreco.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTextPreco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextPreco.setText("0");
        jTextPreco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor da Elegibilidade"));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel9.setText("Descrição");

        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nº Elegibilidade"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jTextPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
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
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1030, 240));

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
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 151, -1));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1040, 30));

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
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldTotaPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldTotaPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel10.setText("Ano:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel14.setText("Médico:");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Total a Desconto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11), new java.awt.Color(204, 0, 0))); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("0");
        jTextField8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "IVA"));

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldDesconto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldDesconto.setText("0.0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("%");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("Abrir Guia", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Buscar Guia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-search-16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Por Data");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Por Seguradora e Data");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton2)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRadioButton1)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-search-16.png"))); // NOI18N
        jButton6.setText("Visualizar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Guia", "Nome do Paciente", "Empresa/Seguradora", "Data", "Usúario"
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

        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-search-16.png"))); // NOI18N
        jButton7.setText("Recibo RG");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Guia Pronto", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField6CaretUpdate
        //calculoTotal();
    }//GEN-LAST:event_jTextField6CaretUpdate

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed

    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inserirTabela();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        if (flag == 1) {
            int linha = jTable1.getSelectedRow();
            if (linha != -1) {
                defaultTableModel.removeRow(jTable1.getSelectedRow());
//            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
                actualizarValorApagar();
                actualizar();

            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
            }
        }
        if (flag == 2) {
//            codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());
            codigoServico = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            controllerGuiatens.elimarItensGuia(codigoFactura, codigoServico);
            defaultTableModel.removeRow(jTable1.getSelectedRow());
            actualizarValorApagar();
            actualizar();

        }

    }//GEN-LAST:event_jLabel8MousePressed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        limparVenda();
        flag = 1;
    }//GEN-LAST:event_jComboBox5ActionPerformed

    public void actualizar() {

        BigDecimal res2 = new BigDecimal((totalGeral()));
        jTextFieldTotaPagar.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));
//        jLabel7.setText("" + (jTable1.getRowCount()));
        calculoTroco();
    }

    public void calculoTroco() {
//        double total_antes = removerPattern(jTextFieldTotaPagar.getText());
        // double total_antes = Double.parseDouble(jTextFieldTotaPagar.getText());
        double entregue_antes = 0;
//        double entregue = total_antes;
        double troco = 0;
        //  if (jTextFieldValorEntregue.getText() == "0") {
        //     troco = -(total_antes);
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
//        BigDecimal res2 = new BigDecimal(troco);
//        jTextFieldTroco.setText("" + res2.setScale(2, BigDecimal.ROUND_HALF_UP));

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
                valor = valor + (valorUnitario * 0);
            }
        }

        return valor;
    }

    public double getDescontoIVAporProduto() {
        double valor = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            if (codigoTaxa == 14) {
                valor = (valorUnitario * 0.14);
            } else {
                valor = (valorUnitario * 0);
            }
        }
        System.out.println("Desconto Produto IVA:" + valor);
        return valor;
    }

    public double getDescontoIVAporProdutoTotal() {
        double valor = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            if (codigoTaxa == 14) {
                valor += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
            } else {
                valor += 0 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
            }
        }
        System.out.println("Desconto Produto IVA:" + valor);
        return valor;
    }

    public double totalGeral() {
        total = 0;
        double d = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            d = removerPattern(String.valueOf(jTable1.getValueAt(i, 5)));
            total = total + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
//            d = Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 6)));
            // total += d;
        }
        return total;
    }

    public double totalDesconto() {
        return Double.valueOf(jTextPreco.getText());
    }

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

    public int getCodigoMedico() {
        return controllerUsuario.getCodigoUtilizador(jComboBox7.getSelectedItem().toString());
    }

    public int getCodigoMedico1() {
        return controllerUsuario.getCodigoMedico(jComboBox7.getSelectedItem().toString());
    }

    public void salvarColaboradores() {
        int codigoFactura = controllerGuia.getLastFactura();
        factura.setCodigoCliente(getCodigoMedicoColaboradores());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setTotalFactura(totalGeral());
        factura.setDataVencimento(getDataActual());
        factura.setEstado(String.valueOf(codigoFactura));
        controllerMedico.salvarHonorario(factura);

    }

    public void salvarMedicoHonorario() {
        Factura factura1 = new Factura();
        int codigofacto = controllerGuia.getLastFactura();
        System.out.println("Codigo Factura:" + codigofacto);
        System.out.println("Data:" + getData());
        factura1.setCodigoCliente(getCodigoMedico1());
        factura1.setCodigoUtilizador(getCodigoUtilizador());
        factura1.setDataVencimento(getData());
        factura1.setEstado(String.valueOf(codigofacto));
        controllerMedico.salvarHonorarioMedico1(factura1);

    }

    public void salvarServicoMedicosIntesSantaMarta() {
        System.out.println("Entrouuuuuuuuuu Aqui trste 1111111");
        FacturaItens f = new FacturaItens();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            codigoFactura = controllerMedico.getLastMedicoFacturaHonorario();
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            String Servico = controllerMedico.getMedicoServico(getCodigoMedico(), codigoProduto);
            System.out.println("Entrouuuuuuuuuu Aqui trste");
            System.out.println("Codigo Servico:" + Servico);
            if (Servico != null) {
                f.setCodigoFactura(codigoFactura);
                f.setCodigoServico(codigoProduto);
                f.setPreco(controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreco());
                System.out.println("Preço:" + f.getPreco());
                f.setPercentagem((controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreconormal() + controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPrecopercentual()));
                System.out.println("Percentagem:" + f.getPercentagem());
                double irt = (controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPreconormal() + controllerMedico.getServicoMedico(getCodigoMedico(), codigoProduto).get(0).getPrecopercentual()) * impostoMedico;
                f.setEcografia(irt);
                controllerMedico.salvarMedicoHonorarioItens(f);
            }

        }
    }

    public void salvarMarcacao() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoServico);
            if (codigoCategoria == 1) {
                int limitemedico = controllerMarcarcaoConsulta.getLimiteDiario(getCodigoMedico1());
                System.out.println("Limite do medico:" + limitemedico);
                int limiteconsulta = controllerMarcarcaoConsulta.getTotalConsultasAgendadasPorMedico(getCodigoMedico1(), getData());
                codigoSeguro = utentes.getCodigoSeguro(getCodigoCliente());
                double precoParticular = controllerServico.getPrecoParticular(jTable1.getValueAt(i, 1).toString());
                double ensa = controllerServico.getPrecoAdvance(jTable1.getValueAt(i, 1).toString());
                double saham = controllerServico.getPrecoSaham(jTable1.getValueAt(i, 1).toString());
                double angolatelecom = controllerServico.getPrecoAngolaTelecom(jTable1.getValueAt(i, 1).toString());
                double saudemais = controllerServico.getPrecoSaudemais(jTable1.getValueAt(i, 1).toString());
                double unisaude = controllerServico.getPrecoUnisaude(jTable1.getValueAt(i, 1).toString());
                double fidelidade = controllerServico.getPrecoFidelidade(jTable1.getValueAt(i, 1).toString());
                double anst = controllerServico.getPrecoEmpresa(jTable1.getValueAt(i, 1).toString());
                if (!controllerMarcarcaoConsulta.consultaExistente(getCodigoCliente(), getCodigoMedico1(), codigoServico, getData())) {
                    if (limiteconsulta <= limitemedico) {
                        marcacaoConsulta.setCodigoUtlizador(user);
                        marcacaoConsulta.setCodigoMedico(getCodigoMedico1());
                        marcacaoConsulta.setCodigoPaciente(getCodigoCliente());
                        marcacaoConsulta.setCodigoServico(codigoServico);
                        //    marcacaoConsulta.setDataAtendimento(String.);
                        marcacaoConsulta.setDataMarcacao(getData());
                        marcacaoConsulta.setHora(getData());
                        marcacaoConsulta.setObservacao("");
                        marcacaoConsulta.setEstado("Não urgente (Azul) - Atendimento em até 4 horas.");
                        marcacaoConsulta.setCodigoEstado(1);
                        if (codigoSeguro == 8 || codigoSeguro == 19) {
                            marcacaoConsulta.setPreco(precoParticular);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7 || codigoSeguro == 15) {
                            marcacaoConsulta.setPreco(ensa);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 9) {
                            marcacaoConsulta.setPreco(angolatelecom);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 10) {
                            marcacaoConsulta.setPreco(anst);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 11) {
                            marcacaoConsulta.setPreco(saham);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 12) {
                            marcacaoConsulta.setPreco(unisaude);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 13) {
                            marcacaoConsulta.setPreco(saudemais);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 14) {
                            marcacaoConsulta.setPreco(fidelidade);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }
                        if (codigoSeguro == 16) {
                            marcacaoConsulta.setPreco(anst);
                            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Consulta já foi marcada");
                }

            }
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (flag == 1) {
            if (!jComboBox7.getSelectedItem().equals(null)) {
                if (controllerGuia.getDataStistema().after(controllerGuia.getDataUltimaFactura()) || controllerGuia.getDataUltimaFactura().equals(controllerGuia.getDataStistema())) {
                    String horaUltimaFactura = controllerGuia.getHoraUltimaFactura();
                    if (getHoraMaior(horaUltimaFactura)) {
                        int numeroGuia = controllerSeguradora.getCodigoNumerador(jComboBox4.getSelectedItem().toString());
                        int numeradorSeguro = controllerGuia.getCodigoNumeradorEmpresa(jComboBox4.getSelectedItem().toString());
                        if (numeradorSeguro != 0) {
                            System.out.println("Entrou aqui");
                            salvarGuia(numeradorSeguro);
                            salvarItemGuia();
                            int codigofactura = controllerGuia.getLastFactura();
                            relatorioVenda.getFacturaGuia(codigofactura);
                            int quantidade = controllerParametros.getValorImpressao();
                            for (int i = 0; i < quantidade; i++) {
                                relatorioVenda.getFacturaticketseguradora(codigofactura);
                            }
                            inserirExame();
                            inserirEcografia(codigoFactura);
                            salvarMedicoHonorario();
                            salvarServicoMedicosIntesSantaMarta();
                            salvarEstatistica(codigofactura);
                            salvarEstatisticaItens();
                            limparVenda();
                            jComboBox1.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeCategoria().toArray()));
                            mostrarGuiaProntas("SELECT f.idFactura as codigo,p.nomeCompleto as nome,date(dataFactura) as data, e.designacao as empresa, u.nomeCompleto as username FROM factura f inner join pacientes p on f.codigoCliente =p.idPaciente\n"
                                    + "inner join empresaseguros e on p.codigoSeguro = e.idSeguros\n"
                                    + "inner join utilizadores u on u.idUtilizador=f.codigoUtilizador\n"
                                    + "where f.codigoSeguro <>8 and f.estado='FACTURA CRÉDITO' AND date(dataFactura) = current_date order by 4");
//                jComboBox2.setModel(new DefaultComboBoxModel(controllerGuia.getNumeroGuia(getData2(), jComboBox1.getSelectedItem().toString()).toArray()));
                        } else {
                            inserirExame();
                            salvarGuia(numeroGuia);
                            salvarItemGuia();
                            int codigofactura = controllerGuia.getLastFactura();
                            inserirEcografia(codigoFactura);
                            salvarMedicoHonorario();
                            salvarServicoMedicosIntesSantaMarta();
                            salvarEstatistica(numeroGuia);
                            salvarEstatisticaItens();

                            relatorioVenda.getFacturaGuia(codigofactura);
                            int quantidade = controllerParametros.getValorImpressao();
                            for (int i = 0; i < quantidade; i++) {
                                relatorioVenda.getFacturaticketseguradora(codigofactura);
                            }

                            limparVenda();
                            jComboBox1.setModel(new DefaultComboBoxModel(controllerSeguradora.getNomeCategoria().toArray()));
                            mostrarGuiaProntas("SELECT f.idFactura as codigo,p.nomeCompleto as nome,date(dataFactura) as data, e.designacao as empresa, u.nomeCompleto as username FROM factura f inner join pacientes p on f.codigoCliente =p.idPaciente\n"
                                    + "inner join empresaseguros e on p.codigoSeguro = e.idSeguros\n"
                                    + "inner join utilizadores u on u.idUtilizador=f.codigoUtilizador\n"
                                    + "where f.codigoSeguro <>8 and f.estado='FACTURA CRÉDITO' AND date(dataFactura) = current_date order by 4");
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Não é permitido emitir factura com data anterior, verifica a data do computador!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insira nome do Médico", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (flag == 2) {
            //   codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());
            if (controllerGuia.existeGuia(codigoFactura)) {
                editarGuia(codigoFactura);
                controllerGuiatens.eliminar(codigoFactura);
                salvarItemGuia1(codigoFactura);
                controllerEstatista.eliminar(codigoFactura);
                salvarEstatisticaItens1(codigoFactura);
                relatorioVenda.getFacturaGuia(codigoFactura);
                int quantidade = controllerParametros.getValorImpressao();
                for (int i = 0; i < quantidade; i++) {
                    relatorioVenda.getFacturaticketseguradora(codigoFactura);
                }
                // relatorioVenda.getFacturaGuia(codigoFactura);
                limparVenda();
                System.out.println("Entrou");
            }

        }


    }//GEN-LAST:event_jButton5ActionPerformed

    public void inserirEcografia(int codigoFactura) {
        pedidoExames.setCodigoPaciente(getCodigoCliente());
        pedidoExames.setCodigoMedico(getCodigoMedico1());
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
    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        if (!jTextField1.getText().isEmpty()) {
            codigo = Integer.parseInt(jTextField1.getText());
            codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getCodigoNomeSeguro(codigo, codigoSeguro).toArray()));
        }
    }//GEN-LAST:event_jTextField1CaretUpdate

    public boolean salvarPedidoExameItem(int codigoExame, int codProduto, int codigoStatus, int codigoProdutoIem, int quantidade) {
        ExamesPorFazerItem item = new ExamesPorFazerItem(codigoExame, codProduto, codigoStatus, codigoProdutoIem, quantidade);
        controllerExamesporFazerItens.create(item);
        return true;
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
        exame.setDataPedido(getData());
        exame.setQuantidade(quantidade);
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
                    }
                    if (codigoProdutoItem == 0) {
                        salvarPedidoExameItem(codigoUltimoExamePorFazer, codigoServico, 1, 0, quantidade);
                        itemGravado = true;
                    }

                }
            }
        }

    }

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        if (!jTextField3.getText().isEmpty()) {
            codigo = Integer.parseInt(jTextField3.getText());
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicoporCodigo(codigo).toArray()));
        }
    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField4CaretUpdate
        if (!jTextField4.getText().isEmpty()) {
            jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicosPorlike(jTextField4.getText()).toArray()));
        }
    }//GEN-LAST:event_jTextField4CaretUpdate

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        if (!jTextField2.getText().isEmpty()) {
            codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioBuscarSeguro(jTextField2.getText(), codigoSeguro).toArray()));
        }
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        flag = 1;
        codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
        System.out.println("Codigo Seguro:" + codigoSeguro);
        jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNomeBenefiarioSeguradora(codigoSeguro).toArray()));
        jComboBox1.setModel(new DefaultComboBoxModel(controllerGuia.getNomeGuia(jComboBox4.getSelectedItem().toString()).toArray()));
        limparVenda();
        //  ArrayList<String> nome = controllerGuia.getNomeGuia(jComboBox4.getSelectedItem().toString());
//        jComboBox2.setModel(new DefaultComboBoxModel(controllerGuia.getNumeroGuia(getData2(), jComboBox1.getSelectedItem().toString().trim()).toArray()));


    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //    jComboBox2.setModel(new DefaultComboBoxModel(controllerGuia.getNumeroGuia(getData2(), jComboBox1.getSelectedItem().toString()).toArray()));

    }//GEN-LAST:event_jComboBox1ActionPerformed

    public String getData1() {
        return d.converteDataSql(jDateChooser2.getDate()).toString();
    }

    public String getData2() {
        return d.converteDataSql(jDateChooser3.getDate()).toString();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mostrarGuiaProntas("SELECT f.idFactura as codigo,p.nomeCompleto as nome,date(dataFactura) as data, e.designacao as empresa, u.nomeCompleto as username FROM factura f inner join pacientes p on f.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on p.codigoSeguro = e.idSeguros\n"
                + "inner join utilizadores u on u.idUtilizador=f.codigoUtilizador\n"
                + "where date(dataFactura) between '" + getData1() + "' and '" + getData2() + "' AND f.codigoSeguro <>8 and f.estado='FACTURA CRÉDITO'\n"
                + "and e.designacao='" + jComboBox1.getSelectedItem().toString() + "' order by 4");
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        String data = jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString();
        relatorioVenda.getFacturaGuiaSegundaVia(codigoFactura);
//        if (controllerGuia.getData().equals(data)) {
////        if (getDataActual() == controllerGuia.getDataActura(codigoFactura)) {
//            relatorioVenda.getFacturaGuia(codigoFactura);
//            //  relatorioVenda.getFacturaGuia(codigoFactura);
//        } else {
//
//        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        if (evt.getClickCount() == 2) {
            JOptionPane.showMessageDialog(null, "Não lhe é permitido alterar factura uma vez assinada!", "Mind Vision Tecnology", JOptionPane.ERROR_MESSAGE);
        }

//        if (evt.getClickCount() == 2) {
//            // Angola Telecom
//            flag = 2;
//
//            codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
//            String nomePaciente = "" + jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString();
//            String nomeSegura = "" + jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString();
//            codigoSeguro = controllerSeguradora.getCodigoSeguradora(nomeSegura);
//            System.out.println("Codigo Seguro:" + codigoSeguro);
//            jComboBox4.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodaporNome(nomeSegura).toArray()));
//            jComboBox5.setModel(new DefaultComboBoxModel(utentes.getNome_Segura(codigoSeguro, nomePaciente).toArray()));
//            if (codigoSeguro == 9) {
//                mostrarFacturaAT("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco6, gt.quantidade,t.taxa,gt.descontoProduto,gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Advance Care Seguradora
//            if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7) {
//                mostrarFacturaEnsa("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco1, gt.quantidade,t.taxa,gt.descontoProduto, gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Seguragora SAHAM
//            if (codigoSeguro == 10) {
//                mostrarFacturaSAHAM("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco5, gt.quantidade,t.taxa, gt.descontoProduto, gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo \n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Seguragora UniSaude
//            if (codigoSeguro == 12) {
//                mostrarFacturaUnisaude("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco2, gt.quantidade,t.taxa,gt.descontoProduto, gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo \n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Seguragora Saude +
//            if (codigoSeguro == 13) {
//                mostrarFacturaSaudeMais("SELECT DISTINCT g.idFactura, s.idServico,s.designacao,s.preco3, gt.quantidade,t.taxa,gt.descontoProduto,gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Fidelidade
//            if (codigoSeguro == 14) {
//                mostrarFacturaSaudeFinalidade("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco4, gt.quantidade,t.taxa, gt.descontoProduto,gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Master Seguros
//            if (codigoSeguro == 16) {
//                mostrarFacturaMasterSeguros("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco7, gt.quantidade,t.taxa, gt.descontoProduto,gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // MedisPuls
//            if (codigoSeguro == 17) {
//                mostrarFacturaAT("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco6, gt.quantidade,t.taxa, gt.descontoProduto, gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//            // Prodencial
//            if (codigoSeguro == 18) {
//                mostrarFacturaSAHAM("SELECT DISTINCT g.idFactura, s.idServico,s.designacao, s.preco5, gt.quantidade, t.taxa,gt.descontoProduto,gt.totalGeral FROM factura g inner join factura_itens gt on g.idFactura =gt.codigoFactura\n"
//                        + "inner join servicos s on gt.codigoProduto =idServico inner join tipotaxa t on s.codigoTipoTaxa=t.codigo\n"
//                        + "where g.idFactura ='" + codigoFactura + "'");
//                flag = 2;
//                jTabbedPane1.setSelectedIndex(0);
//            }
//
//        }
//        actualizarValorApagar();
//        actualizar();

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        codigoFactura = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        String guia = controllerGuia.getCodigoRecibo(codigoFactura);
        if (!guia.isEmpty()) {
            relatorioVenda.getRecibo1(codigoFactura);
        } else {
            controllerGuia.updateRecibo(codigoFactura);
            relatorioVenda.getRecibo(codigoFactura);
        }


    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        jTable1.setValueAt(String.valueOf(getValorMonetario(getDescontoMeto(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString(), getValorNormal(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString())))), jTable1.getSelectedRow(), 5);
        updateDesconto();
        jTextFieldDesconto.setText("0.0");
        actualizaPreco();
        actualizarValorApagar();
        actualizarValorIva();

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyTyped

    public String getValorMonetario(Double valor) {
        return Calculo.converterCash(valor);
    }

    public double getValorNormal(String valor) {
        return Calculo.getValueNormal(valor);
    }

    public double getDescontoMeto(String value, Double preco) {
        return Calculo.desconto(value, preco, this);
    }

    public void actualizarValorApagar() {
        double valor, valorTotal = 0;
        double valorIVA = 0;
        double troco = 0;
        double desconto = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
            if (codigoTaxa == 14) {
                valorIVA += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
            }
            valorTotal = valorTotal + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
            desconto = desconto + (getValorNormal(jTable1.getValueAt(i, 5).toString()));

//                valorTotal = valorTotal + valor;
////                    System.out.println("Total:" + valorTotal);
//                jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
//                //  troco = Double.parseDouble(0.0);
//            } else {
//                valor = Double.valueOf(jTable1.getValueAt(i, 3).toString());
//                valorTotal = valorTotal + valor;
//                System.out.println("Total:" + valorTotal);
            jTextField8.setText(String.valueOf(desconto));
            jTextFieldTotaPagar.setText(String.valueOf(valorTotal));
            //  troco = Double.parseDouble(0.0);
        }

    }

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

    public void actualizaPreco() {
        double toal_geral = 0, subTotal = 0;
        int codigoServico, codigoTaxa;
        double total = 0, total_iva = 0, desconto = 0;
        double Totalgeral = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            codigoServico = controllerServico.getCodigoServico(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString());
            codigoTaxa = controllerServico.getCodigoTaxa(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
            // codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);

            if (codigoTaxa == 14) {

                total_iva += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
                toal_geral = toal_geral + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
//                toal_geral = toal_geral + getValorNormal(jTable1.getValueAt(i, 3).toString()) + total_iva;
                System.out.println("Total IVA:" + total_iva);
                System.out.println("Total Geral:" + toal_geral);
                //*0.14;
            } else {
                desconto += getValorNormal(jTable1.getValueAt(i, 5).toString());
                //    toal_geral += getValorNormal(jTable1.getValueAt(i, 3).toString());
                total_iva += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
            }

        }
        //  Totalgeral = toal_geral- desconto;
        jTextFieldTotaPagar.setText("" + getValorMonetario(toal_geral));
        jTextFieldDesconto.setText("" + getValorMonetario(total_iva));
        jTextField8.setText("" + getValorMonetario(desconto));
    }

//    public double getDescontoPersentagem() {
//        double percet = 0;
//        try {
//            if (!jTextFieldDesconto.getText().equals("")) {
//                double desconto = Double.parseDouble(jTextFieldDesconto.getText());l
//                if (desconto != 0) {
//                    desconto = (desconto / 100);
//                    percet = (double) (getValorApagar() * desconto);
//                }
//            }
//
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
//
//        return percet;
//    }
    public String getElegibilidade() {
        return jTextField5.getText();
    }

    public double getValorApagar() {
        double valor = 0;
        try {
            if (!jTextFieldTotaPagar.getText().equals("")) {
                valor = Double.parseDouble(jTextFieldTotaPagar.getText());
            }

        } catch (NumberFormatException ex) {
            ex.getMessage();
        }
        return valor;
    }

    public int getCodigoUtilizador() {
        return user;
    }

    public int getCodigoCliente() {
        return utentes.getCodigoUtente1(jComboBox5.getSelectedItem().toString(), codigoSeguro);
    }

    public void inserirTabela() {
        Object fila[] = new Object[7];
        double preco = 0.0, totalPreco = 0.0;
        double IVA = 0;
        int quantidade = Integer.parseInt(jTextField6.getText());
        codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
        codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
        double precoDesconto = controllerSeguradora.getDesconto(codigoSeguro);
        // Advance Care - Seguradora
        codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
        int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
        if (codigoTaxa == 14) {
            //  IVA = "14%";
            if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7) {
                preco = controllerServico.getPrecoAdvance(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAdvance(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // Angola Telecom = 9
            if (codigoSeguro == 9) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            if (codigoSeguro == 11) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }

            if (codigoSeguro == 10) {
                preco = controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // UNISAÚDE Seguradora = 12
            if (codigoSeguro == 12) {
                preco = controllerServico.getPrecoUnisaude(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoUnisaude(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // SAÚDE + Seguradora = 13
            if (codigoSeguro == 13) {
                preco = controllerServico.getPrecoSaudemais(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoSaudemais(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // FIDELIDADE + Seguradora = 14
            if (codigoSeguro == 14) {
                preco = controllerServico.getPrecoFidelidade(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoFidelidade(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // master seguro + Seguradora = 16
            if (codigoSeguro == 16) {
                preco = controllerServico.getPrecoEmpresa(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoEmpresa(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            // MediPlus + Seguradora = 17
            if (codigoSeguro == 17) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoEmpresa(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            totalPreco = preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
            // Prodiencial
            if (codigoSeguro == 18) {
                preco = controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            if (codigoSeguro > 10) {
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }
            if (codigoSeguro == 8) {
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0.14;
            }

        } else {

            if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7) {
                preco = controllerServico.getPrecoAdvance(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAdvance(jComboBox3.getSelectedItem().toString()) * quantidade * 0;
                IVA = (preco * quantidade) * 0;
            }
            // Angola Telecom = 9
            if (codigoSeguro == 9) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0;
                IVA = (preco * quantidade) * 0;
            }
            if (codigoSeguro == 10) {
                preco = controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString()) * quantidade * 0;
                IVA = (preco * quantidade) * 0;
            }
            if (codigoSeguro == 11) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0;
                IVA = (preco * quantidade) * 0;
            }
            if (codigoSeguro == 12) {
                preco = controllerServico.getPrecoUnisaude(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoUnisaude(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;
            }
            // SAÚDE + Seguradora = 13
            if (codigoSeguro == 13) {
                preco = controllerServico.getPrecoSaudemais(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoSaudemais(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;

            }
            //FIDELIDADE + Seguradora = 14
            if (codigoSeguro == 14) {
                preco = controllerServico.getPrecoFidelidade(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoFidelidade(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;
            }
            // master seguro + Seguradora = 16
            if (codigoSeguro == 16) {
                preco = controllerServico.getPrecoEmpresa(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoEmpresa(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;
            }
            // MediPlus + Seguradora = 17
            if (codigoSeguro == 17) {
                preco = controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoAngolaTelecom(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;

            }
            // Prodiencial
            if (codigoSeguro == 18) {
                preco = controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPrecoSaham(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;
            }
            if (codigoSeguro > 18) {
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = (preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0);
                IVA = (preco * quantidade) * 0;
            }
            if (codigoSeguro == 8) {
                preco = controllerServico.getPreco(jComboBox3.getSelectedItem().toString());
                totalPreco = preco * quantidade + controllerServico.getPreco(jComboBox3.getSelectedItem().toString()) * quantidade * 0.14;
                IVA = (preco * quantidade) * 0;
            }

        }

        fila[0] = codigoServico;
        fila[1] = jComboBox3.getSelectedItem().toString();
        fila[2] = quantidade;
        fila[3] = preco;
        fila[4] = getValorMonetario(IVA);
        fila[5] = precoDesconto * preco;
        fila[6] = totalPreco - (preco * quantidade * precoDesconto);
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(3).setResizable(!false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        actualizarValorApagar();
        actualizarValorIva();
        actualizar();

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

    public void actualizarValorIva() {

        double valor, valorTotal = 0;
        String troco = null;
        if (jTable1.getRowCount() != 0) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                int quantidade = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
                int codigoTaxa = controllerServico.getCodigoTaxa(codigoServico);
                if (codigoTaxa == 14) {

                    valorTotal += 0.14 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));
                    //  valorTotal = valorTotal + (getValorNormal(jTable1.getValueAt(i, 6).toString()));
                    System.out.println("Total IVA:" + valorTotal);
                } else {
                    //desconto += getValorNormal(jTable1.getValueAt(i, 5).toString());
                    valorTotal += 0 * (getValorNormal(jTable1.getValueAt(i, 3).toString()) - getValorNormal(jTable1.getValueAt(i, 5).toString()));

                }

            }

        }
        jTextFieldDesconto.setText("" + getValorMonetario(valorTotal));

    }

    public void inserirTabelaCopagamento() {
        Object fila[] = new Object[6];
        preco = 0.0;
        codigoServico = controllerServico.getCodigoServico(jComboBox6.getSelectedItem().toString());
        codigoSeguro = controllerSeguradora.getCodigoSeguradora(jComboBox4.getSelectedItem().toString());
        preco = Double.valueOf(jTextPreco.getText());
        int quantidade = Integer.parseInt(jTextField6.getText());
        fila[0] = codigoServico;
        fila[1] = jComboBox6.getSelectedItem().toString();
        fila[2] = quantidade;
        fila[3] = preco;
        fila[4] = preco * 0;
        fila[5] = preco * quantidade;
        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(3).setResizable(!false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

//    public double getDesconto() {
//        double valor = 0;
//        try {
//            if (!jTextFieldDesconto.getText().equals("")) {
//                valor = Double.parseDouble(jTextFieldDesconto.getText());
//                System.out.println("Valor do Desconto:" + valor);
//            } else {
//                jTextFieldDesconto.setText("0");
//            }
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        }
//        return valor;
//    }
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

    public final void teclaInserCopagamento() {
        jTextPreco.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabelaCopagamento();

                }
            }

        });
    }

    public final void teclaInserCopagamento1() {
        jTextField5.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabelaCopagamento();
                    jTextPreco.setText("");
                }
            }

        });
    }

//    public double getDescontoIVA() {
//        double valor = 0;
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
//            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
//            if (codigoTaxa == 14) {
//                valor = valor + (valorUnitario * 0.14);
//            } else {
//                valor = valor + (valorUnitario * 0.14);
//            }
//        }
//
//        return valor;
//    }
//    public double getDesconto() {
//        double valor = 0;
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
//        return valor;
//    }
    public String getDataFacturaInicio() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(jDateChooser1.getDate());
        String dataSelecionada = gc.get(GregorianCalendar.YEAR) + "-"
                + ((gc.get(GregorianCalendar.MONTH) + 1) < 10 ? "0" + (gc.get(GregorianCalendar.MONTH) + 1) : (gc.get(GregorianCalendar.MONTH) + 1)) + "-"
                + (gc.get(GregorianCalendar.DATE) < 10 ? "0" + gc.get(GregorianCalendar.DATE) : gc.get(GregorianCalendar.DATE));

        return dataSelecionada;
    }

    public double getTotalDesconto() {
        return Calculo.getValueNormal(jTextField8.getText());
    }

    public void salvarGuia(int numerador) {
        codigo = 0;
        codigo = numerador + 1;
        double descontoUnitario = 0;
        System.out.println("Teste:" + codigo);
        factura.setCodigoCliente(getCodigoCliente());
        factura.setCodigoUtilizador(getCodigoUtilizador());
        factura.setDesconto(totalDesconto());

//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            descontoUnitario = descontoUnitario + Double.parseDouble(String.valueOf(jTable1.getValueAt(i, 5).toString()));
//        }
        factura.setValorApagar(totalGeral() - totalDesconto());
        factura.setNumerador(codigo);
        factura.setCodigoFormaPagamento(6);
        factura.setQuantidadeServico(getQuantidadeGeral());
        factura.setNomeCliente(jComboBox5.getSelectedItem().toString());
        factura.setCodigoSeguro(codigoSeguro);
        factura.setDescontoFactura(getTotalDesconto());
        factura.setNumOrdem(jTextField5.getText());
        factura.setAviaoNavio(jComboBox7.getSelectedItem().toString());
        factura.setDescontoIVA(getDescontoIVAporProdutoTotal());
        factura.setDataVencimento(getDataFacturaInicio());
        factura.setEstado("FACTURA CRÉDITO");
        controllerGuia.salvarGuia(factura);

    }

    public void editarGuia(int codigoFactura) {
        //   codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());
        double descontoTotal = 0.0;

        controllerGuia.update(getValorApagar() + getTotalDesconto() + getDescontoIVAporProdutoTotal(), getTotalDesconto(), codigoFactura, getTotalDesconto(), getDescontoIVAporProdutoTotal());
        // }

    }

    public void salvarEstatistica(int codigoFactura) {
        ModeloEstatistica modeloEstatistica = new ModeloEstatistica();
        modeloEstatistica.setCodigoFactura(String.valueOf(codigoFactura));
        modeloEstatistica.setCodigoPaciente(getCodigoCliente());
        controllerEstatista.salvar(modeloEstatistica);
    }

    public void salvarItemGuia() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoFactura = controllerGuia.getLastFactura();
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
            double valor = 0, totalGeral = 0;
            double preco = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            //   double descontoUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 5).toString());
            double valorUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 3).toString());
            double descontoUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 5).toString());
            //      totalGeral = totalGeral+();
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            if (codigoTaxa == 14) {
                valor = valor + (preco - descontoUnitario) * 0.14;
            } else {
                valor = valor + (preco - descontoUnitario) * 0;
            }
            facturaItens.setPreco(valorUnitario - descontoUnitario);
            facturaItens.setDescontoIVA(valor);
            facturaItens.setCodigoGuia(codigoFactura);
            facturaItens.setCodigoServico(codigoProduto);
            facturaItens.setCodigoCategoriaServico(codigoCategoria);
            facturaItens.setQuantidade(quantidadde);
            facturaItens.setTotalgeral(valorUnitario);
            facturaItens.setDesconto(descontoUnitario);
            controllerGuiatens.salvarItensGuia(facturaItens);
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

    public void salvarItemGuia1(int codigoFactura) {
        String elegibilidade = "";
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            double valor = 0, totalGeral = 0;
            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
            double preco = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
            double valorUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 3).toString());
            double descontoUnitario = Calculo.getValueNormal(jTable1.getValueAt(i, 5).toString());
            int codigoCategoria = controllerServico.getCodigoCategoriaServico(codigoProduto);
            int codigoTaxa = controllerServico.getCodigoTaxa(codigoProduto);
            if (codigoTaxa == 14) {
                valor = valor + (preco - descontoUnitario) * 0.14;
            } else {
                valor = valor + (preco - descontoUnitario) * 0;
            }
            facturaItens.setCodigoGuia(codigoFactura);
            facturaItens.setCodigoServico(codigoProduto);
            facturaItens.setQuantidade(quantidadde);
            facturaItens.setTotalgeral(valorUnitario);
            facturaItens.setDesconto(descontoUnitario);
            facturaItens.setPreco(valorUnitario - descontoUnitario);
            facturaItens.setDescontoIVA(valor);
            facturaItens.setCodigoCategoriaServico(codigoCategoria);
            facturaItens.setTotalgeral(valorUnitario);
            controllerGuiatens.salvarItensGuia(facturaItens);
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

    public void salvarEstatisticaItens() {
        //      int codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());
        int codigoEstatistica = controllerEstatista.getLastEstatistica();
        if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 1, 0, 0, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        //UNISAUDE
        if (codigoSeguro == 12) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 1, 0, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        //SAUDE - MAIS
        if (codigoSeguro == 13) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 1, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // FIDELIDADE
        if (codigoSeguro == 14) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 1, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // MASTER SEGUROS
        if (codigoSeguro == 16) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 1, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // ANSH
        if (codigoSeguro == 10) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 0, 0, 1, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // ANGOLA TELECOM
        if (codigoSeguro == 9) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 0, 1, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // SAHAM
        if (codigoSeguro == 11) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 1, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }

    }

    public void salvarEstatisticaItens1(int codigoEstatistica) {
        //      int codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());

        if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 1, 0, 0, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        //UNISAUDE
        if (codigoSeguro == 12) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 1, 0, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        //SAUDE - MAIS
        if (codigoSeguro == 13) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 1, 0, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // FIDELIDADE
        if (codigoSeguro == 14) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 1, 0, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // MASTER SEGUROS
        if (codigoSeguro == 16) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 1, 0, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // ANSH
        if (codigoSeguro == 10) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 0, 0, 1, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // ANGOLA TELECOM
        if (codigoSeguro == 9) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 0, 1, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }
        // SAHAM
        if (codigoSeguro == 11) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                ModeloEstatistica modeloEstatistica = new ModeloEstatistica(jTable1.getValueAt(i, 1).toString(), codigoProduto, 0, 0, 0, 0, 0, 0, 1, 0, 0, codigoEstatistica);
                controllerEstatista.salvarItensEstatisitca(modeloEstatistica);
            }
        }

    }

//    public void editarItemGuia() {
//        int codigoFactura = Integer.parseInt(jComboBox2.getSelectedItem().toString());
//        boolean flagGuia;
//        for (int i = 0; i < jTable1.getRowCount(); i++) {
//            int codigoProduto = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
//            int quantidadde = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
//            double valorUnitario = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
//            facturaItens.setCodigoServico(codigoProduto);
//            facturaItens.setQuantidade(quantidadde);
//            facturaItens.setTotalgeral(valorUnitario);
//            if (controllerGuia.existeGuiaProduto(codigoFactura, Integer.parseInt(jTable1.getValueAt(i, 0).toString()))) {
//                System.out.println("Codigo do Produto: " + Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
//                controllerGuiatens.updateGuiaItens(facturaItens, codigoFactura, codigoProduto);
//            }
//        }
//
//    }
    public void calculoTotal() {
        //  double antes = removerPattern(jTextField5.getText());
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

    public void limparVenda() {
        jTextFieldTotaPagar.setText("0");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField6.setText("1");
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
            java.util.logging.Logger.getLogger(GuiaReguradora.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiaReguradora.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiaReguradora.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiaReguradora.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //  new Tesouraria().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.plaf.JCalendarTheme jCalendarTheme1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    public javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldTotaPagar;
    private javax.swing.JTextField jTextPreco;
    // End of variables declaration//GEN-END:variables
private class LeitorTeclas implements KeyListener {

        @Override
        public final void keyPressed(KeyEvent arg0) {
            switch (arg0.getKeyCode()) {
                case (KeyEvent.VK_F5):
//                    salvarGuia();
//                    salvarItemGuia();
//                    int codigoFactura = controllerGuia.getLastGuia();
//                    limparVenda();
//                    jComboBox1.setModel(new DefaultComboBoxModel(controllerGuia.getNomeGuia(jComboBox4.getSelectedItem().toString()).toArray()));

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

    public void mostrarFacturaEnsa(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
//            rs = conexao.ejecutarSQLSelect(sql);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco1"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }
        actualizar();
        actualizarValorApagar();

    }

    public void mostrarFacturaAT(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco6"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostrarFacturaSAHAM(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco5"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostrarFacturaUnisaude(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco2"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }
    }

    public void mostrarFacturaSaudeMais(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco3"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostrarFacturaSaudeFinalidade(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco4"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostrarFacturaMasterSeguros(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco7"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostrarFacturaANST(String sql) {
        System.out.println("Teste:" + sql);
        try {
            this.con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(!false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(160);
            jTable1.getColumnModel().getColumn(3).setResizable(!false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("s.idServico"), rs.getString("s.designacao"), rs.getString("gt.quantidade"),
                    rs.getString("s.preco1"), rs.getString("t.taxa"), rs.getString("gt.descontoProduto"), rs.getString("gt.totalGeral")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public final void mostrarGuiaProntas(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(180);
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getTableHeader().setReorderingAllowed(false);
            jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);
            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("codigo"), rs.getString("nome"), rs.getString("empresa"),
                    rs.getString("data"), rs.getString("username")

                });
            }
        } catch (Exception ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public int getCodigoMedicoColaboradores() {
        return 1;
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
            facturaItens1.setCodigoFactura(codigoFactura);
            facturaItens1.setCodigoServico(codigoProduto);
            facturaItens1.setPreco(valorUnitario);
            facturaItens1.setLaboratorio(lab);
            facturaItens1.setRaioX(raioX);
            facturaItens1.setPercentagem(lab);
            facturaItens1.setConsulta(consulta);
            facturaItens1.setEcocardiograma(ecocardiograma);
            facturaItens1.setElectrocardiograma(electrocardiograma);
            facturaItens1.setEcografia(ecografia);
            facturaItens1.setEcografia7(ecografia7);
            facturaItens1.setEcografia10(ecografia10);
            facturaItens1.setEcografiaMorfologia(ecografiamorfologica);
            controllerMedico.salvarHonorarioItens(facturaItens1);

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
