/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.conexao;

import CLINICA.modelo.Config;
import CLINICA.modelo.IO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoBancos {

    public static Connection conn;
    Config conf = (Config) new IO().read("conf");
//    private final String caminho = "jdbc:mysql://" + conf.getIp() + ":3306/ednicareempresa";
    // Biscino do Ba
//    private final String caminho = "jdbc:mysql://" + conf.getIp() + ":3306/empresa";
    private final String caminho = "jdbc:mysql://" + conf.getIp() + ":3306/ednicare";
//    private final String driver = "com.mysql.jdbc.Driver";
    private final String usuario = conf.getUsuario();
    private final String senha = conf.getSenha();
    PreparedStatement ps;
    public Statement stm;
    public ResultSet rs;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Config conf = (Config) new IO().read("conf");
                Class.forName("com.mysql.jdbc.Driver");
                conn = java.sql.DriverManager.getConnection("jdbc:mysql://" + conf.getIp() + ":3306/ednicare", conf.getUsuario(), conf.getSenha());
//                conn = java.sql.DriverManager.getConnection("jdbc:mysql://" + conf.getIp() + ":3306/ednicareempresa", conf.getUsuario(), conf.getSenha());
//               conn = java.sql.DriverManager.getConnection("jdbc:mysql://"+conf.getIp()+":3306/empresa", conf.getUsuario(), conf.getSenha());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoBancos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return conn;
    }

    public Connection ConexaoBD() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(caminho, usuario, senha);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexaoBancos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return conn;
    }

    public void Connectando() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(caminho, usuario, senha);
            stm = conn.createStatement();

        } catch (SQLException e) {
            System.out.println("Erro:" + e);
            JOptionPane.showMessageDialog(null, "Informação indisponive de momento!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBancos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = stm.executeQuery(query);
            return resultSet;
        } catch (Exception ex) {
            ex.printStackTrace();
            //showMessage("Falha ao Carregar os Dados");
        }

        return resultSet;
    }

    public void executaSQL(String sql) {
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
        }
    }

    public ResultSet ejecutarSQLSelect(String sql) {
        //ResultSet resultado;
        try {
            ps = conn.prepareStatement(sql);
            // ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //  resultado = sentencia.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
            return null;
        }
    }

}
