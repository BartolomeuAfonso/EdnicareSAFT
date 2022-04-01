/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import GestaoStock.views.Login;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerJustificativo;
import CLINICA.controller.ControllerTriagem;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.controller.ControllerHistoricoClinico;
import CLINICA.controller.ControllerEspecalidadeMedico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerCids;
import CLINICA.controller.ControllerEmpresa;
import CLINICA.controller.ControllerInternamento;
import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerReceita;
import CLINICA.controller.ControllerPedidoExames;
import CLINICA.controller.ControllerMarcarcaoConsulta;
import CLINICA.controller.ControllerPedidoRaioX;
import CLINICA.modelo.ExamesPorFazerItem;
import CLINICA.modelo.ExamesporFazer;
import CLINICA.modelo.RaioX;
import CLINICA.modelo.Utente;
import CLINICA.modelo.Triagem;
import CLINICA.modelo.Internamento;
import CLINICA.modelo.Receita;
import CLINICA.modelo.PedidoExames;
import CLINICA.modelo.HistorialClinico;
import CLINICA.modelo.HistoricaInterno;
import CLINICA.modelo.PedidoItensRaioX;
import CLINICA.relatorios.RelatorioEstatistica;
import CLINICA.relatorios.RelatorioHistoricoClinico;
import CLINICA.relatorios.RelatorioUtilizador;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Familia do Fresco
 */
public final class HomeMedicos extends javax.swing.JFrame {

    /**
     * Creates new form HomeMedicos
     */
    ControllerMedico controllerMedico;
    ControllerMarcarcaoConsulta controllerMarcarcaoConsulta;
    ControllerUsuario controllerUsuario;
    ControllerTriagem controllerTriagem;
    ControllerServico controllerServico;
    ControllerUtente controllerBeneficiario;
    ControllerCids controllerCids;
    ControllerPedidoExames controllerPedidoExames;
    ControllerHistoricoClinico controllerHistoricoClinico;
    ControllerReceita controllerReceita;
    ConexaoBancos conexao = new ConexaoBancos();
    Triagem triagem = new Triagem();
    Receita receita = new Receita();
    PedidoExames pedidoExames = new PedidoExames();
    String NomeMedico;
    HistorialClinico historialClinico = new HistorialClinico();
    HistoricaInterno historicaInterno = new HistoricaInterno();
    ControllerEspecalidadeMedico especalidadeMedico;
    Data d = new Data();
    PreparedStatement ps;
    ResultSet rs;
    int codigoClinico;
    Connection con;
    String data;
    private DefaultListModel ls;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel1;
    DefaultTableModel defaultTableModel2;
    RelatorioHistoricoClinico relatorioHistoricoClinico = new RelatorioHistoricoClinico();
    int flag = 1;
    PedidoItensRaioX pedidoItensRaioX = new PedidoItensRaioX();
    ControllerPedidoRaioX controllerPedidoRaioX;
    RelatorioUtilizador relatorioUtilizador = new RelatorioUtilizador();
    ControllerExamesporFazerItens controllerExamesporFazerItens;
    ControllerExamesporFazer controllerExamesporFazer;
    ExamesporFazer exame;
    ControllerEmpresa controllerEmpresa;
    ControllerInternamento controllerInternamento;
    Internamento internamento = new Internamento();
    int codigoMedico;
    Object vNomes = new Object();
    int codigoUltimoExamePorFazer = 0;
    RaioX raioX = new RaioX();
    int quantidade = 1;
    boolean itemGravado = false;
    boolean salvo = false;
    Vector vectProdutosItem;
    ControllerJustificativo controllerJustificativo;
    String numeroOrdem, nomeMedico;

    public HomeMedicos(String medico) {
        super("MENU PRINCIPAL ------------- " + "Dr.(a): " + medico + ", CONECTADO AO SISTEMA");
        initComponents();
        //  con = new ConexaoBancos().ConexaoBD();
        controllerUsuario = new ControllerUsuario(con);
        this.NomeMedico = medico;
        JOptionPane.showMessageDialog(this, "Bemvindo ao Ednicare - Dr.(a):" + controllerUsuario.getNome(NomeMedico));
        jLabel7.setText("Dr.(a): " + NomeMedico);

        //   jLabel42.setVisible(true);
        //   jLabel42.setText("Paciente Teste");
        controllerCids = new ControllerCids(con);
        controllerEmpresa = new ControllerEmpresa(con);
        controllerPedidoRaioX = new ControllerPedidoRaioX(con);
        controllerInternamento = new ControllerInternamento(con);
        controllerJustificativo = new ControllerJustificativo(con);
        controllerHistoricoClinico = new ControllerHistoricoClinico(con);
        especalidadeMedico = new ControllerEspecalidadeMedico(con);
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        controllerExamesporFazerItens = new ControllerExamesporFazerItens(con);
        controllerReceita = new ControllerReceita(con);
        controllerPedidoExames = new ControllerPedidoExames(con);
        controllerMedico = new ControllerMedico(con);
        controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
        controllerTriagem = new ControllerTriagem(con);
        controllerServico = new ControllerServico(con);
        controllerBeneficiario = new ControllerUtente(con);
        jLabel42.setVisible(false);

        jComboBox1.setModel(new DefaultComboBoxModel(controllerCids.getNomeCids().toArray()));
        jComboBox12.setModel(new DefaultComboBoxModel(controllerCids.getNomeCids().toArray()));
        jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeExames().toArray()));
        jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getNomecografia().toArray()));
        jComboBox11.setModel(new DefaultComboBoxModel(controllerServico.getNomeRadiografia().toArray()));
        jComboBox8.setModel(new DefaultComboBoxModel(controllerMedico.getNomeMedico().toArray()));

        jComboBox10.setModel(new DefaultComboBoxModel(especalidadeMedico.getNomeEspecialide().toArray()));
        mostraHistoricoClinico("SELECT h.idHistoricoClinico,p.nomeCompleto,h.queixaPresente,h.historiaDoencaActual,h.exameFisico,h.hipoteseDiagnostico from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                + "inner join pacientes p on t.codigoPaciente = p.idpaciente AND h.codigoMedico=" + getCodigoMedico() + " and  Date(h.dataAtendimento)=current_date "
                + "order by h.dataAtendimento ");
        jComboBox5.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiario().toArray()));
        jDateChooser1.setDate(new Date());
        jDateChooser3.setDate(new Date());
        jDateChooser2.setDate(new Date());
        jDateChooser5.setDate(new Date());
        ls = new DefaultListModel();
        defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel1 = (DefaultTableModel) jTable3.getModel();
        defaultTableModel2 = (DefaultTableModel) jTable5.getModel();
        codigoMedico = controllerMedico.getCodigoMedicoUsername(NomeMedico);
        System.out.println("Código Médico:" + codigoMedico);
        getPacienteEmEspera(codigoMedico);
        jComboBox4.setModel(new DefaultComboBoxModel(controllerInternamento.getPacienteporInternar().toArray()));
        nomeMedico = controllerMedico.getNomeMedico(getCodigoMedico());
        numeroOrdem = controllerMedico.getNumeroOrdem(getCodigoMedico());
        controllerEmpresa = new ControllerEmpresa(con);
        jRadioButton2.setSelected(true);
        teclaInserirEcografia();
        teclaInserirExame();
        teclaInserirRaioX();
        mostrarData();
        iconeSistema();
        jTabbedPane2.setVisible(true);
        setLocationRelativeTo(null);

    }

    public final void iconeSistema() {
        // URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public final void getPacienteEmEspera(int codigo) {
        //Object vNomes = new Object();
        ls = new DefaultListModel();
        for (int i = 0; i < controllerTriagem.getPacienteEsperaPorMedico(codigo).size(); i++) {
            vNomes = controllerTriagem.getPacienteEsperaPorMedico(codigo).get(i);
            ls.addElement(vNomes);
        }
        jList1.setModel(ls);

    }

    public final void getPacienteMarcacaoEmEspera(int codigo) {

        ls = new DefaultListModel();
        for (int i = 0; i < controllerMarcarcaoConsulta.getPacienteEsperaPorMedico(codigo).size(); i++) {
            vNomes = controllerMarcarcaoConsulta.getPacienteEsperaPorMedico(codigo).get(i);
            ls.addElement(vNomes);
        }
        jList1.setModel(ls);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField9 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel51 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel50 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel52 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextField22 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextField23 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextField24 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextField27 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextField26 = new javax.swing.JTextArea();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextField25 = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextField21 = new javax.swing.JTextArea();
        jLabel47 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTextField8 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();
        jTextField10 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jTextField12 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jEditorPane3 = new javax.swing.JEditorPane();
        jButton13 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel42 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jTextField9.setText("jTextField9");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTÓRIO MÉDICO");
        setResizable(false);

        jToolBar1.setBackground(new java.awt.Color(102, 204, 255));
        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-sales-performance-32.png"))); // NOI18N
        jLabel2.setText("Folha de Honorário");
        jLabel2.setMaximumSize(new java.awt.Dimension(180, 60));
        jLabel2.setMinimumSize(new java.awt.Dimension(180, 60));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 60));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel2);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-invoice-filled-32.png"))); // NOI18N
        jLabel1.setText("Justificativo Médico");
        jLabel1.setMaximumSize(new java.awt.Dimension(150, 60));
        jLabel1.setMinimumSize(new java.awt.Dimension(160, 60));
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 60));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jToolBar1.add(jLabel1);

        jSeparator1.setMaximumSize(new java.awt.Dimension(10, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(32667, 6));
        jToolBar1.add(jSeparator1);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-medical-doctor-32 (1).png"))); // NOI18N
        jToolBar1.add(jLabel7);

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Espera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nome:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Pai:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Mãe:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Idade:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Telefone:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Seguradora/Empresa:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Idade:");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("Sexo:");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setText("Especialidade:");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(299, 299, 299)
                                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 4, Short.MAX_VALUE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados da Triagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Temperatura:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Altura");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Peso:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Tensão Arterial:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("IMC:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Pulso:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("FUMA:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Hábito Etico:");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setText("%SpO2:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 0, 0));
        jLabel67.setText("Indice de Massa Corporal (IMC = P/h2)");

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-add-database-32.png"))); // NOI18N
        jButton12.setText("Actualizar Lista de Espera");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton15.setText("Novo");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel67)))
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pacientes em Espera/Triagem", jPanel1);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Perguntas Opcionais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("NÃO");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("* Quais medicamentos?");

        jTextField5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTextField5.setText("S/N");

        jCheckBox4.setText("SIM");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("* Apresenta alergia a medicamentos?");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("* Está actualmente em tratamento médico?");

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("NÃO");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("SIM");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("* Qual é doença?");

        jCheckBox7.setSelected(true);
        jCheckBox7.setText("NÃO");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jCheckBox6.setText("SIM");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("* Possui alguma doença Grave/Hereditária/Contagiosa?");

        jTextField6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTextField6.setText("S/N");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("* APP (Antecendente Patologico Pessoal)");

        jTextField4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTextField4.setText("S/N");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jCheckBox6)
                            .addGap(30, 30, 30)
                            .addComponent(jCheckBox7))
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                    .addGap(64, 64, 64)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(100, 100, 100))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jCheckBox2)
                            .addGap(72, 72, 72)
                            .addComponent(jCheckBox3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(jCheckBox5))
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(163, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jLabel49)
                        .addComponent(jLabel50))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox3)
                        .addComponent(jCheckBox4)
                        .addComponent(jCheckBox5)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(jLabel52))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox6)
                        .addComponent(jCheckBox7))
                    .addContainerGap(36, Short.MAX_VALUE)))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Historial Clínico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setText("..\n\n");
        jScrollPane13.setViewportView(jTextArea6);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Historico da Doença:");

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setText("..\n\n");
        jScrollPane14.setViewportView(jTextArea7);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setText("Queixa Principal");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("APF (Antecedente Patológico Familiar)");

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jTextArea8.setText("..\n\n");
        jScrollPane16.setViewportView(jTextArea8);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(1014, 1014, 1014)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );

        jTabbedPane2.addTab("Anamnese", jPanel7);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Exame Físico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTextField22.setColumns(20);
        jTextField22.setRows(5);
        jTextField22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "1. Cabeça", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane17.setViewportView(jTextField22);

        jTextField23.setColumns(20);
        jTextField23.setRows(5);
        jTextField23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "2. Pescoço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane18.setViewportView(jTextField23);

        jTextField24.setColumns(20);
        jTextField24.setRows(5);
        jTextField24.setAutoscrolls(false);
        jTextField24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "3. Toráx", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jTextField24.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField24.setVerifyInputWhenFocusTarget(false);
        jScrollPane19.setViewportView(jTextField24);

        jTextField27.setColumns(20);
        jTextField27.setRows(5);
        jTextField27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "4. Abdômen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane20.setViewportView(jTextField27);

        jTextField26.setColumns(20);
        jTextField26.setRows(5);
        jTextField26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "5. Orgãos genitais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane5.setViewportView(jTextField26);

        jTextField25.setColumns(20);
        jTextField25.setRows(5);
        jTextField25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "6. Membros Superior e Inferior", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane21.setViewportView(jTextField25);

        jTextField21.setColumns(20);
        jTextField21.setRows(5);
        jTextField21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Diagnóstico Presunção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane22.setViewportView(jTextField21);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Buscar CIDS");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField8.setColumns(20);
        jTextField8.setRows(5);
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Avaliação Geral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScrollPane23.setViewportView(jTextField8);

        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("CIDS:");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane23)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(jScrollPane18))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addComponent(jScrollPane21))))
                .addGap(42, 42, 42))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane20)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jTextField3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-ok-filled-32.png"))); // NOI18N
        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-as-filled-32.png"))); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Exame Físico", jPanel23);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Análises:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jScrollPane6.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton6.setText("Adicionar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Data Pedido:");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-close-window-32.png"))); // NOI18N
        jButton7.setText("Remover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jEditorPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Prescrição do Exames Pedido"));
        jScrollPane12.setViewportView(jEditorPane2);

        jTextField10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField10CaretUpdate(evt);
            }
        });

        jLabel78.setText("CIDS:");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(235, 235, 235))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField10)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))))
                .addGap(0, 493, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Resiquição de Exames", jPanel3);

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField12.setToolTipText("Buscar Ecografia");
        jTextField12.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField12CaretUpdate(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setText("Buscar");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setText("Raio X");

        jEditorPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hipóteses"));
        jScrollPane15.setViewportView(jEditorPane3);

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-close-window-32.png"))); // NOI18N
        jButton13.setText("Remover");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton5.setText("Salvar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton14.setText("Adicionar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton13))
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton14))
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(13, 13, 13)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(701, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112))
        );

        jTabbedPane2.addTab("Requisição de Raio X", jPanel21);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel10.setToolTipText("n");

        jEditorPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hipóteses"));
        jScrollPane10.setViewportView(jEditorPane1);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Buscar");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        jTextField2.setToolTipText("Buscar Ecografia");
        jTextField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField2CaretUpdate(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-close-window-32.png"))); // NOI18N
        jButton8.setText("Remover");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton9.setText("Salvar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-low-priority-32.png"))); // NOI18N
        jButton10.setText("Adicionar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Ecografia");

        jTextField14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Diagnósticos"));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jTextField14)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton10))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(705, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Requisição de Ecografia", jPanel10);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setText("R/ DT:\n      S/\nR/ DT:\n      S/\nR  DT:\n      S/\nR/ DT:\n      S/\nR/ DT:\n      S/\n\nR/                               DT:\n     S/");
        jScrollPane8.setViewportView(jTextArea5);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Receituário Médico");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-export-pdf-filled-32.png"))); // NOI18N
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(456, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Receita Médica", jPanel4);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Paciente", "Queixa", "Historial da Doença", "Exame Físico", "Diagnostico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane9.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista de serviços", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton1.setText("Exames");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton2.setText("Historial Clinico");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton3.setText("Receituário");

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton7.setText("Raio X");

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton8.setText("Ecografia");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jRadioButton2)
                .addGap(78, 78, 78)
                .addComponent(jRadioButton1)
                .addGap(74, 74, 74)
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jRadioButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jRadioButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("Nota: Click duas vezes em seguida para obter relatório.");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1295, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Pacientes Atendidos", jPanel9);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pesquisa de Histórico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Utente (Paciente)");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Data:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Até:");

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Buscar Rápida:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel38))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)))
                .addGap(456, 456, 456))
        );

        jTableHistorico.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Código Paciente", "Historial da Doença", "Exame Físico", "Diagnostico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHistoricoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableHistorico);
        if (jTableHistorico.getColumnModel().getColumnCount() > 0) {
            jTableHistorico.getColumnModel().getColumn(0).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(1).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(2).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(3).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Selecione a opção que pretente obter", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton6.setText("Receituário");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton4.setText("Exames");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("Historial Clinico");

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton9.setText("Ecografia");

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton10.setText("Raio X");

        buttonGroup2.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton11.setText("Justificativo Médico");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jRadioButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(jRadioButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jRadioButton11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton9))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton10))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel56.setBackground(new java.awt.Color(0, 153, 204));
        jLabel56.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 195, Short.MAX_VALUE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Historico do(a) Paciente", jPanel2);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Dados do Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jTextField11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTextField11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField11CaretUpdate(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setText("Nome:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setText("Buscar:");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setText("Acompanhante:");

        jTextField13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel58)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, 320, Short.MAX_VALUE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Enfermaria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1º Piso", "2º Piso", "3º Piso", "4º Piso", "5º Piso", "6º Piso", "7º Piso", "8º Piso", "9º Piso", "10º Piso", " " }));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setText("Área Internado:");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setText("Quarto:");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setText("Cama:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jComboBox9, 0, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Dr. Assistente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setText("Nome:");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setText("Alta Prevista:");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setText("Especialidade:");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-ok-filled-32.png"))); // NOI18N
        jButton11.setText("Salvar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(85, 85, 85))
        );

        jTabbedPane2.addTab("Internamento", jPanel14);

        jPanel13.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1330, 570));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 51, 51));
        jLabel42.setText("MINDVISION TECNOLOGY");
        jPanel13.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 510, 40));
        jLabel42.getAccessibleContext().setAccessibleParent(jLabel13);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 51, 51));
        jPanel13.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 240, 40));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 51, 51));
        jPanel13.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 420, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/medico.jpg"))); // NOI18N
        jPanel13.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -120, 1340, 740));

        jMenu6.setText("1. Resultados");
        jMenu6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenu4.setText("Buscar Resultado");
        jMenu4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem9.setText("Laboratório");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem10.setText("Ecografia");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem11.setText("Raio X");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenu6.add(jMenu4);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("2. Enfermaria");
        jMenu7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem12.setText("Triagem");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem13.setText("Controlo de Doentes Internamdos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuItem14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem14.setText("Visualizar sinais vitais/Internamento");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem26.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem26.setText("Buscar Triagem Feitas");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        jMenuBar1.add(jMenu7);

        jMenu8.setText("3. Consulta de Especialidade");
        jMenu8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem19.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem19.setText("Ginecologia");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuItem20.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem20.setText("Cardiologia");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem20);

        jMenuItem21.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem21.setText("Neurologia");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem23.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem23.setText("Cirúrgia");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem24.setText("Ofltamologia");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem24);

        jMenuItem25.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem25.setText("Odontologia");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem25);

        jMenuItem22.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem22.setText("Medicina Natural");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem22);

        jMenuBar1.add(jMenu8);

        jMenu3.setText("4. Transferência");
        jMenu3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem6.setText("Guia Médica");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("5.  Relatórios");
        jMenu2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem3.setText("Buscar consulta por Data");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem5.setText("Honorário Médico");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem7.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem7.setText("Justificação Médica");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem16.setText("Relatório Médico");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem17.setText("Relatório Médico Normal");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem15.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem15.setText("Visualizar Transferência Médica");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem18.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem18.setText("Procedimento Banco de Urgencia");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem27.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem27.setText("Historico do Paciente");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem27);

        jMenuItem28.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem28.setText("Check-Up");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenuBar1.add(jMenu2);

        jMenu9.setText("6. Envio de relatório online");
        jMenu9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem4.setText("Opções");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem4);

        jMenuBar1.add(jMenu9);

        jMenu5.setText("7. Sair");
        jMenu5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem1.setText("Terminar a secção");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jMenuItem2.setText("Encerrar o sistema");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        //  new Servico().setVisible(true);
        if (!jLabel42.getText().isEmpty() || !jLabel42.getText().isEmpty()) {
            String designacaoServico = controllerTriagem.getNomeServico(jLabel42.getText());
            new frmJustificativo(jLabel42.getText(), designacaoServico, getCodigoMedico(), getCodigoCids()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Não existe Paciente selecionado!!!");
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new HonorarioRelatorio().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        limpar();
        carregarDadosItemSeleccionado();
        carregarDadosTriagem();
        carregarDadosDoencasCronica();
        jLabel42.setVisible(true);
        jTabbedPane2.setVisible(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(false);
        mostraPorData("SELECT date(h.dataAtendimento) as data from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                + "inner join pacientes p on t.codigoPaciente = p.idpaciente where p.idpaciente =" + getCodigoPaciente());

    }//GEN-LAST:event_jList1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        inserirTabelaAnalise();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!jLabel42.getText().equals("")) {
            salvarDoencas();
            salvarHistoricoPaciente();
            controllerTriagem.actualizarStatusTriagem(getCodigoTriagem());
            getPacienteEmEspera(getCodigoMedico());
            limpar();
            mostraHistoricoClinico("SELECT h.idHistoricoClinico,p.nomeCompleto,h.queixaPresente,h.historiaDoencaActual,h.exameFisico,h.hipoteseDiagnostico from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                    + "inner join pacientes p on t.codigoPaciente = p.idpaciente AND h.codigoMedico=" + getCodigoMedico() + " and  Date(h.dataAtendimento)=current_date "
                    + "order by h.dataAtendimento ");
            jComboBox4.setModel(new DefaultComboBoxModel(controllerInternamento.getPacienteporInternar().toArray()));

        } else {
            JOptionPane.showMessageDialog(null, "Verificar se todos os campos estão devidamente preenchido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (controllerHistoricoClinico.getLastCodigoByTriagem(getCodigoTriagem())) {
            int codigo = controllerHistoricoClinico.getLastCodigobyHystorico(getCodigoTriagem(), getCodigoPaciente());
            int codigoReceita1 = controllerReceita.getCodigoReceita(getCodigoPaciente(), codigo);
            if (flag == 1) {
                salvarReceita(codigo);
                jLabel42.setText("");
            }
            if (flag == 2) {
                //     String codigoRece = controllerReceita.getCodigoReceitaporData(getCodigoPaciente(), codigoClinico);
                editarReceital(codigoReceita1);

            }
            int codigoPaciente = controllerTriagem.getCodigoPaciente(getCodigoTriagem());
            int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(codigoPaciente);
            int codigoReceita = controllerReceita.getLastInsert();
            System.out.println("Código de Seguro:" + codigoSeguradora);
            limparReceita();
            if (codigoSeguradora != 8) {
                relatorioHistoricoClinico.getReceitaSeguro(codigoReceita);
            } else {
                relatorioHistoricoClinico.getReceita(codigoReceita);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Paciente não passou na triagem!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        jTabbedPane2.setSelectedIndex(6);
    }//GEN-LAST:event_jButton1ActionPerformed

    public int getCodigoUtilizador() {
        return controllerUsuario.getCodigoUtilizador(NomeMedico);
    }
    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        verificarTable();
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    public void verificarTable() {
        if (jTabbedPane2.getSelectedIndex() == 1) {
            if (!jLabel42.getText().equals("")) {
                jTabbedPane2.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                jTabbedPane2.setSelectedIndex(0);
            }
        }
        if (jTabbedPane2.getSelectedIndex() == 2) {
            if (!jLabel42.getText().equals("")) {
                jTabbedPane2.setSelectedIndex(2);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                jTabbedPane2.setSelectedIndex(0);
            }
        }
        if (jTabbedPane2.getSelectedIndex() == 3) {
            if (!jLabel42.getText().equals("")) {
                jTabbedPane2.setSelectedIndex(3);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                jTabbedPane2.setSelectedIndex(0);
            }
        }
        if (jTabbedPane2.getSelectedIndex() == 4) {
            if (!jLabel42.getText().equals("")) {
                jTabbedPane2.setSelectedIndex(4);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                jTabbedPane2.setSelectedIndex(0);
            }

        }
        if (jTabbedPane2.getSelectedIndex() == 5) {
            if (!jLabel42.getText().equals("")) {
                jTabbedPane2.setSelectedIndex(5);
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                jTabbedPane2.setSelectedIndex(0);
            }

        }
//        if (jTabbedPane2.getSelectedIndex() == 6) {
//            if (!jLabel42.getText().equals("")) {
//                jTabbedPane2.setSelectedIndex(4);
//            } else {
//                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
//                jTabbedPane2.setSelectedIndex(0);
//            }
//
//        }
//        if (jTabbedPane2.getSelectedIndex() == 7) {
//            if (!jLabel42.getText().equals("")) {
//                jTabbedPane2.setSelectedIndex(7);
//            } else {
//                JOptionPane.showMessageDialog(null, "Não existe nem paciente Seleccionado!", "Erro", JOptionPane.ERROR_MESSAGE);
//                jTabbedPane2.setSelectedIndex(0);
//            }
//
//        }
        jTabbedPane2.setEnabledAt(7, true);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        //   exame = new ExamesporFazer(getCodigoPaciente(), codigoUsuario, getDataActual());
        int codigoPaciente = controllerTriagem.getCodigoPaciente(getCodigoTriagem());
        int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(codigoPaciente);
        exame = new ExamesporFazer();
        String prescricaco = jEditorPane2.getText();
        exame.setCodigoPaciente(getCodigoPaciente());
        exame.setCodigoUtilizador(getCodigoUtilizador());
        exame.setCodigoHistorico(getCodigoHistorico());
        exame.setCodigoMedico(getCodigoMedico());
        exame.setDataPedido(getData().toString());
        exame.setPacienteInterno("SIM");
        exame.setPrescricao(prescricaco);
        exame.setCids(jComboBox12.getSelectedItem().toString());
        System.out.println("Codigo do Utilizador:" + getCodigoUtilizador());
        controllerExamesporFazer.createMedico(exame);
        salvo = true;
        if (salvo) {
            codigoUltimoExamePorFazer = controllerExamesporFazer.getLastInsert();
            for (int i = 0; i < jTable1.getRowCount(); i++) {

                int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
                System.out.println("Codigo do Serviço:" + codigoServico);

                int codigoProdutoItem = controllerServico.getCodigoProdutoItem(codigoServico);

                vectProdutosItem = controllerServico.getCodigosProdutoItem(codigoServico);
                for (int j = 0; j < vectProdutosItem.size(); j++) {
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
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getPedidoExames(codigoUltimoExamePorFazer);

                } else {
                    relatorioHistoricoClinico.getPedidoExames1(codigoUltimoExamePorFazer);
                }

            } else {
                JOptionPane.showMessageDialog(null, "O seu pedido(s) não foi salvo com Sucesso!!!");
            }

        }
        limparTabela();
        jTabbedPane2.setSelectedIndex(4);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        codigoClinico = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        int idade = controllerBeneficiario.getIdade(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        String sexo = controllerBeneficiario.getSexo(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        jLabel42.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        jLabel72.setText("Idade: " + idade + " Anos");
        jLabel73.setText("Genêro: " + sexo);
        boolean codigoRece = controllerReceita.getCodigoReceitaporData(getCodigoPaciente(), codigoClinico);
        System.out.println("Flag:" + flag);
        int codigoExames = controllerExamesporFazer.getCodigoExames(getData(), getCodigoPaciente());
        int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(getCodigoPaciente());
        int codigoReceita = controllerReceita.getCodigoReceita1(getCodigoPaciente(), codigoClinico);
        int codigoRaio = controllerPedidoRaioX.getLastFactura();
        int codigoEcografia = controllerPedidoExames.getCodigoEcografia(getCodigoPaciente());
        jLabel42.setVisible(true);
        jTabbedPane2.setVisible(true);
        if (evt.getClickCount() == 1) {
            jLabel42.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
            limpar();
            if (!codigoRece) {
                jLabel42.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
                jLabel72.setText("Idade: " + idade + " Anos");
                jLabel73.setText("Genêro: " + sexo);
                flag = 1;
                carregarDadosTriagem();
                carregarDadosDoencasCronica();
                carregarDadosItemSeleccionado1();
                carregarDadosClinico(codigoClinico);
                jButton2.setEnabled(false);
                jButton3.setEnabled(true);
            } else {
                jLabel42.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
                flag = 2;
                carregarDadosTriagem();
                carregarDadosDoencasCronica();
                carregarDadosItemSeleccionado1();
                carregarDadosClinico(codigoClinico);
                jButton2.setEnabled(false);
                jButton3.setEnabled(true);
                jButton1.setText("Editar");
            }

        }
        if (evt.getClickCount() == 2) {
            // getHistorico
            if (jRadioButton2.isSelected()) {
                codigoReceita = controllerReceita.getCodigoReceita(getCodigoPaciente(), codigoClinico);
                if (codigoReceita != 0) {
                    relatorioHistoricoClinico.getHistorialClingico1(codigoClinico, getCodigoPaciente());
                } else {
                    relatorioHistoricoClinico.getHistorialClingico2(codigoClinico);
                }

            }
            // getExames
            if (jRadioButton1.isSelected()) {
                if (codigoSeguradora != 8) {
                    System.out.println("Codigo Exames" + codigoExames);
                    relatorioHistoricoClinico.getPedidoExames(codigoExames);
                } else {
                    relatorioHistoricoClinico.getPedidoExames1(codigoExames);
                }

            }
            // getReceitas
            if (jRadioButton3.isSelected()) {
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getReceitaSeguro1(codigoReceita);
                } else {
                    relatorioHistoricoClinico.getReceita(codigoReceita);
                }

            }
            // getRaio X
            if (jRadioButton7.isSelected()) {
                if (codigoSeguradora != 8) {

                    relatorioHistoricoClinico.getPedidoRaioXSeguro(codigoRaio, nomeMedico, numeroOrdem);
                } else {
                    relatorioHistoricoClinico.getPedidoRaioXParticular(codigoRaio, nomeMedico, numeroOrdem);
                }

            }
            // gett Ecografia
            if (jRadioButton8.isSelected()) {
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getPedidoEcografiaSeguro(codigoEcografia);
                } else {
                    relatorioHistoricoClinico.getPedidoEcografiaParticular(codigoEcografia);
                }

            }
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        jComboBox5.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioBuscar(jTextField1.getText()).toArray()));
    }//GEN-LAST:event_jTextField1CaretUpdate

    public String getDataActual() {
        Calendar calendario = Calendar.getInstance();

        //buscar data
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);

        int h = calendario.get(Calendar.HOUR_OF_DAY);
        int m = calendario.get(Calendar.MINUTE);
        int s = calendario.get(Calendar.SECOND);

        data = ano + "-" + (mes + 1) + "-" + dia + " " + h + ":" + m + ":" + s;
        //String data = ano + "-" + (mes + 1) + "-" + dia;

        return data;
    }
    private void jTableHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoricoMouseClicked

        if (evt.getClickCount() == 2) {
            data = jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString();
            int codigo = Integer.parseInt(jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 1).toString());
            System.out.println("Código do Paciente:" + codigo);
            int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(codigo);

// Historial Clínico
            if (jRadioButton5.isSelected()) {
//                if (evt.getClickCount() == 2) {

                System.out.println("Codigo:" + codigoClinico);
                codigoClinico = Integer.parseInt(jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 0).toString());
                int codigoReceita = controllerReceita.getCodigoReceita(codigo, codigoClinico);
                if (codigoReceita != 0) {
                    relatorioHistoricoClinico.getHistorialClingico1(codigoClinico, codigo);
                } else {
                    relatorioHistoricoClinico.getHistorialClingico2(codigoClinico);
                }
//                }
            }
            // Receituário
            if (jRadioButton6.isSelected()) {
//                if (evt.getClickCount() == 2) {
                int codigoReceita = controllerReceita.getCodigoReceita(codigo, codigoClinico);
                if (codigoSeguradora != 8) {

                    relatorioHistoricoClinico.getReceitaSeguro1(codigoReceita);
                } else {
                    relatorioHistoricoClinico.getReceita(codigoReceita);
                }

//                }
            }
            // Pedido de Exames
            if (jRadioButton4.isSelected()) {
//                if (evt.getClickCount() == 2) {
                codigoUltimoExamePorFazer = controllerExamesporFazer.getCodigoExamesPor(data, codigo);
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getPedidoExames(codigoUltimoExamePorFazer);
                } else {
                    relatorioHistoricoClinico.getPedidoExames1(codigoUltimoExamePorFazer);
                }
//                }
            }
            // Pedido de Ecografia
            if (jRadioButton9.isSelected()) {
//                if (evt.getClickCount() == 2) {
                int codigoEcografia = controllerPedidoExames.getCodigoEcografiaporData(codigo, data);
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getPedidoEcografiaSeguro(codigoEcografia);
                } else {
                    relatorioHistoricoClinico.getPedidoEcografiaParticular(codigoEcografia);
                }
//                }
            }
            // Raio X
            if (jRadioButton10.isSelected()) {
//                if (evt.getClickCount() == 2) {
                int codigoRaio = controllerPedidoRaioX.getCodigoLastFactura(data, codigo);
                if (codigoSeguradora != 8) {
                    relatorioHistoricoClinico.getPedidoRaioXSeguro(codigoRaio, nomeMedico, numeroOrdem);
                } else {
                    relatorioHistoricoClinico.getPedidoRaioXParticular(codigoRaio, nomeMedico, numeroOrdem);
                }
//                }
            }
            // Justificativo Médico
            if (jRadioButton11.isSelected()) {
//                if (evt.getClickCount() == 2) {
                RelatorioEstatistica relatorioEstatistica = new RelatorioEstatistica();
                //  data = "" + jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString();
                int codigoJustifcavo = controllerJustificativo.getCodigoJustificativo(data, codigo);
                // codigoPaciente = controllerBeneficiario.getCodigoUtente(jComboBox5.getSelectedItem().toString());
                relatorioEstatistica.IrelatorioJustificativo(codigo, codigoJustifcavo);
////                }
            }
        }

    }//GEN-LAST:event_jTableHistoricoMouseClicked


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!jLabel42.getText().equals("")) {
            editarHistoricoPaciente(codigoClinico);
            getPacienteEmEspera(1);
            limpar();

        } else {
            JOptionPane.showMessageDialog(null, "Verificar se todos os campos estão devidamente preenchido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new BuscarConsultaData().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new HonorarioRelatorio().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            defaultTableModel.removeRow(jTable1.getSelectedRow());
//            jTextFieldTotaPagar.setText("" + decimalformat.format(totalGeral()));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um exame na tabela!");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        if (!jLabel42.getText().isEmpty() || !jLabel5.getText().isEmpty()) {
            String designacaoServico = controllerTriagem.getNomeServico(jLabel42.getText());
            new frmJustificativo(jLabel42.getText(), designacaoServico, getCodigoMedico(), getCodigoCids()).setVisible(true);
        }

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        inserirTabelaEcografia();

    }//GEN-LAST:event_jButton10ActionPerformed

    public Date getData() {
        return d.converteDataSql(jDateChooser1.getDate());
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        pedidoExames.setCodigoPaciente(getCodigoPaciente());
        pedidoExames.setCodigoMedico(getCodigoMedico());
        controllerPedidoExames.SalvarEcografiaItens(pedidoExames);
        pedidoExames.setDescricao("." + jEditorPane1.getText());
        pedidoExames.setDiagnostico("." + jTextField14.getText());
        int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(getCodigoPaciente());
        int codigoEcografia = controllerPedidoExames.getLastInsertEcografiaItens();
        for (int i = 0; i < jTable3.getRowCount(); i++) {
            int codigoServico = Integer.parseInt(jTable3.getValueAt(i, 0).toString());
            pedidoExames.setCodigoServico(codigoServico);
            pedidoExames.setCodigoTriagem(codigoEcografia);
            controllerPedidoExames.SalvarEcografia(pedidoExames);
        }
        limparTabela1();
        int codigo = controllerPedidoExames.getLastInsertEcografia();
        if (codigoSeguradora != 8) {
            relatorioHistoricoClinico.getPedidoEcografiaSeguro(codigo);
        } else {
            relatorioHistoricoClinico.getPedidoEcografiaParticular(codigo);
        }
        jTabbedPane2.setSelectedIndex(5);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        new ResultadoPronto(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        new EcografiaMedico(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        if (jCheckBox5.isSelected()) {
            jCheckBox4.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            jCheckBox3.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if (jCheckBox3.isSelected()) {
            jCheckBox2.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        if (jCheckBox4.isSelected()) {
            jCheckBox5.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        if (jCheckBox6.isSelected()) {
            jCheckBox7.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        if (jCheckBox7.isSelected()) {
            jCheckBox6.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new RaioXMedico(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    public void salvarInternamento() {
        internamento.setCodigoMedico(getCodigoMedico());
        internamento.setCodigoPaciente(controllerBeneficiario.getCodigoUtente(jComboBox4.getSelectedItem().toString()));
        internamento.setAcompanhamento(jTextField13.getText());
        internamento.setAreaInternado(jComboBox6.getSelectedItem().toString());
        internamento.setCama(jComboBox7.getSelectedItem().toString());
        internamento.setQuarto(jComboBox9.getSelectedItem().toString());
        internamento.setDiagnostico(jTextArea1.getText());
        internamento.setDataSaida(getData6().toString());
        internamento.setMedicoAssistente(jComboBox8.getSelectedItem().toString());
        controllerInternamento.InsertInternamento(internamento);
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        salvarInternamento();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int linha = jTable3.getSelectedRow();
        if (linha != -1) {
            defaultTableModel1.removeRow(jTable3.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um exame na tabela!");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField2CaretUpdate
        jComboBox2.setModel(new DefaultComboBoxModel(controllerServico.getNomecografialike(jTextField2.getText()).toArray()));
    }//GEN-LAST:event_jTextField2CaretUpdate

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        data = jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString();
        if (jLabel42.getText().equals(jComboBox5.getSelectedItem().toString())) {
            mostraHistoricoClinicoPorData("SELECT h.idHistoricoClinico,p.idpaciente,h.historiaDoencaActual,h.exameFisico,h.hipoteseDiagnostico from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                    + "inner join pacientes p on t.codigoPaciente = p.idpaciente where date(h.dataAtendimento)='" + data + "' AND p.idpaciente=" + getCodigoPaciente2());
            //   int codigo = Integer.parseInt(jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 0).toString());
            // System.out.println("Historico:" + codigo);
            jLabel56.setText("A sua ultima consulta foi no dia: " + controllerHistoricoClinico.getUltimadata(getCodigoPaciente2()) + " com Dr. " + controllerHistoricoClinico.getMedicoHistorico(data));
        }
        if (!jLabel42.getText().equals(jComboBox5.getSelectedItem().toString())) {

            mostraHistoricoClinicoPorData("SELECT h.idHistoricoClinico,p.idpaciente,h.historiaDoencaActual,h.exameFisico,h.hipoteseDiagnostico from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                    + "inner join pacientes p on t.codigoPaciente = p.idpaciente where date(h.dataAtendimento)='" + data + "' AND p.idpaciente=" + getCodigoPaciente());
            //   int codigo = Integer.parseInt(jTableHistorico.getValueAt(jTableHistorico.getSelectedRow(), 0).toString());
            // System.out.println("Historico:" + codigo);
            jLabel56.setText("A sua ultima consulta foi no dia: " + controllerHistoricoClinico.getUltimadata(getCodigoPaciente()) + " com Dr. " + controllerHistoricoClinico.getMedicoHistorico(data));
        }

    }//GEN-LAST:event_jTable4MouseClicked

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        mostraPorData("SELECT date(h.dataAtendimento) as data from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                + "inner join pacientes p on t.codigoPaciente = p.idpaciente where p.idpaciente =" + getCodigoPaciente2());
        jLabel42.setText(jComboBox5.getSelectedItem().toString());
        jLabel42.setVisible(true);
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField11CaretUpdate
        if (!jTextField11.getText().isEmpty()) {

            jComboBox4.setModel(new DefaultComboBoxModel(controllerBeneficiario.getNomeBenefiarioBuscarPorInternar(jTextField11.getText()).toArray()));
        }

    }//GEN-LAST:event_jTextField11CaretUpdate

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate
        if (!jTextField3.getText().isEmpty()) {
            jComboBox1.setModel(new DefaultComboBoxModel(controllerCids.getNomePorLike(jTextField3.getText()).toArray()));
        } else {
            jComboBox1.setModel(new DefaultComboBoxModel(controllerCids.getNomePorLike(jTextField3.getText()).toArray()));
        }

    }//GEN-LAST:event_jTextField3CaretUpdate

    private void jTextField10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField10CaretUpdate
        jComboBox3.setModel(new DefaultComboBoxModel(controllerServico.getNomeExamesPorLike(jTextField10.getText()).toArray()));
    }//GEN-LAST:event_jTextField10CaretUpdate

    private void jTextField12CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField12CaretUpdate
        jComboBox11.setModel(new DefaultComboBoxModel(controllerServico.getNomeRadiografiaporLike(jTextField12.getText()).toArray()));
    }//GEN-LAST:event_jTextField12CaretUpdate

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int linha = jTable5.getSelectedRow();
        if (linha != -1) {
            defaultTableModel2.removeRow(jTable5.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um exame na tabela!");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        salvarPedidoRaioX();
        salvarPedidoItensRaioX();
        int codigoSeguradora = controllerBeneficiario.getCodigoSeguro(getCodigoPaciente());
        int codigo = controllerPedidoRaioX.getLastFactura();
        limparTabela2();
        if (codigoSeguradora != 8) {
            relatorioHistoricoClinico.getPedidoRaioXSeguro(codigo, nomeMedico, numeroOrdem);
        } else {
            relatorioHistoricoClinico.getPedidoRaioXParticular(codigo, nomeMedico, numeroOrdem);
        }
        jTabbedPane2.setSelectedIndex(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    public void salvarPedidoRaioX() {
        raioX.setCodigoPaciente(getCodigoPaciente());
        int quantidade = 1;
        raioX.setQuantidade(quantidade);
        raioX.setCodigoServico(getCodigoMedico());
        raioX.setDesignacao(jEditorPane3.getText());
        controllerPedidoRaioX.Salvar(raioX);
    }

    public void salvarPedidoItensRaioX() {
        raioX.setCodigoPaciente(getCodigoPaciente());
        int codigoRaio = controllerPedidoRaioX.getLastFactura();
        int quantidade = 1;
        for (int i = 0; i < jTable5.getRowCount(); i++) {
            System.out.println("Quantidade de Pedido:" + quantidade);
            int codigoServico = Integer.parseInt(jTable5.getValueAt(i, 0).toString());
            pedidoItensRaioX.setCodigoServico(codigoServico);
            pedidoItensRaioX.setCodigoRaio(codigoRaio);
            pedidoItensRaioX.setQuantidade(quantidade);
            controllerPedidoRaioX.SalvarItens(pedidoItensRaioX);
        }
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        inserirTabelaRaioX();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new EnfermariaHome(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new ControloInternado(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        new InternamentoServico().setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (jLabel42.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existe paciente Selecionado");
        } else {
            new GuiaMedica(getCodigoMedico(), jLabel42.getText()).setVisible(true);
        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        new VisualizarGuia().setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        if (jLabel42.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existe paciente Selecionado");
        } else {
            new RelatorioMedico(getCodigoMedico(), jLabel42.getText()).setVisible(true);
        }


    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        if (jLabel42.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existe paciente Selecionado");
        } else {
            new RelatorioNormal(getCodigoMedico(), jLabel42.getText()).setVisible(true);
        }


    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        getPacienteEmEspera(codigoMedico);
        mostraHistoricoClinico("SELECT h.idHistoricoClinico,p.nomeCompleto,h.queixaPresente,h.historiaDoencaActual,h.exameFisico,h.hipoteseDiagnostico from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                + "inner join pacientes p on t.codigoPaciente = p.idpaciente AND h.codigoMedico=" + getCodigoMedico() + " and  Date(h.dataAtendimento)=current_date "
                + "order by h.dataAtendimento ");

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        new ServicoEnfermaria("MIND VISION", 2).setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        limpar();
        jLabel42.setText("");
        jButton2.setEnabled(true);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        int codigo = controllerBeneficiario.getCodigoUtente(jLabel42.getText());
        if (jLabel75.getText().equals("Consulta de Ginecologia") || jLabel75.getText().equalsIgnoreCase("Ginecologia")) {
            new frameGinecologia(jLabel42.getText(), codigo).setVisible(true);
        } else {

            JOptionPane.showMessageDialog(null, "Somente Consulta de Ginecologia!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed

        if (jLabel42.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existe paciente Selecionado");
        } else {
            if (jLabel75.getText().equals("Consulta de Cardiologia") || jLabel75.getText().equalsIgnoreCase("Cardiologia")) {
                new frameCardiologia(getCodigoPaciente(), jLabel42.getText(), getCodigoTriagem(), NomeMedico).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Especialidade errada, somente cardiologia!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new EnvioEmail().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        new BuscarTriagem(NomeMedico).setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        new HistoricoPaciente().setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        new check().setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jTextField7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField7CaretUpdate
        if (!jTextField7.getText().isEmpty()) {
            jComboBox12.setModel(new DefaultComboBoxModel(controllerCids.getNomePorLike(jTextField7.getText()).toArray()));
        } else {
            jComboBox12.setModel(new DefaultComboBoxModel(controllerCids.getNomePorLike(jTextField7.getText()).toArray()));
        }
    }//GEN-LAST:event_jTextField7CaretUpdate

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    public final void teclaInserirEcografia() {
        jTextField2.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabelaEcografia();
                }
            }

        });
    }

    public final void teclaInserirExame() {
        jTextField10.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabelaAnalise();
                }
            }

        });
    }

    public final void teclaInserirRaioX() {
        jTextField12.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    inserirTabelaRaioX();
                }
            }

        });
    }

    public final void mostrarData() {
        jComboBox5.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    mostraPorData("SELECT date(h.dataAtendimento) as data from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                            + "inner join pacientes p on t.codigoPaciente = p.idpaciente where p.idpaciente=" + getCodigoPaciente2());
                    jLabel56.setText("A sua ultima consulta foi realizada no dia: " + controllerHistoricoClinico.getUltimadata(getCodigoPaciente2()));
                }
            }

        });
    }

    public boolean salvarPedidoExameItem(int codigoExame, int codProduto, int codigoStatus, int codigoProdutoIem, int quantidade) {
        ExamesPorFazerItem item = new ExamesPorFazerItem(codigoExame, codProduto, codigoStatus, codigoProdutoIem, quantidade);
        controllerExamesporFazerItens.create(item);
        return true;
    }

    public void carregarDadosItemSeleccionado() {
        String nome = jList1.getSelectedValue();
        jLabel42.setText(nome);
        jLabel5.setText(nome);
        String designacaoServico = controllerTriagem.getNomeServicoConsulta(jLabel42.getText());
        jLabel75.setText(designacaoServico);
        Utente clientes = controllerBeneficiario.getDadosCliente(jLabel42.getText());
        String seguradora = controllerTriagem.getNomeSeguraora(jLabel42.getText());
        String sexo = controllerBeneficiario.getSexo(nome);
        int idade = controllerBeneficiario.getIdade(nome);
        jLabel13.setText(String.valueOf(idade) + " Anos");
        jLabel40.setText(seguradora);
        jLabel8.setText(clientes.getNomePai());
        jLabel11.setText(clientes.getNomeMae());
        jLabel15.setText(clientes.getTelefone());
        jLabel32.setText(String.valueOf(idade));
        jLabel71.setText(sexo);
        jLabel72.setText("Idade: " + idade + " Anos");
        jLabel73.setText("Genêro: " + sexo);

    }

    public void carregarDadosItemSeleccionado1() {

        jLabel5.setText(jLabel42.getText());
        String designacaoServico = controllerTriagem.getNomeServicoConsulta(jLabel42.getText());
        jLabel75.setText(designacaoServico);
        Utente clientes = controllerBeneficiario.getDadosCliente(jLabel42.getText());
        jLabel8.setText(clientes.getNomePai());
        jLabel11.setText(clientes.getNomeMae());
        jLabel15.setText(clientes.getTelefone());

    }

    public void carregarDadosClinico(int codigo) {
        historialClinico = controllerHistoricoClinico.getDadosClinicoDados(codigo);
        String re = controllerReceita.getReceita(getCodigoPaciente());
        jTextArea7.setText(historialClinico.getQueixaPrincipal());
        jTextArea6.setText(historialClinico.getHistorialDoenca());
        jTextField21.setText(historialClinico.getExameFisico());
        jTextField8.setText(historialClinico.getHipoteseDiagnostico());
        jTextArea8.setText(historialClinico.getApf());
        jTextField22.setText(historialClinico.getCabeca());
        jTextField23.setText(historialClinico.getPescoco());
        jTextField24.setText(historialClinico.getTorax());
        jTextField27.setText(historialClinico.getAbdomem());
        jTextField26.setText(historialClinico.getOrgaogenital());
        jTextField25.setText(historialClinico.getMembroinferior());
        jTextArea5.setText("" + re);
    }

    public void carregarDadosTriagem() {

        int codigo = controllerBeneficiario.getCodigoUtente(jLabel42.getText());
        Triagem triagem = controllerTriagem.getDadosTriagem(codigo);
        jLabel17.setText(triagem.getTemperatura());
        jLabel19.setText("" + triagem.getAltura());
        jLabel20.setText("" + triagem.getPeso());
        jLabel27.setText("" + triagem.getPulso());
        jLabel24.setText("" + triagem.getIMC());
        jLabel23.setText("" + triagem.getTensao());
        System.out.println("Fuma:" + triagem.getFumo());
        System.out.println("Bebe:" + triagem.getBebe());
        jLabel44.setText("" + triagem.getFumo());
        jLabel46.setText("" + triagem.getBebe());
        jLabel77.setText("" + triagem.getSaturacaoOxigenio());

    }

    public void carregarDadosDoencasCronica() {

        historicaInterno = controllerHistoricoClinico.getDoencasCronicas(getCodigoPaciente());
        try {
            if (!historicaInterno.getDoencas().isEmpty() || !historicaInterno.getAlergias().isEmpty()) {
                jTextField6.setText("" + historicaInterno.getSetUltimoTratamento());
            }
            jTextField5.setText("" + historicaInterno.getDoencas());
            jTextField4.setText("" + historicaInterno.getAlergias());

        } catch (Exception e) {
        }

    }

    public int getCodigoTriagem() {
        int codigo = controllerBeneficiario.getCodigoUtente(jLabel5.getText());
        return controllerTriagem.getLastCodigoByPaciente(codigo);

    }

    public String getQueixa() {
        return jTextArea7.getText();
    }

    public String getDoenca() {
        return jTextArea6.getText();
    }

    public String getAPF() {
        return jTextArea8.getText();
    }

    public String getExames() {
        return jTextField21.getText();
    }

    public String getCabeca() {
        return jTextField22.getText();
    }

    public String getPescoco() {
        return jTextField23.getText();
    }

    public String getTorax() {
        return jTextField24.getText();
    }

    public String getAbdomem() {
        return jTextField27.getText();
    }

    public String getOrgaogenital() {
        return jTextField26.getText();
    }

    public String getMembros() {
        return jTextField25.getText();
    }

    public String getDiagnostico() {
        return jTextField8.getText();
    }

    public String getReceita() {
        return jTextArea5.getText();
    }

    public int getCodigoHistorico() {
        return controllerHistoricoClinico.getLastCodigobyHystorico(getCodigoTriagem(), getCodigoPaciente());
    }

    public int getCodigoMedico() {
        return controllerMedico.getCodigoMedicoUsername(NomeMedico);
    }

    public void limparExame() {
        jEditorPane2.setText("");
    }

    public void limpar() {

        jTextArea8.setText("");
        jTextArea7.setText("");
        jTextArea6.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        //  jTextField22.setText("");
        jTextField23.setText("");
        jTextField8.setText("");
        jTextField3.setText("");
        jTextField6.setText("");
        jTextField5.setText("");
        jTextField4.setText("");
        jTextField25.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jTextField24.setText("");
        jTextField23.setEditable(true);
        jTextField22.setEditable(true);
        jTextField5.setEditable(true);

    }

    public int getCodigoCids() {
        return controllerCids.getCodigoCids(jComboBox1.getSelectedItem().toString());
    }

    public int getCodigoPaciente() {
        return controllerBeneficiario.getCodigoUtente(jLabel42.getText());
    }

    public int getCodigoPaciente2() {
        return controllerBeneficiario.getCodigoUtente(jComboBox5.getSelectedItem().toString());
    }

//    public Date getData3() {
//        return d.converteDataSql(jDateChooser3.getDate());
//    }
    public Date getData5() {
        return d.converteDataSql(jDateChooser5.getDate());
    }

    public Date getData6() {
        return d.converteDataSql(jDateChooser3.getDate());
    }

    public void salvarHistoricoPaciente() {
        int codigoDoenca = controllerHistoricoClinico.getCodigoDoenca(getCodigoPaciente());
        historialClinico.setCodigoCids(getCodigoCids());
        historialClinico.setCodigoMedico(getCodigoMedico());
        historialClinico.setCodigoPaciente(getCodigoPaciente());
        historialClinico.setCodigoconsulta(getCodigoTriagem());
        historialClinico.setExameFisico(getExames());
        historialClinico.setHistorialDoenca(getDoenca());
        historialClinico.setHipoteseDiagnostico(getDiagnostico());
        historialClinico.setQueixaPrincipal(getQueixa());
        historialClinico.setApf(getAPF());
        historialClinico.setCodigoDoenca(codigoDoenca);
        historialClinico.setCabeca(getCabeca());
        historialClinico.setPescoco(getPescoco());
        historialClinico.setTorax(getTorax());
        historialClinico.setAbdomem(getAbdomem());
        historialClinico.setOrgaogenital(getOrgaogenital());
        historialClinico.setMembroinferior(getMembros());
        controllerHistoricoClinico.salvar(historialClinico);

    }

    public String getDoencaCronicas() {
        return jTextField4.getText();
    }

    public String getMedicamentoAlergico() {
        return jTextField5.getText();
    }

    public String getUltimo() {
        return jTextField6.getText();
    }

    public void salvarDoencas() {
        HistoricaInterno hist = new HistoricaInterno();
        if (!controllerHistoricoClinico.existeHistoriaDoenca(getCodigoPaciente())) {
            hist.setDoencas(getDoencaCronicas());
            hist.setAlergias(getMedicamentoAlergico());
            hist.setSetUltimoTratamento(getUltimo());
            hist.setCodigoPaciente(getCodigoPaciente());
            controllerHistoricoClinico.salvarDoencas(hist);
        }

    }

    public void editarHistoricoPaciente(int codigo) {
        //int codigoDoenca = controllerHistoricoClinico.getCodigoDoenca(getCodigoPaciente());
        historialClinico.setExameFisico(getExames());
        historialClinico.setHistorialDoenca(getDoenca());
        historialClinico.setHipoteseDiagnostico(getDiagnostico());
        historialClinico.setQueixaPrincipal(getQueixa());
        historialClinico.setApf(getAPF());
        historialClinico.setCabeca(getCabeca());
        historialClinico.setPescoco(getPescoco());
        historialClinico.setTorax(getTorax());
        historialClinico.setAbdomem(getAbdomem());
        historialClinico.setOrgaogenital(getOrgaogenital());
        historialClinico.setMembroinferior(getMembros());
        controllerHistoricoClinico.editar(historialClinico, codigo);

    }

    public void salvarReceita(int codigo) {
        receita.setCodigoMedico(getCodigoMedico());
        receita.setCodigoPaciente(getCodigoPaciente());
        receita.setDesignacao(getReceita());
        receita.setCodigoHistorico(codigo);
        controllerReceita.salvar(receita);
    }

    public void editarReceital(int codigo) {
        receita.setDesignacao(getReceita());
        controllerReceita.editar(receita, codigo);
    }

    public void limparReceita() {
        jTextArea5.setText("");
    }

    public void salvarPedido() {
        pedidoExames.setCodigoMedico(getCodigoMedico());
        pedidoExames.setCodigoTriagem(getCodigoHistorico());
        pedidoExames.setCodigoPaciente(getCodigoPaciente());
        pedidoExames.setDataPedido(getData5());
//        pedidoExames.setHora(getHoraConsulta());

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int codigoServico = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            pedidoExames.setCodigoServico(codigoServico);
            controllerPedidoExames.Salvar(pedidoExames);
        }
    }

    public void inserirTabelaAnalise() {
        Object fila[] = new Object[2];
        int codigoServico = controllerServico.getCodigoServico(jComboBox3.getSelectedItem().toString());
        fila[0] = codigoServico;
        fila[1] = jComboBox3.getSelectedItem().toString();

        defaultTableModel.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(497);
        jTable1.getColumnModel().getColumn(1).setResizable(!false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    public final void inserirTabelaRaioX() {
        Object fila[] = new Object[2];
        int codigoServico = controllerServico.getCodigoServico(jComboBox11.getSelectedItem().toString());
        fila[0] = codigoServico;
        fila[1] = jComboBox11.getSelectedItem().toString();

        defaultTableModel2.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable5.getColumnModel().getColumn(0).setResizable(false);
        jTable5.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable5.getColumnModel().getColumn(1).setResizable(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jTable5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    public void inserirTabelaEcografia() {
        Object fila[] = new Object[2];
        int codigoServico = controllerServico.getCodigoServico(jComboBox2.getSelectedItem().toString());
        fila[0] = codigoServico;
        fila[1] = jComboBox2.getSelectedItem().toString();

        defaultTableModel1.addRow(fila);
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    public final void mostraHistoricoClinico(String sql) {

        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getTableHeader().setReorderingAllowed(false);
            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("h.idHistoricoClinico"), rs.getString("p.nomeCompleto"), rs.getString("h.queixaPresente"), rs.getString("h.historiaDoencaActual"),
                    rs.getString("h.exameFisico"), rs.getString("h.hipoteseDiagnostico")

                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void limparTabela() {
        defaultTableModel.setRowCount(0);
        jEditorPane2.setText(data);
    }

    public void limparTabela1() {
        defaultTableModel1.setRowCount(0);
        jEditorPane1.setText("");
        jTextField14.setText("");
    }

    public void limparTabela2() {
        defaultTableModel2.setRowCount(0);
        jEditorPane3.setText("");
    }

    public final void mostraHistoricoClinicoPorData(String sql) {
        System.out.println("Teste:" + sql);
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTableHistorico.getModel();
            jTableHistorico.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableHistorico.getColumnModel().getColumn(0).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTableHistorico.getColumnModel().getColumn(1).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTableHistorico.getColumnModel().getColumn(2).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(3).setPreferredWidth(250);
            jTableHistorico.getColumnModel().getColumn(3).setResizable(false);
            jTableHistorico.getColumnModel().getColumn(4).setPreferredWidth(220);
            jTableHistorico.getColumnModel().getColumn(4).setResizable(false);

            jTableHistorico.getTableHeader().setReorderingAllowed(false);
            jTableHistorico.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableHistorico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("h.idHistoricoClinico"), rs.getString("p.idpaciente"), rs.getString("h.historiaDoencaActual"),
                    rs.getString("h.exameFisico"), rs.getString("h.hipoteseDiagnostico")

                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void mostraPorData(String sql) {
        try {
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            jTable4.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getTableHeader().setReorderingAllowed(false);
            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("data")

                });
            }
        } catch (SQLException ex) {
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
            java.util.logging.Logger.getLogger(HomeMedicos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeMedicos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeMedicos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeMedicos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomeMedicos().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JEditorPane jEditorPane3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTableHistorico;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextArea jTextField21;
    private javax.swing.JTextArea jTextField22;
    private javax.swing.JTextArea jTextField23;
    private javax.swing.JTextArea jTextField24;
    private javax.swing.JTextArea jTextField25;
    private javax.swing.JTextArea jTextField26;
    private javax.swing.JTextArea jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextArea jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
