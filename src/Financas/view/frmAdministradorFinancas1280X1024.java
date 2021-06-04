/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.view;

import Financas.Relatorios.RelatorioVenda;
import sf.ce.utilizacoes.Formatacao;
import Gestao.Restaurantes.Controller.CategoriaController;
import Gestao.Restaurantes.Controller.ProdutosController;
import CLINICA.controller.ControllerEmpresa;
import CLINICA.controller.ControllerUsuario;
import Gestao.Restaurantes.Controller.ComprasController;
import Gestao.Restaurantes.Controller.MesaController;
import Gestao.Restaurantes.Controller.PontoVendaController;
import entidades.Produtos;
import Financas.Relatorios.RelatorioBasico;
import Gestao.Restaurantes.Controller.TipoFuncionarioController;
import Gestao.Restaurantes.Controller.UtilizadoresController;
import Gestao.Restaurantes.Controller.VendaItensController;
import Gestao.Restaurantes.Controller.VendasController;
import Gestao.Restaurantes.Controller.AberturaCaixaController;
import entidades.VendaItem;
import entidades.CompraProduto;
import entidades.Vendas;
import entidades.PedidoCozinha;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.utilizacoes.Data;
import GestaoStock.views.frmFormapagamento;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

//import para botoes
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import static java.lang.System.exit;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Rufino Router
 */
public final class frmAdministradorFinancas1280X1024 extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    // formPrincipal formularioP = new formPrincipal();
    String foto;
    TipoFuncionarioController tipoFuncionarioController;
    ControllerUsuario utilizadoresController;
    public int contBtn = 0;
    double totalGeral = 0.0;
    String categoria;
    String nomeEmpresa, data;
    Produtos produtos = new Produtos();
    ProdutosController produtosController;
    MesaController mesaController;
    CategoriaController categoriaController;
    ControllerEmpresa empresasController;
    VendaItensController vendaItensController;
    VendasController vendasController;
    PontoVendaController pontoVendaController;
    ComprasController pedidoMesaController;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    private String colunsNames[] = {"Designação", "Quantidade", "Preço Unitário", "Total"};
    private DefaultTableModel dataModel = new DefaultTableModel(colunsNames, 0);
    int codigoPermissao, flag = 1;
    private RelatorioVenda relatorioVenda;
    List<Produtos> lista = new ArrayList<Produtos>();
    Vendas vendas = new Vendas();
    VendaItem vendaItem = new VendaItem();
    Object listaproduto = new Object();
//    BDConexao conexao;
    PreparedStatement ps;
    ResultSet rs;
    PedidoCozinha pedido = new PedidoCozinha();
    
    CompraProduto pedidoMesa;
    private Vector<CompraProduto> itemsFactura;
    int codigoUsuario, codigoPedido;
    String nomeUtilizador, tipoMesa;
    private RelatorioBasico relatorioBasico;
    AberturaCaixaController aberturaCaixaController;
    boolean codigoCaixa;
    Data d = new Data();
    int codigoMesa, codigoProduto;
    int cont = 0;
    int eliminabotao = 0;
    
    Formatacao formatacao = new Formatacao();
    
    JButton botao;
    
    FlowLayout layout1;
    JPanel painel;
    
    JScrollPane pppp = new JScrollPane();
    
    JScrollPane painTTes;
    String dep;
    private Connection conexao;

    //JFrame frameTeste JFrame();    
    public frmAdministradorFinancas1280X1024(Connection con) {
        initComponents();
//        jButtonCaixa.setVisible(false);
//        jButton416.setVisible(false);

        this.conexao = con;
        relatorioVenda = new RelatorioVenda(conexao);
        iconeSistema();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jMenuItem49.setVisible(false);
        jMenu13.setVisible(false);
        jMenu15.setVisible(false);
        jMenuItem35.setVisible(false);
    }
    
    public frmAdministradorFinancas1280X1024(String nome) {
//        layout1 = new FlowLayout();
//        layout1.setAlignment((int) LEFT_ALIGNMENT);

        initComponents();
//        jButtonCaixa.setVisible(false);

//        jButton416.setVisible(false);
//        jMenuItem49.setVisible(false);
//        jMenu13.setVisible(false);
//        jMenu15.setVisible(false);
//        jMenuItem35.setVisible(false);
        iconeSistema();
        relatorioVenda = new RelatorioVenda(conexao);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//        jPanel1.setVisible(false);
        // criarBotoes();
        this.nomeUtilizador = nome;
        jLabelUsuario.setText(nomeUtilizador.toUpperCase());
        //this.dep = departamento;
        relatorioBasico = new RelatorioBasico(conexao);
//        itemsFactura = new Vector<PedidoMesa>();
        pontoVendaController = new PontoVendaController(conexao);
        categoriaController = new CategoriaController(conexao);
        
        produtosController = new ProdutosController(conexao);
        empresasController = new ControllerEmpresa(conexao);
        //     vendasController = new VendasController(conexao);
        //    aberturaCaixaController = new AberturaCaixaController(conexao);
        //    vendaItensController = new VendaItensController(conexao);
        //    pedidoMesaController = new ComprasController(conexao);
        nomeEmpresa = empresasController.getNomeEmpresa();

        //   mesaController = new MesaController(conexao);
        //   tipoFuncionarioController = new TipoFuncionarioController(conexao);
        utilizadoresController = new ControllerUsuario(conexao);
        codigoUsuario = utilizadoresController.getCodigoUtilizador(nome);
        //  codigoCaixa = aberturaCaixaController.CodigoAbertura(codigoUsuario);

        botao = new JButton();

        // codigoPermissao = utilizadoresController.getTipoUtilizador(nome);
        // codigoCaixa = aberturaCaixaController.CodigoAbertura(codigoUsuario);
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
    
    public void criarBotoes() {
        
        for (int i = 0; i < 30; i++) {
            
            JButton botao1 = new javax.swing.JButton();
            botao1.setText("Botao" + i);
            
            botao1.setBackground(Color.yellow);
            botao1.setPreferredSize(new Dimension(130, 80));
            
            botao1.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    botaoActionPerformed(evt);
                }
                
                private void botaoActionPerformed(ActionEvent evt) {
                    botao1.setText("JA");
                }
            });
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeMenu = new javax.swing.JTree();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenu17 = new javax.swing.JMenu();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        jMenu20 = new javax.swing.JMenu();
        jMenuItem52 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jMenuItem54 = new javax.swing.JMenuItem();
        jMenuItem55 = new javax.swing.JMenuItem();
        jMenuItem58 = new javax.swing.JMenuItem();
        jMenuItem57 = new javax.swing.JMenuItem();
        jMenuItem59 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ednicare - Tesouraria");
        setForeground(new java.awt.Color(0, 102, 153));
        setResizable(false);
        setState(2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jDesktopPane2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sf/ce/imagens/Icons/Tela.png"))); // NOI18N

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1453, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 181, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabelUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUsuario.setText("Lija Dalva");

        jLabel4.setText("Admin");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });

        jTreeMenu.setBackground(new java.awt.Color(51, 153, 255));
        jTreeMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Finanças");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Cadastros");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Caixas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Plano de Contas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Centro de Custos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Forma Pagamento");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Gerenciamento de Recibos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Pesquisar Recibos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Emitir Recibo");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Movimentações");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Abertura de Caixa");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fecho de Caixa");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Reforço de Caixa");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tranferência entre Caixas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Lançamentos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Contas a Receber");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Contas a Pagar");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Relatórios");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Caixas Cadastrados");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Plano de Conta");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Centro de Custos Cadastrados");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Lançamentos Financeiros");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("A Receber");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("A Pagar");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Caixa/Conta Corrente");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Análitico das Contas do Plano");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Análitico dos Centro de Custos");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demonstração de Resultados");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demostração de Resultados do Caixa");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTreeMenu.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeMenu.setAutoscrolls(true);
        jTreeMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTreeMenu.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jTreeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreeMenu);

        jLabel5.setText("Gráficos");
        jLabel5.setToolTipText("Gráficos Financeiros");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AKZ", "USD", "EUR" }));
        jComboBox1.setBorder(null);
        jComboBox1.setMinimumSize(new java.awt.Dimension(50, 15));
        jComboBox1.setPreferredSize(new java.awt.Dimension(50, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(12, 12, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(7, 7, 7))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelUsuario)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(332, 332, 332))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1123, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jMenu8.setText("Ficheiros");
        jMenu8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem26.setText("1. Cadastro de Caixa");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem26);

        jMenuItem34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem34.setText("2. Tabela de Pagamento");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem34);

        jMenuItem35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem35.setText("3. Pesquisar e gerenciar Recibos");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem35);

        jMenu13.setText("5. Contas a Receber");
        jMenu13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem31.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem31.setText("1. Contas a receber");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem31);

        jMenuItem33.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem33.setText("2. Relatório de Contas a receber");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem33);

        jMenuItem43.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem43.setText("3. Relatório de Contas a receber por Cliente");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem43);

        jMenu8.add(jMenu13);

        jMenu15.setText("6. Contas a Pagar");
        jMenu15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem44.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem44.setText("1. Contas a pagar");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem44);

        jMenuItem45.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem45.setText("2. Relatório de Contas a pagar");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem45);

        jMenuItem46.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem46.setText("3. Relatório de Contas a receber por Fornecedores");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem46);

        jMenu8.add(jMenu15);

        jMenu16.setText("7. Plano  de Contas");
        jMenu16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem47.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem47.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem47.setText("1. Plano de Contas");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem47);

        jMenuItem49.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem49.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem49.setText("2. SubContas(contas correntes)");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem49);

        jMenuItem48.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem48.setText("3. Relatório da Contas do Plano  de Contas");
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem48);

        jMenu8.add(jMenu16);

        jMenu17.setText("8. Cadastro de Centro de Custos");
        jMenu17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem50.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem50.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem50.setText("1. Cadatro Centro de Custos");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem50);

        jMenuItem51.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem51.setText("2. Relatório Analitico dos Centro de Custos");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        jMenu17.add(jMenuItem51);

        jMenu8.add(jMenu17);

        jMenu20.setText("9. Relatório gerenciais Financeiro");
        jMenu20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem52.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem52.setText("1. Relatório de Contas a receber");
        jMenuItem52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem52ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem52);

        jMenuItem53.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem53.setText("2. Relatório de Contas a Pagar");
        jMenu20.add(jMenuItem53);

        jMenuItem54.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem54.setText("3. Relatório de Caixa/Conta Corrente");
        jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem54ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem54);

        jMenuItem55.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem55.setText("4. Relatório Analitico das Contas do Plano de Contas");
        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem55);

        jMenuItem58.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem58.setText("5. Relatório Analitico do Centro de Custo");
        jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem58ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem58);

        jMenuItem57.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem57.setText("6. Relatório Sintéctico do Resultado Financeiro das Contas ");
        jMenuItem57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem57ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem57);

        jMenuItem59.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuItem59.setText("7. Relatório Sintéctico do Resultado Financeiro dos Caixas ");
        jMenuItem59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem59ActionPerformed(evt);
            }
        });
        jMenu20.add(jMenuItem59);

        jMenu8.add(jMenu20);

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        jMenuBar1.add(jMenu8);

        jMenu2.setText("Movimentos de Caixa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem36.setText("1. Movimento de Caixa");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem36);

        jMenuItem39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem39.setText("2. Abertura de Caixa");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem39);

        jMenuItem38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem38.setText("3. Reforço de Caixa");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem38);

        jMenuItem40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem40.setText("5. Transferência entre Caixas");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem40);

        jMenuItem41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem41.setText("6. Pesquisar Movimento de Caixa");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem41);

        jMenuItem42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem42.setText("7. Relatório Movimento de Caixa/Conta Corrente");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem42);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        this.setEnabled(false);
        frmFormapagamento iforn = new frmFormapagamento();
        iforn.setVisible(true);
        iforn.toFront();
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        this.setEnabled(false);
        frmCaixas hst = new frmCaixas(conexao, this);
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        this.setEnabled(false);
        frmMovimentoCaixa hst = new frmMovimentoCaixa(conexao, this, 1);//1 = Chamada principal.
        hst.setVisible(true);
//        hst.toFront();
//        new viewMovimentacoes(jDesktopPane2, conexao, 1).show();
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        
        frmContasReceber hst = new frmContasReceber(conexao, this, 1);//1 = Chamada Principal.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        new viewPlano_contas(conexao, jDesktopPane2).show();
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        
        new viewCentro_custos(conexao, jDesktopPane2).show();
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        this.setEnabled(false);
        frmTransferenciaCaixa hst = new frmTransferenciaCaixa(this, conexao);
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        this.setEnabled(false);
        frmAberturaCaixa hst = new frmAberturaCaixa(this, conexao);
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        this.setEnabled(false);
        frmReforcoCaixa hst = new frmReforcoCaixa(this, conexao);
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
//        this.setEnabled(false);
//        frmRelCaixa hst = new frmRelCaixa(this, conexao, 1);//2 = Chamada Menu.
//        hst.setVisible(true);
//        hst.toFront();
        new viewRelMovimentoCaixa(conexao, jDesktopPane2).show();
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        this.setEnabled(false);
        frmRelMovConta hst = new frmRelMovConta(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        this.setEnabled(false);
        frmRelMovCentrocusto hst = new frmRelMovCentrocusto(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        this.setEnabled(false);
        frmRelCaixa hst = new frmRelCaixa(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        this.setEnabled(false);
        frmMovimentoCaixa hst = new frmMovimentoCaixa(conexao, this, 1);//1 = Chamada principal.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        this.setEnabled(false);
        frmRelMovConta hst = new frmRelMovConta(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed
        this.setEnabled(false);
        frmRelMovCentrocusto hst = new frmRelMovCentrocusto(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem57ActionPerformed
        this.setEnabled(false);
        frmDemostracaoResultado hst = new frmDemostracaoResultado(this, conexao, 1);//2 = Chamada Menu.
        hst.setVisible(true);
        hst.toFront();
    }//GEN-LAST:event_jMenuItem57ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
//        this.setEnabled(false);
//        formGerenciarRecibos hst = new formGerenciarRecibos(this, conexao);//2 = Chamada Menu.
//        hst.setVisible(true);
//        hst.toFront();
        new viewGerenciar_recibos(jDesktopPane2, conexao).show();
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jTreeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMenuMouseClicked
        String atual = jTreeMenu.getLastSelectedPathComponent().toString();

        //Verifica se o objecto selecionado é a raiz da arvore
        if (atual.equals("Caixas")) {
            new viewCaixas(jDesktopPane2, conexao).show();
        }
        if (atual.equals("Plano de Contas")) {
            new viewPlano_contas(conexao, jDesktopPane2).setVisible(true);
        }
        if (atual.equals("Centro de Custos")) {
            new viewCentro_custos(conexao, jDesktopPane2).setVisible(true);
        }
        if (atual.equals("Forma Pagamento")) {
            new frmFormapagamento().setVisible(true);
        }
        if (atual.equals("Pesquisar Recibos")) {
            new viewGerenciar_recibos(jDesktopPane2, conexao).show();
        }
        if (atual.equals("Lançamentos")) {
            new viewMovimentacoes(jDesktopPane2, conexao, 1).show();
        }
        if (atual.equals("Abertura de Caixa")) {
            new viewAberturaCaixa(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Fecho de Caixa")) {
            new viewFecho_caixa(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Reforço de Caixa")) {
            new viewFecho_caixa(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Tranferência entre Caixas")) {
            new viewTransferencia_caixa(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Contas a Pagar")) {
            new viewContasPagar(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Contas a Receber")) {
            new viewContasReceber(conexao, jDesktopPane2).show();
        }
        if (atual.equals("A Pagar")) {
            new viewRelContasPagar(conexao, jDesktopPane2).show();
        }
        if (atual.equals("A Receber")) {
            new viewRelContasReceber(conexao, jDesktopPane2).show();
        }
        if (atual.equals("Demonstração de Resultados")) {
            new viewDemonstracaoResultados(jDesktopPane2, conexao).show();
        }
        if (atual.equals("Demostração de Resultados do Caixa")) {
            new viewDemonstracaoCaixa(jDesktopPane2, conexao).show();
        }
        if (atual.equals("Caixa/Conta Corrente")) {
            new viewRelMovimentoCaixa(conexao, jDesktopPane2).show();
        }
    }//GEN-LAST:event_jTreeMenuMouseClicked

    private void jMenuItem59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem59ActionPerformed
        new viewDemonstracaoCaixa(jDesktopPane2, conexao).show();
    }//GEN-LAST:event_jMenuItem59ActionPerformed

    private void jMenuItem44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        new viewRelContasPagar(conexao, jDesktopPane2).show();
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        new viewRelContasPagar(conexao, jDesktopPane2).show();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
//        new viewGraficos().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited

    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        dispose();

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem52ActionPerformed

//       public void iconeSistema() {
//        URL caminho = this.getClass().getResource("/devTec/imagens/logo.png");
//        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminho);
//        this.setIconImage(iconeTitulo);
//    }
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
//            java.util.logging.Logger.getLogger(frmHome.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmHome.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmHome.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmHome.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
//                new frmPOS1("").setVisible(true);
//            }
//        });
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
                new frmAdministradorFinancas1280X1024("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem59;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTreeMenu;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables

    public void limparTabela() {
        dataModel.setRowCount(0);
    }
    
}
