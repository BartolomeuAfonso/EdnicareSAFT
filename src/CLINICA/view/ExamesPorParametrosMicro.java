/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import CLINICA.controller.ControllerExamesporFazer;
import CLINICA.controller.ControllerUtente;
import CLINICA.controller.ControllerServico;
import CLINICA.controller.ControllerExamesporFazerItens;
import CLINICA.modelo.ExamesIntegrado;
import CLINICA.modelo.ExamesPorFazerItem;
import CLINICA.modelo.ExamesporFazer;
import CLINICA.modelo.ResultadoExame;
import CLINICA.controller.ControllerPossiveisResultados;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Sebastiao
 */
public class ExamesPorParametrosMicro extends javax.swing.JDialog {

    /**
     * Creates new form ExamesPorParametros
     */
    private int codigoPaciente;
    private int codigoExame;
    private final ControllerServico controllerServico;
    private final ControllerUtente controllerUtente;
    private final ControllerExamesporFazer controllerExamesporFazer;
    private final ControllerExamesporFazerItens controllerExamesporFazerItens;
    private final String produto;
    private final String data1;
    private String data2;
    private String dataPedido;
    private String exame;
    private int codigoStatusExame;
    private boolean statusBotaoboolean;
    private int codigoProduto;
    private final ControllerPossiveisResultados possiveisResultadosC;
    private List<TableCellEditor> editors;
    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    int codigoCategoria;

    public ExamesPorParametrosMicro(int codigoExame, int codigoCategoria, String data1, String produto, int codigoStatusExame, int codigoPaciente) {
        System.out.println("Codigo do Exame:" + codigoExame + "Data do Pedido:" + data1 + "Produto:" + produto + "Paciente:" + codigoPaciente + "");
        this.codigoPaciente = codigoPaciente;
        this.codigoCategoria = codigoCategoria;
        controllerExamesporFazer = new ControllerExamesporFazer(con);
        controllerExamesporFazerItens = new ControllerExamesporFazerItens(con);
        controllerServico = new ControllerServico(con);
        controllerUtente = new ControllerUtente(con);
        this.data1 = data1;
        this.data2 = data1;
        this.produto = produto;
        this.codigoStatusExame = codigoStatusExame;
        this.codigoExame = codigoExame;
        statusBotaoboolean = statusBotaoboolean;
        setLocationRelativeTo(null);
        initComponents();
        setModal(true);
        //btSalvar.setEnabled(statusBotao);

        possiveisResultadosC = new ControllerPossiveisResultados(con);
        carregarExames(false, codigoCategoria);
        codigoProduto = controllerServico.getCodigoServico(produto);
        getResultadosByPaciente(codigoExame);
        jLabel1.setText(produto);
        codigoExame = controllerServico.getCodigoServico(jLabel1.getText());

        JComboBox jcbResultados;
        boolean resultado_com_combobox = true;
        if (resultado_com_combobox) {
            jcbResultados = new JComboBox(possiveisResultadosC.listarPossiveisResultados(codigoExame));
            jcbResultados.setEditable(true);
            TableColumn col = jTable1.getColumnModel().getColumn(1);
            col.setCellEditor(new DefaultCellEditor(jcbResultados));
        }

        jTable1.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

                    int codigoServico = controllerServico.getCodigoServico(produto);
                    int codigoProdutoItens = controllerServico.getCodigosProdutoItemActualizado(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), codigoServico);
                    String resultado = "" + jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                    String referencia = ".";

                    controllerExamesporFazerItens.actualizarResultadoDoProdutoItem1(getCodigoExame(), resultado, referencia, getDataActual(), codigoProdutoItens);
                }
            }

        });
        iconeSistema();

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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btnFachar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable()
        {
            public TableCellEditor getCellEditor(int row, int column)
            {

                //int modelColumn = convertColumnIndexToModel( column );
                int modelColumn =  column ;

                System.out.println("modelColum: "+modelColumn);
                System.out.println("column: "+column);

                if (modelColumn == 1 && row < jTable1.getRowCount())
                return editors.get(row);
                else
                return super.getCellEditor(row, column);

            }
        }
        ;
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Exames com Parametros");

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Resultados");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btSalvar.setFont(new java.awt.Font("Century Gothic", 3, 10)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-save-as-filled-32.png"))); // NOI18N
        btSalvar.setText("Actualizar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btnFachar.setFont(new java.awt.Font("Century Gothic", 3, 10)); // NOI18N
        btnFachar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/icons8-exit-sign-32 (2).png"))); // NOI18N
        btnFachar.setText("Sair");
        btnFachar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFachar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFachar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Designação", "Resultado", "Valor de Referência"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public final void carregarExames(boolean listarTodos, int codigoCategoria) {

        codigoProduto = controllerServico.getCodigoServico(produto);

        List<ResultadoExame> lista = controllerExamesporFazer.getResultadosByPaciente3Micro(codigoPaciente, codigoCategoria, data1, data2, codigoStatusExame, listarTodos, true, codigoExame, codigoProduto);
        String colunsNames[] = {"Designação", "Resultado", "Valor de Referência"};

        editors = new ArrayList<TableCellEditor>();
        ArrayList<JComboBox> listaPossiveisResultados = new ArrayList<>();
        ArrayList<DefaultCellEditor> listaDefaultCellEditor = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            System.out.println("exame: " + lista.get(i).getExame());
            int codigoProdutoItem = possiveisResultadosC.getCodigoporProduto(lista.get(i).getExame(),codigoProduto);

            System.out.println("codigoProdutoItem " + codigoProdutoItem + " i = " + i);
            JComboBox combo = new JComboBox(possiveisResultadosC.listarPossiveisResultados(codigoProdutoItem));

            combo.setEditable(true);

            listaPossiveisResultados.add(combo);
        }

        for (JComboBox combo : listaPossiveisResultados) {
            DefaultCellEditor dce = new DefaultCellEditor(combo);

            listaDefaultCellEditor.add(dce);
        }

        for (DefaultCellEditor cellEditor : listaDefaultCellEditor) {
            editors.add(cellEditor);
        }

        //limparTabelaResultados();
        DefaultTableModel dataModel = new DefaultTableModel(colunsNames, 0);

        for (int i = 0; i < lista.size(); ++i) {
            System.out.println("Exame---------------->  " + lista.get(i).getExame());
            System.out.println("Resultado---------------->  " + lista.get(i).getReultado());
            try {

                String vData[]
                        = {
                            //""+lista.get(i).getCodigo(), 
                            lista.get(i).getExame(),
                            //lista.get(i).getReferencia(),
                            lista.get(i).getReultado(),
                            //lista.get(i).getDataResultado(),
                            lista.get(i).getReferencia()
                        };

                dataModel.addRow(vData);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        jTable1.setModel(dataModel);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);

//        jTableResultados.getColumnModel().getColumn(3).setPreferredWidth(10);
        //listarExame = listarExame;
    }

//    public boolean processar(String resultado,int codigoProdutoItem) {
//
//       
//    }
    public void actualizarResultados(int codigoExame) {
        int codigoProdutoItem;
        String resultado = "";

        boolean result = false;

        for (int i = 0; i < jTable1.getRowCount(); i++) {

            try {

                //    System.out.println("Resu:" + resultado);
                if (!resultado.equals("")) {

                    codigoProdutoItem = controllerServico.getCodigosProdutoItem(jTable1.getValueAt(i, 0).toString(), jLabel1.getText());

                    int codExameItem = controllerExamesporFazerItens.getCodigoExameItemComProdutoItem(codigoExame, jTable1.getValueAt(i, 0).toString());

                    System.out.println("CODIGO EXAME ITEM " + codExameItem);
                    resultado = "" + jTable1.getValueAt(i, 1).toString();
                    // resultado = jTable1.getValueAt(i, 1).toString();
                    if (controllerExamesporFazerItens.actualizarResultadoDoProdutoItem(codigoExame, resultado, getDataActual(), codigoProdutoItem));
                    {
//                            dispose();
                        System.out.println("Sql:" + controllerExamesporFazerItens.actualizarResultadoDoProdutoItem(codigoExame, resultado, getDataActual(), codigoProdutoItem));
                        result = true;
                    }
                }

            } catch (java.lang.NullPointerException e) {

            }
        }
        JOptionPane.showMessageDialog(null, "Dados Actualizados com Sucesso", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getCodigoPaciente() {
        System.out.println("Codigo do Paciente:" + codigoPaciente);
        return codigoPaciente;
    }

    public int getCodigoExame() {
        return codigoExame;
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

    public final List<ResultadoExame> getResultadosByPaciente(int codigoExame) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "     examesporfazer.`dataPedido` AS DataPedido,\n"
                + "     examesporfazeritems.`codigoExames` AS CodigoExames,\n"
                + "     examesporfazeritems.`dataResultado` AS DataResultado,\n"
                + "     examesporfazeritems.`resultado` AS Resultado,\n"
                + "     examesintegrado.`designacao` AS examesintegrado_designacao,\n"
                + "     examesporfazer.`codigoPaciente` AS examesporfazer_codigoPaciente\n"
                + "FROM\n"
                + "     `examesporfazer` examesporfazer INNER JOIN `examesporfazeritems` examesporfazeritems ON examesporfazer.`idexamesPorFazer` = examesporfazeritems.`codigoExames`\n"
                + "     INNER JOIN `servicos` servicos ON examesporfazeritems.`codigoProduto` = servicos.`idServico`\n"
                + "     INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "where   servicos.`designacao`='" + produto + "' AND examesporfazeritems.`codigoExames`=" + codigoExame;

        System.out.println("Consulta de vida:" + sql);
        List<ResultadoExame> lista = new ArrayList();
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ResultadoExame(rs.getInt("CodigoExames"), rs.getString("Resultado"), rs.getString("DataPedido"), rs.getString("DataResultado")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        actualizarResultados(codigoExame);
        carregarExames(false, codigoCategoria);
        dispose();

    }//GEN-LAST:event_btSalvarActionPerformed

    private void btnFacharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacharActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnFacharActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        carregarExamesporLike(false, jTextField1.getText().trim());
    }//GEN-LAST:event_jTextField1CaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btnFachar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public final void carregarExamesporLike(boolean listarTodos, String designacao) {

        codigoProduto = controllerServico.getCodigoServico(produto);

        List<ResultadoExame> lista = controllerExamesporFazer.getResultadosByPaciente3porLike(codigoPaciente, data1, data2, codigoStatusExame, listarTodos, true, codigoExame, codigoProduto, designacao);
        String colunsNames[] = {"Designação", "Resultado", "Valor de Referência"};

        editors = new ArrayList<TableCellEditor>();
        ArrayList<JComboBox> listaPossiveisResultados = new ArrayList<>();
        ArrayList<DefaultCellEditor> listaDefaultCellEditor = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            System.out.println("exame: " + lista.get(i).getExame());
            int codigoProdutoItem = possiveisResultadosC.getCodigoporProduto(lista.get(i).getExame(),codigoProduto);

            System.out.println("codigoProdutoItem " + codigoProdutoItem + " i = " + i);
            JComboBox combo = new JComboBox(possiveisResultadosC.listarPossiveisResultados(codigoProdutoItem));

            combo.setEditable(true);

            listaPossiveisResultados.add(combo);
        }

        for (JComboBox combo : listaPossiveisResultados) {
            DefaultCellEditor dce = new DefaultCellEditor(combo);

            listaDefaultCellEditor.add(dce);
        }

        for (DefaultCellEditor cellEditor : listaDefaultCellEditor) {
            editors.add(cellEditor);
        }

        //limparTabelaResultados();
        DefaultTableModel dataModel = new DefaultTableModel(colunsNames, 0);

        for (int i = 0; i < lista.size(); ++i) {
            System.out.println("Exame---------------->  " + lista.get(i).getExame());
            System.out.println("Resultado---------------->  " + lista.get(i).getReultado());
            try {

                String vData[]
                        = {
                            //""+lista.get(i).getCodigo(), 
                            lista.get(i).getExame(),
                            //lista.get(i).getReferencia(),
                            lista.get(i).getReultado(),
                            //lista.get(i).getDataResultado(),
                            lista.get(i).getReferencia()
                        };

                dataModel.addRow(vData);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        jTable1.setModel(dataModel);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);

//        jTableResultados.getColumnModel().getColumn(3).setPreferredWidth(10);
        //listarExame = listarExame;
    }

}
