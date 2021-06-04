/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.utilizacoes;


import GestaoStock.controller.PermissaoControler;
import GestaoStock.controller.UsuarioController;
import GestaoStock.modelo.Acesso;
import GestaoStock.modelo.Utilizador;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sf.ce.conexao.BDConexao;

/**
 *
 * @author
 */
public class Tabla {

    private BDConexao conexao;
    private boolean[] editable = {false, true};

    private String sql;
    PermissaoControler controller = new PermissaoControler();
    Utilizador modelo  = new Utilizador();
    UsuarioController usuarios  = new UsuarioController();


    public void visualizar(JTable tabla, String query) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{"Permissões", "Selecionar"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };
        Acesso acesso = new Acesso();
        ArrayList<Acesso> list = controller.getAllByFilter(query);

        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[2];
                acesso = list.get(i);

                fila[0] = acesso.getAcesso();
                fila[1] = false;
                dt.addRow(fila);
            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(370);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void visualizarTabela(JTable tabla, String query, String head1, String head2) {

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(new String[]{head1, head2}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        ArrayList<Utilizador> list = usuarios.getAll();

        System.out.println("indice " + list.size());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[2];
                modelo = list.get(i);
                fila[0] = modelo.getCodUtilizador();
                fila[1] = modelo.getNomeCompleto();
                dt.addRow(fila);
            }

        }

        tabla.setModel(dt);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }
    
}   

class MyComboBoxEditor extends DefaultCellEditor {

    public MyComboBoxEditor(String[] items) {
        super(new JComboBox(items));
    }
}
