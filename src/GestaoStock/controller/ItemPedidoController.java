/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.ItemPedido;
import GestaoStock.modelo.Pedido;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class ItemPedidoController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarItemPedido(ItemPedido item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO item_pedido(codPedido,codProduto,qtdePedida,codStatus,obs,codUnidade) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, item.getCodPedido());
            prepara.setInt(2, item.getCodProduto());
            prepara.setDouble(3, item.getQtdePedida());
            prepara.setInt(4, 1);
            prepara.setString(5, item.getObs());
            prepara.setInt(6, item.getCodUnidade());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean atualizarPedido(ItemPedido item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  item_pedido set codProduto = ?,qtdePedida = ?,obs = ?,codUnidade=? WHERE codItem=? ";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, item.getCodProduto());
            prepara.setDouble(2, item.getQtdePedida());
            prepara.setString(3, item.getObs());
            prepara.setInt(4, item.getCodUnidade());
            prepara.setInt(5, item.getCodItem());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean eliminarItemPedido(ItemPedido item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  item_pedido set statusPedido = ? WHERE codItem=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, 3);
            prepara.setInt(2, item.getCodItem());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

   

}
