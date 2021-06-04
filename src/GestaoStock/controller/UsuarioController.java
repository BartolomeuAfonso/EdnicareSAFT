/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Utilizador;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class UsuarioController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean InserirUsuario(Utilizador Usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        conexao.Connectando();
        String inserir = "INSERT INTO usuario(nomeCompleto,username,senha,acesso,areafuncional,status)VALUES(?,?,?,md5(?),?,?)";

        try {
//            MessageDigest md = MessageDigest.getInstance("md5");//ou SHA-256.
//            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            for (byte b : messageDigest) {
//                sb.append(String.format("%02X", 0xFF & b));//Máscara
//            }
//            String senhaHex = sb.toString();//Converter em string
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, Usuario.getNomeCompleto());
            prepara.setString(2, Usuario.getUserName());
            prepara.setString(3, Usuario.getSenha());
            prepara.setInt(4, Usuario.getAcesso());
            prepara.setInt(5, Usuario.getAreafuncional());
            prepara.setInt(6, 1);
            prepara.execute();
            prepara.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!" + ex);
        }
        return false;
    }

    public boolean UpdateUsuario(Utilizador Usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        conexao.Connectando();
        String inserir = "update  usuario set nomeCompleto=?,username=?,acesso=?,areafuncional=?  where codUser = ?";

        try {

            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, Usuario.getNomeCompleto());
            prepara.setString(2, Usuario.getUserName());
            prepara.setInt(3, Usuario.getAcesso());
            prepara.setInt(4, Usuario.getAreafuncional());
            prepara.setInt(5, Usuario.getCodUtilizador());
            prepara.execute();
            prepara.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!" + ex);
        }
        return false;
    }

    public boolean UpdatePassword(Utilizador Usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        conexao.Connectando();
        String inserir = " update  usuario set senha = ? where codUser = ? ";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, Usuario.getSenha());
            prepara.setInt(2, Usuario.getCodUtilizador());
            prepara.execute();
            prepara.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!" + ex);
        }
        return false;
    }

    public ArrayList<String> getNomeporCodigo(int codigo) {
        //    conexao.Connectando();
        String sql = "SELECT nomeCompleto FROM usuario s where codUser=" + codigo;
        System.out.println("SQL:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigoUtilizador(String designacao) {
        conexao.Connectando();
        String sql = "SELECT codigoUsuario from usuario where username ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoUsuario");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getCodigoUtilizador1(String designacao) {
        conexao.Connectando();
        String sql = "SELECT codUser from usuario where username ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codUser");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public boolean acesso_usuario(int usuario, int acesso) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO acesso_usuario(usuario,acesso)VALUES(?,?)";

        try {

            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, usuario);
            prepara.setInt(2, acesso);
            prepara.execute();
            prepara.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!!!" + ex);
        }
        return false;
    }

    public void removerUsuario(Utilizador Usuario) {
        conexao.Connectando();
        String eliminar = "UPDATE usuario set status = 2 WHERE Codigos=?";
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(eliminar);
            prepara.setLong(1, Usuario.getCodUtilizador());
            prepara.execute();
            JOptionPane.showMessageDialog(null, "Utilizador excluido do sistema!");
            prepara.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

    }

    public List RetornarObjectoUsuario() {
        conexao.Connectando();
        List ObjectoCompleto = new ArrayList();
        String linha = "SELECT *FROM usuario order by 1";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(linha);
            ResultSet resultado = prepara.executeQuery();
            while (resultado.next()) {
                ObjectoCompleto.add(resultado.getInt("Codigo"));
                ObjectoCompleto.add(resultado.getString("Nome"));
                ObjectoCompleto.add(resultado.getString("UserName"));
                ObjectoCompleto.add(resultado.getString("Senha"));
                ObjectoCompleto.add(resultado.getString("AreaFuncionamento"));
                ObjectoCompleto.add(resultado.getInt("TipoUsuarios_Codigo"));
                ObjectoCompleto.add(resultado.getInt("status_Codigo"));
            }
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return ObjectoCompleto;
    }

    public ArrayList<Utilizador> getAll() {
        conexao.Connectando();
        String query = "select * from usuario";
        ArrayList lista = new ArrayList<>();

        int next = 0;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();

            while (rs.next()) {
                lista.add(
                        new Utilizador(
                                rs.getInt("codUser"), rs.getInt("acesso"),
                                rs.getInt("areafuncional"), rs.getInt("status"),
                                rs.getString("nomecompleto"), rs.getString("username"),
                                rs.getString("senha")
                        )
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Utilizador> getAllByUsername(String descricao) {
        conexao.Connectando();
        String query = "SELECT\n"
                + "     usuario.`codUser` AS usuario_codUser,\n"
                + "     usuario.`nomeCompleto` AS usuario_nomeCompleto,\n"
                + "     usuario.`username` AS usuario_username,\n"
                + "     usuario.`areafuncional` AS usuario_areafuncional,\n"
                + "     nivelacesso.`designacao` AS nivelacesso_designacao,\n"
                + "     areafuncional.`descricaoArea` AS areafuncional_descricaoArea\n"
                + "FROM\n"
                + "     `nivelacesso` nivelacesso INNER JOIN `usuario` usuario ON nivelacesso.`codPermissao` = usuario.`acesso`\n"
                + "     INNER JOIN `areafuncional` areafuncional ON usuario.`areafuncional` = areafuncional.`codArea`"
                + " WHERE(nomeCompleto like '" + descricao.trim() + "')";
        ArrayList lista = new ArrayList<>();

        int next = 0;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();

            while (rs.next()) {
                lista.add(
                        new Utilizador(
                                rs.getInt("usuario_codUser"), rs.getInt("usuario_codUser"),
                                rs.getInt("usuario_codUser"), rs.getInt("usuario_codUser"),
                                rs.getString("usuario_nomeCompleto"), rs.getString("usuario_username"),
                                rs.getString("usuario_username")
                        )
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public String getNiveAcesso(String username) {
        conexao.Connectando();
        String query = "SELECT\n"
                + "     usuario.`nomecompleto` AS nomecompleto,\n"
                + "     nivelacesso.`designacao` AS nivelacesso_designacao\n"
                + "FROM\n"
                + "     `nivelacesso` nivelacesso INNER JOIN `usuario` usuario ON nivelacesso.`codPermissao` = usuario.`acesso` "
                + "WHERE(nomecompleto like '" + username.trim() + "')";

        int next = 0;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();
            while (rs.next()) {
                return rs.getString("nivelacesso_designacao");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public boolean loginExistente(String user, String passe) {
        conexao.Connectando();
        String query = "SELECT codUser FROM usuario WHERE(Username = '" + user
                + "' AND senha = md5('" + passe + "') AND Status = 1)";
        System.out.println("Sql:" + query);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }

        return false;
    }

    public String getSenha(String username) {
        conexao.Connectando();
        String query = "SELECT senha  from usuario WHERE(nomecompleto like '" + username.trim() + "')";

        int next = 0;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();
            while (rs.next()) {
                return rs.getString("senha");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public String IncriptySenha(String senha) {
        try {
            conexao.Connectando();
            MessageDigest md = MessageDigest.getInstance("md5");//ou SHA-256.
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();

            for (byte b : messageDigest) {
                sb.append(String.format("%02X", 0xFF & b));//Máscara
            }
            String senhaHex = sb.toString();//Converter em string

            return senhaHex;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getAreaFuncional(String username) {
        conexao.Connectando();
        String query = "SELECT\n"
                + "     areafuncional.`descricaoArea` AS areafuncional_descricaoArea,\n"
                + "     usuario.`nomeCompleto` AS usuario_nomeCompleto\n"
                + "FROM\n"
                + "     `areafuncional` areafuncional INNER JOIN `usuario` usuario ON areafuncional.`codArea` = usuario.`areafuncional` WHERE(nomecompleto like '%" + username.trim() + "%')";

        int next = 0;
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(query);
            ResultSet rs = prepara.executeQuery();
            while (rs.next()) {
                return rs.getString("areafuncional_descricaoArea");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "";
    }

}
