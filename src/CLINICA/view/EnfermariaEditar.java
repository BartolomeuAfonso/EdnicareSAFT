/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import CLINICA.controller.ControllerMarcarcaoConsulta;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerTriagem;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerUtente;
import CLINICA.modelo.Definicoes;
import CLINICA.modelo.Utente;
import CLINICA.modelo.Triagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.utilizacoes.Data;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.relatorios.RelatorioHistoricoClinico;
import CLINICA.som.ReproduzirMP3;
import GestaoStock.views.Login;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Familia do Fresco
 */
public class EnfermariaEditar extends javax.swing.JFrame {

    /**
     * Creates new form Triagem
     */
    // Metodos para tocar Música
    //String com o caminho do arquivo MP3 a ser tocado
    private Timer timer = null;
    private TimerTask tarefa = null;
    private boolean estadoSom;
    private int i, TEMPO = 5000;
    //
    ControllerMedico controllerMedico;
    ControllerUsuario controllerUsuario;
    ControllerTriagem controllerTriagem;
    ControllerServico controllerServico;
    ControllerUtente controllerBeneficiario;
    ConexaoBancos conexao = new ConexaoBancos();
    Triagem triagem = new Triagem();
    Data d = new Data();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    private DefaultListModel ls;
    RelatorioHistoricoClinico relatorioHistoricoClinico = new RelatorioHistoricoClinico();
    ControllerMarcarcaoConsulta controllerMarcarcaoConsulta;
    String codigoTriagem;
    String user;
    String nomePaciente;
    String urgente = "Emergência (Vermelho) - Atendiento Imediato.";
    AudioStream audios;

    public EnfermariaEditar(String nome, String nomePaciente, String codigoTriagem) {
        initComponents();
        this.user = nome;
        this.codigoTriagem = codigoTriagem;
        con = new ConexaoBancos().ConexaoBD();
        controllerMedico = new ControllerMedico(con);
        controllerMarcarcaoConsulta = new ControllerMarcarcaoConsulta(con);
        controllerUsuario = new ControllerUsuario(con);
        controllerTriagem = new ControllerTriagem(con);
        controllerServico = new ControllerServico(con);
        controllerBeneficiario = new ControllerUtente(con);
        teclaInser();
//        jDateChooser1.setDate(new Date());
        ls = new DefaultListModel();
        getPacienteEmEspera();
        mostraMedico("SELECT distinct p.nomecompleto,me.nomecompleto,s.designacao, e.designacao,m.estado FROM marcacaoconsulta m\n"
                + "inner join pacientes p on m.codigoPAciente = p.idPaciente\n"
                + "inner join medicos me on m.codigoMedico=me.idMedico\n"
                + "inner join especialidade_medico ep on ep.idEspecialidade =me.codigoEspecialidade\n"
                + "inner join servicos s on m.codigoServico = s.idServico\n"
                + "inner join empresaseguros e on e.idSeguros = p.codigoSeguro\n"
                + "where m.dataAtendimento=current_date and m.Atendido='SIM' AND p.nomecompleto ='" + nomePaciente + "'");
        setLocationRelativeTo(null);
//       
//        thread(TEMPO);
        iconeSistema();
    }

    public final void iconeSistema() {
        // URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        URL caminho = this.getClass().getResource("/sf/ce/imagens/Icons/logoteste2.jpg");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    public void thread(int TEMPO) {

        if (timer == null) {

            timer = new Timer();

            tarefa = new TimerTask() {

                @Override
                public void run() {
                    getPacienteEmEspera();

//                    for (int j = 0; j < jTable1.getRowCount(); j++) {
//                        if (!jTable1.getValueAt(i, i).equals("")) {
//                            System.out.println("Nome:" + jTable1.getValueAt(i, i));
                    tocarSom(true);
//                        }
//                    }
                }
            };

        }
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }

    public final void tocarSom(boolean pararSom) {
        InputStream in;
        try {

            in = new FileInputStream(new File(Definicoes.ALARME_SOM));
            audios = new AudioStream(in);
            AudioPlayer.player.start(audios);
            if (pararSom) {
                pararSomCronometado();
            }

            estadoSom = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "" + e);
        }
    }

    public void pararSomCronometado() {
        try {
            Thread.sleep(TEMPO);

            AudioPlayer.player.stop(audios);
            estadoSom = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(HomeEnfermagem.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EDITAR TRIAGEM");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados do Utente ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel1.setText("Nome Completo:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel2.setText("Pai:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel3.setText("Mãe:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel4.setText("Cartão (PS) nº:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel24.setText("A.P.Nº:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel14.setText("Idade:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel24))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 87, Short.MAX_VALUE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados da Triagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Altura:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Temperatura:");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Peso:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel11.setText("IMC:");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel12.setText("Pulso:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField5.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel15.setText("Tensão Aterial:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel16.setText("Frequência Respiratória:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel5.setText("1. Fuma");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel6.setText("2. Hábito Etico");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton1.setText("SIM");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("NÃO");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton3.setText("SIM");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("NÃO");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel21.setText("ºC");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "m", "cm" }));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel22.setText("cpm");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel23.setText("mmHg");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kg", "Gr" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addGap(80, 80, 80)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3)
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton4)
                        .addGap(109, 109, 109))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(39, 39, 39)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField7)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addContainerGap())
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel22)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton3)
                                .addComponent(jRadioButton4)
                                .addComponent(jLabel6)))
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jRadioButton2))
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-sign-32 (1).png"))); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Utente", "Médico", "Consulta", "Convênio", "Estado do Paceinte"
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

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Nota: os campos a vermelhos são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        actualizarIMC();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (dadosTriagemValidos()) {
            int codigo = Integer.parseInt(codigoTriagem);
            SalvarEditar(codigo);
            JOptionPane.showMessageDialog(null, "Triagem Alterado com sucesso");
            limparPaciente();
            dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Verfica os campos obrigatórios - Vermelhos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // tocarSom(true);
        dispose();
//        new Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        nomePaciente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        carregarDadosItemSeleccionado(nomePaciente);
        carregarDadosTriagem(nomePaciente);


    }//GEN-LAST:event_jTable1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    public int getCodigoUtilizador() {
        return controllerUsuario.getCodigoUtilizadorUsername(user);
    }

    public int getCodigoPaciente() {
        return controllerBeneficiario.getCodigoUtente(nomePaciente);
    }

    public int getCodigoMedico() {
        return controllerMedico.getCodigoMedico(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
    }

    public String getPulso() {
        return jTextField4.getText().trim();
    }

//    public String getGuia() {
//        return jTextField8.getText().trim();
//    }
    public float getIMC() {
        float imc = 0;
        try {
            imc = Float.parseFloat(jTextField5.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Erro:" + ex);
        }
        return imc;
    }

    public String getFreqRespiratoria() {
        return jTextField7.getText().trim();
    }

    public String getTensaoArterial() {
        return jTextField6.getText().trim();
    }

    public double getTemperatura() {
        double temperatura = 0;
        try {
            temperatura = Double.parseDouble(jTextField1.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Erro:" + ex);
        }
        return temperatura;
    }

    public double getAltura() {
        double altura = 0;
        try {
            altura = Double.parseDouble(jTextField3.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Erro:" + ex);
        }
        return altura;
    }

    public boolean dadosTriagemValidos() {
        return getPeso() > 0 && getTemperatura() > 0 && getAltura() > 0 && getIMC() >= 0;
    }

    public double getPeso() {
        double peso = 0;
        try {
            peso = Double.parseDouble(jTextField2.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Erro:" + ex);
        }
        return peso;
    }

    public void actualizarIMC() {
        double h = getAltura();
        double p = getPeso();
        double imc = (double) (p / Math.pow(h, 2));

        String massaCorporal = String.valueOf(imc);

        if (!massaCorporal.equalsIgnoreCase("Infinity")) {
            jTextField5.setText("" + imc);
        } else {
            jTextField5.setText("0");
        }
    }

    public void SalvarTriagem() {
        if (jRadioButton1.isSelected() && jRadioButton3.isSelected()) {
            triagem.setAltura(String.valueOf(getAltura()) + "" + jComboBox3.getSelectedItem().toString());
            triagem.setTemperatura(String.valueOf(getTemperatura()) + "" + jLabel21.getText());
            triagem.setPeso(String.valueOf(getPeso()) + "" + jComboBox4.getSelectedItem().toString());
            triagem.setPulso(getPulso());
            triagem.setCodigoMedico(getCodigoMedico());
            triagem.setCodigoPaciente(getCodigoPaciente());
            triagem.setCodigoUtilizador(getCodigoUtilizador());
//            triagem.setDataCadastro(getData());
            triagem.setFrequenciaCardiaca(getFreqRespiratoria() + "" + jLabel22.getText());
            triagem.setIMC(getIMC());
            triagem.setTensao(getTensaoArterial() + "" + jLabel23.getText());
            //     triagem.setGuiaFactura(getGuia());
            triagem.setFumo("SIM");
            triagem.setBebe("SIM");
            controllerTriagem.salvar(triagem);

        } else if (!jRadioButton1.isSelected() && jRadioButton3.isSelected()) {
            triagem.setAltura(String.valueOf(getAltura()) + "" + jComboBox3.getSelectedItem().toString());
            triagem.setTemperatura(String.valueOf(getTemperatura()) + "" + jLabel21.getText());
            triagem.setPeso(String.valueOf(getPeso()) + "" + jComboBox4.getSelectedItem().toString());
            triagem.setPulso(getPulso());
            triagem.setCodigoMedico(getCodigoMedico());
            triagem.setCodigoPaciente(getCodigoPaciente());
            triagem.setCodigoUtilizador(getCodigoUtilizador());
            // triagem.setDataCadastro(getData());
            triagem.setFrequenciaCardiaca(getFreqRespiratoria());
            triagem.setIMC(getIMC());
            triagem.setTensao(getTensaoArterial());
            //   triagem.setGuiaFactura(getGuia());
            triagem.setFumo("NAO");
            triagem.setBebe("SIM");
            controllerTriagem.salvar(triagem);
        } else if (jRadioButton1.isSelected() && !jRadioButton3.isSelected()) {
            triagem.setAltura(String.valueOf(getAltura()) + "" + jComboBox3.getSelectedItem().toString());
            triagem.setTemperatura(String.valueOf(getTemperatura()) + "" + jLabel21.getText());
            triagem.setPeso(String.valueOf(getPeso()) + "" + jComboBox4.getSelectedItem().toString());
            triagem.setPulso(getPulso());
            triagem.setCodigoMedico(getCodigoMedico());
            triagem.setCodigoPaciente(getCodigoPaciente());
            triagem.setCodigoUtilizador(getCodigoUtilizador());
//            triagem.setDataCadastro(getData());
            triagem.setFrequenciaCardiaca(getFreqRespiratoria());
            triagem.setIMC(getIMC());
            triagem.setTensao(getTensaoArterial());
            //  triagem.setGuiaFactura(getGuia());
            triagem.setFumo("SIM");
            triagem.setBebe("NAO");
            controllerTriagem.salvar(triagem);
        } else if (!jRadioButton1.isSelected() && !jRadioButton3.isSelected()) {
            triagem.setAltura(String.valueOf(getAltura()) + "" + jComboBox3.getSelectedItem().toString());
            triagem.setTemperatura(String.valueOf(getTemperatura()) + "" + jLabel21.getText());
            triagem.setPeso(String.valueOf(getPeso()) + "" + jComboBox4.getSelectedItem().toString());
            triagem.setPulso(getPulso());
            triagem.setCodigoMedico(getCodigoMedico());
            triagem.setCodigoPaciente(getCodigoPaciente());
            triagem.setCodigoUtilizador(getCodigoUtilizador());
            //   triagem.setDataCadastro(getData());
            triagem.setFrequenciaCardiaca(getFreqRespiratoria());
            triagem.setIMC(getIMC());
            triagem.setTensao(getTensaoArterial());
            //   triagem.setGuiaFactura(getGuia());
            triagem.setFumo("NAO");
            triagem.setBebe("NAO");
            controllerTriagem.salvar(triagem);
        }

    }

    public void SalvarEditar(int codigo) {
        triagem.setAltura(String.valueOf(getAltura()) + "" + jComboBox3.getSelectedItem().toString());
        triagem.setTemperatura(String.valueOf(getTemperatura()) + "" + jLabel21.getText());
        triagem.setPeso(String.valueOf(getPeso()) + "" + jComboBox4.getSelectedItem().toString());
        triagem.setPulso(getPulso());
        triagem.setCodigoMedico(getCodigoMedico());
        triagem.setCodigoPaciente(getCodigoPaciente());
        triagem.setFrequenciaCardiaca(getFreqRespiratoria());
        triagem.setIMC(getIMC());
        triagem.setTensao(getTensaoArterial());
        controllerTriagem.editar(triagem, codigo);

    }

    public void carregarDadosTriagem(int codigoPaciente) {
        triagem = controllerTriagem.getDadosTriagem(codigoPaciente);
        jTextField1.setText("" + triagem.getTemperatura());
        jTextField3.setText("" + triagem.getAltura());
        jTextField2.setText("" + triagem.getPeso());
        jTextField4.setText("" + triagem.getPulso());
        jTextField5.setText("" + triagem.getIMC());
        jTextField6.setText("" + triagem.getTensao());

    }

    public final void getPacienteEmEspera() {
        Object vNomes = new Object();
        ls = new DefaultListModel();
        for (int i = 0; i < controllerTriagem.PacienteEspera().size(); i++) {
            vNomes = controllerTriagem.PacienteEspera().get(i);
            ls.addElement(vNomes);
        }
//        jList1.setModel(ls);

    }

    public void limparPaciente() {

        jLabel17.setText("");
        jLabel18.setText("");
        jLabel19.setText("");
        jLabel27.setText("");
        jLabel20.setText("");
        jLabel25.setText("");

    }

    public void limparTriagem() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");

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

        String data = ano + "-" + (mes + 1) + "-" + dia;

        return data;
    }

    public void carregarDadosItemSeleccionado(String nome) {
        String nomeSeguro = controllerTriagem.getNomeSeguraora(nome);
        Utente clientes = controllerBeneficiario.getDadosCliente(nome);
        int idade = controllerBeneficiario.getIdade(nome);
        jLabel20.setText(String.valueOf(idade) + " Anos");
        jLabel27.setText(nome);
        jLabel17.setText(clientes.getNomePai());
        jLabel19.setText(clientes.getNomeMae());
        jLabel18.setText(clientes.getTomadorSegurado());
        jLabel25.setText(clientes.getApn());

    }

    public void carregarDadosTriagem(String nome) {

        int codigo = controllerBeneficiario.getCodigoUtente(nome);
        Triagem triagem = controllerTriagem.getDadosTriagem(codigo);
        jTextField1.setText(triagem.getTemperatura());
        jTextField3.setText("" + triagem.getAltura());
        jTextField2.setText("" + triagem.getPeso());
        jTextField4.setText("" + triagem.getPulso());
        jTextField5.setText("" + triagem.getIMC());
        jTextField6.setText("" + triagem.getTensao());
        jTextField7.setText("" + triagem.getFrequenciaCardiaca());

    }

    public final void mostraMedico(String sql) {
        System.out.println("Teste:" + sql);
        try {
            int i = 0;
            con = new ConexaoBancos().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("p.nomecompleto"), rs.getString("me.nomecompleto"), rs.getString("s.designacao"),
                    rs.getString("e.designacao"), rs.getString("m.estado")

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Triagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Triagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Triagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Triagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new EnfermariaHome().setVisible(true);
////            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

//    public void tocar() {
//        URL caminho = this.getClass().getResource("/som/Ambulance_Siren_Distant.mp3");
//        // String path = "C:\\Projecto Santomed\\ProVanguarda\\som\\Ambulance_Siren_Distant.mp3";
//        //Instanciação de um objeto File com o arquivo MP3
//        File mp3File = new File(caminho.getFile());
//        //Instanciação do Objeto MP3, a qual criamos a classe.
//        ReproduzirMP3 musica = new ReproduzirMP3(mp3File);
//        musica.play();
//
//    }
    public final void teclaInser() {
        jTextField1.addKeyListener(
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
                    jTextField3.requestFocus();
                }
            }

        });
        jTextField3.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField2.requestFocus();
                }
            }

        });
        jTextField2.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField7.requestFocus();
                }
            }

        });
        jTextField7.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    jTextField6.requestFocus();
                }
            }

        });
    }
}
