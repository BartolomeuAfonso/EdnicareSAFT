/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Pedido;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class PedidoController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarPedido(Pedido pedido) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO pedido(dataPedido,dataentregar,observacaogeral,statusPedido,codfarmanceutico,codFornecedor) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, pedido.getDataPedido());
            prepara.setString(2, pedido.getDataEntregar());
            prepara.setString(3, pedido.getObservacao());
            prepara.setInt(4, 1);
            prepara.setInt(5, pedido.getCodFarmanceutico());
            prepara.setInt(6, pedido.getCodFornecedor());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean atualizarPedido(Pedido pedido) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  pedido set dataPedido = ?,dataEntregar = ?,observacaoGeral = ?,codFornecedor = ? WHERE codPedido=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, pedido.getDataPedido());
            prepara.setString(2, pedido.getDataEntregar());
            prepara.setString(3, pedido.getObservacao());
            prepara.setInt(4, pedido.getCodFornecedor());
            prepara.setInt(5, pedido.getCodPedido());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean eliminarPedido(Pedido pedido) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  pedido set status = ? WHERE codPedido=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, 3);
            prepara.setInt(2, pedido.getCodPedido());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

   public int getAllPedidosDia(String data) throws SQLException {
        conexao.Connectando();
        String sql = " SELECT count(ip.codPedido) pedidos   FROM pedido p inner join item_pedido ip on ip.codPedido=p.codPedido where dataEntregar ='"+data+"'";

        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("pedidos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

}
