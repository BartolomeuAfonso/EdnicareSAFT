/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class VendaItensController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public VendaItensController(Connection con) {
        this.con = con;
    }

    public void Salvar(entidades.VendaItem vendaItem) {
        sql = "INSERT INTO \"vendaItem\"(\"CodigoVenda\", \"Quantidade\", \"ValorPAgar\", \"ValorUnitario\", \"CodigoProduto\")VALUES (?,?,?,?,?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vendaItem.getCodigoVenda());
            ps.setInt(2, vendaItem.getQuantidade());
            ps.setDouble(3, vendaItem.getValorTotal());
            ps.setDouble(4, vendaItem.getPrecoUnitario());
            ps.setInt(5, vendaItem.getCodigoProduto());
            ps.execute();
            ps.close();
            // JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
            //  System.out.println("Codigo da Venda:" + vendaItem.getCodigoVenda());

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

}
