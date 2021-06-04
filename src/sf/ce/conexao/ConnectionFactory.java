/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.conexao;

import CLINICA.modelo.Config;
import CLINICA.modelo.IO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Config conf = (Config) new IO().read("conf");
                Class.forName("com.mysql.jdbc.Driver");
//                conn = java.sql.DriverManager.getConnection("jdbc:mysql://"+conf.getIp()+":3306/empresa",conf.getUsuario(),conf.getSenha());
//                conn = java.sql.DriverManager.getConnection("jdbc:mysql://"+conf.getIp()+":3306/ednicareempresa",conf.getUsuario(),conf.getSenha());
                conn = java.sql.DriverManager.getConnection("jdbc:mysql://" + conf.getIp() + ":3306/ednicare", conf.getUsuario(), conf.getSenha());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return conn;
    }

}
