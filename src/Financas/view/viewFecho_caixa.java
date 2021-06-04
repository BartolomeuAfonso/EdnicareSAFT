/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import sf.ce.utilizacoes.Data;
import Financas.controller.ctr_Caixa;
import Financas.controller.ctr_Centrocusto;
import Financas.controller.ctr_Contas;
import Financas.controller.ctr_FecharCaixa;
import Financas.controller.ctr_Movimentacoes;
import CLINICA.controller.ControllerUsuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import Financas.Modelo.FecharCaixa;
import Financas.Modelo.Movimentacoes;

/**
 *
 * @author El Router
 */
public class viewFecho_caixa extends javax.swing.JInternalFrame {

    private ctr_FecharCaixa controller;
    private ctr_Caixa ctrCaixa;
    private ctr_Movimentacoes controllerMovimento;
    private ControllerUsuario ctr_user;
    Movimentacoes modelo;
    private Connection conn;
    private DecimalFormat decimalformat;
    public String v1, v2;
    Date dataAtual = new Date();
    Data d = new Data();
    JDesktopPane desktop;

    public viewFecho_caixa(Connection conn, JDesktopPane desktop) {
        initComponents();
        desktop.add(this);
        jDateChooserDataLancamento.setDate(new Date());
        this.conn = conn;
        controller = new ctr_FecharCaixa(conn);
        ctrCaixa = new ctr_Caixa(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);
        ctr_user = new ControllerUsuario(conn);
        modelo = new Movimentacoes();
        decimalformat = new DecimalFormat("#,##0.00");

        jComboBoxCaixa.setModel(new DefaultComboBoxModel(ctrCaixa.getDescricao().toArray()));
        jComboBoxCaixas.setModel(new DefaultComboBoxModel(ctrCaixa.getDescricao().toArray()));
        jComboBoxOperador.setModel(new DefaultComboBoxModel(ctr_user.getUsernameRecepcao().toArray()));
        jComboBoxOperador.insertItemAt("", 0);
        jComboBoxOperador.setSelectedIndex(0);
        jComboBoxCaixa.insertItemAt("", 0);
        jComboBoxCaixa.setSelectedIndex(0);
        jComboBoxCaixas.insertItemAt("PARA O CAIXA -->", 0);
        jComboBoxCaixas.setSelectedIndex(0);
        Calendar ne = Calendar.getInstance();
        jTextFieldHora.setText(String.format("%1$tH:%1$tM:%1$tS", ne));

        jTextFieldTotal.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldTotal.getText().isEmpty()) {
                        if (!jTextFieldTotal.getText().equals("0,00")) {
                            v1 = jTextFieldSaldoCaixa.getText().replaceAll(",00", "");
                            v2 = removerPonto(v1);
                            double saldo = Double.parseDouble(v2);
                            double totalFinal = Double.parseDouble(jTextFieldTotal.getText());
                            double resultado = totalFinal - saldo;
                            if (saldo < totalFinal) {
                                int opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Valor Final Maior que o Saldo Atual <br>Confirma o Valor Informado? </br></html> ", "Aviso do Sistema", 1);
                                if (opc == 0) {
                                    jTextFieldQuebra.setText(decimalformat.format(resultado));
                                    jTextFieldTotal.setText(decimalformat.format(totalFinal));
                                } else {
                                    jTextFieldTotal.setText("0,00");
                                }

                            } else {
                                int opc = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left>Valor Final Menor que o Saldo Atual <br>Confirma o Valor Informado? </br></html> ", "Aviso do Sistema", 1);
                                if (opc == 0) {
                                    jTextFieldQuebra.setText(decimalformat.format(resultado));
                                    jTextFieldTotal.setText(decimalformat.format(totalFinal));
                                } else {
                                    jTextFieldTotal.setText("0,00");
                                }
                            }

                        } else {

                        }
                    }
                }
            }

        });
    }

    public String getOperacaoCaixa(String caixa) {
        return ctrCaixa.getOperacaoByCod(caixa);
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

    public double getValor() {
        double valor = 0;
        v1 = jTextFieldTotal.getText().replaceAll(",00", "");
        v2 = removerPonto(v1);
        System.out.println("v2 " + v2);
        valor = Double.parseDouble(v2);

        return valor;
    }

    public double getSaldo() {
        double valor = 0;
        v1 = jTextFieldSaldoCaixa.getText().replaceAll(",00", "");
        v2 = removerPonto(v1);
        System.out.println("v2 " + v2);
        valor = Double.parseDouble(v2);

        return valor;
    }

    public double getQuebra() {
        double valor = 0;
        v1 = jTextFieldQuebra.getText().replaceAll(",00", "");
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
        jTextFieldHora = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldQuebra = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObs = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCaixas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxOperador = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Fecho de Caixa");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Cash on Delivery_32px.png"))); // NOI18N

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

        jLabel5.setText("Data de Fecho");

        jLabel3.setText("Hora:");

        jTextFieldHora.setEditable(false);
        jTextFieldHora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa de Origem"));

        jLabel6.setText("Histórioco/Obs");

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

        jLabel8.setText("Valor de Final");

        jLabel10.setText("Quebra");

        jTextFieldQuebra.setEditable(false);
        jTextFieldQuebra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldQuebra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQuebra.setText("0,00");
        jTextFieldQuebra.setToolTipText("Limpe o campo para inserir o valor de SAIDA");
        jTextFieldQuebra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldQuebraMouseClicked(evt);
            }
        });
        jTextFieldQuebra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQuebraKeyTyped(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreaObs.setColumns(20);
        jTextAreaObs.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObs);

        jLabel4.setText("Transferir:");

        jComboBoxCaixas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Troco:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0,00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel4))
                            .addGap(25, 25, 25))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(58, 58, 58)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuebra))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(jComboBoxCaixas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldQuebra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxCaixas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7))
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
                .addComponent(jComboBoxOperador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Checkmark_32px.png"))); // NOI18N
        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Forward_32px.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novos/Check_32px.png"))); // NOI18N
        jButton4.setText("Comprovativo");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        setBounds(250, 70, 456, 474);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCaixaActionPerformed
        if (getOperacaoCaixa(jComboBoxCaixa.getSelectedItem().toString()).equalsIgnoreCase("NAO")) {
            JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA não esta habilitado para Abertura e Fecho <br>Desejando habilitar, vá ao MENU FINANCEIRO , opção Cadastro de CAIXA </br></html> ", "Aviso do Sistema", 1);
        } else {
            jTextFieldSaldoCaixa.setText(decimalformat.format(ctrCaixa.getSaldoCaixa(jComboBoxCaixa.getSelectedItem().toString())));

        }
    }//GEN-LAST:event_jComboBoxCaixaActionPerformed

    private void jTextFieldSaldoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSaldoCaixaMouseClicked

    }//GEN-LAST:event_jTextFieldSaldoCaixaMouseClicked

    private void jTextFieldSaldoCaixaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSaldoCaixaKeyTyped

    }//GEN-LAST:event_jTextFieldSaldoCaixaKeyTyped

    private void jTextFieldTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTotalMouseClicked

    }//GEN-LAST:event_jTextFieldTotalMouseClicked

    private void jTextFieldTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyTyped

    }//GEN-LAST:event_jTextFieldTotalKeyTyped

    private void jTextFieldQuebraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldQuebraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuebraMouseClicked

    private void jTextFieldQuebraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuebraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuebraKeyTyped

    private void jComboBoxOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOperadorActionPerformed
        if (!jTextFieldSaldoCaixa.getText().equals("0,00")) {
            if (jComboBoxCaixa.getSelectedItem().toString().equals(jComboBoxOperador.getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, CAIXA de Destino não pode ser o mesmo <br>que o Caixa de Origem, por favor verificar!!! </br></html> ", "Aviso do Sistema", 1);
            } else {
                double v = Double.parseDouble(jTextFieldSaldoCaixa.getText());
                jTextFieldSaldoCaixa.setText(decimalformat.format(v));
                jTextFieldTotal.setText(decimalformat.format(v));
            }
        }
    }//GEN-LAST:event_jComboBoxOperadorActionPerformed

    public void habilitarLancamento() {
        jTextFieldSaldoCaixa.setText("");
        jTextFieldTotal.setText("");
        jTextFieldHora.setText(":");
        jTextFieldSaldoCaixa.setEditable(true);
        jTextFieldTotal.setEditable(true);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FecharCaixa fecho = new FecharCaixa();
        fecho.setData(d.converteDataSql(dataAtual));
        fecho.setCaixa(ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        fecho.setHora(jTextFieldHora.getText());
        fecho.setSaldoCaixa(getSaldo());
        fecho.setValorFecho(getValor());
        fecho.setQuebra(getQuebra());
        fecho.setUtilizador(ctr_user.getCodigoUtilizador(jComboBoxOperador.getSelectedItem().toString()));
        if (!jComboBoxCaixas.getSelectedItem().toString().equals("PARA O CAIXA -->")) {
            fecho.setCaixaTransferir("");
        } else {
            fecho.setCaixaTransferir(jComboBoxCaixas.getSelectedItem().toString());
        }
        fecho.setObservacao(jTextAreaObs.getText());
        if (controller.save(fecho)) {
            salvarMovimentoFecho();
            salvarMovimentoQuebra();
            ctrCaixa.updateStatus(ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()),"CAIXA FECHADO");
            //VALOR TRANSFERIDO PARA O CAIXA -->jComboBoxCaixas.getSelectedItem().toString()
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    public int getCodCaixa() {
        return ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString());
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
    }

    public void salvarMovimentoFecho() {
        modelo.setConta(19);
        modelo.setCaixa(ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao("Fechamento do Caixa - " + jTextFieldHora.getText() + " - " + jComboBoxOperador.getSelectedItem().toString() + "");
        modelo.setDestinatario(jComboBoxCaixa.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimento());
        modelo.setTitulo("FECHO DE CAIXA");
        modelo.setValor(getValor());
        modelo.setSaldo_anterior(getSaldoAnterior());
        modelo.setSaldo(getSaldoAnterior() - getValor());
        ctrCaixa.updateSaldo(1, getValor(), ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        controllerMovimento.save(modelo);
    }
    public void salvarMovimentoQuebra() {
        modelo.setConta(19);
        modelo.setCaixa(ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao("Quebra de Caixa - " + jComboBoxOperador.getSelectedItem().toString() + "");
        modelo.setDestinatario(jComboBoxCaixa.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimento());
        modelo.setTitulo("QUEBRA");
        modelo.setValor(getValor());
        modelo.setSaldo_anterior(getSaldoAnterior());
        modelo.setSaldo(getSaldoAnterior() - getValor());
        ctrCaixa.updateSaldo(1, getValor(), ctrCaixa.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        controllerMovimento.save(modelo);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    private javax.swing.JComboBox<String> jComboBoxCaixas;
    private javax.swing.JComboBox<String> jComboBoxOperador;
    private com.toedter.calendar.JDateChooser jDateChooserDataLancamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObs;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldQuebra;
    private javax.swing.JTextField jTextFieldSaldoCaixa;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
