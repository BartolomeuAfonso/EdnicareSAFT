/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.ItemPedido;
import GestaoStock.modelo.ItemSaida;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class ItemSaidaController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarItemSaida(ItemSaida item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO item_saida(codProduto,qtdeSaida,codSaida,codUnidade) VALUES(?,?,?,?)";
        System.out.println("Sql:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, item.getCodProduto());
            prepara.setInt(2, item.getQtdeSaida());
            prepara.setInt(3, item.getCodSaida());
            prepara.setInt(4, item.getCodUnidade());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean atualizarPedido(ItemSaida item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  item_saida set codProduto = ?,qtdeSaida = ? WHERE codItem=? ";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, item.getCodProduto());
            prepara.setInt(2, item.getQtdeSaida());
            prepara.setInt(3, item.getCodItem());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean eliminarItemPedido(ItemSaida item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  item_saida set status = ? WHERE codItem=?";

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
