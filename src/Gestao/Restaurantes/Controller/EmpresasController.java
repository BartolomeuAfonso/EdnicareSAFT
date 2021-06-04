/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import entidades.Empresas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class EmpresasController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public EmpresasController(Connection con) {
        this.con = con;
    }

    public void salvar(Empresas empresas) {
        sql = "INSERT INTO public.empresas(\n"
                + "	\"NomeEmpresa\", \"NomeGerente\", \"Nif\", \"Telefone\", \"Conta1\", \"Conta2\", \"Endereco\", \"Email\", \"RazaoSocial\", \"Logotipo\")\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getNomeGerente());
            ps.setString(3, empresas.getNif());
            ps.setString(4, empresas.getTelefones());
            ps.setString(5, empresas.getConta1());
            ps.setString(6, empresas.getConta2());
            ps.setString(7, empresas.getEndereco());
            ps.setString(8, empresas.getEmail());
            ps.setString(9, empresas.getRazaoSocial());
            ps.setString(10, empresas.getLogotipo());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void editar(Empresas empresas, int codigo) {
        sql = "UPDATE public.empresas\n"
                + "	SET  \"NomeEmpresa\"=?, \"NomeGerente\"=?, \"Nif\"=?, \"Telefone\"=?, \"Conta1\"=?, \"Conta2\"=?, \"Endereco\"=?, \"Email\"=?, \"RazaoSocial\"=?, \"Logotipo\"=?\n"
                + "	WHERE \"Codigo\"=" + codigo;

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getNomeGerente());
            ps.setString(3, empresas.getNif());
            ps.setString(4, empresas.getTelefones());
            ps.setString(5, empresas.getConta1());
            ps.setString(6, empresas.getConta2());
            ps.setString(7, empresas.getEndereco());
            ps.setString(8, empresas.getEmail());
            ps.setString(9, empresas.getRazaoSocial());
            ps.setString(10, empresas.getLogotipo());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public String getLogotipo() {
        sql = "SELECT \"Logotipo\" as Logotipo FROM empresas";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Logotipo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getNomeEmpresa() {
        sql = "SELECT \"NomeEmpresa\" as NomeEmpresa FROM empresas";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("NomeEmpresa");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public ArrayList<Empresas> listar(String logo) {
        sql = "SELECT  \"Codigo\", \"NomeEmpresa\", \"NomeGerente\", \"Nif\", \"Telefone\", \"Conta1\", \"Conta2\", \"Conta3\", \"Endereco\", \"Email\", \"RazaoSocial\" fROM public.empresas \n"
                + "WHERE \"Codigo\"=1";
//        System.out.println("Teste" + sql);
        ArrayList<Empresas> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresas empresas = new Empresas();
                empresas.setCodigo(rs.getInt("Codigo"));
                empresas.setNomeEmpresa(rs.getString("NomeEmpresa"));
                empresas.setNomeGerente(rs.getString("NomeGerente"));
                empresas.setNif(rs.getString("Nif"));
                empresas.setRazaoSocial(rs.getString("RazaoSocial"));
                empresas.setConta1(rs.getString("Conta1"));
                empresas.setConta2(rs.getString("Conta2"));
                empresas.setEmail(rs.getString("Email"));
                empresas.setEndereco(rs.getString("Endereco"));
                empresas.setTelefones(rs.getString("Telefone"));
                list.add(empresas);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }
    public ArrayList<Empresas> listar() {
        sql = "SELECT  \"Codigo\", \"NomeEmpresa\", \"NomeGerente\", \"Nif\", \"Telefone\", \"Conta1\", \"Conta2\", \"Conta3\", \"Endereco\", \"Email\", \"RazaoSocial\" fROM public.empresas \n"
                + "WHERE \"Codigo\"=1";
//        System.out.println("Teste" + sql);
        ArrayList<Empresas> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresas empresas = new Empresas();
                empresas.setCodigo(rs.getInt("Codigo"));
                empresas.setNomeEmpresa(rs.getString("NomeEmpresa"));
                empresas.setNomeGerente(rs.getString("NomeGerente"));
                empresas.setNif(rs.getString("Nif"));
                empresas.setRazaoSocial(rs.getString("RazaoSocial"));
                empresas.setConta1(rs.getString("Conta1"));
                empresas.setConta2(rs.getString("Conta2"));
                empresas.setEmail(rs.getString("Email"));
                empresas.setEndereco(rs.getString("Endereco"));
                empresas.setTelefones(rs.getString("Telefone"));
                list.add(empresas);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }
}
