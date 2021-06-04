/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.PedidoProduto;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerPedidoProduto {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
     Connection con;

    public ControllerPedidoProduto(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public void Salvar(PedidoProduto pedidoProduto) {
        //conexao.Connectando();
        sql = "INSERT INTO pedidoproduto(dataPedido,codigoCliente,codigoUtilizador)values(?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, (Date) pedidoProduto.getDataPedido());
            ps.setInt(2, pedidoProduto.getCodigoCliente());
            ps.setInt(3, pedidoProduto.getUtilizador());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
     //   conexao.DesconectaBanco();
    }

    public int getLastPedidoProduto() {
        try {
           // conexao.Connectando();
            String sql = "select max(idPedido) as max from pedidoproduto";
            System.out.println("Teste:" + sql);
            PreparedStatement prepara = con.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }
}
