package CLINICA.view;

import CLINICA.modelo.Utilizador;
import CLINICA.controller.ControllerUsuario;
import CLINICA.controller.ControllerTipoUtilizador;
import GestaoStock.controller.UsuarioController;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import sf.ce.utilizacoes.Utilitarios;

public class CadastroUtilizador extends javax.swing.JFrame {

    CLINICA.modelo.Utilizador utilizador = new CLINICA.modelo.Utilizador();
    ControllerUsuario controllerUsuario;
    ControllerTipoUtilizador tipoUtilizador;
    Data d = new Data();
    ConexaoBancos conexao;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    int codigoTipo;
    UsuarioController usuarioController = new UsuarioController();
    Utilitarios util = new Utilitarios();
    int flag = 1;

    public CadastroUtilizador(int codigo) throws SQLException {
        initComponents();
        con = new ConexaoBancos().ConexaoBD();
        this.codigoTipo = codigo;
        controllerUsuario = new ControllerUsuario(con);
        tipoUtilizador = new ControllerTipoUtilizador(con);
        setLocationRelativeTo(null);
        //      util.preencherCombobox(jComboBox2, "usuario", "username");
//        
        iconeSistema();
        if (codigoTipo == 1) {
            // util.preencherCombobox(jComboBox2, "utilizadores", "username");
            jComboBox1.setModel(new DefaultComboBoxModel(tipoUtilizador.getTipoUtilizadorRecepcao().toArray()));
            jComboBox2.setModel(new DefaultComboBoxModel(controllerUsuario.getTodos().toArray()));
            mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t on u.codigoTipoUtilizador=t.idTipoUtilizador");
        }
        if (codigoTipo == 2) {
            jComboBox1.setModel(new DefaultComboBoxModel(tipoUtilizador.getTipoLoja().toArray()));
            jComboBox2.setModel(new DefaultComboBoxModel(controllerUsuario.getTodos().toArray()));
            //  util.preencherCombobox(jComboBox2, "utilizadores", "username");
            mostraMedico("SELECT u.codUser as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.descricao as area FROM usuario u inner join acesso t on u.acesso=t.codPermissoes");

        }
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Utilizadores");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 11))); // NOI18N
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jLabel4.setText("Tipo Acesso");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(244, 244, 244));
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nome Completo"));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(244, 244, 244));
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Username"));

        jTextField4.setBackground(new java.awt.Color(244, 244, 244));
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contacto"));

        jTextField5.setBackground(new java.awt.Color(244, 244, 244));
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Endereço"));

        jTextField6.setBackground(new java.awt.Color(244, 244, 244));
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Email"));

        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-32 (1).png"))); // NOI18N
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPasswordField2.setBackground(new java.awt.Color(244, 244, 244));
        jPasswordField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Senha"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, 338, Short.MAX_VALUE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("1. Cadastro", jPanel2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-as-filled-32.png"))); // NOI18N
        jButton1.setText("Reset");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Username:");

        jPasswordField3.setBackground(new java.awt.Color(244, 244, 244));
        jPasswordField3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Senha"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, 0, 258, Short.MAX_VALUE))
                    .addComponent(jPasswordField3))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("2. Reset senha", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Contactos", "Nivel de Acesso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-cancel-filled-32.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("3. Lista de Usúario", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (flag == 1) {
            int codigo = 1;
            int codigoArea = 12;
            if (codigoTipo == 1) {
                if (!jPasswordField2.getText().isEmpty()) {
                    salvar();
                    limpar();
                    mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t on u.codigoTipoUtilizador=t.idTipoUtilizador");
                } else{
                   JOptionPane.showMessageDialog(null, "Senha é campo de Carácter obrigatório, por favor inserir senha", "Erro", JOptionPane.ERROR_MESSAGE);                    
                }
            } else {
                if (jComboBox1.getSelectedItem().equals("GERENTE")) {
                    salvar();
                    mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t on u.codigoTipoUtilizador=t.idTipoUtilizador");
                    limpar();
                } else {
                    codigo = 2;
                    codigoArea = 11;
                    salvar();
                    mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t on u.codigoTipoUtilizador=t.idTipoUtilizador");
                    limpar();
                }
            }
        }
        if (flag == 2) {
            int codigo = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            editar(codigo);
            limpar();
            mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t on u.codigoTipoUtilizador=t.idTipoUtilizador");

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    public String getReset() {
        return jPasswordField3.getText();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (codigoTipo == 1) {
            int codigo = controllerUsuario.getCodigoUtilizadorUsername(jComboBox2.getSelectedItem().toString());
            controllerUsuario.getAltualizarSenha(getReset(), codigo);
            jPasswordField3.setText("");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        // TODO add your handling code here:
        mostraMedico("SELECT u.idUtilizador as codigo,u.nomeCompleto as nome, u.contacto as telefone, t.designacao as area FROM utilizadores u inner join tipoutilizador t "
                + "on u.codigoTipoUtilizador=t.idTipoUtilizador where u.nomeCompleto like '%" + jTextField1.getText() + "%'");
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int linha = jTable1.getSelectedRow();
        if (linha != -1) {
            int codigoMedico = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                controllerUsuario.eliminar(codigoMedico);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione um produto na tabela!");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            jTextField2.setText("" + jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jTextField4.setText("" + jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            jButton4.setText("Editar");
            flag = 2;
            jTabbedPane1.setSelectedIndex(0);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    public int getCodigoTipoutilizador() {
        return tipoUtilizador.getCodigoTipoUtilizador(jComboBox1.getSelectedItem().toString());
    }

    public String getNomecompleto() {
        return jTextField2.getText();
    }

    public String getUsername() {
        return jTextField3.getText();
    }

    public String getContacto() {
        return jTextField4.getText();
    }

    public String getEmail() {
        return jTextField6.getText();
    }

    public String getMorada() {
        return jTextField5.getText();
    }

    public String getSenha() {
        return jPasswordField2.getText();
    }

    public void limpar() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jPasswordField2.setText("");

    }

    public final void mostraMedico(String sql) {
        //System.out.println("Teste:" + sql);
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            model.setNumRows(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("codigo"), rs.getString("nome"), rs.getString("telefone"),
                    rs.getString("area")

                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro!!!" + ex);
        }

    }

    public void salvar() {
        utilizador.setNomeCompleto(getNomecompleto());
        utilizador.setUsername(getUsername());
        utilizador.setMorada(getMorada());
        utilizador.setContacto(getContacto());
        utilizador.setCodigoStatus(1);
        utilizador.setCodigoTipoUtilizador(getCodigoTipoutilizador());
        utilizador.setEmail(getEmail());
        utilizador.setSenha(getSenha());
        controllerUsuario.salvar(utilizador);
    }

    public void editar(int codigo) {
        utilizador.setNomeCompleto(getNomecompleto());
        utilizador.setUsername(getUsername());
        utilizador.setMorada(getMorada());
        utilizador.setContacto(getContacto());
        utilizador.setCodigoStatus(1);
        utilizador.setCodigoTipoUtilizador(getCodigoTipoutilizador());
        utilizador.setEmail(getEmail());
        utilizador.setSenha(getSenha());
        controllerUsuario.editar(utilizador, codigo);
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
            java.util.logging.Logger.getLogger(Utilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Utilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Utilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Utilizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CadastroUtilizador().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
