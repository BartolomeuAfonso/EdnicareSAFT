/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;
import entidades.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class StockController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public StockController(Connection con) {
        this.con = con;
    }

    public boolean Salvar(Stock stock) {
        sql = "INSERT INTO stock(\"CodigoUsuario\", \"nFactura\", \"DataCompra\", \"DataEntrada\")\n"
                + "	VALUES (?, ?, ?, ?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock.getCodigoUsuario());
            ps.setString(2, stock.getNumeroFactura());
            ps.setString(3, stock.getDataCompra());
            ps.setString(4, stock.getDataEntreda());
            ps.execute();
//            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public void Editar(Stock stock, int Codigo) {

        sql = "UPDATE stock set \"QuantidadeMin\"=?,\"QuantidadeMax\"=?,\"QuantidadeActual\"=?,\"CodigoUsuario\"=?, \"DataCompra\"=?,  \"DataEntreda\"=? WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock.getQuantidadeMin());
            ps.setInt(2, stock.getQuantidadeMax());
            ps.setInt(3, stock.getQuantidadeActual());
            ps.setInt(4, stock.getCodigoUsuario());
            ps.setString(5, stock.getNumeroFactura());
            ps.setString(6, stock.getDataCompra());
            ps.setString(7, stock.getDataEntreda());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public int getLastInsert() {
        String sql = "SELECT MAX(\"Codigo\") as UltimoCodigo FROM stock";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UltimoCodigo");
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
