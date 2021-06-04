/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.PedidoProdutoItens;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerItensPedido {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
     Connection con;

    public ControllerItensPedido(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public void salvar(PedidoProdutoItens pedidoProdutoItens) {
       // conexao.Connectando();
        sql = "INSERT INTO pedidoprodutoitens(codigoProduto,codigoPedidoProduto,quantidade)values(?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoProdutoItens.getCodigoProduto());
            ps.setInt(2, pedidoProdutoItens.getCodigoPedidoProduto());
            ps.setInt(3, pedidoProdutoItens.getQuantidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }
}
