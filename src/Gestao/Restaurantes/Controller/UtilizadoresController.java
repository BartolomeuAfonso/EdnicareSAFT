/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Utilizadores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramos Soft
 */
public class UtilizadoresController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public UtilizadoresController(Connection con) {
        this.con = con;
    }

    public void salvar(Utilizadores utilizadores) {

        sql = "INSERT INTO usuarios(\"NomeCompleto\",\"NomeUser\", \"SenhaUser\", \"SenhaAdmin\", \"CodigoTipoUsuario\",\"CodigoStatus\", \"codigoPontoVenda\") VALUES (?,?,md5(?),md5(?),?,?,?)";
        //  //System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, utilizadores.getNomeCompleto());
            ps.setString(2, utilizadores.getNomeUsuario());
            ps.setString(3, utilizadores.getSenhaUsuario());
            ps.setString(4, utilizadores.getSenhaAdmin());
            ps.setInt(5, utilizadores.getCodigoTipoUsuario());
            ps.setInt(6, 1);
            ps.setInt(7, utilizadores.getVendas());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
            e.printStackTrace();
        }
    }

    public void editar(Utilizadores utilizadores, int codigo) {

        sql = "UPDATE  usuarios SET \"NomeCompleto\" =?,\"NomeUser\"=?, \"SenhaUser\"= md5(?), \"CodigoTipoUsuario\"=?  WHERE  \"Codigo\" =" + codigo;
        //   System.out.println("Teste:" + sql);

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, utilizadores.getNomeCompleto());
            ps.setString(2, utilizadores.getNomeUsuario());
            ps.setString(3, utilizadores.getSenhaUsuario());
            ps.setInt(4, utilizadores.getCodigoTipoUsuario());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public int CodigoUsuario(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM \"usuarios\" WHERE \"NomeUser\" like '" + nome + "'";
        //    System.out.println("Teste" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int CodigoTipoUsuario(String nome) {
        sql = "SELECT \"CodigoTipoUsuario\" AS Codigo FROM \"usuarios\" WHERE \"NomeUser\" like '" + nome + "'";
        //     System.out.println("Teste" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public int CodigoPontoVendaUsuario(String nome) {
        sql = "SELECT \"codigoPontoVenda\" AS Codigo FROM \"usuarios\" WHERE \"NomeUser\" like '" + nome + "'";
        //     System.out.println("Teste" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int CodigoUsuarioCompleto(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM \"usuarios\" WHERE \"NomeCompleto\" like '" + nome + "'";
//        System.out.println("Teste" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public int CodigoUsuarioCompleto1(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM \"usuarios\" WHERE \"NomeUser\" like '" + nome + "'";
//        System.out.println("Teste" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public ArrayList<Utilizadores> getListar(int codigo) {
        sql = " SELECT * FROM usuarios Where \"Codigo\" =" + codigo;
        ArrayList<Utilizadores> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Utilizadores utilizadores = new Utilizadores();
                utilizadores.setNomeCompleto(rs.getString("NomeCompleto"));
                utilizadores.setNomeUsuario(rs.getString("NomeUser"));
                utilizadores.setSenhaUsuario(rs.getString("SenhaUser"));
                cl.add(utilizadores);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public void Eliminar(int Codigo) {
        sql = "UPDATE usuarios SET \"CodigoStatus\"=2  WHERE \"Codigo\" =" + Codigo;
        //   System.out.println("SQl" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public ArrayList<String> getlistarGarco() {
        ArrayList<String> al = new ArrayList<>();
        sql = "SELECT \"NomeCompleto\" AS NOMECOMPLETO FROM \"usuarios\" WHERE \"CodigoTipoUsuario\"=1 ORDER BY 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add(rs.getString("NOMECOMPLETO"));
            }
            ps.close();
        } catch (SQLException e) {

            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return al;
    }

    public ArrayList<String> getlistarUtilizador() {
        ArrayList<String> al = new ArrayList<>();
        sql = "SELECT \"NomeCompleto\" AS NOMECOMPLETO FROM \"usuarios\"  ORDER BY 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add(rs.getString("NOMECOMPLETO"));
            }
//            ps.close();
        } catch (SQLException e) {

            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return al;
    }
    public ArrayList<String> getlistarUtilizadorAdmin() {
        ArrayList<String> al = new ArrayList<>();
        sql = "SELECT \"NomeCompleto\" AS NOMECOMPLETO FROM \"usuarios\" WHERE \"CodigoTipoUsuario\"=1  ORDER BY 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                al.add(rs.getString("NOMECOMPLETO"));
            }
//            ps.close();
        } catch (SQLException e) {

            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return al;
    }

    public boolean login(String user, String pass) {
        sql = "select \"Codigo\" from  \"usuarios\" where \"NomeUser\" like'" + user + "' and \"SenhaUser\" = md5('" + pass + "') and \"CodigoStatus\" =1";
//              System.out.println("Teste:"+sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {

            return false;
        }
        return false;
    }
    public String getSenha(String user) {
        sql = "select \"SenhaUser\" from  \"usuarios\" where \"NomeUser\" ='" + user + "'  and \"CodigoStatus\" =1";
        //      System.out.println("Teste:"+sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException ex) {

            return "";
        }
        return "";
    }
    public String getUsernameByCod(int user) {
        sql = "select \"NomeUser\" from  \"usuarios\" where \"Codigo\" =" + user + "";
        //      System.out.println("Teste:"+sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException ex) {

            return "";
        }
        return "";
    }
   
}
