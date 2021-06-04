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
public class TipoPagamento {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public TipoPagamento(Connection con) {
        this.con = con;
    }

    public void Salvar(entidades.TipoFormaPagamento tfp) {

        sql = "INSERT INTO formaTipoPagameno(\"CodigoVenda\",\"CodigoPagamento\")VALUES (?, ?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, tfp.getCodigoVenda());
            ps.setInt(2, tfp.getCodigoPagamento());
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void Eliminar(int Codigo) {
        sql = "DELET FROM formaTipoPagameno WHERE Codigo = " + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");

        } catch (SQLException e) {
        }
    }
}
