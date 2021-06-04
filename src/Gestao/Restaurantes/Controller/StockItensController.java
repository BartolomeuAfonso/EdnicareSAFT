/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;


import entidades.StockMovimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramos Soft
 */
public class StockItensController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public StockItensController(Connection con) {
        this.con = con;
    }

    public boolean Salvar(StockMovimento stockMovimento) {

        sql = "INSERT INTO public.\"stockMovimento\"(\"CodigoStock\", \"CodigoProduto\", \"CodigoArmazen\",\"QuantidadeMin\",\"QuantidadeMax\")\n"
                + "	VALUES (?,?, ?,?,?)";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stockMovimento.getCodigoStock());
            ps.setInt(2, stockMovimento.getCodigoProduto());
            ps.setInt(3, stockMovimento.getCodigoArmazem());
            ps.setInt(4, stockMovimento.getQuantidadeMin());
            ps.setInt(5, stockMovimento.getQuantidadeMax());
            ps.execute();
//            ps.close();
            return true;
            ///   JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public void Actualizar() {
        sql = "";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public int getQuantidadeMin(int codigo) {
        String sql = "SELECT\"QuantidadeMin\" as QuantidadeMin FROM \"stockMovimento\" WHERE \"CodigoProduto\"=" + codigo;
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("QuantidadeMin");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getQuantidadeActual(int codigo) {
        String sql = "SELECT\"QuantidadeMax\" as QuantidadeMax FROM \"stockMovimento\" WHERE \"CodigoProduto\"=" + codigo;
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("QuantidadeMax");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void actualizarQuantidade(int Codigo, int quantidade) {

        sql = "UPDATE  \"stockMovimento\" set \"QuantidadeMax\"= \"QuantidadeMax\" +" + quantidade + " WHERE \"CodigoProduto\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
//            JOptionPane.showMessageDialog(null, "Quantidade Adicionado com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void diminuirQuantidade(int Codigo, int quantidade) {

        sql = "UPDATE  \"stockMovimento\" set \"QuantidadeMax\"= \"QuantidadeMax\" - " + quantidade + " WHERE \"CodigoProduto\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            // JOptionPane.showMessageDialog(null, "Quantidade diminuido com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void actualizar(int Codigo, int quantidade) {

        sql = "UPDATE  \"stockMovimento\" set \"QuantidadeMax\"=" + quantidade + " WHERE \"CodigoProduto\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Quantidade Actualizado com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }
}
