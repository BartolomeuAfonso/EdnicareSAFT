/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.reportComprovativos;
import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Centrocusto;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_Movimentacoes;
import CLINICA.controller.ControllerUsuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import Financas.Modelo.Movimentacoes;

/**
 *
 * @author El Router
 */
public class frmReforcoCaixa extends javax.swing.JFrame {

    /**
     * Creates new form frmLancamentoMovimento
     */
    private ControllerUsuario users;
    private ctr_Caixa controller;
    private ctr_Contas controllerContas;
    private ctr_Centrocusto controllerCentro;
    private ctr_Movimentacoes controllerMovimento;
    Movimentacoes modelo;
    private Connection conn;
    private DecimalFormat decimalformat;
    JFrame control;
    reportComprovativos report;

    public String v1, v2, v3, v4;
    Date dataAtual = new Date();
    Data d = new Data();

    public frmReforcoCaixa() {
    }

    public frmReforcoCaixa(JFrame control, Connection conn) {
        this();
        initComponents();
        jDateChooserDataLancamento.setDate(new Date());
        this.conn = conn;
        controller = new ctr_Caixa(conn);
        controllerContas = new ctr_Contas(conn);
        controllerCentro = new ctr_Centrocusto(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);
        users = new ControllerUsuario(conn);
        modelo = new Movimentacoes();
        report = new reportComprovativos(conn);

        decimalformat = new DecimalFormat("#,##0.00");
        this.control = control;

        jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
        jComboBoxOperador.setModel(new DefaultComboBoxModel(users.getTodos().toArray()));
        jComboBoxOperador.insertItemAt("", 0);
        jComboBoxOperador.setSelectedIndex(0);
        jComboBoxCaixa.insertItemAt("Selecione o Caixa que está a ser reforçado->", 0);
        jComboBoxCaixa.setSelectedIndex(0);

        jTextFieldValorReforco.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    double vl = Double.parseDouble(jTextFieldValorReforco.getText());
                    jTextFieldValorReforco.setText(decimalformat.format(vl));
                    double v = getValorCaixa();
                    jTextFieldTotal.setText(decimalformat.format(v));

                }
            }

        });

        try {
            MaskFormatter hora = new MaskFormatter("##:##");
            jFormattedTextFieldHora.setFormatterFactory(new DefaultFormatterFactory(hora));
        } catch (ParseException ex) {
//            Logger.getLogger(frmNovo_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        iconeSistema();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }

    //        char car = evt.getKeyChar();
//        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)
//                && (car != (char) KeyEvent.VK_BACK_SPACE)) {
//            evt.consume();
//            JOptionPane.showMessageDialog(null, "Só é admissivel letras", "Validação", JOptionPane.INFORMATION_MESSAGE);
//        }
    public String getOperacaoCaixa(String caixa) {
        return controller.getOperacaoByCod(caixa);
    }

    public int getTipoMovimento() {
        return 1;//REVER...
    }

    public String removerPonto(String num) {
        String novoNumero = "";

        for (int i = 0; i < num.length(); ++i) {
            if (num.charAt(i) == ',') {
                break;
            } else if (num.charAt(i) != '.') {
                novoNumero += num.charAt(i);
            }
        }

        return novoNumero;
    }

    public double getValorCaixa() {
        double valor = 0, valor2 = 0, result = 0;
        if (!jTextFieldValorReforco.getText().isEmpty()) {
            if (!jTextFieldValorReforco.getText().equals("0,00")) {
                v1 = jTextFieldSaldoCaixa.getText().replaceAll(",00", "");
                v2 = removerPonto(v1);

                v3 = jTextFieldValorReforco.getText().replaceAll(",00", "");
                v4 = removerPonto(v3);

                valor = Double.parseDouble(v2);
                valor2 = Double.parseDouble(v4);
                result = valor + valor2;
            } else {

            }
        }

        return result;
    }

    public double getValor() {
        double valor = 0;
        v1 = jTextFieldValorReforco.getText().replaceAll(",00", "");
        v2 = removerPonto(v1);
        System.out.println("v2 " + v2);
        valor = Double.parseDouble(v2);
        return valor;
    }

    public double getSaldo() {
        double valor = 0;
        v1 = jTextFieldTotal.getText().replaceAll(",00", "");
        v2 = removerPonto(v1);
        System.out.println("v2 " + v2);
        valor = Double.parseDouble(v2);
        return valor;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooserDataLancamento = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCaixa = new javax.swing.JComboBox<>();
        jTextFieldSaldoCaixa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldHora = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldValorReforco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxOperador = new javax.swing.JComboBox<>();
        jButtonSalvar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonComprovativo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REFORÇO DE CAIXA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Saldo Atual");

        jLabel1.setText("Caixa Selecionado");

        jComboBoxCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCaixaActionPerformed(evt);
            }
        });

        jTextFieldSaldoCaixa.setEditable(false);
        jTextFieldSaldoCaixa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldSaldoCaixa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSaldoCaixa.setText("0,00");
        jTextFieldSaldoCaixa.setToolTipText("Limpe o campo para inserir o valor de ENTRADA");
        jTextFieldSaldoCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldSaldoCaixaMouseClicked(evt);
            }
        });
        jTextFieldSaldoCaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSaldoCaixaKeyTyped(evt);
            }
        });

        jLabel5.setText("Data de Abertura");

        jLabel3.setText("Hora:");

        jFormattedTextFieldHora.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldHora.setCaretColor(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldHora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa de Origem"));

        jLabel6.setText("Histórioco/Obs");

        jTextFieldValorReforco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldValorReforco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldValorReforco.setText("0,00");
        jTextFieldValorReforco.setToolTipText("Limpe o campo para inserir o valor de SAIDA");
        jTextFieldValorReforco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldValorReforcoMouseClicked(evt);
            }
        });
        jTextFieldValorReforco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorReforcoKeyTyped(evt);
            }
        });

        jLabel8.setText("VALOR DO REFORÇO");

        jLabel10.setText("TOTAL NO CAIXA");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTotal.setText("0,00");
        jTextFieldTotal.setToolTipText("Limpe o campo para inserir o valor de SAIDA");
        jTextFieldTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTotalMouseClicked(evt);
            }
        });
        jTextFieldTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTotalKeyTyped(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldValorReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorReforco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Operador/Responsável");

        jComboBoxOperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOperadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addComponent(jComboBoxOperador, 0, 261, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Checkmark_32px.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Forward_32px.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonComprovativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Check_32px.png"))); // NOI18N
        jButtonComprovativo.setText("Comprovativo");
        jButtonComprovativo.setEnabled(false);
        jButtonComprovativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComprovativoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonComprovativo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonComprovativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
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

        setSize(new java.awt.Dimension(456, 434));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        control.setEnabled(true);
        control.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void habilitarLancamento() {
        jTextFieldSaldoCaixa.setEditable(false);
        jTextFieldValorReforco.setEditable(false);
        jFormattedTextFieldHora.setEditable(false);
        jTextAreaDescricao.setEditable(false);
        jButtonComprovativo.setEnabled(true);
        jComboBoxOperador.setEnabled(true);
        jComboBoxCaixa.setEnabled(true);
        jDateChooserDataLancamento.setEnabled(true);
        jButtonSalvar.setEnabled(false);
        jTextFieldValorReforco.setEditable(false);
    }

    public int getCodCaixa() {
        return controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString());
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
    }
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        modelo.setConta(15);//REVER...
        modelo.setCaixa(controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao(jTextAreaDescricao.getText() + " - " + "Hora: " + jFormattedTextFieldHora.getText() + "-" + jComboBoxOperador.getSelectedItem().toString());
        modelo.setDestinatario(jComboBoxCaixa.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimento());
        modelo.setTitulo("REFORÇO");
        modelo.setValor(getValor());
        modelo.setSaldo(getSaldo());
        modelo.setSaldo_anterior(getSaldoAnterior());
        controller.updateSaldo(1, getValor(), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));

        if (!jTextFieldValorReforco.getText().equals("0,00")) {
            if (jComboBoxCaixa.getSelectedIndex() != 0) {
                int cof = JOptionPane.showConfirmDialog(rootPane, "CONFIRMAR O LANÇAMENTO", "Atenção", 1);
                if (cof == 0) {
                    if (controllerMovimento.save(modelo)) {
                        habilitarLancamento();
                    }
                }
            } else {

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Insira o Valor para reforço do Caixa");
        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldSaldoCaixaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSaldoCaixaKeyTyped

    }//GEN-LAST:event_jTextFieldSaldoCaixaKeyTyped

    private void jTextFieldSaldoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSaldoCaixaMouseClicked

    }//GEN-LAST:event_jTextFieldSaldoCaixaMouseClicked

    private void jTextFieldValorReforcoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorReforcoKeyTyped

    }//GEN-LAST:event_jTextFieldValorReforcoKeyTyped

    private void jTextFieldValorReforcoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldValorReforcoMouseClicked

    }//GEN-LAST:event_jTextFieldValorReforcoMouseClicked

    private void jComboBoxOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOperadorActionPerformed
        if (!jTextFieldSaldoCaixa.getText().equals("0,00")) {
            if (jComboBoxCaixa.getSelectedItem().toString().equals(jComboBoxOperador.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
            } else {
                double v = Double.parseDouble(jTextFieldSaldoCaixa.getText());
                jTextFieldSaldoCaixa.setText(decimalformat.format(v));
                jTextFieldValorReforco.setText(decimalformat.format(v));
            }
        }
    }//GEN-LAST:event_jComboBoxOperadorActionPerformed

    private void jTextFieldTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalMouseClicked

    private void jTextFieldTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalKeyTyped

    private void jComboBoxCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCaixaActionPerformed
        if (getOperacaoCaixa(jComboBoxCaixa.getSelectedItem().toString()).equalsIgnoreCase("NAO")) {
            JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA não esta habilitado para Abertura e Fecho <br>Desejando habilitar, vá ao MENU FINANCEIRO , opção Cadastro de CAIXA </br></html> ", "Aviso do Sistema", 1);
        } else {
            jTextFieldSaldoCaixa.setText(decimalformat.format(controller.getSaldoCaixa(jComboBoxCaixa.getSelectedItem().toString())));
            jTextFieldTotal.setText(decimalformat.format(controller.getSaldoCaixa(jComboBoxCaixa.getSelectedItem().toString())));
        }

    }//GEN-LAST:event_jComboBoxCaixaActionPerformed

    private void jButtonComprovativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComprovativoActionPerformed
        report.getComprovativo(Data.getData(jDateChooserDataLancamento), jFormattedTextFieldHora.getText(), jComboBoxCaixa.getSelectedItem().toString(), jTextFieldValorReforco.getText(), jTextFieldTotal.getText(), jTextAreaDescricao.getText(), jComboBoxOperador.getSelectedItem().toString());
    }//GEN-LAST:event_jButtonComprovativoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Timer tempo = new Timer(1000, new frmReforcoCaixa.hora());
        tempo.start();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmLancamentoMovimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmLancamentoMovimento().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonComprovativo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    private javax.swing.JComboBox<String> jComboBoxOperador;
    private com.toedter.calendar.JDateChooser jDateChooserDataLancamento;
    private javax.swing.JFormattedTextField jFormattedTextFieldHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldSaldoCaixa;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldValorReforco;
    // End of variables declaration//GEN-END:variables
    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar ne = Calendar.getInstance();
            jFormattedTextFieldHora.setText(String.format("%1$tH:%1$tM", ne));
        }
    }

}
