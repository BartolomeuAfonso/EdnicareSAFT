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
import Financas.controller.ctr_Movimentacoes;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import Financas.Modelo.Movimentacoes;

/**
 *
 * @author El Router
 */
public class frmLancamentoMovimento extends javax.swing.JFrame {

    /**
     * Creates new form frmLancamentoMovimento
     */
    private ctr_Caixa controller;
    private ctr_Contas controllerContas;
    private ctr_Centrocusto controllerCentro;
    private ctr_Movimentacoes controllerMovimento;
    Movimentacoes modelo;
    private Connection conn;
    private DecimalFormat decimalformat;
    frmMovimentosCaixa control;
    public String v1, v2, caixas;
    Date dataAtual = new Date();
    Data d = new Data();
    int flag = 0, c = 0;

    public frmLancamentoMovimento() {
    }

    public frmLancamentoMovimento(frmMovimentosCaixa control, Connection conn, int flag, String caixa, int cont) {
        this();
        initComponents();
        jDateChooserDataLancamento.setDate(new Date());
        this.conn = conn;
        this.flag = flag;
        this.c = cont;
        this.caixas = caixa;
        controller = new ctr_Caixa(conn);
        controllerContas = new ctr_Contas(conn);
        controllerCentro = new ctr_Centrocusto(conn);
        controllerMovimento = new ctr_Movimentacoes(conn);
        modelo = new Movimentacoes();
        decimalformat = new DecimalFormat("#,##0.00");
        this.control = control;

        jComboBoxContas.setModel(new DefaultComboBoxModel(controllerContas.getDescricao().toArray()));
        jComboBoxContas.insertItemAt("", 0);
        jComboBoxContas.setSelectedIndex(0);

        iconeSistema();
        jTextFieldEntrada.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldEntrada.getText().isEmpty()) {
                        if (!jTextFieldEntrada.getText().equals("0,00")) {
                            if (!getTipoConta().equals("Conta RECEITA")) {
                                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Voce está lançando uma DESPESA na conta RECEITA. <br>Por Favor selecionar a conta correcta para continuar. </br></html> ", "Aviso do Sistema", 1);
                                jTextFieldEntrada.setText("0,00");
                                jTextFieldSaida.setFocusable(true);
                            } else {
                                jTextFieldSaida.setText("0,00");
                                double v = Double.parseDouble(jTextFieldEntrada.getText());
                                jTextFieldEntrada.setText(decimalformat.format(v));
                                jTextFieldSaida.setFocusable(true);
                                jTextFieldSaida.setEditable(false);
                            }
                        } else {
                            jTextFieldEntrada.setText("0,00");
                        }
                    }
                }
            }

        });
        jTextFieldSaida.addKeyListener(
                new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (!jTextFieldSaida.getText().isEmpty()) {
                        if (!jTextFieldSaida.getText().equals("0,00")) {
                            if (!getTipoConta().equals("Conta DESPESA")) {
                                JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Voce está lançando uma DESPESA na conta RECEITA. <br>Por Favor selecionar a conta correcta para continuar. </br></html> ", "Aviso do Sistema", 1);
                                jTextFieldSaida.setText("0,00");
                                jTextFieldEntrada.setFocusable(true);
                            } else {
                                jTextFieldEntrada.setText("0,00");
                                double v = Double.parseDouble(jTextFieldSaida.getText());
                                jTextFieldSaida.setText(decimalformat.format(v));
                                jTextFieldEntrada.setFocusable(true);
                                jTextFieldEntrada.setEditable(false);
                            }
                        } else {
                            jTextFieldSaida.setText("0,00");
                        }
                    }
                }
            }

        });

        ativar();
    }

    public void iconeSistema() {
        URL caminho = this.getClass().getResource("/meus icons/GRest.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
        this.setIconImage(iconeTitulo);
    }
    public void ativar() {
        if (flag == 0) {
            jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao().toArray()));
            jComboBoxCaixa.insertItemAt("", 0);
            jComboBoxCaixa.setSelectedIndex(0);
        } else {
            if (flag == 2) {
                setar();
                System.out.println("tipo " + getTipoConta());
                if (c == 1) {
                    jTextFieldSaida.setEditable(false);
                    jTextFieldEntrada.setEditable(true);
                } else {
                    jTextFieldSaida.setEditable(true);
                    jTextFieldEntrada.setEditable(false);
                }

            }
            jComboBoxCaixa.setModel(new DefaultComboBoxModel(controller.getDescricao2(caixas).toArray()));
            jComboBoxCaixa.insertItemAt(caixas, 0);
            jComboBoxCaixa.setSelectedIndex(0);
        }
    }

    public void setar() {
        if (!jTextFieldConta.getText().isEmpty()) {
            jTextFieldEntrada.setEditable(true);
            jTextFieldSaida.setEditable(true);
            jTextFieldDescricaoconta.setText(controllerContas.getDescricaoByCod(Integer.parseInt(jTextFieldConta.getText())));
            jTextFieldCentrocusto.setText(controllerContas.getCentroByDescricao(jTextFieldDescricaoconta.getText()));
        }
    }

    public String getTipoConta() {
        System.out.println("conta " + jTextFieldDescricaoconta.getText());
        return controllerCentro.getTipoByConta(jTextFieldDescricaoconta.getText());
    }

    public int getTipoMovimento() {
        if (getTipoConta().equals("Conta DESPESA")) {
            return 2;
        } else {
            return 1;
        }

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

    public int getCodCaixa() {
        return controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString());
    }

    public double getValor() {
        double valor = 0;
        if (getTipoConta().equals("Conta DESPESA")) {
            v1 = jTextFieldSaida.getText().replaceAll(",00", "");
            v2 = removerPonto(v1);
            System.out.println("v2 " + v2);
            valor = Double.parseDouble(v2);
        } else {
            v1 = jTextFieldEntrada.getText().replaceAll(",00", "");
            v2 = removerPonto(v1);
            System.out.println("v2 " + v2);
            valor = Double.parseDouble(v2);
        }
        return valor;
    }

    public double getSaldoAnterior() {
        int lastMovimento = controllerMovimento.getLastMovimentoByCaixa(getCodCaixa());
        double saldo = controllerMovimento.getLastSaldoByCaixa(lastMovimento);

        return saldo;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCaixa = new javax.swing.JComboBox<>();
        jDateChooserDataLancamento = new com.toedter.calendar.JDateChooser();
        jTextFieldDocumento = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldConta = new javax.swing.JTextField();
        jTextFieldDescricaoconta = new javax.swing.JTextField();
        jTextFieldCentrocusto = new javax.swing.JTextField();
        jComboBoxContas = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEntrada = new javax.swing.JTextField();
        jTextFieldSaida = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCheque = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lbCod = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lançamento de Movimento de Caixa");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Caixa Selecionado");

        jLabel2.setText("Data do Lançamento");

        jLabel3.setText("Nº Documento");

        jComboBoxCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDocumento))
                    .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooserDataLancamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Conta a Movimentar"));

        jLabel4.setText("Nº da  Conta");

        jLabel5.setText("Descrição");

        jLabel6.setText("Centro de Custo");

        jTextFieldConta.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldConta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldConta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldContaKeyTyped(evt);
            }
        });

        jTextFieldDescricaoconta.setEditable(false);
        jTextFieldDescricaoconta.setForeground(new java.awt.Color(102, 102, 102));

        jTextFieldCentrocusto.setEditable(false);
        jTextFieldCentrocusto.setForeground(new java.awt.Color(102, 102, 102));

        jComboBoxContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pesquisar Contas" }));
        jComboBoxContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDescricaoconta)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldConta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxContas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextFieldCentrocusto))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldDescricaoconta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldCentrocusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lançamento"));

        jLabel7.setText("VALOR ENTRADA");

        jLabel8.setText("VALOR SAIDA");

        jTextFieldEntrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldEntrada.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldEntrada.setText("0,00");
        jTextFieldEntrada.setToolTipText("Limpe o campo para inserir o valor de ENTRADA");
        jTextFieldEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldEntradaMouseClicked(evt);
            }
        });
        jTextFieldEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEntradaKeyTyped(evt);
            }
        });

        jTextFieldSaida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldSaida.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSaida.setText("0,00");
        jTextFieldSaida.setToolTipText("Limpe o campo para inserir o valor de SAIDA");
        jTextFieldSaida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldSaidaMouseClicked(evt);
            }
        });
        jTextFieldSaida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSaidaKeyTyped(evt);
            }
        });

        jLabel9.setText("Nº Cheque");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/meus icons/icons8_Calculator_16px_1.png"))); // NOI18N
        jButton1.setText("Calculadora");
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jTextFieldEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCheque)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Lançamento"));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
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

        lbCod.setForeground(new java.awt.Color(240, 240, 240));
        lbCod.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbCod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbCod)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(499, 551));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        control.setEnabled(true);
        control.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContasActionPerformed
        jTextFieldConta.setText("" + controllerContas.getCodByNome(jComboBoxContas.getSelectedItem().toString()));
        jTextFieldDescricaoconta.setText(jComboBoxContas.getSelectedItem().toString());
        jTextFieldCentrocusto.setText(controllerContas.getCentroByDescricao(jComboBoxContas.getSelectedItem().toString()));
    }//GEN-LAST:event_jComboBoxContasActionPerformed

    private void jTextFieldContaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContaKeyTyped
        if (!jTextFieldConta.getText().isEmpty()) {
            jTextFieldEntrada.setEditable(true);
            jTextFieldSaida.setEditable(true);
            jTextFieldDescricaoconta.setText(controllerContas.getDescricaoByCod(Integer.parseInt(jTextFieldConta.getText())));
            jTextFieldCentrocusto.setText(controllerContas.getCentroByDescricao(jTextFieldDescricaoconta.getText()));
        }

    }//GEN-LAST:event_jTextFieldContaKeyTyped

    public void habilitarLancamento() {
        jTextFieldEntrada.setText("");
        jTextFieldSaida.setText("");
        jTextFieldDocumento.setText("");
        jTextFieldDescricaoconta.setText("");
        jTextFieldCentrocusto.setText("");
        jTextAreaDescricao.setText("");
        jTextFieldCheque.setText("");
        jTextFieldConta.setText("");
        jComboBoxContas.setSelectedIndex(0);
        jTextFieldEntrada.setEditable(true);
        jTextFieldSaida.setEditable(true);
    }
    private void jTextFieldEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEntradaMouseClicked
        if (!jTextFieldSaida.getText().isEmpty()) {
            if (!jTextFieldSaida.getText().equals("0,00")) {
                if (!getTipoConta().equals("Conta DESPESA")) {
                    JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Voce está lançando uma DESPESA na conta RECEITA. <br>Por Favor selecionar a conta correcta para continuar. </br></html> ", "Aviso do Sistema", 1);
                    jTextFieldSaida.setText("0,00");
                } else {
                    jTextFieldEntrada.setText("0,00");
                    double v = Double.parseDouble(jTextFieldSaida.getText());
                    jTextFieldSaida.setText(decimalformat.format(v));
                }
            } else {
                jTextFieldSaida.setText("0,00");
            }
        }
    }//GEN-LAST:event_jTextFieldEntradaMouseClicked

    private void jTextFieldSaidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSaidaMouseClicked
        if (!jTextFieldEntrada.getText().isEmpty()) {
            if (!jTextFieldEntrada.getText().equals("0,00")) {
                if (!getTipoConta().equals("Conta RECEITA")) {
                    JOptionPane.showMessageDialog(rootPane, "<html><font color = \"#000000\"> <left>Atenção, Voce está lançando uma RECEITA na conta DESPESA. <br>Por Favor selecionar a conta correcta para continuar. </br></html> ", "Aviso do Sistema", 1);
                    jTextFieldEntrada.setText("0,00");
                } else {
                    jTextFieldSaida.setText("0,00");
                    double v = Double.parseDouble(jTextFieldSaida.getText());
                    jTextFieldEntrada.setText(decimalformat.format(v));
                }
            } else {
                jTextFieldEntrada.setText("0,00");
            }

        }
    }//GEN-LAST:event_jTextFieldSaidaMouseClicked

    private void jTextFieldEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEntradaKeyTyped

    }//GEN-LAST:event_jTextFieldEntradaKeyTyped

    private void jTextFieldSaidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSaidaKeyTyped

    }//GEN-LAST:event_jTextFieldSaidaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modelo.setConta(Integer.parseInt(jTextFieldConta.getText()));
        modelo.setCaixa(controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        modelo.setDate(d.converteDataSql(dataAtual));
        modelo.setDescricao(jTextAreaDescricao.getText());
        modelo.setDestinatario(jComboBoxCaixa.getSelectedItem().toString());
        modelo.setTipo_movimento(getTipoMovimento());
        modelo.setTitulo(jTextFieldDocumento.getText());
        modelo.setValor(getValor());
        modelo.setSaldo_anterior(getSaldoAnterior());

        if (getTipoMovimento() == 1) {
            modelo.setSaldo(getSaldoAnterior() + getValor());
            controller.updateSaldo(1, getValor(), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        } else {
            modelo.setSaldo(getSaldoAnterior() - getValor());
            controller.updateSaldo(2, getValor(), controller.getCodByNome(jComboBoxCaixa.getSelectedItem().toString()));
        }
        if (!jTextFieldDocumento.getText().isEmpty()) {
            if (flag != 2) {
                if (controllerMovimento.save(modelo)) {
                    JOptionPane.showMessageDialog(rootPane, "Lançamento realizado com sucesso");
                    habilitarLancamento();
                }
            } else {
                if (controllerMovimento.update(modelo, Integer.parseInt(lbCod.getText()))) {
                    JOptionPane.showMessageDialog(rootPane, "Lançamento realizado com sucesso");
                    habilitarLancamento();
                }
            }

        } else {
            int op = JOptionPane.showConfirmDialog(rootPane, "<html><font color = \"#000000\"> <left> Não foi informado Nº DOC.<br>Deseja Continuar? </br></html> ", "Atenção", 1);
            if (op == 0) {
                if (controllerMovimento.save(modelo)) {
                    habilitarLancamento();
                }
            } else {

            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Process proc = Runtime.getRuntime().exec("C:/Windows/system32/calc.exe");
        } catch (RuntimeException | IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCaixa;
    public static javax.swing.JComboBox<String> jComboBoxContas;
    public static com.toedter.calendar.JDateChooser jDateChooserDataLancamento;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextAreaDescricao;
    public static javax.swing.JTextField jTextFieldCentrocusto;
    public static javax.swing.JTextField jTextFieldCheque;
    public static javax.swing.JTextField jTextFieldConta;
    public static javax.swing.JTextField jTextFieldDescricaoconta;
    public static javax.swing.JTextField jTextFieldDocumento;
    public static javax.swing.JTextField jTextFieldEntrada;
    public static javax.swing.JTextField jTextFieldSaida;
    public static javax.swing.JLabel lbCod;
    // End of variables declaration//GEN-END:variables
}
