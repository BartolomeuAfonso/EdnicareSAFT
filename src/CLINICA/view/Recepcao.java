/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerMarcarcaoConsulta;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import CLINICA.controller.ControllerNaturalidade;
import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerSeguradora;
import CLINICA.controller.ControllerEmpresa;
import CLINICA.modelo.MarcacaoConsulta;
import CLINICA.relatorios.RelatorioServicos;
import CLINICA.modelo.Utente;
import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ListSelectionModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import CLINICA.relatorios.RelatorioPaciente;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author El Router
 */
public class Recepcao extends javax.swing.JFrame {

//    private String extensao = ".xml";
    private DefaultTableModel defaultTableModel;
    private DecimalFormat decimalformat;
    Utente beneficiario = new Utente();
    RelatorioServicos relatorioServicos = new RelatorioServicos();
    ControllerMarcarcaoConsulta controllerMarcarcaoConsulta;
    ControllerNaturalidade controllerNaturalidade;
    MarcacaoConsulta marcacaoConsulta = new MarcacaoConsulta();
    ControllerUtente controllerBeneficiario;
    ControllerSeguradora controllerSeguradora;
    ControllerEmpresa controllerEmpresa;
    ControllerUsuario controllerUsuario;
    ControllerServico controllerServico;
    Data d = new Data();
    ConexaoBancos conexao;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    RelatorioPaciente relatorioPaciente = new RelatorioPaciente();
    String nomePaciente;
    int codigoUser;
    int tipoArea;
    int tipoUtilizador;
    String nomeempresa;
//    SaftXML saftXML = new SaftXML();

    public Recepcao(int codigoUsuario, int tipoArea) {
        initComponents();
        codigoUser = codigoUsuario;
        System.out.println("Codigo do Usuario:" + codigoUsuario);

        //  con = new ConexaoBancos().ConexaoBD();
        controllerNaturalidade = new ControllerNaturalidade(con);
        controllerBeneficiario = new ControllerUtente(con);
        controllerUsuario = new ControllerUsuario(con);
        controllerServico = new ControllerServico(con);
        controllerSeguradora = new ControllerSeguradora(con);
        controllerServico = new ControllerServico(con);
        controllerEmpresa = new ControllerEmpresa(con);
        nomeempresa = controllerEmpresa.getNomeEmpresa();
        this.tipoUtilizador = controllerUsuario.getTipoUtilizadorporID(codigoUser);
        
        jDateChooser2.setDate(new Date());
        this.tipoArea = tipoArea;
        
        if (tipoArea == 1) {
            controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
            // defaultTableModel = (DefaultTableModel) jTable2.getModel();

            decimalformat = new DecimalFormat("#,##0.00");
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(true);
            jDateChooser1.setDate(new Date());
            jComboBox8.setModel(new DefaultComboBoxModel(controllerNaturalidade.getNaturalidade().toArray()));
            jComboBox2.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodasSeguradoras().toArray()));
            jComboBox5.setModel(new DefaultComboBoxModel(controllerServico.getNomeServicoHonorario().toArray()));
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
            jComboBox7.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
            jComboBox3.setModel(new DefaultComboBoxModel(controllerUsuario.getNomeMedico().toArray()));
            if (jRadioButton2.isSelected()) {
                jPanel2.setVisible(false);
                
            }
            if (!jCheckBox1.isSelected()) {
                jPanel5.setVisible(false);
                jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
            }
            mostrarPaciente("SELECT DISTINCT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                    + "on p.codigoSeguro = e.idSeguros AND p.codigoStatus=1 group by 1 desc limit 50");
        }
        if (tipoArea == 2) {
            controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
            jComboBox8.setModel(new DefaultComboBoxModel(controllerNaturalidade.getNaturalidade().toArray()));
            jComboBox2.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodasSeguradoras().toArray()));
            jComboBox5.setModel(new DefaultComboBoxModel(controllerServico.getConsultaMarcao().toArray()));
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
            jComboBox7.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
            mostrarPaciente("SELECT DISTINCT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                    + "on p.codigoSeguro = e.idSeguros AND p.codigoStatus=1 limit 100");
            jPanel3.setVisible(true);
            jPanel6.setVisible(true);
            jButton1.setVisible(false);
            jButton13.setVisible(false);
            jCheckBox1.setVisible(false);
            jLabel23.setVisible(false);
            jLabel24.setVisible(false);
            jTextField1.setVisible(false);
            jLabel4.setText("Empresa");
            jTextField6.setVisible(false);
            jPanel2.setVisible(false);
            jPanel5.setVisible(false);
            jButton8.setVisible(false);
            jButton7.setVisible(false);
            jButton15.setVisible(false);
            jButton6.setVisible(false);
            jTabbedPane1.setTitleAt(0, "Dado do Cliente");
            jTabbedPane1.setTitleAt(1, "Lista do Clientes");
            
        }
        // Comentar esse opção para clínicas excepto ao centro médico Octávio.
        if (tipoUtilizador == 4) {
            jRadioButton3.setVisible(false);
        }
        iconeSistema();
        teclaInser();
        
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        btSalvar = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        minuto = new javax.swing.JSpinner();
        segundos = new javax.swing.JSpinner();
        hora = new javax.swing.JSpinner();
        jButton9 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jTextField7 = new javax.swing.JTextField();
        jRadioButton10 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RECEPÇÃO");
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Pessoais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("*Buscar:");
        jPanel17.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 20));

        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 380, -1));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 290, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Naturalidade:");
        jPanel17.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, -1));

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 380, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("* Morada");
        jPanel17.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 60, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Estado civil:");
        jPanel17.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nascimento:");
        jPanel17.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jLabel17.setText("Sexo:");
        jPanel17.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 40, 20));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMININO" }));
        jPanel17.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 100, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLTEIRO(A)", "CASADO(A)", "DIVORCIADO(A)", "VIUVO(A)" }));
        jPanel17.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 130, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("* Telefone:");
        jPanel17.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 70, 20));
        jPanel17.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 380, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel17.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 380, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Pai:");
        jPanel17.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Mâe:");
        jPanel17.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, -1, -1));

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 290, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("E-mail:");
        jPanel17.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 50, 20));

        jFormattedTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextField1MouseClicked(evt);
            }
        });
        jPanel17.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 380, -1));

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 290, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("* Nº BI:");
        jPanel17.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));
        jPanel17.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 280, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel17.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 380, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("*Nome:");
        jPanel17.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("* Contacto:");
        jPanel17.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 20));

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 290, -1));

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo de Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("Particular");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton3.setText("Seguradora/Empresa");
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseClicked(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox1.setText("Marcar Consulta");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton2)
                .addGap(42, 42, 42)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton2)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
        );

        btSalvar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-32.png"))); // NOI18N
        jButton11.setText("Sair F(Esc)");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados da Seguradora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Cartão (PS) n º:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("A.P.N º:");

        jLabel4.setText("Seguradora:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton14.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-as-filled-32.png"))); // NOI18N
        jButton14.setText("Alterar Dados");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Marcar Consulta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Buscar Nome:");

        jTextField8.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField8CaretUpdate(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Paciente:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Médico:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Consulta:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Data de Atendimento:");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setText("h");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel18.setText("min");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-ok-filled-32.png"))); // NOI18N
        jButton9.setText("Salvar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel27.setText("seg");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox5, 0, 255, Short.MAX_VALUE)
                                    .addComponent(jTextField8)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(minuto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(segundos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27))
                            .addComponent(jComboBox1, 0, 371, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(147, 147, 147))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(minuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(segundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel27))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("* Campos obrigatório.");

        jButton16.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-edit-file-32.png"))); // NOI18N
        jButton16.setText("Novo");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25))
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(btSalvar)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16)
                    .addComponent(jButton11))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dados do Paciente", jPanel16);

        jTable1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Telefone", "Morada", "Entidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Escolha de Utente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Todos");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton4.setText("Particular");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton5.setText("Seguradora/Empresa");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-32 (1).png"))); // NOI18N
        jButton10.setText("1. Especialidade");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-32 (1).png"))); // NOI18N
        jButton12.setText("2. Clínica Geral");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jRadioButton1)
                .addGap(57, 57, 57)
                .addComponent(jRadioButton4)
                .addGap(52, 52, 52)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton1)
                .addComponent(jRadioButton4)
                .addComponent(jRadioButton5)
                .addComponent(jButton10)
                .addComponent(jButton12))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Operações"));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-export-pdf-filled-32.png"))); // NOI18N
        jButton6.setText("Listar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-binder-32.png"))); // NOI18N
        jButton7.setText("Agendar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton8.setText("Pedido de Exames");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-receipt-32 (1).png"))); // NOI18N
        jButton13.setText("Facturação (F1)");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-receipt-32 (1).png"))); // NOI18N
        jButton15.setText("Guia");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-close-window-32.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Opções de Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton6.setText("Telefone");

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton7.setSelected(true);
        jRadioButton7.setText(" Nome do Paciente");

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton8.setText("Nome do Pai");

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton9.setText("Nome da Mãe");

        jTextField7.setToolTipText("Buscar");
        jTextField7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField7CaretUpdate(evt);
            }
        });

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton10.setText("Nº B.I");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jRadioButton7)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton8)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton10)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(535, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Lista de pacientes", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setSize(new java.awt.Dimension(934, 679));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        dispose();
//        salvarSAFT();
    }//GEN-LAST:event_jButton11ActionPerformed
    
    public Object getPaciente() {
        beneficiario.setNomeCompleto(getNome());
        beneficiario.setNomeMae(getnomeMae());
        beneficiario.setNomePai(getnomePai());
        beneficiario.setMorada(getMorada());
        beneficiario.setContacto(getContacto());
        beneficiario.setTelefone(getTelefone());
        beneficiario.setBI(getBI());
        beneficiario.setEstado_civil(jComboBox6.getSelectedItem().toString());
        beneficiario.setGenero(jComboBox4.getSelectedItem().toString());
        beneficiario.setCodigoNaturalidade(getNaturalidade());
        beneficiario.setApn(jTextField1.getText());
//        beneficiario.setEmpresa(codigo);
        beneficiario.setNascimento(getData1());
        beneficiario.setTomadorSegurado(jTextField6.getText());
        return beneficiario;
    }

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        int codigo;
        if (jRadioButton2.isSelected()) {
            if (camposValidos()) {
                salvar(8);
                limpar();
                jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
                mostrarPaciente("SELECT DISTINCT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                        + "on p.codigoSeguro = e.idSeguros AND p.codigoStatus=1 group by 1 desc limit 50");
                if (tipoArea == 1) {
                    new TesourariaColaborador(getCodigoUsuario(), 1).setVisible(true);
                }
                
            }
            
        } else {
            if (camposValidos()) {
                codigo = getEmpresa();
                salvar(codigo);
                jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
                limpar();
                mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                        + "on p.codigoSeguro = e.idSeguros where p.codigoStatus=1");
                if (tipoArea == 1) {
                    new GuiaReguradora(getCodigoUsuario()).setVisible(true);
                }
                
            }

    }//GEN-LAST:event_btSalvarActionPerformed
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int codigo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            controllerBeneficiario.eliminar(codigo);
            mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                    + "on p.codigoSeguro = e.idSeguros where p.codigoStatus=1");
        } else if (resposta == JOptionPane.NO_OPTION) {
            
            mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                    + "on p.codigoSeguro = e.idSeguros where p.codigoStatus=1");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int codigo;
        if (tipoArea == 1) {
            if (jRadioButton2.isSelected()) {
                if (camposValidos()) {
                    editar(8);
                    limpar();
                    jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
                    mostrarPaciente("SELECT DISTINCT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                            + "on p.codigoSeguro = e.idSeguros AND p.codigoStatus=1 group by 1 desc limit 50");
                    new TesourariaColaborador(getCodigoUsuario(), 1).setVisible(true);
                }
                
            } else {
                if (camposValidos()) {
                    codigo = getEmpresa();
                    editar(codigo);
                    limpar();
                    jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
                    mostrarPaciente("SELECT DISTINCT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                            + "on p.codigoSeguro = e.idSeguros AND p.codigoStatus=1 group by 1 desc limit 50");
                    new GuiaReguradora(getCodigoUsuario()).setVisible(true);
                }
                
            }
        }
        if (tipoArea == 2) {
            if (camposValidos()) {
                editar(8);
                limpar();
                jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
                mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                        + "on p.codigoSeguro = e.idSeguros where p.codigoStatus=1 AND p.codigoSeguro =8");
                // new TesourariaColaborador(getCodigoUsuario(), 1).setVisible(true);
            }
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        //        jComboBox9.setEnabled(true);
        //        jTextField13.setEnabled(false);
        //        jTextField14.setEnabled(false);
        jPanel2.setVisible(false);
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseClicked
        //        jComboBox9.setEnabled(false);
        //        jTextField13.setEnabled(false);
        //        jTextField14.setEnabled(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jRadioButton3MouseClicked
    
    public boolean camposValidos() {
        if (getNome().trim().equals("") || getTelefone().trim().equals("")) {
            // JOptionPane.showMessageDialog(null, "Campo nome e Contacto são Obrigatório");
            JOptionPane.showMessageDialog(null, "Campo nome e Contacto são Obrigatório!", "Aviso", JOptionPane.WARNING_MESSAGE);
            jFormattedTextField1.setBackground(Color.red);
            return false;
            
        }
        
        return true;
    }
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                + "on p.codigoSeguro = e.idSeguros where p.codigoStatus=1");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                + "on p.codigoSeguro = e.idSeguros where e.idSeguros=8 and p.codigoStatus=1 ");
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if (jRadioButton5.isSelected()) {
            mostrarPaciente("SELECT p.idPaciente,p.nomeCompleto,p.telefone,p.morada,e.designacao FROM pacientes p inner join empresaseguros e\n"
                    + "on p.codigoSeguro = e.idSeguros where e.idSeguros <>8 and p.codigoStatus=1");
        }

    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (jRadioButton1.isSelected()) {
            relatorioPaciente.getListaUtente();
        }
        if (jRadioButton4.isSelected()) {
            relatorioPaciente.getListaUtenteParticular(8);
        }
        if (jRadioButton5.isSelected()) {
            relatorioPaciente.getListaUtenteSeguradora(8);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField7CaretUpdate
        // Por Telefone
        if (jRadioButton6.isSelected()) {
            if (!jTextField7.getText().isEmpty()) {
                System.out.println("Teste:" + jTextField7.getText());
                mostrarPaciente("SELECT distinct p.idPaciente,p.nomeCompleto,p.telefone,p.morada, e.designacao FROM empresaseguros e inner join pacientes p on p.codigoSeguro = e.idSeguros WHERE p.telefone LIKE '" + jTextField7.getText() + "%' and p.codigoStatus=1");
            }
        }
        // Nome do Paciente
        if (jRadioButton7.isSelected()) {
            if (!jTextField7.getText().isEmpty()) {
                mostrarPaciente("SELECT distinct p.idPaciente,p.nomeCompleto,p.telefone,p.morada, e.designacao FROM empresaseguros e inner join pacientes p on p.codigoSeguro = e.idSeguros WHERE p.nomeCompleto LIKE '" + jTextField7.getText() + "%' and p.codigoStatus=1");
            }
        }  // Nome do Pai
        if (jRadioButton8.isSelected()) {
            if (!jTextField7.getText().isEmpty()) {
                mostrarPaciente("SELECT distinct p.idPaciente,p.nomeCompleto,p.nomePai,p.nomeMae,p.bilheteIdentidade,p.telefone,p.morada, e.designacao FROM empresaseguros e inner join pacientes p on p.codigoSeguro = e.idSeguros WHERE p.nomePai LIKE '" + jTextField7.getText() + "%' and p.codigoStatus=1");
            }
        }  // Nome da Mãe
        if (jRadioButton9.isSelected()) {
            if (!jTextField7.getText().isEmpty()) {
                mostrarPaciente("SELECT distinct p.idPaciente,p.nomeCompleto,p.nomePai,p.nomeMae,p.bilheteIdentidade,p.telefone,p.morada, e.designacao FROM empresaseguros e inner join pacientes p on p.codigoSeguro = e.idSeguros WHERE p.nomeMae LIKE '" + jTextField7.getText() + "%' and p.codigoStatus=1");
            }
        }
// Bilhete de Identidade
        if (jRadioButton10.isSelected()) {
            if (!jTextField7.getText().isEmpty()) {
                mostrarPaciente("SELECT distinct p.idPaciente,p.nomeCompleto,p.nomePai,p.nomeMae,p.bilheteIdentidade,p.telefone,p.morada, e.designacao FROM empresaseguros e inner join pacientes p on p.codigoSeguro = e.idSeguros WHERE p.bilheteIdentidade LIKE '" + jTextField7.getText() + "%' and p.codigoStatus=1");
    }//GEN-LAST:event_jTextField7CaretUpdate
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        nomePaciente = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        int codigoPaciente = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        int codigo = controllerBeneficiario.getCodigoSeguro(codigoPaciente);
        String codigoClienteFacturado = controllerBeneficiario.verificarClienteFacturado(codigoPaciente);
        if (evt.getClickCount() == 2) {
            if (controllerBeneficiario.pacienteSegurado(nomePaciente)) {
                beneficiario = controllerBeneficiario.getDadosCliente(nomePaciente);
                jTextField2.setText(beneficiario.getNomeCompleto());
                jTextField4.setText(beneficiario.getNomePai());
                jTextField12.setText(beneficiario.getNomeMae());
                jTextField1.setText(beneficiario.getApn());
                jFormattedTextField1.setText(beneficiario.getTelefone());
                jTextField6.setText(beneficiario.getTomadorSegurado());
                if (codigoClienteFacturado.equalsIgnoreCase("Consumidor Final")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else if (codigoClienteFacturado.equalsIgnoreCase("999999999")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else if (codigoClienteFacturado.equalsIgnoreCase("")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else {
                    jTextField15.setEditable(false);
                    jTextField2.setEditable(false);
                }
                
                jTextField15.setText(beneficiario.getBI());
                jTextField9.setText(beneficiario.getMorada());
                jDateChooser2.setDate(controllerBeneficiario.getData(nomePaciente));
                jTextField10.setText(beneficiario.getContacto());
                jTextField16.setText(beneficiario.getEmail());
                //        jComboBox4.setModel(new DefaultComboBoxModel(controllerBeneficiario.get.toArray()));
                jComboBox2.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodaporCodigo(codigo).toArray()));
                jTabbedPane1.setSelectedIndex(0);
                btSalvar.setEnabled(false);
                jButton14.setEnabled(true);
                // Para meter true para outra clínica excepto a Centro Octávio
                jPanel2.setVisible(false);
            } else {
                beneficiario = controllerBeneficiario.getDadosCliente(nomePaciente);
                jTextField2.setText(beneficiario.getNomeCompleto());
                jTextField4.setText(beneficiario.getNomePai());
                jTextField12.setText(beneficiario.getNomeMae());
                jTextField1.setText(beneficiario.getApn());
                if (codigoClienteFacturado.equalsIgnoreCase("Consumidor Final")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else if (codigoClienteFacturado.equalsIgnoreCase("999999999")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else if (codigoClienteFacturado.equalsIgnoreCase("")) {
                    jTextField15.setEditable(true);
                    jTextField2.setEditable(true);
                } else {
                    jTextField15.setEditable(false);
                    jTextField2.setEditable(false);
                }
                jFormattedTextField1.setText(beneficiario.getTelefone());
                jTextField6.setText(beneficiario.getTomadorSegurado());
                jTextField15.setText(beneficiario.getBI());
                jTextField9.setText(beneficiario.getMorada());
                jDateChooser2.setDate(controllerBeneficiario.getData(nomePaciente));
                jTextField16.setText(beneficiario.getEmail());
                //       jComboBox4.setModel(new DefaultComboBoxModel(controllerBeneficiario.getGenero(nomePaciente).toArray()));
                jComboBox2.setModel(new DefaultComboBoxModel(controllerSeguradora.getTodaporCodigo(codigo).toArray()));
                jTextField10.setText(beneficiario.getContacto());
                jTabbedPane1.setSelectedIndex(0);
                btSalvar.setEnabled(false);
                jButton14.setEnabled(true);
                jPanel2.setVisible(false);
            }
            
        }
        

    }//GEN-LAST:event_jTable1MouseClicked

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (jCheckBox1.isSelected()) {
            jPanel5.setVisible(true);
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
        } else {
            jPanel5.setVisible(false);
            jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioUltimo().toArray()));
            
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (horaConsultaValida()) {
            if (!limiteAtingido()) {
                salvarMarcar();
                int codigo = controllerMarcarcaoConsulta.getLastCodigoByPacienteToday(getCodigoUtente(), getDataConsulta());
                relatorioServicos.getMarcacao(codigo);
            } else {
                JOptionPane.showMessageDialog(null, "Já não vaga na Agenda do Medico!", nomePaciente, JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Verifique a data e hora da consulta!", nomePaciente, JOptionPane.ERROR_MESSAGE);
            
        }
        

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new MarcacaoConsultas(codigoUser).show();
        hide();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField8CaretUpdate
        jComboBox1.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNome(jTextField8.getText()).toArray()));
    }//GEN-LAST:event_jTextField8CaretUpdate

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new PedidoExame(getCodigoUsuario()).show();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (evt.getSource() == jButton10) {
            String nome = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            System.out.println("Nome do Camarada:" + nome);
            if (!nome.isEmpty()) {
                new ListaDoctor(nome, 2, codigoUser).setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == jButton12) {
            String nome = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            System.out.println("Nome do Camarada:" + nome);
            if (!nome.isEmpty()) {
                new ListaDoctor(nome, 1, codigoUser).setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (tipoArea == 1) {
            new TesourariaColaborador(codigoUser, 1).setVisible(true);
            System.out.println("Codigo do Usuario Coma Bem" + codigoUser);
        }
        if (tipoArea == 2) {
            new Tesouraria(codigoUser, 2).setVisible(true);
            System.out.println("Codigo do Usuario Coma Bem" + codigoUser);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        new GuiaReguradora(codigoUser).setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jFormattedTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextField1MouseClicked
        jFormattedTextField1.setBackground(Color.WHITE);
    }//GEN-LAST:event_jFormattedTextField1MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        limpar();
        btSalvar.setEnabled(true);
        jButton14.setEnabled(false);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        jComboBox7.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioBuscar(jTextField2.getText()).toArray()));

    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        nomePaciente = jComboBox7.getSelectedItem().toString();
        beneficiario = controllerBeneficiario.getDadosCliente(nomePaciente);
        jTextField2.setText(beneficiario.getNomeCompleto());
        jTextField4.setText(beneficiario.getNomePai());
        jTextField12.setText(beneficiario.getNomeMae());
        jTextField1.setText(beneficiario.getApn());
        jFormattedTextField1.setText(beneficiario.getTelefone());
        jTextField6.setText(beneficiario.getTomadorSegurado());
        jTextField15.setText(beneficiario.getBI());
        jTextField9.setText(beneficiario.getMorada());
        jDateChooser2.setDate(controllerBeneficiario.getData(nomePaciente));
        jTextField10.setText(beneficiario.getContacto());
        btSalvar.setEnabled(false);
        jButton14.setEnabled(true);

    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed
    
    public Date getDateSistema() {
        return jDateChooser2.getDate();
    }
    
    public int getNaturalidade() {
        return controllerNaturalidade.getCodigoNaturalidade(jComboBox8.getSelectedItem().toString());
    }
    
    public String getNome() {
        return jTextField2.getText();
    }
    
    public String getMorada() {
        return jTextField9.getText();
    }
    
    public String getTelefone() {
        return jFormattedTextField1.getText();
    }
    
    public String getContacto() {
        return jTextField10.getText();
    }
    
    public String getnomePai() {
        return jTextField4.getText();
    }
    
    public String getnomeMae() {
        return jTextField12.getText();
    }
    
    public String getBI() {
        if (!jTextField15.getText().isEmpty() || jTextField15.getText().equalsIgnoreCase("999999999") ) {
            jTextField15.setText("Consumidor Final");
            return jTextField15.getText();
        } else {
            jTextField15.setText("Consumidor Final");
            return jTextField15.getText();
        }
        
    }
    
    public void limpar() {
        jTextField1.setText("");
        jTextField6.setText("");
        jTextField15.setText("");
        jTextField12.setText("");
        jTextField10.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField9.setText("");
        jTextField16.setText("");
        jFormattedTextField1.setText("");
    }
    
    public void salvar(int codigo) {
        
        java.sql.Date data = d.converteDataSql(jDateChooser2.getDate());
        if (camposValidos()) {
            if (!controllerBeneficiario.existe(getNome(), data, codigo)) {
                beneficiario.setNomeCompleto(getNome());
                beneficiario.setNomeMae(getnomeMae());
                beneficiario.setNomePai(getnomePai());
                beneficiario.setMorada(getMorada());
                beneficiario.setContacto(getContacto());
                beneficiario.setTelefone(getTelefone());
                beneficiario.setBI(getBI());
                beneficiario.setEstado_civil(jComboBox6.getSelectedItem().toString());
                beneficiario.setGenero(jComboBox4.getSelectedItem().toString());
                beneficiario.setCodigoNaturalidade(getNaturalidade());
                beneficiario.setApn(jTextField1.getText());
                beneficiario.setEmpresa(codigo);
                beneficiario.setNif(getBI());
                beneficiario.setEmail(jTextField16.getText());
                beneficiario.setNascimento(getData1());
                beneficiario.setTomadorSegurado(jTextField6.getText());
                controllerBeneficiario.Inserir(beneficiario);
            } else {
                JOptionPane.showMessageDialog(null, "Já Existe um Paciente com este Nome e com a mesma data de Nascimento!\n Por favor verificar o nome do Paciente na lista Existente.", "Clínica Santo António", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void editar(int codigo) {
        java.sql.Date data = d.converteDataSql(jDateChooser2.getDate());
        if (camposValidos()) {
            int codigoPaciente = controllerBeneficiario.getCodigoUtente(nomePaciente);
            beneficiario.setNomeCompleto(getNome());
            beneficiario.setNomeMae(getnomeMae());
            beneficiario.setNomePai(getnomePai());
            beneficiario.setMorada(getMorada());
            beneficiario.setContacto(getContacto());
            beneficiario.setTelefone(getTelefone());
            beneficiario.setBI(getBI());
            beneficiario.setEstado_civil(jComboBox6.getSelectedItem().toString());
            beneficiario.setGenero(jComboBox4.getSelectedItem().toString());
            beneficiario.setCodigoNaturalidade(getNaturalidade());
            beneficiario.setApn(jTextField1.getText());
            beneficiario.setEmpresa(codigo);
            beneficiario.setNascimento(getData1());
            beneficiario.setEmail(jTextField16.getText());
            beneficiario.setTomadorSegurado(jTextField6.getText());
            controllerBeneficiario.editar(beneficiario, codigoPaciente);
        }
    }
    
    public String getDataConsulta() {
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
    
    public int getEmpresa() {
        return controllerSeguradora.getCodigoSeguradora(jComboBox2.getSelectedItem().toString());
    }
    
    public int getCodigoMedico() {
        return controllerUsuario.getCodigoMedico(jComboBox3.getSelectedItem().toString());
    }
    
    public int getCodigoUsuario() {
        return codigoUser;
    }
    
    public int getCodigoUtente() {
        return controllerBeneficiario.getCodigoUtente(jComboBox1.getSelectedItem().toString());
    }
    
    public int getCodigoServico() {
        return controllerServico.getCodigoServico(jComboBox5.getSelectedItem().toString());
    }
    
    public int getHora() {
        return (int) hora.getValue();
    }
    
    public int getMinuto() {
        return (int) minuto.getValue();
    }
    
    public int getSegundo() {
        return (int) segundos.getValue();
    }
    
    public Date getData() {
        return d.converteDataSql(jDateChooser1.getDate());
    }
    
    public Date getData1() {
        return d.converteDataSql(jDateChooser2.getDate());
    }
    
    public String getDataActual() {
        Calendar calendario = Calendar.getInstance();

        //buscar data
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);
        
        int h = calendario.get(Calendar.HOUR_OF_DAY);
        int m = calendario.get(Calendar.MINUTE);
        int s = calendario.get(Calendar.SECOND);
        
        String data = ano + "-" + (mes + 1) + "-" + dia + " " + h + ":" + m + ":" + s;
        //String data = ano + "-" + (mes + 1) + "-" + dia;

        return data;
    }
    
    public String getHoraConsulta() {
        Time t = new Time(getHora(), getMinuto(), getSegundo());
        return t.toString();
    }
    
    public boolean horaConsultaValida() {
        Date datConsulta = jDateChooser1.getDate();
        Date dataActual = new Date();
        datConsulta.setHours(getHora());
        datConsulta.setMinutes(getMinuto());
        
        if (datConsulta.compareTo(dataActual) >= 0) {
            return true;
        }
        return false;
    }
    
    public boolean limiteAtingido() {
        if (controllerMarcarcaoConsulta.getLimiteDiario(getCodigoMedico()) <= controllerMarcarcaoConsulta.getTotalConsultasAgendadasPorMedico(getCodigoMedico(), getDataConsulta())) {
            return true;
        }
        return false;
    }
    
    public void salvarMarcar() {
        marcacaoConsulta.setCodigoUtlizador(codigoUser);
        marcacaoConsulta.setCodigoMedico(getCodigoMedico());
        marcacaoConsulta.setCodigoPaciente(getCodigoUtente());
        marcacaoConsulta.setCodigoServico(getCodigoServico());
        marcacaoConsulta.setDataAtendimento(getData());
        marcacaoConsulta.setDataMarcacao(getDataActual());
        marcacaoConsulta.setHora(getHoraConsulta());
        marcacaoConsulta.setCodigoEstado(5);
        marcacaoConsulta.setEstado("Não urgente (Azul) - Atendimento em até 4 horas.");
        int codigoSeguro = controllerBeneficiario.getCodigoSeguro(getCodigoUtente());
        double precoParticular = controllerServico.getPrecoParticular(jComboBox5.getSelectedItem().toString());
        double ensa = controllerServico.getPrecoAdvance(jComboBox5.getSelectedItem().toString());
        double saham = controllerServico.getPrecoSaham(jComboBox5.getSelectedItem().toString());
        double angolatelecom = controllerServico.getPrecoAngolaTelecom(jComboBox5.getSelectedItem().toString());
        double saudemais = controllerServico.getPrecoSaudemais(jComboBox5.getSelectedItem().toString());
        double unisaude = controllerServico.getPrecoUnisaude(jComboBox5.getSelectedItem().toString());
        double fidelidade = controllerServico.getPrecoFidelidade(jComboBox5.getSelectedItem().toString());
        double anst = controllerServico.getPrecoEmpresa(jComboBox5.getSelectedItem().toString());
        if (codigoSeguro == 8 || codigoSeguro == 19) {
            marcacaoConsulta.setPreco(precoParticular);
            controllerMarcarcaoConsulta.salvarRapida(marcacaoConsulta);
        }
        if (codigoSeguro == 1 || codigoSeguro == 2 || codigoSeguro == 3 || codigoSeguro == 4 || codigoSeguro == 5 || codigoSeguro == 6 || codigoSeguro == 7 || codigoSeguro == 15) {
            marcacaoConsulta.setPreco(ensa);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 9) {
            marcacaoConsulta.setPreco(angolatelecom);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 10) {
            marcacaoConsulta.setPreco(anst);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 11) {
            marcacaoConsulta.setPreco(saham);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 12) {
            marcacaoConsulta.setPreco(unisaude);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 13) {
            marcacaoConsulta.setPreco(saudemais);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 14) {
            marcacaoConsulta.setPreco(fidelidade);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        if (codigoSeguro == 16) {
            marcacaoConsulta.setPreco(anst);
            controllerMarcarcaoConsulta.salvar(marcacaoConsulta);
        }
        
    }
    
    public final void mostrarPaciente(String sql) {
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(125);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(125);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("p.idPaciente"), rs.getString("p.nomeCompleto"), rs.getString("p.telefone"),
                    rs.getString("p.morada"), rs.getString("e.designacao")
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
            java.util.logging.Logger.getLogger(Recepcao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recepcao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recepcao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recepcao.class
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
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Recepcao(codigoUser).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JSpinner hora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JSpinner minuto;
    private javax.swing.JSpinner segundos;
    // End of variables declaration//GEN-END:variables

    public final void teclaInser() {
        jTextField2.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jComboBox8.requestFocus();
                }
            }
            
        });
        jComboBox8.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jComboBox6.requestFocus();
                }
            }
            
        });
        jComboBox6.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jComboBox4.requestFocus();
                }
            }
            
        });
        jComboBox4.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField9.requestFocus();
                }
            }
            
        });
        jTextField9.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField4.requestFocus();
                }
            }
            
        });
        jTextField4.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField10.requestFocus();
                }
            }
            
        });
        jTextField10.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField12.requestFocus();
                }
            }
            
        });
        jTextField12.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jFormattedTextField1.requestFocus();
                }
            }
            
        });
        jFormattedTextField1.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField15.requestFocus();
                }
            }
            
        });
        jTextField15.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField6.requestFocus();
                }
            }
            
        });
        jTextField6.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField1.requestFocus();
                }
            }
            
        });
        jTextField1.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jComboBox2.requestFocus();
                }
            }
            
        });
    }
}
