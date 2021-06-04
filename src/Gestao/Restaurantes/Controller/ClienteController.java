/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import entidades.Clientes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class ClienteController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public ClienteController(Connection con) {
        this.con = con;
    }

    public boolean Salvar(entidades.Clientes clientes) {
//        sql = "INSERT INTO clientes(\"Descricao\", \"Telefone\", \"Endereco\", \"Email\",\"CodigoStatus\",\"saldo\")VALUES (?,?, ?, ?, ?, ?)";
        sql = "INSERT INTO clientes(\"Descricao\", \"Telefone\", \"Endereco\", \"Email\",\"CodigoStatus\")VALUES (?, ?, ?, ?, ?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, clientes.getNome());
            ps.setInt(2, clientes.getTelefone());
            ps.setString(3, clientes.getEndereco());
            ps.setString(4, clientes.getEmail());
            ps.setInt(5, clientes.getCodigoStatus());
//            ps.setDouble(6, clientes.getSaldo());
//            System.out.println("Passou Aqui");
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return false;

    }

    public void Editar(entidades.Clientes clientes, int codigo) {

        sql = "UPDATE clientes SET \"Descricao\"=?, \"Telefone\"=?, \"Endereco\"=?,\"Email\"=?,\"CodigoStatus\"=? WHERE \"Codigo\" =" + codigo;
        System.out.println("Sql:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, clientes.getNome());
            ps.setInt(2, clientes.getTelefone());
            ps.setString(3, clientes.getEndereco());
            ps.setString(4, clientes.getEmail());
            //  ps.setString(5, clientes.getDataCadastro());
            ps.setInt(5, clientes.getCodigoStatus());
            // ps.setInt(6, clientes.getCodigo());
            System.out.println("Passou aqui");
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void Eliminar(int Codigo) {
        sql = "UPDATE clientes SET \"CodigoStatus\"=2  WHERE \"Codigo\" =" + Codigo;
//        System.out.println("SQl" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public ArrayList<String> getlistaNome() {
        sql = " SELECT  \"Descricao\" as Descricao FROM  \"clientes\"  WHERE \"CodigoStatus\"=1 ";
        System.out.println("Teste:" + sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<String> getlistaNomeBuscar(String designacao) {
        sql = " SELECT  \"Descricao\" as Descricao FROM  \"clientes\"  WHERE \"Descricao\" LIKE '" + designacao + "%' ";
        System.out.println("Teste:" + sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<entidades.Clientes> listar() {
        sql = " SELECT * FROM clientes c";
        ArrayList<Clientes> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.setCodigo(rs.getInt("Codigo"));
                clientes.setNome(rs.getString("Descricao"));
                clientes.setTelefone(rs.getInt("Telefone"));
                clientes.setEmail(rs.getString("Email"));
                clientes.setEndereco(rs.getString("Endereco"));
                clientes.setCodigoStatus(rs.getInt("CodigoStatus"));
                cl.add(clientes);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<Clientes> getListar(int codigo) {
        sql = " SELECT * FROM clientes Where \"Codigo\" =" + codigo;
        ArrayList<Clientes> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.setNome(rs.getString("Descricao"));
                clientes.setTelefone(rs.getInt("Telefone"));
                clientes.setEmail(rs.getString("Email"));
                clientes.setEndereco(rs.getString("Endereco"));
                cl.add(clientes);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public int Codigo(String nome) {
        sql = "SELECT \"Codigo\" as Codigo  FROM clientes Where \"Descricao\" ='" + nome + "'";
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
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
    public int getLastinsert() {
        sql = "SELECT MAX(\"Codigo\") as Codigo  FROM clientes ";
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
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
    public double getSaldo(int cod) {
        sql = "SELECT \"saldo\" as saldo  FROM clientes Where \"codigo\" ='" + cod + "'";
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("saldo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String Nomecliente(int Codigo) {
        sql = "SELECT Descricao FROM clientes Where Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

}
