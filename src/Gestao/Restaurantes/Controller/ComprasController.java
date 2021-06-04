/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.CompraProduto;
import entidades.ItensCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramos Soft
 */
public class ComprasController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public ComprasController(Connection con) {
        this.con = con;
    }

    public void save(CompraProduto compras) {
        sql = "INSERT INTO compra_produto(\n"
                + "           \"dataCompra\", \"hora\", \"fatura\", \"dataFatura\", \"idFornecedor\", \n"
                + "            \"pagamento\", \"dataEntrega\", \"horaEntrega\", \"status\", \"total\")\n"
                + "    VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
        try {
//            con = new Conexao().ConexaoBD();
            java.sql.Date data = new java.sql.Date(compras.getDataCompra().getTime());
            java.sql.Date data1 = new java.sql.Date(compras.getDataFatura().getTime());
            java.sql.Date data2 = new java.sql.Date(compras.getDataEntrega().getTime());
            ps = con.prepareStatement(sql);
            ps.setDate(1, data);
            ps.setString(2, compras.getHora());
            ps.setString(3, compras.getFatura());
            ps.setDate(4, data1);
            ps.setInt(5, compras.getFornecedor());
            ps.setInt(6, compras.getFormaPagamento());
            ps.setDate(7, data2);
            ps.setString(8, compras.getHoraEntrega());
            ps.setString(9,"ATENDIDO");
            ps.setDouble(10,compras.getValorCompra());
            ps.execute();
            ps.close();
            //       JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }
    public void saveItens(ItensCompra items) {
        sql = "INSERT INTO item_compra(\n"
                + "           \"idCompra\", \"idProduto\", \"qtd\", \"valorUnitario\", \"valorTotal\")\n"
                + "    VALUES (?, ?, ?, ?, ?)";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, items.getIdCompra());
            ps.setInt(2, items.getIdProduto());
            ps.setInt(3, items.getQtd());
            ps.setDouble(4, items.getValorUnitario());
            ps.setDouble(5, items.getValorTotal());
           
            ps.execute();
            ps.close();
            //       JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    
    public void deleteItens(int Codigo) {
        sql = "delete from item_compra  WHERE \"Codigo\"=" + Codigo + "";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC" + e);

        }
    }
    public void delete(int Codigo) {
        sql = "delete from compra_produto  WHERE \"Codigo\"=" + Codigo + "";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso:");
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC" + e);

        }
    }
    
       public boolean exist(int id) {
        sql = "SELECT \"Codigo\" as Codigo  FROM item_compra Where \"idProduto\" =" + id + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

}
